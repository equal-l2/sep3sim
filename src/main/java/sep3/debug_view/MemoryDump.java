package sep3.debug_view;

import javax.swing.*;
import java.awt.*;

public class MemoryDump extends JPanel {
	JTable dump;

	public MemoryDump() {
		setLayout(new BorderLayout());

		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());

		JTable addr = new JTable(8192, 1);
		addr.setFont(new Font("monospaced", Font.PLAIN, 14));
		for (int i = 0; i < 8192; i++) {
			addr.setValueAt(String.format("%04X", i * 8), i, 0);
		}
		addr.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));

		dump = new JTable(8192, 8);
		dump.setFont(new Font("monospaced", Font.PLAIN, 14));
		for (int i = 0; i < 8192; i++) {
			for (int j = 0; j < 8; j++) {
				dump.setValueAt("0000", i, j);
			}
		}

		p.add(addr, BorderLayout.WEST);
		p.add(dump, BorderLayout.CENTER);

		JScrollPane sp = new JScrollPane();
		sp.getViewport().add(p);
		add(new JLabel("Memory"), BorderLayout.NORTH);
		add(sp, BorderLayout.CENTER);
		setVisible(true);
	}

	public void setLabel(int addr, int val) {
		dump.setValueAt(String.format("%04X", val), addr / 8, addr % 8);
	}
}
