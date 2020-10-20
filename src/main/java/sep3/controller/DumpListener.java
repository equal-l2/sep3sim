package sep3.controller;

import sep3.Model;
import sep3.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DumpListener implements ActionListener {
	private final Model model;
	private final View view;

	public DumpListener(Model m, View v) {
		model = m;
		view = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.getMemory().dump();
	}
}
