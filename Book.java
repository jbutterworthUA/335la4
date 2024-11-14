
/**
 * Authors: Jason Butterworth (jbutterworth) and Dylan Carothers II (dylanacarothers).
 * Class Purpose: Create a Book object that will store immutable qualities of title and author.
 * Encapsulation Notes: Book class will be final, allowing us to use getter methods without worrying
 * about mutation.
 */

final class Book {
	private String title;
	private String author;

	/*
	 * Constructor for the Book class.
	 * @param title. String representing the title of the book.
	 * @param author. String representing the author of the book.
	 */
	public Book(String title, String author) {
		if (title == null || author == null) {
			throw new IllegalArgumentException();
		}
		this.title = title;
		this.author = author;
	}

	/*
	 * Helper method to get title of the book. Since book is final and title is
	 * provided, prevents escaping reference.
	 * 
	 * @return String. The title of the book, which is immutable.
	 */
	public String getTitle() {
		return this.title;
	}

	/*
	 * Helper method to get author of the book. Since book is final and author is
	 * provided, prevents escaping reference.
	 * 
	 * @return String. The author of the book, which is immutable.
	 */
	public String getAuthor() {
		return this.author;
	}
}
