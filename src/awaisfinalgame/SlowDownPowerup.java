/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: Slow down powerup timer calling on SlowDownPowerupTask
 */
package awaisfinalgame;

import java.util.Timer;
import java.util.TimerTask;

public class SlowDownPowerup {

	public static void applyPowerup() {

		// Description: Timer to apply the Powerup
		// Precondition: Does not take in anything
		// Postcondition: Timer is set calling on SlowDownPowerupTask
		Timer timer = new Timer();
		TimerTask speedPowerupTask = new SlowDownPowerupTask(timer);
		timer.schedule(speedPowerupTask, 0, 1000);
	}

}
