package sep3.model;

import java.util.Observable;

import sep3.controller.LCDDisplayable;

// SEP-3 レジスタ
public class Register extends Observable implements LCDDisplayable {
	private int value;				// 現在このレジスタが記憶している値
	private int preValue;			// ゲートを越えて到着したデータ（次のclock()でvalueを書き換える

	// 各種getter, setter
	public int  getValue()					{ return value; }
	protected int getPreValue()				{ return preValue; } // ISRegisterにだけ公開したい

	Register() { value = 0; preValue = 0; }

	// レジスタ値を初期設定するときだけ使う。普段は使わない
	public void setInitValue(int d) { value = d; preValue = d; touch(); }
	// セレクタによってゲートを越えてきた情報を置く
	public void setValue(int d)	{ preValue = d; }
	// 表示のため、モデルが更新されたかのように扱う
	public void touch()				{ setChanged(); notifyObservers(); }
	// クロック投入
	public void clock()				{ if (value != preValue) { value = preValue; touch(); } }
}
