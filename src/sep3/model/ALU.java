package sep3.model;

import sep3.model.operation.*;

// SEP-3 の算術論理演算回路
public class ALU {
	@SuppressWarnings("unused")
	private Bus aBus, bBus, sBus;
	@SuppressWarnings("unused")
	private Register psw;
	private InstructionSet iset;

	// ALUをnewするときは、Aバス、Bバス、Sバスと、PSWを指定する
	ALU(Bus a, Bus b, Bus s, Register r, InstructionSet is) {
		aBus = a; bBus = b; sBus = s; psw = r; iset = is;
	}

	// ALUに動作指定opを与えて動かす
	// opの定義は、sep3.model.operation.InstructionSetにある
	public void operate(int op) {
		// 演算ごとに動作を切り替えて行う。
		// それぞれのopの動作は、sep3.model.operation内のクラスファイルに定義する
		Operation o = iset.getOperation(op);
		o.operate();
	}
}
