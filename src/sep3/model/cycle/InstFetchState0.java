package sep3.model.cycle;

import sep3.*;
import sep3.model.*;
import sep3.model.operation.*;

public class InstFetchState0 extends State {
	@Override
	public State clockstep(Model model) {
		//System.out.println("%% IF0 %%");
		// ステータスカウンタの設定。次の２行は、すべての状態において、最初に必ず記述すること
		CPU cpu = model.getCPU();
		cpu.getRegister(CPU.REG_SC).setInitValue(StateFactory.SC_IF0);

		// PC の値を MAR, MDR へ送る

		// PCのゲートを開けて命令読み出し番地をAバスへ
		cpu.getABusSelector().selectFrom(CPU.REG_PC);
		// Bバスへのゲートはすべて閉じる
		cpu.getBBusSelector().selectFrom();
		// ALUはAバスの値をそのままSバスへ流す
		cpu.getALU().operate(InstructionSet.OP_THRA);
		// Sバスの値をMAR, MDRへ送る
		cpu.getSBusSelector().selectTo(CPU.REG_MAR, CPU.REG_MDR);

		// 次の状態へ
		return cpu.getStateFactory().getState(StateFactory.SC_IF1);
	}

}
