package sep3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sep3.*;

public class ResetButtonListener implements ActionListener {
	private Model model;
	@SuppressWarnings("unused")
	private View  view;

	public ResetButtonListener(Model m, View v) {
		model = m; view = v;
	}

	public void actionPerformed(ActionEvent e){
		//System.out.println("enter actionlistener of reset button");
		// リセットボタンが押されたら、モデル上でもリセット。ただし電源が入っているときだけ
		if (model.getPowerSW().isOn()) {
			model.reset();
		}
	}
}
