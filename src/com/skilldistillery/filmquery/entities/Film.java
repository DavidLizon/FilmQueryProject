package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {

	// No arg-ctor
	public Film() {
	}

	// ctor w/args
	public Film(int filmId, String title, String description, int releaseYear, String languageName, int rentalDuration,
			double rentalRate, int length, double replacementCost, String rating, String specialFeatures) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageName = languageName;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}


	private int filmId;
	private String title;
	private String description;
	private int releaseYear;
	private String languageName;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> actors;
	

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
	
	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}


	@Override
	public int hashCode() {
		return Objects.hash(description, filmId, actors, languageName, length, rating, releaseYear, rentalDuration,
				rentalRate, replacementCost, specialFeatures, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(description, other.description) && filmId == other.filmId
				&& Objects.equals(actors, other.actors) && languageName == other.languageName && length == other.length
				&& Objects.equals(rating, other.rating) && releaseYear == other.releaseYear
				&& rentalDuration == other.rentalDuration
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
	}

//	title, year, rating, and description are displayed
	public String stringReturnFromSearchByFilmID() {
		String actorList = ""; 
		for (Actor actor : actors) {
			actorList += actor; 
		}
		
		
		return "Title: " + title + "\nLanguage: " + languageName + "\nReleased: " + releaseYear +  "\nRating: " + rating + "\nDescription: " 
				+ description + " " + actorList + "\n"
				+ "==================================================================================================";
	}
	
	
	@Override
	public String toString() {
		if(actors != null) {
		return "ID: " + filmId + "| Title: " + title + "| Description: " + description + "| Release Year: "
				+ releaseYear + "| LanguageId: " + languageName + "| Rental Duration: " + rentalDuration + "| Rental Rate: "
				+ rentalRate + "| Length: " + length + "| Replacement Cost: " + replacementCost + "| Rating: " + rating
				+ "| Special features: " + specialFeatures + "Actors: " + actors + "\n";
		} else {
			return "Film ID: " + filmId + "| Title: " + title + "| Description: " + description + "| Release Year: "
					+ releaseYear + "| LanguageId: " + languageName + "| Rental Duration: " + rentalDuration + "| Rental Rate: "
					+ rentalRate + "| Length: " + length + "| Replacement Cost: " + replacementCost + "| Rating: " + rating
					+ "| Special features: " + specialFeatures + "\n";
		}
	}

	
}
