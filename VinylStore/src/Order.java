import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Order implements Comparable<Order>{

	
	// Attributes
	
	static int num = 0;
	private int orderID;
	private Employee employee;
	private Customer customer;
	private ArrayList<Vinyl> products;
	private double totalPrice;
	private LocalDate orderDate;
	private LocalDate deliveryDate = null;
	private Address shipAddress;
	private int discount;

	
	// Constructor
	
	public Order(Employee employee, Customer customer, 
			LocalDate orderDate, int discount)  throws IllegalVinylPrice, IlegalDate {
		
		products = new ArrayList<>(); 
		setOrderID(orderID);
		setEmployee(employee);
		setCustomer(customer);
		setProducts(products);
		setDeliveryDate(orderDate);
		setOrderDate(orderDate);
		setDiscount(discount);
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
	
	public void setDiscount(int discount) {
		this.discount = discount;
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
	
	public double getTotalPrice()  throws IllegalVinylPrice{
		
		for (Vinyl product : this.products) {
			this.totalPrice += product.getPrice();
		}
		
		if (this.discount > 0) {
			this.totalPrice -= (this.totalPrice * this.discount/100);
		}
		
		if(totalPrice < 0 || discount < 0 )
			throw new IllegalVinylPrice("Price Must Be Greater Than 0");
		
		return totalPrice;
	}
	
	public double getDiscount() {
		return (double) discount/100 ; 
	}
	
	public void addProducts(Vinyl vinyl) {
		this.products.add(vinyl);
		
	}
	
	
	@Override
	public String toString() {
		
		Collections.sort(this.products);
		
		String products = "";
		
		for(int i = 0; i < this.products.size(); i++) {
			products += " "+this.products.get(i).getClass().getSimpleName()+"[" + this.products.get(i).getVinylID()+", "+ this.products.get(i).getName()+", "+this.products.get(i).getPrice()+"$]";

		}
		
		
		try {
			return "Order [orderID=" + this.orderID + ", customerID=" + this.customer.getID() + ", employeeID=" + this.employee.getID()
					+ ", orderDate=" + this.orderDate + ", deliveryDate=" + this.deliveryDate + ", shipAddress=" + this.shipAddress +", products=" + products + ", totalPrice=" + this.getTotalPrice() + "$]";
		} catch (IllegalVinylPrice e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;

	}




	@Override
	public int compareTo(Order o) {
		
		if(this.orderDate.isBefore(o.orderDate) || this.orderDate.equals(o.orderDate))
			return 1; 
		
		else return -1; 
	}
	
}
