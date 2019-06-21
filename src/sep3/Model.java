package sep3;
import sep3.model.Bus;
import sep3.model.CPU;
import sep3.model.Memory;
import sep3.model.OnOffFlag;
import sep3.model.Selector;
import sep3.model.runmode.ObservableRunMode;
import sep3.model.runmode.RunMode;
import sep3.model.runmode.RunModeFactory;

public class Model {
	private Bus			addrBus, dataBus;  // アドレスバスとデータバス
	private CPU			cpu;               // SEP-3 CPU
	private Memory		mem;               // 主記憶装置
	private OnOffFlag	dispSW;            // R7, ISRの表示切替
	private OnOffFlag	powerSW;           // 電源スイッチ

	// 走行モードごとに仕事を生成
	RunModeFactory rmf = new RunModeFactory();
	// 現走行モードのコンテナ
	ObservableRunMode currentRunMode = new ObservableRunMode();
	public ObservableRunMode getObservableRunMode()		{ return currentRunMode; }

	// 各種 getter
	public Bus getAddrBus()					{ return addrBus; }
	public Bus getDataBus()					{ return dataBus; }
	public Selector getAddrBusSelector()	{ return addrBus.getSelector(); }
	public Selector getDataBusSelector()	{ return dataBus.getSelector(); }
	public CPU getCPU()						{ return cpu; }
	public Memory getMemory()				{ return mem; }
	public OnOffFlag getDispSW()			{ return dispSW; }
	public OnOffFlag getPowerSW()			{ return powerSW; }

	// SEP-3 のモデル生成
	public Model() {
		addrBus   = new Bus(Bus.NeedSelector, !Bus.NeedSelector);	// アドレスバスの出力先セレクタは不要（メモリしかない）
		dataBus   = new Bus(Bus.NeedSelector, Bus.NeedSelector);	// データバスはどちらにもセレクタ必要
		cpu       = new CPU(addrBus, dataBus);
		getAddrBusSelector().setCPU(cpu);
		getDataBusSelector().setCPU(cpu);
		mem       = new Memory(addrBus, dataBus);
		dispSW    = new OnOffFlag();
		powerSW   = new OnOffFlag();
		currentRunMode.setRunMode(rmf.getRunMode(RunModeFactory.RM_NORMAL));	// 最初に起動するときは、通常走行モード
	}

	// only for test
	String msg;
	public String getMessage() { return msg; }
	String stack2string(StackTraceElement[] sa) {
		String msg = "";
		for (int i = 0; i < sa.length; ++i) {
			msg = msg + sa[i].toString() + "\n";
		}
		return msg;
	}

	// 電源投入
	public void powerOn() {
		mem.powerOn();
		cpu.powerOn();
		reset();
	}

	// 電源断
	public void powerOff() {
		cpu.powerOff();
		mem.powerOff();
		setRunMode(getRunMode().getID());	// 走行モードは変えない（Viewに伝えて走行モード表示のLEDを消灯してもらうために実行する
	}

	// 電源を落とさずにリセット（主記憶装置はそのままで、他を初期化）
	public void reset() {
		cpu.reset();
		mem.reset();
		setRunMode(getRunMode().getID());	// 走行モードは変えない（Viewに伝えてLEDを点灯してもらうために実行する）
		addrBus.setValue(0);
		dataBus.setValue(0);
		cpu.setCurrentState(cpu.getCurrentState().clockstep(this));	// まず最初のゲートを開ける
	}

	// 走行モード設定
	public void setRunMode(int rm)	{ currentRunMode.setRunMode(rmf.getRunMode(rm)); }
	public RunMode getRunMode()		{ return currentRunMode.getRunMode(); }

	// 実行
	public void run()	{ currentRunMode.getRunMode().run(this); }
	// モデルにクロック投入（1ステップの状態遷移をさせる）： 上記 run() の中で使う
	public void clock()	{ cpu.clock(); }

}
