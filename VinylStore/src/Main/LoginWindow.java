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
		frmVinylS.setBounds(100, 100, 445, 405);
		frmVinylS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmVinylS.getContentPane().setLayout(null);
				

				ImageIcon img = new ImageIcon("VinylStoreIcon.png");
				frmVinylS.setIconImage(img.getImage());
		
				
				lblLogIn = new JLabel("Log In");
				lblLogIn.setBounds(189, 37, 62, 23);
				frmVinylS.getContentPane().add(lblLogIn);
				lblLogIn.setFont(new Font("Tahoma", Font.BOLD, 19));
				
				lblUserName = new JLabel("User Name:");
				lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblUserName.setBounds(89, 84, 102, 20);
				frmVinylS.getContentPane().add(lblUserName);
				
				fieldName = new TextField();
				fieldName.setBackground(new Color(245, 245, 245));
				lblUserName.setLabelFor(fieldName);
				fieldName.setFont(new Font("Tahoma", Font.PLAIN, 16));
				fieldName.setBounds(197, 84, 137, 24);
				frmVinylS.getContentPane().add(fieldName);
				
				lblPassword = new JLabel("Password: ");
				lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblPassword.setBounds(89, 118, 102, 20);
				frmVinylS.getContentPane().add(lblPassword);
				
				fieldPassword = new TextField();
				fieldPassword.setBackground(new Color(245, 245, 245));
				lblPassword.setLabelFor(fieldPassword);
				fieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
				fieldPassword.setBounds(197, 114, 137, 24);
				frmVinylS.getContentPane().add(fieldPassword);
				
				btnSubmit = new JButton("Submit");
				btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnSubmit.setBackground(new Color(0, 51, 102));
				btnSubmit.setForeground(new Color(255, 255, 255));
				btnSubmit.setBounds(167, 160, 95, 29);
				frmVinylS.getContentPane().add(btnSubmit);
				
				JTextPane txtpnForAdminLogin = new JTextPane();
				txtpnForAdminLogin.setBackground(new Color(245, 245, 245));
				txtpnForAdminLogin.setText("For Admin Login: \r\n\tuser name & password: ADMIN\r\n\r\nFor Cutomer Login: \r\n\tuser name & passworf: id");
				txtpnForAdminLogin.setBounds(51, 215, 333, 118);
				frmVinylS.getContentPane().add(txtpnForAdminLogin);
				
				btnSubmit.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						deserialize("store.ser"); 

						String name = fieldName.getText(); 
						String pass = fieldPassword.getText(); 
					
						
						if(!(name.equals("") && pass.equals(""))) {
							
							if(name.equals("ADMIN") && pass.equals("ADMIN")) { 
							
								JOptionPane.showMessageDialog(frmVinylS,"You are signed in as admin ");
								frmVinylS.setVisible(false);
								AdminPage Admin = new AdminPage();
								Admin.setVisible(true);
						
							} 
							
							else if(ExistsEmployee(name , pass)) {
						
								JOptionPane.showMessageDialog(frmVinylS,"hello "+name+"! You are signed in as admin ");
								frmVinylS.setVisible(false);
								AdminPage Admin = new AdminPage(employee.getID());
						    	Admin.setVisible(true);

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
