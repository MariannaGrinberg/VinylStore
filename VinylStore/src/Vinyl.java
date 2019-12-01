import java.util.ArrayList;
import java.util.Date;

public class Vinyl {

	static int num = 0; 
	private int vinylID;
	private String name;
	private ArrayList<String> artist;
	private String description; 
	private Date releaseDate;
	private String format;
	private Condition condition;
	private float price; 
	private ArrayList<Song> songs; 
	private float discount;

	
	public Vinyl(String name, ArrayList<String> artist, String description, Date releaseDate, String format, 
				 Condition condition, float price, float discount) {
		
		songs = new ArrayList<>();
		vinylID = ++num; 
		setName(name);
		setArtist(artist);
		setDescription(description);
		setReleaseDate(releaseDate);
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

	public Date getReleaseDate() {
		return releaseDate;
	}

	public String getFormat() {
		return format;
	}

	public String getName() {
		return name;
	}


	public ArrayList<String> getArtist() {
		return artist;
	}


	public Condition getCondition() {
		return condition;
	}
	
	public float getPrice() {
		return price; 
	}

	//Setters

	public void setArtist(ArrayList<String> artist) {
		this.artist = artist;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public void setPrice(float price2) {
		if (this.discount > 0.0) {
			this.price = this.price - (this.price * this.discount);
		}
		
		else {
			this.price = price2;			
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
		this.discount = discount;
	}

	@Override
	public String toString() {
		
		String songs = "";
		String artists = "";
		
		for(int i = 0; i < this.songs.size(); i++) 
			songs += " ["+this.songs.get(i)+"] ";
		
		for (String art : this.artist) {
			artists += " ["+art+"] ";
		}
		
		return "Vinyl [vinylID=" + vinylID + ", name=" + name +", artist=" + artists + ", description=" + description + ", releaseDate=" + releaseDate
				+ ", format=" + format + ", condition=" + condition + ", price=" + price + ", songs=" + songs + "]";
	}
	

	
	
	
}
