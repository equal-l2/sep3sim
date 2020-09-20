package sep3.model.cycle;

import sep3.*;
import sep3.model.*;
import sep3.model.operation.InstructionSet;

public class ExecState0 extends State {
	@Override
	public State clockstep(Model model) {
		//System.out.println("%% EX0 %%");
		CPU cpu = model.getCPU();
		cpu.getRegister(CPU.REG_SC).setInitValue(StateFactory.SC_EX0);

		// ALUで、命令ごとの演算をする。
		final Decoder d = cpu.getDecoder();
		int opCode = d.getOpCode();
		cpu.getALU().operate(opCode);

		// 次の状態へ

		int next;
		final int op = d.getOpCode();
		if (op == InstructionSet.OP_HLT) {
			next = StateFactory.SC_HLT;
		} else if (!cpu.getInstructionSet().isLegalInstruction(op)) {
			next = StateFactory.SC_ILL;
		}
		else if (cpu.getDecoder().getToMode() == Decoder.MODE_D) {
			next = StateFactory.SC_IF0;
		} else {
			next = StateFactory.SC_EX1;
		}

		return cpu.getStateFactory().getState(next);
	}
}
