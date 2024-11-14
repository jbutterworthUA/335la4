/**
 * Authors: Jason Butterworth (jbutterworth) and Dylan Carothers II (dylanacarothers).
 * Class Purpose: Create BookReview object to build off of BookRead class, including data regarding its user rating.
 * Encapsulation Notes: Class will only return primitive data which cannot be changed,
 * and objects will never be passed outside references.
 */

public class BookReview {
    private BookRead bookRead;
    private int rate;

    /*
	 * Constructor for the BookReview class. Bundles previous BookRead class with a
	 * new attribute to mark its user rating.
	 * 
	 * @param BookRead. The BookRead object we will be initializing our instance
	 *                  variable with.
	 * @param int.      The rating we will be initializing our instance variable
	 *                  with. Determined by user.
	 */
    public BookReview(BookRead myBookRead, int myRate) {
        this.bookRead = myBookRead;
        this.rate = myRate;
    }

    /*
    * @pre myRate should be an int from 1-5
    */
    public void setRate(int myRate) {
        this.rate = myRate;
    }

    /**
     * Retrieve the current user rating for a book in the rated list.
     * @return int between 1 - 5 for the rating.
     */
    public int getRate() {
        return this.rate;
    }
    
    /**
     * Retrieve the current BookRead attribute of the BookReview object.
     * All instances of method will be called after already having index, so idx param not needed.
     * @return BookRead object.
     */
    public BookRead getBook() {
    	return bookRead;
    }

}
