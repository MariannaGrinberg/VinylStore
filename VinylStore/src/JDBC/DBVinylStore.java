package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBVinylStore {

	static String connectionUrl = "jdbc:sqlserver://localhost;databaseName=VinylStore;integratedSecurity=true;" ;  

	ResultSet rs;
	static Connection con = null;  
	static Statement stmt = null; 

	public static void main(String[] args) {

		DBVinylStore obj = new DBVinylStore();
	
		obj.Close();

	}

	public DBVinylStore() {

		try {  
			// Establish the connection.  
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			con = DriverManager.getConnection(connectionUrl);  

		} catch (ClassNotFoundException|SQLException  e ) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void Close() {

		if (con != null) 
			try { 
				con.close(); 
			} catch(Exception e) {
				e.printStackTrace();
			}  

	}



}
