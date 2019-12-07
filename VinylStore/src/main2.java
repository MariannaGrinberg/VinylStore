import java.time.LocalDate;

public class main2 {

	public static void main(String[] args) {
		
		Store store = new Store("Vinyl Store");
		
		try {
			
			Vinyl v1 = new Vinyl("Mixed Vinyl", "", "2019", Format.TWELVE_SINGLE, Condition.BrandNew, 80 , 0);
		
			//Song Number 1 in the first Vinyl
			LocalDate date1 =  LocalDate.of(2019, 4, 18);
			Song memories = new Song("Memories", "Maroon5", date1, Genre.Pop);
			
			//Song Number 2 in the first Vinyl
			LocalDate date2 =  LocalDate.of(2018, 1, 25);
			Song PhoneNumbers = new Song("Phone Numbers", "Dominic Fike", date2, Genre.NewAge);
			
			//Song Number 3 in the first Vinyl
			LocalDate date3 =  LocalDate.of(2017, 8, 17);
			Song Perfect = new Song("Perfect", "Ed Sheeran", date3, Genre.Classical);
			
			//Song Number 4 in the first Vinyl
			LocalDate date4 =  LocalDate.of(2018, 12, 10);
			Song Nights = new Song("3 Nights", "Dominic Fike", date4, Genre.RnB );
			
			//Song Number 5 in the first Vinyl
			LocalDate date5 =  LocalDate.of(2016, 7, 21);
			Song InMyHead = new Song("In My Head", "Ryland James", date5, Genre.Pop );
			
			// Add Song to First Vinyl
			v1.addSong(memories);
			v1.addSong(PhoneNumbers);
			v1.addSong(Perfect);
			v1.addSong(Nights);
			v1.addSong(InMyHead);
			
			//Add Vinyl to Store
		  	store.addProduct(v1);
			
			//Add fisrt Customer
			Address customerAddres = new Address(City.A, "ocishkin", "40", "-");
		
			Customer marianna = new Customer(320821374, "Marianna", "1234567", "Marianna",
					"Grinberg", customerAddres, "0546431326", "marianna4268@gmail.com");
			
			//ADD first Employee
			Address employeeAdress = new Address(City.B, "balfur", "2", "-");
			
			Employee yval = new Employee(320827394, "manager","567890", "Yval", "Parnas", 
					employeeAdress, "0526431526", "Yuvii.p@gmail.com ", date5);
		
			
			//Coustumer Do a Order 
			Order number1 = new Order(yval, marianna,LocalDate.now(), 10);
		
			//Add Product to Order.
			number1.addProducts(v1);

			//Set DeliveryDate for 5 Days 
			number1.setDeliveryDate(number1.getOrderDate().plusDays(5));
			
			//Print the Order 
			System.out.println(number1);
			
			//Print the products for sale
			System.out.println(store);
			
			
			
			} catch (InvalidUserName | IlegalPassword |IllegalVinylPrice e) {
				
				e.printStackTrace();
				
			}
			
		
	}


}
