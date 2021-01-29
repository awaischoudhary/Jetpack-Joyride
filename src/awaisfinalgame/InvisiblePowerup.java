package awaisfinalgame;

import java.util.Timer;
import java.util.TimerTask;

public class InvisiblePowerup {

	public static void applyPowerup() {
		Timer timer = new Timer();
		TimerTask invisiblePowerupTask = new InvisiblePowerupTask(timer);
		timer.schedule(invisiblePowerupTask, 0, 1000);
	}
}
