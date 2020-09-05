package sep3;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import sep3.view.DataInputSwitch;
import sep3.view.DataOutputLED;
import sep3.view.LCD;
import sep3.view.LED;
import sep3.view.LED7segment;
import sep3.view.LabeledLED;
import sep3.view.LabeledLED7segment;
import sep3.view.LabeledPushButton;
import sep3.view.PowerSwitch;
import sep3.view.ToggleSwitch;

@SuppressWarnings("serial")
public class View extends JFrame {
	private JMenuItem			fmFile, fmExit, mmNormal, mmInst, mmClock;
	private PowerSwitch			powerSW;
	private LCD					lcd;
	private LabeledLED7segment	seg;
	private DataOutputLED		outLED;
	private LabeledLED			itfLED, illLED, hltLED, ackLED;
	private LabeledLED			normalLED, instLED, clockLED;
	private ToggleSwitch		dispSW, stopSW;
	private DataInputSwitch		inSW;
	private LabeledPushButton	startButton, resetButton, ackButton;
	private JMenuItem dump;

	// 各種 getter
	public JMenuItem		getFileMenu()			{ return fmFile; }
	public JMenuItem		getExitMenu()			{ return fmExit; }
	public JMenuItem		getNormalMenu()			{ return mmNormal; }
	public JMenuItem		getInstMenu()			{ return mmInst; }
	public JMenuItem		getClockMenu()			{ return mmClock; }
	public PowerSwitch		getPowerSwitch()		{ return powerSW; }
	public LCD				getLCD()				{ return lcd; }
	public LED7segment		getR7ISR()				{ return seg.getLED7segment(); }
	public DataOutputLED	getDataOutputLED()		{ return outLED; }
	public LED				getIllLED()				{ return illLED.getLED(); }
	public LED				getHltLED()				{ return hltLED.getLED(); }
	public LED				getAckLED()				{ return ackLED.getLED(); }
	public LED				getNormalRunLED()		{ return normalLED.getLED();}
	public LED				getInstRunLED()			{ return instLED.getLED(); }
	public LED				getClockRunLED()		{ return clockLED.getLED(); }
	public ToggleSwitch		getDispSW()				{ return dispSW; }
	public ToggleSwitch		getStopSW()				{ return stopSW; }
	public DataInputSwitch	getDataInputSwitch()	{ return inSW; }
	public JButton			getStartButton()		{ return startButton.getButton(); }
	public JButton			getResetButton()		{ return resetButton.getButton(); }
	public JButton			getAckButton()			{ return ackButton.getButton(); }
	public JMenuItem getDump() { return dump; }

	public View() {
		setLayout(new BorderLayout());

		// 最上段にメニューバー
		JMenuBar menu = new JMenuBar();
			JMenu fm = new JMenu("File"); fm.setMnemonic('F');
			fmFile = new JMenuItem("Open .bin file"); fmFile.setMnemonic('O');
			fm.add(fmFile);
			fmExit = new JMenuItem("Exit"); fmExit.setMnemonic('E');
			fm.add(fmExit);
		menu.add(fm);
			JMenu mm = new JMenu("Run Mode"); mm.setMnemonic('M');
			mmNormal = new JMenuItem("Normal"); mmNormal.setMnemonic('N');
			mm.add(mmNormal);
			mmInst = new JMenuItem("Instruction Step"); mmInst.setMnemonic('I');
			mm.add(mmInst);
			mmClock = new JMenuItem("Clock Step"); mmClock.setMnemonic('C');
			mm.add(mmClock);
		menu.add(mm);
			JMenu util = new JMenu("Util");
			dump = new JMenuItem("Dump");
			util.add(dump);
		menu.add(util);
		add("North", menu);

		// 左パネルの最上部に電源スイッチ
		JPanel leftPanel = new JPanel();
		powerSW = new PowerSwitch();
		leftPanel.add("North", powerSW);
		add("West", leftPanel);

		// 中パネルにLCDとR7/ISRとLED列
		JPanel centerPanel = new JPanel();
		// 中パネル左にLCD
		lcd = new LCD();
		centerPanel.add("West", lcd);
		// 間をあけるための空白
		centerPanel.add("Center", new JLabel("            "));
		// 中パネル右にR7/ISR
		seg = new LabeledLED7segment("R7/ISR", 10, "North", 32);
		centerPanel.add("East", seg);
		// 中パネル下にLED列
		JPanel bot1Panel = new JPanel();
			// データ出力LED
			outLED = new DataOutputLED();
			bot1Panel.add("East", outLED);
			// 間をあけるための空白
			bot1Panel.add("Center", new JLabel("            "));
			// 単独LED群
			JPanel ledseq = new JPanel();
				ledseq.setLayout(new GridLayout(1, 8));
				ledseq.add(new LabeledLED(LabeledLED.LED_GREEN, " ", 8, "North"));		// 使っていないLED
				clockLED = new LabeledLED(LabeledLED.LED_GREEN, "Clock", 8, "North");
				ledseq.add(clockLED);
				instLED = new LabeledLED(LabeledLED.LED_GREEN, "Inst", 8, "North");
				ledseq.add(instLED);
				normalLED = new LabeledLED(LabeledLED.LED_GREEN, "Norm", 8, "North");
				ledseq.add(normalLED);
				itfLED = new LabeledLED(LabeledLED.LED_GREEN, " ", 8, "North");
				ledseq.add(itfLED);
				illLED = new LabeledLED(LabeledLED.LED_GREEN, "ILL", 8, "North");
				ledseq.add(illLED);
				hltLED = new LabeledLED(LabeledLED.LED_GREEN, "HLT", 8, "North");
				ledseq.add(hltLED);
				ackLED = new LabeledLED(LabeledLED.LED_GREEN, "ACK", 8, "North");
				ledseq.add(ackLED);
			bot1Panel.add("West", ledseq);
		centerPanel.add("South", bot1Panel);
		add("Center", centerPanel);

		// 右パネルは何もなし

		// 下パネルにスイッチ、ボタン
		JPanel bottomPanel = new JPanel();
			// 左から順に、単独トグルスイッチ2つ
			JPanel sw4Panel = new JPanel();
				sw4Panel.setLayout(new BoxLayout(sw4Panel, BoxLayout.X_AXIS));
				dispSW = new ToggleSwitch(); sw4Panel.add(dispSW);
				stopSW = new ToggleSwitch(); sw4Panel.add(stopSW);
			bottomPanel.add(sw4Panel);
			// その隣にデータ入力スイッチ
			inSW = new DataInputSwitch();
			bottomPanel.add(inSW);
			// さらにその隣にボタンが4つ
			JPanel button4Panel = new JPanel();
				button4Panel.setLayout(new GridLayout(1,4));
				ackButton   = new LabeledPushButton("Ack", 8);
				startButton = new LabeledPushButton("Start", 8);
				resetButton = new LabeledPushButton("Rest", 8);
				button4Panel.add(new LabeledPushButton(" ", 8));		// ラベルなしのボタン（一番左のやつ）
				button4Panel.add(ackButton);
				button4Panel.add(startButton);
				button4Panel.add(resetButton);
			bottomPanel.add(button4Panel);
		add("South", bottomPanel);

		// ウィンドウ消去の時の処理
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// アプリケーション名
		setTitle("SEP-3 Simulator");
		// 表示
		setSize(500,250);
		setVisible(true);
	}
}
