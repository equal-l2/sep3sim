package sep3.model.cycle;

import sep3.*;
import sep3.model.CPU;

public class HaltState extends State {

	//HaltState(int s) { super(s); }

	@Override
	public State clockstep(Model model) {
		//System.out.println("%% HLT %%");
		model.getCPU().getRegister(CPU.REG_SC).setInitValue(StateFactory.SC_HLT);
		return this;
	}

}
