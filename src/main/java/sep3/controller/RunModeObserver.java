package sep3.controller;

import sep3.Model;
import sep3.View;
import sep3.model.runmode.RunModeFactory;

import java.util.Observable;
import java.util.Observer;

public class RunModeObserver implements Observer {
	private final Model model;
	private final View view;

	public RunModeObserver(Model m, View v) {
		model = m;
		view = v;
	}

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
