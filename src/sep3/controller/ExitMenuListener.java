package sep3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sep3.*;

public class ExitMenuListener implements ActionListener {
	@SuppressWarnings("unused")
	private Model model;
	@SuppressWarnings("unused")
	private View  view;

	public ExitMenuListener(Model m, View v) {
		model = m; view = v;
	}

	public void actionPerformed(ActionEvent e){
		//System.out.println("enter actionlistener of EXIT menu");
		// メニューバー上でEXITが選ばれたら、プログラム終了
		System.exit(0);
	}
}
