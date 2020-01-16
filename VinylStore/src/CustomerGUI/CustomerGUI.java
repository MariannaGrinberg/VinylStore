package CustomerGUI;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.sun.glass.events.WindowEvent;

import Classes.Customer;
import Classes.Store;
import JDBC.DBVinylStore;
import Main.LoginWindow;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class CustomerGUI {

	private JFrame customerWindow;
	private Customer customer;
	private static DBVinylStore db;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerGUI window = new CustomerGUI("111111111");
					window.customerWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerGUI(String customerID) {
		
		db = new DBVinylStore();

		this.customer = db.getCustomerByID(customerID);

		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		customerWindow = new JFrame();
		customerWindow.getContentPane().setBackground(new Color(245, 245, 245));
		customerWindow.setTitle("Vinyl Store");
		customerWindow.setBounds(100, 100, 488, 542);
		customerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		customerWindow.getContentPane().setLayout(null);
		
		// set Icon
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		customerWindow.setIconImage(img.getImage());
		
		
		JButton btnShowProducts = new JButton("Show Products");
		btnShowProducts.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnShowProducts.setBackground(new Color(176, 224, 230));
		btnShowProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductsGUI productsWindow = new ProductsGUI(customer.getID());
				productsWindow.getWindow().setVisible(true);
				customerWindow.setVisible(false);
				customerWindow.dispose();
			}
		});
		btnShowProducts.setBounds(128, 104, 208, 81);
		customerWindow.getContentPane().add(btnShowProducts);
		
		JButton btnMyCart = new JButton("My Cart");
		btnMyCart.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnMyCart.setBackground(new Color(176, 224, 230));
		btnMyCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(db.customerCartIsEmpty(customer.getID())) {
					JOptionPane.showMessageDialog(customerWindow, "Your Cart is Empty!!");
				}
				else {
					MyCartGUI myCartWindow = new MyCartGUI(customer.getID());
					myCartWindow.getWindow().setVisible(true);
					customerWindow.setVisible(false);
					customerWindow.dispose();
				}
			}
		});
		btnMyCart.setBounds(128, 201, 208, 81);
		customerWindow.getContentPane().add(btnMyCart);
		
		JButton btbMyOrders = new JButton("My Orders");
		btbMyOrders.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btbMyOrders.setBackground(new Color(176, 224, 230));
		btbMyOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!db.customerHasOrders(customer.getID())) {
					JOptionPane.showMessageDialog(customerWindow, "You don't have any orders in your account!!");
				}
				else {
					CustomerOrdersGUI customerOrderWindow = new CustomerOrdersGUI(customer.getID());
					customerOrderWindow.getWindow().setVisible(true);
					customerWindow.setVisible(false);
					customerWindow.dispose();
				}
			}
		});
		btbMyOrders.setBounds(128, 298, 208, 81);
		customerWindow.getContentPane().add(btbMyOrders);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.setBounds(324, 455, 142, 31);
		
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(customerWindow, "GoodBy! =]");
				LoginWindow LW = new LoginWindow();
				LW.getFrame().setVisible(true);
				customerWindow.dispose();
				
			}
		});
		
		customerWindow.getContentPane().add(mntmLogout);
		
		JLabel lblWelcome = new JLabel("Welcome " + this.customer.getUsername());
		lblWelcome.setFont(new Font("Arial", Font.PLAIN, 17));
		lblWelcome.setBounds(12, 13, 277, 20);
		customerWindow.getContentPane().add(lblWelcome);
	}
	
	public JFrame getWindow() {
		return this.customerWindow;
	}
}
