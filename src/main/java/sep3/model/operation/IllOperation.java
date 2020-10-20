package sep3.model.operation;

import sep3.model.CPU;

public class IllOperation extends Operation {
	private final CPU cpu;

	IllOperation(CPU cpu) {
		super(cpu);
		this.cpu = cpu;
	}

	public void operate() {
		// AバスもBバスも使わない
		useABus(false);
		useBBus(false);

		// Sバスには、Aバスの値をそのまま渡す（Aバスは使わないので0になっている）
		cpu.getSBus().setValue(cpu.getABus().getValue());

		// Sバスの値は捨てる
		writeBack(false);

		// ILL状態にする
		cpu.getIllLamp().on();
	}
}
