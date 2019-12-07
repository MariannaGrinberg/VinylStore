import java.time.LocalDate;
import java.util.Date;

public class Song {

	static int num = 0; 
	private int songID;
	private String name;
	private String artist;
	private LocalDate releaseDate; 
	private Genre genre;
	
	public Song(String name, String artist, LocalDate releaseDate, Genre genre) {
		songID = num++; 
		setName(name);
		setArtist(artist);
		setReleaseDate(releaseDate);
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
	
	public LocalDate getReleaseDate() {
		return releaseDate;
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
	
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "[songID=" + songID + ", name=" + name + ", artist=" + artist + ", releaseDate=" + releaseDate
				+ ", genre=" + genre + "]";
	} 
	
	
	
	
	
}
