package sep3.controller;

import sep3.Model;
import sep3.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitMenuListener implements ActionListener {
	@SuppressWarnings("unused")
	private final Model model;
	@SuppressWarnings("unused")
	private final View view;

	public ExitMenuListener(Model m, View v) {
		model = m;
		view = v;
	}

	public void actionPerformed(ActionEvent e) {
		//System.out.println("enter actionlistener of EXIT menu");
		// メニューバー上でEXITが選ばれたら、プログラム終了
		System.exit(0);
	}
}
