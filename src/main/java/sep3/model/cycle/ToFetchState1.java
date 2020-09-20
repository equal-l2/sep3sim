package sep3.model.cycle;

import sep3.*;
import sep3.model.*;
import sep3.model.operation.*;

public class ToFetchState1 extends State {
	@Override
	public State clockstep(Model model) {
		//System.out.println("%% TF1 %%");
		// ステータスカウンタの設定。次の２行は、すべての状態において、最初に必ず記述すること
		CPU cpu = model.getCPU();
		cpu.getRegister(CPU.REG_SC).setInitValue(StateFactory.SC_TF1);

		/* 主記憶からデータを取り出してMDRへ送る */
		final Decoder d = cpu.getDecoder();

		// MAR をアドレスバスに流す
		model.getAddrBusSelector().selectFrom(CPU.REG_MAR);

		switch(d.getOpCode()) {
			case InstructionSet.OP_CLR:
			case InstructionSet.OP_MOV:
				// Aバスの値を使わず、かつMMIOをTオペランドとして取りうる命令(現時点ではMOVとCLRのみ)
				// は(TオペランドのAckで停止されると困るので)特別なメソッドでメモリを読み出す
				model.getMemory().readValueWithoutBlock();
				break;
			default:
				// メモリを読み出してデータバスへ出力
				model.getMemory().access(Memory.MEM_RD);
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
