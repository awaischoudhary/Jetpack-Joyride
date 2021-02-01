/* Program Name: AwaisFinalGame
 * Programmer: Awais Choudhary
 * Date: February 2, 2020
 * Description: Score class, all score related methods
 */
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

	// Score constructor 
	public Score(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
	}
	
	// Description: displays the score 
	// Precondition: Takes in player
	// Postcondition: score is displayed on canvas
	public void displayingScore(Player player) {
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
	


