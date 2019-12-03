import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
	
	Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		
	}
	
	private Store newStore() {
		
		System.out.print("Enter Store Name: ");
		String storeName = in.next();
		
		return new Store(upperFirst(storeName));
		
	}
	
	private Vinyl newVinyl() {
		
		// Vinyl Name
		System.out.print("Enter Vinyl name: ");
		String vinylName = in.next();
		
		// Artist/s Name
		String[] artist = inputArtists();
		
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
			return new Vinyl(vinylName, artist, description, releaseYear, format, condition,
					   		 price, discount);
		} catch (IllegalVinylPrice e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
		
		
	}
	
	private float inputDiscount() {
		System.out.print("Enter discount (%): ");
		float discount = in.nextFloat();
		
		return discount / 100;
	}

	private Condition inputCondition() {
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

	private Condition conditionSwitchCase(int input) {
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

	private Format inputFormat() {
		int input = 0;
		while (1 > input || input > 7) {
			
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
	
	private Format formatSwitchCase(int input) {
		
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
	
	private String[] inputArtists() {
		System.out.println("Enter Artist/s (if there is more then one, seperate with ', '): ");
		String artists = in.next();
		String[] artistsList = artists.split(", ");
		
		for (String artName : artistsList) {
			artName = upperFirst(artName);
		}
		
		return artistsList;
	}
	
	
	private String upperFirst(String name) {
		String[] listName = name.split(" ");
		String returnName = "";
		
		for (String word : listName) {
			returnName += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
		}
		
		return returnName;
	}

}
