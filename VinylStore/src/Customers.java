import java.util.Date;

public class Customers extends User {
	
	private Date startWorkingDate;

	public Customers(int ID, String username, int password, String firstName, String lastName, 
			Address address,int phoneNumber, String email, Date startWorkingDate) {
	
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
