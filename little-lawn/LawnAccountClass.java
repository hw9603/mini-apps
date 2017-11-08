package eecs285.proj1.hewen;

///Programmer: Wen He
///Date: September 2016
///Purpose: This class is used to instantiate objects representing a
///         lawn cutting account for one of the customers. It can 
///         also simulate the cutting process according to the customers'
///         specific preferences.

public class LawnAccountClass {
  private CustomerClass customerObj;
  private double sunLevel;
  private double preferredCutLength;
  private double currentLength;
  private int numCutsPerformed;

  ///Value constructor to initialize the newly generated lawn
  ///account's attributes accordingly.
  LawnAccountClass(
      CustomerClass inCustomerObj,
      double inSunLevel,
      double inPrefCutLength,
      double inCurrentLength
      )
  {
    customerObj = inCustomerObj;
    sunLevel = inSunLevel;
    preferredCutLength = inPrefCutLength;
    currentLength = inCurrentLength;
  }

  ///The method prints the customer info, including the total number of
  ///times we've cut the lawn, the sun level of the lawn, the current 
  ///and preferred cut lengths.
  void printLawnAccountInfo(
      )
  {
    System.out.print("Customer - Last, First: ");
    System.out.print(customerObj.getLastName());
    System.out.println(", " + customerObj.getFirstName());
    System.out.println("  #Cuts: " + numCutsPerformed);
    System.out.println("  Yard Sun Level: " + sunLevel);
    System.out.print("  Length Info - Current: " + currentLength);
    System.out.println(" Preferred Cut Length: " + preferredCutLength);
  }

  ///Advances time in the simulation by a single day. The lawn will grow 
  ///an appropriate amount and the function will return true if it needs
  ///to be cut at the end of the day's growth, or false if it does not.
  boolean advanceOneDay(
      )
  {
    currentLength = currentLength + sunLevel * 0.215;
    if (currentLength - preferredCutLength >= 1.5)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }

  ///Advances time by the specified number of days. Returns true if the
  ///lawn needs to be cut at the end of that number of days, or false if
  ///it does not. Note that advancing multiple days may cause the lawn to
  ///grow beyond the length at which it would normally get cut - this 
  ///function will NOT cut the lawn or anything like that - it just advances
  ///time, and if the lawn grows long, so be it...
  boolean advanceNDays(
      int numDaysToAdvance  ///Number of days to be advanced
      )
  {
    currentLength = currentLength + numDaysToAdvance * sunLevel * 0.215;
    if (currentLength - preferredCutLength >= 1.5)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }

  ///This function returns true if the lawn's current length is sufficient
  ///to require a cut by our service, or false if it does not.
  boolean checkIfNeedsToBeCut(
      )
  {
    if (currentLength - preferredCutLength >= 1.5)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }

  ///Cut the lawn down to the customer's preferred cut length.
  void performACut(
      )
  {
    System.out.print("Cutting " + customerObj.getFirstName());
    System.out.print(" " + customerObj.getLastName());
    System.out.print("'s lawn from " + currentLength + " to ");
    currentLength = preferredCutLength;
    numCutsPerformed++;
    System.out.println(currentLength);
  }
}
