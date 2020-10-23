package sep3.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// メモリマップトI/OでLEDに出力したい場合、その事実をモデルからビューに伝えるための変数
public class IOValue {
	private int value;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public int getValue() {
		return value;
	}

	public void setValue(int v) {
		var old = value;
		value = v;
		// 出力すべきものが来たので、ビューに知らせて表示してもらう
		pcs.firePropertyChange("value", old, value);
	}

	public void addListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}
}
