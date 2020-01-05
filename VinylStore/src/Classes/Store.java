package Classes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Store implements Serializable {
	

	private static final long serialVersionUID = 4856934097028409796L;

	// Attributes
	static int num = 0;
	private int storeID;
	private String name;
	private ArrayList<Vinyl> products;
	private ArrayList<Customer> customers;
	private ArrayList<Employee> employees;
	private ArrayList<User> users;
	private ArrayList<Order> orders;

	public Store(String name) {
		products = new ArrayList<>(); 
		customers = new ArrayList<>();
		employees = new ArrayList<>();
		orders = new ArrayList<>();
		users = new ArrayList<>();
		setStoreID();
		setName(name);
	}


	// Setters
	
	public void setStoreID() {
		this.storeID = ++num;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	// Getters
	
	public String getName() {
		return this.name;
	}

	public int getStoreID() {
		return this.storeID;
	}
	
	public ArrayList<Vinyl> getProducts() {
		
		Collections.sort(this.products);
		return this.products;
	}
	
	public ArrayList<User> getUsers() {
		
		return this.users;
		
	}
	
	public Vinyl getProductByID(int ID) {
		
		Collections.sort(this.products);
		
		for (Vinyl product : this.products) {
			if (product.getVinylID() == ID) {
				return product;
			}
		}
		
		return null;
	}
	
	public ArrayList<Customer> getCustomers() {
		
		Collections.sort(this.customers);
		return this.customers;
	}
	
	public ArrayList<Employee> getEmployees() {
		
		Collections.sort(this.employees);
		return this.employees;
		
	}
	
	public ArrayList<Order> getOrders() {
		
		Collections.sort(this.orders);
		return this.orders;
		
	}
	
	public Customer getCustomerByID(String ID) {
		
		for (Customer customer : this.customers) {
			if (customer.getID().equals(ID)) {
				return customer;
			}
		}
		
		return null;
	}
	
	public Employee getEmployeeByID(String ID) {
		
		for (Employee employee : this.employees) {
			if (employee.getID() == ID) {
				return employee;
			}
		}
		
		return null;
	}
	
	public Order getOrderByID(int ID) {
		
		for (Order order : this.orders) {
			if (order.getOrderID() == ID) {
				return order;
			}
		}
		
		return null;
	}
	
	public ArrayList<Order> getHandledOrders() {
		ArrayList<Order> handledOrders = new ArrayList<Order>();
		
		for (Order order : this.orders) {
			if (order.getEmployee() != null) {
				handledOrders.add(order);
			}
		}
		
		return handledOrders;
	}
	
	public ArrayList<Order> getNotHandledOrders() {
		ArrayList<Order> notHandledOrders = new ArrayList<Order>();
		
		for (Order order : this.orders) {
			if (order.getEmployee() == null) {
				notHandledOrders.add(order);
			}
		}
		
		return notHandledOrders;
	}
	
	public ArrayList<Order> getOrdersByCustomerID(String ID) {
		ArrayList<Order> orders = new ArrayList<Order>();
		
		for (Order order : this.orders) {
			if (order.getCustomer().getID() == ID) {
				orders.add(order);
			}
		}
		
		return orders;
	}
	
	public ArrayList<Order> getOrderByEmployeeID(String ID) {
		ArrayList<Order> orders = new ArrayList<Order>();
		
		for (Order order : this.orders) {
			if (order.getEmployee().getID() == ID) {
				orders.add(order);
			}
		}
		
		return orders;
	}
	
	
	// Add
	
	public void addProduct(Vinyl vinyl) {
		this.products.add(vinyl);
	}
	
	public void addCustomer(Customer customer) {
		this.customers.add(customer);
		addUser(customer); 
	}
	
	public void addEmployee(Employee employee) {
		this.employees.add(employee);
		addUser(employee); 
	}
	
	private void addUser(User user) {
		this.users.add(user);
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
	}


	@Override
	public String toString() {
		
		Collections.sort(this.products);
		String products = "";
		
		for (Vinyl product : this.products) {
			products += product + ", ";
		}
		
		products = products.substring(0, products.length() -2 );
		
		return "Store [storeID=" + storeID + ", name=" + name + ", products=[" + products + "]]";
	}
	
}
