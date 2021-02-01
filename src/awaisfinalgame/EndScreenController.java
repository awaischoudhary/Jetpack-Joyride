/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: End screen controller, handles end screen
 */
package awaisfinalgame;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EndScreenController{
		
	// labels for final score displays
	@FXML 
	Label displayScore;
	@FXML 
	Label displayFinalScore;

	// Description: Resets values when playing again
	// Precondition: Does not take in anything
	// Postcondition: speeds and images are reset
	public void startGame() {
		Background.bgSpeed = 2;
		Laser.speed = 2.1;
		Coin.speed = 2.1;
		Player.score = 0;
		Coin.coinScore = 0;
		Player.setPlayerNormal(); 
		MissileWarning.curMissileWarning = MissileWarning.missileWarning;
	}
	
	// Description: Gets the score, and passes it on the scene
	// Precondition: Taskes in the metersScore and coinScore
	// Postcondition: All scores are displayed at label
	public void getScore(int metersScore, int coinScore) {	
		String score = metersScore + " meters + " + coinScore + " coins";	
		int finalScore =  metersScore + coinScore;
		
		// displaying both scores
		displayScore.setText(score);
		displayFinalScore.setText("FINAL SCORE: "+ finalScore);
	}

	// Description: End screen buttons handler
	// Precondition: Takes in the action even
	// Postcondition: Does actions depending on button clicked
	public void endClickHandler(ActionEvent evt) throws IOException {
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();

		// if user clicked button with play again, restart game
		if (buttonLabel.equals("PLAY AGAIN")) {
			Player.curImageName = Player.movingPlayer1;
			startGame();
			changeToGame(evt);
		} 
		// if user clicked button with quit, quit game
		else if (buttonLabel.equals("QUIT")) {
			System.exit(0);
		}
	}
	
	// Description: Change to game method
	// Precondition: Takes in the action even
	// Postcondition: Changes game scene to main game
	public void changeToGame(ActionEvent evt) throws IOException {			
		// loading the scene and fxml
        FXMLLoader loader = new FXMLLoader();  
        loader.setLocation(getClass().getResource("AwaisFinalGame.fxml"));  
		BorderPane root = (BorderPane) loader.load();
		Scene scene = new Scene(root, 1000, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        AwaisFinalGameController controller = loader.getController();     

        Stage stage = (Stage)((Node)evt.getSource()).getScene().getWindow();
         
        stage.setScene(scene);
        controller.setScene(stage);
        controller.setStage(stage);
    	controller.gameLoop();
      
        stage.show();
}
	
	
	
}
