package edu.miracosta.cs112.mpaulding.view;

import edu.miracosta.cs112.mpaulding.controller.Controller;
import edu.miracosta.cs112.mpaulding.model.Burrito;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * The <code>ModifyScene</code> class allows a user to modify information about an existing burrito.
 * Some fields are validated (must be provided) before the user is able to save the information.
 *
 * @author Michael Paulding
 * @version 1.1
 */
public class ModifyScene extends Scene {
    private Scene mPrevScene;
    private Burrito mBurritoToModify;

    private Controller controller = Controller.getInstance();
    private ObservableList<Burrito> allBurritosList;
    private Burrito selectedBurrito;

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

    private Button modifyButton = new Button("Modify");
    private Button cancelButton = new Button("Cancel");


    /**
     * In the <code>ModifyScene</code>, a user is able to modify an existing Burrito in the collection of reviews.
     * Location, name, neighborhood and price are required fields (error message will appear if not provided)
     * Rating and notes are optional.
     * @param prevScene A reference to the previous scene so that after saving (or canceling), user
     *                  is returned back to the previous scene (typically the main scene).
     */
    public ModifyScene(Scene prevScene, Burrito burritoToModify) {
        super(new GridPane(), 450, 250);
        mPrevScene = prevScene;
        mBurritoToModify = burritoToModify;

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        pane.add(new Label("Location:"), 0, 0);
        locationTF.setText(mBurritoToModify.getLocation());
        pane.add(locationTF, 1, 0);
        pane.add(locationLabel, 2, 0);
        locationLabel.setTextFill(Color.RED);
        locationLabel.setVisible(false);

        pane.add(new Label("Name:"), 0, 1);
        nameTF.setText(mBurritoToModify.getName());
        pane.add(nameTF, 1, 1);
        pane.add(nameLabel, 2, 1);
        nameLabel.setTextFill(Color.RED);
        nameLabel.setVisible(false);

        pane.add(new Label("Neighborhood:"), 0, 2);
        neighborhoodTF.setText(mBurritoToModify.getNeighborhood());
        pane.add(neighborhoodTF, 1, 2);
        pane.add(neighborhoodLabel, 2, 2);
        neighborhoodLabel.setTextFill(Color.RED);
        neighborhoodLabel.setVisible(false);

        pane.add(new Label("Price $"), 0, 3);
        priceTF.setText(String.valueOf(mBurritoToModify.getPrice()));
        pane.add(priceTF, 1, 3);
        pane.add(priceLabel, 2, 3);
        priceLabel.setTextFill(Color.RED);
        priceLabel.setVisible(false);

        pane.add(new Label("Rating [0-5]:"), 0, 4);
        ratingTF.setText(String.valueOf(mBurritoToModify.getRating()));
        pane.add(ratingTF, 1, 4);

        pane.add(new Label("Notes:"), 0, 5);
        notesTF.setText(String.valueOf(mBurritoToModify.getNotes()));
        pane.add(notesTF, 1, 5);

        pane.add(cancelButton, 0, 6);
        pane.add(modifyButton, 1, 6);
        // Now that the grid pane has been laid out, set it as the root of the scene.
        this.setRoot(pane);

        // Associate the button actions (click) to the methods below.
        modifyButton.setOnAction(event -> modify());
        cancelButton.setOnAction(event -> goBackToPrevScene());

    }

    /**
     * Reads each of the text fields and validates them.  If the required fields (location, name, neighborhood, price)
     * are empty, user will see an error message (red text) next to the text field and will be prevented from modifying.
     * Otherwise the burrito to modify will be set to all the values in the text fields.
     */
    private void modify() {
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

        if (locationLabel.isVisible() || nameLabel.isVisible() || neighborhoodLabel.isVisible() || priceLabel.isVisible())
            return;

        //TODO: If any of the error labels are visible, return.

        //TODO: Otherwise, use the mBurritoToModify object and set each of its instance variables
        mBurritoToModify.setLocation(location);
        mBurritoToModify.setName(name);
        mBurritoToModify.setPrice(price);
        mBurritoToModify.setRating(rating);
        mBurritoToModify.setNotes(notes);
        //TODO: to the values of the variables and go back to the previous scene.
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
