import java.time.LocalDate;

public class main2 {

	public static void main(String[] args) {
		
		Store store = new Store("Vinyl Store");
		
		try {
			
			Vinyl v1 = new Vinyl("Mixed Vinyl", "", "2019", Format.TWELVE_SINGLE, Condition.BrandNew, 80 , 0);
		
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
			
			LocalDate date =  LocalDate.of(2016, 7, 21);
			Employee yuval = new Employee(320827394, "manager","567890", "Yval", "Parnass", 
					employeeAdress, "0549305781", "Yuvii.p@gmail.com ", date);
		
			
			//Coustumer Do a Order 
			Order order1 = new Order(yuval, marianna,LocalDate.now(), 10);
		
			//Add Product to Order.
			order1.addProducts(v1);

			//Set DeliveryDate for 5 Days 
			order1.setDeliveryDate(order1.getOrderDate().plusDays(5));
			
			//Print the Order 
			System.out.println(order1);
			
			//Print the products for sale
			System.out.println(store);
			
			
			
			} catch (InvalidUserName | IlegalPassword |IllegalVinylPrice e) {
				
				e.printStackTrace();
				
			}
			
		
	}


}
