package Classes;
import java.io.Serializable;

import enums.City;

public class Address implements Serializable {
	


	private static final long serialVersionUID = -5779274771630847263L;
	
	// Attributes
	
	City city;
	String street;
	String number;
	String zipCode;
	
	
	// Constructor
	
	public Address(City city, String street, String number, String zipCode) {
		
		setCity(city);
		setStreet(street);
		setNumber(number);
		setZipCode(zipCode);
		
	}
	
	
	// Setters
	
	private void setZipCode(String zipCode) {
		this.zipCode = zipCode;
		
	}

	private void setNumber(String number) {
		this.number = number;
	}

	private void setStreet(String street) {
		this.street = street;
	}

	private void setCity(City city) {
		this.city = city;
	}
	
	
	// Getters
	
	public String getZipCode() {
		return this.zipCode;
	}
	
	public String getNumber() {
		return this.number;
	}
	
	public String getStreet() {
		return this.street;
	}
	
	public City getCity() {
		return this.city;
	}
	
	
	// toString
	
	@Override
	public String toString() {
		return "Address [city=" + city + ", street=" + street + ", number=" + number + ", zipCode=" + zipCode + "]";
	}
	
}
