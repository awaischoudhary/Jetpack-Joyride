package awaisfinalgame;

import java.io.IOException;
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
	int powerupHit = (int)(Math.random()*100);
	double bgSpeed1 = 2;
	static boolean invisible = false;

	int iterations = 0;

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
		
		Powerup powerup = new Powerup(gc, gameCanvas);		
		
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
				
				if (iterations > 10) {
					if (player.score % 20 == 0 && player.score > 1) {
						Laser.speed += 0.1;
						Coin.speed += 0.1;
						Missile.speed += 0.1;
					}
					iterations=0;
				}
				iterations++;
				
	
				
				
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
				
					if (collidedMissile && !invisible) {
						player.vy = 3.5;
						Player.curImageName = Player.playerDead;
						Missile.x = -100;
					
					}
					if (player.getY() > 340 & Player.curImageName == Player.playerDead) {
						System.out.println("Game Over");
						stop();
					}
				}
				
				collidedPowerup = player.collisionPowerup(powerup);
				
				if (collidedPowerup) {
					Powerup.y = 1000;
					
					
					// change the powerup for next time
					powerupHit++;

					if (powerupHit%2 == 0) {	
						SpeedPowerup.applyPowerup();
					}
					else if (powerupHit%2 == 1) {
						InvisiblePowerup.applyPowerup();
					}
				}
				
										
	
				
				
				for (int i = 0; i < Laser.numLasers; i++) {
					Laser l = laserList.get(i);
					
					collidedLaser = player.collisionLaser(l);
				
					if (collidedLaser && !invisible) {
						
						
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
					
				
				powerup.move();
				
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
	
//	/* Description: changes to end screen
//	 * Precondition: does not take in anything
//	 * Postcondition: changes the scene
//	 */
//	public void changeToEndScreen() {
//
//		try {
//			FXMLLoader loader = new FXMLLoader();  
//	        loader.setLocation(getClass().getResource("EndScreen.fxml"));  
//	        BorderPane sceneParent = (BorderPane)loader.load();   
//	            
//	        Scene scene = new Scene(sceneParent);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//
//	        MonkeyGameController controller = loader.getController();
//	        controller.getScore(Score.gameScore);
//			stage.setScene(scene);
//			stage.show();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//	
//	/* Description: pass on score to end screen
//	 * Precondition: takes in gameScore
//	 * Postcondition: displays the score in a label
//	 */
//	public void getScore(int gameScore) {		
//		String finalScore = "Your Score Was: " + gameScore;	
//		displayScore.setText(finalScore);
//	}
//
//	/* Description: endClickHandler, handles buttons on end screen
//	 * Precondition: takes in ActionEvent
//	 * Postcondition: allows user to play again or quit
//	 */
//	public void endClickHandler(ActionEvent evt) throws IOException {
//		Button clickedButton = (Button) evt.getTarget();
//		String buttonLabel = clickedButton.getText();
//
//		if (buttonLabel.equals("PLAY AGAIN")) {
//			changeToGame(evt);
//		} else if (buttonLabel.equals("QUIT")) {
//			System.exit(0);
//		}
//	}
//
//	/* Description: change the scene to main game
//	 * Precondition: takes in ActionEvent
//	 * Postcondition: allows the user to play the game again when game is over
//	 */
//	public void changeToGame(ActionEvent evt) throws IOException {
//		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(getClass().getResource("AwaisFinalGame.fxml"));
//		BorderPane sceneParent = (BorderPane) loader.load();
//
//		Scene scene = new Scene(sceneParent);
//
//		AwaisFinalGameController controller = loader.getController();
//
//		Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
//
//		stage.setScene(scene);
//		controller.setScene(stage);
//		controller.setStage(stage);
//		controller.gameLoop();
//
//		stage.show();
//	}

	
}
