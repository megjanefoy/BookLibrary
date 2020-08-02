import java.util.Date;
import java.lang.String;

/**
 * I have received no outside help on this Programming Assignment #5.
 * 
 * @author Megan Jane Thompson
 * @version 03/28/2019
 */

/**
 * This class constructs a Library containig books and patrons.
 */
public class Library {
  
  public static final char AUTHOR_SEARCH = 'A';
  public static final char OVERDUE_SEARCH = 'O';
  public static final char PATRON_SEARCH = 'P';
  public static final char TITLE_SEARCH = 'T';
  public static Book[] books = new Book[0];
  public static Patron[] patrons = new Patron[0];

  
  /**
   * This method initializes an array of Book and Patron objects.
   * 
   * @param books    refers to an array of books within the Library system.
   * @param patrons  refers to an array of patrons within the Library system.
   */
  public Library (Book[] books, Patron[] patrons){
    Library.books = new Book[books.length];
    Library.patrons = new Patron[patrons.length];
    
    for (int i = 0; i < books.length; ++i) {
      Library.books[i] = books[i];
    }
    for (int i = 0; i < patrons.length; ++i) {
      Library.patrons[i] = patrons[i];
    }
  }

  
  /**
   * This method checks in a book if the book belongs to the Library and is currently 
   * checked out. It will update the status of the book, reset its patron and due date, 
   * determine the fine (if needed), and update the patron's balance. 
   * 
   * @param book         refers to the array of books within the Library system.
   * @return             returns the status as true for success and false for failure 
   *                     (i.e book is not in Library).
   */
  public boolean checkin(Book book) {
    Patron person = book.getPatron();
    boolean checkinTest;
    double fine;
    checkinTest = false;
    fine = 0.0;
    
    if (book != null) {
      for (int i = 0; i < Library.books.length; ++i) {
        if ((Library.books[i].equals(book)) && (book.getStatus() == 2)) {
          book.checkin();
          fine = determineFine(book);
          person.adjustBalance(fine);
          checkinTest = true;
        }
      }
    }
    return checkinTest;
  }

  
  /**
   * This method will checkout a book if it belongs to the Library and is available. 
   * It will update the status and patron, and set the due date to 10 days from today's date.
   * 
   * @param book          refers to the array of books within the Library system.
   * @param patron        refers to the array of patrons within the Library system.
   * @return              returns the status as true for success and false for failure 
   *                      (i.e book is not in Library).
   */
  public boolean checkout(Book book, Patron patron) {
    Date due = new Date();
    boolean checkoutTest;
    due = DateUtils.addDays(due, 10);
    checkoutTest = false;
    
    for (int i = 0; i < Library.books.length; ++i) {
      if ((Library.books[i].equals(book)) && (book.getStatus() == 1)) {
        book.checkout(patron, due);
        checkoutTest = true;
      }
    }
    return checkoutTest;
  }
  
  
  /**
   * This method will compute the fine currently due for a book that is checked out. 
   * The fine being $0.50 per day for each day after the due date.
   * 
   * @param book  refers to the array of books within the Library system.
   * @returns     returns the calculated fine amount as a double.
   */
  public double determineFine(Book book) {
    Date date = new Date();
    double fine;
    fine = 0.0;
    
    if (book.getDue() != null) {
      if (date.after(book.getDue())) {
        fine = 0.5 * DateUtils.interval(book.getDue(), date);
      }
    }
    return fine;
  }
  
  
  /**
   * This method searches for books, depending on the key and type, and returns an array 
   * of books that matched the seach. If no books are found, the array have a length of zero.
   * 
   * @param key       refers to the key entered to compare within the search.
   * @param type      refers to the search type entry.
   * @return          returns an array of books that matched within the search.
   */
  public Book[] searchBooks(Object key, char type) {
    Book[] search = new Book[0];
    Date date = null;
    Patron patron = null;
    Patron patron2 = null;
    String title;
    String author;
    int dateDiff;
    int c;
    int hits;
    dateDiff = 0;
    c = 0;
    hits = 0;
    
    if (type == 'T') {
      for (int i = 0; i < Library.books.length; ++i) {
        title = Library.books[i].getTitle();
        if ((title != null) && (key instanceof String)) {
          String str = (String) key;
          if (title.equals(str)) {
            hits = hits + 1;
          }
        }
      }
      if (hits > 0) {   
        search = new Book[hits];
        for (int i = 0; i < Library.books.length; ++i) {
          title = Library.books[i].getTitle();
          if ((title != null) && (key instanceof String)) {
            if ((title != null) && (title instanceof String)) {
              String str = (String) key;
              if (title.equals(str)) {
                search[c] = Library.books[i];
                ++c;
              }
            }
          }
        }
      }
    }
    
    else if (type == 'A') {
      for (int i = 0; i < Library.books.length; ++i) {
        author = Library.books[i].getAuthor();
        if ((author != null) && (key instanceof String)) {
          String str = (String) key;
          if (author.equals(str)) {
            hits = hits + 1;
          }
        }
      }
      if (hits > 0) {   
        search = new Book[hits];
        for (int i = 0; i < Library.books.length; ++i) {
          author = Library.books[i].getAuthor();
          if ((author != null) && (key instanceof String)) {
            String str = (String) key;
            if (author.equals(str)) {
              search[c] = Library.books[i];
              ++c;
            }
          }
        }
      }
    }
    
    else if (type == 'P') {
      for (int j = 0; j < Library.patrons.length; ++j) {  
        patron = Library.patrons[j];
        if ((patron != null) && (key != null)) {
          if (key instanceof Patron || key instanceof Integer) {
            if (patron.equals(key)) {
              for (int i = 0; i < Library.books.length; ++i) {
                if (Library.books[i].getStatus() == 2) {
                  patron2 = Library.books[i].getPatron();
                  if (patron.equals(patron2)) {
                    hits = hits + 1;
                  }
                }
              }
            }
          }
        }
      }
      if (hits > 0) {   
        search = new Book[hits];
        for (int j = 0; j < Library.patrons.length; ++j) {  
          patron = Library.patrons[j];
          if ((patron != null) && (key != null)) {
            if (key instanceof Patron || key instanceof Integer) {
              if (patron.equals(key)) {
                for (int i = 0; i < Library.books.length; ++i) {
                  if (Library.books[i].getStatus() == 2) {
                    patron2 = Library.books[i].getPatron();
                    if (patron2.equals(patron)) {
                      search[c] = Library.books[i];
                      ++c;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    
    else if (type == 'O') {
      for (int i = 0; i < Library.books.length; ++i) {
        date = Library.books[i].getDue();
        if (date != null && key != null) {
          if (key instanceof Date) {
            Date keyDate = (Date) key;
            dateDiff = DateUtils.interval(date, keyDate);                
            if (dateDiff >= 0) {
              hits = hits + 1;
            }
          }
        }       
      }
      if (hits > 0) {   
        search = new Book[hits];
        for (int i = 0; i < Library.books.length; ++i) {
          date = Library.books[i].getDue();
          if (date != null && key != null) {
            if (key instanceof Date) {
              Date keyDate = (Date) key;
              dateDiff = DateUtils.interval(date, keyDate);
              if (dateDiff >= 0) {
                search[c] = Library.books[i];
                ++c;
              }
            }
          }
        }
      }
    }
    return search;
  }
}