package Cartoon;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;

/*
 * This class models a bird, which is made out of two arc shapes. They can move
 * horizontally across the screen to simulate birds flying.
 */
public class Bird {

	private Arc _rightWing;
	private Arc _leftWing;
	private double _speed;
	
	/*
	 * The constructor for a bird. It takes in the doubles "x" and "y" as the
	 * starting coordinates for the bird and "s" for its speed. It also takes in
	 * a pane as an argument for the wing arcs to be added to.
	 */
	public Bird(Pane pane, double x, double y, double s){
		/*
		 * Initializes the arcs but sets their starting x positions relative
		 * to 0 so that their coordinate systems align with the game pane's
		 */
		_leftWing = new Arc(0, y, 20, 15, 45, 100);
		_rightWing = new Arc(33, y, 20, 15, 45, 100);

		_leftWing.setStroke(Color.DARKSLATEGRAY);
		_leftWing.setStrokeWidth(5);
		_rightWing.setStroke(Color.DARKSLATEGRAY);
		_rightWing.setStrokeWidth(5);
		
		_speed =s;
		
		pane.getChildren().addAll(_rightWing, _leftWing);
		
		// Now moves the wings to their starting positions
		_leftWing.setLayoutX(x);
		_rightWing.setLayoutX(x);
	}
	
	/*
	 * This method moves the bird horizontally by its speed. It moves the bird's
	 * Y-location in a sin wave based on its x position. It also has the bird
	 * reset to the left edge of the screen once it goes offscreen on the right.
	 */
	public void move(){
		_rightWing.setLayoutX(_rightWing.getLayoutX()+_speed);
		_leftWing.setLayoutX(_leftWing.getLayoutX()+_speed);
		_rightWing.setLayoutY(15*Math.sin(_rightWing.getLayoutX()/15));
		_leftWing.setLayoutY(15*Math.sin(_leftWing.getLayoutX()/15));
		
		if(_rightWing.getLayoutX()> Constants.GAMEPANE_PREF_WIDTH+33){
			_rightWing.setLayoutX(-33);
			_leftWing.setLayoutX(-33);
		}
	}
	
	// Method to return the bird's x coordinate
	public double getXLoc(){
		return _rightWing.getLayoutX();
	}
}
