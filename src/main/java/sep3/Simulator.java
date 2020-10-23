package sep3;

public class Simulator {
	Model model;
	View view;
	Controller ctl;
	DebugView debug;

	Simulator() {
		model = new Model();                    // SEP-3の機能モデル生成
		view = new View();                        // SEP-3のUI生成
		debug = new DebugView();
		ctl = new Controller(model, view, debug);    // 両者をつなぐ
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Simulator sep3 = new Simulator();
	}
}
