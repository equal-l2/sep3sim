package sep3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sep3.*;
import sep3.model.CPU;

public class AckButtonListener implements ActionListener {
	private Model model;
	private View  view;

	public AckButtonListener(Model m, View v) {
		model = m; view = v;
	}

	public void actionPerformed(ActionEvent e){
		//System.out.println("enter actionlistener of ACK button");
		// ランプを消し、入力スイッチの状態を読み、データバスに載せ、MDRに届くようにする
		model.getMemory().getAckLamp().off();
		int v = view.getDataInputSwitch().getData();
		//System.out.println(v);
		model.getDataBus().setValue(v);
		model.getDataBusSelector().selectTo(CPU.REG_MDR);
		// Ack ボタンはクロックと別なので、また走る
		//model.clock();
		model.run();
	}
}
