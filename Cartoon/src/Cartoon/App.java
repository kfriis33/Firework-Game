package Cartoon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class models the application to start the cartoon. It creates a stage
 * for everything to go in and initializes the top-level class, PaneOrganizer,
 * which organizes the various panes and initializes the cartoon.
 */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Create top-level object, set up the scene, and show the stage here.
        stage.setTitle("Fireworks!");
        stage.show();

        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot());
        stage.setScene(scene);
    }

    public static void main(String[] argv) {
        launch(argv);
    }
}
