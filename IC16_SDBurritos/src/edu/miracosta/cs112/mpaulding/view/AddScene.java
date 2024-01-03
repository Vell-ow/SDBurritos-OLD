package edu.miracosta.cs112.mpaulding.view;

import edu.miracosta.cs112.mpaulding.controller.Controller;
import edu.miracosta.cs112.mpaulding.model.Burrito;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * The <code>AddScene</code> class allows a user to enter the information to add a new Burrito to the collection
 * of reviews.  Some fields are validated (must be provided) before the user is able to save the information.
 *
 * @author Michael Paulding
 * @version 1.1
 */
public class AddScene extends Scene {
    private Scene mPrevScene;

    private Controller controller = Controller.getInstance();
    private ObservableList<Burrito> allBurritosList;

    private TextField locationTF = new TextField();
    private Label locationLabel = new Label("Location is required.");

    private TextField nameTF = new TextField();
    private Label nameLabel = new Label("Name is required.");

    private TextField neighborhoodTF = new TextField();
    private Label neighborhoodLabel = new Label("Neighborhood is required.");

    private TextField priceTF = new TextField();
    private Label priceLabel = new Label("Price is required.");

    private TextField ratingTF = new TextField();
    private TextField notesTF = new TextField();

    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");


    /**
     * In the <code>AddScene</code>, a user is able to add a new Burrito to the collection of reviews.
     * Location, name, neighborhood and price are required fields (error message will appear if not provided)
     * Rating and notes are optional.
     * @param prevScene A reference to the MainScene so that after saving (or canceling), user
     *                  is returned back to the main scene.
     */
    public AddScene(Scene prevScene) {
        super(new GridPane(), 450, 250);
        mPrevScene = prevScene;

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        pane.add(new Label("Location:"), 0, 0);
        pane.add(locationTF, 1, 0);
        pane.add(locationLabel, 2, 0);
        locationLabel.setTextFill(Color.RED);
        locationLabel.setVisible(false);

        pane.add(new Label("Name:"), 0, 1);
        pane.add(nameTF, 1, 1);
        pane.add(nameLabel, 2, 1);
        nameLabel.setTextFill(Color.RED);
        nameLabel.setVisible(false);

        pane.add(new Label("Neighborhood:"), 0, 2);
        pane.add(neighborhoodTF, 1, 2);
        pane.add(neighborhoodLabel, 2, 2);
        neighborhoodLabel.setTextFill(Color.RED);
        neighborhoodLabel.setVisible(false);

        pane.add(new Label("Price $"), 0, 3);
        pane.add(priceTF, 1, 3);
        pane.add(priceLabel, 2, 3);
        priceLabel.setTextFill(Color.RED);
        priceLabel.setVisible(false);

        pane.add(new Label("Rating [0-5]:"), 0, 4);
        pane.add(ratingTF, 1, 4);

        pane.add(new Label("Notes:"), 0, 5);
        pane.add(notesTF, 1, 5);

        pane.add(cancelButton, 0, 6);
        pane.add(saveButton, 1, 6);

        saveButton.setOnAction(event -> save());
        cancelButton.setOnAction(event -> goBackToPrevScene());
        this.setRoot(pane);
    }


    /**
     * Reads each of the text fields and validates them.  If the required fields (location, name, neighborhood, price)
     * are empty, user will see an error message (red text) next to the text field and will be prevented from saving.
     * Otherwise a new Burrito object will be instantiated and added to the list of all burritos.
     */
    private void save() {
        //
        String location, name, neighborhood, notes;
        double price=0.0, rating=0.0;

        location = locationTF.getText();
        locationLabel.setVisible(location.isEmpty());

        name = nameTF.getText();
        nameLabel.setVisible(name.isEmpty());

        neighborhood = neighborhoodTF.getText();
        neighborhoodLabel.setVisible(neighborhood.isEmpty());

        notes = notesTF.getText();  // Notes are optional, can be empty

        try {
            price = Double.parseDouble(priceTF.getText());
            if (price < 0.0)
                priceLabel.setVisible(true);
        }
        catch (NumberFormatException e) {  priceLabel.setVisible(true); }

        try {
            rating = Double.parseDouble(ratingTF.getText());
        }
        catch (NumberFormatException e) {
            // Quietly ignored, since rating can be empty
        }

        priceLabel.setVisible(priceTF.getText().isEmpty());

        //TODO: If any of the error labels are visible, return.
        if(locationLabel.isVisible() || nameLabel.isVisible() || neighborhoodLabel.isVisible() || priceLabel.isVisible())
        {
            return;
        }
        //TODO: Otherwise, instantiate a new Burrito object and add it to the list of all burritos.
        controller.getAllBurritos().add(new Burrito(location, name, neighborhood, price, rating, notes));
        //TODO: Then go back to the previous scene.
        goBackToPrevScene();

    }

    /**
     * Navigates back to the previous scene without having to create a new one.
     */
    private void goBackToPrevScene() {
        //TODO: Navigate back to the previous scene (e.g. MainScene)
        ViewNavigator.loadScene("San Diego Burritos", mPrevScene);
    }

}
