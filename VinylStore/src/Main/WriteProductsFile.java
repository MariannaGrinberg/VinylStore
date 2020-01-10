package Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import Classes.Address;
import Classes.Customer;
import Classes.Employee;
import Classes.Song;
import Classes.Store;
import Classes.Vinyl;
import Exceptions.IlegalPassword;
import Exceptions.IllegalVinylPrice;
import Exceptions.InvalidUserName;
import enums.City;
import enums.Condition;
import enums.Format;
import enums.Genre;

public class WriteProductsFile {
	
	static Store store; 

	public static void main(String[] args) {
		
		deserialize("store.ser");
		store = null; 
		serialize("store.ser");
		
		Vinyl mixedVinyl;
		Vinyl abbeyRoad;
		Vinyl wishYouWereHere;
		Vinyl exodus;
		Vinyl electricLadyland;
		
		try {
			Store store = new Store("Vinyl Store");
			
			// Customers

			Address customerAddres1 = new Address(City.Afula, "ocishkin", "40", "-");

			Customer customer1 = new Customer("1", "Customer1", "1234567", "Customer1",
					"Customer1", customerAddres1, "0541111111", 
					"customer@gmail.com", LocalDate.of(1996, 2, 4));
			
			Address customerAddres2 = new Address(City.Haifa, "Rabon", "20", "2");

			Customer customer2 = new Customer("2", "Customer2", "132132", "Customer2",
					"Customer2", customerAddres2, "0541111111", 
					"customer@gmail.com", LocalDate.of(1996, 2, 4));
			
			Address customerAddres3 = new Address(City.TLV, "Derech Hashslom", "35", "4");

			Customer customer3 = new Customer("3", "Customer3", "123123", "Customer3",
					"Customer3", customerAddres3, "0541111111", 
					"customer@gmail.com", LocalDate.of(1996, 2, 4));
			

			store.addCustomer(customer1);
			store.addCustomer(customer2);
			store.addCustomer(customer3);
			
			
			// Employees
			
			Address employeeAdress1 = new Address(City.Haifa, "balfur", "2", "-");

			Employee employee1 = new Employee("12345678", "manager","567890", "Employee1", "Employee1", 
					employeeAdress1, "0541111111", "employee@gmail.com ", LocalDate.of(1993, 1, 13));
			
			Address employeeAdress2 = new Address(City.Other, "Q. Tivon, Hahoresh", "26", "-");

			Employee employee2 = new Employee("1234567", "employee1","567890", "Employee2", "Employee2", 
					employeeAdress2, "0541111111", "employee@gmail.com ", LocalDate.of(1991, 2, 3));
			
			Address employeeAdress3 = new Address(City.TLV, "Ben Gurion", "4", "2");

			Employee employee3 = new Employee("123456", "employee2","567890", "Employee3", "Employee3", 
					employeeAdress3, "0549305781", "employee@gmail.com ", LocalDate.of(1993, 1, 13));

			
			store.addEmployee(employee1);
			store.addEmployee(employee2);
			store.addEmployee(employee3);
			
			
			// Vinyls

			mixedVinyl = new Vinyl("Mixed Vinyl", "2019", Format.EP, Condition.BrandNew, 0 , 80);
		

			Song memories = new Song("Memories", "Maroon5", Genre.Pop);
	
			Song PhoneNumbers = new Song("Phone Numbers", "Dominic Fike", Genre.NewAge);
	
			Song Perfect = new Song("Perfect", "Ed Sheeran", Genre.Classical);
	
			Song Nights = new Song("3 Nights", "Dominic Fike", Genre.RnB );
	
			Song InMyHead = new Song("In My Head", "Ryland James", Genre.Pop );
	
			// Add Song to First Vinyl
			mixedVinyl.addSong(memories);
			mixedVinyl.addSong(PhoneNumbers);
			mixedVinyl.addSong(Perfect);
			mixedVinyl.addSong(Nights);
			mixedVinyl.addSong(InMyHead);
			
			mixedVinyl.setArtist();
			mixedVinyl.setDescription();


			
			abbeyRoad = new Vinyl("Abbey Road", "1969", Format.EP, Condition.BrandNew, 0, 80);
			
			Song comeTogether = new Song("Come Together", "The Beatles", Genre.Rock);
			Song something = new Song("Something", "The Beatles", Genre.Rock);
			Song maxwellsSilverHammer = new Song("Maxwell's Silver Hammer", "The Beatles", Genre.Rock);
			Song ohDarling = new Song("Oh! Darling", "The Beatles", Genre.Rock);
			Song octopussGarden = new Song("Octopus's Garden", "The Beatles", Genre.Rock);
			
			abbeyRoad.addSong(comeTogether);
			abbeyRoad.addSong(something);
			abbeyRoad.addSong(maxwellsSilverHammer);
			abbeyRoad.addSong(ohDarling);
			abbeyRoad.addSong(octopussGarden);
			
			abbeyRoad.setArtist();
			abbeyRoad.setDescription();
			
			Vinyl theWall = new Vinyl("The Wall", "1979", Format.LP, Condition.BrandNew, 10, 100);
			
			Song inTheFlash = new Song("In The Flash?", "Pink Floyd", Genre.Rock);
			Song theThinIce= new Song("The Thin Ice", "Pink Floyd", Genre.Rock);
			Song anotherBrickInTheWall1 = new Song("Another Brick in the Wall (Part I)", "Pink Floyd", Genre.Rock);
			Song theHappiestDaysOfOurLives = new Song("Tha Happiest Days of Our Lives", "Pink Floyd", Genre.Rock);
			Song anotherBrickInTheWall2 = new Song("Another Brick in the Wall (Part II)", "Pink Floyd", Genre.Rock);
			
			theWall.addSong(inTheFlash);
			theWall.addSong(theThinIce);
			theWall.addSong(anotherBrickInTheWall1);
			theWall.addSong(theHappiestDaysOfOurLives);
			theWall.addSong(anotherBrickInTheWall2);
			
			theWall.setArtist();
			theWall.setDescription();
			
			
			wishYouWereHere = new Vinyl("Wish You Were Here", "1975", Format.LP, Condition.Used, 0, 60);
			
			Song s1 = new Song("Shine On You Crazy Diamond (Parts I–V)", "Pink Floyd", Genre.Rock);
			Song s2 = new Song("Welcome to the Machine", "Pink Floyd", Genre.Rock);
			Song s3 = new Song("Have a Cigar", "Pink Floyd", Genre.Rock);
			Song s4 = new Song("Wish You Were Here", "Pink Floyd", Genre.Rock);
			Song s5 = new Song("Shine On You Crazy Diamond (Parts VI–IX)", "Pink Floyd", Genre.Rock);
			
			wishYouWereHere.addSong(s1);
			wishYouWereHere.addSong(s2);
			wishYouWereHere.addSong(s3);
			wishYouWereHere.addSong(s4);
			wishYouWereHere.addSong(s5);
			
			wishYouWereHere.setArtist();
			wishYouWereHere.setDescription();

			
			exodus = new Vinyl("Bob Marley", "1997", Format.EP, Condition.damaged, 0, 20);
			
			Song s6 = new Song("Natural Mystic", "Bob Marley", Genre.Reggae);
			Song s7 = new Song("So Much Things to Say", "Bob Marley", Genre.Reggae);
			Song s8 = new Song("Guiltiness", "Bob Marley", Genre.Reggae);
			Song s9 = new Song(	"The Heathen", "Bob Marley", Genre.Reggae);
			Song s10 = new Song("Exodus", "Bob Marley", Genre.Reggae);
			
			exodus.addSong(s6);
			exodus.addSong(s7);
			exodus.addSong(s8);
			exodus.addSong(s9);
			exodus.addSong(s10);
			
			exodus.setArtist();
			exodus.setDescription();
			
			
			electricLadyland = new Vinyl("Electric Ladyland", "1968", Format.EP, Condition.old, 0, 50);
			
			Song s11 = new Song("...And the Gods Made Love", "Jimi Hendrix", Genre.Rock);
			Song s12 = new Song("Have You Ever Been (To Electric Ladyland)", "Jimi Hendrix", Genre.Rock);
			Song s13 = new Song("Crosstown Traffic", "Jimi Hendrix", Genre.Rock);
			Song s14 = new Song("Voodoo Chile", "Jimi Hendrix", Genre.Rock);
			Song s15 = new Song("Little Miss Strange", "Jimi Hendrix", Genre.Rock);
			
			electricLadyland.addSong(s11);
			electricLadyland.addSong(s12);
			electricLadyland.addSong(s13);
			electricLadyland.addSong(s14);
			electricLadyland.addSong(s15);
			
			electricLadyland.setArtist();
			electricLadyland.setDescription();
			
			
			store.addProduct(mixedVinyl);
			store.addProduct(abbeyRoad);
			store.addProduct(theWall);
			store.addProduct(wishYouWereHere);
			store.addProduct(exodus);
			store.addProduct(electricLadyland);
			
						
			
			for(Vinyl vinyl : store.getProducts()) {
				System.out.println(vinyl);
			}
			
			
			FileOutputStream file;
			file = new FileOutputStream(new File("store.ser"));
			
			ObjectOutputStream obj = new ObjectOutputStream(file);
			
			
			obj.writeObject(store);
			
			obj.close();
			file.close();
			
			
			System.out.println("Done!");
			
			
		} catch (IllegalVinylPrice | IOException | InvalidUserName | IlegalPassword e) {
			System.out.println(e.getMessage());
		}
	
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
