package sep3.model.operation;
import sep3.model.CPU;

public class BrnOperation extends Operation {
	private CPU cpu;
	BrnOperation(CPU cpu) { super(cpu); this.cpu = cpu; }
	public void operate() {
		final int p = cpu.getRegister(CPU.REG_PSW).getValue();
		int op;
		if ((p & CPU.PSW_N) != 0) {
			op = InstructionSet.OP_JMP;
		} else {
			op = InstructionSet.OP_NOP;
		}
		cpu.getALU().operate(op);
	}
}
