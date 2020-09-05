package sep3.controller;

import java.util.Observable;
import java.util.Observer;
import sep3.*;

public class AckLampObserver implements Observer {
	private Model model;
	private View  view;

	public AckLampObserver(Model m, View v) { model = m; view = v; }
	public void update(Observable o, Object arg) {
		//System.out.println("enter Observer of Ack lamp");
		// モデル上のランプ状態を、そのままビューに伝える
		if (model.getMemory().getAckLamp().isOn()) {
			view.getAckLED().on();
		} else {
			view.getAckLED().off();
		}
	}
}
