import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RatedBookListTest {
	private Book jasonBook = new Book("JB Book", "Jason");
	private BookRead jasonBookRead = new BookRead(jasonBook);
	private BookReview jasonBookReview = new BookReview(jasonBookRead, 1);
	private Book dylanBook = new Book("DC Book", "Dylan");
	private BookRead dylanBookRead = new BookRead(dylanBook);
	private BookReview dylanBookReview = new BookReview(dylanBookRead, 3);
	private RatedBookList myRatedList = new RatedBookList();

	@Test
	void emptySizeTest() {
		assertEquals(myRatedList.size(), 0);
	}
	
	@Test
	void addReviewBookTest() {
		myRatedList.addReviewBook(jasonBookReview);
		myRatedList.addReviewBook(dylanBookReview);
	}
	
	@Test
	void sizeTest() {
		assertEquals(myRatedList.size(), 2);
	}
	
	@Test
	void getTest() {
		assertEquals(myRatedList.get(1), dylanBookReview);
	}
}
