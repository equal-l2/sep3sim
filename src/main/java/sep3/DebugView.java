package sep3;

import sep3.debug_view.MemoryDump;

import javax.swing.*;

public class DebugView extends JFrame {
	MemoryDump memDump = new MemoryDump();

	public DebugView() {
		add(memDump);
		pack();
		setVisible(true);
	}

	public MemoryDump getMemoryDump() {
		return memDump;
	}
}
