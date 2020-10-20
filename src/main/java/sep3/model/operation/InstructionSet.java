package sep3.model.operation;

import sep3.misc.Factory;
import sep3.model.CPU;

// SEP-3 の命令セット
public class InstructionSet extends Factory<Integer, Operation> {
	public static final int OP_HLT = 0x00;        // 値は、操作コードのビット列に従う
	public static final int OP_CLR = 0x04;
	public static final int OP_ASL = 0x08;
	public static final int OP_ASR = 0x09;
	public static final int OP_LSL = 0x0C;
	public static final int OP_LSR = 0x0D;
	public static final int OP_ROL = 0x0E;
	public static final int OP_ROR = 0x0F;
	public static final int OP_MOV = 0x10;
	public static final int OP_JMP = 0x11;
	public static final int OP_RET = 0x12;
	public static final int OP_RIT = 0x13;
	public static final int OP_ADD = 0x14;
	public static final int OP_RJP = 0x15;
	public static final int OP_SUB = 0x18;
	public static final int OP_CMP = 0x1B;
	public static final int OP_NOP = 0x1C;
	public static final int OP_OR = 0x20;
	public static final int OP_XOR = 0x21;
	public static final int OP_AND = 0x22;
	public static final int OP_BIT = 0x23;
	public static final int OP_JSR = 0x2C;
	public static final int OP_RJS = 0x2D;
	public static final int OP_SVC = 0x2E;
	public static final int OP_BRN = 0x30;
	public static final int OP_BRZ = 0x31;
	public static final int OP_BRV = 0x32;
	public static final int OP_BRC = 0x33;
	public static final int OP_RBN = 0x38;
	public static final int OP_RBZ = 0x39;
	public static final int OP_RBV = 0x3A;
	public static final int OP_RBC = 0x3B;
	public static final int OP_INC = 0x100;        // 命令とは直接関係がなく、内部でだけ使う＋１動作
	public static final int OP_DEC = 0x101;        // 命令とは直接関係がなく、内部でだけ使う－１動作
	public static final int OP_THRA = 0x102;        // 命令とは直接関係がなく、Aバスのデータを素通しする動作
	public static final int OP_THRB = 0x103;        // 命令とは直接関係がなく、Bバスのデータを素通しする動作
	public static final int OP_ADD2 = 0x104;        // ADDと同じだが、PSW更新せず
	public static final int OP_ILL = 0x200;        // 不正な命令

	private final Operation illop;

	public InstructionSet(CPU cpu) {
		makeItem(OP_HLT, new HltOperation(cpu));
		makeItem(OP_CLR, new ClrOperation(cpu));
		makeItem(OP_ASL, new AslOperation(cpu));
		makeItem(OP_ASR, new AsrOperation(cpu));
		makeItem(OP_LSL, new LslOperation(cpu));
		makeItem(OP_LSR, new LsrOperation(cpu));
		makeItem(OP_ROL, new RolOperation(cpu));
		makeItem(OP_ROR, new RorOperation(cpu));
		makeItem(OP_MOV, new MovOperation(cpu));
		makeItem(OP_JMP, new JmpOperation(cpu));
		makeItem(OP_RET, new RetOperation(cpu));
		makeItem(OP_RIT, new RitOperation(cpu));
		makeItem(OP_ADD, new AddOperation(cpu));
		makeItem(OP_RJP, new RjpOperation(cpu));
		makeItem(OP_SUB, new SubOperation(cpu));
		makeItem(OP_CMP, new CmpOperation(cpu));
		makeItem(OP_NOP, new NopOperation(cpu));
		makeItem(OP_OR, new OrOperation(cpu));
		makeItem(OP_XOR, new XorOperation(cpu));
		makeItem(OP_AND, new AndOperation(cpu));
		makeItem(OP_BIT, new BitOperation(cpu));
		makeItem(OP_JSR, new JsrOperation(cpu));
		makeItem(OP_RJS, new RjsOperation(cpu));
		makeItem(OP_SVC, new SvcOperation(cpu));
		makeItem(OP_BRN, new BrnOperation(cpu));
		makeItem(OP_BRZ, new BrzOperation(cpu));
		makeItem(OP_BRV, new BrvOperation(cpu));
		makeItem(OP_BRC, new BrcOperation(cpu));
		makeItem(OP_RBN, new RbnOperation(cpu));
		makeItem(OP_RBZ, new RbzOperation(cpu));
		makeItem(OP_RBV, new RbvOperation(cpu));
		makeItem(OP_RBC, new RbcOperation(cpu));
		makeItem(OP_INC, new IncOperation(cpu));
		makeItem(OP_DEC, new DecOperation(cpu));
		makeItem(OP_THRA, new ThraOperation(cpu));
		makeItem(OP_THRB, new ThrbOperation(cpu));
		makeItem(OP_ADD2, new Add2Operation(cpu));
		illop = new IllOperation(cpu);
		makeItem(OP_ILL, illop);
	}

	// 用意されている命令であるかどうかのチェック（不正命令のチェック）
	public boolean isLegalInstruction(int opcode) {
		Operation v;
		switch (opcode) {
			case OP_INC:
			case OP_DEC:
			case OP_THRA:
			case OP_THRB:
			case OP_ADD2:
			case OP_ILL:
				v = null;
				break;
			default:
				v = getItem(opcode);
				break;
		}
		return v != null;
	}

	// 操作コードを指定して、命令クラスを取得する
	public Operation getOperation(int opcode) {
		Operation v;
		v = getItem(opcode);
		if (v == null) {
			v = illop;
		}
		return v;
	}
}
