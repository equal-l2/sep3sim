package sep3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sep3.*;
import sep3.model.runmode.RunModeFactory;

public class RunModeClockStepMenuListener implements ActionListener {
	private Model model;
	@SuppressWarnings("unused")
	private View  view;

	public RunModeClockStepMenuListener(Model m, View v) {
		model = m; view = v;
	}

	public void actionPerformed(ActionEvent e){
		//System.out.println("enter actionlistener of Clock Step Mode");
		model.setRunMode(RunModeFactory.RM_CLOCK);
	}
}
