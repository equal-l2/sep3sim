package sep3.model.operation;

import sep3.model.CPU;

public class RbvOperation extends Operation {
	private final CPU cpu;

	RbvOperation(CPU cpu) {
		super(cpu);
		this.cpu = cpu;
	}

	public void operate() {
		final int p = cpu.getRegister(CPU.REG_PSW).getValue();
		int op;
		if ((p & CPU.PSW_V) != 0) {
			op = InstructionSet.OP_RJP;
		} else {
			op = InstructionSet.OP_NOP;
		}
		cpu.getALU().operate(op);
	}
}
