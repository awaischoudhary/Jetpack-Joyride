package awaisfinalgame;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
	static boolean collidedCoin = false;
	int coinY;
	int backgroundX1 = 0;
	int backgroundX2= 1000;
	int powerupHit = 0;


	public void setScene(Stage stage) {
		gameScene = stage.getScene();
	}
	
	public void setStage(Stage s) {
		stage = s;
	}

	public void gameLoop() {

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
		coinY = (int)(Math.random()*(370));
		for (int i = 0; i < Coin.numCoins; i++) {
			coinList.add(new Coin(gc, gameCanvas, i));
			
		}
		
		ArrayList<Missile> missileList = new ArrayList<Missile>();
		for (int i = 0; i < Missile.numMissiles; i++) {
			missileList.add(new Missile(gc, gameCanvas));
		}
		
		ArrayList<MissileWarning> missileWarningList = new ArrayList<MissileWarning>();
		for (int i = 0; i < MissileWarning.numWarning; i++) {
			missileWarningList.add(new MissileWarning(gc, gameCanvas));
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
		
		//Powerup powerup = new Powerup(gc, gameCanvas);
	
		
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
				
	
				if (player.score % 200 == 0 && player.score > 1) {
					Laser.speed += 0.1;
					Coin.speed += 0.1;
					Missile.speed += 0.1;
				}
				
				backgroundX1-=2;
				backgroundX2-=2;
				
				gc.drawImage(background1, backgroundX1, 0);
				gc.drawImage(background2, backgroundX2, 0);	
				
				for (int i = 0; i < Coin.numCoins; i++) {
					coinList.get(i).move(i);		
				}

			
				for (int i = 0; i < MissileWarning.numWarning; i++) {
					missileWarningList.get(i).move();
				}
				

				for (int i = 0; i <  Missile.numMissiles; i++) {
					Missile m = missileList.get(i);
					
					collidedMissile = player.collisionMissile(m);
				
					if (collidedMissile) {
						player.vy = 3.5;
						Player.curImageName = Player.playerDead;
						Missile.x = -100;
					
					}
					if (player.getY() > 340 & Player.curImageName == Player.playerDead) {
						System.out.println("Game Over");
						stop();
					}
				}
				
//				collidedPowerup = player.collisionPowerup(powerup);
//				
//				if (collidedPowerup) {
//					Powerup.y = 1000;
//
//					// initiate a timer with the corresponding (polymorphism) timer task 
//					// powerup.timer.schedule(powerup.applyPowerupTask, 0, 1000)
//
//					// this will use polymorphism, it will know which specific
//					// powerups appyPowerup function to use
//					powerup.applyPowerup(laserList, missileList);
//				// 	powerup.applyPowerup(laserList, missileList, player);
//					
//					
//					// change the powerup for next time
//					powerupHit++;
//					if (powerupHit%2 == 0) {
//						powerup = new SpeedPowerup();
//					}
//					else if (powerupHit%2 == 1) {
//						//powerup = new LaserPowerup(gc, gameCanvas);
//					}
//				}
				
										
	
				
				
				for (int i = 0; i < Laser.numLasers; i++) {
					Laser l = laserList.get(i);
					
					collidedLaser = player.collisionLaser(l);
				
					if (collidedLaser) {
				
						
						}
		
						
						
						
						
						
						Player.curImageName = Player.playerDead;
						stop();

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
						coinList.get(i).collisionRespawning();
						Coin.coinScore++;
						
					}
				}
					
				
				//powerup.move();
				
				for (int i = 0; i < Laser.numLasers; i++) {
					laserList.get(i).move();
				}
				
				
				score.display(player);
				for (int i = 0; i < Missile.numMissiles; i++) {
					missileList.get(i).move();
				}
				
				player.move();
			

				

			}
		}.start();
	}
}
