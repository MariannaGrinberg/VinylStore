import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		try {

			// Store

			Store store = new Store("Vinyl Store");

			Vinyl vinyl = new Vinyl("Mixed Vinyl", "", "2019", Format.TWELVE_SINGLE, Condition.BrandNew, 80 , 0);

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
			vinyl.addSong(memories);
			vinyl.addSong(PhoneNumbers);
			vinyl.addSong(Perfect);
			vinyl.addSong(Nights);
			vinyl.addSong(InMyHead);

			//Add Vinyl to Store
			store.addProduct(vinyl);

			// Employee

			Address employeeAdress = new Address(City.Haifa, "balfur", "2", "-");

			LocalDate date = LocalDate.of(1993, 1, 13);
			Employee yuval = new Employee("320827394", "manager","567890", "Yuval", "Parnass", 
					employeeAdress, "0549305781", "Yuvii.p@gmail.com ", date);

			store.addEmployee(yuval);

			// Customer

			Address customerAddres = new Address(City.Afula, "ocishkin", "40", "-");

			Customer marianna = new Customer("320821374", "Marianna", "1234567", "Marianna",
					"Grinberg", customerAddres, "0546431326", 
					"marianna4268@gmail.com", LocalDate.of(1996, 2, 4));

			store.addCustomer(marianna);

			// Order

			Order order = new Order(marianna, LocalDate.now());
			order.addProducts(vinyl);


			// Main

			System.out.println("Welcome to " + store.getName() + "!!");

			while (true) {
				int choice = -1;

				choice = login();
				switch (choice) {

				case 1:
					while (true) {
						choice = employeeMenu();

						switch (choice) {

						case 1:
							showVinyls(store);
							continue;

						case 2:
							showEmployees(store);
							continue;

						case 3:
							showCustomers(store);
							continue;

						case 4:
							showOrders(store);
							continue;

						case 5:
							vinyl = newVinyl();
							store.addProduct(vinyl);
							continue;

						case 6:
							Customer customer = newCustomer();
							store.addCustomer(customer);
							continue;

						case 7:
							Employee employee = newEmployee();
							store.addEmployee(employee);
							continue;
							
						case 8:
							handleOrder(yuval, store);
							continue;

						default:
							break;
						}
						choice = -1;
						break;
					}
					continue;

				case 2:
					while(true) {
						choice = customerMenu();

						switch (choice) {
						case 1:
							order = newOrder(marianna, store);

							if (order == null) {
								System.out.println("There is a problem with your order");
							}

							else {
								store.addOrder(order);								
							}

							continue;

						case 2:
							ArrayList<Order> orders = store.getOrdersByCustomerID(marianna.getID());
							if (orders.isEmpty()) {
								System.out.println("You don't have any orders");
							}
							else {
								for (Order o : orders) {
									System.out.println(o);
								}
							}
							continue;

						default:
							break;

						}
						choice = -1;
						break;
					}

				default:
					break;


				}

			}


		} catch (InvalidUserName | IlegalPassword |IllegalVinylPrice | IlegalDate e) {
			
			System.out.println(e.getMessage() + "Please try again");
	
		}	
	}


	private static void handleOrder(Employee employee, Store store) {
		ArrayList<Order> orders = store.getNotHandledOrders();
		int orderID = -1;

		if (orders.isEmpty()) {
			System.out.println("There is no Orders to handle...");
		}
		else {
			while (orderID < 1 || orderID > orders.size()) {
				System.out.println("Choose Order to handle from the list below:");

				int i = 0;
				for (Order o : orders) {
					System.out.println(++i + ". " + o);
				}
				
				orderID = in.nextInt();
			}
			
			Order orderToHandle = store.getOrderByID(orderID);
			
			System.out.println("Enter Delivery Date: ");
			
			try {
				
				orderToHandle.setDeliveryDate(inputDate());
				orderToHandle.setEmployee(employee);
				
				System.out.println("Done!!");
		
			} catch (IlegalDate e) {
		
				System.out.println(e.getMessage() + "Please try again");
			
			};
			
		}
	}
	
	private static LocalDate inputDate() {
		int day = 0;
		int month = 0;
		int year = 0;
		
		while (day < 1 || day > 31) {
			System.out.println("Enter day: ");
			day = in.nextInt();
		}
		
		while (month < 1 || month > 12) {
			System.out.println("Enter Month: ");
			month = in.nextInt();
		}
		
		while (year < 2019 || year > 2020) {
			System.out.println("Enter Year: ");
			year = in.nextInt();
		}
		
				
		return LocalDate.of(year, month, day);
		
	}

	private static Order newOrder(Customer customer, Store store) {

		try {
			
			ArrayList<Vinyl> cart = new ArrayList<>();
			Order order = new Order(customer, LocalDate.now());

			int choice = -1;

			while(choice < 0 || choice > 2) {
				choice = orderMenu();

				switch (choice) {

				case 1:
					if (cart.isEmpty()) {
						System.out.println("Your Cart is Empty...");
						choice = -1;
					}
					else {

						float totalPrice = 0;

						for (Vinyl product : cart) {
							System.out.println(product);
							totalPrice += product.getPrice();
						}

						System.out.println("Total Price: " + totalPrice + "$");
						choice = -1;
					}
					continue;

				case 2:
				
					System.out.println("Choose Vinyl from the options below:");

					int vinylID = -1;

					while (vinylID < 1 || vinylID > store.getProducts().size()) {
						int i = 0;

						for (Vinyl product : store.getProducts()) {
							System.out.println(++i + ". " + product);
						}

						vinylID = in.nextInt();
					}

					Vinyl product = store.getProductByID(vinylID);
					cart.add(product);
					
					System.out.println("'" + product.getName() + "' was added to your Cart");
					choice = -1;
					continue;

				case 3:

					for (Vinyl p : cart) {
						order.addProducts(p);
					}

					order.setOrderDate(LocalDate.now());

					System.out.println("Thank you for your order :)");

					return order;

				default: 
					continue;

				}
			}
			
		} catch (IllegalVinylPrice | IlegalDate e) {
		
			System.out.println(e.getMessage() + "Please try again");
		
		}
		
		return null;
	}


		private static int orderMenu() {
			int input = -1;
			while (1 > input || input > 3) {

				System.out.println("1. My Cart \n"
						+ "2. Add a Vinyl to Cart \n"
						+ "3. Commit Order");
				input = in.nextInt();
			}

			return input;
		}

		private static Employee newEmployee() {
			// Employee ID
			System.out.println("Enter Employee ID: ");
			String ID = in.next();

			// Employee UserName
			System.out.print("Enter Employee UserName: ");
			String userName = in.next();

			// Password
			System.out.println("Enter Password: ");
			String password = in.next();

			// Employee First Name
			System.out.println("Enter Employee First Name: ");
			String firstName = in.next();

			// Employee Last Name
			System.out.println("Enter Employee Last Name: ");
			String lastName = in.next();

			// Employee Address
			Address address = inputAddress();

			// Phone Number
			System.out.println("Enter Phone Number: ");
			String phoneNumber = in.next();

			// Email
			System.out.println("Enter Email: ");
			String email = in.next();

			// Date
			LocalDate date = LocalDate.now(); 

			Employee employee = null;

			try {

				employee = new Employee(ID, userName, password, firstName, lastName, address, phoneNumber, email, date);

			} catch (InvalidUserName | IlegalPassword e) {

				System.out.println(e.getMessage() + "Please try again");

			}

			return employee;
		}

		private static Customer newCustomer(){
		
			Customer customer = null; 
			
			try { 
				
			// Customer ID
			System.out.println("Enter Customer ID: ");
			String ID = in.next();

			// Customer UserName
			System.out.print("Enter Customer UserName: ");
			String userName = in.next();

			// Password
			System.out.println("Enter Password: ");
			String password = in.next();

			// Customer First Name
			System.out.println("Enter Customer First Name: ");
			String firstName = in.next();

			// Customer Last Name
			System.out.println("Enter Customer Last Name: ");
			String lastName = in.next();

			// Customer Address
			Address address = inputAddress();

			// Phone Number
			System.out.println("Enter Phone Number: ");
			String phoneNumber = in.next();

			// Email
			System.out.println("Enter Email: ");
			String email = in.next();

			// Date
			LocalDate date = LocalDate.now();


			customer = new Customer(ID, userName, password, firstName, lastName, address, phoneNumber, email, date);
			System.out.println("Customer Added Succesfuly");
			
			
			} catch (InvalidUserName | IlegalPassword e) {

				System.out.println(e.getMessage() + "Please try again");

			}

			return customer;
		}

		private static Address inputAddress() {

			// City

			int input = 0;
			while (1 > input || input > 4) {

				System.out.println("Choose City: \n"
						+ "1. TLV \n"
						+ "2. Haifa \n"
						+ "3. Afula \n"
						+ "4. Other ");
				input = in.nextInt();
			}

			City city =  citySwitchCase(input);

			// Street
			System.out.print("Enter Street Name: ");
			String street = in.next();

			// Number
			System.out.print("Enter House Number: ");
			String number = in.next();

			// ZipCode
			System.out.print("Enter ZipCode: ");
			String zipcode = in.next();

			Address address = new Address(city, street, number, zipcode);

			return address;
		}

		private static City citySwitchCase(int input) {
			City city = null;

			switch (input) {
			case 1: 
				city = City.TLV;
				break;

			case 2: 
				city = City.Haifa;
				break;

			case 3: 
				city = City.Afula;
				break;

			case 4: 
				city = City.Other;
				break;
			}

			return city;
		}

		private static void showOrders(Store store) {
			for (Customer customer : store.getCustomers()) {
				printOrdersOf(customer);
			}
		}

		private static void printOrdersOf(Customer customer) {

			for (Order order : customer.getOrders()) {
				System.out.println(order);
			}
		}

		private static void showCustomers(Store store) {
			ArrayList<Customer> customers = store.getCustomers();

			for (Customer customer : customers) {
				System.out.println(customer);
			}
		}

		private static void showEmployees(Store store) {
			ArrayList<Employee> employees = store.getEmployees();

			for (Employee employee : employees) {
				System.out.println(employee);
			}
		}

		private static void showVinyls(Store store) {
			ArrayList<Vinyl> vinyls = store.getProducts();

			for (Vinyl vinyl : vinyls) {
				System.out.println(vinyl);
			}
		}

		private static int employeeMenu() {
			int choice = -1;

			while (choice < 0 || choice > 8) {
				System.out.println("hello Employee :) \n"
						+ "Choose one of the options below: \n"
						+ "1. Show Vinyls on store \n"
						+ "2. Show employees \n"
						+ "3. Show Customers \n"
						+ "4. Show Orders \n"
						+ "5. Add new Vinyl \n"
						+ "6. Add new Customer \n"
						+ "7. Add new Employee \n"
						+ "8. Handle Order \n\n"
						+ "0. Back");
				
				choice = in.nextInt();
			}

			return choice;
		}

		private static int login() {
			int choice = -1;
			while (choice < 1 || choice > 2) {
				System.out.println("Login as: \n"
						+ "1. Employee \n"
						+ "2. Customer");
				choice = in.nextInt();
			}
			return choice;

		}

		private static int vinylMenu(){
			int choice = -1;

			while (choice != 1 || choice != 0) {
				System.out.println("1. Add song to vinyl \n\n");
				System.out.println("0. Back");

				choice = in.nextInt(); 
			}

			return choice;
		}


		private static Vinyl newVinyl() {

			// Vinyl Name
			System.out.println("Enter Vinyl name: ");
			String vinylName = in.next();

			// Description
			System.out.print("Enter Vinyl description: ");
			String description = in.next();

			// Release Year
			System.out.println("Enter Vinyl release year: ");
			String releaseYear = in.next();

			// Format
			Format format = inputFormat();

			// Condition
			Condition condition = inputCondition();

			// Price
			System.out.println("Enter Price: ");
			float price = in.nextFloat();

			// Discount
			float discount = inputDiscount();



			try {

				Vinyl vinyl = new Vinyl(vinylName, description, releaseYear, 
						format, condition, price, discount);

				int choice = -1;

				while (true) {
					choice = vinylMenu();

					if (choice == 0) {
						break;
					}

					addSong(vinyl);
				}

				return vinyl;

			} catch (IllegalVinylPrice e) {

				System.out.println(e.getMessage() + "Please try again");
			}
			return null; 


		}

		private static void addSong(Vinyl vinyl) {

			vinyl.addSong(insertSong());

		}

		private static float inputDiscount() {
			System.out.print("Enter discount (%): ");
			float discount = in.nextFloat();

			return discount / 100;
		}

		private static Condition inputCondition() {
			int input = 0;
			while (1 > input || input > 4) {

				System.out.println("Choose one of the Conditions below: \n"
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
						+ "11. Rock \n");


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
				Genre = Genre.Rock;
				break;
			}


			return Genre;

		}

		private static Song insertSong() {

			System.out.println("Enter a Song Name");
			String songName = in.next();

			System.out.println("Enter Artist Name");
			String artist = in.next();

			Genre ganre = inputGenre(); 

			return new Song(songName, artist, ganre); 
		}

		private static int customerMenu() {

			int input = -1;
			while (0 > input || input > 2) {

				System.out.println("hello Customer :) \n"
						+ "Choose one of the options below: \n"
						+ "1. Place an Order \n"
						+ "2. Show my Orders \n\n"
						+ "0. Back");
				input = in.nextInt();
			}
			return input; 
		}
	}
