import junit.framework.TestCase;
import java.util.Date;

/**
 * This class was provided by the instructor.
 * Tests Library: currently one test method.
 * 
 * @author Deeand Farzana
 * @version 12/03/2016
 *
 */
public class LibraryTest extends TestCase {

    /** A single test for Library. **/
    public void testLibrary() {

        String title = "Starting Out With Java";
        String author = "Tony Gaddis";
        String isbn = "978-0-13-395705-1";
        int year = 2016;
        int pages = 1188;
        
        String title2 = "Starting Out With Java Edition 4";
        String author2 = "T. Gaddis";
        String isbn2 = "978-0-13-608020-6";
        int year2 = 2008;
        int pages2 = 977;
        
        Book b0 = new Book(title2, author2, isbn2, year2, pages2);
        Book b1 = new Book(title, author, isbn, year, pages);
        Book b2 = new Book(title, author, isbn, year, pages); 
        
        Book[] deesBooks = {b0, b1, b2};
        
        Patron p0 = new Patron("Dee A. B. Weikle", "weikleda@jmu.edu", 
                                    2, 1.50);
        Patron p1 = new Patron("Chris Mayfield", "mayfiecs@jmu.edu", 
                                    1, 1.00);
        Patron p2 = new Patron("Alvin Chao", "chaoaj@jmu.edu", 
                                    0, 0);
                                    
        Patron[] deesPatrons = {p0, p1, p2};
        Library deesLibrary = new Library(deesBooks, deesPatrons);
        
        if (deesLibrary.checkout(deesBooks[1], deesPatrons[2])) {
            assertEquals("Library checkout1:", Book.UNAVAILABLE,
                          deesBooks[1].getStatus());
            assertTrue("Library checkout3:", 
                 deesPatrons[2].equals(deesBooks[1].getPatron()));
            assertEquals("Library checkout 4:", 10, 
                 DateUtils.interval(new Date(), deesBooks[1].getDue())); 
            
            if (deesLibrary.checkin(deesBooks[1])) {
                assertEquals("Library checkout5", Book.AVAILABLE,
                             deesBooks[1].getStatus());
                assertEquals("Library checkout6", null, 
                             deesBooks[1].getPatron());
                assertEquals("Library checkout7", null, deesBooks[1].getDue());
            
            }     
        } else {
            assertTrue("Library checkout failed", false);
        }
        
        // Test with book not in the library
        Book b3 = new Book("Title", "Author", "isbn", 2010, 220);
        assertFalse("Library no book checkout", deesLibrary.checkout(b3, p0));
        
        // Test determine fine, checkout book with overdue date
        Date today = new Date();
        Date tenDaysAgo = DateUtils.addDays(today, -10);
        b0.checkout(p0, tenDaysAgo);
        assertEquals("5.00 fine", 5.00, deesLibrary.determineFine(b0));
        
        // Test search books - something there
        Book[] actual = deesLibrary.searchBooks("Tony Gaddis", 
                                        Library.AUTHOR_SEARCH);
        assertEquals("Library author search 2 books:", 2, actual.length);
        assertTrue("Library author search 2 books, b1", 
                    actual[0] == b1 || actual[1] == b1);
        assertTrue("Library author search 2 books, b2", 
                    actual[0] == b2 || actual[1] == b2);
        
        Book[] actual2 = deesLibrary.searchBooks(title, Library.TITLE_SEARCH);
        assertEquals("Library title search 2 books:", 2, actual2.length);
        assertTrue("Library title search 2 books, b1", 
                    actual2[0] == b1 || actual2[1] == b1);
        assertTrue("Library title search 2 books, b2", 
                    actual2[0] == b2 || actual2[1] == b2);       
 
        //////////////////DID NOT PASS TESTS FOR THE FOLLOWING//////////////
        Book[] actual3 = deesLibrary.searchBooks(p0, Library.PATRON_SEARCH);
        assertEquals("Library patron search 2 books:", 1, actual3.length);
        assertTrue("Library patron search 2 books, b1", actual3[0] == b0);
        
        Book[] actual4 = deesLibrary.searchBooks(new Date(), 
                                         Library.OVERDUE_SEARCH);
        assertEquals("Library overdue search 2 books:", 1, actual4.length);
        assertTrue("Library overdue search 2 books, b1", actual4[0] == b0);
        ////////////////////////////////////////////////////////////////////                                        
        
        // Test search books nothing there
        Book[] actual5 = deesLibrary.searchBooks("Author", 
                                          Library.AUTHOR_SEARCH);
        assertEquals("Library author search not there:", 0, actual5.length);
        
        Book[] actual6 = deesLibrary.searchBooks("Title", 
                                          Library.TITLE_SEARCH);
        assertEquals("Library title search not there:", 0, 
                     actual6.length);       
        
        //////////////////DID NOT PASS TESTS FOR THE FOLLOWING//////////////
        Book[] actual7 = deesLibrary.searchBooks(p2, 
                   Library.PATRON_SEARCH);
        assertEquals("Library patron search not there:", 0, 
                   actual7.length);
        ///////////////////////////////////////////////////////////////////  
                                                 
        Date d = new Date();        
        Book[] actual8 = deesLibrary.searchBooks(DateUtils.addDays(d, -20), 
                                                 Library.OVERDUE_SEARCH);
        assertEquals("Library overdue search not there:", 0, actual8.length);
        
       //////////////////DID NOT PASS TESTS FOR THE FOLLOWING//////////////                                          
        // Test patron search with int idnumber instead
       Book[] actual9 = deesLibrary.searchBooks(0, Library.PATRON_SEARCH);
       assertEquals("Library patron search idnum not there:", 0, 
                   actual9.length);
        
       Book[] actual10 = deesLibrary.searchBooks(2, Library.PATRON_SEARCH);
       assertEquals("Library patron search idnum b0", 1, actual10.length);
       assertTrue("Library patron search idnum b0", actual10[0] == b0);
       ///////////////////////////////////////////////////////////////////
          
    }
}
