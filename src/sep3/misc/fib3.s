     . = 0xF800
     ; 動作テスト
     MOV #65504, R0  ; MMIOアドレスをR0へ
     MOV (R0), (R6)+ ; MMIOの入力をスタックに積む (n)
     MOV #0, (R6)+   ; 引数をスタックに積む (a)
     MOV #1, (R6)+   ; 引数をスタックに積む (b)
     RJS FIB_TAIL    ; 呼び出し
     MOV R3, (R0)    ; MMIOに戻り値を出力
     HLT

; フィボナッチ数計算手続
; (末尾再帰版)
; R3に結果を入れて返す
;
; R0: アドレス
; R1~R3: 引数のコピー

FIB_TAIL: ; レジスタ退避
     MOV R0, (R6)+
     MOV R1, (R6)+
     MOV R2, (R6)+

     ; 引数をR1へ入れる
     MOV R6, R0    ; スタックトップのアドレスをコピー
     SUB #7, R0    ; 第1引数のアドレスまでずらす

     MOV (R0)+, R1 ; R1 <- n
     MOV (R0)+, R2 ; R2 <- a
     SUB #1, R1    ; 
     RBN FIN       ; n == 0 ?

     MOV (R0), R3  ; R3 <- b
     ADD R2, R3    ; R3 += a

     MOV R1, (R6)+
     MOV R3, (R6)+
     MOV R2, (R6)+

     RJS FIB_TAIL  ; R3はもう使わないので
                   ; 戻り値で壊れてもよい
     SUB #3, R6    ; スタックを元に戻す
     RJP FEND

FIN: MOV R2, R3    ; n = 0 のとき a を返す

FEND:; レジスタ復帰
     MOV -(R6), R2
     MOV -(R6), R1
     MOV -(R6), R0

     RET
