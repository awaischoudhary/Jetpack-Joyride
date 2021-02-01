/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: Background class, where we move the background
 */
package awaisfinalgame;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Coin {

	static int numCoins = 10;
	static int coinScore = 0;
	static int coinY = 250;
	static double speed = 2.1;
	 
	double x;
	double y;
	double dx= 0;

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
	
	
	public void randomCoin(int coinIndex) {
		this.x = 1050;	
		if (coinIndex == 0) {
		    coinY =  (int)(Math.random()*370);
		}		
		this.y = coinY;
	}
	
	public void move(int coinIndex) {		
		this.dx = -this.speed;	
		this.x += this.dx;
		
		resetCoins(coinIndex);
	
		gc.drawImage(this.image, this.x, this.y);		
	}
	
	private void resetCoins(int coinIndex) {	
		if (this.x < -500) {
			randomCoin(coinIndex);
		}
	}
	
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
	
	
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}
}