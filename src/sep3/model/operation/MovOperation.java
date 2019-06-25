package sep3.model.operation;
import sep3.model.CPU;

public class MovOperation extends Operation {
	private CPU cpu;
	MovOperation(CPU cpu) { super(cpu); this.cpu = cpu; }

	public void operate() {
		// Aバスは使わず、B0の値をBバスへ出力する
		useABus(false);
		useBBus(true);

		// Bバスの値をそのままSバスに出力する
		int i = cpu.getBBus().getValue();
		cpu.getSBus().setValue(i);

		// Sバスの値をToオペランドに書き込む
		writeBack(true);

		// PSWの更新: MOVの演算結果（つまり、Bバスの値）に応じてNZのビットを立てる
		int p = psw_NZ(i);
		p |= cpu.getRegister(CPU.REG_PSW).getValue() & 0x0001;			// C will not change
		cpu.getRegister(CPU.REG_PSW).setValue(p);
	}
}
