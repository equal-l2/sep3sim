package sep3.model.operation;

import sep3.model.CPU;

// 通常のADD命令用
public class AddOperation extends Operation {
	private final CPU cpu;

	AddOperation(CPU cpu) {
		super(cpu);
		this.cpu = cpu;
	}

	public void operate() {
		// AバスにMDRの値を、BバスへB0の値を出力する
		useABus(true);
		useBBus(true);

		// 両バスの値を足してSバスに渡す
		int i = cpu.getABus().getValue();
		int j = cpu.getBBus().getValue();
		int o = i + j;
		cpu.getSBus().setValue(o & 0xFFFF);

		// Sバスの値をToオペランドに書き込む
		writeBack(true);

		// PSWの更新
		int p = psw_NZ(o & 0xFFFF);
		boolean sameMSBin = (i & 0x8000) == (j & 0x8000);
		boolean sameMSBout = (i & 0x8000) == (o & 0x8000);
		if (sameMSBin && !sameMSBout) {
			p |= CPU.PSW_V;
		}
		if (bit(o, 0x10000)) {
			p |= CPU.PSW_C;
		}
		cpu.getRegister(CPU.REG_PSW).setValue(p);
	}
}
