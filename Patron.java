/**
 * I have received no outside help on this Programming Assignment #5.
 * 
 * @author Megan Jane Thompson
 * @version 04/03/2019
 */

/**
 * This class defines a Patron.
 */
public class Patron {
  private String name;
  private String email;
  private int idNumber;
  private double balance;

  /**
   * This method constructs and initializes all atributes of a Patron.
   * 
   * @param name     refers to the Patron's name.
   * @param email    refers to the Patron's emails.
   * @param idNumber refers to the Patrons's idNumber.
   * @param balance  refers to the Patron's account balance.
   */
  public Patron (String name, String email, int idNumber, double balance) {
    this.name = name;
    this.email = email;
    this.idNumber = idNumber;
    this.balance = balance;
  }
  
  /**
   * This method adjusts the Patron's account balance and returns the new amount as a double.
   * 
   * @param amount  refers to the current balance amount.
   * @return        returns updated balance amount as a double.
   */
  public double adjustBalance(double amount) {
    
    this.balance = this.balance + amount;
    return this.balance;
  }
  
  /**
   * This method checks to see if two Patrons have the same id number and returns true if it is a match.
   * 
   * @param other  refers to the other Patron.
   * @return       returns a boolean "true" if id numbers match and "false" is they do not match.
   */
  public boolean equals(Object other) {
    boolean result;
    result = false;
    
    if (other instanceof Patron) {  
      Patron b = (Patron) other;
      result = (this.idNumber == b.idNumber);
    }
    else if (other instanceof Integer) {
      Integer id = (Integer) other;
      result = (this.idNumber == id);
    }
                                         
    return result;
  }
  
  /**
   * This method converts the Patron's information into a String format.
   * 
   * @return     returns a String containing the Patron's information.
   */
  public String toString() {
    String str;
    str = "Name: " + this.name + ", Email: " + this.email + 
          ", ID: " + this.idNumber + ", Balance: $" + String.format("%.2f", this.balance) + ".";
    
    return str;
  }
}