/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: All the functions and tasks of the slow down powerup
 */
package awaisfinalgame;

import java.util.Timer;
import java.util.TimerTask;

public class SlowDownPowerupTask extends TimerTask {

	// variable to loop through 10 seconds and apply powerup then stop
	int secondsPowerup = 0;

	// change of speed variable on all objects
	double changingSpeed = 0.6;

	// declaring static variables, powerup images
	static String stillSpeedImage = "images/playerJumpSpeed.png";
	static String movingPlayerSpeed = "images/playerMovingSpeed1.png";
	static String movingPlayerSpeed2 = "images/playerMovingSpeed2.png";

	Timer timer;

	// SlowDownPowerupTask constructor
	public SlowDownPowerupTask(Timer t) {
		this.timer = t;

	}

	// Description: Logic for timer to run through 
	// Precondition: Does not take in anything
	// Postcondition: Timer keeps running through 
	public void run() {

		// check if the seconds are at 0 (start)
		if (secondsPowerup == 0) {		
			// slow down the speed of all objects
			slowDownSpeed();
			
			// set player to powerup player images
			setPlayerBlue();
			
			// increment the secondsPowerup
			secondsPowerup += 1;
		}
		// check if the seconds are at 10 (end)
		else if (secondsPowerup == 10) {
			// reset the speed back to normal
			resetSpeed();
			
			// set player images back to normal
			Player.setPlayerNormal();
			
			// set secondsPowerup to 0, and cancel timer
			secondsPowerup = 0;
			timer.cancel();
		} 
		// otherwise increment the secondsPowerup
		else {
			secondsPowerup++;

		}
	}

	// Description: Sets the player to blue powerup images
	// Precondition: Does not take in anything
	// Postcondition: Player images change
	public void setPlayerBlue() {
		Player.jumpPlayer = stillSpeedImage;
		Player.movingPlayer1 = movingPlayerSpeed;
		Player.movingPlayer2 = movingPlayerSpeed2;
	}

	// Description: Slows down all speeds of the game
	// Precondition: Does not take in anything
	// Postcondition: speeds are lowered
	public void slowDownSpeed() {
		Coin.speed -= changingSpeed;
		Missile.speed -= changingSpeed;
		Laser.speed -= changingSpeed;
	}


	// Description: resets all speeds of the game
	// Precondition: Does not take in anything
	// Postcondition: speeds are reset
	public void resetSpeed() {
		Coin.speed += changingSpeed;
		Missile.speed += changingSpeed;
		Laser.speed += changingSpeed;
	}
}
