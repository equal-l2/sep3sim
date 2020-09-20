package sep3.model.cycle;

import sep3.*;
import sep3.model.*;
import sep3.model.operation.*;

public class InstFetchState1 extends State {
	@Override
	public State clockstep(Model model) {
		//System.out.println("%% IF1 %%");
		// ステータスカウンタの設定。次の２行は、すべての状態において、最初に必ず記述すること
		CPU cpu = model.getCPU();
		cpu.getRegister(CPU.REG_SC).setInitValue(StateFactory.SC_IF1);

		// MARに格納されている番地から命令を読み出してISRへ送る

		// MAR をアドレスバスに流す
		model.getAddrBusSelector().selectFrom(CPU.REG_MAR);
		// メモリを読み出してデータバスへ出力
		model.getMemory().access(Memory.MEM_RD);
		// データバスの値をISRへ送る
		model.getDataBusSelector().selectTo(CPU.REG_ISR);

		// 命令を読み出している間に、PC のカウントアップ

		// PCの値をAバスに流す
		cpu.getABusSelector().selectFrom(CPU.REG_PC);
		// Bバスへのゲートはすべて閉じる
		cpu.getBBusSelector().selectFrom();
		// ALUはAバスの値を+1してSバスに流す
		cpu.getALU().operate(InstructionSet.OP_INC);
		// Sバスの値をPCに送る
		cpu.getSBusSelector().selectTo(CPU.REG_PC);

		// 次の状態へ
		return cpu.getStateFactory().getState(StateFactory.SC_FF0);
	}
}