package sep3.model.cycle;

import sep3.*;
import sep3.model.*;

public class ExecState1 extends State {
	@Override
	public State clockstep(Model model) {
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

		return cpu.getStateFactory().getState(StateFactory.SC_IF0);
	}
}
