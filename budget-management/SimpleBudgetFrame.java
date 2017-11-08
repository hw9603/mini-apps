package eecs285.proj4.hewen;

import java.awt.event.ActionListener;
import javax.swing.*;
import javafx.event.ActionEvent;

public class SimpleBudgetFrame extends JFrame
{
  String filename;
  static JMenuItem saveItem;
  static JMenuItem exitItem;
  static JComboBox<String> catCombo;
  static JTextField merchantField;
  static JTextField amountField;
  static JLabel balance;
  static JButton transaction = new JButton("Add Transaction");
  static ClientServerSocket theClient;
  
  SimpleBudgetFrame(String _Title, String _filename)
  {
    super(_Title);
    filename = _filename;

    catCombo = new JComboBox<String>();
    final String SERVER_IP_ADDR = "127.0.0.1";
    final int NETWORK_PORT_NUM = 45000;
    theClient = new ClientServerSocket(SERVER_IP_ADDR, NETWORK_PORT_NUM);
    theClient.startClient();
    theClient.sendString(filename);
    int numOfCategory = theClient.recvInt();
    String [] cat = new String[numOfCategory];
    //String test = theClient.recvString();
    //System.out.println(test);
    for (int i = 0 ; i < numOfCategory ; i++)
    {
      cat[i] = theClient.recvString();
    }
    
    for (int i = 0 ; i < numOfCategory ; i++)
    {
      catCombo.addItem(cat[i]);
    }
    
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    saveItem = new JMenuItem("Save Budget");
    saveItem.addActionListener(new InterfaceListener());
    exitItem = new JMenuItem("Exit Program");
    exitItem.addActionListener(new InterfaceListener());
    fileMenu.add(saveItem);
    fileMenu.add(exitItem);
    menuBar.add(fileMenu);
    setJMenuBar(menuBar);
    
    setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
    Box vertBox = new Box(BoxLayout.Y_AXIS);
    Box row1Box = new Box(BoxLayout.X_AXIS);
    Box row2Box = new Box(BoxLayout.X_AXIS);
    Box row3Box = new Box(BoxLayout.X_AXIS);
    Box row4Box = new Box(BoxLayout.X_AXIS);
    Box row5Box = new Box(BoxLayout.X_AXIS);
    row1Box.add(new JLabel("Category: "));
    row2Box.add(new JLabel("Merchant: "));
    row3Box.add(new JLabel("Amount: $"));
    
    catCombo.addActionListener(new ComboListener());
    row1Box.add(catCombo);
    merchantField = new JTextField(30);
    row2Box.add(merchantField);
    amountField = new JTextField(30);
    row3Box.add(amountField);
    
    row4Box.add(new JLabel("Balance: $"));
    balance = new JLabel();
    balance.setText("N/A");
    transaction.addActionListener(new ButtonListener());
    row4Box.add(balance);
    row5Box.add(transaction);
    
    vertBox.add(row1Box);
    vertBox.add(row2Box);
    vertBox.add(row3Box);
    vertBox.add(row4Box);
    vertBox.add(row5Box);
    add(vertBox);
  }
}