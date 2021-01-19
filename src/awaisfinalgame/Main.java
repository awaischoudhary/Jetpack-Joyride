package awaisfinalgame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	

	final static javafx.scene.image.Image Player1 = new javafx.scene.image.Image(PlayerAnimation.class.getResource("/images/playerMoving1.png").toString());
	final static javafx.scene.image.Image Player2 = new javafx.scene.image.Image(PlayerAnimation.class.getResource("/images/playerStill.png").toString());
	final static javafx.scene.image.Image Player3 = new javafx.scene.image.Image(PlayerAnimation.class.getResource("/images/playerMoving2.png").toString());

	
	private Group player;
	@Override
	public void start(Stage primaryStage) {
		
		final ImageView player1 = new ImageView(Player1);
		final ImageView player2 = new ImageView(Player2);
		final ImageView player3 = new ImageView(Player3);

		player = new Group(player1);
		
		player.setTranslateX(200);
		player.setTranslateY(220);
		
		Timeline t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE);
		
		t.getKeyFrames().add(new KeyFrame(
			Duration.millis(200),
			(ActionEvent event) -> {
				player.getChildren().setAll(player2);
			}
		));
		
		t.getKeyFrames().add(new KeyFrame(
				Duration.millis(300),
				(ActionEvent event) -> {
					player.getChildren().setAll(player3);
				}
			));
		
		t.play();

		primaryStage.setScene(new Scene(player, 1000, 500));
		primaryStage.show();
		
		

		try {
			primaryStage.setTitle("Jetpack Joyride");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AwaisFinalGame.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root, 1000, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			AwaisFinalGameController controller = loader.getController();
			//primaryStage.setScene(scene);
			controller.setScene(primaryStage);
			controller.gameLoop();
			//primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
