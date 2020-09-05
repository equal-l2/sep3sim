package sep3.view;

import java.awt.GridLayout;

import javax.swing.JPanel;

//16bit 表示8つのLCD
@SuppressWarnings("serial")
public class LCD extends JPanel {
	private final int NROW = 2;		// 行
	private final int NCOL = 4;		// 列
	private LabeledLED7segment[][] array;

	public LCD() {
		String label[][] = {{ "R0/MAR", "R1/MDR", "R2/Mbus", "R3/" },
							{ "R4/Abus", "R5/Bbus", "R6/Sbus", "B0/SC" }};
		setLayout(new GridLayout(NROW, NCOL));
		array = new LabeledLED7segment[NROW][NCOL];
		for (int i = 0; i < NROW; ++i) {
			for (int j = 0; j < NCOL; ++j) {
				array[i][j] = new LabeledLED7segment(label[i][j], 10, (i%2==1)?"South":"North");
				add(array[i][j]);
			}
		}
	}

	public LED7segment getLED7segment(int i, int j) {
		return array[i][j].getLED7segment();
	}

	public void powerOff() {
		for (int i = 0; i < NROW; ++i) {
			for (int j = 0; j < NCOL; ++j) {
				array[i][j].getLED7segment().powerOff();
			}
		}
	}
}
