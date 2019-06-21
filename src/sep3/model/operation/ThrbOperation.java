package sep3.model.operation;
import sep3.model.CPU;

// Bバスの値をSバスに素通しする
public class ThrbOperation extends Operation {
	private CPU cpu;
	ThrbOperation(CPU cpu) { super(cpu); this.cpu = cpu; }
	public void operate() {
		// Bバスの値をそのままSバスに渡す
		// EX0で起動するメソッドではないので、他の処理は一切不要。
		cpu.getSBus().setValue(cpu.getBBus().getValue());
	}
}
