package sep3.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// モデル上のHLT, ILL状態をビューに通知するための変数
public class OnOffFlag {
	private boolean on = false;   // 起動時はoff
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void toggle() {
		setFlag(!on);
	}

	public void on() {
		setFlag(true);
	}

	public void off() {
		setFlag(false);
	}

	private void setFlag(boolean to) {
		var old = on;
		on = to;

		// フラグの変更をビューに通知する
		pcs.firePropertyChange(null,null,null);
	}

	public boolean isOn() {
		return on;
	}

	public void addListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}
}
