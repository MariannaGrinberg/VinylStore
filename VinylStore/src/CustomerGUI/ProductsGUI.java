package CustomerGUI;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Classes.Customer;
import Classes.Store;
import Classes.Vinyl;
import JDBC.DBVinylStore;

import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductsGUI {

	private JFrame productsWindow;
	private JTable table;
	private DefaultTableModel model;
	private JTextArea descriptionField;
	private Store store;
	private Vinyl selectedProduct = null;
	private Customer customer;
	private static DBVinylStore db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductsGUI window = new ProductsGUI("111111111");
					window.productsWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProductsGUI(String customerID) {
		
		db = new DBVinylStore();
		
		this.customer = db.getCustomerByID(customerID);

		initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		productsWindow = new JFrame();
		productsWindow.setTitle("Vinyl Store - Products - " + this.customer.getUsername());
		productsWindow.setBounds(100, 100, 1110, 617);
		productsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		productsWindow.getContentPane().setLayout(null);

		// set Icon
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		productsWindow.setIconImage(img.getImage());


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 120, 737, 359);
		productsWindow.getContentPane().add(scrollPane);

		this.table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setAlignmentX(SwingConstants.CENTER);
		table.setRowHeight(22);
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedRowIndex = table.getSelectedRow();
				int productID = (int) model.getValueAt(selectedRowIndex, 0);

				selectedProduct = db.getProductByID(productID);

				descriptionField.setText(selectedProduct.getDescription());

			}
		});

		//headers for the table
		String[] columns = new String[] {
				"ID", "Name", "Release Year", "Format", "Condition", "Price ($)" 
		};

		this.model = new DefaultTableModel();
		this.model.setColumnIdentifiers(columns);
		this.table.setModel(model);


		ArrayList<Vinyl> products = db.getProducts();


		for(Vinyl product : products) {

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
	menuItem.setFont(new Font("Segoe UI", Font.PLAIN, 19));
	menuItem.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			CustomerGUI customerWindow = new CustomerGUI(customer.getID());
			customerWindow.getWindow().setVisible(true);
			productsWindow.setVisible(false);
			productsWindow.dispose();
		}
	});
	menuItem.setBounds(0, 0, 255, 44);
	productsWindow.getContentPane().add(menuItem);

	JLabel lblProductsInStore = new JLabel("Products in Store:");
	lblProductsInStore.setFont(new Font("Tahoma", Font.BOLD, 22));
	lblProductsInStore.setBounds(40, 82, 980, 34);
	productsWindow.getContentPane().add(lblProductsInStore);

	JLabel lblDescriptiom = new JLabel("Description:");
	lblDescriptiom.setFont(new Font("Tahoma", Font.BOLD, 22));
	lblDescriptiom.setBounds(792, 82, 228, 34);
	productsWindow.getContentPane().add(lblDescriptiom);

	JButton btnAddToCart = new JButton("Add to Cart");
	btnAddToCart.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(selectedProduct != null) {
				
				db.addToCart(customer.getID(), selectedProduct.getVinylID());;

				JOptionPane.showMessageDialog(productsWindow, "Product #" + selectedProduct.getVinylID() + " was added to your Cart successfully!!");

			}
			else {
				JOptionPane.showMessageDialog(productsWindow, "No Product Selected!!");

			}
		}
	});
	btnAddToCart.setBounds(638, 501, 139, 44);
	productsWindow.getContentPane().add(btnAddToCart);

	JButton btnMyCart = new JButton("My Cart");
	btnMyCart.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(customer.getCart().isEmpty()) {
				JOptionPane.showMessageDialog(productsWindow, "Your Cart is Empty!!");
			}
			else {
				MyCartGUI myCartWindow = new MyCartGUI(customer.getID());
				myCartWindow.getWindow().setVisible(true);
				productsWindow.setVisible(false);
				productsWindow.dispose();
			}
		}
	});
	btnMyCart.setBounds(40, 501, 139, 44);
	productsWindow.getContentPane().add(btnMyCart);

	JScrollPane scrollPane_1 = new JScrollPane();
	scrollPane_1.setBounds(790, 120, 270, 359);
	productsWindow.getContentPane().add(scrollPane_1);

	descriptionField = new JTextArea();
	scrollPane_1.setViewportView(descriptionField);
	descriptionField.setFont(new Font("Tahoma", Font.PLAIN, 19));
}

public JFrame getWindow() {
	return this.productsWindow;
}
}
