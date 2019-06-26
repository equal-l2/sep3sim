package sep3.model.operation;
import sep3.model.CPU;

public class LslOperation extends Operation {
	private CPU cpu;
	LslOperation(CPU cpu) { super(cpu); this.cpu = cpu; }
	public void operate() {
		// C: 入力のMSB

		// AバスにMDRの値を出力し、Bバスは使わない
		// (Fオペランドがないため)
		useABus(true);
		useBBus(false);

		// Bバスの値を左シフトしてSバスに渡す
		final int i = cpu.getABus().getValue();
		final int o = i << 1;

		cpu.getSBus().setValue(o & 0xFFFF);

		// Sバスの値をToオペランドに書き込む
		writeBack(true);

		// PSWの更新
		int p = psw_NZ(o & 0xFFFF);

		boolean carry = (i & 0x8000) != 0;
		if (carry) {
			p |= CPU.PSW_C;
		}
		cpu.getRegister(CPU.REG_PSW).setValue(p);
	}
}
