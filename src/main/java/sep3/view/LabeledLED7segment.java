package sep3.view;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

// 押しボタン
@SuppressWarnings("serial")
public class LabeledLED7segment extends JPanel {
	LED7segment led7;

	public LED7segment getLED7segment() { return led7; }

	// ラベル文字列labelをポイント数ptで、パネルのpos位置（"North", "West"など）に表示してある7セグメントLEDを作る。
	// 7セグメントLEDの表示ポイント数はLEDpt。
	public LabeledLED7segment(String label, int labelPt, String pos, int LEDpt) {
		setLayout(new BorderLayout());
		led7 = new LED7segment(LEDpt);
		add(led7, "Center");

		Font font = new Font("Arial", Font.PLAIN, labelPt);
		JLabel l = new JLabel(label);
		l.setFont(font);
		l.setHorizontalAlignment(JLabel.CENTER);
		l.setVerticalAlignment(JLabel.CENTER);
		add(l, pos);

		//setSelected(true);			// off状態から始める
	}
	public LabeledLED7segment(String label, int labelPt, String pos) { this(label, labelPt, pos, 20); }	// LEDの表示はデフォルト20pt
	public LabeledLED7segment(String label, String pos) { this(label,  10, pos); }	// デフォルトのラベルフォントの大きさptは10
	public LabeledLED7segment(String label) { this(label, 10, "South"); }			// デフォルトの位置は、ボタンの下
	public LabeledLED7segment(String label, int pt) { this(label, pt, "South"); }	// ptだけ変える
	public LabeledLED7segment() { this(""); }										// 何も書いてないときは、ラベル文字列なし
}
