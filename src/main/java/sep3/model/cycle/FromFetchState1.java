package sep3.model.cycle;

import sep3.*;
import sep3.model.*;
import sep3.model.operation.*;

public class FromFetchState1 extends State {
	@Override
	public State clockstep(Model model) {
		System.out.println("%% FF1 %%");
		// ステータスカウンタの設定。次の２行は、すべての状態において、最初に必ず記述すること
		CPU cpu = model.getCPU();
		cpu.getRegister(CPU.REG_SC).setInitValue(StateFactory.SC_FF1);

		/* 主記憶からデータを取り出してMDRへ送る */
		/* アドレスはFF0でMARに載っているのでそれを使う */
		final Decoder d = cpu.getDecoder();

		// MAR をアドレスバスに流す
		model.getAddrBusSelector().selectFrom(CPU.REG_MAR);
		// メモリを読み出してデータバスへ出力
		model.getMemory().access(Memory.MEM_RD);
		// データバスの値をMDRへ送る
		model.getDataBusSelector().selectTo(CPU.REG_MDR);

		// ポストインクリメントを扱う
		if (d.getFromMode() == Decoder.MODE_IP) {
			// レジスタを+1する
			final int reg = d.getFromRegister();
			cpu.getABusSelector().selectFrom(reg);
			cpu.getBBusSelector().selectFrom();
			cpu.getALU().operate(InstructionSet.OP_INC);
			cpu.getSBusSelector().selectTo(reg);
		}

		// 次の状態へ
		return cpu.getStateFactory().getState(StateFactory.SC_FF2);
	}

}
