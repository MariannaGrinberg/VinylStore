import java.time.LocalDate;
import java.util.Date;

public class Employee extends User {

	private Date startWorkingDate;
	
	public Employee(String ID, String username, String password, String firstName, String lastName, Address address,
			String phoneNumber, String email, Date startWorkingDate) throws InvalidUserName ,IlegalPassword {
		super(ID, username, password, firstName, lastName, address, phoneNumber, email);
		setStartWorkingDate(startWorkingDate);
	}
	
	@Override
	public String toString() {
		
		return this.getClass().getSimpleName() + super.toString() + "startWorkingDate=" + startWorkingDate + "]";
	
	}

	public Date getStartWorkingDate() {
		return startWorkingDate;
	}

	
	public void setStartWorkingDate(Date startWorkingDate) {
		this.startWorkingDate = startWorkingDate;
	}

}
