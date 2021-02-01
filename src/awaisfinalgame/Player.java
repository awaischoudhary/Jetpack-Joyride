package awaisfinalgame;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public class Player {

	static int score = 0;

	int pixelPoints[][] = {{26,9},{34,8},{42,7},{43,14},{41,22},
	{38,30},{39,38},{36,44},{36,52},{28,52},{28,43},{24,38},{23,45},
	{13,45},{11,38},{13,32},{8,25},{7,14},{12,7},{17,2},{24,4}};
	
	int speed = 3;
	
	double x;
	double y;
	double vy = 0.1;	
	double ay = 0.06;

	long animationLoop = 0;

	String up = "SPACE";

	static String jumpPlayer = "images/playerJump.png";
	static String movingPlayer1 = "images/playerMoving1.png";
	static String movingPlayer2 = "images/playerMoving2.png";
	static String playerDead = "images/playerDead.png";
	static String curImageName = jumpPlayer;
	Image image = new Image(curImageName);

	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	ArrayList<String> input;

	public Player(GraphicsContext gc, Canvas gameCanvas, ArrayList<String> input) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		this.input = input;
		setPosition();
	}

	public void setPosition() {
		this.x = 40;
		this.y = 340;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void move() {

		settingGravity();

		animatingPlayer();

		this.gc.drawImage(this.image, this.x, this.y);
	}

	/* Description: Setting gravity on player
	 * Precondition: takes nothing in
	 * Postcondition: gives a gravity affect
	 */
	public void settingGravity() {
		
		// check if current player image is not dead
		if (curImageName != playerDead) {
			
			// check if the input array from mouse or key contains space bar or a left mouse click
			if (this.input.contains(this.up) || this.input.contains("PRIMARY")) {	
				
				// set velocity -2.0 every time condition is true pushing it up on the screen
				this.vy = -2.0;
			}
		}

		// use a variable to keep track of current y
		double y = this.y;
		
		// add velocity to current y position  
		this.y += this.vy;

		// set the y as current y plus velocity
		this.setY(this.getY() + vy);

		// increase the velocity by adding acceleration
		vy += ay;
		
		// checks if player hits ground, if so it sets velocity to 0
		if (this.y > 340) {
			this.vy = 0;
		}
		// check to see if player hits roof, if so set the y to current y
		if (this.y < 0) {
			this.y = y;
		}
		
	} // settingGravity method

	
	private void animatingPlayer() {
		if (curImageName != playerDead) {
			if (animationLoop > 10) {
				if (curImageName == movingPlayer2) {
					curImageName = movingPlayer1;

				} else {
					curImageName = movingPlayer2;
				}
				animationLoop = 0;
				score++;
			}

			animationLoop++;

			if (this.y < 340) {
				curImageName = jumpPlayer;
			}
		}

		settingSprite();
	}

	public void settingSprite() {
		if (curImageName == playerDead) {
			this.image = new Image(curImageName, 50, 35, false, false);
		} else if (curImageName == movingPlayer1 || curImageName == movingPlayer2) {
			this.image = new Image(curImageName, 45, 55, false, false);
		} else {
			this.image = new Image(curImageName, 55, 55, false, false);
		}
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}

	public boolean collisionLaser(Laser laser) {
		
		boolean collide = this.getBoundary().intersects(laser.getBoundary());

		return collide;
	}

	public boolean collisionMissile(Missile missile) {
		boolean collide = this.getBoundary().intersects(missile.getBoundary());
		return collide;
	}

	public boolean collisionPowerup(Powerup powerup) {
		boolean collide = this.getBoundary().intersects(powerup.getBoundary());
		return collide;
	}

	public boolean collisionCoin(Coin coin) {
		boolean collide = this.getBoundary().intersects(coin.getBoundary());
		return collide;
	}

	public static void setPlayerNormal() {
		jumpPlayer = "images/playerJump.png";
		movingPlayer1 = "images/playerMoving1.png";
		movingPlayer2 = "images/playerMoving2.png";
	}

	public boolean pixelCollision(Image image, double x, double y) {
		PixelReader imagePixels = image.getPixelReader();
	
		for (int i = 0; i < pixelPoints.length; i++) {
			int pixelsOnX = (int) (pixelPoints[i][0] + this.x - x);
			int pixelsOnY = (int) (pixelPoints[i][1] + this.y - y);
	
			try {
				if (pixelsOnX >= 0 && pixelsOnX < image.getWidth()-1 && pixelsOnY >= 0
						&& pixelsOnY < image.getHeight()-1) {

					if (imagePixels.getColor(pixelsOnX, pixelsOnY).getOpacity() == 1.0) {
						return true;
					}
				}
			} catch (Exception e) {
				return false;
			}
			
		}
		return false;
	}
}
