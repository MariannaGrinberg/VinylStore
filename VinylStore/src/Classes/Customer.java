package Classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import Exceptions.IlegalPassword;
import Exceptions.InvalidUserName;

public class Customer extends User implements Comparable<Customer>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2178719367713954180L;
	private ArrayList<Vinyl> cart;

	public Customer(String ID, String username, String password, String firstName, String lastName, 
					 Address address,String phoneNumber, String email)  
							 throws InvalidUserName, IlegalPassword {
	
		super(ID, username, password, firstName, lastName, address, phoneNumber, email);
		this.cart = new ArrayList<>();
	
	}
	
	public String getID() {
		return this.ID;
	}
	
	public ArrayList<Vinyl> getCart() {
		return this.cart;
	}
	
	public void addToCart(Vinyl vinyl) {
		this.cart.add(vinyl);
	}
	
	public void removeFromCart(int productIndex) {
		this.cart.remove(productIndex);
	}
	
	public void clearCart() {
		this.cart = new ArrayList<>();
	}

	
	@Override
	public String toString() {
		
		return  super.toString();
	
	}

	@Override
	public int compareTo(Customer c) {
		
		return this.firstName.compareTo(c.firstName);
	}
	
	
	

}
