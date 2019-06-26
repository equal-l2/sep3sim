package sep3.model.operation;
import sep3.model.CPU;

public class BitOperation extends Operation {
	private CPU cpu;
	BitOperation(CPU cpu) { super(cpu); this.cpu = cpu; }
	public void operate() {
		// AバスにMDRの値を、BバスへB0の値を出力する
		useABus(true);
		useBBus(true);

		// 両バスの値をORしてSバスに渡す
		int i = cpu.getABus().getValue();
		int j = cpu.getBBus().getValue();
		int o = i & j;
		cpu.getSBus().setValue(o & 0xFFFF);

		// Sバスの値を捨てる
		writeBack(false);

		// PSWの更新
		int p = psw_NZ(o & 0xFFFF);
		p |= (cpu.getRegister(CPU.REG_PSW).getValue() & CPU.PSW_C) // Cは保存する
		cpu.getRegister(CPU.REG_PSW).setValue(p);
	}
}
