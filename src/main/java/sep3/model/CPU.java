package sep3.model;
import sep3.model.cycle.State;
import sep3.model.cycle.StateFactory;
import sep3.model.operation.InstructionSet;

// SEP-3 CPU
public class CPU {
	private State			currentState;		// CPU の命令実行サイクル状態変数
	private StateFactory	sf;					// その状態集合
	private OnOffFlag		halted, illegal;	// CPU の停止状態（HLT）、不正命令実行状態（ILL）
	private InstructionSet	iset;				// SEP-3 の命令集合
	private Bus				aBus, bBus, sBus;	// A, B, S の各内部バス
	private Decoder			decoder;			// デコーダ
	private ALU				alu;				// ALU
	private Register[]		regs;				// 各種レジスタ
	public static final int REG_R0	= 0;		// 　R0からISRまでのレジスタ定義（PSW, SP, PCは別名も用意）
	public static final int REG_R1	= 1;		// 　数値は、LEDアレイの表示位置と連動している
	public static final int REG_R2	= 2;
	public static final int REG_R3	= 3;
	public static final int REG_R4	= 4;
	public static final int REG_R5	= 5;
	public static final int REG_PSW	= 5;
	public static final int REG_R6	= 6;
	public static final int REG_SP	= 6;
	public static final int REG_R7	= 7;
	public static final int REG_PC	= 7;
	public static final int REG_B0	= 8;
	public static final int REG_MAR	= 9;
	public static final int REG_MDR	= 10;
	public static final int REG_ISR	= 11;
	public static final int REG_DUMMY = 12;
	public static final int REG_SC	= 15;		// 　ステータスカウンタ（currentStateを示すレジスタ）


	public static final int PSW_N	= 0x8;		// PSW のビット定義
	public static final int PSW_Z	= 0x4;
	public static final int PSW_V	= 0x2;
	public static final int PSW_C	= 0x1;

	// CPU 構成要素のgetter
	public Bus				getABus()			{ return aBus; }
	public Bus				getBBus()			{ return bBus; }
	public Bus				getSBus()			{ return sBus; }
	public Selector			getABusSelector()	{ return aBus.getSelector(); }
	public Selector 		getBBusSelector()	{ return bBus.getSelector(); }
	public Selector 		getSBusSelector()	{ return sBus.getSelector(); }
	public Register			getRegister(int i)	{ return regs[i]; }
	public Decoder			getDecoder()		{ return decoder; }
	public ALU				getALU()			{ return alu; }
	public OnOffFlag		getHaltLamp()		{ return halted; }
	public OnOffFlag		getIllLamp()		{ return illegal; }
	public StateFactory		getStateFactory()	{ return sf; }
	public InstructionSet	getInstructionSet()	{ return iset; }

	// 主記憶装置とのインタフェースである、アドレスバス、データバスを受け取る
	public CPU(Bus addrBus, Bus dataBus) {
		// 命令セットの生成
		iset = new InstructionSet(this);
		// 各種構成要素を作成
		aBus    = new Bus(this, Bus.NeedSelector, !Bus.NeedSelector);
		bBus    = new Bus(this, Bus.NeedSelector, !Bus.NeedSelector);
		sBus    = new Bus(this, !Bus.NeedSelector, Bus.NeedSelector);
		decoder = new Decoder();
		regs    = new Register[REG_SC+1];
		for (int i = 0; i < REG_B0; ++i) {
			regs[i] = new Register();
		}
		regs[REG_B0]  = new Register();
		regs[REG_MAR] = new Register();
		regs[REG_MDR] = new Register();
		regs[REG_DUMMY] = new Register();
		regs[REG_SC]  = new Register();
		regs[REG_ISR] = new ISRegister(decoder, regs[REG_SC]);
		alu           = new ALU(aBus, bBus, sBus, regs[REG_PSW], iset);

		// A バスにレジスタ（R0～R7, MDR）を接続
		for (int i = REG_R0; i <= REG_R7; ++i) { getABusSelector().addFrom(i); }
		getABusSelector().addFrom(REG_MDR);
		// B バスにレジスタを接続（B0）
		getBBusSelector().addFrom(REG_B0);
		// S バスにレジスタを接続（R0～R7, MAR, MDR, B0）
		for (int i = REG_R0; i <= REG_R7; ++i) { getSBusSelector().addTo(i); }
		getSBusSelector().addTo(REG_MDR);
		getSBusSelector().addTo(REG_MAR);
		getSBusSelector().addTo(REG_B0);
		// アドレスバスにレジスタを接続
		addrBus.getSelector().addFrom(REG_MAR);
		// データバスにレジスタを接続
		dataBus.getSelector().addFrom(REG_MDR);
		dataBus.getSelector().addTo(REG_MDR);
		dataBus.getSelector().addTo(REG_ISR);
		// HLT, ILL状態をビューに伝えるための変数を作る
		halted  = new OnOffFlag();
		illegal = new OnOffFlag();
		// CPUの状態遷移を作る
		sf      = new StateFactory();
		// CPUリセット
		reset();
	}
	// 全レジスタにクロックを与える（レジスタの値が変わる）
	public void clock() {
		for (int i = REG_R0; i <= REG_ISR; ++i) { regs[i].clock(); }
	}
	// CPUリセット
	public void reset() {
		for (int i = REG_R0; i <= REG_R5; ++i) { regs[i].setInitValue(0); }
		regs[REG_R6].setInitValue(0x0010);
		regs[REG_R7].setInitValue(0xF800);
		regs[REG_B0].setInitValue(0);
		regs[REG_MAR].setInitValue(0);
		regs[REG_MDR].setInitValue(0);
		regs[REG_DUMMY].setInitValue(0);
		regs[REG_ISR].setInitValue(0);
		aBus.setValue(0);
		bBus.setValue(0);
		sBus.setValue(0);
		halted.off();
		illegal.off();
		// 命令実行サイクルを、読み出しの最初の状態にセットする
		currentState = sf.getState(StateFactory.SC_IF0);
	}
	// 電源入の処理
	public void powerOn() { reset(); }
	// 電源断の処理
	public void powerOff() {
		for (int i = REG_R0; i <= REG_ISR; ++i) { regs[i].setInitValue(0); }
		regs[REG_SC].setInitValue(0);
		aBus.setValue(0);
		bBus.setValue(0);
		sBus.setValue(0);
		halted.off();
		illegal.off();
	}
	// 命令実行サイクルのどの状態にあるかを得る、セットする
	public State getCurrentState()		{ return currentState; }
	public void setCurrentState(State s) { currentState = s; }
}

