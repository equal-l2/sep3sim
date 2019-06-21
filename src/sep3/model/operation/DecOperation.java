package sep3.model.operation;
import sep3.model.CPU;

// プレデクリメント・レジスタ間接のとき使う
public class DecOperation extends Operation {
	private CPU cpu;
	DecOperation(CPU cpu) { super(cpu); this.cpu = cpu; }
	public void operate() {
		// Aバスの値を-1してSバスに渡す
		// EX0で起動するメソッドではないので、他の処理は一切不要。
		cpu.getSBus().setValue((cpu.getABus().getValue() - 1) & 0xFFFF);
	}
}
