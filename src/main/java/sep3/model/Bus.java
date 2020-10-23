package sep3.model;

import sep3.controller.LCDDisplayable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observable;

// SEP-3 のバス
public class Bus implements LCDDisplayable {
	public static final boolean NeedSelector = true;
	private int value;                            // このバスに現在流れている値
	private Selector selector;                        // どのレジスタの値を選択的に流すか決める回路
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public Bus(boolean in, boolean out) {
		// このバスへデータを流すためのセレクタ（in）と、バスから渡すためのセレクタ（out）を登録する
		if (in & out) {
			selector = new Selector(this, this);
		} else if (in) {
			selector = new Selector(this, null);
		} else if (out) {
			selector = new Selector(null, this);
		}
	}

	public Bus(CPU cpu, boolean in, boolean out) {
		this(in, out);
		selector.setCPU(cpu);
	}

	// 各種getterとsetter
	public int getValue() {
		return value;
	}

	public void setValue(int v) {
		var old = value;
		value = v;
		pcs.firePropertyChange(null,null,null);
	}

	public Selector getSelector() {
		return selector;
	}

	public void setSelector(Selector s) {
		selector = s;
	}

	// 表示のため、モデルが更新されたかのように扱う
	public void touch() {
		pcs.firePropertyChange(null,null,null);
	}

	public void addListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}
}
