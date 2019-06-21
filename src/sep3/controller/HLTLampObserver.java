package sep3.controller;

import java.util.Observable;
import java.util.Observer;
import sep3.*;

public class HLTLampObserver implements Observer {
	private Model model;
	private View  view;

	public HLTLampObserver(Model m, View v) { model = m; view = v; }
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
