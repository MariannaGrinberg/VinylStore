package Classes;
import java.io.Serializable;

import enums.Genre;

public class Song implements Comparable<Song>, Serializable  {

	private static final long serialVersionUID = -8070433891061115939L;
	
	static int num = 0; 
	private int songID;
	private String name;
	private String artist;
	private Genre genre;
	
	public Song(String name, String artist, Genre genre) {
		songID = num++; 
		setName(name);
		setArtist(artist);
		setGenre(genre);
				
	}
	
	//Getters 
	
	public int getSongID() {
		return songID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public Genre getGenre() {
		return genre;
	}
	
	//Setters 
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "[songID=" + songID + ", name=" + name + ", artist=" + artist + 
			   ", genre=" + genre + "]";
	}

	@Override
	public int compareTo(Song o) {

		return this.name.compareTo(o.name);
	
	} 
	
	
	
	
	
}
