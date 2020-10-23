package sep3.model;

import java.util.LinkedList;

// バスの入力口、出力口に設置されているゲートのどれを選択して、バスへ／からデータを取得するか決める
// ための制御回路（セレクタと呼ぶ）
public class Selector {
	// 接続バス
	private final Bus outBus;
	private final Bus inBus;
	// 選択対象レジスタ管理用
	private LinkedList<Integer> toConnected, fromConnected;
	private CPU cpu;

	public Selector(Bus in, Bus out) {
		inBus = in;
		outBus = out;
		if (in != null) fromConnected = new LinkedList<>();
		if (out != null) toConnected = new LinkedList<>();
	}

	public Selector(CPU c, Bus in, Bus out) {
		this(in, out);
		cpu = c;
	}

	public void setCPU(CPU c) {
		cpu = c;
	}

	// 入力側ゲート集合に、新たなゲートを追加
	public void addFrom(int r) {
		fromConnected.add(r);
	}

	// 出力側ゲート集合に、新たなゲートを追加
	public void addTo(int r) {
		toConnected.add(r);
	}

	// 入力側ゲート集合のすべてを閉じる（つまり、バスには値0を流しているのと同じ状態になる）
	public void selectFrom() {
		inBus.setValue(0);
	}

	// 入力側ゲート集合のうち、レジスタrひとつだけを選択してバスへデータを流す
	public void selectFrom(int r) {
		for (int p : fromConnected) {
			if (p == r) {
				inBus.setValue(cpu.getRegister(r).getValue());
			}
		}
	}

	// 出力側ゲート集合のすべてを閉じる（どこへも書き込まない）
	public void selectTo() {
	}

	// 出力側ゲート集合のうち、ひとつゲートを開いてデータを渡す
	public void selectTo(int r) {
		for (int p : toConnected) {
			if (p == r) {
				cpu.getRegister(r).setValue(outBus.getValue());
			}
		}
	}

	// 出力側ゲート集合のうち、ふたつゲートを開いてデータを渡す
	public void selectTo(int r1, int r2) {
		for (int p : toConnected) {
			if (p == r1) {
				cpu.getRegister(r1).setValue(outBus.getValue());
			}
			if (p == r2) {
				cpu.getRegister(r2).setValue(outBus.getValue());
			}
		}
	}

	// 出力側ゲート集合のうち、みっつゲートを開いてデータを渡す
	public void selectTo(int r1, int r2, int r3) {
		for (int p : toConnected) {
			if (p == r1) {
				cpu.getRegister(r1).setValue(outBus.getValue());
			}
			if (p == r2) {
				cpu.getRegister(r2).setValue(outBus.getValue());
			}
			if (p == r3) {
				cpu.getRegister(r3).setValue(outBus.getValue());
			}
		}
	}
}
