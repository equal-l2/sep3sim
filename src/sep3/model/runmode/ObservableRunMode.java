package sep3.model.runmode;

import java.util.Observable;

// CPU走行モードを変更した場合、その事実をモデルからビューに伝えるためのクラス
public class ObservableRunMode extends Observable {
	private RunMode rm;
	public void setRunMode(RunMode m)	{ rm = m; setChanged(); notifyObservers(); }
	public RunMode getRunMode()			{ return rm; }
}
