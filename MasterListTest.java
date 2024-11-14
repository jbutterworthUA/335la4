import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MasterListTest {
	
	private MasterList myList = new MasterList();
	
	@Test
	void emptySizeTest() {
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
	void sizeTest() {
		assertEquals(myList.size(), 2);
	}
	
	@Test
	void getTest() {
		assertEquals(myList.get(0).getBook().getTitle(), "The Jason Book");
		assertEquals(myList.get(0).getBook().getAuthor(), "Jason");
	}
	
}
