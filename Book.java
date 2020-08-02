import java.util.Date;

/**
 * This class was provided by the instructor.
 * Represents a book in a library.
 * 
 * @author Farzana Rahman
 * @author Chris Mayfield
 * @version 11/28/2016
 */
public class Book {
    
    public static final int AVAILABLE = 1;
    
    public static final int UNAVAILABLE = 2;
    
    private String title;
    
    private String author;
    
    private String isbn;
    
    private int pages;
    
    private int year;
    
    private int status;
    
    private Patron patron;
    
    private Date due;
    
    /**
     * Constructs a book from the given values.
     * 
     * @param title the book's title
     * @param author the book's author (or authors)
     * @param isbn international standard book number
     * @param year the book's year of publication
     * @param pages number of pages in the book
     */
    public Book(String title, String author, String isbn, int year, int pages) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
        this.pages = pages;
        this.status = AVAILABLE;
        this.patron = null;
        this.due = null;
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }
    
    /**
     * @return the pages
     */
    public int getPages() {
        return pages;
    }
    
    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }
    
    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }
    
    /**
     * @return the due date
     */
    public Date getDue() {
        return due;
    }
    
    /**
     * @return the patron
     */
    public Patron getPatron() {
        return patron;
    }
    
    /**
     * Checks the book in.
     */
    public void checkin() {
        this.status = AVAILABLE;
        this.patron = null;
        this.due = null;
    }
    
    /**
     * Checks the book out.
     * 
     * @param patron the patron checking the book out
     * @param due when the book is due for return
     */
    public void checkout(Patron patron, Date due) {
        this.status = UNAVAILABLE;
        this.patron = patron;
        this.due = due;
    }
    
    /**
     * Compares this book to the specified object.
     * 
     * @param other the object to compare this book against
     * @return true if the books have the same isbn
     */
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Book) {
            Book book = (Book) other;
            result = this.isbn.equals(book.isbn);
        } else if (other instanceof String) {
            String str = (String) other;
            result = this.isbn.equals(str);
        }
        return result;
    }
    
    /**
     * @return the basic info of the Book as a String
     */
    public String toString() {
        String s = "Title: " + title + ", "
            + "Author: " + author + ", "
            + "ISBN: " + isbn + ", "
            + "Year: " + year + ", "
            + "Pages: " + pages + ".";
        return s;
    }
    
}
