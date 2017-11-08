package eecs285.proj3.hewen;

import static eecs285.proj3.hewen.WheelOfFortuneFrame.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LetterButton extends JButton
{
  String letter;
  boolean hasPressed;
  public LetterButton(String _letter)
  {
    super(_letter);
    hasPressed = false;
    letter = _letter;
    addActionListener(new ActionListener()
                     {
                       public void actionPerformed(ActionEvent e)
                       {
                         nameTitle[currPlayer].setTitleColor(Color.BLACK);
                         namePanel[currPlayer].setBorder(nameTitle[currPlayer]);
                         mainFrame.repaint();
                         mainFrame.add(nameArea, BorderLayout.NORTH);
                         setEnabled(false);
                         hasPressed = true;
                         
                         if (currPlayer == numPlayers - 1)
                         {
                           currPlayer = 0;
                           nameTitle[currPlayer].setTitleColor(Color.RED);
                           namePanel[currPlayer].setBorder(nameTitle[currPlayer]);
                           mainFrame.repaint();
                         }
                         else
                         {
                           currPlayer++;
                           nameTitle[currPlayer].setTitleColor(Color.RED);
                           namePanel[currPlayer].setBorder(nameTitle[currPlayer]);
                           mainFrame.repaint();
                         }
                         if (player[currPlayer].hasConsonant == true)
                         {
                           vowel.setEnabled(true);
                         }
                         else
                         {
                           vowel.setEnabled(false);
                         }
                         spin.setEnabled(true);
                         puzzle.setEnabled(true);
                         
                         for (int k = 0 ; k < correctCons.length ; k++)
                         {
                           correctCons[k].setEnabled(false);
                         }
                         for (int k = 0 ; k < cons.length ; k++)
                         {
                           cons[k].setEnabled(false);
                         }
                         
                         for (int k = 0 ; k < correctVows.length ; k++)
                         {
                           correctVows[k].setEnabled(false);
                         }
                         for (int k = 0 ; k < vows.length ; k++)
                         {
                           vows[k].setEnabled(false);
                         }
                       }
                     });
    setEnabled(false);
  }
}
