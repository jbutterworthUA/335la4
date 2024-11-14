import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/*
 * Authors: Jason Butterworth (jbutterworth) and Dylan Carothers II (dylanacarothers)
 * Class Purpose: Serve as the intermediary between the client-side MyLibary UI
 * class and those which control the overall management of each object. Will
 * handle the actual tasks of user requests by routing to appropriate class.
 */

public class LibrarySetup {
	private MasterList masterList;
	private RatedBookList ratedList;

    /**
     * Constructor for the LibrarySetup class.
     * Will initialize new masterList and ratedList objects to store books.
     */
	public LibrarySetup() {
		masterList = new MasterList();
		ratedList = new RatedBookList();
	}

    /**
     * Given a txt file from the user, add all books within that file to the masterList.
     */
	public boolean addBooks(String fileName) {
		Scanner inFile = null;
		try {
			// Create file if possible.
			inFile = new Scanner(new File(fileName));
		} catch (Exception e) {
			// Otherwise, alert user that file was not found.
			return false;
		}

		// Skip the first line of the file.
		inFile.nextLine();
		// Split the file and create BookRead object for each available line.
		while (inFile.hasNextLine()) {
			String currentLine = inFile.nextLine();
			String[] lineArray = currentLine.split(";");
			String title = lineArray[0];
			String author = lineArray[1];
			masterList.addBook(title, author);
		}
		return true;
	}

    /**
     * Given a title and author from the user, add a new book to the masterList.
     */
	public boolean addBook(String title, String author) {
		return masterList.addBook(title, author);
	}

    /**
     * Retrieve all books in the user's library, organized by user-determined method.
     * (Title/Author/Read Status)
     */
	public MasterList getBooks(String sortMethod) {
        // Get all books sorted by title.
        if (sortMethod.equals("title")) {
            masterList.sortTitle();
            return masterList;
        }

        // Get all books sorted by author.
        if (sortMethod.equals("author")) {
            masterList.sortAuthor();
            return masterList;
        }

        // Get all books that have been read. (Sorted by title.)
        if (sortMethod.equals("read")) {
            masterList.sortTitle();
            return masterList;
        }

        // Get all books that have not been read (Sorted by title.)
        if (sortMethod.equals("unread")) {
           masterList.sortTitle();
           return masterList;
        }
        return null;
	}

	/*
	 * Suggest a random unread book from the user's library for them to read.
	 */
	public Book suggestRead() {
		// Retrieve all possible unread books using our helper method.
		ArrayList<BookRead> unreadBooks = getUnreadBooks();
		
		// If there are no unread books to suggest from, print a message and exit.
		if (unreadBooks.size() == 0) {
			return null;
		}
		
		// Shuffle the list of unread books and retrieve Book object at index 0.
		Collections.shuffle(unreadBooks);
		Book suggestion = unreadBooks.get(0).getBook();

        return suggestion;
	}

	/*
	 * Helper method to retrieve all books which have not been read.
	 * @return ArrayList<BookRead>. Collection of all books with an unread status.
	 */
	private ArrayList<BookRead> getUnreadBooks() {
		ArrayList<BookRead> unreadBooks = new ArrayList<>();
		// Iterate through every book in our masterList and check its status.
		for (int i = 0; i < masterList.size(); i++) {
			// If the current book has not been read, add to unread list.
			if (!masterList.get(i).isRead()) {
				unreadBooks.add(masterList.get(i));
			}
		}
		return unreadBooks;
	}

    /**
     * Search for specific book(s) using a method determined by the user.
     */
	public ArrayList<BookRead> search(String searchMethod, String searchKeyword, int searchRate) {
		
        ArrayList<BookRead> possibleBooks = new ArrayList<BookRead>();
		// If we are searching by title, call searchByTitle helper method.
		if (searchMethod.equals("title")) {
            // Retrieve all possible books.
            possibleBooks = searchByTitle(searchKeyword);

            return possibleBooks;
		}

		// If we are searching by author, we will iterate through masterList.
		if (searchMethod.equals("author")) {
            // Retrieve all possible books.
            possibleBooks = searchByAuthor(searchKeyword);
            return possibleBooks;
		}

        if (searchMethod.equals("rate")) {
            ArrayList<BookReview> initialList = searchByRate(searchRate);
            possibleBooks = new ArrayList<BookRead>();
            // Convert BookReview objects to BookRead so we can export.
            for (int i = 0; i < initialList.size(); i++) {
                BookRead myBook = initialList.get(i).getBook();
                possibleBooks.add(myBook);
            }
        }
        return possibleBooks;
	}
    public ArrayList<BookReview> getPossibleRatedBooks(ArrayList<BookReview> possibleBooks) {
        return possibleBooks;
    }
    

    /**
     * Helper method to retrieve all books which have the same title the user has inputted.
     * @param searchTitle
     * @return ArrayList of all possible books the user could be searching for.
     */
    private ArrayList<BookRead> searchByTitle(String searchTitle) {
        // Create list to store all possible books.
        ArrayList<BookRead> possibleBooks = new ArrayList<>();

        // Search every book in masterList, checking if the title matches our searchTitle.
        for (int i = 0; i < masterList.size(); i++) {
            String currentTitle = masterList.get(i).getBook().getTitle();
            // If the title of the current book matches, add it to possible books.
            if (currentTitle.equals(searchTitle)) {
                possibleBooks.add(masterList.get(i));
            }
        }
        return possibleBooks;
    }

    /**
     * Helper method to retrieve all books which have the same author the user has inputted.
     * @param searchAuthor
     * @return ArrayList of all possible books the user could be searching for.
     */
    private ArrayList<BookRead> searchByAuthor(String searchAuthor) {
        // Create list to store all possible books.
        ArrayList<BookRead> possibleBooks = new ArrayList<>();

        // Search every book in masterList, checking if the title matches our searchTitle.
        for (int i = 0; i < masterList.size(); i++) {
            String currentAuthor = masterList.get(i).getBook().getAuthor();
            // If the title of the current book matches, add it to possible books.
            if (currentAuthor.equals(searchAuthor)) {
                possibleBooks.add(masterList.get(i));
            }
        }
        return possibleBooks;
    }

    /**
     * Helper method to retrieve all books which have the same rate the user has inputted.
     * @param searchRate
     * @return ArrayList of all possible books the user could be searching for.
     */
    private ArrayList<BookReview> searchByRate(int searchRate) {
        // Create list to store all possible books.
        ArrayList<BookReview> possibleBooks = new ArrayList<>();

        // Search every book in masterList, checking if the title matches our searchTitle.
        for (int i = 0; i < ratedList.size(); i++) {
            int currentRate = ratedList.get(i).getRate();
            // If the title of the current book matches, add it to possible books.
            if (currentRate == searchRate) {
                possibleBooks.add(ratedList.get(i));
            }
        }
        return possibleBooks;
    }

	/*
	 * Helper method to determine if a user has given a valid method to search for a
	 * book.
	 * 
	 * @param input. The String containing a user's input method. @return. True if
	 * user input a valid way to search, false if not.
	 */
	private boolean isValid(String input) {
		// Create place to store valid inputs from user.
		ArrayList<String> validMethods = new ArrayList<String>(3);
		validMethods.add("title");
		validMethods.add("author");
		validMethods.add("rate");

		if (validMethods.contains(input)) {
			return true;
		}

		return false;
	}

    /**
     * Set/update rating of a book in the user's library.
     */
	public boolean rate(String searchTitle, String searchAuthor, int newRate) {
        
        // Integer to keep track of if we have completed the rating process.
        int found = 0;

        // Check if the book we are searching for is already in ratedList for us to update.
        for (int i = 0; i < ratedList.size(); i++) {
            String currentTitle = ratedList.get(i).getBook().getBook().getTitle();
            String currentAuthor = ratedList.get(i).getBook().getBook().getAuthor();
            // If the book is already in ratedList, get the new rating and update it.
            if (currentTitle.equals(searchTitle) && currentAuthor.equals(searchAuthor)) {
                // Mark that we have found the book.
                found = 1;
                ratedList.get(i).setRate(newRate);
                return true;
            }
        }

        // If we did not find the book in ratedList, we will search masterList and add it to ratedList.
        if (found == 0) {
            for (int i = 0; i < masterList.size(); i++) {
                BookRead currentBookRead = masterList.get(i);
                String currentTitle = masterList.get(i).getBook().getTitle();
                String currentAuthor = masterList.get(i).getBook().getAuthor();

                // When we find the book in masterList, we will need to create BookReview object and add to ratedList.
                if (currentTitle.equals(searchTitle) && currentAuthor.equals(searchAuthor)) {
                    // Mark that we have found the book.
                    found = 1;
                    BookReview newReview = new BookReview(currentBookRead, newRate);
                    ratedList.addReviewBook(newReview);
                    return true;
                }
            }
        }
        return false;
	}

    /**
     * Set the status of a user's book to read.
     */
	public int setToRead(String title, String author) {
		// Find the correct book in our masterList and update its read status.
		for (int i = 0; i < masterList.size(); i++) {
			String currentTitle = masterList.get(i).getBook().getTitle();
			String currentAuthor = masterList.get(i).getBook().getAuthor();
			// If title and author match, set the book's status to read.
			if (currentTitle.equals(title) && currentAuthor.equals(author)) {
				masterList.get(i).setRead();
			}
            return 1;
		}
        return 0;
	}
}
