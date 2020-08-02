import junit.framework.TestCase;
import java.util.Date;

/**
 * This class was provided by the instructor.
 * Tests Book: currently one test method.
 * 
 * @author Dee A. B. Weikle
 * @version 11/29/2016
 *
 */
public class BookTest extends TestCase {

    /** A single test for book. **/
    public void testBook() {
    
        String title = "Starting Out With Java";
        String author = "Tony Gaddis";
        String isbn = "978-0-13-395705-1";
        int year = 2016;
        int pages = 1188;
        
        //Test constructor and getters
        Book book1 = new Book(title, author, isbn, year, pages);
        assertEquals("Book: getTitle check ", book1.getTitle(), title);
        assertEquals("Book: getAuthor check ", book1.getAuthor(), author);
        assertEquals("Book: getIsbn check ", book1.getIsbn(), 
                                    isbn);
        assertEquals("Book: getYear check ", book1.getYear(), 
                                    year);
        assertEquals("Book: getPages check ", book1.getPages(), 
                                    pages);
        assertEquals("Book: getStatus check ", book1.getStatus(), 
                                               book1.AVAILABLE);
        assertEquals("Book: getDue ", book1.getDue(), null);
        assertEquals("Book: getPatron ", book1.getPatron(), null);
        
        String title2 = "Starting Out With Java Edition 4";
        String author2 = "T. Gaddis";
        String isbn2 = "978-0-13-608020-6";
        int year2 = 2008;
        int pages2 = 977;
        
        // Test equals
        Book book2 = new Book(title2, author2, isbn2, year2, pages2);
        Book book3 = new Book(title, author, isbn, year, pages);
        assertFalse("Book: book1 != book2", book1.equals(book2));
        assertTrue("Book: book1 == book3", book1.equals(book3));
        assertFalse("Book: string not equal test", 
                     book1.equals(book2.getIsbn()));
        assertTrue("Book: string equal test", book1.equals(book3.getIsbn()));
        
        
        // Test toString
        assertEquals("Book: toString", book1.toString(),
                     "Title: Starting Out With Java, Author: Tony Gaddis, "
                     + "ISBN: 978-0-13-395705-1, Year: 2016, Pages: 1188.");
                     
        // Test checkout
        Patron aPatron = new Patron("Dee A. B. Weikle", "weikleda@jmu.edu", 
                                    2, 1.50);
        Patron ePatron = new Patron("Dee A. B. Weikle", "weikleda@jmu.edu", 
                                    2, 1.50);
        Date dueDate = new Date();
        book1.checkout(ePatron, dueDate);
        assertTrue("Book: checkout", ePatron.equals(book1.getPatron()));
        
        assertEquals("Book: checkout date", book1.getDue(), dueDate);
        assertEquals("Book: checkout status", book1.getStatus(), 
                      book1.UNAVAILABLE);
        
        // Test checkin
        book1.checkin();
        assertEquals("Book: checkin", book1.getPatron(), null);
        
        assertEquals("Book: checkout date", book1.getDue(), null);
        assertEquals("Book: checkout status", book1.getStatus(), 
                     book1.AVAILABLE);       
        
    }
}
