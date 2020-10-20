package sep3.model.operation;

import sep3.model.CPU;

public class AslOperation extends Operation {
	private final CPU cpu;

	AslOperation(CPU cpu) {
		super(cpu);
		this.cpu = cpu;
	}

	public void operate() {
		// V: シフト前のMSB != 後処理前のMSB
		// C: 後処理前のMSB

		// AバスにMDRの値を出力し、Bバスは使わない
		// (Fオペランドがないため)
		useABus(true);
		useBBus(false);

		// Bバスの値を左シフトしてSバスに渡す
		final int i = cpu.getABus().getValue();
		final int j = i << 1;

		final int sign = i & 0x8000; // 入力の符号ビットのみ
		final int data = j & 0x7FFF; // 結果のデータビットのみ(最上位ビットを0にした)
		final int o = sign | data; // 結果の符号ビットを入力の符号ビットで置き換え

		cpu.getSBus().setValue(o);

		// Sバスの値をToオペランドに書き込む
		writeBack(true);

		// PSWの更新
		int p = psw_NZ(o & 0xFFFF);

		boolean overflow = (i & 0x8000) != (j & 0x8000); // シフト前とシフト後で符号ビットが違う -> オーバーフロー
		boolean carry = (j & 0x8000) != 0; // シフト後(後処理前)の最上位ビットはデータビットの溢れ分
		if (overflow) {
			p |= CPU.PSW_V;
		}
		if (carry) {
			p |= CPU.PSW_C;
		}
		cpu.getRegister(CPU.REG_PSW).setValue(p);
	}
}
