package awaisfinalgame;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SpeedPowerupTask extends TimerTask{

	// seconds powered up
	public static int numSecondPoweredUp = 0;
	
	static String stillSpeedImage = "images/playerStillSpeed.png";
	static String movingPlayerSpeed = "images/playerMovingSpeed1.png";
	static String movingPlayerSpeed2 = "images/playerMovingSpeed2.png";
	

    Timer timer;

	public SpeedPowerupTask(Timer t) {
		this.timer = t;
    
	}
    
	public void run() {
		
		
		double changingSpeed = 0.6;
		
		
		if (numSecondPoweredUp == 0) {
 			Coin.speed -= changingSpeed;
			Missile.speed -= changingSpeed;
			Laser.speed -= changingSpeed;
            // player.makeBlue();
			Player.stillPlayerImage = stillSpeedImage;
			Player.movingPlayerImage = movingPlayerSpeed;
			Player.movingPlayerImage2 = movingPlayerSpeed2;
			numSecondPoweredUp += 1;
		} 

		else if (numSecondPoweredUp == 10) {
			Missile.speed += changingSpeed;
			Laser.speed += changingSpeed;
			Coin.speed += changingSpeed;
			Player.stillPlayerImage ="images/playerStill.png";
			Player.movingPlayerImage = "images/playerMoving1.png";
			Player.movingPlayerImage2 = "images/playerMoving2.png";
			
			numSecondPoweredUp = 0;
			timer.cancel();
		} 
		else {
			numSecondPoweredUp++;
			
		}

	}
}