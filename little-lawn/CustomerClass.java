package eecs285.proj1.hewen;

///Programmer: Wen He
///Date: September 2016
///Purpose: This class is used to instantiate objects representing our
///         customers. Each customer objects will describe one customer, 
///         but we can create multiple customer objects in order to 
///         maintain records for multiple customers.

public class CustomerClass
{
  private String lastName;
  private String firstName;
  
  ///Value constructor that simply assigns the newly created
  ///customer's attributes to the corresponding values provided.
  CustomerClass(
      String inLastName,
      String inFirstName
      )
  {
    lastName = inLastName;
    firstName = inFirstName;
  }

  ///This method prints the cusomer's attributes in a formatted way.
  ///It will print the last name and first name of the customer.
  void printCustomerInfo(
      )
  {
    System.out.print("Customer - Last, First: ");
    System.out.println(lastName + ", " + firstName);
  }
  
  ///Simple getter function that returns the customer's first name
  ///attribute.
  String getFirstName(
      )
  {
    return(firstName);
  }

  ///Simple getter function that returns the customer's last name
  ///attribute. 
  String getLastName(
      )
  {
    return(lastName);
  }
}
