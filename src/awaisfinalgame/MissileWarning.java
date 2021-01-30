package awaisfinalgame;

import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class MissileWarning {
	
	long iterationsWarning = 0;
	long iterationsPlacing = 0;

	// x and y positions
	double x;
	double y;
	double dy= 0;
	
	// get the banana image 
	static String missileWarning = "images/missileWarning.png";
	static String missileFinalWarning = "images/missileFinalWarning.png";
	static String curMissileWarning = missileWarning;
	Image image = new Image(curMissileWarning, 40, 40, false, false);
	
	public AudioClip warningSound = new AudioClip(Paths.get("src/sounds/MissileWarning.wav").toUri().toString());

	
	// set canvas and graphics context
	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	public MissileWarning(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomMissileWarning();
	}
	
	public void randomMissileWarning() {
		this.x = 950;
		this.y = (int)(Math.random()*(400-this.image.getHeight()));
	}
	
	public void move(Player player, Missile missile) {
		if (curMissileWarning == missileWarning) {
			this.y = player.getY();
		}
		timingMissileWarning(missile);
		
		gc.drawImage(this.image, this.x, this.y);
		
	}
	
	public void timingMissileWarning(Missile missile) {
	
		if (iterationsWarning > 100) {
			curMissileWarning = missileFinalWarning;
			warningSound.play();
			iterationsWarning = 0;
		}
		iterationsWarning++;
		
		if (curMissileWarning == missileFinalWarning) {
			this.image = new Image(curMissileWarning, 45, 45, false, false);
			if (missile.getX() < 1000) {
				this.x=1000;
				iterationsWarning = 0;
			}
			if (missile.getX() < -500) {
				curMissileWarning = missileWarning;
				missile.setX(1100);
				this.x=950;
				this.image = new Image(curMissileWarning, 40, 40, false, false);
			}
		}	
		
		
	}
	
	public double getY() {
		return y;
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}
}

