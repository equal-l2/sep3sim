package sep3.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

// トグルスイッチ
@SuppressWarnings("serial")
public class ToggleSwitch extends JToggleButton {
	static final String onFile = "toggleon.png";        // on時のリソース
	static final String offFile = "toggleoff.png";    // off時のリソース

	public ToggleSwitch() {
		ImageIcon onIcon = null, offIcon = null;
		URL url = this.getClass().getClassLoader().getResource(onFile);
		if (url != null) {
			onIcon = new ImageIcon(url);
		}
		setSelectedIcon(onIcon);
		url = getClass().getClassLoader().getResource(offFile);
		if (url != null) {
			offIcon = new ImageIcon(url);
		}
		setIcon(offIcon);
		setPreferredSize(new Dimension(onIcon.getIconWidth(), onIcon.getIconHeight()));
		setSelected(false);            // off状態から始める
	}
}
