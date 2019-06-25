package sep3.view;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.*;

// 電源スイッチ
@SuppressWarnings("serial")
public class PowerSwitch extends JButton {
	private URL url;
	static final String onFile  = "sep3/view/powerbutton2.png";	// 電源on時のリソース
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
