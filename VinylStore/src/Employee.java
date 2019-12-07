import java.time.LocalDate;
import java.util.Date;

public class Employee extends User {

	private LocalDate startWorkingDate;
	
	public Employee(int ID, String username, String password, String firstName, String lastName, Address address,
			String phoneNumber, String email, LocalDate startWorkingDate) throws InvalidUserName ,IlegalPassword {
		super(ID, username, password, firstName, lastName, address, phoneNumber, email);
		setStartWorkingDate(startWorkingDate);
	}
	
	@Override
	public String toString() {
		
		return this.getClass().getSimpleName() + super.toString() + "startWorkingDate=" + startWorkingDate + "]";
	
	}

	public LocalDate getStartWorkingDate() {
		return startWorkingDate;
	}

	
	public void setStartWorkingDate(LocalDate startWorkingDate) {
		this.startWorkingDate = startWorkingDate;
	}

}
