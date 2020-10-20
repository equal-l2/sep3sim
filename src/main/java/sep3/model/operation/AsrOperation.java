package sep3.model.operation;

import sep3.model.CPU;

public class AsrOperation extends Operation {
	private final CPU cpu;

	AsrOperation(CPU cpu) {
		super(cpu);
		this.cpu = cpu;
	}

	public void operate() {
		// C: シフト前のLSB

		// AバスにMDRの値を出力し、Bバスは使わない
		// (Fオペランドがないため)
		useABus(true);
		useBBus(false);

		// Bバスの値を右シフトしてSバスに渡す
		final int i = cpu.getABus().getValue();
		final int o = i >> 1;

		cpu.getSBus().setValue(o & 0xFFFF);

		// Sバスの値をToオペランドに書き込む
		writeBack(true);

		// PSWの更新
		int p = psw_NZ(o & 0xFFFF);
		final int lsb = i & 0x0001;
		if (lsb != 0) {
			p |= CPU.PSW_C;
		}
		cpu.getRegister(CPU.REG_PSW).setValue(p);
	}
}
