import java.util.ArrayList;
import java.util.Iterator;

public class Library {
    String address;
    static String hours = "9AM - 5PM";
    ArrayList<Book> bookList = new ArrayList<Book>();
    
    //Constructor
    public Library(String libraryAddress) {
    	address = libraryAddress;
    }
    
    
    //addBook() Method - adds book object to ArrayList bookList
    public void addBook(Object newBook) {
    	bookList.add((Book) newBook);
    }
    
    //printOpeningHours() method
    public static void printOpeningHours() {
    	System.out.println(hours);
    }
    
    //printAddress() method
    public void printAddress() {
    	System.out.println(address);
    }
    
    //borrowBook() method - checks for input title in bookList and then borrows it if available
    public void borrowBook(String bookTitle) {
    	Iterator<Book> itBookList = bookList.iterator();
    	boolean bookExists = false;
    	
    	while (itBookList.hasNext()) {
    		Book thisBook = itBookList.next();
    		
    		if (thisBook.title == bookTitle) {
    			if (thisBook.isBorrowed() == true) {
    				System.out.printf("Sorry! %s is currently checked out by someone else.\n\n", bookTitle);
    			} else {
    				System.out.printf("You've successfully checked out %s!\n\n", bookTitle);
    				thisBook.borrowed();
    			}
    			
    			bookExists = true;
    		} 
    	}
    	
    	if (bookExists == false) {
    		System.out.printf("Sorry! %s is not available at this library.\n\n", bookTitle);
    	}
    }

    //returnBook() method - finds the book and calls the Book returned() method
    public void returnBook(String bookTitle) {
    	Iterator<Book> itBookList = bookList.iterator();
    	boolean bookReturned = false;
    	
    	while (itBookList.hasNext()) {
    		Book thisBook = itBookList.next();
    		
    		if (thisBook.title == bookTitle) {
    			if (thisBook.isBorrowed() == true) {
    				thisBook.returned();
    				bookReturned = true;
    				System.out.printf("%s has been returned.\n\n", bookTitle);
    			} 
    		} 
    	}
    	
    	if (bookReturned == false) {
    		System.out.printf("Huh! We don't have any borrowed copies of %s that need to be returned.\n\n", bookTitle);
    	}
    }
    
    //printAvailableBooks() method - Lists all the books at the library and whether they are borrowed
    public void printAvailableBooks() {
    	Iterator<Book> itBookList = bookList.iterator();
    	
    	while(itBookList.hasNext()) {
    		Book thisBook = itBookList.next();
    		if (thisBook.borrowed == false) {
    			System.out.printf("%s is AVAILABLE\n", thisBook.title);
    		} else if(thisBook.borrowed == true) {
    			System.out.printf("%s is NOT AVAILABLE\n", thisBook.title);
    		}
    	}
    	
    	if (bookList.size() == 0) {
    		System.out.println("This library doesn't have ANY books!");
    	}
    }
    
    public static void main(String[] args) {
        // Create two libraries
        Library firstLibrary = new Library("10 Main St.");
        Library secondLibrary = new Library("228 Liberty St.");

        // Add four books to the first library
        firstLibrary.addBook(new Book("The Da Vinci Code"));
        firstLibrary.addBook(new Book("Le Petit Prince"));
        firstLibrary.addBook(new Book("A Tale of Two Cities"));
        firstLibrary.addBook(new Book("The Lord of the Rings"));

        // Print opening hours and the addresses
        System.out.println("Library hours:");
        printOpeningHours();
        System.out.println();

        System.out.println("Library addresses:");
        firstLibrary.printAddress();
        secondLibrary.printAddress();
        System.out.println();

        // Try to borrow The Lords of the Rings from both libraries
        System.out.println("Borrowing The Lord of the Rings:");
        firstLibrary.borrowBook("The Lord of the Rings");
        firstLibrary.borrowBook("The Lord of the Rings");
        secondLibrary.borrowBook("The Lord of the Rings");
        System.out.println();

        // Print the titles of all available books from both libraries
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
        System.out.println();
        System.out.println("Books available in the second library:");
        secondLibrary.printAvailableBooks();
        System.out.println();

        // Return The Lords of the Rings to the first library
        System.out.println("Returning The Lord of the Rings:");
        firstLibrary.returnBook("The Lord of the Rings");
        System.out.println();

        // Print the titles of available from the first library
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
    }
}