package sep3.view;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

// 押しボタン
@SuppressWarnings("serial")
public class LabeledLED extends JPanel {
	LED led;
	JLabel  l;

	public static final int LED_GREEN = 1;
	public static final int LED_RED = 2;

	public LED getLED() { return led; }

	// ラベル文字列labelをポイント数ptで、パネルのpos位置（"North", "West"など）に表示してあるLEDを作る。
	public LabeledLED(int color, String label, int labelPt, String pos) {
		setLayout(new BorderLayout());
		switch (color) {
		case LED_GREEN:
			led = new GreenLED();
			break;
		case LED_RED:
			led = new RedLED();
			break;
		default:
			led = new LED();
			break;
		}
		add(led, "Center");

		Font font = new Font("Arial", Font.PLAIN, labelPt);
		l = new JLabel(label);
		l.setFont(font);
		l.setHorizontalAlignment(JLabel.CENTER);
		l.setVerticalAlignment(JLabel.CENTER);
		add(l, pos);

		//setSelected(true);			// off状態から始める
	}
	public LabeledLED(int color, String label, String pos) { this(color, label,  10, pos); }	// デフォルトのラベルフォントの大きさptは10
	public LabeledLED(int color, String label) { this(color, label, 10, "South"); }				// デフォルトの位置は、ボタンの下
	public LabeledLED(int color, String label, int pt) { this(color, label, pt, "South"); }		// ptだけ変える
	public LabeledLED(int color) { this(color, ""); }											// 何も書いてないときは、ラベル文字列なし
	public LabeledLED() { this(LED_RED); }														// デフォルトの色は赤
}
