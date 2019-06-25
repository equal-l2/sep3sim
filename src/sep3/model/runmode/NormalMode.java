package sep3.model.runmode;

import sep3.Model;
import sep3.model.cycle.State;
import sep3.model.cycle.StateFactory;

// CPUの通常走行モード：　HLT, ILLのどちらか、あるいはACKランプが点灯して入力待ちになるまで走る
public class NormalMode extends RunMode {
	public NormalMode() { super.setID(RunModeFactory.RM_NORMAL); }
	public void run(Model model) {
		// 電源が入っているときのみ
		if (model.getPowerSW().isOn()) {
			StateFactory sf = model.getCPU().getStateFactory();
			State hlt = sf.getState(StateFactory.SC_HLT);
			State ill = sf.getState(StateFactory.SC_ILL);
			State s = model.getCPU().getCurrentState();
			do {
				model.clock();			// クロックを打って
				s = s.clockstep(model);	// 1つ状態を遷移する
				model.getCPU().setCurrentState(s);
			// CPUを進めてはいけない状態（HLT状態、ILL状態、入力待ち状態）になるまで
			} while ((s != hlt) & (s != ill) & (!model.getMemory().getAckLamp().isOn()));
		}
	}
}
