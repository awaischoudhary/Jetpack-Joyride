package awaisfinalgame;


import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MissileWarning {

	static int numWarning = 1;
	 
	
	double dy= 0;
	
	long iterationsWarning = 0;
	long iterationsPlacing = 0;

	// x and y positions
	double x;
	static double y;	
	
	// get the banana image 
	static String missileWarning = "images/missileWarning.png";
	static String missileFinalWarning = "images/missileFinalWarning.png";
	static String curMissileWarning = missileWarning;
	Image image = new Image(curMissileWarning, 40, 40, false, false);
	
	// set canvas and graphics context
	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	public MissileWarning(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomMissileWarning();
	}

	public MissileWarning(String imageName, GraphicsContext gc, Canvas gameCanvas) {
		this.curMissileWarning = imageName;
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomMissileWarning();
	}

	public void randomMissileWarning() {
		this.x = 950;
		this.y = (int)(Math.random()*(400-this.image.getHeight()));
	}
	
	public void move() {
		if (curMissileWarning == missileWarning) {
			this.y = Player.y;
		}
		timingMissileWarning();
		
		gc.drawImage(this.image, this.x, this.y);
		
	}
	
	public void timingMissileWarning() {
	
		if (iterationsWarning > 100) {
			curMissileWarning = missileFinalWarning;
			
			iterationsWarning = 0;
		}
		iterationsWarning++;
		if (curMissileWarning == missileFinalWarning) {
			this.image = new Image(curMissileWarning, 45, 45, false, false);
			if (Missile.x < 1000) {
				this.x=1000;
				iterationsWarning = 0;
			}
			if (Missile.x < -500) {
				curMissileWarning = missileWarning;
				Missile.x = 1100;
				this.x=950;
				this.image = new Image(curMissileWarning, 40, 40, false, false);
			}
		}	
		
		
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

	public double getDy() {
		return dy;
	}

	public void setDx(double dy) {
		this.dy = dy;
	}


	public String getImageName() {
		return curMissileWarning;
	}

	public void setImageName(String imageName) {
		this.curMissileWarning = imageName;
	}
	
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}
}

