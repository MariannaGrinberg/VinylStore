package Classes;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import Exceptions.IlegalPassword;
import Exceptions.InvalidUserName;

public class Employee extends User implements Comparable<Employee>, Serializable  {

	private static final long serialVersionUID = -47294228207066246L;

	private LocalDate startWorkingDate;
	
	public Employee(String ID, String username, String password, String firstName, String lastName, Address address,
			String phoneNumber, String email, LocalDate startWorkingDate) throws InvalidUserName ,IlegalPassword {
		super(ID, username, password, firstName, lastName, address, phoneNumber, email);
		setStartWorkingDate(startWorkingDate);
	}
	
	@Override
	public String toString() {	
		return this.getClass().getSimpleName() + ":" + super.toString();	
	}

	public LocalDate getStartWorkingDate() {
		return startWorkingDate;
	}

	
	public void setStartWorkingDate(LocalDate startWorkingDate) {
		this.startWorkingDate = startWorkingDate;
	}

	@Override
	public int compareTo(Employee e) {
		
		return this.firstName.compareTo(e.firstName);
	
	}

}