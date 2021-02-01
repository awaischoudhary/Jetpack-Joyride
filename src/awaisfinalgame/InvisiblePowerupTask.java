package awaisfinalgame;

import java.util.Timer;
import java.util.TimerTask;

public class InvisiblePowerupTask extends TimerTask {

	static String stillInvisibleImage = "images/playerJumpInvisible.png";
	static String movingPlayerInvisible = "images/playerMovingInvisible1.png";
	static String movingPlayerInvisible2 = "images/playerMovingInvisible2.png";

	Timer timer;

	int secondsPowerup = 0;
	static boolean invisible = false;
	
	public InvisiblePowerupTask(Timer t) {
		this.timer = t;
	}

	public void run() {

		if (secondsPowerup == 0) {
			setPlayerGrey();
			invisible = true;
			secondsPowerup += 1;
		}

		else if (secondsPowerup == 10) {
			Player.setPlayerNormal();
			invisible = false;

			secondsPowerup = 0;
			timer.cancel();
		} else {
			secondsPowerup++;

		}

	}
	
	public void setPlayerGrey() {
		Player.jumpPlayer = stillInvisibleImage;
		Player.movingPlayer1 = movingPlayerInvisible;
		Player.movingPlayer2 = movingPlayerInvisible2;
	}
}
