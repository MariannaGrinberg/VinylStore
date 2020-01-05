package EmployeeGUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import Classes.Address;
import Classes.Customer;
import Classes.Employee;
import Classes.Order;
import Classes.Store;
import Classes.Vinyl;
import Exceptions.IllegalVinylPrice;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class OrderInformation  extends JFrame {
	
	public static Store store; 
	private JTable tablehandled;
	private JTable tableRequireCare;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private JTextArea textAreaHandled;
	private JTextArea textAreaRequire;
	
	public OrderInformation() {
		
		deserialize("store.ser");
		setTitle("OrderDetails");
		getContentPane().setLayout(null);
		setSize(866,608);
		
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		this.setIconImage(img.getImage());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 173, 403, 249);
		getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(426, 173, 403, 249);
		getContentPane().add(scrollPane_1);
		
		JTextPane txtpnOrdersHandled = new JTextPane();
		txtpnOrdersHandled.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtpnOrdersHandled.setText("Orders handled");
		txtpnOrdersHandled.setBounds(126, 131, 149, 26);
		getContentPane().add(txtpnOrdersHandled);
		
		JTextPane txtpnOrdersThatRequire = new JTextPane();
		txtpnOrdersThatRequire.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtpnOrdersThatRequire.setText("Orders that require care");
		txtpnOrdersThatRequire.setBounds(534, 131, 219, 26);
		getContentPane().add(txtpnOrdersThatRequire);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(0, 0, 84, 29);
		getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminPage AP = new AdminPage();
				AP.getFrame().setVisible(true);
				dispose();
				
			}
		});
		
		JTextPane txtpnOrdersDetails = new JTextPane();
		txtpnOrdersDetails.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		txtpnOrdersDetails.setText("Orders Details");
		txtpnOrdersDetails.setBounds(325, 55, 149, 26);
		getContentPane().add(txtpnOrdersDetails);
		
		textAreaHandled = new JTextArea();
		textAreaHandled.setBounds(15, 430, 401, 106);
		getContentPane().add(textAreaHandled);
		
		textAreaRequire = new JTextArea();
		textAreaRequire.setBounds(426, 430, 403, 106);
		getContentPane().add(textAreaRequire);
		
		tablehandled = new JTable();
		tablehandled.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int selectedRowIndex = tablehandled.getSelectedRow();
		
				int orderID = (int) tablehandled.getValueAt(selectedRowIndex, 0);
			
				
				String text = store.getOrderByID(orderID).toString();
				textAreaHandled.setText(text);
				
			}
		});
		
	    tableRequireCare = new JTable();
	    tableRequireCare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int selectedRowIndex = tableRequireCare.getSelectedRow();
		
				int orderID = (int) tableRequireCare.getValueAt(selectedRowIndex, 0);
			
				
				String text = store.getOrderByID(orderID).toString();
				textAreaRequire.setText(text);
				
			}
		});
			//headers for the table
	        String[] columns = new String[] {
	            "orderID", "Customer name", "orderDate", "deliveryDate", "shipAddress", "totalPrice" 
	      
	        };
	        
	        model1 = new DefaultTableModel();
	        model1.setColumnIdentifiers(columns);
	        tablehandled.setModel(model1);
	        
	        model2 = new DefaultTableModel();
	        model2.setColumnIdentifiers(columns);
	        tableRequireCare.setModel(model1);
	        
	  
			for(Order order : store.getOrders()) {
				
				if(order.getEmployee() == null) {
				
					Object[] row = new Object[6];
					
					row[0] = order.getOrderID();
					row[1] = order.getCustomer().getFirstName()+" "+order.getCustomer().getLastName();
					row[2] = order.getOrderDate();
					row[3] = order.getDeliveryDate();
					row[4] = order.getShipAddress();
					try {
						row[5] = order.getTotalPrice();
					} catch (IllegalVinylPrice e) {
						row[5]= " ";
					}
					
					model2.addRow(row);
				
				}
				
				else {
					
					Object[] row = new Object[6];
					
					row[0] = order.getOrderID();
					row[1] = order.getCustomer().getFirstName()+" "+order.getCustomer().getLastName();
					row[2] = order.getOrderDate();
					row[3] = order.getDeliveryDate();
					row[4] = order.getShipAddress();
					try {
						row[5] = order.getTotalPrice();
					} catch (IllegalVinylPrice e) {
						row[5]= " ";
					}
					
					model1.addRow(row);
					
					
				}
				
	        }
			
			tablehandled.setFont(new Font("Segoe UI", Font.PLAIN, 17));
			scrollPane.setViewportView(tablehandled);
			
			tableRequireCare.setFont(new Font("Segoe UI", Font.PLAIN, 17));
			scrollPane_1.setViewportView(tableRequireCare);
		
		
	}

private static void deserialize(String fileName) {
		
		try {
			
			FileInputStream fileIn = 
					new FileInputStream(fileName);
			
			ObjectInputStream in = 
					new ObjectInputStream(fileIn);
			
				store = (Store) in.readObject();

			
		} catch (IOException | ClassNotFoundException e) {
			
			System.out.println(e.getMessage());
			
		}

		
	}

}
