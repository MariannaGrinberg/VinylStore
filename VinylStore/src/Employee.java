
public class Employee extends User {

	
	
	public Employee(int ID, String username, String password, String firstName, String lastName, Address address,
			int phoneNumber, String email) throws InvalidUserName ,IlegalPassword {
		super(ID, username, password, firstName, lastName, address, phoneNumber, email);
		
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + super.toString() + "]";
	}
	
	

}
