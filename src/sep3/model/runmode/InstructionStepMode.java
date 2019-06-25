package sep3.model.runmode;

import sep3.Model;
import sep3.model.cycle.State;
import sep3.model.cycle.StateFactory;

// CPUの命令ステップ走行モード：　命令読み出しの最初の状態、HLT、ILLのどれか、
// あるいはACKランプが点灯して入力待ちになるまで走るになるまで走る
public class InstructionStepMode extends RunMode {
	public InstructionStepMode() { super.setID(RunModeFactory.RM_INST); }
	public void run(Model model) {
		// 電源が入っているときのみ
		if (model.getPowerSW().isOn()) {
			StateFactory sf = model.getCPU().getStateFactory();
			State hlt = sf.getState(StateFactory.SC_HLT);
			State ill = sf.getState(StateFactory.SC_ILL);
			State beg = sf.getState(StateFactory.SC_IF1);
			State s = model.getCPU().getCurrentState();
			do {
				model.clock();			// クロックを打って
				s = s.clockstep(model);	// 1つ状態を遷移する
				model.getCPU().setCurrentState(s);
			// CPUを進めてはいけない状態（HLT状態、ILL状態、入力待ち状態）になるか、命令実効サイクルの最初の状態になるまで
			} while ((s != hlt) & (s != ill) & (!model.getMemory().getAckLamp().isOn()) & (s != beg));
		}
	}
}