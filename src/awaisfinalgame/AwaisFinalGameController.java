/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: Main game controller, where game loop and animation timer is
 */
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;


public class AwaisFinalGameController {

	@FXML
	Canvas gameCanvas;	
	GraphicsContext gc;
	Scene gameScene;
	Stage stage;
	
	// gives a random powerup
	int randomPowerup = (int) (Math.random() * 100);

	// audio types
	public AudioClip bgMusic = new AudioClip(Paths.get("src/sounds/Jetpack Joyride.mp3").toUri().toString());
	public AudioClip coinPickup = new AudioClip(Paths.get("src/sounds/coinPickup.wav").toUri().toString());
	public AudioClip laserHit = new AudioClip(Paths.get("src/sounds/LaserHit.wav").toUri().toString());
	public AudioClip powerupPickup = new AudioClip(Paths.get("src/sounds/PowerupHit.wav").toUri().toString());
	public AudioClip missileExplode = new AudioClip(Paths.get("src/sounds/MissileExplode.wav").toUri().toString());

	// Description: Sets the game scene
	// Precondition: takes in the stage
	// Postcondition: gameScene is set to scene passed in
	public void setScene(Stage stage) {
		gameScene = stage.getScene();
	}

	// Description: Sets the stage
	// Precondition: takes in the stage
	// Postcondition: stage is set to stage passed in
	public void setStage(Stage s) {
		stage = s;
	}

	// game loop where all objects are ran in animation timer 
	public void gameLoop() {

		// setting the background image
		gc = gameCanvas.getGraphicsContext2D();
		Image background1 = new Image("images/background.png", 1000, 500, false, false);
		Image background2 = new Image("images/background.png", 1000, 500, false, false);

		// setting an infinite loop of background music
		bgMusic.setCycleCount(AudioClip.INDEFINITE);
		bgMusic.setVolume(0.3);
		bgMusic.play();

		// creating array list for input
		ArrayList<String> input = new ArrayList<String>();
		
		// ArrayLists for different objects
		ArrayList<Laser> laserList = new ArrayList<Laser>();
		for (int i = 0; i < Laser.numLasers; i++) {
			laserList.add(new Laser(gc, gameCanvas, i));
		}

		ArrayList<Coin> coinList = new ArrayList<Coin>();
		for (int i = 0; i < Coin.numCoins; i++) {
			coinList.add(new Coin(gc, gameCanvas, i));
		}

		// objects for player, score, powerup, missile, & warning
		Player player = new Player(gc, gameCanvas, input);

		Score score = new Score(gc, gameCanvas);

		Powerup powerup = new Powerup(gc, gameCanvas);

		MissileWarning missileWarning = new MissileWarning(gc, gameCanvas);

		Missile missile = new Missile(gc, gameCanvas);

		// when the key is pressed, check if key is not in array list, if so add it
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

		// when the mouse is pressed, check if mouse is not in array list, if so add it
		gameScene.setOnMousePressed(e -> {
			String code = e.getButton().toString();
			if (!input.contains(code)) {
				input.add(code);
			}
		});

		// when the mouse is released, check if mouse is in array list, if so remove it
		gameScene.setOnMouseReleased(e -> {
			String code = e.getButton().toString();
			if (input.contains(code)) {
				input.remove(code);
			}
		});

		// animation timer
		new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

				// calling the scrolling background method 
				Background.movingBackground();

				// drawing the looping backgrounds
				gc.drawImage(background1, Background.backgroundX1, 0);
				gc.drawImage(background2, Background.backgroundX2, 0);

				// calling collision methods of objects
				missileColliding(player, missile);
				powerupColliding(player, powerup);				
				laserColliding(player, laserList);	
				coinColliding(player, coinList);
		
				// displaying the scores
				score.displayingScore(player);

				// moving the objects
				powerup.move();	
				
				// for loop to move all coins in ArrayList
				for (int i = 0; i < Coin.numCoins; i++) {
					coinList.get(i).move(i);
				} 

				// for loop to move all lasers in ArrayList
				for (int i = 0; i < Laser.numLasers; i++) {
					laserList.get(i).move();
				}		
				
				missileWarning.move(player, missile);
				missile.move(missileWarning);	
				player.move();
				
				// if player hits ground show him dead and stop animation timer, music, and call the end screen 
				if (player.getY() > 340 & Player.curImageName == Player.playerDead) {
					stop();
					bgMusic.stop();
					changeToEndScreen();
				}
			}
		}.start();
	}
	

	// Description: end speeds when player dies 
	// Precondition: Does not take in anything
	// Postcondition: speeds are set to 0
	public void endSpeed() {
		Background.bgSpeed = 0;
		Laser.speed = 0;
		Coin.speed = 0;
		Powerup.speed = 0;
	}
	
	// Description: Apply actions when powerup is collided
	// Precondition: Takes in player and powerup
	// Postcondition: player has a random powerup
	public void powerupColliding(Player player, Powerup powerup) {	
		
		// check to see if player collides with rectangle box
		if (player.collisionPowerup(powerup)) {
			
			// check for see if player gets pixel collision
			if (player.pixelColliding(powerup.getImage(), powerup.getX(), powerup.getY())) {
				
				// reset powerup position and play sound
				int resetPosition = 1000;
				powerup.setY(resetPosition);
				powerupPickup.play();

				// change the powerup for next time
				randomPowerup++;

				// chooses random powerup between the two
				if (randomPowerup % 2 == 0) {
					SlowDownPowerup.applyPowerup();
				} else if (randomPowerup % 2 == 1) {
					InvisiblePowerup.applyPowerup();
				}
			}
		}

	}
	
	// Description: Apply actions when coin is collided
	// Precondition: Takes in player and coin array list
	// Postcondition: player picks up coins 
	public void coinColliding(Player player, ArrayList<Coin> coinList) {
		
		// for loop to go through array list of coins
		for (int i = 0; i < Coin.numCoins; i++) {
			Coin c = coinList.get(i);

			// if player collides with rectangle box of coin
			if (player.collisionCoin(c)) {
				
				// if player collides with coin pixels
				if (player.pixelColliding(c.getImage(), c.getX(), c.getY())) {

					// play sound, add score, and spawn coin again
					coinPickup.play();
					coinList.get(i).collisionRespawning();
					Coin.coinScore++;
				}
			}
		}
	}

	// Description: Apply actions when missile is collided
	// Precondition: Takes in player and missile
	// Postcondition: player dies when collided
	public void missileColliding(Player player, Missile missile) {		
		
		// check if player collides rectangle box and is not invisible powerup
		if (player.collisionMissile(missile) && !InvisiblePowerupTask.invisible) {
			
			// check if player collides with missile pixels
			if (player.pixelColliding(missile.getImage(), missile.getX(), missile.getY())) {
				
				// set the missile off screen, play explode, and end speeds
				int offScreen = -100;				
				missileExplode.play();
				Player.curImageName = Player.playerDead;
				missile.setX(offScreen);
				endSpeed();
			}
		}
	}
	
	
	// Description: Apply actions when laser is collided
	// Precondition: Takes in player and laser array list
	// Postcondition: player dies when collided
	public void laserColliding(Player player, ArrayList<Laser> laserList) {
		
		// for loop to go through all lasers
		for (int i = 0; i < Laser.numLasers; i++) {
			Laser l = laserList.get(i);

			// check if player collides rectangle box and is not invisible powerup
			if (player.collisionLaser(l) && !InvisiblePowerupTask.invisible) {

				// check if player collides with laser pixels
				if (player.pixelColliding(l.getImage(), l.getX(), l.getY())) {
					
					// play sound, end speed, and make player dead
					laserHit.play();
					endSpeed();
					Player.curImageName = Player.playerDead;
				}

			}
		}
	}
	
	
	// Description: Changes game scene to end screen
	// Precondition: Does not take in anything
	// Postcondition: scene is changed
	public void changeToEndScreen() {

		try {
			FXMLLoader loader = new FXMLLoader();  
	        loader.setLocation(getClass().getResource("EndScreen.fxml"));  
	        BorderPane sceneParent = (BorderPane)loader.load();   
	            
	        Scene scene = new Scene(sceneParent);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// referencing end screen controller to getScore method
	        EndScreenController controller = loader.getController();
	        controller.getScore(Player.score, Coin.coinScore);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}