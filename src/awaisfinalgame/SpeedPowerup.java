package awaisfinalgame;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class SpeedPowerup {
	
	
	public void applyPowerup(ArrayList<Laser> laserList, ArrayList<Missile> missileList) {
		Timer timer = new Timer();
		TimerTask speedPowerupTask = new SpeedPowerupTask(laserList, missileList, timer);
		timer.schedule(speedPowerupTask, 0, 1000);

	}
	
}


