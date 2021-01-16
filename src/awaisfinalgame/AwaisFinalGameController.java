package awaisfinalgame;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AwaisFinalGameController {

	// setting the canvas
	@FXML
	Canvas gameCanvas;
	
	// setting the score
	@FXML 
	Label displayScore;

	// setting the graphics context, the gameScene, and stage
	GraphicsContext gc;
	Scene gameScene;
	Stage stage;
	
	public void setScene(Stage stage) {
		gameScene = stage.getScene();
	}
	
	public void gameLoop() {
		System.out.println("Hello");
		
	}
}
