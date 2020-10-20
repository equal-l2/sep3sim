package sep3.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

// 電源スイッチ
@SuppressWarnings("serial")
public class PowerSwitch extends JButton {
	static final String onFile = "powerbutton2.png";    // 電源on時のリソース
	private final URL url;

	//static final String offFile = "sep3/view/poweroff.png";	// 電源off時のリソース
	public PowerSwitch() {
		ImageIcon onIcon = null;
		//ImageIcon offIcon = null;
		url = getClass().getClassLoader().getResource(onFile);
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
		//setSelected(false);			// off状態から始める
	}
}
