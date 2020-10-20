package sep3.controller;

import sep3.Model;
import sep3.View;

import java.util.Observable;
import java.util.Observer;

public class HLTLampObserver implements Observer {
	private final Model model;
	private final View view;

	public HLTLampObserver(Model m, View v) {
		model = m;
		view = v;
	}

	public void update(Observable o, Object arg) {
		//System.out.println("enter Observer of HLT lamp");
		// モデル上のHLTランプ状態を、ビュー上のHLTランプに反映させる
		if (model.getCPU().getHaltLamp().isOn()) {
			view.getHltLED().on();
		} else {
			view.getHltLED().off();
		}
	}
}
