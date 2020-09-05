package sep3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sep3.*;
import sep3.model.CPU;
import sep3.model.OnOffFlag;

public class DumpListener implements ActionListener {
	private Model model;
	private View  view;

	public DumpListener(Model m, View v) {
		model = m; view = v;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		model.getMemory().dump();
	}
}
