package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Classes.Customer;
import enums.City;

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
		if (rs != null) try { rs.close(); } catch(Exception e) {}  
		if (stmt != null) try { stmt.close(); } catch(Exception e) {}
	}

	public void executeStatement(String sql) {
		try {

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

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
					 "VALUES ('" + newCustomer.getID() + "', '" + newCustomer.getUsername() + "', '" + newCustomer.getPassword() + "', '" + newCustomer.getFirstName() + "', '" + newCustomer.getLastName() + "', '" + newCustomer.getPhoneNumber() + "', '" + newCustomer.getEmail() + "', '" + getCity(newCustomer.getAddress().getCity()) + "', '" + newCustomer.getAddress().getStreet() + "', '" + newCustomer.getAddress().getNumber() + "', '" + newCustomer.getAddress().getZipCode() + "');";

		try {
			
			open();

			executeStatement(sql);
			
		} finally {  
			close();
		}

	}
	
	private String getCity(City city) {
		String cityStr = "";

		if (city == City.Afula)
			cityStr = "Afula";

		else if (city == City.Haifa)
			cityStr = "Haifa";

		else if (city == City.TLV)
			cityStr = "TLV";

		else if (city == City.Other)
			cityStr = "Othre";

		return cityStr;
	}


}
