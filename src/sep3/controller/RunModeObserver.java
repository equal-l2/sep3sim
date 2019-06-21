package sep3.controller;

import java.util.Observable;
import java.util.Observer;

import sep3.*;
import sep3.model.runmode.RunModeFactory;

public class RunModeObserver implements Observer {
	private Model model;
	private View  view;

	public RunModeObserver(Model m, View v) { model = m; view = v; }
	public void update(Observable o, Object arg) {
		//System.out.println("enter Observer of RumMode");
		// モデル上の走行状態を、ビュー上の走行モードランプに反映させる
		if (model.getPowerSW().isOn()) {
			switch (model.getRunMode().getID()) {
			case RunModeFactory.RM_NORMAL:
				view.getNormalRunLED().on();
				view.getInstRunLED().off();
				view.getClockRunLED().off();
				break;
			case RunModeFactory.RM_INST:
				view.getNormalRunLED().off();
				view.getInstRunLED().on();
				view.getClockRunLED().off();
				break;
			case RunModeFactory.RM_CLOCK:
				view.getNormalRunLED().off();
				view.getInstRunLED().off();
				view.getClockRunLED().on();
				break;
			default:
				view.getNormalRunLED().off();
				view.getInstRunLED().off();
				view.getClockRunLED().off();
				break;
			}
		} else {
			view.getNormalRunLED().off();
			view.getInstRunLED().off();
			view.getClockRunLED().off();
		}
	}
}
