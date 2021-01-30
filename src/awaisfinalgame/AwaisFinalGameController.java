package awaisfinalgame;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;


public class AwaisFinalGameController {

	// setting the canvas
	@FXML
	Canvas gameCanvas;

	// setting the graphics context, the gameScene, and stage
	GraphicsContext gc;
	Scene gameScene;
	Stage stage;
	
	boolean collidedLaser = false;
	boolean collidedMissile = false;
	boolean collidedPowerup = false;
	boolean collidedCoin = false;
	
	int backgroundX1 = 0;
	int backgroundX2= 1000;
	int speedingGame = 0;

	int randomPowerup = (int)(Math.random()*100);
		
	public AudioClip bgMusic = new AudioClip(Paths.get("src/sounds/BackgroundMusic.mp3").toUri().toString());
	public AudioClip coinPickup = new AudioClip(Paths.get("src/sounds/coinPickup.wav").toUri().toString());
	public AudioClip laserHit = new AudioClip(Paths.get("src/sounds/LaserHit.wav").toUri().toString());
	public AudioClip powerupPickup = new AudioClip(Paths.get("src/sounds/PowerupHit.wav").toUri().toString());
	public AudioClip missileExplode = new AudioClip(Paths.get("src/sounds/MissileExplode.wav").toUri().toString());


	public void setScene(Stage stage) {
		gameScene = stage.getScene();
	}
	
	public void setStage(Stage s) {
		stage = s;
	}

	public void gameLoop() {

		bgMusic.setCycleCount(AudioClip.INDEFINITE);
		bgMusic.play();
		
		gc = gameCanvas.getGraphicsContext2D();
		Image background1 = new Image("images/background.png", 1000, 500, false, false);
		Image background2 = new Image("images/background.png", 1000, 500, false, false);

		// ArrayList for keyboard input
		ArrayList<String> input = new ArrayList<String>();
		

		ArrayList<Laser> laserList = new ArrayList<Laser>();
		for (int i = 0; i < Laser.numLasers; i++) {
			laserList.add(new Laser(gc, gameCanvas, i));
		}
		
		ArrayList<Coin> coinList = new ArrayList<Coin>();
		for (int i = 0; i < Coin.numCoins; i++) {
			coinList.add(new Coin(gc, gameCanvas, i));
			
		}
		
		
		
		// when the key is pressed, check if key is in array list, if not add it
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (!input.contains(code)) {
					input.add(code);
				}
			}
		});

		// when the key is released, check if key is in array list, if so remove it
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (input.contains(code)) {
					input.remove(code);
				}
			}
		});

		gameScene.setOnMousePressed(e->{
			String code = e.getButton().toString();
			if (!input.contains(code))
				input.add(code);
		});
		
		gameScene.setOnMouseReleased(e->{
			String code = e.getButton().toString();
			if (input.contains(code))
				input.remove(code);
		});
		
		Player player = new Player(gc, gameCanvas, input);
		
		Score score = new Score(gc, gameCanvas);
		
		Powerup powerup = new Powerup(gc, gameCanvas);	
		
		MissileWarning missileWarning = new MissileWarning(gc, gameCanvas);
		
		Missile missile = new Missile(gc, gameCanvas);

		
		new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
			
				
				if (backgroundX1 <= -1000) {
					backgroundX1 = 1000;
				}
				if (backgroundX2 <= -1000) {
					backgroundX2 = 1000;
				}
				
				if (speedingGame > 10) {
					if (player.score % 20 == 0 && player.score > 1) {
						Laser.speed += 0.1;
						Coin.speed += 0.1;
						Missile.speed += 0.1;
					}
					speedingGame=0;
				}
				speedingGame++;
				
	
				
				
				backgroundX1-=2;
				backgroundX2-=2;
				
				gc.drawImage(background1, backgroundX1, 0);
				gc.drawImage(background2, backgroundX2, 0);	
				
				for (int i = 0; i < Coin.numCoins; i++) {
					coinList.get(i).move(i);		
				}

			
				
					
				collidedMissile = player.collisionMissile(missile);
				
					if (collidedMissile && ! InvisiblePowerupTask.invisible) {
						missileExplode.play();
						Player.curImageName = Player.playerDead;
						missile.setX(-100);
					}
					
				
				collidedPowerup = player.collisionPowerup(powerup);
				
				if (collidedPowerup) {
					powerup.setY(1000);
					powerupPickup.play();
					
					// change the powerup for next time
					randomPowerup++;

					if (randomPowerup % 2 == 0) {	
						SpeedPowerup.applyPowerup();
					}
					else if (randomPowerup % 2 == 1) {
						InvisiblePowerup.applyPowerup();
					}
				}
				
										
	
				
				
				for (int i = 0; i < Laser.numLasers; i++) {
					Laser l = laserList.get(i);
					
					collidedLaser = player.collisionLaser(l);
				
					if (collidedLaser && ! InvisiblePowerupTask.invisible) {
						laserHit.play();
						
						Player.curImageName = Player.playerDead;

					}
					if (player.getY() > 340 & Player.curImageName == Player.playerDead) {
						// GAME IS OVER
						System.out.println("Game Over");
						stop();
					}
				}
				

				for (int i = 0; i < Coin.numCoins; i++) {
					Coin c = coinList.get(i);
					
					collidedCoin = player.collisionCoin(c);
					
					if (collidedCoin) {
						coinPickup.play();
						coinList.get(i).collisionRespawning();
						Coin.coinScore++;
						
					}
				}
					
				
				powerup.move();
				
				for (int i = 0; i < Laser.numLasers; i++) {
					laserList.get(i).move();
				}
				
				
				score.display(player);
				missileWarning.move(player, missile);
				missile.move(missileWarning);

				player.move();
			
			
				

			}
		}.start();
	}
}