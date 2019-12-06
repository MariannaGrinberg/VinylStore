import java.util.ArrayList;
import java.util.Date;

public class Vinyl {

	static int num = 0; 
	private int vinylID;
	private String name;
	private String description; 
	private String releaseYear;
	private Format format;
	private Condition condition;
	private float price;
	private ArrayList<Song> songs; 
	private float discount;


	public Vinyl(String name, String description, String releaseYear, Format format, 
				 Condition condition, float price, float discount) throws IllegalVinylPrice{
		
		songs = new ArrayList<>();
		vinylID = ++num; 
		setName(name);
		setDescription(description);
		setReleaseDate(releaseYear);
		setFormat(format);
		setCondition(condition);
		setDiscount(discount);
		setPrice(price); 
	}
	
	//Getters

	public int getVinylID() {
		return vinylID;
	}

	public String getDescription() {
		return description;
	}

	public String getReleaseDate() {
		return releaseYear;
	}

	public Format getFormat() {
		return format;
	}

	public String getName() {
		return name;
	}


	public Condition getCondition() {
		return condition;
	}
	
	public float getPrice() {
		return price; 
	}
	
	public float getDiscount() {
		return discount; 
	}

	//Setters


	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setReleaseDate(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public void setPrice(float price2) throws IllegalVinylPrice{
		if (price2 > 0.0) {
			
			if (this.getDiscount() == 0.0)
				this.price = price2;
				
			else if (this.getDiscount() > 0.0) 
				this.price = this.price - (this.price * this.getDiscount());
				
			else throw new IllegalVinylPrice("Illega Discount");
			
		}
		
		else {
			throw new IllegalVinylPrice("Price must be bigger then 0.");
		}
	}
	
	public void addSong(Song song) {
		this.songs.add(song);
		
	}
	
	public String genre() {
		
		String str = "";
		
		for(int i = 0; i < songs.size(); i++) 
			str += "#"+songs.get(i).getGenre()+" ";
			
		return str;
		
		
	}
	
	public void setDiscount(float discount) {
		if (discount <= 0.0)
			this.discount = 0;
		else this.discount = discount; 
			
	}

	@Override
	public String toString() {
		
		String songs = "";
		String artists = "";
		
		for(int i = 0; i < this.songs.size(); i++) 
			songs += " ["+this.songs.get(i)+"] ";
		
		for(int i = 0; i < this.songs.size(); i++) 
			artists += " ["+this.songs.get(i).getArtist()+"] ";

		
		return "Vinyl [vinylID=" + vinylID + ", name=" + name +", artist=" + artists + ", description=" + description + ", releaseDate=" + releaseYear
				+ ", format=" + format + ", condition=" + condition + ", price=" + price + ", songs=" + songs + "]";
	}
	

	
	
}
