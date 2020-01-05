package Classes;
import java.io.Serializable;

import Exceptions.IlegalPassword;
import Exceptions.InvalidUserName;

public abstract class User implements Serializable {
	
	private static final long serialVersionUID = 6770373975976947387L;

	protected String ID; 
	protected String username; 
	protected String password; 
	protected String firstName;
	protected String lastName; 
	protected Address address; 
	protected String phoneNumber; 
	protected String email; 
	

	public User(String ID, String username, String password, String firstName,
					String lastName, Address address, String phoneNumber, String email) throws InvalidUserName,IlegalPassword {
		
		setID(ID);
		setUsername(username);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setPhoneNumber(phoneNumber);
		setEmail(email);
		
	}

	// Getters
	
	public String getID() {
		return ID;
	}

	
	public String getUsername() {
		return username;
	}
	
	
	public String getPassword() {
		return password;
	}

	
	public String getFirstName() {
		return firstName;
	}
	
	
	public String getLastName() {
		return lastName;
	}
	
	
	public Address getAddress() {
		return address;
	}
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	
	public String getEmail() {
		return email;
	}

	// Setters
	
	public void setID(String ID) {
		this.ID = ID;
	}


	public void setUsername(String username) throws InvalidUserName {
		if (username.length() >= 2)
			this.username = username;
		
		else throw new InvalidUserName("User Name must be at least 2 lleters");
	}


	public void setPassword(String password) throws IlegalPassword {
		if (password.length() >= 6)
			this.password = password;
		
		else throw new IlegalPassword("Password must be at least Length 6.");
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return " [ID=" + ID + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", phoneNumber=" + phoneNumber + ", email="
				+ email;
	}
	
	

}
