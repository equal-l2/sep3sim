package sep3.model.cycle;

import sep3.*;
import sep3.model.*;

public class ExecState0 extends State {
	@Override
	public State clockstep(Model model) {
		//System.out.println("State EX0");
		CPU cpu = model.getCPU();
		cpu.getRegister(CPU.REG_SC).setInitValue(StateFactory.SC_EX0);

		// ALUで、命令ごとの演算をする。
		int opCode = cpu.getDecoder().getOpCode();
		cpu.getALU().operate(opCode);

		// 次の状態へ
		int nextState;
		if (cpu.getDecoder().getToMode() == Decoder.MODE_D) {
			nextState = StateFactory.SC_HLT;
		} else {
			nextState = StateFactory.SC_HLT;
		}
		return cpu.getStateFactory().getState(nextState);
	}
}
