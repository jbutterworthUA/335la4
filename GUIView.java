import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class GUIView extends JFrame {
    private static LibrarySetup model = new LibrarySetup();
	private JPanel panel;
    private JLabel instructionLabel;
	private JTextArea outputArea;
    private JLabel extraLabel;
	
	public GUIView() {
		setUp();
	}
	
	private void setUp() {
		//setting size of frame
		this.setSize(800,800);
		

        // Setting up the output area.
		outputArea = new JTextArea();
		outputArea.setSize(400,500);
        outputArea.setEditable(false);
        outputArea.setBounds(100, 100, 600, 500);
        
        // Setting up the scroll pane.
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(100, 100, 600, 500);
        this.add(scrollPane);

        // Add instruction label above the input text field.
        instructionLabel = new JLabel("Welcome! Click a button to interact with your library.");
        instructionLabel.setBounds(180, 680, 620, 20);
        this.add(instructionLabel);
		
        // Add additional instruction label above the input text field.
        extraLabel = new JLabel("");
        extraLabel.setBounds(180, 710, 620, 20);
        this.add(extraLabel);
		
		//adding the panel
		panel = new JPanel();
		this.add(panel);

		//adding a window listener for closing the app
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
	}
	
	private void start() {

		// Setting up the required buttons.
        JButton searchButton = new JButton("Search");
        searchButton.setActionCommand("search");
        searchButton.addActionListener(new ButtonClickListener());
        panel.add(searchButton);

        JButton addBookButton = new JButton("Add Book");
        addBookButton.setActionCommand("addBook");
        addBookButton.addActionListener(new ButtonClickListener());
        panel.add(addBookButton);

        JButton setBookButton = new JButton("Set Book to Read");
        setBookButton.setActionCommand("set");
        setBookButton.addActionListener(new ButtonClickListener());
        panel.add(setBookButton);

        JButton rateButton = new JButton("Rate Book");
        rateButton.setActionCommand("rate");
        rateButton.addActionListener(new ButtonClickListener());
        panel.add(rateButton);

        JButton getBooksButton = new JButton("Get Books");
        getBooksButton.setActionCommand("get");
        getBooksButton.addActionListener(new ButtonClickListener());
        panel.add(getBooksButton);

        JButton suggestButton = new JButton("Suggest Read");
        suggestButton.setActionCommand("suggest");
        suggestButton.addActionListener(new ButtonClickListener());
        panel.add(suggestButton);

        JButton addBooksButton = new JButton("Add Books");
        addBooksButton.setActionCommand("addBooks");
        addBooksButton.addActionListener(new ButtonClickListener());
        panel.add(addBooksButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setActionCommand("exit");
        exitButton.addActionListener(new ButtonClickListener());
        panel.add(exitButton);


	}
	
	//Listener for the two buttons
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			if(command.equals("search")) {
                ArrayList<String> validMethods = getSearchMethods();
                outputArea.setText("");
				String searchMethod = JOptionPane.showInputDialog(GUIView.this, "How would you like to search for the book? (title, author, rate)");
                // If user did not enter a valid search method, continue prompting until they do so.
				while (!validMethods.contains(searchMethod)) {
					searchMethod = JOptionPane.showInputDialog(GUIView.this, "How would you like to search for the book? (title, author, rate)");
					searchMethod = searchMethod.toLowerCase();
				}
                if (searchMethod.equals("title")) {
                    String keyword = JOptionPane.showInputDialog(GUIView.this, "Enter the " + searchMethod + " of the book you are looking for.");
                    ArrayList<BookRead> possibleBooks = model.search(searchMethod, keyword, 0);
                    if (possibleBooks.size() == 0) {
                        instructionLabel.setText("No books in library matching your search.");
                    }
                    for (int i = 0; i < possibleBooks.size(); i++) {
                        outputArea.append(possibleBooks.get(i).getBook().getTitle() + " by ");
                        outputArea.append(possibleBooks.get(i).getBook().getAuthor() + "\n");
                    }
                }
                if (searchMethod.equals("author")) {
                    String keyword = JOptionPane.showInputDialog(GUIView.this, "Enter the " + searchMethod + " of the book you are looking for.");
                    ArrayList<BookRead> possibleBooks = model.search(searchMethod, keyword, 0);
                    if (possibleBooks.size() == 0) {
                        instructionLabel.setText("No books in library matching your search.");
                    }
                    for (int i = 0; i < possibleBooks.size(); i++) {
                        outputArea.append(possibleBooks.get(i).getBook().getTitle() + " by ");
                        outputArea.append(possibleBooks.get(i).getBook().getAuthor() + "\n");
                    }
                }
                if (searchMethod.equals("rate")) {
                    String rateWord = JOptionPane.showInputDialog(GUIView.this, "Enter the " + searchMethod + " of the book you are looking for. (1 - 5)");
                    int rate = Integer.parseInt(rateWord);
                    while (rate < 1 || rate > 5) {
                        rateWord = JOptionPane.showInputDialog(GUIView.this, "Enter the " + searchMethod + " of the book you are looking for. (1 - 5)");
                        rate = Integer.parseInt(rateWord);
                    }
                    ArrayList<BookRead> possibleBooks = model.search(searchMethod, "", rate);
                    if (possibleBooks.size() == 0) {
                        instructionLabel.setText("No books in library matching your search.");
                    }
                    for (int i = 0; i < possibleBooks.size(); i++) {
                        outputArea.append(possibleBooks.get(i).getBook().getTitle() + " ");
                        outputArea.append(possibleBooks.get(i).getBook().getAuthor() + "\n");
                    }
                }
				
			} else if(command.equals("addBook")) {
                outputArea.setText("");
                String title = JOptionPane.showInputDialog(GUIView.this, "Enter the title of the book to be added: ");
                String author = JOptionPane.showInputDialog(GUIView.this, "Enter the author of the book to be added: ");
                model.addBook(title, author);
                instructionLabel.setText(title + " by " + author + " has been added to your library.");

			} else if(command.equals("set")) {
                outputArea.setText("");
                String title = JOptionPane.showInputDialog(GUIView.this, "Enter the title of the book you want to mark as read: ");
                String author = JOptionPane.showInputDialog(GUIView.this, "Enter the author of the book you want to mark as read: ");
                int completed = model.setToRead(title, author);
                if (completed == 1) {
                    instructionLabel.setText(title + " by " + author + " has been marked as read.");
                }
                else {
                    instructionLabel.setText(title + " by " + author + " does not exist in your library.");
                }
			} else if(command.equals("rate")) {
                outputArea.setText("");
				String title = JOptionPane.showInputDialog(GUIView.this, "Enter the title of the book you would like to rate: ");
                String author = JOptionPane.showInputDialog(GUIView.this, "Enter the author of the book you would like to rate: ");
                String rateString = JOptionPane.showInputDialog(GUIView.this, "Enter the rate. (1 - 5)");
                int newRate = Integer.parseInt(rateString);
                while (newRate < 1 || newRate > 5) {
                    rateString = JOptionPane.showInputDialog(GUIView.this, "Enter the rate. (1 - 5)");
                    newRate = Integer.parseInt(rateString);
                }
                model.rate(title, author, newRate);
                instructionLabel.setText("The rate for " + title + " by " + author + " has been updated.");

			} else if(command.equals("get")) {
                outputArea.setText("");
                ArrayList<String> validMethods = getSortMethods();
				String sortMethod = JOptionPane.showInputDialog(GUIView.this, "How would you like your books sorted? (title, author, read, unread)");
                while (!validMethods.contains(sortMethod)) {
            		sortMethod = JOptionPane.showInputDialog(GUIView.this, "Please enter a valid sorting method (title, author, read, unread):");
                    sortMethod = sortMethod.toLowerCase();
        		}
                MasterList sorted = model.getBooks(sortMethod);
                for (int i = 0; i < sorted.size(); i++) {
                    outputArea.append(sorted.get(i).getBook().getTitle() + " by ");
                    outputArea.append(sorted.get(i).getBook().getAuthor() + "\n");
                }
			} else if(command.equals("suggest")) {
                outputArea.setText("");
                Book suggestion = model.suggestRead();
                if (suggestion == null) {
                    instructionLabel.setText("No unread books in your library.");
                }
                String title = suggestion.getTitle();
				String author = suggestion.getAuthor();
                instructionLabel.setText("Try reading " + title + " by " + author);
				
			} else if(command.equals("addBooks")) {
				outputArea.setText("");
                String fileName = JOptionPane.showInputDialog(GUIView.this, "Enter the name of the text file you want to add:");
				model.addBooks(fileName);
                instructionLabel.setText("The contents from " + fileName + " have been added to your library.");
			} else if(command.equals("exit")) {
                outputArea.setText("");
				System.exit(0);	
			}
		}
	}

    // Helper method to check user entered valid sorting method.
    private ArrayList<String> getSortMethods() {
        ArrayList<String> validSortMethods = new ArrayList<>();
        validSortMethods.add("title");
        validSortMethods.add("author");
        validSortMethods.add("read");
        validSortMethods.add("unread");
        return validSortMethods;
    }

    // Helper method to check user entered valid searching method.
    private ArrayList<String> getSearchMethods() {
        ArrayList<String> validSearchMethods = new ArrayList<>();
        validSearchMethods.add("title");
        validSearchMethods.add("author");
        validSearchMethods.add("rate");
        return validSearchMethods;
    }

	//main method
	public static void main(String[] args) {
		GUIView view = new GUIView();
		view.start();
		view.setVisible(true);
	}
}