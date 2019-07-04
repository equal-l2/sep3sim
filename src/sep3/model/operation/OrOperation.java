package sep3.model.operation;
import sep3.model.CPU;
import sep3.model.Register;

public class OrOperation extends Operation {
	private CPU cpu;
	OrOperation(CPU cpu) { super(cpu); this.cpu = cpu; }
	public void operate() {
		// AバスにMDRの値を、BバスへB0の値を出力する
		useABus(true);
		useBBus(true);

		// 両バスの値をORしてSバスに渡す
		final int i = cpu.getABus().getValue();
		final int j = cpu.getBBus().getValue();
		final int o = i | j;
		cpu.getSBus().setValue(o & 0xFFFF);

		// Sバスの値をToオペランドに書き込む
		writeBack(true);

		// PSWの更新
		final Register psw_ref = cpu.getRegister(CPU.REG_PSW);
		int p = psw_NZ(o & 0xFFFF);
		p |= (psw_ref.getValue() & CPU.PSW_C); // Cは保存する
		psw_ref.setValue(p);
	}
}
