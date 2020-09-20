package sep3.model.cycle;

import sep3.*;
import sep3.model.*;
import sep3.model.operation.*;

public class ToFetchState0 extends State {
	@Override
	public State clockstep(Model model) {
		//System.out.println("%% TF0 %%");
		// ステータスカウンタの設定。次の２行は、すべての状態において、最初に必ず記述すること
		CPU cpu = model.getCPU();
		cpu.getRegister(CPU.REG_SC).setInitValue(StateFactory.SC_TF0);

		/* Tオペランドのレジスタの内容をMAR, MDRに置く */

		final Decoder d = cpu.getDecoder();
		final int reg = d.getToRegister();

		// 指定されたレジスタの値をMAR, MDRに送る

		// 指定レジスタのゲートを開け、値をAバスへ送る
		cpu.getABusSelector().selectFrom(reg);
		// Bバスへのゲートは全て閉じる
		cpu.getBBusSelector().selectFrom();
		// ALUにはAバスの値を素通りさせ、Sバスに流す
		cpu.getALU().operate(InstructionSet.OP_THRA);
		// Sバスの値をMAR & MDRへ
		cpu.getSBusSelector().selectTo(CPU.REG_MAR, CPU.REG_MDR);

		int next;
		if (d.getToMode() == Decoder.MODE_D) {
			next = StateFactory.SC_EX0;
		} else {
			// 間接参照の場合はTF1でデータを取り出す
			next = StateFactory.SC_TF1;
		}

		return cpu.getStateFactory().getState(next);
	}

}
