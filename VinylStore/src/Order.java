import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Order implements Comparable<Order>{

	
	// Attributes
	
	static int num = 0;
	private int orderID;
	private Employee employee = null;
	private Customer customer;
	private ArrayList<Vinyl> products;
	private double totalPrice;
	private LocalDate orderDate;
	private LocalDate deliveryDate = null;
	private Address shipAddress;

	
	// Constructor
	
	public Order(Customer customer, LocalDate orderDate)  throws IllegalVinylPrice, IlegalDate {
		
		products = new ArrayList<>(); 
		setOrderID(orderID);
		setCustomer(customer);
		setProducts(products);
		setOrderDate(orderDate);
		setDeliveryDate(orderDate);
		setShipAddress();
	}
	
	


	// Setters
	
	public void setOrderID(int orderID) {
		this.orderID = ++num;
	}

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
		
		if(deliveryDate.isBefore(this.orderDate))
			throw new IlegalDate("Delivery Date Must Be After Order Date"); 
			
		else this.deliveryDate = deliveryDate;
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
	
	public void addProducts(Vinyl vinyl) {
		this.products.add(vinyl);
		
	}
	
	
	@Override
	public String toString() {
		
		if (this.products.isEmpty()) {
			return "There is no Products in this store";
		}
		else {
			
		
		Collections.sort(this.products);
		
		String products = "";
		
		for(int i = 0; i < this.products.size(); i++) {
			products += " "+this.products.get(i).getClass().getSimpleName()+"[" + this.products.get(i).getVinylID()+", "+ this.products.get(i).getName()+", "+this.products.get(i).getPrice()+"$]";

		}
		
		
		try {
			return "Order [orderID=" + this.orderID + ", customerID=" + this.customer.getID()
				+ ", orderDate=" + this.orderDate + ", deliveryDate=" + this.deliveryDate 
				+ ", shipAddress=" +this.customer.getAddress() +", products=" + products 
				+ ", totalPrice=" + this.getTotalPrice() + "$]";
		} catch (IllegalVinylPrice e) {
			e.printStackTrace();
		}
		return products;

		}
	}

	@Override
	public int compareTo(Order o) {
		
		if(this.orderDate.isBefore(o.orderDate) || this.orderDate.equals(o.orderDate))
			return 1; 
		
		else return -1; 
	}
	
}
