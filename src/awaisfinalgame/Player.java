/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: Player class, all player related methods
 */
package awaisfinalgame;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public class Player {

	// game score in meters
	static int score = 0;

	// pixel points on players to get perfect collision
	int pixelPoints[][] = {{26,9},{34,8},{42,7},{43,14},{41,22},
	{38,30},{39,38},{36,44},{36,52},{28,52},{28,43},{24,38},{23,45},
	{13,45},{11,38},{13,32},{8,25},{7,14},{12,7},{17,2},{24,4}};
	
	// declaring variables
	int speed = 3;
	
	double x;
	double y;
	double vy = 0.1;	
	double ay = 0.06;

	// animation loop for animation timer 
	long animationLoop = 0;

	String up = "SPACE";

	// all images for animation
	static String jumpPlayer = "images/playerJump.png";
	static String movingPlayer1 = "images/playerMoving1.png";
	static String movingPlayer2 = "images/playerMoving2.png";
	static String playerDead = "images/playerDead.png";
	static String curImageName = jumpPlayer;
	Image image = new Image(curImageName);

	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	// array list for input
	ArrayList<String> input;

	// Player constructor
	public Player(GraphicsContext gc, Canvas gameCanvas, ArrayList<String> input) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		this.input = input;
		setPosition();
	}

	// Description: Sets the position of the player
	// Precondition: Does not take in anything
	// Postcondition: player is set at a position
	public void setPosition() {
		this.x = 40;
		this.y = 340;
	}


	// Description: moving the player
	// Precondition: Does not take in anything
	// Postcondition: sets gravity and animation on player
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
		
	} 

	// Description: Sets an animation on player
	// Precondition: Does not take in anything
	// Postcondition: player has an animation
	private void animatingPlayer() {
		
		// check to see if image is not dead
		if (curImageName != playerDead) {
			
			// then loop through animation timer for every 10
			if (animationLoop > 10) {
				// check if player image is move 2, if so set to move 1
				if (curImageName == movingPlayer2) {
					curImageName = movingPlayer1;

				} 
				// otherwise set to move 2
				else {
					curImageName = movingPlayer2;
				}
				// reset loop value
				animationLoop = 0;
				
				// increment the score with legs movements
				score++;
			}
			// increment the loop value
			animationLoop++;

			// check to see if player is not touching ground, if so set to jump
			if (this.y < 340) {
				curImageName = jumpPlayer;
			}
		}

		// call the drawing images method
		settingSprite();
	}

	// Description: Draws the different images with different pixels
	// Precondition: Does not take in anything
	// Postcondition: all images are resized and drawn equally
	public void settingSprite() {	
		// if player is dead draw it 50 by 35
		if (curImageName == playerDead) {
			this.image = new Image(curImageName, 50, 35, false, false);
		} 
		// if player is moving draw it 45 by 55
		else if (curImageName == movingPlayer1 || curImageName == movingPlayer2) {
			this.image = new Image(curImageName, 45, 55, false, false);
		} 
		// otherwise just draw it 55 by 55
		else {
			this.image = new Image(curImageName, 55, 55, false, false);
		}
	}
	
	// Description: Resetting player image back to normal
	// Precondition: Does not take in anything
	// Postcondition: all images are set to no powerup, original player
	public static void setPlayerNormal() {
		jumpPlayer = "images/playerJump.png";
		movingPlayer1 = "images/playerMoving1.png";
		movingPlayer2 = "images/playerMoving2.png";
	}
	
	// getters and setters
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

	// Description: Collision with laser rectangle box
	// Precondition: Takes in laser
	// Postcondition: returns collide if it collides with laser
	public boolean collisionLaser(Laser laser) {	
		boolean collide = this.getBoundary().intersects(laser.getBoundary());
		return collide;
	}

	// Description: Collision with Missile rectangle box
	// Precondition: Takes in missile
	// Postcondition: returns collide if it collides with Missile
	public boolean collisionMissile(Missile missile) {
		boolean collide = this.getBoundary().intersects(missile.getBoundary());
		return collide;
	}

	// Description: Collision with powerup rectangle box
	// Precondition: Takes in powerup
	// Postcondition: returns collide if it collides with powerup
	public boolean collisionPowerup(Powerup powerup) {
		boolean collide = this.getBoundary().intersects(powerup.getBoundary());
		return collide;
	}

	// Description: Collision with coin rectangle box
	// Precondition: Takes in coin
	// Postcondition: returns collide if it collides with coin
	public boolean collisionCoin(Coin coin) {
		boolean collide = this.getBoundary().intersects(coin.getBoundary());
		return collide;
	}
	
	// Description: Pixel perfect collision
	// Precondition: Takes in the image, x position, y position
	// Postcondition: returns true if pixel collision occurs
	public boolean pixelColliding(Image image, double x, double y) {
		// getting pixel reader of image
		PixelReader imagePixels = image.getPixelReader();
	
		// loop through the pixelPoints 2d array
		for (int i = 0; i < pixelPoints.length; i++) {
			// Get pixels on x and pixels on y every time it loops
			int pixelsOnX = (int) (pixelPoints[i][0] + this.x - x);
			int pixelsOnY = (int) (pixelPoints[i][1] + this.y - y);
	
			try {				
				// check to see if pixels are being checked on the right spot
				if (pixelsOnX >= 0 && pixelsOnX < image.getWidth()-1 && pixelsOnY >= 0
						&& pixelsOnY < image.getHeight()-1) {

					// check for the opacity of that point, then retrun true 
					if (imagePixels.getColor(pixelsOnX, pixelsOnY).getOpacity() == 1.0) {
						return true;
					}
				}
			} catch (Exception e) {
				// otherwise return false
				return false;
			}
			
		}
		return false;
	}
}
