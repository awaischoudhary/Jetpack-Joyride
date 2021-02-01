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
	
	public Powerup(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		placingPowerup();
	}

	public void placingPowerup() {
		
		this.x = 1100;
		this.y = 100;
	}

	public void move() {
		this.dx = -this.speed;	
		this.x += this.dx;
		
		resettingPowerup();
		
		gc.drawImage(this.image, this.x, this.y);

	}
	
	public void resettingPowerup() {
		
		if (Player.score % 150 == 0 && Player.score > 1) {
			this.speed = 2;
			this.y = 100;
		}
			
		if (this.x < -this.image.getWidth()) {
			this.x = 1100;
			this.speed = 0;
		}
	}
	
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

	public void setY(double y) {
		this.y = y;
	}
	
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}
}