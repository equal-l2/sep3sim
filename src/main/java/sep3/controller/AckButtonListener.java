package sep3.controller;

import sep3.Model;
import sep3.View;
import sep3.model.CPU;
import sep3.model.OnOffFlag;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AckButtonListener implements ActionListener {
	private final Model model;
	private final View view;

	public AckButtonListener(Model m, View v) {
		model = m;
		view = v;
	}

	public void actionPerformed(ActionEvent e) {
		//System.out.println("enter actionlistener of ACK button");
		// ランプを消し、入力スイッチの状態を読み、データバスに載せ、MDRに届くようにする
		OnOffFlag ack = model.getMemory().getAckLamp();
		if (ack.isOn()) {
			ack.off();
			int v = view.getDataInputSwitch().getData();
			//System.out.println(v);
			model.getDataBus().setValue(v);
			model.getDataBusSelector().selectTo(CPU.REG_MDR);
			// Ack ボタンはクロックと別なので、また走る
			//model.clock();
			model.run();
		}
	}
}
