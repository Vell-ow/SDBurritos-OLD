package edu.miracosta.cs112.mpaulding.view;

import edu.miracosta.cs112.mpaulding.controller.Controller;
import edu.miracosta.cs112.mpaulding.model.Burrito;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * The <code>MainScene</code> represents the very first scene for the SD Burritos application.
 * It contains filters for location (store), neighborhood, rating and price.
 * The <code>MainScene</code> also allows for a user to add a new burrito review or modify
 * existing reviews.
 */
public class MainScene extends Scene {

    private ComboBox<String> locationsCB;
    private ComboBox<String> neighborhoodsCB;
    private Slider minRatingSlider = new Slider(0.0, 5.0, 0.0);
    private Slider maxPriceSlider = new Slider(0.0, 15.0, 15.0);
    private ListView<Burrito> burritosLV = new ListView<>();
    private Button modifyBurritoButton = new Button("* Modify Burrito");
    private Button addBurritoButton = new Button("+ Add Burrito");

    private Controller controller = Controller.getInstance();
    private ObservableList<Burrito> burritosList;
    private Burrito selectedBurrito;

    /**
     * Constructs a new <code>MainScene</code>, representing the very first scene for the SD Burritos application.
     * It contains filters for location (store), neighborhood, rating and price.
     * The <code>MainScene</code> also allows for a user to add a new burrito review or modify
     * existing reviews.
     */
    public MainScene() {
        super(new GridPane(), 850, 400);

        burritosList = controller.getAllBurritos();
        burritosLV.setItems(burritosList);
        burritosLV.setPrefWidth(850);

        //TODO: Add a listener on the selected item property of the burritosLV
        burritosLV.getSelectionModel().selectedItemProperty().addListener((observableValue, burrito, t1) -> selectBurrito(t1));
        //TODO: In the changed(...) method, assign the selected burrito to the new value
        //TODO: If the selected burrito is null (no burrito selected), then disable the modifyBurritoButton
        //TODO: Otherwise, it should be enabled.


        locationsCB = new ComboBox<>(controller.getDistinctLocations());
        locationsCB.setOnAction(e -> filter());

        neighborhoodsCB = new ComboBox<>(controller.getDistinctNeighborhoods());
        neighborhoodsCB.setOnAction(e -> filter());

        minRatingSlider.setShowTickMarks(true);
        minRatingSlider.setShowTickLabels(true);
        minRatingSlider.setMajorTickUnit(1.0f);
        minRatingSlider.setBlockIncrement(1.0f);
        minRatingSlider.setOnMouseDragged(e -> filter());

        maxPriceSlider.setShowTickMarks(true);
        maxPriceSlider.setShowTickLabels(true);
        maxPriceSlider.setMajorTickUnit(5.0f);
        maxPriceSlider.setBlockIncrement(1.0f);
        maxPriceSlider.setOnMouseDragged(e -> filter());

        modifyBurritoButton.setDisable(true);
        modifyBurritoButton.setOnAction(event -> modifyBurrito());
        addBurritoButton.setOnAction(event -> addBurrito());
        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));
        pane.add(new Label("Filters:"), 0, 0);
        pane.add(new Label("Location:"), 0, 1);
        pane.add(locationsCB, 1, 1);

        pane.add(new Label("Neighborhood:"), 0, 2);
        pane.add(neighborhoodsCB, 1, 2);

        pane.add(new Label("Min Rating:"), 0, 3);
        pane.add(minRatingSlider, 1, 3);

        pane.add(new Label("Max Price $"), 0, 4);
        pane.add(maxPriceSlider, 1, 4);

        pane.add(addBurritoButton, 0, 5, 2, 1);
        pane.add(burritosLV, 0, 6, 2, 1);
        pane.add(modifyBurritoButton, 0, 7, 2, 1);

        this.setRoot(pane);
    }

    private void selectBurrito(Burrito t1) {
        selectedBurrito = t1;
        modifyBurritoButton.setDisable(selectedBurrito == null);
    }

    /**
     * Allows the user to modify an existing burrito by navigating to the ModifyBurrito scene.
     * However, if the selected burrito is null, just return (do nothing)
     * Make sure to update the display (list view and combo boxes) after modifying the burrito.
     */
    private void modifyBurrito() {
        //TODO: If the selected burrito is null, just return
        if(selectedBurrito == null)
        {
            return;
        }
        //TODO: Otherwise, use the ViewNavigator to load the ModifyScene
        ViewNavigator.loadScene("Modify Burrito", new ModifyScene(this, selectedBurrito));
        //TODO: Update the display when done.
        updateDisplay();

    }

    /**
     * Allows the user to add a new burrito review by navigating to the AddBurrito scene.
     * Make sure to update the display (list view and combo boxes) after adding the new burrito.
     */
    private void addBurrito() {
        ViewNavigator.loadScene("Add Burrito", new AddScene(this));
        //TODO: Use the ViewNavigator to load the AddScene
        updateDisplay();
        //TODO: Update the display when done.

    }

    /**
     * Updates the display after adding/modifying a burrito.  The idea being, if the user enters a new
     * location or neighborhood, it should appear in the appropriate combo box.  Also, the list view
     * should refresh to show the new/modified burrito.
     */
    private void updateDisplay()
    {
        //TODO: Refresh the burrito list view
        burritosLV.refresh();
        //TODO: Set the items of the locations combo box to get the distinct locations (again)
        locationsCB.setItems(controller.getDistinctLocations());
        //TODO: Set the items of the neighborhoods combo box to get the distinct neighborhoods(again)
        neighborhoodsCB.setItems(controller.getDistinctNeighborhoods());
    }

    /**
     * Reads the necessary information (location, neighborhood, min rating and max price)
     * in order to send the criteria to the Controller for filtering.
     */
    private void filter() {
        String location = locationsCB.getSelectionModel().getSelectedItem();
        String neighborhood = neighborhoodsCB.getSelectionModel().getSelectedItem();
        double minRating = minRatingSlider.getValue();
        double maxPrice = maxPriceSlider.getValue();

        burritosList = controller.filter(location, neighborhood, minRating, maxPrice);
        burritosLV.setItems(burritosList);
    }
}
