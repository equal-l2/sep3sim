package sep3.model.runmode;

import sep3.misc.Factory;

// CPU走行モード集合
public class RunModeFactory extends Factory<Integer, RunMode> {
	public static final int RM_NORMAL = 0;    // 通常走行モード
	public static final int RM_CLOCK = 1;    // クロックステップ走行モード
	public static final int RM_INST = 2;    // 命令ステップ走行モード

	public RunModeFactory() {
		makeItem(RM_NORMAL, new NormalMode());
		makeItem(RM_CLOCK, new ClockStepMode());
		makeItem(RM_INST, new InstructionStepMode());
	}

	public RunMode getRunMode(int key) {
		return getItem(key);
	}
}
