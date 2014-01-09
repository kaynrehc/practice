package com.enthusys.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * User: mchernyak
 * Date: 1/7/14
 * Time: 1:33 PM
 */
public class User implements Comparable, Serializable {
	private static final Logger logger = LoggerFactory.getLogger(UserPlay.class);
	private static Integer baseNumber;
	private Integer number;
	private String gender;
	private String givenName;
	private String middleInitial;
	private String surname;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private String emailAddress;
	private String username;
	private String password;

	/**
	 * Base number plus the line number read from the file make up the User 'unique' number
	 *
	 * @param baseNumber static variable to hold the current base number
	 */
	public static void setBaseNumber(Integer baseNumber) {
		User.baseNumber = baseNumber;
	}

	@Override
	public int compareTo(Object rhs) {
		int rv = 0;

		User user = (User) rhs;
		if (this == user)
			return 0;

		rv = this.username.compareTo(user.username);
		if (rv != 0)
			return rv;

		rv = this.surname.compareTo(user.surname);
		if (rv != 0)
			return rv;

		rv = this.givenName.compareTo(user.givenName);
		if (rv != 0)
			return rv;

		rv = this.middleInitial.compareTo(user.middleInitial);
		if (rv != 0)
			return rv;

		rv = this.streetAddress.compareTo(user.streetAddress);
		if (rv != 0)
			return rv;

		return (this.number - user.number);
	}

	public User(String csvline) {
		String[] columns = csvline.split(",");

		number = baseNumber + Integer.parseInt(columns[0]);
		gender = columns[1];
		givenName = columns[2];
		middleInitial = columns[3];
		surname = columns[4];
		streetAddress = columns[5];
		city = columns[6];
		state = columns[7];
		zipCode = columns[8];
		country = columns[9];
		emailAddress = columns[10];
		username = columns[11];
		password = columns[12];
	}

	@Override
	public boolean equals(Object rhs) {
		if (this == rhs) return true;
		if (rhs == null || getClass() != rhs.getClass()) return false;

		User user = (User) rhs;

		if (!city.equals(user.city)) return false;
		if (!country.equals(user.country)) return false;
		if (!emailAddress.equals(user.emailAddress)) return false;
		if (!gender.equals(user.gender)) return false;
		if (!givenName.equals(user.givenName)) return false;
		if (!middleInitial.equals(user.middleInitial)) return false;
		if (!number.equals(user.number)) return false;
		if (!password.equals(user.password)) return false;
		if (!state.equals(user.state)) return false;
		if (!streetAddress.equals(user.streetAddress)) return false;
		if (!surname.equals(user.surname)) return false;
		if (!username.equals(user.username)) return false;
		if (!zipCode.equals(user.zipCode)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = number.hashCode();
		result = 31 * result + gender.hashCode();
		result = 31 * result + givenName.hashCode();
		result = 31 * result + middleInitial.hashCode();
		result = 31 * result + surname.hashCode();
		result = 31 * result + streetAddress.hashCode();
		result = 31 * result + city.hashCode();
		result = 31 * result + state.hashCode();
		result = 31 * result + zipCode.hashCode();
		result = 31 * result + country.hashCode();
		result = 31 * result + emailAddress.hashCode();
		result = 31 * result + username.hashCode();
		result = 31 * result + password.hashCode();
		return result;
	}

	/**
	 * Include all of the User attributes in toString
	 *
	 * @return String object containing all relevant info about User instance
	 */
	@Override
	public String toString() {
		return "User{" +
				"number=" + number +
				", username='" + username + '\'' +
				", givenName='" + givenName + '\'' +
				", middleInitial='" + middleInitial + '\'' +
				", surname='" + surname + '\'' +
				", streetAddress='" + streetAddress + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", zipCode='" + zipCode + '\'' +
				", country='" + country + '\'' +
				", emailAddress='" + emailAddress + '\'' +
				", password='" + password + '\'' +
				", gender='" + gender + '\'' +
				'}';
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
