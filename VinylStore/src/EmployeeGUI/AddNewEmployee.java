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
import java.util.ArrayList;

import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextPane;

import Classes.Address;
import Classes.Employee;
import Classes.Store;
import Classes.User;
import Exceptions.IlegalPassword;
import Exceptions.InvalidUserName;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import enums.City;
import javax.swing.JButton;
import java.awt.Color;

public class AddNewEmployee extends JFrame{
	
	public static Store store; 
	
	
	public AddNewEmployee() {
		getContentPane().setBackground(new Color(248, 248, 255));
		setTitle("ADD Employee");
		getContentPane().setLayout(null);
		setSize(531, 626);
		
		// set Icon
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		this.setIconImage(img.getImage());
		
		JLabel lblFisrtName = new JLabel("First Name:");
		lblFisrtName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFisrtName.setBounds(35, 133, 88, 20);
		getContentPane().add(lblFisrtName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLastName.setBounds(241, 133, 88, 20);
		getContentPane().add(lblLastName);
		
		TextField firstNameField = new TextField();
		firstNameField.setBackground(new Color(245, 245, 245));
		lblFisrtName.setLabelFor(firstNameField);
		firstNameField.setBounds(129, 129, 90, 24);
		getContentPane().add(firstNameField);
		
		TextField lastNameField = new TextField();
		lastNameField.setBackground(new Color(245, 245, 245));
		lblLastName.setLabelFor(lastNameField);
		lastNameField.setBounds(331, 133, 90, 24);
		getContentPane().add(lastNameField);
		
		JLabel lblEmployeeId = new JLabel("Employee ID:");
		lblEmployeeId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmployeeId.setBounds(34, 85, 110, 20);
		getContentPane().add(lblEmployeeId);
		
		TextField idField = new TextField();
		idField.setBackground(new Color(245, 245, 245));
		lblEmployeeId.setLabelFor(idField);
		idField.setBounds(151, 85, 110, 24);
		getContentPane().add(idField);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number: ");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPhoneNumber.setBounds(35, 188, 119, 20);
		getContentPane().add(lblPhoneNumber);
		
		TextField phoneField = new TextField();
		phoneField.setBackground(new Color(245, 245, 245));
		lblPhoneNumber.setLabelFor(phoneField);
		phoneField.setBounds(157, 184, 128, 24);
		getContentPane().add(phoneField);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(35, 238, 56, 20);
		getContentPane().add(lblEmail);
		
		TextField emailField = new TextField();
		emailField.setBackground(new Color(245, 245, 245));
		lblEmail.setLabelFor(emailField);
		emailField.setBounds(97, 238, 188, 24);
		getContentPane().add(emailField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(245, 245, 245));
		comboBox.setModel(new DefaultComboBoxModel(City.values()));
		comboBox.setBounds(87, 299, 78, 24);
		getContentPane().add(comboBox);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setLabelFor(comboBox);
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCity.setBounds(35, 301, 44, 20);
		getContentPane().add(lblCity);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblStreet.setBounds(181, 301, 56, 20);
		getContentPane().add(lblStreet);
		
		TextField streetField = new TextField();
		streetField.setBackground(new Color(245, 245, 245));
		lblStreet.setLabelFor(streetField);
		streetField.setBounds(241, 299, 128, 24);
		getContentPane().add(streetField);
		
		TextField numberField = new TextField();
		numberField.setBackground(new Color(245, 245, 245));
		numberField.setBounds(450, 299, 44, 24);
		getContentPane().add(numberField);
		
		TextField zipField = new TextField();
		zipField.setBackground(new Color(245, 245, 245));
		zipField.setBounds(119, 356, 78, 24);
		getContentPane().add(zipField);
		
		JLabel lblNumber = new JLabel("number:");
		lblNumber.setLabelFor(numberField);
		lblNumber.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNumber.setBounds(375, 301, 69, 20);
		getContentPane().add(lblNumber);
		
		JLabel lblZipCode = new JLabel("ZIP Code:");
		lblZipCode.setLabelFor(zipField);
		lblZipCode.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblZipCode.setBounds(35, 356, 78, 20);
		getContentPane().add(lblZipCode);
		
		JTextPane txtpnAddNewEmployee = new JTextPane();
		txtpnAddNewEmployee.setForeground(new Color(128, 0, 128));
		txtpnAddNewEmployee.setBackground(new Color(248, 248, 255));
		txtpnAddNewEmployee.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtpnAddNewEmployee.setText("ADD New Employee Form");
		txtpnAddNewEmployee.setBounds(131, 28, 238, 26);
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
		btnSubmit.setBackground(new Color(0, 51, 102));
		btnSubmit.setBounds(190, 485, 115, 29);
		getContentPane().add(btnSubmit);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(35, 403, 88, 20);
		getContentPane().add(lblUserName);
		
		TextField userField = new TextField();
		userField.setBackground(new Color(245, 245, 245));
		lblUserName.setLabelFor(userField);
		userField.setBounds(129, 403, 132, 24);
		getContentPane().add(userField);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(267, 403, 78, 20);
		getContentPane().add(lblPassword);
		
		TextField passField = new TextField();
		passField.setBackground(new Color(245, 245, 245));
		lblPassword.setLabelFor(passField);
		passField.setBounds(350, 399, 144, 24);
		getContentPane().add(passField);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			Address adress = new Address((City)comboBox.getSelectedItem() , streetField.getText(), numberField.getText(), zipField.getText());	
				
			try {
			
				Employee newEmployee = new Employee(idField.getText(), firstNameField.getText(), userField.getText(), passField.getText(),
						lastNameField.getText(), adress, phoneField.getText(), emailField.getText(), LocalDate.now());
		
				AddEmployee(newEmployee);
				JOptionPane.showMessageDialog(getContentPane(),"Employee Saved Successfuly!");
				
			} catch (InvalidUserName | IlegalPassword e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

				
			}
		});
		
		
	}

	protected void AddEmployee(Employee newEmployee) {
		
		//Save Employee
		deserialize("store.ser"); 
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
}
