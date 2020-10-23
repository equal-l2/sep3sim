package sep3.controller;

import sep3.Model;
import sep3.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HLTLampListener implements PropertyChangeListener {
	private final Model model;
	private final View view;

	public HLTLampListener(Model m, View v) {
		model = m;
		view = v;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// モデル上のHLTランプ状態を、ビュー上のHLTランプに反映させる
		if (model.getCPU().getHaltLamp().isOn()) {
			view.getHltLED().on();
		} else {
			view.getHltLED().off();
		}
	}
}
