package sep3.view;

import javax.swing.*;
import java.awt.*;

// データ入力用トグルスイッチ（16個）
@SuppressWarnings("serial")
public class DataInputSwitch extends JPanel {
	private final ToggleSwitch[] dataSwitch;

	public DataInputSwitch() {
		// 4つ毎に小スペースを入れて区切って表示したいので、こういうプログラムにしている
		JPanel[] panel;
		panel = new JPanel[4];
		dataSwitch = new ToggleSwitch[16];
		setLayout(new FlowLayout());
		for (int i = 0; i < 4; ++i) {
			panel[i] = new JPanel();
			panel[i].setLayout(new GridLayout(1, 4));
			for (int j = 0; j < 4; ++j) {
				dataSwitch[i * 4 + j] = new ToggleSwitch();
				panel[i].add(dataSwitch[i * 4 + j]);
			}
			add(panel[i]);
		}
	}

	// スイッチ状態を読み取って、16ビット数値データにして返す
	public int getData() {
		int v = 0;
		for (ToggleSwitch sw : dataSwitch) {
			v <<= 1;
			if (sw.isSelected()) {
				++v;
			}
		}
		return v & 0xFFFF;
	}
}
