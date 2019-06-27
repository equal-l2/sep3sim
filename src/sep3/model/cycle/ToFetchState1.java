package sep3.model.cycle;

import sep3.*;
import sep3.model.*;
import sep3.model.operation.*;

public class ToFetchState1 extends State {
	@Override
	public State clockstep(Model model) {
		System.out.println("%% TF1 %%");
		// ステータスカウンタの設定。次の２行は、すべての状態において、最初に必ず記述すること
		CPU cpu = model.getCPU();
		cpu.getRegister(CPU.REG_SC).setInitValue(StateFactory.SC_TF1);

		/* 主記憶からデータを取り出してMDRへ送る */
		final Decoder d = cpu.getDecoder();

		// MAR をアドレスバスに流す
		model.getAddrBusSelector().selectFrom(CPU.REG_MAR);
		// メモリを読み出してデータバスへ出力
		model.getMemory().access(Memory.MEM_RD);

		// Aバスを使わない命令ではTオペランドのAckで停止されると困る
		// のでランプを消す
		switch(d.getOpCode()) {
			case InstructionSet.OP_CLR: case InstructionSet.OP_HLT: case InstructionSet.OP_ILL: case InstructionSet.OP_JMP:
			case InstructionSet.OP_MOV: case InstructionSet.OP_NOP: case InstructionSet.OP_RET: case InstructionSet.OP_RIT:
				System.out.println("Shut the Lamp!");
				model.getMemory().getAckLamp().off();
				break;
			default: System.out.println("Ignore normal ops " + Integer.toString(d.getOpCode()));
		}

		// データバスの値をMDRへ送る
		model.getDataBusSelector().selectTo(CPU.REG_MDR);

		if (d.getToMode() == Decoder.MODE_IP) {
			// レジスタを+1する
			final int reg = d.getToRegister();
			cpu.getABusSelector().selectFrom(reg);
			cpu.getBBusSelector().selectFrom();
			cpu.getALU().operate(InstructionSet.OP_INC);
			cpu.getSBusSelector().selectTo(reg);
		}

		// 次の状態へ
		return cpu.getStateFactory().getState(StateFactory.SC_EX0);
	}

}
