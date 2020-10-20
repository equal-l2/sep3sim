package sep3.model.operation;

import sep3.model.CPU;

public class RolOperation extends Operation {
	private final CPU cpu;

	RolOperation(CPU cpu) {
		super(cpu);
		this.cpu = cpu;
	}

	public void operate() {
		// C: 入力のfビット

		// AバスにMDRの値を出力し、Bバスは使わない
		// (Fオペランドがないため)
		useABus(true);
		useBBus(false);

		// Bバスの値を左シフトしてSバスに渡す
		final int i = cpu.getABus().getValue();
		final int j = i << 1;

		// 入力の最上位ビットをシフト後の最下位ビットへ移植
		final int msb = (i & 0x8000) == 0 ? 0 : 1; // 入力の最上位ビット
		final int data = j & 0xFFFE; // 結果の最下位ビット以外
		final int o = msb | data;

		cpu.getSBus().setValue(o & 0xFFFF);

		// Sバスの値をToオペランドに書き込む
		writeBack(true);

		// PSWの更新
		int p = psw_NZ(o & 0xFFFF);
		if (msb != 0) {
			p |= CPU.PSW_C;
		}
		cpu.getRegister(CPU.REG_PSW).setValue(p);
	}
}
