package awaisfinalgame;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Player {

	// x and y positions
	double x = 100;
	double y = 100;

	// monkey speed
	int speed = 3;

	 // moving position
	double vy = 0.1;
	double ay = 0.06;

	// assigning variables to keyboard values
	String up = "SPACE";

	// get the monkey image
	String imageName = "images/playerStill.png";
	Image image = new Image(imageName, 60, 50, false, false);

	// set canvas and graphics context
	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	ArrayList<String> input;

	public Player(GraphicsContext gc, Canvas gameCanvas, ArrayList<String> input) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		this.input = input;
		setPosition();
	}

	// Monkey constructors
	public Player(String imageName, GraphicsContext gc, Canvas gameCanvas, ArrayList<String> input) {
		this.imageName = imageName;
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		this.input = input;
	}

	public void setPosition() {
		this.x = 40;
		this.y = 340;
	}

	// getters and setters for monkey
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void move() {
	
		if (this.input.contains(this.up)) {
			this.vy = -1.5 ;
		}  
		
		double y = this.y;
		this.y += this.vy;
		

        this.setY(this.getY() + vy);
          
        vy += ay;
        
        if (this.y > 340) {
			this.vy=0;
		}
        if (this.y < 0) {
			this.y = y ;
		}
		
		this.gc.drawImage(this.image, this.x, this.y);
	}
 
}
