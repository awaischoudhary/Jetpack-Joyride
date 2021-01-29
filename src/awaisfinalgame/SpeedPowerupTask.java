package awaisfinalgame;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SpeedPowerupTask extends TimerTask{

	// seconds powered up
	public static int numSecondPoweredUp = 0;
	

    Timer timer;
    
    ArrayList<Laser> lasers;
    ArrayList<Missile> missiles;


	// powerup timer constructor
	public SpeedPowerupTask(ArrayList<Laser> laserList, ArrayList<Missile> missileList, Timer t) {
        this.timer = t;
        this.lasers = laserList;
        this.missiles = missileList;
	}
	
// 	public SpeedPowerupTask(ArrayList<Laser> laserList, ArrayList<Missile> missileList, Player player, Timer t) {
//         this.timer = t;
//         this.lasers = laserList;
//         this.missiles = missileList;
// 	}

	/* Description: speeds the monkey up until porweup second is 6 
	 * Precondition: takes nothing in
	 * Postcondition: monkey speed up for 6 seconds
	 */
	public void run() {
		
		// if the numSecondPowerup is 0 add by 1 and call speed method
		if (numSecondPoweredUp == 0) {
            // monkey.speedPowerup();
            for (int i = 0; i < lasers.size(); i++) {
                Laser laser = lasers.get(i);
                //laser.slowDown(); // laser.deactivate()
            }
            for (int i = 0; i < missiles.size(); i++) {
                Missile missile = missiles.get(i);
                //missile.slowDown();
            }
            // player.makeBlue();
			numSecondPoweredUp += 1;
		} 
		// if the numSecondPowerup is 6 set it to 0 again
		// speed down monkey, and cancel timer
		else if (numSecondPoweredUp == 6) {
			for (int i = 0; i < lasers.size(); i++) {
                Laser laser = lasers.get(i);
                //laser.resetSpeed();
            }
            for (int i = 0; i < missiles.size(); i++) {
                Missile missile = missiles.get(i);
                //missile.resetSpeed();
            }
            // player.unBlue();
			numSecondPoweredUp = 0;
			timer.cancel();
		} 
		// otherwise increase second powerup
		else {
			numSecondPoweredUp++;
			
		}

	}
}