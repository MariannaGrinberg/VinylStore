import java.util.ArrayList;

public class Store {
	
	// Attributes
	int num = 0;
	int storeID;
	String name;
	ArrayList<Vinyl> products;

	public Store(String name) {
		setStoreID();
		setName(name);
	}


	// Setters
	
	public void setStoreID() {
		this.storeID = ++num;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	// Getters
	
	public String getName() {
		return this.name;
	}

	public int getStoreID() {
		return this.storeID;
	}
	
	public ArrayList<Vinyl> getProducts() {
		return this.products;
	}
	
	
	// Add product
	
	public void addProduct(Vinyl vinyl) {
		this.products.add(vinyl);
	}


	@Override
	public String toString() {
		String products = "";
		
		for (Vinyl product : this.products) {
			products += product + ", ";
		}
		
		products = products.substring(0, products.length() -2 );
		
		return "Store [storeID=" + storeID + ", name=" + name + ", products=[" + products + "]]";
	}
	
}
