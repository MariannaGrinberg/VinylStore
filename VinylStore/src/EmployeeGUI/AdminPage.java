package EmployeeGUI;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

import Main.LoginWindow;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

public class AdminPage extends JFrame{

	private static final long serialVersionUID = 1L;

	public AdminPage(String ID) {
		
		initialize(); 
	}
	
	public AdminPage() {
		
		initialize(); 
	}
	
	
	
	public void initialize() {
		getContentPane().setBackground(new Color(245, 245, 245));

		setTitle("Employee Window");
		getContentPane().setLayout(null);
		setSize(631, 621);
		
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		this.setIconImage(img.getImage());
		
		
		JButton btnViewEmployees = new JButton("View Employees");
		btnViewEmployees.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnViewEmployees.setBackground(new Color(176, 224, 230));
		btnViewEmployees.setBounds(32, 303, 252, 80);
		getContentPane().add(btnViewEmployees);
		
		JButton btnAddEmployee = new JButton("Add New Employee");
		btnAddEmployee.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnAddEmployee.setBackground(new Color(176, 224, 230));
		btnAddEmployee.setBounds(307, 303, 252, 80);
		getContentPane().add(btnAddEmployee);
		
		JButton btnAddNewCustomer = new JButton("Add New Customer");
		btnAddNewCustomer.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnAddNewCustomer.setBackground(new Color(176, 224, 230));
		btnAddNewCustomer.setBounds(307, 399, 252, 80);
		getContentPane().add(btnAddNewCustomer);
		
		JButton btnAddVinyl = new JButton("Add Vinyl to Store");
		btnAddVinyl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddVinyl.setBackground(new Color(176, 224, 230));
		btnAddVinyl.setBounds(307, 207, 252, 80);
		getContentPane().add(btnAddVinyl);
		
		JButton btnViewOrders = new JButton("View Orders");
		btnViewOrders.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnViewOrders.setBackground(new Color(176, 224, 230));
		btnViewOrders.setBounds(32, 207, 252, 80);
		getContentPane().add(btnViewOrders);
		
		
		JButton btnViewVinyl = new JButton("View Priducts in Store");
		btnViewVinyl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnViewVinyl.setBackground(new Color(176, 224, 230));
		btnViewVinyl.setBounds(32, 399, 252, 80);
		getContentPane().add(btnViewVinyl);
		
		JTextPane txtpnEditDate = new JTextPane();
		txtpnEditDate.setBackground(new Color(245, 245, 245));
		txtpnEditDate.setFont(new Font("Segoe UI Semibold", Font.BOLD, 25));
		txtpnEditDate.setBounds(381, 128, 70, 39);
		getContentPane().add(txtpnEditDate);
		txtpnEditDate.setText("Edit Data");
		
		JTextPane txtpnView = new JTextPane();
		txtpnView.setBackground(new Color(245, 245, 245));
		txtpnView.setFont(new Font("Segoe UI Semibold", Font.BOLD, 25));
		txtpnView.setBounds(140, 128, 95, 39);
		getContentPane().add(txtpnView);
		txtpnView.setText("View");
		
		JButton btnBack = new JButton("Logout");
		btnBack.setBackground(new Color(192, 192, 192));
		btnBack.setBounds(0, 0, 86, 29);
		getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(getFrame(), "GoodBy! =]");
				LoginWindow LW = new LoginWindow();
				LW.getFrame().setVisible(true);
				dispose();
			
				
			}
		});
		btnViewVinyl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ShowProducts products = new ShowProducts();
				products.getFrame().setVisible(true);
				dispose();
				
			}
		
		});
		btnViewOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				OrderInformation orderInformation = new OrderInformation();
				orderInformation.setVisible(true);
				dispose();
				
			}
		});
		btnAddVinyl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				AddVinyl addVinyl = new AddVinyl();
				addVinyl.setVisible(true);
				dispose();
				
			}
		});
		btnAddNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddNewEmployee addEmployee = new  AddNewEmployee(); 
				addEmployee.setVisible(true); 
				dispose();
				
				
			}
		});
	btnViewEmployees.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			ViewEmployees viewEmployees = new ViewEmployees(); 
			viewEmployees.setVisible(true);
			dispose();
			
			
		}
	});
	
	}
	
	public JFrame getFrame() {
		return this;
	}
	
	
}
