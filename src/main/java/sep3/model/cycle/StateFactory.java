package sep3.model.cycle;

import sep3.misc.Factory;

public class StateFactory extends Factory<Integer, State> {
	public static final int SC_IF0  = 0x0001;
	public static final int SC_IF1  = 0x0002;
	public static final int SC_FF0  = 0x0004;
	public static final int SC_FF1  = 0x0008;
	public static final int SC_FF2  = 0x0010;
	public static final int SC_TF0  = 0x0020;
	public static final int SC_TF1  = 0x0040;
	public static final int SC_EX0  = 0x0080;
	public static final int SC_EX1  = 0x0100;
	public static final int SC_HLT  = 0x8000;
	public static final int SC_ILL  = 0x8001;

	public StateFactory() {
		makeItem(new Integer(SC_IF0), new InstFetchState0());
		makeItem(new Integer(SC_IF1), new InstFetchState1());
		makeItem(new Integer(SC_FF0), new FromFetchState0());
		makeItem(new Integer(SC_FF1), new FromFetchState1());
		makeItem(new Integer(SC_FF2), new FromFetchState2());
		makeItem(new Integer(SC_TF0), new ToFetchState0());
		makeItem(new Integer(SC_TF1), new ToFetchState1());
		makeItem(new Integer(SC_EX0), new ExecState0());
		makeItem(new Integer(SC_EX1), new ExecState1());
		makeItem(new Integer(SC_HLT), new HaltState());
		makeItem(new Integer(SC_ILL), new IllState());
	}
	public State getState(Integer key) { return getItem(key); }
	//public int   getValue(Integer key) { return key.intValue(); }
}
