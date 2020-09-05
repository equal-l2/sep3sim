package sep3.model.operation;
import sep3.model.CPU;

public class RetOperation extends Operation {
	private CPU cpu;
	RetOperation(CPU cpu) { super(cpu); this.cpu = cpu; }

	public void operate() {
		// Aバスは使わず、B0の値をBバスへ出力する
		useABus(false);
		useBBus(true);

		// Bバスの値をそのままSバスに出力する
		int i = cpu.getBBus().getValue();
		cpu.getSBus().setValue(i);

		// Sバスの値をToオペランドに書き込む
		writeBack(true);
	}
}
