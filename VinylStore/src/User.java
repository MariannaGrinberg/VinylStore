
public abstract class User {
	
	protected int ID; 
	protected String username; 
	protected int password; 
	protected String firstName;
	protected String lastName; 
	protected Address address; 
	protected int phoneNumber; 
	protected String email; 
	

	public User(int ID, String username, int password, String firstName,
					String lastName, Address address, int phoneNumber, String email ) {
		
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
	
	public int getID() {
		return ID;
	}

	
	public String getUsername() {
		return username;
	}
	
	
	public int getPassword() {
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
	
	
	public int getPhoneNumber() {
		return phoneNumber;
	}

	
	public String getEmail() {
		return email;
	}

	// Setters
	
	public void setID(int ID) {
		this.ID = ID;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(int password) {
		this.password = password;
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


	public void setPhoneNumber(int phoneNumber) {
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
