import junit.framework.TestCase;

/**
 * This class was provided by the instructor.
 * Tests Patron: currently one test method.
 * 
 * @author sFarzana and Ralph
 * @version 12/03/2016
 *
 */
public class PatronTest extends TestCase {

    /** A single test for Patron. **/
    public void testPatron() {
    
        Patron aPatron1 = new Patron("Dee A. B. Weikle", "weikleda@jmu.edu", 
                                    2, 1.50);
        assertEquals("Patron: toString", "Name: Dee A. B. Weikle,"
                     + " Email: weikleda@jmu.edu, ID: 2, Balance: $1.50.", 
                     aPatron1.toString());
        Patron aPatron2 = new Patron("Dee A. B. Weikle", "weikleda@jmu.edu", 
                                    2, 1.50);
        assertTrue("Patron: equals", aPatron1.equals(aPatron2));
        
        aPatron1.adjustBalance(2.50);
        assertEquals("Patron: toString", "Name: Dee A. B. Weikle,"
                     + " Email: weikleda@jmu.edu, ID: 2, Balance: $4.00.", 
                     aPatron1.toString());        

    }
}
