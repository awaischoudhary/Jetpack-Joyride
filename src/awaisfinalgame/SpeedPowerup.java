package awaisfinalgame;

import java.util.Timer;
import java.util.TimerTask;

public class SpeedPowerup {

	public static void applyPowerup() {
		Timer timer = new Timer();
		TimerTask speedPowerupTask = new SpeedPowerupTask(timer);
		timer.schedule(speedPowerupTask, 0, 1000);

	}
	
}


