package sep3.model.operation;

import sep3.model.CPU;

public class JsrOperation extends Operation {
	private final CPU cpu;

	JsrOperation(CPU cpu) {
		super(cpu);
		this.cpu = cpu;
	}

	public void operate() {
		// AバスもBバスも使わない
		useABus(false);
		useBBus(false);

		// R7の値が必要なのでAバスへ流す
		cpu.getABusSelector().selectFrom(CPU.REG_R7);
		// Aバスの値をSバスへ渡す
		cpu.getSBus().setValue(cpu.getABus().getValue());

		// Sバスの値をToオペランドに書き込む
		writeBack(true);

		// この後R7を変更してジャンプを行う必要があるが
		// それはEX1にやらせる
	}
}
