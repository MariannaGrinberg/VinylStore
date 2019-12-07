import java.time.LocalDate;
import java.util.Date;

public class Customer extends User {

	public Customer(int ID, String username, String password, String firstName, String lastName, 
			Address address,String phoneNumber, String email) throws InvalidUserName, IlegalPassword {
	
		super(ID, username, password, firstName, lastName, address, phoneNumber, email);
	
	
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + super.toString() + "]";
	}

	

	
	
	

}
