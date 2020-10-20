package sep3.model;

import java.util.Observable;

// モデル上のHLT, ILL状態をビューに通知するための変数
public class OnOffFlag extends Observable {
	private boolean on = false;   // 起動時はoff

	// フラグの変更をビューに通知する
	public void toggle() {
		on = !on;
		setChanged();
		notifyObservers();
	}

	public void on() {
		on = true;
		setChanged();
		notifyObservers();
	}

	public void off() {
		on = false;
		setChanged();
		notifyObservers();
	}

	public boolean isOn() {
		return on;
	}
}
