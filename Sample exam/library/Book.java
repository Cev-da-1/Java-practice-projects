import java.util.*;

public class Book {
	private String author;
	private Genre genre;
	private Integer publicationYear;
	private String title;
	
	public Book(String title, String author, Integer publicationYear, Genre genre) {
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.genre = genre;
	}
	
	public String getAuthor() {return author;}
	public Genre getGenre() {return genre;}
	public Integer getPublicationYear() {return publicationYear;}
	public String getTitle() {return title;}
	
	public String toString() {return title+" ("+author+")";}
	
	public boolean equals(Object object) {
		if (object.getClass().getSimpleName().equals("Book")) {
			Book compareBook = (Book) object;
			return (title.equals(compareBook.getTitle()) && author.equals(compareBook.getAuthor()));
		}
		return false;
		
	}
	
	public int hashCode() {
		return (title+author).hashCode();
	}
	
	
}