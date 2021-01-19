package awaisfinalgame;

import javafx.scene.Group;

public class PlayerAnimation {

	final static javafx.scene.image.Image Player1 = new javafx.scene.image.Image(PlayerAnimation.class.getResource("images/playerMoving1.png").toString());
	final static javafx.scene.image.Image Player2 = new javafx.scene.image.Image(PlayerAnimation.class.getResource("images/playerStill.png").toString());
	final static javafx.scene.image.Image Player3 = new javafx.scene.image.Image(PlayerAnimation.class.getResource("images/playerMoving2.png").toString());

	
	private Group player;
}
