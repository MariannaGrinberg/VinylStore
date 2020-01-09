package CustomerGUI;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Classes.Customer;
import Classes.Order;
import Classes.Store;
import Classes.Vinyl;
import Exceptions.IlegalDate;
import Exceptions.IllegalVinylPrice;

import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MyCartGUI {

	private JFrame myCartWindow;
	private JTable table;
	JTextArea totalPriceField;
	private DefaultTableModel model;
	private JTextArea descriptionField;
	private Store store;
	private int productIndex = -1;
	private Vinyl selectedProduct = null;
	private Customer customer;
	private double totalPrice;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyCartGUI window = new MyCartGUI("1");
					window.myCartWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyCartGUI(String customerID) {
		
		// get store from file
		FileInputStream file;
		try {
		file = new FileInputStream(new File("store.ser"));

		ObjectInputStream obj;
		obj = new ObjectInputStream(file);


		// Read objects
		store = (Store) obj.readObject();
		
		obj.close();
		file.close();
		
		this.customer = store.getCustomerByID(customerID);
		updateTotalPrice();
		
		
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
		

		myCartWindow = new JFrame();
		myCartWindow.setTitle("Vinyl Store - MyCart - " + this.customer.getUsername());
		myCartWindow.setBounds(100, 100, 1110, 617);
		myCartWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myCartWindow.getContentPane().setLayout(null);
		
		// set Icon
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		myCartWindow.setIconImage(img.getImage());
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 120, 756, 359);
		myCartWindow.getContentPane().add(scrollPane);
		
		this.table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				productIndex = table.getSelectedRow();
				int productID = (int) model.getValueAt(productIndex, 0);
				
				selectedProduct = store.getProductByID(productID);
				
				descriptionField.setText(selectedProduct.getDescription());
				
			}
		});
        
		//headers for the table
        String[] columns = new String[] {
            "ID", "Name", "Release Year", "Format", "Condition", "Price ($)" 
        };
        
        this.model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        
		
		for(Vinyl product : this.customer.getCart()) {
			
			Object[] row = new Object[6];
			
			row[0] = product.getVinylID();
			row[1] = product.getName();
			row[2] = product.getReleaseDate();
			row[3] = product.getFormat();
			row[4] = product.getCondition();
			row[5] = product.getPrice();
			
			model.addRow(row);
		}
        
		scrollPane.setViewportView(table);
		
		JMenuItem menuItem = new JMenuItem("< Back to Main Window");
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CustomerGUI customerWindow = new CustomerGUI(customer.getID());
				customerWindow.getWindow().setVisible(true);
				myCartWindow.setVisible(false);
				myCartWindow.dispose();
			}
		});
		menuItem.setBounds(0, 0, 243, 31);
		myCartWindow.getContentPane().add(menuItem);
		
		JLabel lblYourCart = new JLabel("My Cart:");
		lblYourCart.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblYourCart.setBounds(40, 90, 187, 26);
		myCartWindow.getContentPane().add(lblYourCart);
		
		JLabel lblDescriptiom = new JLabel("Description:");
		lblDescriptiom.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblDescriptiom.setBounds(823, 90, 187, 26);
		myCartWindow.getContentPane().add(lblDescriptiom);
		
		JButton btnRemoveFromCart = new JButton("Remove from Cart");
		btnRemoveFromCart.setBackground(new Color(240, 128, 128));
		btnRemoveFromCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(productIndex != -1) {
					customer.removeFromCart(productIndex);
					
					FileOutputStream file;
					try {
					file = new FileOutputStream(new File("store.ser"));
					
					ObjectOutputStream obj = new ObjectOutputStream(file);
					
					obj.writeObject(store);
					
					obj.close();
					file.close();
					
					int rowCount = model.getRowCount();
					
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    model.removeRow(i);
					}
					
					for(Vinyl product : customer.getCart()) {
						
						Object[] row = new Object[6];
						
						row[0] = product.getVinylID();
						row[1] = product.getName();
						row[2] = product.getReleaseDate();
						row[3] = product.getFormat();
						row[4] = product.getCondition();
						row[5] = product.getPrice();
						
						model.addRow(row);
					}
					
					descriptionField.setText("");
					
					updateTotalPrice();
					totalPriceField.setText(totalPrice + "$");

					JOptionPane.showMessageDialog(myCartWindow, "Product #" + selectedProduct.getVinylID() + " was Deleted from your Cart successfully!!");

					
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(myCartWindow, "No Product Selected!!");

				}
			}
		});
		btnRemoveFromCart.setBounds(597, 501, 180, 44);
		myCartWindow.getContentPane().add(btnRemoveFromCart);
		
		JButton btnPlaceNewOrder = new JButton("Place New Order");
		btnPlaceNewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(customer.getCart().isEmpty()) {
					JOptionPane.showMessageDialog(myCartWindow, "Your Cart is Empty!!");
				}
				else {
					try {
			
						
						Order order = new Order(customer, LocalDate.now());
						ArrayList<Order> orders = store.getOrders();
						
						order.setID(store.getLastOrderID());
						
						for(Vinyl product : customer.getCart()) {
							order.addProducts(product);
						}
						
						store.addOrder(order);
						
						customer.clearCart();
						
						
						// Update Store File
						FileOutputStream file;
						file = new FileOutputStream(new File("store.ser"));
						
						ObjectOutputStream obj = new ObjectOutputStream(file);
						
						obj.writeObject(store);
						
						obj.close();
						file.close();
						
						
						int rowCount = model.getRowCount();
						
						//Remove rows one by one from the end of the table
						for (int i = rowCount - 1; i >= 0; i--) {
						    model.removeRow(i);
						}
						
						updateTotalPrice();
						totalPriceField.setText(totalPrice + "$");
						
						descriptionField.setText("");
						
						JOptionPane.showMessageDialog(myCartWindow, "Order #" + order.getOrderID() + " was Successfully Placed!!\nThank you for your Order :)");
						
						
					} catch (IllegalVinylPrice e) {
						e.printStackTrace();
					} catch (IlegalDate e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		btnPlaceNewOrder.setBackground(new Color(135, 206, 250));
		btnPlaceNewOrder.setBounds(40, 501, 180, 44);
		myCartWindow.getContentPane().add(btnPlaceNewOrder);
		
		this.totalPriceField = new JTextArea();
		totalPriceField.setEditable(false);
		this.totalPriceField.setFont(new Font("Arial", Font.PLAIN, 16));
		this.totalPriceField.setText(this.totalPrice + "$");
		
		this.totalPriceField.setBounds(811, 448, 249, 31);
		myCartWindow.getContentPane().add(this.totalPriceField);
		
		JLabel lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTotalPrice.setBounds(823, 424, 187, 20);
		myCartWindow.getContentPane().add(lblTotalPrice);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(811, 120, 249, 296);
		myCartWindow.getContentPane().add(scrollPane_1);
		
		descriptionField = new JTextArea();
		descriptionField.setEditable(false);
		scrollPane_1.setViewportView(descriptionField);
		descriptionField.setFont(new Font("Tahoma", Font.PLAIN, 17));
	}
	
	public JFrame getWindow() {
		return this.myCartWindow;
	}
	
	private void updateTotalPrice() {
		this.totalPrice = 0;
		
		for(Vinyl product : customer.getCart()) {
			this.totalPrice += product.getPrice();
		}
	}

	
	
}
