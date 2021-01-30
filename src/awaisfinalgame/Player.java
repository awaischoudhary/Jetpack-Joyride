package awaisfinalgame;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {

	static int score = 0;

	int speed = 3;

	double x = 100;
	double y = 100;
	double vy = 0.1;
	double ay = 0.06;

	long animationLoop = 0;

	String up = "SPACE";

	static String stillPlayerImage = "images/playerStill.png";
	static String movingPlayerImage1 = "images/playerMoving1.png";
	static String movingPlayerImage2 = "images/playerMoving2.png";
	static String playerDead = "images/playerDead.png";
	static String curImageName = stillPlayerImage;
	Image image = new Image(curImageName, 60, 50, false, false);

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

	public void settingGravity() {

		if (curImageName != playerDead) {
			if (this.input.contains(this.up) || this.input.contains("PRIMARY")) {
				this.vy = -2.0;
			}
		}

		double y = this.y;
		this.y += this.vy;

		this.setY(this.getY() + vy);

		vy += ay;

		if (this.y > 340) {
			this.vy = 0;
		}
		if (this.y < 0) {
			this.y = y;
		}
	}

	private void animatingPlayer() {
		if (curImageName != playerDead) {
			if (animationLoop > 10) {
				if (curImageName == movingPlayerImage2) {
					curImageName = movingPlayerImage1;

				} else {
					curImageName = movingPlayerImage2;
				}
				animationLoop = 0;
				score++;
			}

			animationLoop++;

			if (this.y < 340) {
				curImageName = stillPlayerImage;
			}
		}

		settingSprite();
	}

	public void settingSprite() {
		if (curImageName == playerDead) {
			this.image = new Image(curImageName, 50, 35, false, false);
		} else if (curImageName == movingPlayerImage1 || curImageName == movingPlayerImage2) {
			this.image = new Image(curImageName, 45, 55, false, false);
		} else {
			this.image = new Image(curImageName, 55, 55, false, false);
		}
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}

	public boolean collisionLaser(Laser laser) {
		int pixelsIn = 20;
		int pixelsOut = 30;
		int pixelsOver = 10;
		int pixelsUnder = 10;

		boolean collide = this.x + image.getWidth() > laser.x + pixelsIn
				&& this.x + image.getWidth() < laser.x + laser.image.getWidth() + pixelsOut
				&& this.y + image.getHeight() > laser.y + pixelsOver
				&& this.y < laser.y + laser.image.getHeight() - pixelsUnder;

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
		Player.stillPlayerImage = "images/playerStill.png";
		Player.movingPlayerImage1 = "images/playerMoving1.png";
		Player.movingPlayerImage2 = "images/playerMoving2.png";
	}

}
