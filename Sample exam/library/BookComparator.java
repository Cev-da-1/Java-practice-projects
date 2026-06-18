import java.util.*;
public class BookComparator implements Comparator<Book> {
	public int compare(Book book1, Book book2) {
		
		if (!book1.getTitle().equals(book2.getTitle())) return book1.getTitle().compareToIgnoreCase(book2.getTitle());
		if (!(book1.getAuthor().split(" ")[1]+book1.getAuthor().split(" ")[0]).equals(book2.getAuthor().split(" ")[1]+book2.getAuthor().split(" ")[0])) return (book1.getAuthor().split(" ")[1]+book1.getAuthor().split(" ")[0]).compareToIgnoreCase(book2.getAuthor().split(" ")[1]+book2.getAuthor().split(" ")[0]);
		if (!book1.getPublicationYear().equals(book2.getPublicationYear())) return book2.getPublicationYear().compareTo(book1.getPublicationYear());
		return book1.getGenre().compareTo(book2.getGenre());
		
	}
}