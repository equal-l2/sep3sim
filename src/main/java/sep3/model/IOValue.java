package sep3.model;

import java.util.Observable;

// メモリマップトI/OでLEDに出力したい場合、その事実をモデルからビューに伝えるための変数
public class IOValue extends Observable {
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int v) {
		value = v;
		setChanged();
		notifyObservers(); // 出力すべきものが来たので、ビューに知らせて表示してもらう
	}
}
