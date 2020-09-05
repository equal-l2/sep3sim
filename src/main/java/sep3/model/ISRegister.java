package sep3.model;

import sep3.model.cycle.StateFactory;

// 命令レジスタ：　ただのレジスタにはない機能が必要
public class ISRegister extends Register {
	private Decoder decoder;
	private Register sc;

	ISRegister(Decoder d, Register r) {
		decoder =d;
		sc = r;
	}

	public void clock() {
		if (sc.getValue() == StateFactory.SC_IF1) {	// 命令読み出し終了のときに仕事をする
			super.clock();							// ISRを更新して
			decoder.decode(super.getValue());		// 命令が格納されたので、デコーダに渡す
			//System.out.println("ISR="+String.format("%1$04x", (super.getValue() & 0xFFFF)).toUpperCase());
			setChanged();
			notifyObservers();
		}
	}
}
