package sep3.model.runmode;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observable;

// CPU走行モードを変更した場合、その事実をモデルからビューに伝えるためのクラス
public class ListenableRunMode {
	private RunMode rm;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public RunMode getRunMode() {
		return rm;
	}

	public void setRunMode(RunMode m) {
		var old = rm;
		rm = m;
		pcs.firePropertyChange(null, null, null);
	}

	public void addListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}
}
