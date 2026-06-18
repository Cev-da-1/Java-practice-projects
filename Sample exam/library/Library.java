import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Library {
	private List<Book> books;
	
	public Library() {
		books = new ArrayList<>();
	}
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public List<Book> getBooks() {
		return books;
	}
	
	public Set<Book> getUniqueBooks() {
		Set<Book> uniqueBooks = new HashSet<>();
		for (Book book: books) {
			if (!uniqueBooks.contains(book)) uniqueBooks.add(book);
		}
		return uniqueBooks;
	}
	
	public Book removeBook(int index) {
		if (index < books.size()) return books.remove(index);
		throw new IndexOutOfBoundsException();
	}
	
	public Stream<Book> findFictionBooks (
		Integer publishedInOrAfter, String searchTerm
	) {
		Stream<Book> stream;
		if ((publishedInOrAfter == null) && (searchTerm == null || searchTerm.isEmpty())) {
			stream = books.stream().filter(item -> item.getGenre() != Genre.NON_FICTION);
		}
		
		else if (publishedInOrAfter == null) {
			stream = books.stream().filter(item -> item.getGenre() != Genre.NON_FICTION)
								   .filter(item -> item.getTitle().toLowerCase().contains(searchTerm.toLowerCase()));
		}
		else if (searchTerm == null || searchTerm.isEmpty()) {
			stream = books.stream().filter(item -> item.getGenre() != Genre.NON_FICTION)
								   .filter(item -> item.getPublicationYear() >= publishedInOrAfter);
		}
		
		
		else {
			stream = getUniqueBooks().stream().filter(item -> item.getGenre() != Genre.NON_FICTION)
								   .filter(item -> item.getPublicationYear() >= publishedInOrAfter)
								   .filter(item -> item.getTitle().toLowerCase().contains(searchTerm.toLowerCase()));
		}
		
		return stream;
		
		

	}
	
	public ArrayList<Book> getSortedBooks() {
		return getUniqueBooks().stream().sorted(new BookComparator()).collect(Collectors.toCollection(ArrayList::new));
	}
}
	
	