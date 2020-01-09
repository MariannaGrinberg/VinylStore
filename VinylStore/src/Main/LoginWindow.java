package Main;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Canvas;
import javax.swing.JPasswordField;

import Classes.Customer;
import Classes.Employee;
import Classes.Store;
import Classes.User;
import Classes.Vinyl;
import CustomerGUI.CreateCustomer;
import CustomerGUI.CustomerGUI;
import EmployeeGUI.AdminPage;

import javax.swing.SwingConstants;

import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;

public class LoginWindow {
	
	static Store store; 

	private JFrame frmVinylS;
	private JLabel lblUserName;
	private TextField fieldName;
	private JLabel lblPassword;
	private TextField fieldPassword;
	private JLabel lblLogIn;
	private JButton btnSubmit;
	private static Customer customer = null;
	private static Employee employee = null; 

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frmVinylS.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frmVinylS = new JFrame();
		frmVinylS.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\maria\\git\\VinylStore\\VinylStore\\\u05D4\u05D5\u05E8\u05D3\u05D4.png"));
		frmVinylS.getContentPane().setBackground(new Color(240, 248, 255));
		frmVinylS.setTitle("Login Window");
		frmVinylS.setBounds(100, 100, 635, 636);
		frmVinylS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmVinylS.getContentPane().setLayout(null);
				

				ImageIcon img = new ImageIcon("VinylStoreIcon.png");
				frmVinylS.setIconImage(img.getImage());
		
				
				lblLogIn = new JLabel("Log In");
				lblLogIn.setBounds(270, 84, 105, 45);
				frmVinylS.getContentPane().add(lblLogIn);
				lblLogIn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 26));
				
				lblUserName = new JLabel("User Name:");
				lblUserName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
				lblUserName.setBounds(88, 170, 127, 20);
				frmVinylS.getContentPane().add(lblUserName);
				
				fieldName = new TextField();
				fieldName.setBackground(new Color(255, 250, 250));
				lblUserName.setLabelFor(fieldName);
				fieldName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
				fieldName.setBounds(237, 159, 181, 37);
				frmVinylS.getContentPane().add(fieldName);
				
				lblPassword = new JLabel("Password: ");
				lblPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
				lblPassword.setBounds(88, 218, 119, 20);
				frmVinylS.getContentPane().add(lblPassword);
				
				fieldPassword = new TextField();
				fieldPassword.setBackground(new Color(255, 250, 250));
				lblPassword.setLabelFor(fieldPassword);
				fieldPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
				fieldPassword.setBounds(237, 210, 181, 37);
				frmVinylS.getContentPane().add(fieldPassword);
				
				btnSubmit = new JButton("Submit");
				btnSubmit.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
				btnSubmit.setBackground(new Color(0, 51, 102));
				btnSubmit.setForeground(new Color(255, 255, 255));
				btnSubmit.setBounds(256, 290, 119, 37);
				frmVinylS.getContentPane().add(btnSubmit);
				
				JTextPane txtpnForAdminLogin = new JTextPane();
				txtpnForAdminLogin.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
				txtpnForAdminLogin.setBackground(new Color(240, 248, 255));
				txtpnForAdminLogin.setText("For Admin Login: Username & Password - ADMIN");
				txtpnForAdminLogin.setBounds(69, 451, 498, 90);
				frmVinylS.getContentPane().add(txtpnForAdminLogin);
				
				JButton btnNewButton = new JButton("Create New Account");
				btnNewButton.setForeground(new Color(255, 255, 255));
				btnNewButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
				btnNewButton.setBackground(new Color(102, 205, 170));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						CreateCustomer createCustomer = new CreateCustomer();
						createCustomer.setVisible(true);
						frmVinylS.dispose();
						
						
					}
				});
				btnNewButton.setBounds(208, 356, 210, 37);
				frmVinylS.getContentPane().add(btnNewButton);
				
				btnSubmit.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						deserialize("store.ser"); 

						String name = fieldName.getText(); 
						String pass = fieldPassword.getText(); 
					
						
						if(!(name.equals("") && pass.equals(""))) {
							
							if(name.equals("ADMIN") && pass.equals("ADMIN")) { 
							
								JOptionPane.showMessageDialog(frmVinylS,"You are signed in as admin ");
								
								AdminPage Admin = new AdminPage();
								Admin.setVisible(true);
								frmVinylS.dispose();
						
							} 
							
							else if(ExistsEmployee(name , pass)) {
						
								JOptionPane.showMessageDialog(frmVinylS,"hello "+name+"! You are signed in as admin ");
								
								AdminPage Admin = new AdminPage(employee.getID());
						    	Admin.setVisible(true);
						    	frmVinylS.dispose();

							}
							
							else if(ExistsCoustumer(name , pass)) {
							
								JOptionPane.showMessageDialog(frmVinylS,"hello "+name+"! You are signed in as Custumer ");
								CustomerGUI customerWindow = new CustomerGUI(customer.getID()); 
								customerWindow.getWindow().setVisible(true);
								frmVinylS.dispose();
							
								
								
							}
						
							 
						}
						
						else JOptionPane.showMessageDialog(frmVinylS,"please try again");

						
					}

					private boolean ExistsCoustumer(String name, String pass) {
		
						for(User user : store.getUsers())
							
							if(user instanceof Customer) 
								
								if(user.getUsername().equals(name) 
										&& user.getPassword().equals(pass)) {
								
									customer =  (Customer) user; 
									return true;
								}
						
						
						return false;
					}

					private boolean ExistsEmployee(String name, String pass) {
						
						
						for(User user : store.getUsers())
							
							if(user instanceof Employee) 
								
								if(user.getUsername().equals(name) 
										&& user.getPassword().equals(pass)) {
									employee = (Employee) user;
									return true;
					}
						
						
						return false;
					}
				
				});
	}
	
	@SuppressWarnings("unchecked")
	private static void deserialize(String fileName) {
		
		try {
			
			FileInputStream fileIn = 
					new FileInputStream(fileName);
			
			ObjectInputStream in = 
					new ObjectInputStream(fileIn);
			
				store = (Store) in.readObject();

				fileIn.close();
				in.close();
			
		} catch (IOException | ClassNotFoundException e) {
			
			System.out.println(e.getMessage());
			
		}

		
	}
	
	public JFrame getFrame() {
		return frmVinylS;
	}
}
