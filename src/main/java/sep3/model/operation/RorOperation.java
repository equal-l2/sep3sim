package sep3.model.operation;
import sep3.model.CPU;

public class RorOperation extends Operation {
	private CPU cpu;
	RorOperation(CPU cpu) { super(cpu); this.cpu = cpu; }
	public void operate() {
		// C: 入力のLSB

		// AバスにMDRの値を出力し、Bバスは使わない
		// (Fオペランドがないため)
		useABus(true);
		useBBus(false);

		// Bバスの値を右シフトしてSバスに渡す
		final int i = cpu.getABus().getValue();
		final int j = i >>> 1;

		// 入力の最下位ビットをシフト後の最上位ビットに移植
		final int lsb = (i & 0x0001) == 0 ? 0 : 0x8000; // 入力の最下位ビット
		final int data = j & 0x7FFF; // 結果の最上位ビット以外
		final int o = lsb | data;

		cpu.getSBus().setValue(o & 0xFFFF);

		// Sバスの値をToオペランドに書き込む
		writeBack(true);

		// PSWの更新
		int p = psw_NZ(o & 0xFFFF);
		if (lsb != 0) {
			p |= CPU.PSW_C;
		}
		cpu.getRegister(CPU.REG_PSW).setValue(p);
	}
}
