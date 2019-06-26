package sep3.model.cycle;

import sep3.*;
import sep3.model.*;
import sep3.model.operation.*;

public class FromFetchState0 extends State {
	@Override
	public State clockstep(Model model) {
		System.out.println("%% FF0 %%");
		// ステータスカウンタの設定。次の２行は、すべての状態において、最初に必ず記述すること
		CPU cpu = model.getCPU();
		cpu.getRegister(CPU.REG_SC).setInitValue(StateFactory.SC_FF0);

		/* FF1, FF2に向けて読み出しの準備をする */

		final Decoder d = cpu.getDecoder();
		final int reg = d.getFromRegister();

		// 指定されたレジスタの値をMAR, MDRに送る
		// 直接参照の場合はここで値をB0に入れてしまえばいいようにも思えるが
		// ここでMDRに値を入れておくことでFF2はアドレッシングに関係なく
		// 「MDRからB0へ値を送る」という処理に単純化できるようになる
		// 天才か?

		// 指定レジスタのゲートを開け、値をAバスへ送る
		cpu.getABusSelector().selectFrom(reg);
		// Bバスへのゲートは全て閉じる
		cpu.getBBusSelector().selectFrom();
		// ALUにはAバスの値を素通りさせ、Sバスに流す
		cpu.getALU().operate(InstructionSet.OP_THRA);
		// Sバスの値をMAR & MDRへ
		cpu.getSBusSelector().selectTo(CPU.REG_MAR, CPU.REG_MDR);

		final int mode = d.getFromMode();
		int next;
		switch(mode) {
			case Decoder.MODE_D:
				next = StateFactory.SC_FF2;
				break;
			case Decoder.MODE_MI:
				// プレデクリメントではこの時点で-1を行う
				cpu.getABusSelector().selectFrom(reg);
				cpu.getBBusSelector().selectFrom();
				cpu.getALU().operate(InstructionSet.OP_DEC);
				cpu.getSBusSelector().selectTo(reg);
				// fall-through
			default:
				next = StateFactory.SC_FF1;
		}

		return cpu.getStateFactory().getState(next);
	}

}
