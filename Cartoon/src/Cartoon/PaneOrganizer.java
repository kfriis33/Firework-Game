package Cartoon;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/*
 * This class organizes the panes in the program. It creates an instance of 
 * Cartoon which contains and initializes the other objects in the cartoon. 
 * This class also creates a quit button to close the app, and it 
 * provides labels with instructions for the user.
 */
public class PaneOrganizer {
	
	private BorderPane _pane;
	
	/*
	 * This is the constructor for a PaneOrganizer. It calls the helper method
	 * setUpPane() to initialize the panes, quit button, and instructions.
	 */
	public PaneOrganizer(){
		this.setUpPane();
	}
	
	/* This helper method creates an instance of Cartoon and passes the root 
	 * pane of Cartoon into the center of the BorderPane, which is set as the 
	 * scene in App.java. It also creates a quit button to close the app and a 
	 * VBox alignment for the instructions.
	 */
	public void setUpPane(){
		_pane = new BorderPane();
		_pane.setStyle("-fx-background-color: black;");
		
		Cartoon cartoon = new Cartoon();
		_pane.setCenter(cartoon.getRoot());
		
		Button quitButton = new Button("Quit");
		quitButton.setOnAction(new ClickHandler());
		
		Text instructions = new Text();
		instructions.setWrappingWidth(100);
		instructions.setFill(Color.WHITE);
		instructions.setText("\n\nPress the left and right arrow keys to move the rocket,"
				+ " then the up arrow to shoot! \n\nPress C to change the rocket's color"
				+ "\n\nPress the Spacebar to make it explode, then shoot again!\n");
		
		VBox vb = new VBox();
		vb.setPadding(new Insets(5));
		vb.getChildren().addAll(instructions, quitButton);
	
		_pane.setRight(vb);
	}
	
	/*
	 * This method returns the BorderPane that contains the Cartoon, the
	 * quit button, and the instructions. This method is called in the App.java
	 * class to add the pane to the stage.
	 */
	public Pane getRoot(){
		return _pane;
	}

	/*
	 * This private inner EventHandler class is linked to the button
	 * so the button listens for clicks to exit the application.
	 */
	private class ClickHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e){
			Platform.exit();
		}
	}
}