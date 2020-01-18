package CustomerGUI;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Classes.Customer;
import Classes.Order;
import Classes.Store;
import Exceptions.IllegalVinylPrice;
import JDBC.DBVinylStore;

import javax.swing.JScrollPane;

public class CustomerOrdersGUI {

	private JFrame customerOrdersWindow;
	private Customer customer;
	private JTable table;
	private JTextArea totalPriceField;
	private JTextArea orderDetailsField;
	private DefaultTableModel model;
	private Order selectedOrder = null;
	private String totalPrice;
	private static DBVinylStore db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerOrdersGUI window = new CustomerOrdersGUI("111111111");
					window.customerOrdersWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerOrdersGUI(String customerID) {

		db = new DBVinylStore();

		this.customer = db.getCustomerByID(customerID);

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		customerOrdersWindow = new JFrame();
		customerOrdersWindow.setTitle("Vinyl Store - My Orders - " + this.customer.getUsername());
		customerOrdersWindow.setBounds(100, 100, 980, 611);
		customerOrdersWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		customerOrdersWindow.getContentPane().setLayout(null);

		// set Icon
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		customerOrdersWindow.setIconImage(img.getImage());

		JMenuItem menuItem = new JMenuItem("< Back to Main Window");
		menuItem.setBounds(0, 0, 243, 31);
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CustomerGUI customerWindow = new CustomerGUI(customer.getID());
				customerWindow.getWindow().setVisible(true);
				customerOrdersWindow.setVisible(false);
				customerOrdersWindow.dispose();
			}
		});

		this.table = new JTable();	
		table.setDefaultEditor(Object.class, null);
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				totalPrice = "";
				int selectedRowIndex = table.getSelectedRow();
				int orderID = (int) model.getValueAt(selectedRowIndex, 0);

				selectedOrder = db.getOrderByID(orderID);

				orderDetailsField.setText(db.getOrderDescription(orderID));

				totalPrice = db.getOrderTotalPrice(orderID) + "$";
				totalPriceField.setText(totalPrice);

			}
		});

		//headers for the table
		String[] columns = new String[] {
				"ID", "Order Date", "Status", "Delivary Date" 
		};

		this.model = new DefaultTableModel();
		this.model.setColumnIdentifiers(columns);
		this.table.setModel(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setAlignmentX(SwingConstants.CENTER);
		table.setRowHeight(22);

		ArrayList<Order> orders = db.getOrdersByCustomerID(customer.getID());
		for(Order order : orders) {

			Object[] row = new Object[4];

			row[0] = order.getOrderID();
			row[1] = order.getOrderDate();
			row[2] = order.getStatus();

			if(order.getDeliveryDate() == null) {
				row[3] = " - ";
			}
			else {
				row[3] = order.getDeliveryDate();
			}
			this.model.addRow(row);
		}

		customerOrdersWindow.getContentPane().add(menuItem);

		JLabel lblMyOrders = new JLabel("My Orders:");
		lblMyOrders.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblMyOrders.setBounds(60, 60, 187, 31);
		customerOrdersWindow.getContentPane().add(lblMyOrders);

		JLabel lblOrderdetails = new JLabel("Order Details:");
		lblOrderdetails.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblOrderdetails.setBounds(658, 60, 187, 32);
		customerOrdersWindow.getContentPane().add(lblOrderdetails);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 107, 566, 431);
		customerOrdersWindow.getContentPane().add(scrollPane);

		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(658, 108, 261, 280);
		customerOrdersWindow.getContentPane().add(scrollPane_1);

		this.orderDetailsField = new JTextArea();
		orderDetailsField.setEditable(false);
		orderDetailsField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_1.setViewportView(orderDetailsField);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(658, 479, 261, 51);
		customerOrdersWindow.getContentPane().add(scrollPane_2);

		this.totalPriceField = new JTextArea();
		totalPriceField.setEditable(false);
		this.totalPriceField.setFont(new Font("Arial", Font.PLAIN, 17));
		scrollPane_2.setViewportView(totalPriceField);

		JLabel lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTotalPrice.setBounds(658, 432, 187, 31);
		customerOrdersWindow.getContentPane().add(lblTotalPrice);
	}

	public JFrame getWindow() {
		return this.customerOrdersWindow;
	}
}
