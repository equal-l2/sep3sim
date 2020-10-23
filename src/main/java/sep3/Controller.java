package sep3;


import sep3.controller.*;
import sep3.model.CPU;

public class Controller {

	public Controller(Model model, View view) {
		// メニューの処理
		view.getFileMenu().addActionListener(new FileMenuListener(model, view));
		view.getExitMenu().addActionListener(new ExitMenuListener(model, view));
		view.getNormalMenu().addActionListener(new RunModeNormalMenuListener(model, view));
		view.getInstMenu().addActionListener(new RunModeInstStepMenuListener(model, view));
		view.getClockMenu().addActionListener(new RunModeClockStepMenuListener(model, view));
		view.getDump().addActionListener(new DumpListener(model, view));

		/* ビューの変更をモデルに伝える */
		// 電源スイッチ
		view.getPowerSwitch().addActionListener(new PowerSwitchListener(model, view));

		// トグルスイッチ： PC/ISR切り替え
		view.getDispSW().addActionListener(new DispSwitchListener(model, view));

		// ack ボタン
		view.getAckButton().addActionListener(new AckButtonListener(model, view));

		// reset ボタン
		view.getResetButton().addActionListener(new ResetButtonListener(model, view));

		// start ボタン
		view.getStartButton().addActionListener(new StartButtonListener(model, view));

		// データ入力スイッチ
		//   リスナーなし

		/* モデルの変更をビューに伝える */
		// R0 から R6
		final boolean dispSWon = true;            // true:  表示切り替えスイッチがONのとき…を意味する
		final boolean dispSWoff = false;        // false: 表示切り替えスイッチがOFFのとき…を意味する

		// 表示切替スイッチがOFFのときのLCD
		// R0 - R6 と B0
		model.getCPU().getRegister(CPU.REG_R0).addListener(new RegisterListener(model, view, dispSWoff, 0, 0));    // 0, 0 はLCDの表示位置を表す
		model.getCPU().getRegister(CPU.REG_R1).addListener(new RegisterListener(model, view, dispSWoff, 0, 1));
		model.getCPU().getRegister(CPU.REG_R2).addListener(new RegisterListener(model, view, dispSWoff, 0, 2));
		model.getCPU().getRegister(CPU.REG_R3).addListener(new RegisterListener(model, view, dispSWoff, 0, 3));
		model.getCPU().getRegister(CPU.REG_R4).addListener(new RegisterListener(model, view, dispSWoff, 1, 0));
		model.getCPU().getRegister(CPU.REG_R5).addListener(new RegisterListener(model, view, dispSWoff, 1, 1));
		model.getCPU().getRegister(CPU.REG_R6).addListener(new RegisterListener(model, view, dispSWoff, 1, 2));
		model.getCPU().getRegister(CPU.REG_B0).addListener(new RegisterListener(model, view, dispSWoff, 1, 3));
		// 表示切替スイッチがONのときのLCD
		// MAR, MDR, Mbus（データバス）, Dummy, Abus, Bbus, Sbus, SC
		model.getCPU().getRegister(CPU.REG_MAR).addListener(new RegisterListener(model, view, dispSWon, 0, 0));
		model.getCPU().getRegister(CPU.REG_MDR).addListener(new RegisterListener(model, view, dispSWon, 0, 1));
		model.getDataBus().addListener(new BusListener(model, view, dispSWon, 0, 2));
		model.getCPU().getRegister(CPU.REG_DUMMY).addListener(new RegisterListener(model, view, dispSWon, 0, 3));
		model.getCPU().getABus().addListener(new BusListener(model, view, dispSWon, 1, 0));
		model.getCPU().getBBus().addListener(new BusListener(model, view, dispSWon, 1, 1));
		model.getCPU().getSBus().addListener(new BusListener(model, view, dispSWon, 1, 2));
		model.getCPU().getRegister(CPU.REG_SC).addListener(new RegisterListener(model, view, dispSWon, 1, 3));

		// R7/ISR
		model.getCPU().getRegister(CPU.REG_R7).addListener(new R7ISRListener(model, view, dispSWoff));
		model.getCPU().getRegister(CPU.REG_ISR).addListener(new R7ISRListener(model, view, dispSWon));

		// データ出力LED
		model.getMemory().getIOValue().addListener(new IOOutputListener(model, view));

		// HLT LED
		model.getCPU().getHaltLamp().addListener(new HLTLampListener(model, view));

		// ILL LED
		model.getCPU().getIllLamp().addListener(new ILLLampListener(model, view));

		// ACK LED
		model.getMemory().getAckLamp().addListener(new AckLampListener(model, view));

		// 走行モード
		model.getListenableRunMode().addListener(new RunModeListener(model, view));
	}
}
