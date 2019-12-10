import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Customer extends User implements Comparable<Customer>{
	
	private ArrayList<Order> orders;

	public Customer(String ID, String username, String password, String firstName, String lastName, 
					 Address address,String phoneNumber, String email, LocalDate date)  
							 throws InvalidUserName, IlegalPassword {
	
		super(ID, username, password, firstName, lastName, address, phoneNumber, email);
		orders = new ArrayList<>();
	
	}
	
	public ArrayList<Order> getOrders() {
		return orders;
	}


	public void addOrder(Order order) {
		this.orders.add(order);
	}

	
	@Override
	public String toString() {
		
		return this.getClass().getSimpleName() + super.toString() + "]";
	
	}

	@Override
	public int compareTo(Customer c) {
		
		return this.firstName.compareTo(c.firstName);
	}
	
	
	

}
