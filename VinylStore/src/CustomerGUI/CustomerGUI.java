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

public class CustomerGUI {

	private JFrame customerWindow;
	private Store store;
	private Customer customer;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerGUI window = new CustomerGUI("1");
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
		
		// get store from file
		FileInputStream file;
		try {
		file = new FileInputStream(new File("store.ser"));

		ObjectInputStream obj;
		obj = new ObjectInputStream(file);


		// Read objects
		this.store = (Store) obj.readObject();
		
		obj.close();
		file.close();
		
		this.customer = this.store.getCustomerByID(customerID);
		
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		customerWindow = new JFrame();
		customerWindow.setTitle("Vinyl Store");
		customerWindow.setBounds(100, 100, 451, 518);
		customerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		customerWindow.getContentPane().setLayout(null);
		
		// set Icon
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		customerWindow.setIconImage(img.getImage());
		
		
		JButton btnShowProducts = new JButton("Show Products");
		btnShowProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductsGUI productsWindow = new ProductsGUI(customer.getID());
				productsWindow.getWindow().setVisible(true);
				customerWindow.setVisible(false);
				customerWindow.dispose();
			}
		});
		btnShowProducts.setBounds(120, 89, 172, 81);
		customerWindow.getContentPane().add(btnShowProducts);
		
		JButton btnMyCart = new JButton("My Cart");
		btnMyCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(customer.getCart().isEmpty()) {
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
		btnMyCart.setBounds(120, 186, 172, 81);
		customerWindow.getContentPane().add(btnMyCart);
		
		JButton btbMyOrders = new JButton("My Orders");
		btbMyOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(store.getOrdersByCustomerID(customer.getID()).isEmpty()) {
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
		btbMyOrders.setBounds(120, 283, 172, 81);
		customerWindow.getContentPane().add(btbMyOrders);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.setBounds(322, 431, 142, 31);
		
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
		lblWelcome.setBounds(15, 16, 277, 20);
		customerWindow.getContentPane().add(lblWelcome);
	}
	
	public JFrame getWindow() {
		return this.customerWindow;
	}
}
