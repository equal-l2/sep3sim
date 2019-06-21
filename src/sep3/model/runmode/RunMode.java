package sep3.model.runmode;

import sep3.Model;

// CPU走行モードの共通メソッド
public abstract class RunMode {
	private int id;
	public void setID(int i) { id = i; }
	public int  getID()      { return id; }
	abstract public void run(Model model);
}
