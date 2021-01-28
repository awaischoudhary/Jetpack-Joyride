package awaisfinalgame;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Score {

	// set canvas and graphics context
	GraphicsContext gc;	
	@FXML
	Canvas gameCanvas;

	
	public Score(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
	}
	
	
	public void display(Player player) {
		String scoreString = Player.score + "m";
		gc.setFont(Font.font("Rockwell", FontWeight.BOLD, 20));
		gc.setFill(Color.WHITE); 
		gc.fillText(scoreString, 5 ,20);
		
		String coinString = Coin.coinScore + "c";
		gc.setFont(Font.font("Rockwell", FontWeight.BOLD, 20));
		gc.setFill(Color.GOLD); 
		gc.fillText(coinString, 5 ,40);

	}

	
}
	


