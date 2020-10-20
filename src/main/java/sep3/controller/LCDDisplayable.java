package sep3.controller;

// バスLEDアレイへの表示用のインタフェース（バス、レジスタが実装してないとダメ）
public interface LCDDisplayable {
	int getValue();                            // バスやレジスタが現在保持している値
}
