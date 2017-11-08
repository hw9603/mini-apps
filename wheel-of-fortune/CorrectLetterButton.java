package eecs285.proj3.hewen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.*;

public class CorrectLetterButton extends JButton
{
  String letter;
  boolean hasPressed;
  public CorrectLetterButton(String _letter)
  {
    super(_letter);
    letter = _letter;
    hasPressed = false;
    addActionListener(new ActionListener()
                     {
                       public void actionPerformed(ActionEvent e)
                       {
                         setEnabled(false);
                         hasPressed = true;
                         for (Integer intVar : puz.get(letter))
                         {
                           puzzleOutput[intVar * 2] = letter.charAt(0);
                         }
                         puzzleContent.setText(String.valueOf(puzzleOutput));
                         int i;
                         boolean flag = false;
                         for (i = 0 ; i < vows.length ; i++)
                         {
                           //System.out.println(vows[i].letter);
                           if (e.getSource() == vows[i])
                           {
                             flag = true;
                             break;
                           }
                         }
                         for (i = 0 ; i < correctVows.length ; i++)
                         {
                           //System.out.println(correctVows[i].letter);
                           if (e.getSource() == correctVows[i])
                           {
                             flag = true;
                             break;
                           }
                         }
                         if (flag == false)
                         {
                           player[currPlayer].money += wheelSpaces[spaceIndex].getValue();
                           player[currPlayer].judgeMoney();
                           player[currPlayer].hasConsonant = true;
                         }
                         money[currPlayer].setText(String.valueOf(player[currPlayer].money));
                         if (player[currPlayer].notEnoughMoney == false)
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
