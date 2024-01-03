package edu.miracosta.cs112.mpaulding.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

/**
 * The <code>Burrito</code> class represents the San Diego burrito, with information such as its location (restaurant or stand),
 * name (name of the burrito itself), neighborhood (e.g. Oceanside), price, rating (out of 5) and notes (comments).
 *
 * @author Michael Paulding
 * @version 1.0
 */
public class Burrito implements Serializable {
// TODO: Ensure the Burrito class is Serializable so it may be written to a binary file.
	private String mLocation;
	private String mName;
	private String mNeighborhood;
	private double mPrice;
	private double mRating;
	private String mNotes;

	/**
	 * Constructs a new <code>Burrito</code> object.
	 * @param location The location of the restaurant or stand
	 * @param name The name of the burrito itself (e.g. California)
	 * @param neighborhood The neighborhood of the location (e.g. Oceanside)
	 * @param price The price of the burrito in dollars
	 * @param rating The rating (out of 5)
	 * @param notes The notes (comments) about the burrito
	 */
	public Burrito(String location, String name, String neighborhood, double price, double rating, String notes) {
		mLocation = location;
		mName = name;
		mNeighborhood = neighborhood;
		mPrice = price;
		mRating = rating;
		mNotes = notes;
	}

	/**
	 * Gets the location of the burrito.
	 * @return The location
	 */
	public String getLocation() {
		return mLocation;
	}

	/**
	 * Sets the location of the burrito.
	 * @param location The new location
	 */
	public void setLocation(String location) {
		mLocation = location;
	}

	/**
	 * Gets the name of the burrito.
	 * @return The name
	 */
	public String getName() {
		return mName;
	}

	/**
	 * Sets the name of the burrito.
	 * @param name The new name
	 */
	public void setName(String name) {
		mName = name;
	}

	/**
	 * Gets the neighborhood of the burrito.
	 * @return The neighborhood
	 */
	public String getNeighborhood() {
		return mNeighborhood;
	}

	/**
	 * Sets the neighborhood of the burrito.
	 * @param neighborhood The new neighborhood
	 */
	public void setNeighborhood(String neighborhood) {
		mNeighborhood = neighborhood;
	}

	/**
	 * Gets the price of the burrito.
	 * @return The price
	 */
	public double getPrice() {
		return mPrice;
	}

	/**
	 * Sets the price of the burrito.
	 * @param price The new price
	 */
	public void setPrice(double price) {
		mPrice = price;
	}

	/**
	 * Gets the rating of the burrito.
	 * @return The rating
	 */
	public double getRating() {
		return mRating;
	}

	/**
	 * Sets the rating of the burrito.
	 * @param rating The new rating
	 */
	public void setRating(double rating) {
		mRating = rating;
	}

	/**
	 * Gets the notes about the burrito.
	 * @return The notes
	 */
	public String getNotes() {
		return mNotes;
	}

	/**
	 * Sets the notes about the burrito.
	 * @param notes The new notes
	 */
	public void setNotes(String notes) {
		mNotes = notes;
	}

	@Override
	/**
	 * See #Object.equals
	 */
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Burrito burrito = (Burrito) o;
		return Double.compare(burrito.mPrice, mPrice) == 0 &&
				Double.compare(burrito.mRating, mRating) == 0 &&
				Objects.equals(mLocation, burrito.mLocation) &&
				Objects.equals(mName, burrito.mName) &&
				Objects.equals(mNeighborhood, burrito.mNeighborhood) &&
				Objects.equals(mNotes, burrito.mNotes);
	}

	@Override
	/**
	 * See #Object.hashCode
	 */
	public int hashCode() {
		return Objects.hash(mLocation, mName, mNeighborhood, mPrice, mRating, mNotes);
	}

	@Override
	/**
	 * See #Object.toString
	 */
	public String toString() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return "Burrito [" +
				"Location=" + mLocation  +
				", Name=" + mName +
				", Neighborhood=" + mNeighborhood  +
				", Price=" + currency.format(mPrice) +
				", Rating=[" + mRating + "/5]" +
				", Notes=" + mNotes +
				']';
	}
}
