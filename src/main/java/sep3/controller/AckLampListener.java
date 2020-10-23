package sep3.controller;

import sep3.Model;
import sep3.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

public class AckLampListener implements PropertyChangeListener {
	private final Model model;
	private final View view;

	public AckLampListener(Model m, View v) {
		model = m;
		view = v;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// モデル上のランプ状態を、そのままビューに伝える
		if (model.getMemory().getAckLamp().isOn()) {
			view.getAckLED().on();
		} else {
			view.getAckLED().off();
		}
	}
}
