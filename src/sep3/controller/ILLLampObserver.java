package sep3.controller;

import java.util.Observable;
import java.util.Observer;
import sep3.*;

public class ILLLampObserver implements Observer {
	private Model model;
	private View  view;

	public ILLLampObserver(Model m, View v) { model = m; view = v; }
	public void update(Observable o, Object arg) {
		//System.out.println("enter Observer of ILL lamp");
		// モデル上のILLランプの状態を、ビュー上のILLランプに反映させる
		if (model.getCPU().getIllLamp().isOn()) {
			view.getIllLED().on();
		} else {
			view.getIllLED().off();
		}
	}
}
