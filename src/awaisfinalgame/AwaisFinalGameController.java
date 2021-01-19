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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AwaisFinalGameController {

	// setting the canvas
	@FXML
	Canvas gameCanvas;

	// setting the graphics context, the gameScene, and stage
	GraphicsContext gc;
	Scene gameScene;
	Stage stage;

	public void setScene(Stage stage) {
		gameScene = stage.getScene();
	}

	public void gameLoop() {

		gc = gameCanvas.getGraphicsContext2D();
		Image background = new Image("images/background.png", 1000, 500, false, false);

		// ArrayList for keyboard input
		ArrayList<String> input = new ArrayList<String>();

		ArrayList<Laser> laserList = new ArrayList<Laser>();
		for (int i = 0; i < Laser.numLasers; i++) {
			laserList.add(new Laser(gc, gameCanvas));
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

		Player player = new Player(gc, gameCanvas, input);
		
		

		new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
				gc.drawImage(background, 0, 0);

				for (int i = 0; i < Laser.numLasers; i++) {
					laserList.get(i).drawLaser();
				}
				
				player.move();

			}
		}.start();
	}
}
