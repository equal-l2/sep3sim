package sep3.controller;

import sep3.Model;
import sep3.View;
import sep3.model.Bus;

import java.util.Observable;
import java.util.Observer;

public class BusObserver implements Observer {
	private final Model model;
	private final View view;
	private final boolean when;
	private final int row;        // LCDの表示位置（行）
	private final int col;        // LCDの表示位置（列）

	public BusObserver(Model m, View v, boolean w, int r, int c) {
		model = m;
		view = v;
		when = w;
		row = r;
		col = c;
	}

	public void update(Observable o, Object arg) {
		// バス上を流れるデータの値が変わったら、LEDアレイの該当部分に新しいデータ（値）を表示する
		// ただし、表示切り替えスイッチがwhenの時に限る。
		Bus b = (Bus) o;
		if (model.getDispSW().isOn() == when) {
			view.getLCD().getLED7segment(row, col).setValue(b.getValue());
		}
	}
}
