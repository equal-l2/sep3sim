package sep3.controller;

import sep3.DebugView;
import sep3.Model;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MemoryListener implements PropertyChangeListener {
	Model model;
	DebugView debug;

	public MemoryListener(Model m, DebugView d) {
		model = m;
		debug = d;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		var mem = model.getMemory();
		if (evt instanceof IndexedPropertyChangeEvent) {
			var idx = ((IndexedPropertyChangeEvent) evt).getIndex();
			debug.getMemoryDump().setLabel(idx, mem.getValue(idx));
		} else {
			for (int i = 0; i < 0x10000; i++) {
				debug.getMemoryDump().setLabel(i, mem.getValue(i));
			}
		}
	}
}
