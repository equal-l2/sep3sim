package sep3.controller;

import java.util.Observable;
import java.util.Observer;
import sep3.*;
import sep3.model.*;

public class R7ISRObserver implements Observer {
	private Model model;
	private View  view;
	private boolean when;

	public R7ISRObserver(Model m, View v, boolean w) {
		model = m; view = v; when = w;
	}
	public void update(Observable o, Object arg) {
		//System.out.println("enter Observer of Register #" + rn);
		// モデル上でレジスタ値が変更されたら、該当するLEDアレイの表示を変更する。
		// ただし、表示切り替えスイッチがwhenの時に限る。
		Register r = (Register) o;
		if (model.getDispSW().isOn() == when) {
			view.getR7ISR().setValue(r.getValue());
		}
	}
}
