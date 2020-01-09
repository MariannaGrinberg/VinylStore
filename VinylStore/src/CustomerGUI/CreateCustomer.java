package CustomerGUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextPane;

import Classes.Address;
import Classes.Customer;
import Classes.Employee;
import Classes.Store;
import EmployeeGUI.AdminPage;
import Exceptions.IlegalPassword;
import Exceptions.InvalidUserName;
import Main.LoginWindow;

import java.awt.Font;
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
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import enums.City;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreateCustomer  extends JFrame{
	
	static Store store; 
	
	public CreateCustomer() {
		deserialize("store.ser"); 
		getContentPane().setBackground(new Color(240, 248, 255));
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 23));
		setTitle("Create Account");
		getContentPane().setLayout(null);
		
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		this.setIconImage(img.getImage());
		
		setSize(858,889);
		
		JTextPane txtpnCreateAccount = new JTextPane();
		txtpnCreateAccount.setBackground(new Color(240, 248, 255));
		txtpnCreateAccount.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 34));
		txtpnCreateAccount.setText("Create New Account");
		txtpnCreateAccount.setBounds(245, 59, 343, 52);
		getContentPane().add(txtpnCreateAccount);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		lblId.setBounds(79, 169, 39, 20);
		getContentPane().add(lblId);
		
		JLabel lblFisrtName = new JLabel("Fisrt Name:");
		lblFisrtName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		lblFisrtName.setBounds(79, 344, 121, 30);
		getContentPane().add(lblFisrtName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		lblLastName.setBounds(420, 346, 121, 27);
		getContentPane().add(lblLastName);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		lblCity.setBounds(79, 427, 54, 35);
		getContentPane().add(lblCity);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(UIManager.getColor("ComboBox.buttonHighlight"));
		comboBox.setModel(new DefaultComboBoxModel(City.values()));
		comboBox.setBounds(216, 432, 110, 35);
		getContentPane().add(comboBox);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		lblStreet.setBounds(79, 502, 75, 25);
		getContentPane().add(lblStreet);
		
		TextField FieldStreet = new TextField();
		FieldStreet.setFont(new Font("Dialog", Font.PLAIN, 22));
		FieldStreet.setBounds(216, 502, 178, 35);
		getContentPane().add(FieldStreet);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		lblNumber.setBounds(79, 574, 102, 25);
		getContentPane().add(lblNumber);
		
		TextField FieldNumber = new TextField();
		FieldNumber.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNumber.setLabelFor(FieldNumber);
		FieldNumber.setBounds(216, 564, 83, 35);
		getContentPane().add(FieldNumber);
		
		JLabel lblZipCode = new JLabel("Zip Code:");
		lblZipCode.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		lblZipCode.setBounds(420, 569, 114, 30);
		getContentPane().add(lblZipCode);
		
		TextField FieldZip = new TextField();
		FieldZip.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblZipCode.setLabelFor(FieldZip);
		FieldZip.setBounds(547, 564, 132, 35);
		getContentPane().add(FieldZip);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		lblPhoneNumber.setBounds(79, 638, 170, 44);
		getContentPane().add(lblPhoneNumber);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		lblEmail.setBounds(79, 714, 69, 35);
		getContentPane().add(lblEmail);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		lblUserName.setBounds(79, 247, 133, 44);
		getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		lblPassword.setBounds(420, 247, 110, 45);
		getContentPane().add(lblPassword);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(220, 220, 220));
		btnBack.setBounds(0, 0, 77, 29);
		getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			LoginWindow LW = new LoginWindow();
			LW.getFrame().setVisible(true);
			dispose();
		
			
		}
	});
		
		JTextArea FieldId = new JTextArea();	
		FieldId.setFont(new Font("Courier New", Font.PLAIN, 20));
		FieldId.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		FieldId.setBackground(UIManager.getColor("TextArea.background"));
		FieldId.setBounds(143, 170, 251, 30);
		getContentPane().add(FieldId);
		FieldId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidateID(FieldId.getText()) == true){
				    Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
				    FieldId.setBorder(border);
				}
				else if(ValidateID(FieldId.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					FieldId.setBorder(border);
				}
						
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(ValidateID(FieldId.getText()) == true){
				    Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
				    FieldId.setBorder(border);
				}
				else if(ValidateID(FieldId.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					FieldId.setBorder(border);
				}
				
			
			}
	
		});
		
		JTextArea FieldUser = new JTextArea();
		FieldUser.setFont(new Font("Courier New", Font.PLAIN, 20));
		FieldUser.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		FieldUser.setBackground(Color.WHITE);
		FieldUser.setBounds(216, 260, 178, 30);
		getContentPane().add(FieldUser);
		FieldUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidateUserName(FieldUser.getText()) == true){
				
					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					FieldUser.setBorder(border);
				}
			
				else if(ValidateUserName(FieldUser.getText()) == false) {
				
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					FieldUser.setBorder(border);
				}
						
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(ValidateUserName(FieldUser.getText()) == true){
				    Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
				    FieldUser.setBorder(border);
				}
			
				else if(ValidateUserName(FieldUser.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					FieldUser.setBorder(border);
				}
				
			}
	
		});
		
		JTextArea FieldPassword = new JTextArea();
		FieldPassword.setFont(new Font("Courier New", Font.PLAIN, 20));
		FieldPassword.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		FieldPassword.setBackground(Color.WHITE);
		FieldPassword.setBounds(547, 260, 178, 30);
		getContentPane().add(FieldPassword);
		FieldPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidatePassword(FieldPassword.getText()) == true){
				
					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					FieldPassword.setBorder(border);
				}
			
				else if(ValidatePassword(FieldPassword.getText()) == false) {
				
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					FieldPassword.setBorder(border);
				}
						
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(ValidatePassword(FieldPassword.getText()) == true){
				    Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
				    FieldPassword.setBorder(border);
				}
			
				else if(ValidatePassword(FieldPassword.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					FieldPassword.setBorder(border);
				}
				
			}
	
		});
		
		JTextArea FieldLastName = new JTextArea();
		FieldLastName.setFont(new Font("Courier New", Font.PLAIN, 20));
		FieldLastName.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		FieldLastName.setBackground(Color.WHITE);
		FieldLastName.setBounds(547, 344, 178, 30);
		getContentPane().add(FieldLastName);
		FieldLastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidateName(FieldLastName.getText()) == true){
				
					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					FieldLastName.setBorder(border);
				}
			
				else if(ValidateName(FieldLastName.getText()) == false) {
				
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					FieldLastName.setBorder(border);
				}
						
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(ValidateName(FieldLastName.getText()) == true){
				    Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
				    FieldLastName.setBorder(border);
				}
			
				else if(ValidateName(FieldLastName.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					FieldLastName.setBorder(border);
				}
				
			}
	
		});
		
		JTextArea FieldFirstName = new JTextArea();
		FieldFirstName.setFont(new Font("Courier New", Font.PLAIN, 20));
		FieldFirstName.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		FieldFirstName.setBackground(Color.WHITE);
		FieldFirstName.setBounds(215, 344, 178, 30);
		getContentPane().add(FieldFirstName);
		FieldFirstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidateName(FieldFirstName.getText()) == true){
				
					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					FieldFirstName.setBorder(border);
				}
			
				else if(ValidateName(FieldFirstName.getText()) == false) {
				
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					FieldFirstName.setBorder(border);
				}
						
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(ValidateName(FieldFirstName.getText()) == true){
				    Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
				    FieldFirstName.setBorder(border);
				}
			
				else if(ValidateName(FieldFirstName.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					FieldFirstName.setBorder(border);
				}
				
			}
	
		});
		
		JTextArea FieldPhone = new JTextArea();
		FieldPhone.setFont(new Font("Courier New", Font.PLAIN, 20));
		FieldPhone.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		FieldPhone.setBackground(Color.WHITE);
		FieldPhone.setBounds(261, 651, 251, 30);
		getContentPane().add(FieldPhone);
		FieldPhone.setFont(new Font("Courier New", Font.PLAIN, 20));
		
		FieldPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidateFhone(FieldPhone.getText()) == true){
				
					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					FieldPhone.setBorder(border);
				}
			
				else if(ValidateFhone(FieldPhone.getText()) == false) {
				
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					FieldPhone.setBorder(border);
				}
						
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(ValidateFhone(FieldPhone.getText()) == true){
				    Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
				    FieldPhone.setBorder(border);
				}
			
				else if(ValidateFhone(FieldPhone.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					FieldPhone.setBorder(border);
				}
				
			}
	
		});
		
		TextField FieldEmail = new TextField();
		FieldEmail.setFont(new Font("Dialog", Font.PLAIN, 22));
		FieldEmail.setBounds(261, 714, 251, 35);
		getContentPane().add(FieldEmail);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setBackground(new Color(102, 205, 170));
		btnSubmit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		btnSubmit.setBounds(355, 785, 152, 44);
		getContentPane().add(btnSubmit);
		
	
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			Address adress = new Address((City)comboBox.getSelectedItem() , FieldStreet.getText(), FieldNumber.getText(), FieldZip.getText());	

			try {
				
				if(ValidateID(FieldId.getText()) == true 
					&&ValidateName(FieldFirstName.getText()) == true 
					&& ValidateName(FieldLastName.getText()) == true 
					&& ValidateFhone(FieldPhone.getText()) == true) {
				
						Customer newCustomer = new Customer(FieldId.getText(), FieldUser.getText(),
								FieldPassword.getText(),FieldFirstName.getText(),FieldLastName.getText(),
								adress, FieldPhone.getText(), FieldEmail.getText(), LocalDate.now());
		
						if(store.containsCustomer(newCustomer)== false) {
							
							AddCoustomer(newCustomer);
							
							JOptionPane.showMessageDialog(getFrame(),"hello "+newCustomer.getFirstName()+"! You are signed in as Custumer ");
							CustomerGUI customerWindow = new CustomerGUI(newCustomer.getID()); 
							customerWindow.getWindow().setVisible(true);
							dispose();
						}
					
						else JOptionPane.showMessageDialog(getContentPane(),"Account Is Alredy Exist, Please Try Again!"); 

				}
				
				else JOptionPane.showMessageDialog(getContentPane(),"One Or More Of The Details You Entered Are Invalid! "
																  + "\nFist And Last Name Must Start With Capital Letter And They Can't Contain Numbers"
																  + "\nAn ID Must Be 9 Digits Long And Contain Only Numbers"
																  + "\nAn Phone Number Must Be 10 Digits Long And Contain Only Numbers");
			

				
			} catch (InvalidUserName | IlegalPassword e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(getContentPane(),e1.getMessage());
			}

				
			}
		});
		

	}
	
	protected void AddCoustomer(Customer customer) {
		
		//Save Employee
		store.addCustomer(customer);
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
	public JFrame getFrame() {
		return this;
	}
public boolean ValidateName(String name) {
		
		return name.matches("[A-Z][^0-9]*") && name.length() > 2;
		
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
