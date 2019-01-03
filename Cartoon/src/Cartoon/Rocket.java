package Cartoon;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/*
 * This class models a rocket that is launched from the bottom of the screen
 * and can be exploded into a firework with the explode() method. It is made up
 * of javafx shapes that move together when the setXLox() and setYLoc methods 
 * are called. It also changes color with the changeColor() method.
 */
public class Rocket {
	private Rectangle _rec;
	private Polygon _tri;
	private Line _fuse;
	private Label _sizzleLabel;
	
	/*
	 * The constructor for a rocket. It takes in a Pane as a parameter so
	 * that the shapes can be added to the same pane as a composite shape.
	 * It uses the helper methods setUpShapes() to organize the shapes and 
	 * setYLoc() to move the composite shape to the starting height. 
	 */
	public Rocket(Pane rocketPane){
		this.setUpShapes(); 
		this.setYLoc(Constants.START_HEIGHT);
		rocketPane.getChildren().addAll(_rec, _tri, _fuse, _sizzleLabel);
	}
	
	/*
	 * This method instantiates and sets the relative locations, sizes, and 
	 * colors of the shapes in the rocket using constants from the
	 * Constants.java class.
	 */
	public void setUpShapes(){
		_rec = new Rectangle();
		_rec.setHeight(Constants.REC_HEIGHT);
		_rec.setWidth(Constants.REC_WIDTH);
		_rec.setX(Constants.REC_X);
		_rec.setFill(Color.RED);
		
		_tri = new Polygon(Constants.TRI_X,0,
				Constants.TRI_X+Constants.TRI_WIDTH,0,
				Constants.TRI_X + (Constants.TRI_WIDTH)/2,-1*Constants.TRI_HEIGHT);
		_tri.setFill(Color.MAROON);

		_fuse = new Line();
		_fuse.setStartX(Constants.FUSE_X);
		_fuse.setEndX(Constants.FUSE_X);
		_fuse.setStroke(Color.YELLOW);
		
		_sizzleLabel = new Label("sizzle... sizzle...");
		_sizzleLabel.setTextFill(Color.ORANGE);
		_sizzleLabel.setLayoutX(Constants.SIZ_LABEL_X);
	}
	
	/*
	 * This method moves the y location of the shapes by the number taken in 
	 * as an argument, "y".
	 */
	public void setYLoc(double y){
		_rec.setY(y);
		_tri.setTranslateY(y);	
		_fuse.setStartY(y+Constants.REC_HEIGHT);
		_fuse.setEndY(y+1.5*Constants.REC_HEIGHT);
		_sizzleLabel.setTranslateY(y + Constants.REC_HEIGHT + 8);
	}
	
	// Method to return the rocket's Y position, based on the central rectangle
	public double getYLoc(){
		return _rec.getY();
	}
	
	/*
	 * This method moves the x location of the shapes by the number taken in 
	 * as an argument, "y".
	 */
	public void setXLoc(double x){
		_rec.setX(_rec.getX() + x);
		_tri.setLayoutX(_tri.getLayoutX() + x);
		_fuse.setLayoutX(_fuse.getLayoutX() +x);
		_sizzleLabel.setLayoutX(_sizzleLabel.getLayoutX() +x);
	}
	
	// Method to return the rocket's X position, based on the central rectangle
	public double getXLoc(){
		return _rec.getX();
	}
	
	/*
	 * This method creates an instance of Firework in the location of the
	 * rocket's most recent location and calls the firewo	private Polygon _tri;
rk's fadeOut method to
	 * make the image fade out. It also moves the rocket off the screen.
	 */
	public void explode(Pane game){
		Firework firework = new Firework(
				game, _rec.getX()+Constants.REC_WIDTH/2,_rec.getY());
		firework.fadeOut();
		this.setYLoc(-500); //moves rocket off screen
	}
	
	// Method to reset the rocket to its original Y location
	public void reset(){
		this.setYLoc(Constants.START_HEIGHT);
	}
	
	/*
	 * This method changes the color of the rocket using three random numbers
	 * as the rgb values. It uses the darker method built into the Color class
	 * so that the triangle is the same color as the rectangle, just darker.
	 */
	public void changeColor(){
		int rand1 = (int)(Math.random()*255);
		int rand2 = (int)(Math.random()*255);
		int rand3 = (int)(Math.random()*255);

		Color c = Color.rgb(rand1, rand2, rand3);
		_rec.setFill(c);
		_tri.setFill(c.darker());
	}
}
