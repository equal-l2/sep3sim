package sep3.model.runmode;

import sep3.Model;

// CPU走行モードの共通メソッド
public abstract class RunMode {
	private int id;

	public int getID() {
		return id;
	}

	public void setID(int i) {
		id = i;
	}

	abstract public void run(Model model);
}
