package sep3.model.cycle;

import sep3.*;
import sep3.model.CPU;

public class IllState extends State {

	//IllState(int s) { super(s); }

	@Override
	public State clockstep(Model model) {
		model.getCPU().getRegister(CPU.REG_SC).setInitValue(StateFactory.SC_ILL);
		return this;
	}

}
