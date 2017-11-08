package eecs285.proj4.hewen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.*;
public class SimpleBudgetServer
{
  public static void main(String args[])
  {
    ClientServerSocket theServer;
    final String SERVER_IP_ADDR = "127.0.0.1";
    final int NETWORK_PORT_NUM = 45000;
    theServer = new ClientServerSocket(SERVER_IP_ADDR, NETWORK_PORT_NUM);
    theServer.startServer();
    String filename = theServer.recvString();
    Scanner s;
    int numCategory = 0;
    
    HashMap<String, Double> originalBudgetMap = new HashMap<String, Double>();
    HashMap<String, Double> budgetMap = new HashMap<String, Double>();
    //HashMap<String, HashMap<String, Double>> transMap = new HashMap<String, HashMap<String, Double>>();
    LinkedList<String> trans = new LinkedList<String>();
    try
    {
      s = new Scanner(new File(SimpleBudgetServer.class.getClassLoader().getResource("eecs285/proj4/hewen/" + filename).toURI()));
      numCategory = Integer.parseInt(s.nextLine());
      theServer.sendInt(numCategory);
      // read the original budget
      for (int i = 0 ; i < numCategory ; i++)
      {
        String str = s.nextLine();
        double bgt;
        String cate = str.substring(0, str.indexOf(':'));
        theServer.sendString(cate);
        bgt = Double.parseDouble(str.substring(str.indexOf(':') + 1));
        originalBudgetMap.put(cate, bgt);
        budgetMap.put(cate, bgt);
      }
      // load transaction
      boolean flag = false;
      while (s.hasNextLine())
      {
        flag = true;
        String str = s.nextLine();
        //String cat = str.substring(0, str.indexOf(':'));
        //String rest = str.substring(str.indexOf(':')+1);
        trans.add(str);
      }
      // calculate the current budget
      for (Map.Entry<String, Double> entry : budgetMap.entrySet())
      {
        double currBgt = entry.getValue();
        for (String ent : trans)
        {
          if (flag == true)
          {
            String ss[] = new String[3];
            ss = ent.split(":");
            if (ss[0].equals(entry.getKey()))
            {
              currBgt -= Double.parseDouble(ss[2]);
            }
          }
        }
        budgetMap.replace(entry.getKey(), entry.getValue(), currBgt);
      }
    } 
    catch (FileNotFoundException e)
    {
      System.out.println("Caught a file not found exception");
      System.exit(4);
    } 
    catch (URISyntaxException e)
    {
      System.out.println("Caught a URI syntax exception");
      System.exit(5);
    }
    String category = null, merchant;
    double amount;
    while (true)
    {
      String signal = theServer.recvString();
      while(signal.equals("combo"))
      {
        category = theServer.recvString();
        
        for (Map.Entry<String, Double> entry : budgetMap.entrySet())
        {
          if (entry.getKey().equals(category))
          {
            theServer.sendDouble(entry.getValue());
          }
        }
        signal = theServer.recvString();
      }
      if (signal.equals("save"))
      {
        PrintStream outFile;
        try
        {
          outFile = new PrintStream(new File(SimpleBudgetServer.class.getClassLoader().getResource("eecs285/proj4/hewen/" + filename).toURI()));
          outFile.println("" + numCategory);
          // First print the original budget
          for (Map.Entry<String, Double> entry : originalBudgetMap.entrySet())
          {
            outFile.print(entry.getKey() + ":");
            outFile.println(entry.getValue());
          }
          // Then print the previous transaction
          for (String entry : trans)
          {
            outFile.println(entry);
          }
        } 
        catch (FileNotFoundException e)
        {
          System.out.println("Unable to write to the file");
        }
        catch (URISyntaxException e)
        {
          System.out.println("Caught an URI syntax exception when writing to the file");
        }
      }
      if (signal.equals("button"))
      {
        amount = theServer.recvDouble();
        merchant = theServer.recvString();
        trans.add(category+":"+merchant+":"+amount);
        for (HashMap.Entry<String, Double> entry : budgetMap.entrySet())
        {
          if (entry.getKey().equals(category))
          {
            double currBal = entry.getValue() - amount;
            budgetMap.replace(category, entry.getValue(), currBal);
          }
        }
      }
    }
  }
}
