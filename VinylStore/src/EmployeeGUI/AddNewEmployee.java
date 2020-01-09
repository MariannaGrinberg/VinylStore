package EmployeeGUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import Classes.Address;
import Classes.Employee;
import Classes.Store;
import Exceptions.IlegalPassword;
import Exceptions.InvalidUserName;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import enums.City;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

public class AddNewEmployee extends JFrame{
	
	public static Store store; 
	
	
	public AddNewEmployee() {
		deserialize("store.ser"); 
		getContentPane().setBackground(new Color(240, 248, 255));
		setTitle("Add Employee");
		getContentPane().setLayout(null);
		setSize(786, 933);
		
		// set Icon
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		this.setIconImage(img.getImage());
		
		JLabel lblFisrtName = new JLabel("First Name:");
		lblFisrtName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblFisrtName.setBounds(76, 338, 119, 20);
		getContentPane().add(lblFisrtName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblLastName.setBounds(408, 338, 109, 20);
		getContentPane().add(lblLastName);
		
		JLabel lblEmployeeId = new JLabel("Employee ID:");
		lblEmployeeId.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblEmployeeId.setBounds(76, 196, 134, 20);
		getContentPane().add(lblEmployeeId);
		
		
		JLabel lblPhoneNumber = new JLabel("Phone Number: ");
		lblPhoneNumber.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblPhoneNumber.setBounds(76, 406, 171, 20);
		getContentPane().add(lblPhoneNumber);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblEmail.setBounds(76, 474, 69, 20);
		getContentPane().add(lblEmail);
		
		TextField emailField = new TextField();
		emailField.setFont(new Font("Dialog", Font.PLAIN, 20));
		emailField.setBackground(new Color(245, 245, 245));
		lblEmail.setLabelFor(emailField);
		emailField.setBounds(151, 474, 338, 30);
		getContentPane().add(emailField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(245, 245, 245));
		comboBox.setModel(new DefaultComboBoxModel(City.values()));
		comboBox.setBounds(135, 546, 97, 30);
		getContentPane().add(comboBox);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setLabelFor(comboBox);
		lblCity.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblCity.setBounds(76, 540, 44, 30);
		getContentPane().add(lblCity);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblStreet.setBounds(244, 548, 78, 20);
		getContentPane().add(lblStreet);
		
		TextField streetField = new TextField();
		streetField.setFont(new Font("Dialog", Font.PLAIN, 20));
		streetField.setBackground(new Color(245, 245, 245));
		lblStreet.setLabelFor(streetField);
		streetField.setBounds(326, 546, 369, 30);
		getContentPane().add(streetField);
		
		TextField numberField = new TextField();
		numberField.setFont(new Font("Dialog", Font.PLAIN, 20));
		numberField.setBackground(new Color(245, 245, 245));
		numberField.setBounds(345, 616, 78, 30);
		getContentPane().add(numberField);
		
		TextField zipField = new TextField();
		zipField.setFont(new Font("Dialog", Font.PLAIN, 20));
		zipField.setBackground(new Color(245, 245, 245));
		zipField.setBounds(555, 616, 140, 30);
		getContentPane().add(zipField);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setLabelFor(numberField);
		lblNumber.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblNumber.setBounds(242, 616, 97, 30);
		getContentPane().add(lblNumber);
		
		JLabel lblZipCode = new JLabel("ZIP Code:");
		lblZipCode.setLabelFor(zipField);
		lblZipCode.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblZipCode.setBounds(452, 616, 97, 30);
		getContentPane().add(lblZipCode);
		
		JTextPane txtpnAddNewEmployee = new JTextPane();
		txtpnAddNewEmployee.setForeground(new Color(0, 0, 0));
		txtpnAddNewEmployee.setBackground(new Color(240, 248, 255));
		txtpnAddNewEmployee.setFont(new Font("Tahoma", Font.BOLD, 30));
		txtpnAddNewEmployee.setText("Add New Employee Form");
		txtpnAddNewEmployee.setBounds(216, 88, 390, 47);
		getContentPane().add(txtpnAddNewEmployee);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(211, 211, 211));
		btnBack.setBounds(0, 0, 69, 29);
		getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminPage AP = new AdminPage();
				AP.getFrame().setVisible(true);
				dispose();
				
			}
		});
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setBackground(new Color(102, 205, 170));
		btnSubmit.setBounds(294, 755, 155, 53);
		getContentPane().add(btnSubmit);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblUserName.setBounds(76, 262, 119, 20);
		getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblPassword.setBounds(408, 262, 104, 20);
		getContentPane().add(lblPassword);
		
		JTextArea idField = new JTextArea();
		idField.setFont(new Font("Courier New", Font.PLAIN, 20));
		
		idField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidateID(idField.getText()) == true){
				    Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					idField.setBorder(border);
				}
				else if(ValidateID(idField.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					idField.setBorder(border);
				}
						
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(ValidateID(idField.getText()) == true){
				    Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					idField.setBorder(border);
				}
				else if(ValidateID(idField.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					idField.setBorder(border);
				}
				
			
			}
	
		});
		
		
		
		idField.setBackground(new Color(245, 245, 245));
		idField.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		idField.setBounds(216, 196, 172, 30);
		getContentPane().add(idField);
		
		JTextArea firstNameField = new JTextArea();
		firstNameField.setFont(new Font("Courier New", Font.PLAIN, 20));
		
		firstNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidateName(firstNameField.getText()) == true){
				
					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
				    firstNameField.setBorder(border);
				}
			
				else if(ValidateName(firstNameField.getText()) == false) {
				
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					firstNameField.setBorder(border);
				}
						
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(ValidateName(firstNameField.getText()) == true){
				    Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
				    firstNameField.setBorder(border);
				}
			
				else if(ValidateName(firstNameField.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					firstNameField.setBorder(border);
				}
				
			}
	
		});
		
		firstNameField.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		firstNameField.setBackground(new Color(245, 245, 245));
		firstNameField.setBounds(216, 334, 172, 30);
		getContentPane().add(firstNameField);
		
		JTextArea lastNameField = new JTextArea();
		lastNameField.setFont(new Font("Courier New", Font.PLAIN, 20));
		lastNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidateName(lastNameField.getText()) == true){
				
					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					lastNameField.setBorder(border);
				}
			
				else if(ValidateName(lastNameField.getText()) == false) {
				
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					lastNameField.setBorder(border);
				}
						
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(ValidateName(lastNameField.getText()) == true){
				    Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
				    lastNameField.setBorder(border);
				}
			
				else if(ValidateName(lastNameField.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					lastNameField.setBorder(border);
				}
				
			}
	
		});
		lastNameField.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		lastNameField.setBackground(new Color(245, 245, 245));
		lastNameField.setBounds(532, 334, 172, 30);
		getContentPane().add(lastNameField);
		
		JTextArea phoneField = new JTextArea();
		phoneField.setFont(new Font("Courier New", Font.PLAIN, 20));
		
		phoneField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidateFhone(phoneField.getText()) == true){
				
					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					phoneField.setBorder(border);
				}
			
				else if(ValidateFhone(phoneField.getText()) == false) {
				
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					phoneField.setBorder(border);
				}
						
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(ValidateFhone(phoneField.getText()) == true){
				    Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
				    phoneField.setBorder(border);
				}
			
				else if(ValidateFhone(phoneField.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					phoneField.setBorder(border);
				}
				
			}
	
		});
		phoneField.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		phoneField.setBackground(new Color(245, 245, 245));
		phoneField.setBounds(244, 406, 245, 30);
		getContentPane().add(phoneField);
		
		JTextArea userField = new JTextArea();
		userField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidateUserName(userField.getText()) == true){
				
					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					userField.setBorder(border);
				}
			
				else if(ValidateUserName(userField.getText()) == false) {
				
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					userField.setBorder(border);
				}
						
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(ValidateUserName(userField.getText()) == true){
				    Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
				    userField.setBorder(border);
				}
			
				else if(ValidateUserName(userField.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					userField.setBorder(border);
				}
				
			}
	
		});
		userField.setFont(new Font("Courier New", Font.PLAIN, 20));
		userField.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		userField.setBackground(new Color(245, 245, 245));
		userField.setBounds(216, 262, 172, 30);
		getContentPane().add(userField);
		
		JTextArea passField = new JTextArea();
		passField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidatePassword(passField.getText()) == true){
				
					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					passField.setBorder(border);
				}
			
				else if(ValidatePassword(passField.getText()) == false) {
				
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					passField.setBorder(border);
				}
						
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(ValidatePassword(passField.getText()) == true){
				    Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
				    passField.setBorder(border);
				}
			
				else if(ValidatePassword(passField.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					passField.setBorder(border);
				}
				
			}
	
		});
		passField.setFont(new Font("Courier New", Font.PLAIN, 20));
		passField.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		passField.setBackground(new Color(245, 245, 245));
		passField.setBounds(532, 262, 172, 30);
		getContentPane().add(passField);
		
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			Address adress = new Address((City)comboBox.getSelectedItem() , streetField.getText(), numberField.getText(), zipField.getText());	
		
			try {
						
					if(ValidateID(idField.getText()) == true 
						&&ValidateName(firstNameField.getText()) == true 
						&& ValidateName(lastNameField.getText()) == true 
						&& ValidateFhone(phoneField.getText()) == true) {
				
							Employee newEmployee = new Employee(idField.getText(), firstNameField.getText(), userField.getText(), passField.getText(),
							lastNameField.getText(), adress, phoneField.getText(), emailField.getText(), LocalDate.now());
			
				
							if(store.containsEmployee(newEmployee)== false) {
							
							
							AddEmployee(newEmployee);
						
							JOptionPane.showMessageDialog(getContentPane(),"Employee Saved Successfuly!");
							AdminPage AP = new AdminPage();
							AP.getFrame().setVisible(true);
							dispose();
						
						}
					
						else JOptionPane.showMessageDialog(getContentPane(),"Employee Alredy Exist, Please Try Again!"); 

						
					}
					
					else JOptionPane.showMessageDialog(getContentPane(),"One Or More Of The Details You Entered Are Invalid! "
																	  + "\nFist And Last Name Must Start With Capital Letter And They Can't Contain Numbers"
																	  + "\nAn ID Must Be 9 Digits Long And Contain Only Numbers"
																	  + "\nAn Phone Number Must Be 10 Digits Long And Contain Only Numbers ");
				
//			}
				
				
			} catch (InvalidUserName | IlegalPassword e1) {
				JOptionPane.showMessageDialog(getContentPane(),e1.getMessage());

				
			}

				
			}
		});
		
		
	}

	protected void AddEmployee(Employee newEmployee) {
		
		//Save Employee
		store.addEmployee(newEmployee);
		serialize("store.ser"); 
		
	}
	
	

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
	private static void serialize(String fileName) {
		
		try {
			
			FileOutputStream fileOut = 
					new FileOutputStream(fileName);
			
			ObjectOutputStream out =
					new ObjectOutputStream(fileOut);
		

			out.writeObject(store);
			
			out.close();
			fileOut.close();
			
		
			
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
			
		}

	}
	
	
	public boolean ValidateName(String name) {
		
		return name.matches("[A-Z][^0-9]*")  && name.length() > 2;
		
	}
	
	
	public boolean ValidateID(String ID) {
		
		return ID.matches("[0-9]{9}");
		
	}
	
	public boolean ValidateFhone(String ID) {
		
		return ID.matches("[0-9]{10}");
		
	}
	
	public boolean ValidatePassword(String Password) {
		
		return Password.length() > 6;
		
	}
	
	public boolean ValidateUserName(String UserName) {
		
		return UserName.length() > 2; 
		
	}
}
