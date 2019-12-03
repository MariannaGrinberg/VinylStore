import java.util.Date;

public class Customer extends User {
	
	private Date startWorkingDate;

	public Customer(int ID, String username, String password, String firstName, String lastName, 
			Address address,int phoneNumber, String email, Date startWorkingDate) throws InvalidUserName, IlegalPassword {
	
		super(ID, username, password, firstName, lastName, address, phoneNumber, email);
		setStartWorkingDate(startWorkingDate);
	
	}

	public Date getStartWorkingDate() {
		return startWorkingDate;
	}

	
	public void setStartWorkingDate(Date startWorkingDate) {
		this.startWorkingDate = startWorkingDate;
	}

	
	@Override
	public String toString() {
		
		return this.getClass().getSimpleName() + super.toString() + "startWorkingDate=" + startWorkingDate + "]";
	
	}
	
	
	

}
