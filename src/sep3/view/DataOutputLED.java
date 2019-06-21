package sep3.view;
import java.awt.*;

import javax.swing.*;

// データ出力用LED（16ビット）
@SuppressWarnings("serial")
public class DataOutputLED extends JPanel {
	private LED[] dataLED;

	public DataOutputLED() {
		// 4つ毎に小スペースを入れて区切って表示したいので、こういうプログラムにしている
		JPanel[] panel;
		panel = new JPanel[4];
		dataLED = new LED[16];
		setLayout(new FlowLayout());
		for (int i = 0; i < 4; ++i) {
			panel[i] = new JPanel();
			panel[i].setLayout(new GridLayout(1, 4));
			for (int j = 0; j < 4; ++j) {
				dataLED[i*4+j] = new RedLED();
				panel[i].add(dataLED[i*4+j]);
			}
			add(panel[i]);
		}
	}
	// 16ビット数値データをLEDのon/offに分割表示（2進数へ変換）
	public void setData(int v) {
		for (int i = dataLED.length - 1; i >= 0; --i) {
			if ((v & 0x01) == 0x01) {
				dataLED[i].on();
			} else {
				dataLED[i].off();
			}
			v >>= 1;
		}
	}
}
