package JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Classes.Address;
import Classes.Customer;
import Classes.Employee;
import Classes.Order;
import Classes.Vinyl;
import Exceptions.IlegalDate;
import Exceptions.IlegalPassword;
import Exceptions.IllegalVinylPrice;
import Exceptions.InvalidUserName;
import enums.City;
import enums.Condition;
import enums.Format;

public class DBVinylStore {

	static String connectionUrl = "jdbc:sqlserver://localhost;databaseName=VinylStore;integratedSecurity=true;" ;  

	public ResultSet rs;
	public static Connection con;  
	public static Statement stmt;

	public static void main(String[] args) {

		DBVinylStore obj = new DBVinylStore();

	}

	public DBVinylStore() {
		con = null;
		stmt = null;
	}

	public void open() {
		try {  
			// Establish the connection.  
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			con = DriverManager.getConnection(connectionUrl);  

		} catch (ClassNotFoundException|SQLException  e ) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void close() {
		if (con != null) try { con.close(); } catch(Exception e ) {}
		if (rs != null) try { rs.close(); } catch(Exception e) {}  
		if (stmt != null) try { stmt.close(); } catch(Exception e) {}
	}

	private Connection getCon() {
		return con;
	}

	public void executeStatement(String sql) {
		try {

			stmt = con.createStatement();

			if (sql.contains("INSERT") || sql.contains("insert") || sql.contains("DELETE"))
				stmt.executeUpdate(sql);

			else
				rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void newOrderExecute(PreparedStatement st) {
		try {

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean containCustomer(String customerID, String userName) {

		String sql = "SELECT COUNT(CustomerID) as 'count'\r\n" + 
				"FROM Customer\r\n" + 
				"WHERE CustomerID = '" + customerID + "' OR UserName = '" + userName + "'";

		try {

			open();

			executeStatement(sql);
			while(rs.next()) {
				if (rs != null && rs.getInt("count") > 0) {
					return true;
				}
				else
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  
			close();
		}

		return false;


	}

	public void addCustomer(Customer newCustomer) throws SQLException {	

		String sql = "INSERT INTO Customer\r\n" + 
				"VALUES ('" + newCustomer.getID() + "', '" + newCustomer.getUsername() + "', '" + newCustomer.getPassword() + "', '" + newCustomer.getFirstName() + "', '" + newCustomer.getLastName() + "', '" + newCustomer.getPhoneNumber() + "', '" + newCustomer.getEmail() + "', '" + newCustomer.getAddress().getCity().toString() + "', '" + newCustomer.getAddress().getStreet() + "', '" + newCustomer.getAddress().getNumber() + "', '" + newCustomer.getAddress().getZipCode() + "');";

		try {

			open();

			executeStatement(sql);

		} finally {  
			close();
		}

	}

	public void addOrder(Order order) throws SQLException {	

		String sql;
		try {
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			sql = "INSERT INTO Orders (totalPrice, descript, OrderDate, CustomerID)\r\n" + 
					"VALUES (?,?,?,?)";

			open();

			PreparedStatement st = con.prepareStatement(sql);
			st.setFloat(1, (float)order.getTotalPrice());
			st.setString(2, order.toString());
			st.setDate(3, sqlDate);
			st.setString(4, order.getCustomer().getID());

			newOrderExecute(st);



		} catch (IllegalVinylPrice e) {
			e.printStackTrace();

		} finally {  
			close();
		}

		order.setID(lastOrderID());


		for(Vinyl product : order.getProducts()) {
			try {
				sql = "INSERT INTO VinylsInOrder (OrderID, VinylID)\r\n" + 
						"VALUES (" + order.getOrderID() + ", " + product.getVinylID() + ")";

				open();

				executeStatement(sql);

			} finally {  
				close();
			}
		}

	}

	public void clearCart(String customerID) {

		try {
			String sql = "DELETE FROM Cart\r\n" + 
					"WHERE CustomerID = '" + customerID + "' ";
			open();
			executeStatement(sql);

		} finally {  
			close();
		}
	}

	public ArrayList<Vinyl> getProducts(){
		String sql = "SELECT *" + 
				"FROM Vinyl";

		ArrayList<Vinyl> products = new ArrayList<>();

		try {

			open();

			executeStatement(sql);
			while(rs.next()) {

				if (rs != null) {
					try {
						Vinyl vinyl = new Vinyl(rs.getInt("vinylID"), rs.getString("VinylName"), rs.getString("ReleaseYear"), 
								fixLineBreak(rs.getString("Descript")), getFormat(rs.getString("VinylFormat")), getCondition(rs.getString("Condition")), 
								(int)rs.getFloat("discount"), rs.getFloat("price"));
						products.add(vinyl);
					} catch (IllegalVinylPrice e) {
						e.printStackTrace();
					}
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  
			close();
		}

		return products;
	}

	public ArrayList<Vinyl> getCart(String customerID) {

		ArrayList<Vinyl> cart = new ArrayList<>();
		ArrayList<Integer> productsIDs = new ArrayList<>(); 

		String sql = "SELECT *\r\n" + 
				"FROM Cart\r\n" + 
				"WHERE CustomerID = '" + customerID + "'";

		try {

			open();

			executeStatement(sql);
			while(rs.next()) {

				if (rs != null) {

					productsIDs.add(rs.getInt("VinylID"));

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  
			close();
		}

		for (int productID : productsIDs) {
			cart.add(getProductByID(productID));
		}

		return cart;

	}

	public int lastOrderID() {

		int ID = 0;

		String sql = "SELECT MAX(OrderID) AS OrderID\r\n" + 
				"FROM Orders";

		try {

			open();

			//			CustomerID UserName Pass FirstName LastName PhoneNumber Email City Street Number  ZipCode

			executeStatement(sql);
			while(rs.next()) {

				if (rs != null) {
					ID = rs.getInt("OrderID");
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  
			close();
		}

		return ID;
	}

	public void addToCart(String customerID, int productID) {
		String sql = "INSERT INTO Cart\r\n" + 
				"VALUES (" + productID + ", '" + customerID + "')";

		try {

			open();

			executeStatement(sql);

		} finally {  
			close();
		}
	}

	public void removeFromCart(String customerID, int productID) {

		String sql = "DELETE FROM Cart\r\n" + 
				"WHERE CustomerID = '" + customerID + "' AND ID IN ( SELECT TOP 1 ID\r\n" + 
				"										   FROM Cart\r\n" + 
				"										   WHERE VinylID = " + productID + ")";

		try {

			open();

			executeStatement(sql);

		} finally {  
			close();
		}

	}

	public Customer getCustomerByID(String ID) {

		Customer customer = null;

		String sql = "SELECT *\r\n" + 
				"FROM Customer\r\n" + 
				"WHERE CustomerID = '" + ID + "'";

		try {

			open();

			//			CustomerID UserName Pass FirstName LastName PhoneNumber Email City Street Number  ZipCode

			executeStatement(sql);
			while(rs.next()) {

				if (rs != null) {
					Address address = new Address(getCity(rs.getString("City")), rs.getString("Street"), rs.getString("Number"), rs.getString("ZipCode"));

					try {
						customer = new Customer(rs.getString("CustomerID"), rs.getString("UserName"), rs.getString("Pass"), 
								rs.getString("FirstName"), rs.getString("LastName"), address, rs.getString("PhoneNumber"), rs.getString("Email"));
					} catch (InvalidUserName e) {
						e.printStackTrace();
					} catch (IlegalPassword e) {
						e.printStackTrace();
					}

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  
			close();
		}

		return customer;
	}

	public ArrayList<Order> getOrdersByCustomerID(String ID) {
		
		ArrayList<Integer> ordersID = new ArrayList<>();
		ArrayList<Order> orders = new ArrayList<>();
		ArrayList<String> employees = new ArrayList<>();

		String sql = "SELECT OrderID, EmployeeID\r\n" + 
				"FROM Orders\r\n" + 
				"WHERE CustomerID = '" + ID + "'";

		try {

			open();

			executeStatement(sql);
			while(rs.next()) {

				if (rs != null) {
					ordersID.add(rs.getInt("OrderID"));
					
					if (rs.getString("EmployeeID") != null)
						employees.add(rs.getString("EmployeeID"));
					else
						employees.add("");
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  
			close();
		}


//		try {
//			
//			open();
//			
//			executeStatement(sql);
//
//			while(rs.next()) {
//
//				if (rs != null) {
//
//					LocalDate orderDate = rs.getDate("OrderDate").toLocalDate();
//					LocalDate deliveryDate = null;
//					int orderID = rs.getInt("OrderID");
//
//					if (rs.getString("EmployeeID") != null)
//						employees.add(rs.getString("EmployeeID"));
//					else
//						employees.add("");
//
//					if (rs.getDate("DeliveryDate") != null)
//						deliveryDate = rs.getDate("DeliveryDate").toLocalDate();
//
//					Customer customer = getCustomerByID(rs.getString("CustomerID"));
//
//
//					Order order;
//					try {
//						order = new Order(orderID, customer, orderDate);
//
//						order.setDeliveryDate(deliveryDate);
//
//						orders.add(order);
//
//					} catch (IllegalVinylPrice | IlegalDate e) {
//						e.printStackTrace();
//					}
//
//				}
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {  
//			close();
//		}


		for(int i = 0; i < ordersID.size(); i++) {
			
			Order order = getOrderByID(ordersID.get(i));
			
			if(!employees.get(i).equals("")) {
				order.setEmployee(getEmployeeByID(employees.get(i)));
			}
			
			orders.add(order);
		}

		return orders;
	}

	public float getOrderTotalPrice(int ID) {
		float totalPrice = 0;

		String sql = "SELECT totalPrice\r\n" + 
				"FROM Orders\r\n" + 
				"WHERE OrderID = " + ID;

		try {

			open();

			executeStatement(sql);
			while(rs.next()) {

				if (rs != null) {
					totalPrice = rs.getFloat("totalPrice");
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  
			close();
		}

		return totalPrice;
	}

	public Order getOrderByID(int ID) {

		Order order = null;

		String sql = "SELECT *\r\n" + 
				"FROM Orders\r\n" + 
				"WHERE OrderID = " + ID;

		try {

			open();

			executeStatement(sql);
			while(rs.next()) {

				if (rs != null) {
					LocalDate orderDate = rs.getDate("OrderDate").toLocalDate();
					LocalDate deliveryDate = null;
					int orderID = rs.getInt("OrderID");
					if(rs.getDate("DeliveryDate") != null)
						deliveryDate = rs.getDate("DeliveryDate").toLocalDate();
					Customer customer = getCustomerByID(rs.getString("CustomerID"));
					Employee employee = null;
					

					try {
						order = new Order(orderID, customer, orderDate);

						order.setDeliveryDate(deliveryDate);

						if (employee != null)
							order.setEmployee(employee);	


					} catch (IllegalVinylPrice | IlegalDate e) {
						e.printStackTrace();
					}

				}

			}



		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  
			close();
		}

		return order;
	}


	public Vinyl getProductByID(int ID) {

		Vinyl vinyl = null;

		String sql = "SELECT *\r\n" + 
				"FROM Vinyl\r\n" + 
				"WHERE vinylID = " + ID ;

		try {

			open();

			//			CustomerID UserName Pass FirstName LastName PhoneNumber Email City Street Number  ZipCode

			executeStatement(sql);
			while(rs.next()) {

				if (rs != null) {
					try {
						vinyl = new Vinyl(rs.getInt("vinylID"), rs.getString("VinylName"), rs.getString("ReleaseYear"), 
								fixLineBreak(rs.getString("Descript")), getFormat(rs.getString("VinylFormat")), getCondition(rs.getString("Condition")), 
								(int)rs.getFloat("discount"), rs.getFloat("price"));
					} catch (IllegalVinylPrice e) {
						e.printStackTrace();
					}
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  
			close();
		}

		return vinyl;
	}

	public Employee getEmployeeByID(String ID) {

		Employee employee = null;

		String sql = "SELECT *\r\n" + 
				"FROM Employee\r\n" + 
				"WHERE EmployeeID = '" + ID + "'";

		try {

			open();

			executeStatement(sql);
			while(rs.next()) {

				if (rs != null) {
					Address address = new Address(getCity(rs.getString("City")), rs.getString("Street"), rs.getString("Number"), rs.getString("ZipCode"));

					try {
						employee = new Employee(rs.getString("EmployeeID"), rs.getString("UserName"), rs.getString("Pass"), 
								rs.getString("FirstName"), rs.getString("LastName"), address, rs.getString("PhoneNumber"), rs.getString("Email"), null);
					} catch (InvalidUserName e) {
						e.printStackTrace();
					} catch (IlegalPassword e) {
						e.printStackTrace();
					}

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  
			close();
		}

		return employee;
	}

	public boolean customerCartIsEmpty(String ID) {
		String sql = "SELECT COUNT(*) as count\r\n" + 
				"FROM Cart\r\n" + 
				"WHERE CustomerID = '" + ID + "'";

		try {

			open();

			executeStatement(sql);
			while(rs.next()) {

				if (rs != null) {
					if (rs.getInt("count") == 0)
						return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  
			close();
		}

		return false;

	}

	public String getOrderDescription(int ID) {
		String sql = "SELECT descript\r\n" + 
				"FROM Orders\r\n" + 
				"WHERE OrderID = '" + ID + "'";

		String description = null;

		try {

			open();

			executeStatement(sql);
			while(rs.next()) {
				description = rs.getString("descript");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  
			close();
		}

		return description;
	}

	public boolean customerHasOrders(String ID) {
		String sql = "SELECT COUNT(*) as count\r\n" + 
				"FROM Orders\r\n" + 
				"WHERE CustomerID = '" + ID + "'";

		try {

			open();

			executeStatement(sql);
			while(rs.next()) {

				if (rs != null) {
					if (rs.getInt("count") > 0)
						return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  
			close();
		}

		return false;

	}

	private City getCity(String city) {
		City returnCity = null;

		if (city.equals("Afula"))
			returnCity = City.Afula;

		else if (city.equals("Haifa"))
			returnCity = City.Haifa;

		else if (city.equals("TLV"))
			returnCity = City.TLV;

		else if (city.equals("Other"))
			returnCity = City.Other;

		return returnCity;
	}

	private Format getFormat(String format) {
		Format returnFormat = null;

		if (format.equals("LP"))
			returnFormat = Format.LP;

		else if (format.equals("EP"))
			returnFormat = Format.EP;

		else if (format.equals("SEVEN"))
			returnFormat = Format.SEVEN;

		else if (format.equals("TEN"))
			returnFormat = Format.TEN;

		else if (format.equals("TWELVE_SINGLE"))
			returnFormat = Format.TWELVE_SINGLE;

		else if (format.equals("FORTY_FIVE_RPM"))
			returnFormat = Format.FORTY_FIVE_RPM;

		else if (format.equals("SEVENTY_EIGHT_RPM"))
			returnFormat = Format.SEVENTY_EIGHT_RPM;

		return returnFormat;


	}

	//	'old', 'BrandNew', 'Used', 'damaged'

	private Condition getCondition(String condition) {
		Condition returnCondition = null;

		if (condition.equals("old"))
			returnCondition = Condition.old;

		else if (condition.equals("BrandNew"))
			returnCondition = Condition.BrandNew;

		else if (condition.equals("Used"))
			returnCondition = Condition.Used;

		else if (condition.equals("damaged"))
			returnCondition = Condition.damaged;

		return returnCondition;
	}

	public String fixLineBreak(String str) {
		String returnStr = str.replaceAll("//", "\n");
		return returnStr;
	}

}
