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
		assertTrue(myList.addBook("The Jason Book", "Jason"));
		assertTrue(myList.addBook("The Dylan Book", "Dylan"));
		assertFalse(myList.addBook("The Dylan Book", "Dylan"));
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
	
	@Test
	void masterListSortingTest() {
		myList.addBook("a", "z");
		myList.addBook("b", "y");
		myList.addBook("c", "x");
		myList.addBook("d", "w");
		myList.addBook("d", "w");
		myList.addBook("e", "v");
		myList.sortAuthor();
		assertEquals("e", myList.get(0).getBook().getTitle());
		assertEquals("d", myList.get(1).getBook().getTitle());
		assertEquals("a", myList.get(4).getBook().getTitle());
		myList.sortTitle();
		assertEquals("z", myList.get(0).getBook().getAuthor());
		assertEquals("y", myList.get(1).getBook().getAuthor());
		assertEquals("v", myList.get(4).getBook().getAuthor());
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
	
	
	private LibrarySetup setup = new LibrarySetup();
	
	@Test
	void setupAddBooksTest() {
		assertFalse(setup.addBooks("fakeName.txt"));
		assertTrue(setup.addBooks("/Users/dylancarothers/335Workspace/books.txt"));
	}
	
	@Test
	void setupAddBookTest() {
		assertTrue(setup.addBook("title1", "author1"));
		assertTrue(setup.addBook("title2", "author2"));
		assertFalse(setup.addBook("title2", "author2"));
		assertTrue(setup.addBook("title3", "author3"));
		assertTrue(setup.addBook("title1", "author2"));
		assertTrue(setup.addBook("title2", "author1"));
	}
	
	@Test
	void setupGetBooksTest() {
		setup.addBook("title1", "author3");
		setup.addBook("title2", "author2");
		setup.addBook("title3", "author1");
		assertTrue(setup.setToRead("title2", "author2"));
		MasterList masterList = setup.getBooks("author");
		assertEquals(masterList.get(0).getBook().getTitle(), "title3");
		masterList = setup.getBooks("title");
		assertEquals(masterList.get(0).getBook().getAuthor(), "author3");
		masterList = setup.getBooks("read");
	//	assertEquals(masterList.get(0).getBook().getTitle(), "title2");
//		assertEquals(masterList.size(), 1);
		masterList = setup.getBooks("unread");
		assertEquals(masterList.get(0).getBook().getTitle(), "title1");
		assertEquals(masterList.get(1).getBook().getTitle(), "title3");
		assertEquals(masterList.size(), 2);
		masterList = setup.getBooks("fakeSearch");
		assertEquals(masterList, null);
	}
	
	@Test
	void setupSuggestTest() {
		setup.addBook("title1", "author3");
		setup.addBook("title2", "author2");
		setup.setToRead("title2", "author2");
		assertEquals(setup.suggestRead().getTitle(), "title1");
		setup.setToRead("title1", "author3");
		assertEquals(setup.suggestRead(), null);
	}
	
	@Test
	void setupSearchTest() {
		setup.addBook("title1", "author3");
		setup.addBook("title2", "author2");
		setup.addBook("title3", "author1");
		setup.rate("title1", "author3", 1);
		setup.rate("title2", "author2", 2);
		setup.rate("title3", "author1", 3);
		assertEquals(setup.search("title", "title3", 10000).get(0).getBook().getAuthor(), "author1");
		assertEquals(setup.search("author", "author1", 10000).get(0).getBook().getTitle(), "title3");
		assertEquals(setup.search("rate", "fakeValue", 2).get(0).getBook().getTitle(), "title2");
		assertEquals(setup.search("invalidSearch", "title3", 10000).size(), 0);
	}
	
	@Test
	void setupRateTest() {
		setup.addBook("title1", "author3");
		setup.addBook("title2", "author2");
		setup.addBook("title3", "author1");
		assertTrue(setup.rate("title1", "author3", 1));
		assertTrue(setup.rate("title2", "author2", 2));
		assertTrue(setup.rate("title3", "author1", 3));
		assertTrue(setup.rate("title1", "author3", 5));
		assertTrue(setup.rate("title2", "author2", 4));
		assertTrue(setup.rate("title3", "author1", 3));
		assertFalse(setup.rate("title4", "author4", 3));
		assertFalse(setup.rate("title1", "author4", 3));
		assertFalse(setup.rate("title4", "author3", 3));
	}
	
	@Test
	void setupSetToReadTest() {
		setup.addBook("title1", "author3");
		setup.addBook("title2", "author2");
		setup.addBook("title3", "author1");
		assertTrue(setup.setToRead("title1", "author3"));
		assertTrue(setup.setToRead("title2", "author2"));
		assertTrue(setup.setToRead("title3", "author1"));
		assertFalse(setup.setToRead("title1", "author2"));
		assertFalse(setup.setToRead("title2", "author3"));
	}
}
