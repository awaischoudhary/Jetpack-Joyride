package awaisfinalgame;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Missile {

	static int numMissiles = 1;
	 
	// x and y positions
	static double x;
	double y;
	
	int speed = 10;
	
	double dx= 0;
	
	// get the banana image 
	String curMissile = "images/missile1.png";
	Image image = new Image(curMissile, 60, 30, false, false);
	
	// set canvas and graphics context
	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	public Missile(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomMissile();
	}

	public Missile(String imageName, GraphicsContext gc, Canvas gameCanvas) {
		this.curMissile = imageName;
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomMissile();
	}

	public void randomMissile() {
		this.x = 1100;
		this.y = (int)(Math.random()*(400-this.image.getHeight()));
	}
	
	public void move() {
		
		if (MissileWarning.curMissileWarning == MissileWarning.missileFinalWarning) {
			this.y = MissileWarning.y;
			this.dx = -this.speed;	
			this.x += this.dx;
		}
		
		

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


	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public String getImageName() {
		return curMissile;
	}

	public void setImageName(String imageName) {
		this.curMissile = imageName;
	}
	
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}
}

