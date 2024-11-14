import java.util.Comparator;

/**
 * Authors: Jason Butterworth (jbutterworth) and Dylan Carothers II (dylanacarothers).
 * Class Purpose: Create BookRead object to build off of Book class, including data about its read status.
 * Encapsulation Notes: Immutable Book class will be used as an attribute, while primitive type of boolean (also immutable)
 * dictates the read status. 
 */
public class BookRead {
	private Book book;
	private boolean isRead;

	/*
	 * Constructor for the BookRead class. Bundles previous Book class with a new
	 * attribute to mark its read status.
	 * 
	 * @param Book. The Book object we will be initializing our instance variable
	 * with.
	 */
	public BookRead(Book myBook) {
		this.book = myBook;
		isRead = false;
	}

	/*
	 * Setter method which will be used to update a BookRead object's read status.
	 * Read status will only be changed to true, as it is initialized to false in
	 * constructor.
	 */
	public void setRead() {
		this.isRead = true;
	}

	/*
	 * Getter method which will be used to access the instance variable of our Book
	 * object. This is an escaping reference, but its okay, as Book class is final
	 * and immutable.
	 */
	public Book getBook() {
		return this.book;
	}

	/*
	 * Getter method which will return the boolean value representing the read value
	 * of the book. Object will not act as escaping reference, as Book class is
	 * final and immutable.
	 */
	public boolean isRead() {
		return isRead;
	}

	public static Comparator<BookRead> createTitleComparator() {
		return new Comparator<BookRead>() {
			public int compare(BookRead book1, BookRead book2) {
				return book1.getBook().getTitle().compareTo(book2.getBook().getTitle());
			}
		};
	}

	public static Comparator<BookRead> createAuthorComparator() {
		return new Comparator<BookRead>() {
			public int compare(BookRead book1, BookRead book2) {
				return book1.getBook().getAuthor().compareTo(book2.getBook().getAuthor());
			}
		};
	}

}
