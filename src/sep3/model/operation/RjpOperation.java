package sep3.model.operation;
import sep3.model.CPU;

public class AddOperation extends Operation {
	private CPU cpu;
	AddOperation(CPU cpu) { super(cpu); this.cpu = cpu; }
	public void operate() {
		// AバスにMDRの値を、BバスへB0の値を出力する
		useABus(true);
		useBBus(true);

		// 両バスの値を足してSバスに渡す
		int i = cpu.getABus().getValue();
		int j = cpu.getBBus().getValue();
		int o = i + j;
		cpu.getSBus().setValue(o & 0xFFFF);

		// Sバスの値をToオペランドに書き込む
		writeBack(true);
	}
}
