package sep3.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

// 押しボタン
@SuppressWarnings("serial")
public class PushButton extends JButton {
	static final String onFile = "sep3/view/pushbutton2.png";        // on時のリソース

	//static final String offFile = "sep3/view/PushButton2.png";		// off時のリソース
	public PushButton(String s) {
		ImageIcon onIcon = null;
		//ImageIcon offIcon = null;
		URL url = this.getClass().getClassLoader().getResource(onFile);
		if (url != null) {
			onIcon = new ImageIcon(url);
		}
		setIcon(onIcon);
		//url = getClass().getClassLoader().getResource(offFile);
		//if (url != null) {
		//	offIcon = new ImageIcon(url);
		//}
		//setIcon(offIcon);
		setPreferredSize(new Dimension(onIcon.getIconWidth(), onIcon.getIconHeight()));
		//setSelected(true);			// off状態から始める
	}
}
