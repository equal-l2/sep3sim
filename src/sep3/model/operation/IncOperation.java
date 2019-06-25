package sep3.model.operation;
import sep3.model.CPU;

// ポストインクリメント・レジスタ間接のとき、命令フェッチのときに使う
public class IncOperation extends Operation {
	private CPU cpu;
	IncOperation(CPU cpu) { super(cpu); this.cpu = cpu; }
	public void operate() {
		// Aバスの値を+1してSバスに渡す
		// EX0で起動するメソッドではないので、他の処理は一切不要。
		cpu.getSBus().setValue((cpu.getABus().getValue() + 1) & 0xFFFF);
	}
}
