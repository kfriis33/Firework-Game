package Cartoon;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;

/*
 * This class models a Firework. It is made up out of a firework image and 
 * a label. It has a method fadeOut() that uses the javafx transition to make
 * both objects fade out slowly from the screen.
 */
public class Firework {
	private ImageView _fireworkImage;
	private Label _bangLabel;
	
	/*
	 * The constructor for a firework. It initializes the ImageView for the
	 * firework image and initalizes the bang label. It takes in doubles as
	 * arguments for the X and Y coordinates of the firework. It also takes in 
	 * a pane and adds the image and firework to it.
	 */
	public Firework(Pane pane, double centerX, double centerY){
		_fireworkImage = new ImageView(
				new Image(getClass().getResource("firework.png").toExternalForm()));
		_fireworkImage.setFitWidth(Constants.FIREWORK_WIDTH);
		_fireworkImage.setPreserveRatio(true);
		_fireworkImage.setX(centerX - (Constants.FIREWORK_WIDTH/2));
		_fireworkImage.setY(centerY - (Constants.FIREWORK_WIDTH/2));
		
		_bangLabel = new Label("BANG!");
		_bangLabel.setFont(new Font(30));
		_bangLabel.setTextFill(Color.WHITE);
		_bangLabel.setLayoutX(centerX + (Constants.FIREWORK_WIDTH/2)- 75);
		_bangLabel.setLayoutY(centerY - (Constants.FIREWORK_WIDTH/2) +20);
		_bangLabel.setRotate(45);
		pane.getChildren().addAll(_fireworkImage, _bangLabel);
	}
	
	/*
	 * This method makes the firework ImageView and the bang label fade out
	 * slowly. It uses the FadeTransition class from the Javafx package to
	 * accomplish this.
	 */
	public void fadeOut(){
		// Fades out the fireworkImage over 4000 milliseconds
		FadeTransition ft1 = new FadeTransition(
				Duration.millis(4000), _fireworkImage);
		ft1.setFromValue(1.0);
		ft1.setToValue(0);
		ft1.setCycleCount(1);
		ft1.setAutoReverse(true);
		ft1.play();
		
		// Fades out the bangLabel over 2000 milliseconds
		FadeTransition ft2 = new FadeTransition(Duration.millis(2000),
				_bangLabel);
		ft2.setFromValue(1.0);
		ft2.setToValue(0);
		ft2.setCycleCount(1);
		ft2.setAutoReverse(true);
		ft2.play();
	}
}
