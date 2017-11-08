package eecs285.proj4.hewen;

import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;
import static eecs285.proj4.hewen.SimpleBudgetFrame.*;

public class ComboListener implements ActionListener
{
  public void actionPerformed(ActionEvent actEv)
  {
    double balanceDouble;
    theClient.sendString("combo");
    theClient.sendString((String)catCombo.getSelectedItem());
    balanceDouble = theClient.recvDouble();
    DecimalFormat df = new DecimalFormat("#######0.00");
    balance.setText("" + df.format(balanceDouble));
  }
}
