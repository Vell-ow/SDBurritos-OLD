package edu.miracosta.cs112.mpaulding.view;

import edu.miracosta.cs112.mpaulding.controller.Controller;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The <code>View</code> class represents the user interface for the application.  It initializes the
 * primary stage (window) and sets a custom burrito icon (see code below), then instructs the ViewNavigator
 * to load the very first scene (the MainScene).
 *
 * @author Michael Paulding
 * @version 1.2
 */
public class View extends Application {

	/**
	 * Starts the application by setting the stage (window) with a custom burrito icon, then navigating
	 * to the first scene (canvas), which happens to be the MainScene for this application.
	 * @param primaryStage The primary stage (window)
	 * @throws Exception if one occurs during startup.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Changing the icon (top left of stage) to custom burrito icon
		// Burrito icon credit goes to:
		// <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a>
		// from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images\\burrito.png")));
		ViewNavigator.setStage(primaryStage);
		ViewNavigator.loadScene("San Diego Burritos", new MainScene());
	}

	/**
	 * Stop is called whenever the application shuts down (e.g. user closes the window).
	 * In this case we request the Controller to initiate the saving of all burrito data to the binary file.
	 * @throws Exception if one occurs during shutdown.
	 */
	@Override
	public void stop() throws Exception {
		Controller.getInstance().saveData();
	}

	/**
	 * The entry point to this JavaFX application.  Application.launch will eventually make a call
	 * to the start() method.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);

	}

}
