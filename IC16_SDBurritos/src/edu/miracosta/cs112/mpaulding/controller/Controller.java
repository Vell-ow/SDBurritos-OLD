package edu.miracosta.cs112.mpaulding.controller;

import edu.miracosta.cs112.mpaulding.model.Model;
import edu.miracosta.cs112.mpaulding.model.Burrito;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The <code>Controller</code> is a Singleton object that relays all commands between the Model and View
 * (and vice versa).  There is only one Controller object, accessible by a call to the static getInstance()
 * method.
 *
 * @author Michael Paulding
 * @version 1.0
 */
public class Controller {

	private static Controller theInstance;
	private ObservableList<Burrito> mAllBurritosList;
	private ObservableList<Burrito> mFilteredBurritosList;

	private Controller() {}

	/**
	 * Gets the one instance of the Controller.
	 * @return The instance
	 */
	public static Controller getInstance() {
		if (theInstance == null) {
			theInstance = new Controller();
			// TODO: If the binary file has data, populate the mAllBurritosList from the binary file
			if(Model.binaryFileHasData())
			{
				theInstance.mAllBurritosList = Model.populateListFromBinaryFile();
			}

			theInstance.mFilteredBurritosList = FXCollections.observableArrayList();
		}
		return theInstance;
	}

	/**
	 * Gets the list of all burritos.
	 * @return The list of all burritos.
	 */
	public ObservableList<Burrito> getAllBurritos() {
		return mAllBurritosList;
	}

	/**
	 * Gets a list of the distinct locations, without duplicates, in sorted order.
	 * @return The list of distinct locations
	 */
	public ObservableList<String> getDistinctLocations()
	{
		ObservableList<String> locationsList = FXCollections.observableArrayList();
		locationsList.add(" ");
		for (Burrito b : mAllBurritosList)
			if (!locationsList.contains(b.getLocation()))
				locationsList.add(b.getLocation());
		FXCollections.sort(locationsList);
		return locationsList;
	}

	/**
	 * Gets a list of the distinct neighborhoods, without duplicates, in sorted order.
	 * @return The list of distinct neighborhoods
	 */
	public ObservableList<String> getDistinctNeighborhoods()
	{
		ObservableList<String> neighborhoodsList = FXCollections.observableArrayList();
		neighborhoodsList.add(" ");
		String neighborhood;
		for (Burrito b : theInstance.mAllBurritosList) {
			neighborhood = b.getNeighborhood().trim();
			if (!neighborhood.isEmpty() && !neighborhoodsList.contains(neighborhood))
				neighborhoodsList.add(neighborhood);
		}
		FXCollections.sort(neighborhoodsList);
		return neighborhoodsList;
	}

	/**
	 * Filters the list of burritos according to the specified criteria, including location (exact match),
	 * neighborhood (exact match), minimum rating and/or maximum price.
	 * @param location The location to match
	 * @param neighborhood The neighborhood to match
	 * @param minRating The minimum rating for the burrito
	 * @param maxPrice The maximum price for the burrito
	 * @return The filtered list of burritos, according to the specified criteria.
	 */
	public ObservableList<Burrito> filter(String location, String neighborhood, double minRating, double maxPrice)
	{		
		mFilteredBurritosList.clear();
		boolean meetsCriteria;

		for (Burrito b : mAllBurritosList)
		{
			meetsCriteria = true;
			if (location != null && !location.equalsIgnoreCase(" ") && !location.equalsIgnoreCase(b.getLocation()))
				meetsCriteria = false;
			if (neighborhood != null && !neighborhood.equalsIgnoreCase(" ") && !neighborhood.equalsIgnoreCase(b.getNeighborhood()))
				meetsCriteria = false;
			if (b.getRating() < minRating || b.getPrice() > maxPrice)
				meetsCriteria = false;
			if (meetsCriteria)
				mFilteredBurritosList.add(b);
		}
		return mFilteredBurritosList;
	}

	/**
	 * Makes a request for the model to save all the burrito data (the list of all burritos) to
	 * a persistent binary file.
	 */
	public void saveData() {
		Model.writeDataToBinaryFile(mAllBurritosList);
	}
}
