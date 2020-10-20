package sep3.model.operation;

import sep3.model.CPU;

public class RjsOperation extends Operation {
	private final CPU cpu;

	RjsOperation(CPU cpu) {
		super(cpu);
		this.cpu = cpu;
	}

	public void operate() {
		cpu.getALU().operate(InstructionSet.OP_JSR);
	}
}
