package Classes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import Exceptions.IllegalVinylPrice;
import enums.Condition;
import enums.Format;

public class Vinyl implements Comparable<Vinyl>, Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private String artist = "";


	public Vinyl(String name, String releaseYear, Format format, 
				 Condition condition,int discount,float price) throws IllegalVinylPrice{
		
		songs = new ArrayList<>();
		vinylID = ++num; 
		setName(name);
		setDescription();
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

	public String getArtist() {
		return artist;
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

	public void setArtist() {
		ArrayList<Song> songsArr = this.songs;
		String artist = songsArr.get(1).getArtist();
		
		boolean check = true;
		
		String[] artists = new String[this.songs.size()];
		for(Song song : this.songs) {
			if(!artist.contentEquals(song.getArtist())) {
				check = false;
				break;
			}
		}

		if(check) {
			this.artist = artist;
		}
		else {
			this.artist = "";
		}
		
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription() {

		String description = "#" + this.vinylID + " - " + this.name; 

		if(!this.artist.equals("")) {
			description += " - " + this.artist + ":\n";
		}
		else {
			description += ":\n";
		}
		
		
		int count = 1;
		for(Song song : this.songs) {
			description = description + "\n" + count++ + ". " + song.getName();
		}
		
		this.description = description;
		
	}
	
	public void setVinylID(int ID) {
		this.vinylID = ++ID;
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
		
		if (price2 >= 0.0f) {
			
			if (this.getDiscount() == 0.0f)
				this.price = price2;
				
			else if (this.getDiscount() > 0.0f) 
				this.price = price2 - (price2 * this.getDiscount());
				
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
	
	public void setDiscount(int discount) throws IllegalVinylPrice {
		if (discount < 0 && discount > 100 ) {
		
			this.discount = 0.0f;
		    throw new IllegalVinylPrice("Illega Discount.. The Discount Set To : 0");
		  }
		
		else this.discount = (float)discount/100;
			
	}
	

	@Override
	public String toString() {
		return this.description;
	}

	@Override
	public int compareTo(Vinyl v) {
		
		return this.vinylID - v.vinylID;

	}
	

	
	
}
