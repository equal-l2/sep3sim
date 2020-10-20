package sep3.model.operation;

import sep3.model.CPU;

public class ClrOperation extends Operation {
	private final CPU cpu;

	ClrOperation(CPU cpu) {
		super(cpu);
		this.cpu = cpu;
	}

	public void operate() {
		// AバスもBバスも使わない
		useABus(false);
		useBBus(false);

		// Sバスに0を流す
		cpu.getSBus().setValue(0);

		// Sバスの値をToオペランドに書き込む
		writeBack(true);

		// PSWの更新
		int p = psw_NZ(0);
		cpu.getRegister(CPU.REG_PSW).setValue(p);
	}
}
