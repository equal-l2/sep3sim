package sep3.model.runmode;

import sep3.*;
import sep3.model.cycle.State;

// CPUのクロックステップ走行モード：　1クロック進めて止まる
public class ClockStepMode extends RunMode {
	public ClockStepMode() { super.setID(RunModeFactory.RM_CLOCK); }
	public void run(Model model) {
		// 電源が入っているときのみ
		if (model.getPowerSW().isOn()) {
			model.clock();										// クロックを打って
			State s = model.getCPU().getCurrentState();
			model.getCPU().setCurrentState(s.clockstep(model));	// 1つ状態を遷移する
		}
	}
}
