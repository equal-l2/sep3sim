package sep3.model.operation;

import sep3.model.CPU;

public class SubOperation extends Operation {
	private final CPU cpu;

	SubOperation(CPU cpu) {
		super(cpu);
		this.cpu = cpu;
	}

	public void operate() {
		// AバスにMDRの値を、BバスへB0の値を出力する
		useABus(true);
		useBBus(true);

		// 両バスの値を使って減算し、Sバスに渡す
		int i = cpu.getABus().getValue(); // Tオペランド
		int j = cpu.getBBus().getValue(); // Fオペランド
		int j_cmp = ~j + 1;
		int o = i + j_cmp; // 実質 i - j
		cpu.getSBus().setValue(o & 0xFFFF);

		// Sバスの値をToオペランドに書き込む
		writeBack(true);

		// PSWの更新
		int p = psw_NZ(o & 0xFFFF);
		boolean sameMSBin = (i & 0x8000) == (j & 0x8000);
		boolean sameMSBout = (i & 0x8000) == (o & 0x8000);
		if (!sameMSBin && !sameMSBout) {
			// FとTの符号が違い、かつTの符号と計算結果の符号が違うとき
			p |= CPU.PSW_V;
		}
		if (j > i) { // (uint)F > (uint)T
			p |= CPU.PSW_C;
		}
		cpu.getRegister(CPU.REG_PSW).setValue(p);
	}
}
