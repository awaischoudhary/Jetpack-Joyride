package awaisfinalgame;

import java.util.Timer;
import java.util.TimerTask;

public class InvisiblePowerupTask extends TimerTask {
	
		// seconds powered up
		public static int numSecondPoweredUp = 0;

	    Timer timer;

		static String stillInvisibleImage = "images/playerStillInvisible.png";
		static String movingPlayerInvisible = "images/playerMovingInvisible1.png";
		static String movingPlayerInvisible2 = "images/playerMovingInvisible2.png";
		
		public InvisiblePowerupTask(Timer t) {
			this.timer = t;
	    
		}
	    
		public void run() {

			
			if (numSecondPoweredUp == 0) {
				AwaisFinalGameController.invisible = true;
				Player.stillPlayerImage = stillInvisibleImage;
				Player.movingPlayerImage = movingPlayerInvisible;
				Player.movingPlayerImage2 = movingPlayerInvisible2;

				numSecondPoweredUp += 1;

			} 

			else if (numSecondPoweredUp == 10) {
			
				AwaisFinalGameController.invisible = false;
				System.out.println(AwaisFinalGameController.invisible);
				
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
