package sep3;

import javax.print.attribute.standard.DocumentName;
import javax.swing.*;
import java.awt.*;

public class DebugView extends JFrame {
	JTable dump;

	public DebugView() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());

		JTable addr = new JTable(8192, 1);
		addr.setFont(new Font("monospaced", Font.PLAIN, 14));
		for(int i = 0; i < 8192; i++) {
			addr.setValueAt(String.format("%04X", i*8), i, 0);
		}

		dump = new JTable(8192, 8);
		dump.setFont(new Font("monospaced", Font.PLAIN, 14));
		for(int i = 0; i < 8192; i++) {
			for(int j = 0; j < 8; j++) {
				dump.setValueAt("0000", i, j);
			}
		}

		p.add("West", addr);
		p.add("Center", dump);

		JScrollPane sp = new JScrollPane();
		sp.getViewport().add(p);
		add(sp);
		pack();
		setVisible(true);
	}

	public void setLabel(int addr, int val) {
		dump.setVisible(true);
		dump.setValueAt(String.format("%04X", val), addr / 8, addr % 8);
	}

	public void powerOff() {
		//dump.setVisible(false);
	}
}
