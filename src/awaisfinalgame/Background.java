/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: Background class, where we move the background
 */
package awaisfinalgame;

public class Background {

	// declaring variables
	static int backgroundX1 = 0;
	static int backgroundX2= 1000;
	static int speedingGame = 0;
	static int bgSpeed = 2;
		
	// Description: Moves the background
	// Precondition: Does not take in anything
	// Postcondition: Backgrounds are moving
	public static void movingBackground(){
		
		// call speedingGame method
		speedingUpGame();
		
		// if backgrounds moves fully off the screen, set position back at 1000
		if (backgroundX1 <= -1000) {
			backgroundX1 = 1000;
		}
		if (backgroundX2 <= -1000) {
			backgroundX2 = 1000;
		}
		
		// subtract a value of 2 to move both backgrounds
		backgroundX1-=bgSpeed;
		backgroundX2-=bgSpeed;
		
	} // movingBackground method
	
	
	// Description: Speeds the game up as it goes on
	// Precondition: Does not take in anything
	// Postcondition: game gets harder 
	public static void speedingUpGame() {
		
		// speeding game happens after every 10 animation timer
		// check if grader than 10, then speed up
		if (speedingGame > 10) {
			
			// every time the score increases by 20 increase the objects speeds
			if (Player.score % 20 == 0 && Player.score > 1) {
				Laser.speed += 0.1;
				Coin.speed += 0.1;
				Missile.speed += 0.1;
			}
			speedingGame=0;
		}
		speedingGame++;		
	}
} // speedingUpGame method
