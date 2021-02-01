/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: All the functions and tasks of the invisible powerup
 */
package awaisfinalgame;

import java.util.Timer;
import java.util.TimerTask;

public class InvisiblePowerupTask extends TimerTask {

	// declaring static variables, powerup images
	static String stillInvisibleImage = "images/playerJumpInvisible.png";
	static String movingPlayerInvisible = "images/playerMovingInvisible1.png";
	static String movingPlayerInvisible2 = "images/playerMovingInvisible2.png";

	Timer timer;

	// variable to loop through 10 seconds and apply powerup then stop
	int secondsPowerup = 0;
	
	// boolean to see if player is invisible
	static boolean invisible = false;
	
	// InvisiblePowerupTask constructor
	public InvisiblePowerupTask(Timer t) {
		this.timer = t;
	}
	
	// Description: Logic for timer to run through 
	// Precondition: Does not take in anything
	// Postcondition: Timer keeps running through 
	public void run() {

		// check if the seconds are at 0 (start)
		if (secondsPowerup == 0) {
			
			// if so then set the player to powerup images
			setPlayerGrey();
			invisible = true;
			
			// increment on secondsPowerup
			secondsPowerup += 1;
		}

		// check if the seconds are at 10 (end)
		else if (secondsPowerup == 10) {
			
			// reset player image
			Player.setPlayerNormal();
			invisible = false;

			// set secondsPowerup to 0 and end timer
			secondsPowerup = 0;
			timer.cancel();
		}
		// otherwise just increase secondsPowerup
		else {
			secondsPowerup++;

		}

	}
	
	// Description: Setting the player image to invisible powerup
	// Precondition: Does not take in anything
	// Postcondition: Player is a different image
	public void setPlayerGrey() {
		Player.jumpPlayer = stillInvisibleImage;
		Player.movingPlayer1 = movingPlayerInvisible;
		Player.movingPlayer2 = movingPlayerInvisible2;
	}
}
