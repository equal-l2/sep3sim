package sep3.view;

// LEDのひとつ分
@SuppressWarnings("serial")

public class RedLED extends LED {
	public RedLED() {
		super("LED2red.png", "LED2off.png");	// 最初に作るときは消灯状態
	}
}