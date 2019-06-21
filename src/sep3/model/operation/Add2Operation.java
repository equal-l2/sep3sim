package sep3.model.operation;
import sep3.model.CPU;

// PSW の更新をしない ADD （相対分岐などで使う）
public class Add2Operation extends Operation {
	private CPU cpu;
	Add2Operation(CPU cpu) { super(cpu); this.cpu = cpu; }
	public void operate() {
		// AバスにMDRの値を、BバスへB0の値を出力する
		useABus(true);
		useBBus(true);

		// 両バスの値を足してSバスに渡す。PSWの更新はしない
		int i = cpu.getABus().getValue();
		int j = cpu.getBBus().getValue();
		int o = i + j;
		cpu.getSBus().setValue(o & 0xFFFF);

		// Sバスの値を捨てる
		writeBack(false);
	}
}
