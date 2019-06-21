package sep3.model.cycle;

import sep3.Model;

public abstract class State {
	public abstract State clockstep(Model model);
}
