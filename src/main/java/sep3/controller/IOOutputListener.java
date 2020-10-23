package sep3.controller;

import sep3.Model;
import sep3.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class IOOutputListener implements PropertyChangeListener {
	private final Model model;
	private final View view;

	public IOOutputListener(Model m, View v) {
		model = m;
		view = v;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// モデル上で出力行為があったら、その値を取り出してビュー上の出力LEDを点灯させる
		int i = model.getMemory().getIOValue().getValue();
		view.getDataOutputLED().setData(i);
	}
}
