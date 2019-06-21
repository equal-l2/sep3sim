package sep3.view;
import java.awt.*;
import javax.swing.*;

// データ入力用トグルスイッチ（16個）
@SuppressWarnings("serial")
public class DataInputSwitch extends JPanel {
	private ToggleSwitch[] dataSwitch;

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
				dataSwitch[i*4+j] = new ToggleSwitch();
				panel[i].add(dataSwitch[i*4+j]);
			}
			add(panel[i]);
		}
	}
	// スイッチ状態を読み取って、16ビット数値データにして返す
	public int getData() {
		int v = 0;
		for (int i = 0; i < dataSwitch.length; ++i) {
			v <<= 1;
			if (dataSwitch[i].isSelected()) {
				++v;
			}
		}
		return v & 0xFFFF;
	}
}
