package sep3.model;

import sep3.misc.FileIO;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;

// SEP-3 主記憶
public class Memory {
	public static final int MEM_RD = 0;   // 読み出しモード
	public static final int MEM_WR = 1;   // 書き込みモード

	private final Bus addrBus;
	private final Bus dataBus;        // アドレスバスとデータバス（CPUとのインタフェース）
	private final int[] mem = new int[0x10000];    // 記憶する場所
	private final IOValue ioValue;                // メモリマップトI/Oで出力した値を置く（ビューへの通知のため）
	private final OnOffFlag ackLamp;                // メモリマップトI/Oで入力要求があったことをビューへ通知するフラグ

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	// CPUとのインタフェースである、アドレスバス、データバスを受け取る
	public Memory(Bus aBus, Bus dBus) {
		addrBus = aBus;
		dataBus = dBus;
		ioValue = new IOValue();
		ackLamp = new OnOffFlag();
	}

	public IOValue getIOValue() {
		return ioValue;
	}

	public OnOffFlag getAckLamp() {
		return ackLamp;
	}

	// 読み出しか書き込みを指定してアクセスする
	public void access(int rw) {
		int addr = addrBus.getValue();
		if (rw == MEM_RD) {
			if (addr == 0xFFE0) {        // 入力のとき
				ackLamp.on();
			} else {
				dataBus.setValue(mem[addr]);
			}
		} else {
			if (addr == 0xFFE0) {        // 出力のとき
				ioValue.setValue(dataBus.getValue());
			} else {
				setValue(addr, dataBus.getValue());
			}
		}
	}

	// メモリの値をMMIOによるブロックなしで読む
	// TF1ではTオペランドが指す内容の値を命令に関わらず読み出す
	// しかしTオペランドを書き込みにしか使わない命令では
	// この時にMMIOでブロックしては困るので
	// こういう抜け道を作っておく必要がある
	public void readValueWithoutBlock() {
		int addr = addrBus.getValue();
		dataBus.setValue(mem[addr]);
	}

	// モデルの初期化
	public void powerOn() {
		for (int i = 0; i < mem.length; ++i) {
			mem[i] = 0;
		}
		pcs.firePropertyChange(null, null, null);
		reset();
		// IPL の仕事をする
		try {
			final String iplFile = "pro.bin";
			FileIO fio = new FileIO(this);
//			url = getClass().getClassLoader().getResource(iplFile);
//			InputStream in = (InputStream) url.getContent();
			InputStream in = getClass().getClassLoader().getResourceAsStream(iplFile);
			if (in != null) {
				fio.load(in);
				in.close();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void reset() {
		ackLamp.off();
		ioValue.setValue(0);
	}

	public void powerOff() {
		reset();
	}

	public void dump() {
		final int WIDTH = 8;
		for (int i = 0; i < 0x200; i += WIDTH) {
			System.out.printf("0x%04x: ", i);
			for (int j = 0; j < WIDTH; j++) {
				System.out.printf("%04x ", mem[i + j]);
			}
			System.out.println();
		}
	}

	// メモリを直接書き換えたいときに使う（binファイルのロード時）
	public void setValue(int addr, int value) {
		mem[addr] = value;
		pcs.fireIndexedPropertyChange(null, addr, null, null);
	}

	// DebugView用
	public int getValue(int addr) {
		return mem[addr];
	}

	public void addListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}
}
