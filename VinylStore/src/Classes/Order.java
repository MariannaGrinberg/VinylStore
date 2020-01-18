package Classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import Exceptions.IlegalDate;
import Exceptions.IllegalVinylPrice;

public class Order implements Comparable<Order>, Serializable {

	
	// Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7767864006248193881L;
	
	transient static int num = 0;
	private int orderID;
	private Employee employee = null;
	private Customer customer;
	private ArrayList<Vinyl> products;
	private double totalPrice;
	private LocalDate orderDate;
	private LocalDate deliveryDate = null;
	private Address shipAddress;

	
	// Constructor
	
	public Order(int ID, Customer customer, LocalDate orderDate)  throws IllegalVinylPrice, IlegalDate {
		
		
		this.orderID = ID;
		products = new ArrayList<>(); 
		setCustomer(customer);
		setProducts(products);
		setOrderDate(orderDate);
		setShipAddress();
		
	}
	
	


	// Setters
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setProducts(ArrayList<Vinyl> products) {
		this.products = products;
	}

	public void setShipAddress() {
		this.shipAddress = customer.getAddress();
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) throws IlegalDate{
		
		this.deliveryDate = deliveryDate;
	}
	
	
	// Getters
	
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public Address getShipAddress() {
		return shipAddress;
	}

	public int getOrderID() {
		return orderID;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public ArrayList<Vinyl> getProducts() {
		return products;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}
	
	public double getTotalPrice()  throws IllegalVinylPrice {
		this.totalPrice = 0;
		for (Vinyl product : this.products) {
			this.totalPrice += product.getPrice();
		}
		
		if (this.totalPrice > 0) {
			return totalPrice;
			
		}
		else {
			throw new IllegalVinylPrice("Invalid Price!");
		}
	}
	
	public String getStatus() {
		if(this.deliveryDate == null) {
			return "In Proccess";
		}
		else {
			return "Delivered";
		}
	}
	
	public void addProducts(Vinyl vinyl) {
		this.products.add(vinyl);
		
	}
	
	
	@Override
	public String toString() {

		if (this.products.isEmpty()) {
			return "There is no Products in this Order";
		}
		else {


			Collections.sort(this.products);

			String str = "";
			ArrayList<Integer> productIDs = new ArrayList<>();

			for(Vinyl product : this.products) {
				if(!(productIDs.contains(product.getVinylID()))) {
					productIDs.add(product.getVinylID());
					str += "#" + product.getVinylID() + " - " + product.getName() + " - " + product.getPrice() + "$     X " + getNumberOf(product.getVinylID()) + "\n";
				}
				else {
					continue;
				}
			}


			return str;
		}
	}

	

	@Override
	public int compareTo(Order o) {
		
		if(this.orderDate.isBefore(o.orderDate) || this.orderDate.equals(o.orderDate))
			return 1; 
		
		else return -1; 
	}
	
	private int getNumberOf(int ID) {
		
		int count = 0;
		
		for(Vinyl product : this.products) {
			if(product.getVinylID() == ID)
				count++;
		}
		
		return count;
		
	}
	
	public void setID(int ID) {
		
		this.orderID = ID; 
	}
	
}
