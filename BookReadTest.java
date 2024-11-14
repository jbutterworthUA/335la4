import static org.junit.jupiter.api.Assertions.*;
import java.util.Comparator;
import org.junit.jupiter.api.Test;

class BookReadTest {
	private Book jasonBook = new Book("The Jason Book", "Jason");
	private BookRead jasonBookRead = new BookRead(jasonBook);
	private Book dylanBook = new Book("The Dylan Book", "Dylan");
	private BookRead dylanBookRead = new BookRead(dylanBook);
	private BookRead dylanBookRead2 = new BookRead(dylanBook);
	
	
	@Test
	void getBookTest() {
		
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
}
