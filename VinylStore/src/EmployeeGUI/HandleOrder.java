package EmployeeGUI;

import javax.swing.JFrame;

import Classes.Employee;
import Classes.Order;
import Classes.Store;
import Classes.Vinyl;
import Exceptions.IlegalDate;
import Exceptions.IllegalVinylPrice;

import javax.swing.ImageIcon;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class HandleOrder extends JFrame {
	
	static Store store; 
	
	public HandleOrder(int OrderID){
		getContentPane().setBackground(new Color(245, 245, 245));
		setTitle("Handle Order");
		getContentPane().setLayout(null);
		
		deserialize("store.ser");
		
		JTextPane txtpnHandleOrderNumber = new JTextPane();
		txtpnHandleOrderNumber.setBackground(new Color(245, 245, 245));
		txtpnHandleOrderNumber.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 29));
		txtpnHandleOrderNumber.setText("Handle Order Number: "+OrderID);
		txtpnHandleOrderNumber.setBounds(205, 55, 339, 47);
		getContentPane().add(txtpnHandleOrderNumber);
		
		JLabel lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblCustomer.setBounds(39, 146, 130, 30);
		getContentPane().add(lblCustomer);
		
		JTextArea textAreaCustomerDetails = new JTextArea();
		textAreaCustomerDetails.setBorder(new LineBorder(SystemColor.textInactiveText));
		textAreaCustomerDetails.setEditable(false);
		textAreaCustomerDetails.setFont(new Font("Courier New", Font.PLAIN, 20));
		textAreaCustomerDetails.setBounds(172, 146, 497, 114);
		getContentPane().add(textAreaCustomerDetails);
		textAreaCustomerDetails.setText(store.getOrderByID(OrderID).getCustomer()+"");
		
		JLabel lblProducts = new JLabel("products:");
		lblProducts.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblProducts.setBounds(39, 276, 117, 30);
		getContentPane().add(lblProducts);
		
		
		JLabel lblOrderdate = new JLabel("Order Date:");
		lblOrderdate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblOrderdate.setBounds(39, 556, 142, 30);
		getContentPane().add(lblOrderdate);
		
		
		JTextArea textAreaDate = new JTextArea();
		textAreaDate.setBorder(new LineBorder(SystemColor.textInactiveText));
		textAreaDate.setEditable(false);
		textAreaDate.setTabSize(10);
		textAreaDate.setFont(new Font("Courier New", Font.PLAIN, 22));
		textAreaDate.setBounds(172, 561, 173, 30);
		getContentPane().add(textAreaDate);
		textAreaDate.setText(store.getOrderByID(OrderID).getOrderDate()+"");
		
		JLabel lblAdress = new JLabel("Address:");
		lblAdress.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblAdress.setBounds(39, 437, 117, 20);
		getContentPane().add(lblAdress);
		
		JTextArea textAreaAdress = new JTextArea();
		textAreaAdress.setBorder(new LineBorder(SystemColor.textInactiveText));
		textAreaAdress.setEditable(false);
		textAreaAdress.setFont(new Font("Courier New", Font.PLAIN, 20));
		textAreaAdress.setBounds(172, 443, 497, 68);
		getContentPane().add(textAreaAdress);
		textAreaAdress.setText(store.getOrderByID(OrderID).getShipAddress()+"");
		
		
		JLabel lblNewLabel = new JLabel("Time To Deliver:");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblNewLabel.setBounds(360, 556, 184, 30);
		getContentPane().add(lblNewLabel);
		
		JLabel lblEmployee = new JLabel("Employee ID:");
		lblEmployee.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblEmployee.setBounds(39, 660, 130, 30);
		getContentPane().add(lblEmployee);
		
		TextField textFieldEmployeeID = new TextField();
		textFieldEmployeeID.setFont(new Font("Dialog", Font.PLAIN, 21));
		textFieldEmployeeID.setBounds(172, 660, 173, 30);
		getContentPane().add(textFieldEmployeeID);
		

		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 22));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8"}));
		comboBox.setBounds(559, 559, 61, 30);
		getContentPane().add(comboBox);
		setSize(745,990);
		
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		this.setIconImage(img.getImage());
		
		JButton btnApplyChanges = new JButton("apply changes");
		btnApplyChanges.setBackground(new Color(102, 205, 170));
		btnApplyChanges.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnApplyChanges.setBounds(252, 841, 177, 47);
		getContentPane().add(btnApplyChanges);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(172, 276, 497, 145);
		getContentPane().add(scrollPane);
		
		JTextArea textAreaProducts = new JTextArea();
		textAreaProducts.setEditable(false);
		scrollPane.setViewportView(textAreaProducts);
		
		
		textAreaProducts.setText(store.getOrderByID(OrderID)+"");
		textAreaProducts.setFont(new Font("Courier New", Font.PLAIN, 20));
		
		JLabel labeltotalPrice = new JLabel("Total Price:");
		labeltotalPrice.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		labeltotalPrice.setBounds(39, 750, 130, 30);
		getContentPane().add(labeltotalPrice);
		
		JTextArea textAreaTotalPrice = new JTextArea();
		try {
			textAreaTotalPrice.setText(store.getOrderByID(OrderID).getTotalPrice()+"");
		} catch (IllegalVinylPrice e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		textAreaTotalPrice.setTabSize(10);
		textAreaTotalPrice.setFont(new Font("Courier New", Font.PLAIN, 22));
		textAreaTotalPrice.setEditable(false);
		textAreaTotalPrice.setBorder(new LineBorder(SystemColor.textInactiveText));
		textAreaTotalPrice.setBounds(172, 750, 173, 30);
		getContentPane().add(textAreaTotalPrice);
		
		btnApplyChanges.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				try {
									
					Employee employee =  store.getEmployeeByID(textFieldEmployeeID.getText());
					store.getOrderByID(OrderID).setEmployee(employee);
			
				
					LocalDate DeliverDate = store.getOrderByID(OrderID).getOrderDate().plusDays
							(Integer.valueOf(comboBox.getSelectedItem().toString()));
					store.getOrderByID(OrderID).setDeliveryDate(DeliverDate);

					JOptionPane.showMessageDialog(getContentPane(), "Order Number: "+OrderID+ " Is Handled!");
				
					serialize("store.ser"); 
					
				} catch (IlegalDate e1) {
					 //TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				}
				
				
				OrderInformation OI = new OrderInformation(); 
				OI.getFrame().setVisible(true);
				dispose();
						
				
			}
		});
		
	
	}
	
	
	public JFrame getFrame() {
		return this;
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
}
