import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BookTest {
	private Book myBook = new Book("Title", "Author");
	
	@Test
	void titleTest() {
		assertEquals("Title", myBook.getTitle());
		
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
		assertEquals("Author", myBook.getAuthor());
	}
	
	@Test
	void emptyAuthorTest() {
		Exception myException = assertThrows(IllegalArgumentException.class, () -> {
			new Book("Title", null);
		});
		
		assertEquals(IllegalArgumentException.class, myException.getClass());
	}
}
