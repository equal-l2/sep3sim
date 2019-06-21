package sep3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sep3.*;
import sep3.model.CPU;

public class DispSwitchListener implements ActionListener {
	private Model model;
	@SuppressWarnings("unused")
	private View  view;

	public DispSwitchListener(Model m, View v) {
		model = m; view = v;
	}

	public void actionPerformed(ActionEvent e){
		//System.out.println("enter actionlistener of PC/ISR");
		// トグルスイッチ#10が切り替えられたら、7セグメントLEDの表示レジスタを替える
		model.getDispSW().toggle();
		if (model.getPowerSW().isOn()) {	// 電源が入っているときだけ、ビューを替える
			if (model.getDispSW().isOn()) {
				model.getCPU().getRegister(CPU.REG_MAR).touch();
				model.getCPU().getRegister(CPU.REG_MDR).touch();
				model.getDataBus().touch();
				model.getCPU().getRegister(CPU.REG_DUMMY).touch();
				model.getCPU().getABus().touch();
				model.getCPU().getBBus().touch();
				model.getCPU().getSBus().touch();
				model.getCPU().getRegister(CPU.REG_SC).touch();
				model.getCPU().getRegister(CPU.REG_ISR).touch();
			} else {
				model.getCPU().getRegister(CPU.REG_R0).touch();
				model.getCPU().getRegister(CPU.REG_R1).touch();
				model.getCPU().getRegister(CPU.REG_R2).touch();
				model.getCPU().getRegister(CPU.REG_R3).touch();
				model.getCPU().getRegister(CPU.REG_R4).touch();
				model.getCPU().getRegister(CPU.REG_R5).touch();
				model.getCPU().getRegister(CPU.REG_R6).touch();
				model.getCPU().getRegister(CPU.REG_B0).touch();
				model.getCPU().getRegister(CPU.REG_PC).touch();
			}
		}
	}
}
