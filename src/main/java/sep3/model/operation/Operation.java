package sep3.model.operation;

import sep3.model.CPU;
import sep3.model.Decoder;

// 命令に共通するメソッド
public abstract class Operation {
	private final CPU cpu;

	public Operation(CPU cpu) {
		this.cpu = cpu;
	}

	public abstract void operate();                // 命令ごとの演算処理本体

	// 以下、すべてのOperationに共通して使えるメソッド

	// EX0状態における、この操作のAバス使用状況。trueなら使う、falseなら使わない。
	public void useABus(boolean b) {
		if (b) cpu.getABusSelector().selectFrom(CPU.REG_MDR);
		else cpu.getABusSelector().selectFrom();
	}

	// EX0状態における、この操作のBバス使用状況。trueなら使う、falseなら使わない。
	public void useBBus(boolean b) {
		if (b) cpu.getBBusSelector().selectFrom(CPU.REG_B0);
		else cpu.getBBusSelector().selectFrom();
	}

	// EX0状態における、この操作の結果（Sバス上の値）の扱い。
	// trueなら、Toオペランドがレジスタアドレシングのときは該当レジスタに書き込む。
	//                         それ以外のアドレシングのときは、MDRへ書き込む
	// falseのときはなにもしない。
	public void writeBack(boolean b) {
		if (b) {
			if (cpu.getDecoder().getToMode() == Decoder.MODE_D) {
				cpu.getSBusSelector().selectTo(cpu.getDecoder().getToRegister());
			} else {
				cpu.getSBusSelector().selectTo(CPU.REG_MDR);
			}
		}
	}

	// 指定ビットの状態を返す
	public boolean bit(int i, int mask) {
		return (i & mask) != 0;
	}

	// 値oにしたがって、N（符号）とZ（ゼロ）ビットを更新する
	public int psw_NZ(int o) {
		int p = 0;
		if (bit(o, 0x8000)) {
			p |= CPU.PSW_N;
		}
		if (o == 0) {
			p |= CPU.PSW_Z;
		}
		return p;
	}
}
