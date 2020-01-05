package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import Classes.Address;
import Classes.Customer;
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

	public static void main(String[] args) {
		Vinyl mixedVinyl;
		Vinyl abbeyRoad;
		try {
			Store store = new Store("Vinyl Store");

			Address customerAddres = new Address(City.Afula, "ocishkin", "40", "-");

			Customer customer = new Customer("1", "Customer", "1234567", "Customer",
					"Customer", customerAddres, "0541111111", 
					"customer@gmail.com", LocalDate.of(1996, 2, 4));

			store.addCustomer(customer);
			
			
			String description = "MixedVinyl:\n\n"
							   + "1. Memories - Maroon 5\n"
							   + "2. Phone Numbers - Dominic Fike\n"
							   + "3. Perfect - Ed Sheeran\n"
							   + "4. 3 Nights - Dominic Fike\n"
							   + "5. In My Head - Ryland James";
			
			mixedVinyl = new Vinyl("Mixed Vinyl", description, "2019", Format.EP, Condition.BrandNew, 80 , 0);
		

			//Song Number 1 in the first Vinyl
			Song memories = new Song("Memories", "Maroon5", Genre.Pop);
	
			//Song Number 2 in the first Vinyl
			Song PhoneNumbers = new Song("Phone Numbers", "Dominic Fike", Genre.NewAge);
	
			//Song Number 3 in the first Vinyl
			Song Perfect = new Song("Perfect", "Ed Sheeran", Genre.Classical);
	
			//Song Number 4 in the first Vinyl
			Song Nights = new Song("3 Nights", "Dominic Fike", Genre.RnB );
	
			//Song Number 5 in the first Vinyl
			Song InMyHead = new Song("In My Head", "Ryland James", Genre.Pop );
	
			// Add Song to First Vinyl
			mixedVinyl.addSong(memories);
			mixedVinyl.addSong(PhoneNumbers);
			mixedVinyl.addSong(Perfect);
			mixedVinyl.addSong(Nights);
			mixedVinyl.addSong(InMyHead);
			
			description = "Abbey Rode - By The Beatles: \n\n"
						+ "1. Come Together\n"
						+ "2. Something\n"
						+ "3. Maxwell's Silver Hammer\n"
						+ "4. Oh! Darling\n"
						+ "5. Octopus's Garden\n";

			
			abbeyRoad = new Vinyl("Abbey Road", description, "1969", Format.EP, Condition.BrandNew, 80, 0);
			
			Song comeTogether = new Song("Come Together", "The Beatles", Genre.Rock);
			Song something = new Song("Something", "The Beatles", Genre.Rock);
			Song maxwellsSilverHammer  = new Song("Maxwell's Silver Hammer", "The Beatles", Genre.Rock);
			Song ohDarling = new Song("Oh! Darling", "The Beatles", Genre.Rock);
			Song octopussGarden = new Song("Octopus's Garden", "The Beatles", Genre.Rock);
			
			abbeyRoad.addSong(comeTogether);
			abbeyRoad.addSong(something);
			abbeyRoad.addSong(maxwellsSilverHammer);
			abbeyRoad.addSong(ohDarling);
			abbeyRoad.addSong(octopussGarden);
			
			store.addProduct(mixedVinyl);
			store.addProduct(abbeyRoad);
			
			FileOutputStream file;
			file = new FileOutputStream(new File("store.ser"));
			
			ObjectOutputStream obj = new ObjectOutputStream(file);
			
			
			obj.writeObject(store);
			
			obj.close();
			file.close();
			
			
			System.out.println("Done!");
			
			
		} catch (IllegalVinylPrice e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidUserName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IlegalPassword e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
