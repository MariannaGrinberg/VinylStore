package EmployeeGUI;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Classes.Store;
import Classes.Vinyl;

import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class ShowProducts {

	private JFrame productsWindow;
	private JTable table;
	private Store store;
	private DefaultTableModel model;
	private JTextArea descriptionField;
	private Vinyl selectedProduct = null; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println(Store);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowProducts window = new ShowProducts();
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
	public ShowProducts() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		productsWindow = new JFrame();
		productsWindow.setTitle("Vinyl Store - Products");
		productsWindow.setBounds(100, 100, 1047, 612);
		productsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		productsWindow.getContentPane().setLayout(null);
		
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		productsWindow.setIconImage(img.getImage());
		
		FileInputStream file;
		try {
		file = new FileInputStream(new File("store.ser"));

		ObjectInputStream obj;
		obj = new ObjectInputStream(file);


		// Read objects
		this.store = (Store) obj.readObject();
		
		obj.close();
		file.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel lblProductsInStore = new JLabel("Products in Store:");
		lblProductsInStore.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
		lblProductsInStore.setBounds(15, 74, 228, 38);
		productsWindow.getContentPane().add(lblProductsInStore);
		
		JTextPane txtpnDescriptionfield = new JTextPane();
		txtpnDescriptionfield.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
		txtpnDescriptionfield.setBackground(SystemColor.menu);
		txtpnDescriptionfield.setText("Description:");
		txtpnDescriptionfield.setBounds(849, 74, 136, 38);
		productsWindow.getContentPane().add(txtpnDescriptionfield);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 116, 793, 370);
		productsWindow.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int selectedRowIndex = table.getSelectedRow();
		
				int productID = (int) model.getValueAt(selectedRowIndex, 0);
			
				selectedProduct = store.getProductByID(productID);
				
				descriptionField.setText(selectedProduct.getDescription());
				
			}
		});
        
		//headers for the table
        String[] columns = new String[] {
            "ID", "Name", "Release Year", "Format", "Condition", "Price ($)" 
        };
        
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        table.setModel(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 17));
        table.setAlignmentX(SwingConstants.CENTER);
        table.setRowHeight(22);
        
		
		for(Vinyl product : this.store.getProducts()) {
			
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
		menuItem.setBounds(0, 0, 243, 31);
		productsWindow.getContentPane().add(menuItem);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(823, 116, 187, 370);
		productsWindow.getContentPane().add(scrollPane_1);
		
		descriptionField = new JTextArea();
		descriptionField.setEditable(false);
		scrollPane_1.setViewportView(descriptionField);
		descriptionField.setForeground(new Color(0, 0, 0));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminPage AP = new AdminPage();
				AP.getFrame().setVisible(true);
				productsWindow.dispose();
			
				
			}
		});
		
		
	
	}
	
	public JFrame getFrame() {
		return productsWindow;
	}
}
