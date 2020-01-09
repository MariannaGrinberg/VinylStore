package EmployeeGUI;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;

import Classes.Employee;
import Classes.Store;
import Classes.Vinyl;

import javax.swing.JFormattedTextField;

public class ViewEmployees  extends JFrame{
	
	public static Store store; 
	private JTable table;
	private DefaultTableModel model;
	
	public ViewEmployees() {
		
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		this.setIconImage(img.getImage());
		
		getContentPane().setBackground(new Color(245, 245, 245));
		
		deserialize("store.ser"); 
		getContentPane().setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		setTitle("View Employees");
		getContentPane().setLayout(null);
		setSize(1151,565); 
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(211, 211, 211));
		btnBack.setBounds(0, 0, 80, 29);
		getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminPage AP = new AdminPage();
				AP.getFrame().setVisible(true);
				dispose();
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 93, 1099, 400);
		getContentPane().add(scrollPane);
	
		 table = new JTable();
		 table.setDefaultEditor(Object.class, null);
		//headers for the table
        String[] columns = new String[] {
            "ID", "Fist name", "Last Name", "Phone Numbe", "Email", "Start Working Date" 
      
        };
        
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 17));
        table.setRowHeight(22);
        
        
  
		for(Employee employee : store.getEmployees()) {
			
			Object[] row = new Object[6];
			
			row[0] = employee.getID();
			row[1] = employee.getFirstName();
			row[2] = employee.getLastName();
			row[3] = employee.getPhoneNumber();
			row[4] = employee.getEmail();
			row[5] = employee.getStartWorkingDate();
			
			model.addRow(row);
			
        }
		
		table.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		scrollPane.setViewportView(table);
		
		JTextPane txtpnEmployeesWhoWork = new JTextPane();
		txtpnEmployeesWhoWork.setForeground(new Color(95, 158, 160));
		txtpnEmployeesWhoWork.setBackground(new Color(245, 245, 245));
		txtpnEmployeesWhoWork.setFont(new Font("Segoe UI", Font.BOLD, 23));
		txtpnEmployeesWhoWork.setText("Employees Who Work In A Vinyl Store");
		txtpnEmployeesWhoWork.setBounds(341, 40, 415, 37);
		getContentPane().add(txtpnEmployeesWhoWork);
	
	}
	
	private static void deserialize(String fileName) {
		
		try {
			
			FileInputStream fileIn = 
					new FileInputStream(fileName);
			
			ObjectInputStream in = 
					new ObjectInputStream(fileIn);
			
				store = (Store) in.readObject();
				
				fileIn.close();
				in.close();

			
		} catch (IOException | ClassNotFoundException e) {
			
			System.out.println(e.getMessage());
			
		}

		
	}
}
