package sep3.view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

// 7セグメントLED
@SuppressWarnings("serial")
public class LED7segment extends JLabel {
	private int value;

	public LED7segment(int fontSize) {
		// 現物に似せて作るのは大変なので、単なるテキストで誤魔化す
		setFont(new Font("Courier New", Font.BOLD, fontSize));
		value = 0;
		setBorder(new LineBorder(Color.BLACK, 1, true));
		powerOff();
	}

	public LED7segment() {
		this(20);			// 標準は20pt（結構大きい）
	}

	public void setValue(int v)	{ value = v; setText(String.format("%1$04x", (value & 0xFFFF)).toUpperCase()); }
	public void powerOff()		{ setText("    "); }	// 電源offのときは、何も表示しない（空白文字を4つ表示する）
}
