package sep3.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 押しボタン
@SuppressWarnings("serial")
public class LabeledPushButton extends JPanel {
	JButton b;
	JLabel  l;
	static final String onFile  = "sep3/view/pushbutton2.png";			// on時のリソース
	//static final String offFile = "sep3/view/PushButton2.png";		// off時のリソース

	public JButton getButton() { return b; }

	// ラベル文字列labelをポイント数ptで、パネルのpos位置（"North", "West"など）に表示してあるボタンを作る
	public LabeledPushButton(String label, int pt, String pos) {
		setLayout(new BorderLayout());
		b = new JButton();
		ImageIcon onIcon = null;
		//ImageIcon offIcon = null;
		URL url = this.getClass().getClassLoader().getResource(onFile);
		if (url != null) {
			onIcon = new ImageIcon(url);
		}
		b.setIcon(onIcon);
		//url = getClass().getClassLoader().getResource(offFile);
		//if (url != null) {
		//	offIcon = new ImageIcon(url);
		//}
		//setIcon(offIcon);
		add(b, "Center");
		b.setPreferredSize(new Dimension(onIcon.getIconWidth(), onIcon.getIconHeight()));

		Font font = new Font("Arial", Font.PLAIN, pt);
		l = new JLabel(label);
		l.setFont(font);
		l.setHorizontalAlignment(JLabel.CENTER);
		l.setVerticalAlignment(JLabel.CENTER);
		add(l, pos);

		//setSelected(true);			// off状態から始める

	}
	public LabeledPushButton(String label, String pos) { this(label,  20, pos); }	// デフォルトのptは20
	public LabeledPushButton(String label) { this(label, 20, "South"); }			// デフォルトの位置は、ボタンの下
	public LabeledPushButton(String label, int pt) { this(label, pt, "South"); }	// ptだけ変える
	public LabeledPushButton() { this(""); }										// 何も書いてないときは、ラベル文字列なし
}
