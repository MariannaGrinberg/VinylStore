import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
	
	static Scanner in = new Scanner(System.in);
	
    static int choice;
    static int choice2;
    
    static ArrayList<Vinyl> products = new ArrayList<>();  

	public static void main(String[] args) {
		
		newStore();
		
		do {
			
			displayVinylManue();
			addVinyl(newVinyl());
			
		} while(choice != 2);
	}
	
	private static Store newStore() {
		
		System.out.print("Enter Store Name: ");
		String storeName = in.next();
		

		
		return new Store(upperFirst(storeName));
		
	}
	
	private static void addVinyl(Vinyl vinyl) {
		
		switch(choice2) {
    	
    	case 1: products.add(vinyl);
    	
    		break;

		}
	}
		
	private static void displayVinylManue(){
		
		System.out.println("1. add a new Vinyl");
		System.out.println("2. exit");
		
		choice = in.nextInt(); 
	}
		
	
	private static Vinyl newVinyl() {
		
		// Vinyl Name
		System.out.print("Enter Vinyl name: ");
		String vinylName = in.next();
		
		// Description
		System.out.print("Enter Vinyl description: ");
		String description = in.next();
		
		// Release Year
		System.out.print("Enter Vinyl release year: ");
		String releaseYear = in.next();
		
		// Format
		Format format = inputFormat();
		
		// Condition
		Condition condition = inputCondition();
		
		// Price
		System.out.print("Enter Price: ");
		float price = in.nextFloat();
		
		// Discount
		float discount = inputDiscount();
		
		
		
		try {
			
			Vinyl vinyl = new Vinyl(vinylName, description, releaseYear, 
					format, condition, price, discount);
			
			do {
				
				displaySongManue();
				vinyl.addSong(InsertSong());
				
				
			} while(choice != 2);
			
			return vinyl;
			
		} catch (IllegalVinylPrice e) {
			
			e.printStackTrace();
		}
		return null; 
		
		
	}
	
	private static void addSong(Vinyl vinyl) {
		
		switch(choice) {
    	
    	case 1: vinyl.addSong(InsertSong());
    	
    		break;

		}
	}
		
	private static void displaySongManue(){
		
		System.out.println("1. add a new song");
		System.out.println("2. Maybe Later");
		
		choice = in.nextInt(); 
	}
		
		
	
	private static float inputDiscount() {
		System.out.print("Enter discount (%): ");
		float discount = in.nextFloat();
		
		return discount / 100;
	}

	private static Condition inputCondition() {
		int input = 0;
		while (1 > input || input > 4) {
			
			System.out.println("Choose one of the formats below (1-7): \n"
							 + "1. Brand New \n"
							 + "2. Old \n"
							 + "3. Used \n"
							 + "4. Damaged ");
			input = in.nextInt();
			
		}
		
		return conditionSwitchCase(input);	
	}

	private static Condition conditionSwitchCase(int input) {
		Condition condition = null;
		
		switch (input) {
		case 1: // Brand New
			condition = Condition.BrandNew;
			break;
		
		case 2: // Old
			condition = Condition.old;
			break;
			
		case 3: // Used
			condition = Condition.Used;
			break;
			
		case 4: // 10"
			condition = Condition.damaged;
			break;
		}
		
		return condition;
	}

	private static Format inputFormat() {
		int input = 0;
		while (1 < input || input > 7) {
			
			System.out.println("Choose one of the formats below (1-7): \n"
							 + "1. LP \n"
							 + "2. EP \n"
							 + "3. 7\" \n"
							 + "4. 10\" \n"
							 + "5. 12\" Single \n"
							 + "6. 45 RPM \n"
							 + "7. 78 RPM");
			input = in.nextInt();
			
		}
		
		return formatSwitchCase(input);		
	}
	
	private static Format formatSwitchCase(int input) {
		
		Format format = null;
		
		switch (input) {
		case 1: // LP
			format = Format.LP;
			break;
		
		case 2: // EP
			format = Format.EP;
			break;
			
		case 3: // 7"
			format = Format.SEVEN;
			break;
			
		case 4: // 10"
			format = Format.TEN;
			break;
			
		case 5: // 12" Single
			format = Format.TWELVE_SINGLE;
			break;
			
		case 6: // 45 RPM
			format = Format.FORTY_FIVE_RPM;
			break;
			
		case 7: // 78 RPM
			format = Format.SEVENTY_EIGHT_RPM;
			break;
		}
		
		return format;
	}
	
	private static String upperFirst(String name) {
		String[] listName = name.split(" ");
		String returnName = "";
		
		for (String word : listName) {
			returnName += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
		}
		
		return returnName;
	}
	private static Genre inputGenre() {
		int input = 0;
		while (1 < input || input > 11) {
			
			System.out.println("Choose one of the formats below (1-7): \n"
							 + "1. Blues \n"
							 + "2. Classical \n"
							 + "3. Country \n"
							 + "4. Electronic \n"
							 + "5. Folk \n"
							 + "6. Jazz \n"
							 + "7. NewAge \n"
							 + "8. pop \n"
							 + "9. Reggae \n"
							 + "10. RnB \n"
							 + "11. Rok \n");

			
			input = in.nextInt();
			
		}
		
		return GenreSwitchCase(input);		
	}
	
	private static Genre GenreSwitchCase(int input) {
		
	Genre Genre = null;
		
		switch (input) {
		case 1: 
			Genre = Genre.Blues;
			break;
		
		case 2: 
			Genre = Genre.Classical;
			break;
			
		case 3: 
			Genre = Genre.Country;
			break;
			
		case 4: 
			Genre = Genre.Electronic;
			break;
			
		case 5:
			Genre = Genre.Folk;
			break;
			
		case 6: 
			Genre = Genre.Jazz;
			break;
			
		case 7: 
			Genre = Genre.NewAge;
			break;
			
		case 8: 
			Genre = Genre.Pop;
			break;
		
		case 9: 
			Genre = Genre.Reggae;
			break;
			
		case 10:
			Genre = Genre.RnB;
			break;
			
		case 11:
			Genre = Genre.Rok;
			break;
		}
		
		
		return Genre;
	
	}

	private static Song InsertSong() {
		
		System.out.println("Enter a Song Name");
		String songName = in.next();
		
		System.out.println("Enter Artist Name");
		String artist = in.next();
		
		System.out.println("Enter A ReleaseDate In Foramt: dd/MM/yyyy");
		String date = in.next();
		
		Genre ganre = inputGenre(); 
			
		LocalDate releaseDate = LocalDate.of(in.nextInt(), in.nextInt(), in.nextInt());
	
		return new Song(songName, artist, releaseDate, ganre); 


	
	}
}
