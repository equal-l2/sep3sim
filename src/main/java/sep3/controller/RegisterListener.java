package sep3.controller;

import sep3.Model;
import sep3.View;
import sep3.model.Register;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RegisterListener implements PropertyChangeListener {
	private final Model model;
	private final View view;
	private final boolean when;
	private final int row;        // LCDの表示位置（行）
	private final int col;        // LCDの表示位置（列）

	public RegisterListener(Model m, View v, boolean w, int r, int c) {
		model = m;
		view = v;
		when = w;
		row = r;
		col = c;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// モデル上でレジスタ値が変更されたら、該当するLEDアレイの表示を変更する。
		// ただし、表示切り替えスイッチがwhenの時に限る。
		Register r = (Register) evt.getSource();
		if (model.getDispSW().isOn() == when) {
			view.getLCD().getLED7segment(row, col).setValue(r.getValue());
			//System.out.println("show(" + row + ", " + col + ") as " + String.format("%1$04x", (r.getValue() & 0xFFFF)).toUpperCase());
		}
	}
}
