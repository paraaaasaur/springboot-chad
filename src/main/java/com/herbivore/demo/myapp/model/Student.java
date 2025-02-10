package com.herbivore.demo.myapp.model;

import com.herbivore.demo.myapp.model.enums.OS;

import java.util.EnumSet;

import static com.herbivore.demo.myapp.model.enums.OS.LINUX;

public class Student {

	private String firstName;

	private String lastName;

	private String country;

	private String favoriteLanguage;

	private EnumSet<OS> favoriteSystems;

	public Student(String firstName, String lastName, String country, String favoriteLanguage, OS... favoriteSystems) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.favoriteLanguage = favoriteLanguage;
		this.favoriteSystems = favoriteSystems.length > 0?
				EnumSet.of(favoriteSystems[0], favoriteSystems) :
				EnumSet.noneOf(OS.class);
	}

	public Student() {}

	public Student(String firstName, String lastName) {
		this(firstName, lastName, "Greenland", "Java", LINUX);
	}

	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}

	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}

	public String getCountry() {return country;}
	public void setCountry(String country) {this.country = country;}

	public String getFavoriteLanguage() {return favoriteLanguage;}
	public void setFavoriteLanguage(String favoriteLanguage) {this.favoriteLanguage = favoriteLanguage;}

	public EnumSet<OS> getFavoriteSystems() {return favoriteSystems;}
	public void setFavoriteSystems(EnumSet<OS> favoriteSystems) {this.favoriteSystems = favoriteSystems;}

	@Override
	public String toString() {
		return "Student{" +
			   "firstName='" + firstName + '\'' +
			   ", lastName='" + lastName + '\'' +
			   ", country='" + country + '\'' +
			   ", favoriteLanguage='" + favoriteLanguage + '\'' +
			   ", favoriteSystems=" + favoriteSystems +
			   '}';
	}
}
