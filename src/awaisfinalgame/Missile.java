/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: Missile class, all related missile methods
 */
package awaisfinalgame;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Missile {
	
	// missile speed
	static double speed = 0;

	// x and y positions
	double x;
	double y;
	double dx= 0;
	
	String curMissile = "images/missile1.png";
	Image image = new Image(curMissile, 60, 30, false, false);
		
	// set canvas and graphics context
	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	// Missile constructor
	public Missile(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomMissile();
	}
	
	// Description: Sets the missile at a random spot
	// Precondition: Does not take in anything
	// Postcondition: Missile is set at a random y
	public void randomMissile() {
		this.x = 1100;
		this.y = (int)(Math.random()*(400-this.image.getHeight()));
	}
	
	// Description: moving the laser
	// Precondition: takes in missileWarning
	// Postcondition: moves the missile
	public void move(MissileWarning missileWarning) {
		
		// check if missileWarning has reached the final warning, if so move missile with speed
		if (MissileWarning.curMissileWarning == MissileWarning.missileFinalWarning) {
			this.speed = 10;
			this.y = missileWarning.getY();
			this.dx = -this.speed;	
			this.x += this.dx;
		}	
		gc.drawImage(this.image, this.x, this.y);	
	}
	
	// getters and setters
	public Image getImage() {
		return image;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	// Description: Getting the rectangular boundary of image
	// Precondition: Does not take in anything
	// Postcondition: return the rectangle boundary
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}
}

