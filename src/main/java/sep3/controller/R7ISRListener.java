package sep3.controller;

import sep3.Model;
import sep3.View;
import sep3.model.Register;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

public class R7ISRListener implements PropertyChangeListener {
	private final Model model;
	private final View view;
	private final boolean when;

	public R7ISRListener(Model m, View v, boolean w) {
		model = m;
		view = v;
		when = w;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// モデル上でレジスタ値が変更されたら、該当するLEDアレイの表示を変更する。
		// ただし、表示切り替えスイッチがwhenの時に限る。
		Register r = (Register)evt.getSource();
		if (model.getDispSW().isOn() == when) {
			view.getR7ISR().setValue(r.getValue());
		}
	}
}
