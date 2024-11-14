import java.util.ArrayList;
/**
* Authors: Jason Butterworth (jbutterworth) and Dylan Carothers II (dylanacarothers)
* Class Purpose: Class which will hold a list of all BookReview objects in library, for any book
* which the user has previously rated.
* Encapsulation Notes: No setter methods will ever be called on constructors (no worries of accepting escaping references).
* All returned references are to protected data (i.e BookReview objects).
*/

public class RatedBookList {
    private ArrayList<BookReview> allRatedBooks;

    /**
    * Constructor for RatedBookList class. Will initialize instance variable as a new
    * ArrayList to later be filled with BookReview objects.
    */
    public RatedBookList() {
        allRatedBooks = new ArrayList<BookReview>();
    }

    /**
    * Method to add a book which has now been given a user rating.
    * @param BookReview. The BookReview object being added to our reviewed books list.
    */
    public void addReviewBook(BookReview myBookReview) {
        allRatedBooks.add(myBookReview);
    }
    
    /**
     * Retrieve the BookReview object at a specific index from the rated book list.
     * @param index
     * @return BookReview object.
     */
    public BookReview get(int index) {
    	return allRatedBooks.get(index);
    }
    
    /**
     * Retrieve the current size of the rated book list.
     * @return int of current ArrayList size.
     */
    public int size() {
    	return allRatedBooks.size();
    }
}
