package eecs285.proj3.hewen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.*;

public class NameDialog extends JDialog {
  JTextField nameField;
  JButton okButton;
  public NameDialog(NameDialog nameDialog, String title, int _playerNum)
  {
    super(nameDialog, title, true);
    setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    setLayout(new BorderLayout());
    nameField = new JTextField(40);
    add(new JLabel("Enter name of player #" + _playerNum), BorderLayout.NORTH);
    add(nameField, BorderLayout.EAST);
    okButton = new JButton(" OK ");
    okButton.addActionListener(new ActionListener()
                              {
                                public void actionPerformed(ActionEvent e)
                                {
                                  if (!nameField.getText().isEmpty())
                                  {
                                    setVisible(false);
                                  }
                                }
                              });
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(okButton);
    add(buttonPanel, BorderLayout.SOUTH);
    pack();
    setVisible(true);
  }
  
  public NameDialog(JFrame mainFrame, String title)
  {
    super(mainFrame, title, true);
    setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    setLayout(new BorderLayout());
    nameField = new JTextField(40);
    add(new JLabel("Ask a non-player to enter a puzzle"), BorderLayout.NORTH);
    add(nameField, BorderLayout.EAST);
    okButton = new JButton(" OK ");
    okButton.addActionListener(new ActionListener()
                              {
                                public void actionPerformed(ActionEvent e)
                                {
                                  if (!nameField.getText().isEmpty())
                                  {
                                    setVisible(false);
                                    
                                  }
                                }
                              });
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(okButton);
    add(buttonPanel, BorderLayout.SOUTH);
    pack();
    setVisible(true);
  }
  
  public NameDialog(boolean isPuzzle, JFrame mainFrame, String title)
  {
    super(mainFrame, title, true);
    setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    setLayout(new BorderLayout());
    nameField = new JTextField(40);
    add(new JLabel("Enter complete puzzle exactly as displayed"), BorderLayout.NORTH);
    add(nameField, BorderLayout.EAST);
    okButton = new JButton(" OK ");
    okButton.addActionListener(new ActionListener()
                              {
                                public void actionPerformed(ActionEvent e)
                                {
                                  if (nameField.getText().isEmpty())
                                  {
                                    okButton.setEnabled(false);
                                  }
                                  if (nameField.getText().compareTo(WheelOfFortuneFrame.nameInput[WheelOfFortuneFrame.numPlayers].getName()) == 0)
                                  {
                                    setVisible(false);
                                    JOptionPane.showMessageDialog(WheelOfFortuneFrame.mainFrame, 
                                        nameInput[currPlayer].getName() + " wins " + player[currPlayer].money + "!", 
                                        "Game Over", 
                                        JOptionPane.INFORMATION_MESSAGE);
                                    System.exit(0);
                                  }
                                  else
                                  {
                                    setVisible(false);
                                    JOptionPane.showMessageDialog(WheelOfFortuneFrame.mainFrame, 
                                        "Guess by " +  nameInput[currPlayer].getName() + " was incorrect", 
                                        "Wrong Answer", 
                                        JOptionPane.ERROR_MESSAGE);
                                    nameField.setText(null);;
                                    spin.setEnabled(true);
                                    puzzle.setEnabled(true);
                                    needRepaint = true;
                                  }
                                    nameTitle[currPlayer].setTitleColor(Color.BLACK);
                                    namePanel[currPlayer].setBorder(nameTitle[currPlayer]);
                                    if (currPlayer == numPlayers - 1)
                                    {
                                      currPlayer = 0;
                                      nameTitle[currPlayer].setTitleColor(Color.RED);
                                      namePanel[currPlayer].setBorder(nameTitle[currPlayer]);
                                    }
                                    else
                                    {
                                      currPlayer++;
                                      nameTitle[currPlayer].setTitleColor(Color.RED);
                                      namePanel[currPlayer].setBorder(nameTitle[currPlayer]);
                                    }
                                    
                                    if (player[currPlayer].hasConsonant == true)
                                    {
                                      vowel.setEnabled(true);
                                    }
                                    else
                                    {
                                      vowel.setEnabled(false);
                                    }
                                }
                              });
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(okButton);
    add(buttonPanel, BorderLayout.SOUTH);
    setVisible(false);
    pack();
  }
  
  public String getName()
  {
    return (nameField.getText());
  }
}
