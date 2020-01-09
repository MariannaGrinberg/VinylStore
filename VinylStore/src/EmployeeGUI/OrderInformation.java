package EmployeeGUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
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
import java.awt.Color;
import javax.swing.UIManager;

public class OrderInformation  extends JFrame {
	
	public static Store store; 
	private JTable tablehandled;
	private JTable tableRequireCare;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private JTextArea textAreaHandled;
	private JTextArea textAreaRequire;
	int orderID;
	
	public OrderInformation() {
		getContentPane().setBackground(new Color(255, 250, 250));
		
		deserialize("store.ser");
		setTitle("OrderDetails");
		getContentPane().setLayout(null);
		setSize(1906,655);
		
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		this.setIconImage(img.getImage());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 173, 915, 250);
		getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(952, 173, 915, 250);
		getContentPane().add(scrollPane_1);
		
		JTextPane txtpnOrdersHandled = new JTextPane();
		txtpnOrdersHandled.setBackground(new Color(255, 250, 250));
		txtpnOrdersHandled.setFont(new Font("Segoe UI", Font.BOLD, 22));
		txtpnOrdersHandled.setText("Orders Handled");
		txtpnOrdersHandled.setBounds(316, 119, 182, 38);
		getContentPane().add(txtpnOrdersHandled);
		
		JTextPane txtpnOrdersThatRequire = new JTextPane();
		txtpnOrdersThatRequire.setBackground(new Color(255, 250, 250));
		txtpnOrdersThatRequire.setFont(new Font("Segoe UI", Font.BOLD, 22));
		txtpnOrdersThatRequire.setText("Orders That Require Care");
		txtpnOrdersThatRequire.setBounds(1278, 119, 271, 41);
		getContentPane().add(txtpnOrdersThatRequire);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(220, 220, 220));
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
		txtpnOrdersDetails.setBackground(new Color(255, 250, 250));
		txtpnOrdersDetails.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		txtpnOrdersDetails.setText("Orders Details");
		txtpnOrdersDetails.setBounds(845, 32, 236, 55);
		getContentPane().add(txtpnOrdersDetails);
		
		tablehandled = new JTable();
		tablehandled.setDefaultEditor(Object.class, null);
		
		
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
	    tableRequireCare.setDefaultEditor(Object.class, null);
	
	    tableRequireCare.setRowMargin(2);

	    tableRequireCare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedRowIndex = tableRequireCare.getSelectedRow();
		
				orderID = (int) tableRequireCare.getValueAt(selectedRowIndex, 0);
				
				
			
				String text = store.getOrderByID(orderID).toString();
				
				textAreaRequire.setText(text);
				
			}
		});
	    
	    
			//headers for the table
	        String[] columns = new String[] {
	            "orderID", "Customer ID", "Customer name", "orderDate", "deliveryDate", "shipAddress", "totalPrice" 
	      
	        };
	        
	  
	        model1 = new DefaultTableModel();
	        model1.setColumnIdentifiers(columns);
	        tablehandled.setModel(model1);
	  
	        
	        model2 = new DefaultTableModel();
	        model2.setColumnIdentifiers(columns);
	        tableRequireCare.setModel(model2);
	   
	        
	  
			for(Order order : store.getOrders()) {
				
				if(order.getEmployee() == null) {
				
					Object[] row = new Object[7];
					
					row[0] = order.getOrderID();
					row[1] = order.getCustomer().getID();
					row[2] = order.getCustomer().getFirstName();
					row[3] = order.getOrderDate();
					row[4] = order.getDeliveryDate();
					row[5] = order.getShipAddress().getCity();
					try {
						row[6] = order.getTotalPrice();
					} catch (IllegalVinylPrice e) {
						row[6]= " ";
					}
					
					model2.addRow(row);
				
				}
				
				else {
					
					Object[] row = new Object[7];
					
					row[0] = order.getOrderID();
					row[1] = order.getCustomer().getID();
					row[2] = order.getCustomer().getFirstName();
					row[3] = order.getOrderDate();
					row[4] = order.getDeliveryDate();
					row[5] = order.getShipAddress().getCity();
					try {
						row[6] = order.getTotalPrice();
					} catch (IllegalVinylPrice e) {
						row[6]= " ";
					}
					
					
					model1.addRow(row);
					
					
				}
				
	        }
			
			tablehandled.setAlignmentX(SwingConstants.CENTER);
			tablehandled.setRowHeight(26);
			
			tableRequireCare.setAlignmentX(SwingConstants.CENTER);
			tableRequireCare.setRowHeight(26);
		        
		        
			tablehandled.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
			scrollPane.setViewportView(tablehandled);
			
			
			tableRequireCare.setFont(new Font("Segoe UI", Font.PLAIN, 22));
			scrollPane_1.setViewportView(tableRequireCare);
			
			JButton btnHandle = new JButton("Handle");
			btnHandle.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnHandle.setBackground(new Color(51, 51, 102));
			btnHandle.setForeground(new Color(255, 255, 255));
			btnHandle.setBounds(1727, 130, 115, 29);
			getContentPane().add(btnHandle);
			
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(15, 430, 915, 153);
			getContentPane().add(scrollPane_2);
			
			textAreaHandled = new JTextArea();
			scrollPane_2.setViewportView(textAreaHandled);
			textAreaHandled.setBackground(UIManager.getColor("Menu.background"));
			textAreaHandled.setFont(new Font("Tahoma", Font.PLAIN, 17));
			
			JScrollPane scrollPane_3 = new JScrollPane();
			scrollPane_3.setBounds(952, 430, 915, 153);
			getContentPane().add(scrollPane_3);
			
			textAreaRequire = new JTextArea();
			scrollPane_3.setViewportView(textAreaRequire);
			textAreaRequire.setBackground(UIManager.getColor("Menu.background"));
			textAreaRequire.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnHandle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(orderID != 0) {
					HandleOrder handleOrder = new HandleOrder(orderID); 
					handleOrder.getFrame().setVisible(true);
					dispose();
				
					
					
					
				}
				
			}
		});
			
		
		
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
public JFrame getFrame() {
	return this;
}
}
