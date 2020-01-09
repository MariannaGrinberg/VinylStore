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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Classes.Song;
import Classes.Store;
import Classes.Vinyl;
import Exceptions.IllegalVinylPrice;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import enums.Format;
import enums.Genre;
import enums.City;
import enums.Condition;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AddVinyl extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7451067172389618906L;

	private ArrayList<Song> songs = new ArrayList<>();
	private int songIndex = -1;
	private Song selectedSong;
	private DefaultTableModel model;
	public static Store store; 
	private JTable table;

	public AddVinyl() {


		ImageIcon img = new ImageIcon("VinylStoreIcon.png");
		this.setIconImage(img.getImage());

		getContentPane().setBackground(new Color(240, 248, 255));
		setTitle("Add Vinyl");
		getContentPane().setLayout(null);
		setSize(1060,743);

		JTextPane txtpnAddNewVinyl = new JTextPane();
		txtpnAddNewVinyl.setForeground(new Color(0, 0, 0));
		txtpnAddNewVinyl.setBackground(new Color(240, 248, 255));
		txtpnAddNewVinyl.setFont(new Font("Segoe UI", Font.BOLD, 30));
		txtpnAddNewVinyl.setText("Add New Vinyl to Store");
		txtpnAddNewVinyl.setBounds(152, 53, 359, 44);
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
		btnAddSong.setBackground(new Color(0, 51, 102));
		btnAddSong.setBounds(227, 125, 137, 44);
		getContentPane().add(btnAddSong);
		btnAddSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Song song = addNewSong(); 
				if(song != null) {
					songs.add(song);
					setModel();
					JOptionPane.showMessageDialog(getContentPane(),"Song Added Successfuly!");
				}
				else {
					JOptionPane.showMessageDialog(getContentPane(),"You can't add an empty song!!");
				}
			}
		});


		JLabel lblVinylName = new JLabel("Vinyl Name: ");
		lblVinylName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblVinylName.setBounds(67, 214, 130, 29);
		getContentPane().add(lblVinylName);

		JComboBox comboBoxFormat = new JComboBox();
		comboBoxFormat.setBackground(new Color(245, 245, 245));
		comboBoxFormat.setModel(new DefaultComboBoxModel(Format.values()));
		comboBoxFormat.setBounds(163, 304, 86, 30);
		getContentPane().add(comboBoxFormat);

		JLabel lblFormat = new JLabel("Format:");
		lblFormat.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblFormat.setLabelFor(comboBoxFormat);
		lblFormat.setBounds(67, 303, 81, 27);
		getContentPane().add(lblFormat);

		JComboBox comboBoxCondition = new JComboBox();
		comboBoxCondition.setBackground(new Color(245, 245, 245));
		comboBoxCondition.setModel(new DefaultComboBoxModel(Condition.values()));
		comboBoxCondition.setBounds(444, 304, 98, 30);
		getContentPane().add(comboBoxCondition);

		JLabel lblCondition = new JLabel("Condition:");
		lblCondition.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblCondition.setLabelFor(comboBoxCondition);
		lblCondition.setBounds(318, 302, 111, 28);
		getContentPane().add(lblCondition);

		JLabel lblReleaseYear = new JLabel("Release Year:");
		lblReleaseYear.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblReleaseYear.setBounds(67, 384, 137, 31);
		getContentPane().add(lblReleaseYear);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblPrice.setBounds(67, 469, 57, 20);
		getContentPane().add(lblPrice);

		JLabel lblDiscount = new JLabel("Discount(%):");
		lblDiscount.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblDiscount.setBounds(281, 469, 128, 30);
		getContentPane().add(lblDiscount);


		JTextArea nameFiled = new JTextArea();
		nameFiled.setFont(new Font("Courier New", Font.PLAIN, 20));
		nameFiled.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		nameFiled.setBackground(new Color(245, 245, 245));
		nameFiled.setBounds(212, 218, 330, 30);
		getContentPane().add(nameFiled);
		nameFiled.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidateVinylName(nameFiled.getText()) == true){

					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					nameFiled.setBorder(border);
				}

				else if(ValidateVinylName(nameFiled.getText()) == false) {

					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					nameFiled.setBorder(border);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {

				if(ValidateVinylName(nameFiled.getText()) == true){
					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					nameFiled.setBorder(border);
				}

				else if(ValidateVinylName(nameFiled.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					nameFiled.setBorder(border);
				}

			}

		});

		JTextArea priceFiled = new JTextArea();
		priceFiled.setFont(new Font("Courier New", Font.PLAIN, 20));
		priceFiled.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		priceFiled.setBackground(new Color(245, 245, 245));
		priceFiled.setBounds(139, 469, 130, 30);
		getContentPane().add(priceFiled);
		priceFiled.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidatePrice(priceFiled.getText()) == true){

					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					priceFiled.setBorder(border);
				}

				else if(ValidatePrice(priceFiled.getText()) == false) {

					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					priceFiled.setBorder(border);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {

				if(ValidatePrice(priceFiled.getText()) == true){
					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					priceFiled.setBorder(border);
				}

				else if(ValidatePrice(priceFiled.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					priceFiled.setBorder(border);
				}

			}

		});


		JTextArea discountFiled = new JTextArea();
		discountFiled.setFont(new Font("Courier New", Font.PLAIN, 20));
		discountFiled.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		discountFiled.setBackground(new Color(245, 245, 245));
		discountFiled.setBounds(412, 469, 130, 30);
		getContentPane().add(discountFiled);
		discountFiled.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidateDiscount(discountFiled.getText()) == true){

					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					discountFiled.setBorder(border);
				}

				else if(ValidateDiscount(discountFiled.getText()) == false) {

					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					discountFiled.setBorder(border);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {

				if(ValidateDiscount(discountFiled.getText()) == true){
					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					discountFiled.setBorder(border);
				}

				else if(ValidateDiscount(discountFiled.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					discountFiled.setBorder(border);
				}

			}

		});

		JTextArea yearFiled = new JTextArea();
		yearFiled.setFont(new Font("Courier New", Font.PLAIN, 20));
		yearFiled.setBorder(new MatteBorder(2, 2, 0, 1, (Color) UIManager.getColor("TextField.darkShadow")));
		yearFiled.setBackground(new Color(245, 245, 245));
		yearFiled.setBounds(212, 385, 130, 30);
		getContentPane().add(yearFiled);
		yearFiled.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if(ValidateReleaseYear(yearFiled.getText()) == true){

					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					yearFiled.setBorder(border);
				}

				else if(ValidateReleaseYear(yearFiled.getText()) == false) {

					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					yearFiled.setBorder(border);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {

				if(ValidateReleaseYear(yearFiled.getText()) == true){
					Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
					yearFiled.setBorder(border);
				}

				else if(ValidateReleaseYear(yearFiled.getText()) == false) {
					Border border = BorderFactory.createLineBorder(Color.RED, 2);
					yearFiled.setBorder(border);
				}

			}

		});

		JButton submit2 = new JButton("Submit");
		submit2.setFont(new Font("Tahoma", Font.BOLD, 16));
		submit2.setForeground(new Color(255, 255, 255));
		submit2.setBackground(new Color(102, 205, 170));
		submit2.setBounds(241, 590, 137, 44);
		getContentPane().add(submit2);

		JLabel lblSongs = new JLabel("Songs:");
		lblSongs.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblSongs.setBounds(616, 214, 130, 29);
		getContentPane().add(lblSongs);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(616, 256, 364, 378);
		getContentPane().add(scrollPane);

		table = new JTable();

		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		setModel();

		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				songIndex = table.getSelectedRow();
			}
		});

		scrollPane.setViewportView(table);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(songIndex != -1) {
					songs.remove(songIndex);
					setModel();
					songIndex = -1;
				}
				else {
					JOptionPane.showMessageDialog(getContentPane(),"Song not Selected!!");
				}
			}
		});
		btnRemove.setBackground(new Color(240, 128, 128));
		btnRemove.setBounds(881, 647, 97, 25);
		getContentPane().add(btnRemove);
		submit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent f) {


				try {
					if (ValidateVinylName(nameFiled.getText())== true && 
							ValidateReleaseYear(yearFiled.getText()) == true &&
							ValidatePrice(priceFiled.getText()) == true && 
							((ValidateDiscount(discountFiled.getText()) == true) || discountFiled.getText().equals(""))) { 

						if(songs.isEmpty()) {
							JOptionPane.showMessageDialog(getContentPane(),"You can't add an empty vinyl!!");
						}
						else {

							Vinyl newVinyl	= new Vinyl(nameFiled.getText(), yearFiled.getText(), 
									(Format) comboBoxFormat.getSelectedItem(), (Condition) comboBoxCondition.getSelectedItem(), 
									Integer.valueOf(discountFiled.getText()), Float.valueOf(priceFiled.getText()));


							for(Song song: songs) 
								newVinyl.addSong(song);

							deserialize("store.ser"); 
							store.addProduct(newVinyl);
							serialize("store.ser"); 

							JOptionPane.showMessageDialog(getContentPane(),"Vinyl Seaved Successfuly!");
							AdminPage AP = new AdminPage();
							AP.getFrame().setVisible(true);
							dispose();
						}
					}

					else JOptionPane.showMessageDialog(getContentPane(),"One Or More Of The Details You Entered Are Incorrect, Please Try Again!");


				} catch (NumberFormatException | IllegalVinylPrice f2) {
					JOptionPane.showMessageDialog(getContentPane(),f2.getMessage());

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

	public boolean ValidateVinylName(String name) {

		return name.length() > 2; 

	}

	public boolean ValidateReleaseYear(String ReleaseYear) {

		if(ReleaseYear.matches("[0-9]*")) {
			try {
				return Integer.valueOf(ReleaseYear) <= LocalDate.now().getYear()
						&& Integer.valueOf(ReleaseYear)> 0; 

			}

			catch(NumberFormatException|NullPointerException e) {
				return false;
			}


		}
		return false;
	}

	public boolean ValidatePrice(String price) {

		if(price.matches("[0-9]*")) {

			try {
				return Double.valueOf(price) > 0; 

			}

			catch(NumberFormatException|NullPointerException e) {
				return false;
			}


		}
		return false;
	}

	public boolean ValidateDiscount(String discount) {

		if(discount.matches("[0-9]*")) {

			try {
				return Integer.valueOf(discount) > 0 && Integer.valueOf(discount) < 100; 

			}

			catch(NumberFormatException|NullPointerException e) {
				return false;
			}


		}
		return false;
	}

	private void setModel() {
		//headers for the table
		String[] columns = new String[] {
				"No", "Name", "Genre" 
		};

		this.model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);

		if(!songs.isEmpty()) {
			int rowCount = model.getRowCount();

			//Remove rows one by one from the end of the table
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}


		for(int i = 0; i < songs.size(); i++) {

			Object[] row = new Object[6];

			row[0] = i + 1;
			row[1] = songs.get(i).getName();
			row[2] = songs.get(i).getGenre();

			model.addRow(row);
		}
	}
}
