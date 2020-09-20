package sep3.model.cycle;

import sep3.*;
import sep3.model.*;
import sep3.model.operation.InstructionSet;

public class ExecState1 extends State {
	@Override
	public State clockstep(Model model) {
		//System.out.println("%% EX1 %%");
		CPU cpu = model.getCPU();
		cpu.getRegister(CPU.REG_SC).setInitValue(StateFactory.SC_EX1);

		/* MDRの内容をTオペランドへ書き込む */
		/* 直接参照の場合はEX0で扱っているので、間接参照専用 */
		/* 書き込み先アドレスはTF0でMARに載っているのでそれを使う */

		// MARからアドレスバスへ値を流す
		model.getAddrBusSelector().selectFrom(CPU.REG_MAR);
		// MDRからデータバスへ値を流す
		model.getDataBusSelector().selectFrom(CPU.REG_MDR);
		// 値を書き込む
		model.getMemory().access(Memory.MEM_WR);

		// サブルーチンコール系の命令を扱う
		final int op = cpu.getDecoder().getOpCode();
		if ((op & 0x2C) == 0x2C) { // 命令の頭が1011のとき(サブルーチンコール系の命令)
			// 何はともあれB0からジャンプ先アドレスをBバスへ流す
			cpu.getBBusSelector().selectFrom(CPU.REG_B0);
			switch (op) {
				case InstructionSet.OP_JSR:
				case InstructionSet.OP_SVC:
					// 絶対ジャンプなのでBバスを素通し
					cpu.getALU().operate(InstructionSet.OP_THRB);
					break;
				case InstructionSet.OP_RJS:
					// 相対ジャンプなのでAバスにR7を流す
					cpu.getABusSelector().selectFrom(CPU.REG_R7);
					// 相対ジャンプなのでB0とR7を足す
					cpu.getALU().operate(InstructionSet.OP_ADD2);
			}
			// Sバスの値をR7へ
			cpu.getSBusSelector().selectTo(CPU.REG_R7);
		}

		return cpu.getStateFactory().getState(StateFactory.SC_IF0);
	}
}
