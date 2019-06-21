package sep3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sep3.*;

public class StartButtonListener implements ActionListener {
	private Model model;
	@SuppressWarnings("unused")
	private View  view;

	public StartButtonListener(Model m, View v) {
		model = m; view = v;
	}

	public void actionPerformed(ActionEvent e){
		//System.out.println("enter actionlistener of start button");
		model.run();
	}
}
