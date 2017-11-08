package eecs285.proj1.hewen;

import static java.lang.System.out;

public class LawnAccountDemo {
	public static void main(String [] args){
		CustomerClass custAndrew;
	    CustomerClass custJane;
	    CustomerClass custJim;

	    custAndrew = new CustomerClass("Morgan", "Andrew");
	    custAndrew.printCustomerInfo();
	    custJane = new CustomerClass("Smith", "Jane");
	    custJane.printCustomerInfo();
	    custJim = new CustomerClass("Wilson", "James");
	    custJim.printCustomerInfo();
	    
	    ///Lawn-related testing...
	    LawnAccountClass lawnAcct0;
	    LawnAccountClass lawnAcct1;
	    boolean needsCut;
	    boolean keepLooping;
	    
	    lawnAcct0 = new LawnAccountClass(custAndrew, 0.4, 3.75, 3.75);
	    lawnAcct0.printLawnAccountInfo();
	    lawnAcct1 = new LawnAccountClass(custJim, 0.9, 4, 5.25);
	    lawnAcct1.printLawnAccountInfo();

	    ///Lawn testing with time
	    int dayNumber;
	    
	    dayNumber = 0;
	    keepLooping = true;
	    while (keepLooping)
	    {
	      needsCut = lawnAcct0.advanceOneDay();
	      
	      if (needsCut)
	      {
	        keepLooping = false;
	      }
	      
	      dayNumber++;
	    }
	    
	    out.println("Looped until cut needed!");
	    lawnAcct0.printLawnAccountInfo();

	    out.println("Performing a cut now - day #" + dayNumber + "...");    
	    lawnAcct0.performACut();
	    lawnAcct0.printLawnAccountInfo();

	    //Test second acct
	    dayNumber = 0;
	    keepLooping = true;
	    while (keepLooping)
	    {
	      needsCut = lawnAcct1.advanceOneDay();
	      
	      if (needsCut)
	      {
	        keepLooping = false;
	      }
	      
	      dayNumber++;
	    }

	    out.println("Looped until cut needed!");
	    lawnAcct1.printLawnAccountInfo();

	    out.println("Performing a cut now - day #" + dayNumber + "...");    
	    lawnAcct1.performACut();
	    lawnAcct1.printLawnAccountInfo();
	    
	    //Plow account testing..
	    PlowAccountClass plowAcct0;
	    PlowAccountClass plowAcct1;
	    
	    plowAcct0 = new PlowAccountClass(custJim);
	    plowAcct1 = new PlowAccountClass(custAndrew);
	    
	    plowAcct0.printPlowAccountInfo();
	    plowAcct1.printPlowAccountInfo();

	    for (int i = 0; i < 10; i++)
	    {
	      plowAcct0.performAPlow();
	    }
	    
	    plowAcct1.performAPlow();
	    
	    plowAcct0.printPlowAccountInfo();
	    plowAcct1.printPlowAccountInfo();
	    
	    out.println("All done testing for now!");
	}
}
