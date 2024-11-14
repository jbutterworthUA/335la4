import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BookReviewTest {
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
	void getBookTest() {
		assertEquals(myBookReview.getBook(), myBookRead);
	}
}
