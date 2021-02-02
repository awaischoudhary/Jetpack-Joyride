/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: Laser class, all laser related methods
 */
package awaisfinalgame;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Laser {

	// declaring variables
	static int numLasers = 3;
	static double speed = 2.1;

	double x;
	double y;
	double dx = 0;
	
	String imageName = "images/laser.png";
	Image image = new Image(imageName, 45, 140, false, false);

	// set canvas and graphics context
	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	// Laser constructor
	public Laser(GraphicsContext gc, Canvas gameCanvas, int laserIndex) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomLaser(laserIndex);
	}

	// Description: Sets the laser at a random spot
	// Precondition: Takes in the laser index
	// Postcondition: Laser is set at a random y
	public void randomLaser(int laserIndex) {
		this.x = 1000 + 300 * laserIndex;
		this.y = (int) (Math.random() * (400 - this.image.getHeight()));
	}

	// Description: moving the laser
	// Precondition: does not take in anything
	// Postcondition: moves the lasers
	public void move() {
		
		// dx is equal to the speed value in negative
		this.dx = -this.speed;
		
		// dx changes x position
		this.x += this.dx;

		// calling the reset lasers method
		resetLasers();
		
		gc.drawImage(this.image, this.x, this.y);
	}
	
	// Description: resetting the lasers
	// Precondition: does not take in anything
	// Postcondition: resets the lasers once it goes off
	public void resetLasers() {
		if (this.x < 0 - image.getWidth()) {
			randomLaser(0);
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
	
	// Description: Getting the rectangular boundary of image
	// Precondition: Does not take in anything
	// Postcondition: return the rectangle boundary
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}
	
	

}
