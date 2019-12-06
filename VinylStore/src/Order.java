import java.util.ArrayList;
import java.util.Date;

public class Order {

	
	// Attributes
	
	static int num = 0;
	private int orderID;
	private Employee employee;
	private Customer customer;
	private ArrayList<Vinyl> products;
	private float totalPrice;
	private Date orderDate;
	private Date deliveryDate = null;
	private Address shipAddress;
	private float discount;

	
	// Constructor
	
	public Order(Employee employee, Customer customer, ArrayList<Vinyl> products, 
				 Date orderDate, float discount) {
		
		products = new ArrayList<>(); 
		setOrderID(orderID);
		setEmployee(employee);
		setCustomer(customer);
		setProducts(products);
		setDeliveryDate(orderDate);
		setOrderDate(orderDate);
		setDiscount(discount);
		setTotalPrice();
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

	public void setShipAddress(Address shipAddress) {
		this.shipAddress = shipAddress;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	
	private void setTotalPrice() {
		for (Vinyl product : this.products) {
			this.totalPrice += product.getPrice();
		}
		
		if (this.discount > 0.0) {
			this.totalPrice -= (this.totalPrice * this.discount);
		}
		
	}
	
	// Getters
	
	public Date getDeliveryDate() {
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

	public Date getOrderDate() {
		return orderDate;
	}
	
	@Override
	public String toString() {
		
		String products = "";
		
		for (Vinyl vinyl : this.products) {
			products += " ["+vinyl.getVinylID() + ", " + vinyl.getName() + ", " + vinyl.getPrice() + "$] ";
		}
		
		
		return "Order [orderID=" + this.orderID + ", customerID=" + this.customer.getID() + ", employeeID=" + this.employee.getID()
				+ ", orderDate=" + this.orderDate + ", deliveryDate=" + this.deliveryDate + ", shipAddress=" + this.shipAddress +", products=" + products + ", totalPrice=" + this.totalPrice + "]";
	}
	
}
