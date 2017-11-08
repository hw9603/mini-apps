package eecs285.proj4.hewen;

import java.awt.event.*;
import static eecs285.proj4.hewen.SimpleBudgetFrame.*;

public class ButtonListener implements ActionListener
{
  public void actionPerformed(ActionEvent e)
  {
    theClient.sendString("button");
    theClient.sendDouble(Double.parseDouble(amountField.getText()));
    theClient.sendString(merchantField.getText());
    merchantField.setText(null);
    amountField.setText(null);
  }
}
