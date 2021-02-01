/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: Coin class, all coin related methods 
 */
package awaisfinalgame;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Coin {

	// declaring static variables
	static int numCoins = 10;
	static int coinScore = 0;
	static int coinY = 250;
	static double speed = 2.1;
	 
	// x, y and dx positions
	double x;
	double y;
	double dx= 0;

	// getting the image
	String imageName = "images/coin.png";
	Image image = new Image(imageName, 30, 30, false, false);
		
	// set canvas and graphics context
	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	// coin constructor
	public Coin(GraphicsContext gc,  Canvas gameCanvas, int coinIndex) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		this.y = coinY;
		this.x = 1000+25*coinIndex;
	}
	
	// Description: resetting the coins at a random spot
	// Precondition: Takes in the coinIndex
	// Postcondition: Coins are set at a random y on screen
	public void randomCoin(int coinIndex) {
		
		this.x = 1050;	
		
		// check when coinIndex is 0, if so set all at random y
		if (coinIndex == 0) {
		    coinY =  (int)(Math.random()*370);
		}		
		this.y = coinY;
	}
	
	// Description: moves the coins
	// Precondition: Takes in the coinIndex
	// Postcondition: Coins move to the left at a negative speed
	public void move(int coinIndex) {
		
		// dx is set to speed value in negative
		this.dx = -this.speed;	
		
		// change the x by dx value
		this.x += this.dx;
		
		// reset all coins 
		resetCoins(coinIndex);
	
		gc.drawImage(this.image, this.x, this.y);		
	}
	
	// Description: Resets all coins when coins go off the screen
	// Precondition: Takes in the coinIndex
	// Postcondition: All coins collided or not are reset
	private void resetCoins(int coinIndex) {			
		// check if coins go off the screen, then reset
		if (this.x < -500) {
			randomCoin(coinIndex);
		}
	}
	
	// Description: moves coin off screen if collided
	// Precondition: Does not take in anything
	// Postcondition: coin collided is off screen
	public void collisionRespawning() {
		this.y = 600;
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