package sep3;

public class Simulator {
	Model model;
	View  view;
	Controller ctl;

	Simulator() {
		model = new Model();					// SEP-3の機能モデル生成
		view  = new View();						// SEP-3のUI生成
		ctl   = new Controller(model, view);	// 両者をつなぐ
	}
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Simulator sep3 = new Simulator();
	}
}
