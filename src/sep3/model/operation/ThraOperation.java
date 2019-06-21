package sep3.model.operation;
import sep3.model.CPU;

// Aバスの値をSバスに素通しする
public class ThraOperation extends Operation {
	private CPU cpu;
	ThraOperation(CPU cpu) { super(cpu); this.cpu = cpu; }
	public void operate() {
		// Aバスの値をそのままSバスに渡す
		// EX0で起動するメソッドではないので、他の処理は一切不要。
		cpu.getSBus().setValue(cpu.getABus().getValue());
	}
}
