package eecs285.proj1.hewen;

///Programmer: Wen He
///Date: September 2016
///Purpose: This class is used to instantiate objects representing a
///         snow plowing acoount for one of our customers. 

public class PlowAccountClass {
  private CustomerClass customerObj;
  private int numPlowsPerformed;

  ///Value constructor to initialize the newly generated plow
  ///account's attributes accordingly.
  PlowAccountClass(
      CustomerClass inCustomerObj
      )
  {
    customerObj = inCustomerObj;
  }

  ///Simply prints the plow account information to console in a 
  ///nicely formatted way. This method prints the customer info,
  ///the total number of times we've plowed the account's snow.
  void printPlowAccountInfo(
      )
  {
    System.out.print("Customer - Last, First: ");
    System.out.print(customerObj.getLastName() + ", ");
    System.out.println(customerObj.getFirstName());
    System.out.println("  #Plows: " + numPlowsPerformed);
  }

  ///Updates the plow account to indicate that a plow has been
  ///performed.
  void performAPlow(
      )
  {
    numPlowsPerformed++;
  }
}
