import java.util.ArrayList;
import java.util.Date;

public class Vinyl {

	static int num = 0; 
	private int vinylID;
	private String description; 
	private Date releaseDate;
	private String format;
	private Condition condition;
	private int price; 
	private ArrayList<Song> songs; 

	
	public Vinyl(String description, Date releaseDate, String format, 
			Condition condition, int price) {
		
		songs = new ArrayList<>();
		vinylID = ++num; 
		setDescription(description);
		setReleaseDate(releaseDate);
		setFormat(format);
		setCondition(condition);
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

	public Condition getCondition() {
		return condition;
	}
	
	public int getPrice() {
		return price; 
	}

	//Setters
	
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

	public void setPrice(int price) {
		this.price = price;
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

	@Override
	public String toString() {
		
		String str = "";
		
		for(int i = 0; i < songs.size(); i++) 
			str += " ["+songs.get(i)+"] ";
		
		return "Vinyl [vinylID=" + vinylID + ", description=" + description + ", releaseDate=" + releaseDate
				+ ", format=" + format + ", condition=" + condition + ", price=" + price + ", songs=" + str + "]";
	}
	

	
	
	
}
