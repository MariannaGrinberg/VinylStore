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
import javax.swing.UIManager;
import java.awt.SystemColor;

public class AdminPage extends JFrame{

	private static final long serialVersionUID = 1L;

	public AdminPage(String ID) {
		
		initialize(); 
	}
	
	public AdminPage(){
		getContentPane().setFont(new Font("Dialog", Font.PLAIN, 30));
		
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
		btnViewEmployees.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnViewEmployees.setBackground(new Color(176, 224, 230));
		btnViewEmployees.setBounds(32, 303, 252, 80);
		getContentPane().add(btnViewEmployees);
		
		JButton btnAddEmployee = new JButton("Add New Employee");
		btnAddEmployee.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnAddEmployee.setBackground(new Color(176, 224, 230));
		btnAddEmployee.setBounds(307, 303, 252, 80);
		getContentPane().add(btnAddEmployee);
		
		JButton btnAddVinyl = new JButton("Add Vinyl to Store");
		btnAddVinyl.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnAddVinyl.setBackground(new Color(176, 224, 230));
		btnAddVinyl.setBounds(307, 207, 252, 80);
		getContentPane().add(btnAddVinyl);
		
		JButton btnViewOrders = new JButton("View Orders");
		btnViewOrders.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnViewOrders.setBackground(new Color(176, 224, 230));
		btnViewOrders.setBounds(32, 207, 252, 80);
		getContentPane().add(btnViewOrders);
		
		
		JButton btnViewVinyl = new JButton("View Products in Store");
		btnViewVinyl.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnViewVinyl.setBackground(new Color(176, 224, 230));
		btnViewVinyl.setBounds(32, 399, 527, 80);
		getContentPane().add(btnViewVinyl);
		
		JTextPane txtpnEditDate = new JTextPane();
		txtpnEditDate.setBackground(new Color(245, 245, 245));
		txtpnEditDate.setFont(new Font("Dialog", Font.PLAIN, 30));
		txtpnEditDate.setBounds(400, 139, 70, 39);
		getContentPane().add(txtpnEditDate);
		txtpnEditDate.setText("Edit Data");
		
		JTextPane txtpnView = new JTextPane();
		txtpnView.setBackground(new Color(245, 245, 245));
		txtpnView.setFont(new Font("Dialog", Font.PLAIN, 30));
		txtpnView.setBounds(119, 139, 95, 39);
		getContentPane().add(txtpnView);
		txtpnView.setText("View");
		
		JButton btnBack = new JButton("Logout");
		btnBack.setBackground(new Color(192, 192, 192));
		btnBack.setBounds(0, 0, 86, 29);
		getContentPane().add(btnBack);
		
		JTextPane txtpnOptions = new JTextPane();
		txtpnOptions.setBackground(new Color(245, 245, 245));
		txtpnOptions.setFont(new Font("Tahoma", Font.PLAIN, 32));
		txtpnOptions.setText("Options");
		txtpnOptions.setBounds(246, 62, 156, 50);
		getContentPane().add(txtpnOptions);
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
