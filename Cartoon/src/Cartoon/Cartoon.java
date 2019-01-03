package Cartoon;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/*
 * This class models the cartoon. It creates a Pane that contains everything in 
 * the cartoon and gets passed to the PaneOrganizer class. It also sets up the 
 * Timeline so that the rocket is animated and user input so on command it can 
 * move horizontally, be shot up, change color, or explode into a firework.
 */
public class Cartoon {
	private Pane _gamePane;
	private Rocket _rocket;
	private Bird[] _birds;
	private Label _locLabel; // Label to show rocket's height
	
	// The increment the rocket will move each time the Timeline is updated
	private int _rocketUp; 
		
	/*
	 * The constructor for cartoon. It utilizes the helper methods
	 * setUpGamePane() and setUpTimeline() to set up and itialize the objects in
	 *  the game pane and and start the timeline for animation.
	 */
	public Cartoon(){
		this.setUpGamePane();
		this.setupTimeline();
	}
	
	/* 
	 * This is a helper method to set up and add nodes to the game pane.
	 * It creates and adds the background image, the rocket, the dynamic label.
	 * It also makes the game pane listen for user input.
	 */
	public void setUpGamePane(){
		_gamePane = new Pane();
		_gamePane.setPrefSize(Constants.GAMEPANE_PREF_WIDTH, 
				Constants.GAMEPANE_PREF_HEIGHT);
		_gamePane.setStyle("-fx-background-color: black;");

		ImageView background = new ImageView(new Image
				(getClass().getResource("background.jpg").toExternalForm()));
		background.setFitWidth(Constants.GAMEPANE_PREF_WIDTH);
		background.setPreserveRatio(true);
		
		Pane rocketPane = new Pane();
		_rocket = new Rocket(rocketPane);
		
		Pane birdPane = new Pane();
		_birds = new Bird[4];
		for(int x=0; x<_birds.length; x++){
			_birds[x]= new Bird(birdPane,x*200, x*30 +50, x +1);
		}
		
		_locLabel = new Label();
		_locLabel.setTextFill(Color.WHITE);
		
		_gamePane.getChildren().addAll(background, rocketPane, _locLabel, birdPane);
		_gamePane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
		_gamePane.setFocusTraversable(true);
	}
	
	/* This method sets up a Timeline to run the animation for the program.
	 * It creates a KeyFrame that updates the variables every .1 second, 
	 * allowing movement to show smoothly.
	 */
	public void setupTimeline(){
		KeyFrame kf = new KeyFrame(
					Duration.seconds(.1),
					new TimeHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	// An accessor method to return the gamePane
	public Pane getRoot(){
		return _gamePane;
	}
	
	/* A private inner Eventhandler class to handle the animation of the Timeline.
	 * It controls the vertical movement of the rocket and the dynamic label
	 * displaying the rocket's height.
	 */
	private class TimeHandler implements EventHandler<ActionEvent>{		
		
		/* This method is automatically called by the Timehandler to animate
		 * the rocket moving upwards and the birds flying. It resets the 
		 * positions of both things if they go offscreen, and it updates the 
		 * label to change based on the rocket's height.
		 */
		@Override
		public void handle(ActionEvent event){
			
			// Moves the rocket vertically by the value of _rocketUp
			_rocket.setYLoc(_rocket.getYLoc()+_rocketUp);
			
			if(_rocket.getYLoc()<0){ // If the rocket is offscreen
				_rocket.reset(); // Returns rocket to original position 
				_rocketUp=0; // Makes rocket still
			}
			// Checks to see if the rocket is at the bottom of the screen
			else if(_rocket.getYLoc()== Constants.GAMEPANE_PREF_HEIGHT){
				_locLabel.setText("Rocket height is 0... "
						+ "Press the up arrow to shoot the Rocket");
			}
			else{
				_locLabel.setText(" Rocket height is "
						+ (Constants.GAMEPANE_PREF_HEIGHT-_rocket.getYLoc())
						+ "... Press Spacebar to make explode!");
			}
			
			for(int x=0; x<_birds.length; x++){
				_birds[x].move();
			}
		}
	}
	
	/* A private inner EventHandler class to listen for keyboard input from the 
	 * user. It responds to the keys C, up arrow, left arrow, right arrow, and 
	 * the spacebar. With a switch statement, it changes the rocket based on 
	 * which of these keys is pressed.
	 */
	private class KeyHandler implements EventHandler<KeyEvent> {
		
		/* This method listens for keyboard clicks and makes decisions based on 
		 * what key is pressed. C changes the rocket's color. Up arrow shoots it 
		 * up. Left and right arrows moves it left or right respectively if it 
		 * hasn't already been shot.
		 */
		@Override
		public void handle (KeyEvent e){
			KeyCode keyPressed = e.getCode(); 
			
			switch(keyPressed){
			case C:
			_rocket.changeColor();
				break;
			
			case UP:
			_rocketUp = -10;
				break;
				
			case LEFT:
			// Checks that the rocket has not been shot and is on the screen
			if(_rocket.getYLoc()==Constants.GAMEPANE_PREF_HEIGHT 
			&& _rocket.getXLoc()>0){ 
				_rocket.setXLoc(-7); 
			}
				break;
				
			case RIGHT:
			// Checks that the rocket has not been shot and is on the screen
			if(_rocket.getYLoc()==Constants.GAMEPANE_PREF_HEIGHT && 
			_rocket.getXLoc()<(Constants.GAMEPANE_PREF_WIDTH - Constants.TRI_WIDTH)){ 
				_rocket.setXLoc(8);
			}
				break;
			
			case SPACE:
			//Checks that the rocket has been shot
			if(_rocket.getYLoc()<Constants.GAMEPANE_PREF_HEIGHT){ 
				_rocket.explode(_gamePane); // Explodes into a firework!
			}
				break;
						
			default: // Default case to break out of switch statement
				break;
			}
			
			e.consume(); // Throws away event after it has completed everything
		}		
	}
}
