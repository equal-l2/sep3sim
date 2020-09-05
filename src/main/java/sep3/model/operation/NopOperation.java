package sep3.model.operation;
import sep3.model.CPU;

public class NopOperation extends Operation {
	private CPU cpu;
	NopOperation(CPU cpu) { super(cpu); this.cpu = cpu; }
	public void operate() {
		// AバスもBバスも使わない
		useABus(false);
		useBBus(false);

		// Bバスの値をそのままSバスに出力する
		int i = cpu.getBBus().getValue();
		cpu.getSBus().setValue(i);

		// Sバスの値は捨てる
		writeBack(false);
}
}
