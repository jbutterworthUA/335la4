/*
 * Authors: Jason Butterworth (jbutterworth) and Dylan Carothers II (dylanacarothers).
 * Class Purpose: Running a text-based UI allowing user to get or add information
 * to a personal virtual library.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class MyLibrary {

	private static LibrarySetup mainLibrary = new LibrarySetup();

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		boolean continueLoop = true;

		ArrayList<String> validSortMethods = new ArrayList<>();
        validSortMethods.add("title");
        validSortMethods.add("author");
        validSortMethods.add("read");
        validSortMethods.add("unread");

		ArrayList<String> validSearchMethods = new ArrayList<>();
        validSearchMethods.add("title");
        validSearchMethods.add("author");
        validSearchMethods.add("rate");

		System.out.println("Welcome to your library!");
		System.out.println("Enter a command or type 'showCommands' to print a list of possible commands.");
		System.out.println("Commands ARE case sensitive!");

		while (continueLoop) {
			String input = keyboard.nextLine();

			if (input.equals("search")) {
				// Ask user how they want to search for the book.
				System.out.println("How would you like to search for the book?");
				System.out.println("Please input title, author, or rate: ");
				String answer = keyboard.nextLine();
				String searchMethod = answer.toLowerCase();

				// If user did not enter a valid search method, continue prompting until they do so.
				while (!validSearchMethods.contains(searchMethod)) {
					System.out.println("Please input a valid search method (title, author, rate): ");
					answer = keyboard.nextLine();
					searchMethod = answer.toLowerCase();
				}
				
				if (searchMethod.equals("title")) {
					System.out.println("Enter the " + searchMethod + " of the book you are looking for.");
					String searchKeyword = keyboard.nextLine();
					ArrayList<BookRead> possibleBooks = mainLibrary.search(searchMethod, searchKeyword, 0);
					displayBooks(possibleBooks);
					
				}

				if (searchMethod.equals("author")) {
					System.out.println("Enter the " + searchMethod + " of the book you are looking for.");
					String searchKeyword = keyboard.nextLine();
					ArrayList<BookRead> possibleBooks = mainLibrary.search(searchMethod, searchKeyword, 0);
					displayBooks(possibleBooks);
				}

				if (searchMethod.equals("rate")) {
					System.out.println("Enter the " + searchMethod + " of the book you are looking for. (1 - 5)");
					int searchRate = keyboard.nextInt();
					while (searchRate < 1 || searchRate > 5) {
						System.out.println("Enter the " + searchMethod + " of the book you are looking for. (1 - 5)");
						searchRate = keyboard.nextInt();
					}
					ArrayList<BookRead> possibleBooks = mainLibrary.search(searchMethod, "", searchRate);
					displayBooks(possibleBooks);
				}


				
			} 
			else if (input.equals("addBook")) {
				// Ask user for appropriate info needed for book.
				System.out.println("Enter the title of the book to be added:");
				String title = keyboard.nextLine();
				System.out.println("Enter the author of the book to be added:");
				String author = keyboard.nextLine();
				mainLibrary.addBook(title, author);
				System.out.println(title + " by " + author + " has been added to your library.");
			} 
			else if (input.equals("setToRead")) {
				// Ask the user which book they want to mark as read.
				System.out.println("Enter the title of the book you want to mark as read: ");
				String title = keyboard.nextLine();
				System.out.println("Enter the author of the book you want to mark as read: ");
				String author = keyboard.nextLine();
				int completed = mainLibrary.setToRead(title, author);
				if (completed == 1) {
					System.out.println(title + " by " + author + " has been marked as read!");
				}
				else {
					System.out.println(title + " by " + author + " does not exist in your library.");
				}
			} 

			else if (input.equals("rate")) {
				// Get all info needed from user.
				System.out.println("Type the title of the book you would like to rate: ");
        		String searchTitle = keyboard.nextLine();
        		System.out.println("Type the author of the book you would like to rate: ");
        		String searchAuthor = keyboard.nextLine();
				System.out.println("What would you like the rating of the book to be? (1 - 5): ");
        		int newRate = keyboard.nextInt();
        		while (newRate < 1 || newRate > 5) {
            		System.out.println("Please enter a valid book rating (1 - 5): ");
            		newRate = keyboard.nextInt();
        		}
				mainLibrary.rate(searchTitle, searchAuthor, newRate);
				System.out.println("The rate of " + searchTitle + " by " + searchAuthor + " has been updated!");
			} 

			else if (input.equals("getBooks")) {
				System.out.println("How would you like your books sorted?\n(title, author, read, unread)?:");
        		input = keyboard.next();
        		String sortMethod = input.toLowerCase();
        		
       			while (!validSortMethods.contains(sortMethod)) {
            		System.out.println("Please enter a valid sorting method (title, author, read, unread):");
            		input = keyboard.nextLine();
            		sortMethod = input.toLowerCase();
        		}
				// Get the correctly sorted order of a MasterList.
				MasterList sorted = mainLibrary.getBooks(sortMethod);
				// Display each book to the user.
				for (int i = 0; i < sorted.size(); i++) {
					String currentTitle = sorted.get(i).getBook().getTitle();
					String currentAuthor = sorted.get(i).getBook().getAuthor();
					System.out.println(currentTitle + ", by " + currentAuthor);
				}
			} 

			else if (input.equals("suggestRead")) {
				Book suggestion = mainLibrary.suggestRead();
				if (suggestion == null) {
					System.out.println("No unread books in your library!");
					continue;
				}
				String title = suggestion.getTitle();
				String author = suggestion.getAuthor();
				// Display the suggestion message for the user.
				System.out.println("You should try reading " + title + ", by " + author + ".");
				
			} 

			else if (input.equals("addBooks")) {
				// Prompt user for name of text file.
				System.out.println("Enter the name of the text file:\n");
				String fileName = keyboard.nextLine();
				mainLibrary.addBooks(fileName);
				System.out.println("The contents from " + fileName + " have been added to your library.");
			} 

			else if (input.equals("exit")) {
				continueLoop = false;
				System.out.println("Goodbye!");
			} 

			else if (input.equals("showCommands")) {
				System.out.println("possible commands are:");
				System.out.println("search");
				System.out.println("addBook");
				System.out.println("setToRead");
				System.out.println("rate");
				System.out.println("getBooks");
				System.out.println("suggestRead");
				System.out.println("addBooks");
				System.out.println("showCommands");
				System.out.println("exit");
			}

			System.out.println("----------------------------------");
			System.out.println("Enter next command or type 'showCommands' to print a list of possible commands.");
		}
	}

	/**
     * Helper method to display a list of books to the user.
     * @param possibleBooks. ArrayList of BookRead objects we wish to display.
     */
    private static void displayBooks(ArrayList<BookRead> possibleBooks) {
        // Display all books which the user may have been searching for.
		// If there are no possible books, display alternate message.
		if (possibleBooks.size() == 0) {
			System.out.println("No books matching your search");
		} else {
			System.out.println("List of books matching your search: ");
			for (int i = 0; i < possibleBooks.size(); i++) {
				String title = possibleBooks.get(i).getBook().getTitle();
				String author = possibleBooks.get(i).getBook().getAuthor();

				System.out.println(title + ", by " + author + ".");
			}
		}
    }
}
