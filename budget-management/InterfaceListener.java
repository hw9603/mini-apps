package eecs285.proj4.hewen;

import java.awt.event.*;
import static eecs285.proj4.hewen.SimpleBudgetFrame.*;

public class InterfaceListener implements ActionListener
{
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource().equals(saveItem))
    {
      theClient.sendString("save");
    }
    else if (e.getSource().equals(exitItem))
    {
      System.exit(0);
    }
    
  }

}