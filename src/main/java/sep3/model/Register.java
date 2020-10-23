package sep3.model;

import sep3.controller.LCDDisplayable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observable;

// SEP-3 レジスタ
public class Register implements LCDDisplayable {
	private int value;                // 現在このレジスタが記憶している値
	private int preValue;            // ゲートを越えて到着したデータ（次のclock()でvalueを書き換える
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	Register() {
		value = 0;
		preValue = 0;
	}

	// 各種getter, setter
	public int getValue() {
		return value;
	}

	// セレクタによってゲートを越えてきた情報を置く
	public void setValue(int d) {
		preValue = d;
	}

	protected int getPreValue() {
		return preValue;
	} // ISRegisterにだけ公開したい

	// レジスタ値を初期設定するときだけ使う。普段は使わない
	public void setInitValue(int d) {
		value = d;
		preValue = d;
		touch();
	}

	// 表示のため、モデルが更新されたかのように扱う
	public void touch() {
		pcs.firePropertyChange("value", value, value);
	}

	// クロック投入
	public void clock() {
		if (value != preValue) {
			value = preValue;
			touch();
		}
	}

	public void addListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}
}
