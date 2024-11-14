import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

class libraryTest {

	private Book jasonBook = new Book("The Jason Book", "Jason");
	private BookRead jasonBookRead = new BookRead(jasonBook);
	private Book dylanBook = new Book("The Dylan Book", "Dylan");
	private BookRead dylanBookRead = new BookRead(dylanBook);
	private BookRead dylanBookRead2 = new BookRead(dylanBook);
	
	
	@Test
	void getBookReadTest() {
		
		assertEquals(jasonBook, jasonBookRead.getBook());
	}
	
	@Test
	void readTest() {
		assertFalse(jasonBookRead.isRead());
		jasonBookRead.setRead();
		assertTrue(jasonBookRead.isRead());
	}
	
	@Test
	void titleSortTest() {
		Comparator<BookRead> myComparator = BookRead.createTitleComparator();
		assertTrue(myComparator.compare(jasonBookRead, dylanBookRead) > 0);
		assertTrue(myComparator.compare(dylanBookRead, jasonBookRead) < 0);
		assertEquals(myComparator.compare(dylanBookRead, dylanBookRead2), 0);
		
	}
	
	@Test
	void authorSortTest() {
		Comparator<BookRead> myComparator = BookRead.createAuthorComparator();
		assertTrue(myComparator.compare(jasonBookRead, dylanBookRead) > 0);
		assertTrue(myComparator.compare(dylanBookRead, jasonBookRead) < 0);
		assertEquals(myComparator.compare(dylanBookRead, dylanBookRead2), 0);
	}
	
	
	private Book myBook = new Book("JB Book", "Jason");
	private BookRead myBookRead = new BookRead(myBook);
	private BookReview myBookReview = new BookReview(myBookRead, 1);
	
	@Test
	void rateTest() {
		assertEquals(myBookReview.getRate(), 1);
		myBookReview.setRate(5);
		assertEquals(myBookReview.getRate(), 5);
	}
	
	@Test
	void getBookReviewTest() {
		assertEquals(myBookReview.getBook(), myBookRead);
	}
	
	
	
	@Test
	void titleTest() {
		assertEquals("JB Book", myBook.getTitle());
		
	}
	
	@Test
	void emptyTitleTest() {
		Exception myException = assertThrows(IllegalArgumentException.class, () -> {
			new Book(null, "Author");
		});
		
		assertEquals(IllegalArgumentException.class, myException.getClass());
	}
	
	@Test
	void authorTest() {
		assertEquals("Jason", myBook.getAuthor());
	}
	
	@Test
	void emptyAuthorTest() {
		Exception myException = assertThrows(IllegalArgumentException.class, () -> {
			new Book("Title", null);
		});
		
		assertEquals(IllegalArgumentException.class, myException.getClass());
	}
	
	
	private MasterList myList = new MasterList();
	
	@Test
	void emptyMasterListSizeTest() {
		assertEquals(myList.size(), 0);
	}
	
	@Test
	void addBookTest() {
		myList.addBook("The Jason Book", "Jason");
		myList.addBook("The Dylan Book", "Dylan");
		myList.addBook("The Dylan Book", "Dylan");
		System.out.println(myList.size());
	}
	
	@Test
	void masterListSizeTest() {
		myList.addBook("The Jason Book", "Jason");
		myList.addBook("The Dylan Book", "Dylan");
		myList.addBook("The Dylan Book", "Dylan");
		assertEquals(myList.size(), 2);
	}
	
	@Test
	void masterListGetTest() {
		myList.addBook("The Jason Book", "Jason");
		myList.addBook("The Dylan Book", "Dylan");
		myList.addBook("The Dylan Book", "Dylan");
		assertEquals(myList.get(0).getBook().getTitle(), "The Jason Book");
		assertEquals(myList.get(0).getBook().getAuthor(), "Jason");
	}
	
	

	private BookReview jasonBookReview = new BookReview(jasonBookRead, 1);
	private BookReview dylanBookReview = new BookReview(dylanBookRead, 3);
	private RatedBookList myRatedList = new RatedBookList();

	@Test
	void emptyBookReviewSizeTest() {
		assertEquals(myRatedList.size(), 0);
	}
	
	@Test
	void addReviewBookTest() {
		myRatedList.addReviewBook(jasonBookReview);
		myRatedList.addReviewBook(dylanBookReview);
	}
	
	@Test
	void bookReviewSizeTest() {
		assertEquals(myRatedList.size(), 0);
		myRatedList.addReviewBook(jasonBookReview);
		myRatedList.addReviewBook(dylanBookReview);
		assertEquals(myRatedList.size(), 2);
	}
	
	@Test
	void bookReviewGetTest() {
		myRatedList.addReviewBook(jasonBookReview);
		myRatedList.addReviewBook(dylanBookReview);
		assertEquals(myRatedList.get(1), dylanBookReview);
	}
}
