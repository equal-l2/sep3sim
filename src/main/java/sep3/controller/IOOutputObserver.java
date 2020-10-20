package sep3.controller;

import sep3.Model;
import sep3.View;

import java.util.Observable;
import java.util.Observer;

public class IOOutputObserver implements Observer {
	private final Model model;
	private final View view;

	public IOOutputObserver(Model m, View v) {
		model = m;
		view = v;
	}

	public void update(Observable o, Object arg) {
		//System.out.println("enter Observer DataOutputLED(IOOutputObserver: " + i);
		// モデル上で出力行為があったら、その値を取り出してビュー上の出力LEDを点灯させる
		int i = model.getMemory().getIOValue().getValue();
		view.getDataOutputLED().setData(i);
	}
}
