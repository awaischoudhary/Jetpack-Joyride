package awaisfinalgame;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Laser {

	 static int numLasers = 3;
	 
	// x and y positions
	double x;
	double y;
	
	// get the banana image 
	String imageName = "images/laser.png";
	Image image = new Image(imageName, 50, 140, false, false);
	
	// set canvas and graphics context
	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	// banana constructors
	public Laser(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomLaser();
	}

	// banana constructors
	public Laser(String imageName, GraphicsContext gc, Canvas gameCanvas) {
		this.imageName = imageName;
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomLaser();
	}
	
	/* Description: Positions banana at random spot
	 * Precondition: takes nothing in
	 * Postcondition: changes banana position
	 */
	public void randomLaser() {
		this.x = (int)(Math.random()*(this.gameCanvas.getWidth()-this.image.getWidth()));
		this.y = (int)(Math.random()*(340-this.image.getHeight()));
	}
	
	// getters and setters
	public void drawLaser() {
		gc.drawImage(this.image, this.x, this.y);
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

	public void setY(double y) {
		this.y = y;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}
}
