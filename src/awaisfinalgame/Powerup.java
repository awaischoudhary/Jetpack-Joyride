/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: Powerup class, all powerup related methods
 */
package awaisfinalgame;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Powerup {

	// x and y positions
	double x;
	double y;
	double dx = 0;
	static double speed = 0;

	String imageName = "images/powerup.png";
	Image image = new Image(imageName, 60, 60, false, false);

	// set canvas and graphics context
	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;
	
	// Powerup constructor
	public Powerup(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		placingPowerup();
	}

	// Description: Sets the position of the powerup
	// Precondition: Does not take in anything
	// Postcondition: powerup is set at a position
	public void placingPowerup() {	
		this.x = 1100;
		this.y = 100;
	}

	// Description: move the powerup
	// Precondition: Does not take in anything
	// Postcondition: powerup is moved to left
	public void move() {
		// dx is changed by making it equal to negative speed
		this.dx = -this.speed;	
		
		// x position changes by adding the dx
		this.x += this.dx;
		
		// reset the powerup method
		resettingPowerup();
		
		gc.drawImage(this.image, this.x, this.y);
	}
	
	// Description: Reset the powerup
	// Precondition: Does not take in anything
	// Postcondition: resets the powerup once it passes
	public void resettingPowerup() {
		
		// for every time the score increases by 150
		if (Player.score % 150 == 0 && Player.score > 1) {
			// move the powerup by setting the speed and set y position
			this.speed = 2;
			this.y = 100;
		}
		
		// check to see if powerup when off the screen 
		if (this.x < -this.image.getWidth()) {			
			// reset position and set speed 0
			this.x = 1100;
			this.speed = 0;
		}
	}
	
	// getters and setters
	public Image getImage() {
		return image;
	}
	
	public double getX() {
		return x;
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