     . = 0xF800
     ; 動作テスト
     MOV #65504, R0  ; MMIOアドレスをR0へ
     MOV (R0), (R6)+ ; MMIOの入力をスタックに積む
     RJS FIB         ; FIB
     MOV R3, (R0)    ; MMIOに戻り値を出力
     HLT

; フィボナッチ数計算手続 (仕様準拠)
; R3に結果を入れて返す
;
; (R6-1) : リターンアドレス
; (R6-2) : 引数
; R0: アドレス
; R1: 引数のコピー
; R2: 結果の一時保存領域

FIB: ; レジスタ退避
     MOV R0, (R6)+
     MOV R1, (R6)+
     MOV R2, (R6)+

     ; 引数をR1へ入れる
     MOV R6, R0    ; スタックトップのアドレスをコピー
     SUB #5, R0    ; 引数のアドレスまでずらす
     MOV (R0), R1  ; 引数をR1へコピー
     MOV R6, R0    ; R0は元の値に戻しておく

     ; 引数チェック
     SUB #1, R1    ; R1 = N - 1
     RBZ ONE
     SUB #1, R1    ; R1 = N - 2
     RBZ ONE

     ; 再帰呼出
     CLR R2        ; R2を初期化

     ; FIB(N-2)の計算
     MOV R1, (R6)+ ; N-2をスタックに積む
     RJS FIB       ; FIB(N-2)
     SUB #1, R6    ; SPを元に戻す
     ADD R3, R2    ; 戻り値をR2へ足す

     ; FIB(N-1)の計算
     ADD #1, R1    ; R1 -> N-1
     MOV R1, (R6)+ ; N-1をスタックに積む
     RJS FIB       ; FIB(N-1)
     SUB #1, R6    ; SPを元に戻す
     ADD R3, R2    ; 戻り値をR2へ足す

     ; 計算結果を戻り値領域に格納
     MOV R2, R3
     RJP FIN

ONE: ; 引数が1または2のとき
     MOV #1, R3

FIN: ; レジスタ復帰
     MOV -(R6), R2
     MOV -(R6), R1
     MOV -(R6), R0

     RET
