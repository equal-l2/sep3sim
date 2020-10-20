package sep3.controller;

import sep3.Model;
import sep3.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButtonListener implements ActionListener {
	private final Model model;
	@SuppressWarnings("unused")
	private final View view;

	public StartButtonListener(Model m, View v) {
		model = m;
		view = v;
	}

	public void actionPerformed(ActionEvent e) {
		//System.out.println("enter actionlistener of start button");
		if (!model.getMemory().getAckLamp().isOn()) model.run();
	}
}
