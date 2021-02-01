package awaisfinalgame;

import java.util.Timer;
import java.util.TimerTask;

public class SlowDownPowerup {

	public static void applyPowerup() {
			Timer timer = new Timer();
			TimerTask speedPowerupTask = new SlowDownPowerupTask(timer);
			timer.schedule(speedPowerupTask, 0, 1000);
	}

}
