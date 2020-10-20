package sep3.controller;

import sep3.Model;
import sep3.View;
import sep3.model.runmode.RunModeFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunModeInstStepMenuListener implements ActionListener {
	private final Model model;
	@SuppressWarnings("unused")
	private final View view;

	public RunModeInstStepMenuListener(Model m, View v) {
		model = m;
		view = v;
	}

	public void actionPerformed(ActionEvent e) {
		//System.out.println("enter actionlistener of Instruction Step Mode");
		model.setRunMode(RunModeFactory.RM_INST);
	}
}
