package sep3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sep3.*;
import sep3.model.runmode.RunModeFactory;

public class RunModeNormalMenuListener implements ActionListener {
	private Model model;
	@SuppressWarnings("unused")
	private View  view;

	public RunModeNormalMenuListener(Model m, View v) {
		model = m; view = v;
	}

	public void actionPerformed(ActionEvent e){
		//System.out.println("enter actionlistener of Normal Mode");
		model.setRunMode(RunModeFactory.RM_NORMAL);
	}
}
