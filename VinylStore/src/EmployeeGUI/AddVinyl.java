package EmployeeGUI;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Classes.Song;
import Classes.Store;
import Classes.Vinyl;
import Exceptions.IllegalVinylPrice;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import enums.Format;
import enums.Genre;
import enums.City;
import enums.Condition;
import java.awt.Color;

public class AddVinyl extends JFrame{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -7451067172389618906L;
	
	private ArrayList<Song> songs = new ArrayList<>();
	 public static Store store; 
	 
	public AddVinyl() {
		
		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		this.setIconImage(img.getImage());
		
		getContentPane().setBackground(new Color(248, 248, 255));
		setTitle("Add Vinyl");
		getContentPane().setLayout(null);
		setSize(523,594);
		
		JTextPane txtpnAddNewVinyl = new JTextPane();
		txtpnAddNewVinyl.setForeground(new Color(204, 51, 153));
		txtpnAddNewVinyl.setBackground(new Color(248, 248, 255));
		txtpnAddNewVinyl.setFont(new Font("Segoe UI", Font.BOLD, 19));
		txtpnAddNewVinyl.setText("Add New Vinyl to Store");
		txtpnAddNewVinyl.setBounds(125, 41, 240, 26);
		getContentPane().add(txtpnAddNewVinyl);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(211, 211, 211));
		btnBack.setBounds(0, 0, 71, 29);
		getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminPage AP = new AdminPage();
				AP.getFrame().setVisible(true);
				dispose();
				
			}
		});
		
		JButton btnAddSong = new JButton("Add Song");
		btnAddSong.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddSong.setForeground(Color.WHITE);
		btnAddSong.setBackground(new Color(204, 51, 153));
		btnAddSong.setBounds(177, 96, 115, 29);
		getContentPane().add(btnAddSong);
		btnAddSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			Song song = addNewSong(); 
			songs.add(song);
				
			}
		});
		
		
		JLabel lblVinylName = new JLabel("Vinyl Name: ");
		lblVinylName.setBounds(62, 149, 94, 20);
		getContentPane().add(lblVinylName);
		
		TextField nameFiled = new TextField();
		nameFiled.setBackground(new Color(245, 245, 245));
		nameFiled.setForeground(new Color(0, 0, 0));
		lblVinylName.setLabelFor(nameFiled);
		nameFiled.setBounds(156, 149, 121, 24);
		getContentPane().add(nameFiled);
		
		JLabel lblDescription = new JLabel("Description: ");
		lblDescription.setBounds(62, 185, 94, 20);
		getContentPane().add(lblDescription);
		
		TextField descriptionFiled = new TextField();
		descriptionFiled.setBackground(new Color(245, 245, 245));
		descriptionFiled.setForeground(new Color(0, 0, 0));
		lblDescription.setLabelFor(descriptionFiled);
		descriptionFiled.setBounds(157, 185, 300, 71);
		getContentPane().add(descriptionFiled);
		
		JComboBox comboBoxFormat = new JComboBox();
		comboBoxFormat.setBackground(new Color(245, 245, 245));
		comboBoxFormat.setModel(new DefaultComboBoxModel(Format.values()));
		comboBoxFormat.setBounds(156, 288, 71, 26);
		getContentPane().add(comboBoxFormat);
		
		JLabel lblFormat = new JLabel("Format:");
		lblFormat.setLabelFor(comboBoxFormat);
		lblFormat.setBounds(60, 291, 69, 20);
		getContentPane().add(lblFormat);
		
		JComboBox comboBoxCondition = new JComboBox();
		comboBoxCondition.setBackground(new Color(245, 245, 245));
		comboBoxCondition.setModel(new DefaultComboBoxModel(Condition.values()));
		comboBoxCondition.setBounds(156, 326, 71, 26);
		getContentPane().add(comboBoxCondition);
		
		JLabel lblCondition = new JLabel("Condition:");
		lblCondition.setLabelFor(comboBoxCondition);
		lblCondition.setBounds(58, 329, 83, 20);
		getContentPane().add(lblCondition);
		
		JLabel lblReleaseYear = new JLabel("Release Year:");
		lblReleaseYear.setBounds(62, 376, 103, 20);
		getContentPane().add(lblReleaseYear);
		
		TextField yearFiled = new TextField();
		yearFiled.setBackground(new Color(245, 245, 245));
		yearFiled.setForeground(new Color(0, 0, 0));
		lblReleaseYear.setLabelFor(yearFiled);
		yearFiled.setBounds(177, 376, 100, 24);
		getContentPane().add(yearFiled);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(62, 419, 57, 20);
		getContentPane().add(lblPrice);
		
		TextField priceFiled = new TextField();
		priceFiled.setBackground(new Color(245, 245, 245));
		priceFiled.setForeground(new Color(0, 0, 0));
		lblPrice.setLabelFor(priceFiled);
		priceFiled.setBounds(125, 419, 100, 24);
		getContentPane().add(priceFiled);
		
		JLabel lblDiscount = new JLabel("discount:");
		lblDiscount.setBounds(245, 419, 69, 20);
		getContentPane().add(lblDiscount);
		
		TextField discountFiled = new TextField();
		discountFiled.setBackground(new Color(245, 245, 245));
		discountFiled.setForeground(new Color(0, 0, 0));
		lblDiscount.setLabelFor(discountFiled);
		discountFiled.setBounds(320, 419, 94, 24);
		getContentPane().add(discountFiled);
	
		
		JButton submit2 = new JButton("Submit");
		submit2.setFont(new Font("Tahoma", Font.BOLD, 16));
		submit2.setForeground(new Color(255, 255, 255));
		submit2.setBackground(new Color(51, 51, 102));
		submit2.setBounds(177, 493, 115, 29);
		getContentPane().add(submit2);
		submit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent f) {
				
				
				try {
					
					Vinyl newVinyl	= new Vinyl(nameFiled.getText(), descriptionFiled.getText(), yearFiled.getText(), 
							(Format) comboBoxFormat.getSelectedItem(), (Condition) comboBoxCondition.getSelectedItem(), 
							Float.valueOf(priceFiled.getText()),Float.valueOf(discountFiled.getText()));
			
					
					for(Song song: songs) 
						newVinyl.addSong(song);
						
					deserialize("store.ser"); 
					store.addProduct(newVinyl);
					serialize("store.ser"); 
				
				} catch (NumberFormatException | IllegalVinylPrice f2) {
					// TODO Auto-generated catch block
					System.out.println(f2.getMessage());
				}
				
				
			}
		});
	}

	protected Song addNewSong() {
		
		JPanel Panel = new JPanel(new GridLayout(10,1));
		
		JLabel songName = new JLabel("  Type Song Name: ");
		JTextField Name = new JTextField(10); 
		JLabel artistName = new JLabel("  Type Artist Name: ");
		JTextField Artist = new JTextField(10); 
		JLabel genre = new JLabel("  Choose Genre: ");
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(Genre.values()));
		
		 Panel.add(songName); Panel.add(Name); 
		 Panel.add(artistName); Panel.add(Artist); 
		 Panel.add(genre); Panel.add(comboBox); 
		 
		 int ans = JOptionPane.showConfirmDialog(null, Panel, "Add Song", JOptionPane.OK_CANCEL_OPTION);

		if (ans == JOptionPane.OK_OPTION) {
				
			try {
				
					String name =  Name.getText(); 
					String artist = Artist.getText(); 
					Genre ganreSelected = (Genre) comboBox.getSelectedItem(); 
					
					return new Song(name, artist, ganreSelected); 
						
				                                        
					} 
					catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),"Error",
								JOptionPane.ERROR_MESSAGE);
					                     
					}
	
	}
		return null;
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
