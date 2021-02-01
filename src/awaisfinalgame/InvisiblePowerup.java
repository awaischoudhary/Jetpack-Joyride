/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: Invisible Powerup timer calling on InvisiblePowerupTask
 */
package awaisfinalgame;

import java.util.Timer;
import java.util.TimerTask;

public class InvisiblePowerup {
	
	// Description: Timer to apply the Powerup
	// Precondition: Does not take in anything
	// Postcondition: Timer is set calling on InvisiblePowerupTask
	public static void applyPowerup() {
		Timer timer = new Timer();
		TimerTask invisiblePowerupTask = new InvisiblePowerupTask(timer);
		timer.schedule(invisiblePowerupTask, 0, 1000);
	}
}
