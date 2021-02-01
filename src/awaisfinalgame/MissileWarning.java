/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: Missile Warning class, all related missile warning methods
 */
package awaisfinalgame;

import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class MissileWarning {
	
	// loop check variable in animation timer
	long iterationsWarning = 0;

	// x and y positions
	static double x;
	static double y;
	double dy= 0;
	
	// get the banana image 
	static String missileWarning = "images/missileWarning.png";
	static String missileFinalWarning = "images/missileFinalWarning.png";
	static String curMissileWarning = missileWarning;
	Image image = new Image(curMissileWarning, 40, 40, false, false);
	
	// warning sounds
	public AudioClip warningSound = new AudioClip(Paths.get("src/sounds/MissileWarning.wav").toUri().toString());

	// set canvas and graphics context
	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	// Missile Warning constructor
	public MissileWarning(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomMissileWarning();
	}
	
	// Description: Sets the missile warning at a random spot
	// Precondition: Does not take in anything
	// Postcondition: Missile Warning is set at a random y
	public void randomMissileWarning() {
		this.x = 950+100;
		this.y = (int)(Math.random()*(400-this.image.getHeight()));
	}
	
	// Description: moves the missile Warning
	// Precondition: Takes in the player and missile 
	// Postcondition: Missile Warning is moved 
	public void move(Player player, Missile missile) {
		
		// check to see if missileWarning is first warning, 
		//if so set warning y to player y
		if (curMissileWarning == missileWarning) {
			this.y = player.getY();
		}
		// keep x at 950
		this.x = 950;
		
		// call timingMissileWarning method
		timingMissileWarning(missile);
		
		gc.drawImage(this.image, this.x, this.y);
		
	}
	
	// Description: times the missile warning
	// Precondition: Takes in the missile 
	// Postcondition: Missile Warnings are times
	public void timingMissileWarning(Missile missile) {
	
		// check to see after every 100 loops in animation time
		if (iterationsWarning > 100) {		
			// set the first warning to second warning, play sounds, and reset loop value
			curMissileWarning = missileFinalWarning;
			warningSound.play();
			iterationsWarning = 0;
		}
		// increment the loop value 
		iterationsWarning++;
		
		// check to see if the missile warning is the final warning
		if (curMissileWarning == missileFinalWarning) {
			
			this.image = new Image(curMissileWarning, 45, 45, false, false);
			
			//check to see if missile x is less than 1000
			if (missile.getX() < 1000) {
				// set warning position to 1000 to push of screen and bring missile on
				this.x=1000;		
				// set loop to 0
				iterationsWarning = 0;
			}
			
			// check to re-spawn the missile back to the right
			if (missile.getX() < -500) {
				// set the warning back to first warning, reset missile position
				curMissileWarning = missileWarning;
				missile.setX(1100);
				this.x=950;
				this.image = new Image(curMissileWarning, 40, 40, false, false);
			}
		}		
	}

	// getters and setters
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	// Description: Getting the rectangular boundary of image
	// Precondition: Does not take in anything
	// Postcondition: return the rectangle boundary
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}
}

