package sep3.controller;

import sep3.Model;
import sep3.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

public class ILLLampListener implements PropertyChangeListener {
	private final Model model;
	private final View view;

	public ILLLampListener(Model m, View v) {
		model = m;
		view = v;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// モデル上のILLランプの状態を、ビュー上のILLランプに反映させる
		if (model.getCPU().getIllLamp().isOn()) {
			view.getIllLED().on();
		} else {
			view.getIllLED().off();
		}
	}
}
