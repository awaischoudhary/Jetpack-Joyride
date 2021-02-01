/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: Start screen controller, contains all start screen methods
 */
package awaisfinalgame;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartScreenController {
	
	Scene gameScene;
	Stage stage;
	
	// creating a second stage for instructions
	static Stage secondaryStage;

	// Description: button click buttons handler
	// Precondition: Takes in the action even
	// Postcondition: Does actions depending on button clicked
	public void buttonClickHandler(ActionEvent evt) throws IOException {
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();
	
		// if play clicked, change to game method called
		if (buttonLabel.equals("PLAY")) {
			changeToGame(evt);
		} 
		// if instructions clicked, open instructions method called
		else if (buttonLabel.equals("SETTINGS")) {
			openInstructions();
		} 
		// if quit clicked, quits the menu 
		else if (buttonLabel.equals("QUIT")) {
			System.exit(0);
		}
	}
	
	// Description: Change to game method
	// Precondition: Takes in the action even
	// Postcondition: Changes game scene to main game
	public void changeToGame(ActionEvent evt) throws IOException {	
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
	
	// Description: Open instructions stage
	// Precondition: Does not take in anything
	// Postcondition: opens a new instructions fxml on a new stage
	private void openInstructions() {
		try {
			// load the pop up you created
			Pane instructions = (Pane) FXMLLoader.load(getClass().getResource("Settings.fxml"));

			// create a new scene
			Scene instructionsScene = new Scene(instructions, 500, 350);

			secondaryStage = new Stage();
			secondaryStage.setScene(instructionsScene);
			secondaryStage.setResizable(false);
			secondaryStage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Description: Close instructions window
	// Precondition: Takes in the action even
	// Postcondition: stage closes when exit
	public void closeCurrentWindow(final ActionEvent evt) {
		final Node source = (Node) evt.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

}
