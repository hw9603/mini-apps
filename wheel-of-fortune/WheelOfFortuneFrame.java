package eecs285.proj3.hewen;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URISyntaxException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.util.*;

import static eecs285.proj3.hewen.WheelOfFortuneFrame.NUM_WHEEL_SPACES;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.cons;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.correctCons;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.correctVows;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.currPlayer;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.mainFrame;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.money;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.nameArea;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.namePanel;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.nameTitle;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.numPlayers;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.player;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.puzzle;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.ranGen;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.spaceIndex;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.spin;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.vowel;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.vows;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.wheelSpaces;
import static java.lang.System.out;

public class WheelOfFortuneFrame extends JFrame
{
  static int BUYVOWEL = 250;
  static int NUM_WHEEL_SPACES = 24;
  static String IMAGE_EXTENSION ="jpg";
  static Random ranGen;
  static int spaceIndex;
  static WheelSpace [] wheelSpaces;
  int randomSeedVal;
  static int numPlayers;
  static int currPlayer;
  int NUMVOWELS = 5;
  int NUMCONSONANTS = 21;
  int correctNumVowels;
  int correctNumConsonants;
  static JFrame mainFrame;
  static NameDialog [] nameInput;
  static TitledBorder [] nameTitle;
  static JPanel [] namePanel;
  static JLabel [] money;
  static JPanel nameArea;
  
  JPanel centerPanel;
  static JButton vowel;
  static JButton spin;
  static JButton puzzle;
  JPanel optionArea;
  static JLabel image;
  
  JPanel letterArea;
  JPanel vowelArea;
  JPanel consonantArea;
  TitledBorder Vowels;
  TitledBorder Consonants;
  static LetterButton [] cons;
  static LetterButton [] vows;
  static CorrectLetterButton [] correctCons;
  static CorrectLetterButton [] correctVows;
  static JLabel puzzleContent;
  JPanel southPanel;
  JPanel bottomPanel;
  static HashMap< String,  ArrayList< Integer> > puz;
  static char [] puzzleOutput;
  static PlayerClass [] player;
  NameDialog guessPuzzle;
  static boolean needRepaint;
  public WheelOfFortuneFrame(int _randomSeedVal, int _numPlayers)
  {
    randomSeedVal = _randomSeedVal;
    numPlayers = _numPlayers;
    currPlayer = 0;
    needRepaint = false;
    player = new PlayerClass[numPlayers];
    for (int i = 0 ; i < numPlayers ; i++)
    {
      player[i] = new PlayerClass();
    }
    correctNumVowels = 0;
    correctNumConsonants = 0 ;
    guessPuzzle = new NameDialog(true, mainFrame, "Solve Puzzle");
    nameInput = new NameDialog[numPlayers + 1];
    mainFrame = new JFrame("Wheel Of Fortune");
    mainFrame.setLayout(new BorderLayout());
    
    for (int i = 0 ; i < numPlayers ; i++)
    {
      nameInput[i] = new NameDialog(nameInput[i + 1], "Player Name Input", i);
    }
    nameInput[numPlayers] = new NameDialog(mainFrame, "Puzzle Input");
    
    nameTitle = new TitledBorder[numPlayers];
    namePanel = new JPanel[numPlayers];
    money = new JLabel[numPlayers];
    nameArea = new JPanel(new GridLayout(1, numPlayers));
    for (int i = 0 ; i < numPlayers ; i++)
    {
      nameTitle[i] = BorderFactory.createTitledBorder(nameInput[i].getName());
      nameTitle[i].setTitleJustification(TitledBorder.LEFT);
      nameTitle[i].setTitleColor(Color.BLACK);
      namePanel[i] = new JPanel(new FlowLayout());
      money[i] = new JLabel("0");
      namePanel[i].setBorder(nameTitle[i]);
      namePanel[i].add(money[i]);
      nameArea.add(namePanel[i]);
    }
    nameTitle[currPlayer].setTitleColor(Color.RED);
    optionArea = new JPanel(new GridLayout(5, 1));
    vowel = new JButton("Buy A Vowel");
    vowel.setEnabled(false);
    
    spin = new JButton("Spin The Wheel");
    
    puzzle = new JButton("Solve The Puzzle");
    
    File [] fileList;
    File myDir = null;
    
    wheelSpaces = new WheelSpace[NUM_WHEEL_SPACES];
    try
    {
      myDir = new File(getClass().getClassLoader().getResource(
                                      "eecs285/proj3/hewen/images").toURI());
    }
    catch (URISyntaxException uriExcep)
    {
      System.out.println("Caught a URI syntax exception");
      System.exit(4); //Just bail for simplicity in this project
    }
    for (int i = 1; i <= NUM_WHEEL_SPACES; i++)
    {
      //Get a listing of files named appropriately for an image
      //for wheel space #i.  There should only be one, and this
      //will be checked below.
      fileList = myDir.listFiles(new WheelSpaceImageFilter(i));

      if (fileList.length == 1)
      {
        //System.out.println("Space: " + i + " img: " + fileList[0] +
        //        " val: " + WheelSpaceImageFilter.getSpaceValue(fileList[0]));
        //Index starts at 0, space numbers start at 1 -- hence the "- 1"
        wheelSpaces[i - 1] = new WheelSpace(
                              WheelSpaceImageFilter.getSpaceValue(fileList[0]),
                              new ImageIcon(fileList[0].toString()));
      }
      else
      {
        System.out.println("ERROR: Invalid number of images for space: " + i);
        System.out.println("       Expected 1, but found " + fileList.length);
      }
    }
    
    optionArea.add(vowel);
    optionArea.add(new JLabel(" "));
    optionArea.add(spin);
    optionArea.add(new JLabel(" "));
    optionArea.add(puzzle);
    image = new JLabel(new ImageIcon("eecs285/proj3/hewen/images/1_loseATurn.jpg"));
    ranGen = new Random(randomSeedVal);
    
    centerPanel = new JPanel(new FlowLayout());
    centerPanel.add(optionArea);
    centerPanel.add(image);
    
    puz = new HashMap< String, ArrayList< Integer > > ();
    char[] content = nameInput[numPlayers].getName().toCharArray();
    puzzleOutput = new char[2 * content.length];
    HashSet< Character > correctVowelSet = new HashSet< Character > ();
    HashSet< Character > correctConsonantSet = new HashSet< Character > ();
    for (int i = 0 ; i < 2 * content.length ; i += 2)
    {
      if (Character.isLetter(content[i / 2]))
      {
        puzzleOutput[i] = '-';
        puzzleOutput[i + 1] = ' ';
        content[i / 2] = Character.toUpperCase(content[i / 2]);
        if(puz.containsKey(String.valueOf(content[i / 2])))
        {
          ArrayList< Integer > temp = puz.get(String.valueOf(content[i / 2]));
          temp.add(i / 2);
          puz.put(String.valueOf(content[i / 2]), temp);
        }
        else
        {
          ArrayList< Integer > temp = new ArrayList< Integer >();
          temp.add(i / 2);
          puz.put(String.valueOf(content[i / 2]), temp);
        }
        if (content[i / 2] == 'A' || content[i / 2] == 'E' || content[i / 2] == 'I' || content[i / 2] == 'O' || content[i / 2] == 'U')
        {
          correctVowelSet.add(content[i / 2]);
        }
        else
        {
          correctConsonantSet.add(content[i / 2]);
        }
      }
      else
      {
        puzzleOutput[i] = content[i / 2];
        puzzleOutput[i + 1] = ' ';
      }
    }
    
    /********************************************************/
    /******************                   *******************/
    /****************** Crazy thing begin *******************/
    /******************                   *******************/
    /********************************************************/
    Vowels = BorderFactory.createTitledBorder("Vowels");
    Consonants = BorderFactory.createTitledBorder("Consonants");
    correctVows = new CorrectLetterButton[correctVowelSet.size()];
    correctCons = new CorrectLetterButton[correctConsonantSet.size()];
    letterArea = new JPanel(new FlowLayout());
    vows = new LetterButton[NUMVOWELS - correctVowelSet.size()];
    vowelArea = new JPanel(new GridLayout(3, 2));
    vowelArea.setBorder(Vowels);
    
    int i = 0;
    for (Character charVar : correctVowelSet)
    {
      correctVows[i] = new CorrectLetterButton(String.valueOf(charVar));
      i++;
    }
    i = 0;
    for (Character charVar : correctConsonantSet)
    {
      correctCons[i] = new CorrectLetterButton(String.valueOf(charVar));
      i++;
    }
    
    int vowNum = 0;
    for (i = 0 ; i < correctVowelSet.size() ; i++)
    {
      if (correctVows[i].letter.compareTo("A") == 0)
      {
        vowelArea.add(correctVows[i]);
        break;
      }
    }
    if (i == correctVowelSet.size())
    {
      vows[vowNum] = new LetterButton("A");
      vowelArea.add(vows[vowNum]);
      vowNum++;
    }
    
    for (i = 0 ; i < correctVowelSet.size() ; i++)
    {
      if (correctVows[i].letter.compareTo("E") == 0)
      {
        vowelArea.add(correctVows[i]);
        break;
      }
    }
    if (i == correctVowelSet.size())
    {
      vows[vowNum] = new LetterButton("E");
      vowelArea.add(vows[vowNum]);
      vowNum++;
    }
    
    for (i = 0 ; i < correctVowelSet.size() ; i++)
    {
      if (correctVows[i].letter.compareTo("I") == 0)
      {
        vowelArea.add(correctVows[i]);
        break;
      }
    }
    if (i == correctVowelSet.size())
    {
      vows[vowNum] = new LetterButton("I");
      vowelArea.add(vows[vowNum]);
      vowNum++;
    }
    
    for (i = 0 ; i < correctVowelSet.size() ; i++)
    {
      if (correctVows[i].letter.compareTo("O") == 0)
      {
        vowelArea.add(correctVows[i]);
        break;
      }
    }
    if (i == correctVowelSet.size())
    {
      vows[vowNum] = new LetterButton("O");
      vowelArea.add(vows[vowNum]);
      vowNum++;
    }
    
    for (i = 0 ; i < correctVowelSet.size() ; i++)
    {
      if (correctVows[i].letter.compareTo("U") == 0)
      {
        vowelArea.add(correctVows[i]);
        break;
      }
    }
    if (i == correctVowelSet.size())
    {
      vows[vowNum] = new LetterButton("U");
      vowelArea.add(vows[vowNum]);
      vowNum++;
    }
    
    /********************************************************/
    /******************                   *******************/
    /******************     More Crazy    *******************/
    /******************                   *******************/
    /********************************************************/
    
    cons = new LetterButton[NUMCONSONANTS - correctConsonantSet.size()];
    consonantArea = new JPanel(new GridLayout(3, 7));
    consonantArea.setBorder(Consonants);
    int consNum = 0;
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("B") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("B");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("C") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("C");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("D") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("D");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("F") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("F");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("G") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("G");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("H") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("H");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("J") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("J");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("K") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("K");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("L") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("L");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("M") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("M");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("N") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("N");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("P") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("P");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("Q") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("Q");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("R") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("R");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("S") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("S");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("T") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("T");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("V") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("V");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("W") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("W");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("X") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("X");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("Y") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("Y");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    
    for (i = 0 ; i < correctConsonantSet.size() ; i++)
    {
      if (correctCons[i].letter.compareTo("Z") == 0)
      {
        consonantArea.add(correctCons[i]);
        break;
      }
    }
    if (i == correctConsonantSet.size())
    {
      cons[consNum] = new LetterButton("Z");
      consonantArea.add(cons[consNum]);
      consNum++;
    }
    letterArea.add(vowelArea);
    letterArea.add(consonantArea);
    
    puzzleContent = new JLabel(String.valueOf(puzzleOutput));
    
    vowel.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if (e.getSource() == vowel)
        {
          player[currPlayer].money -= BUYVOWEL;
          money[currPlayer].setText(String.valueOf(player[currPlayer].money));
          player[currPlayer].judgeMoney();
          vowel.setEnabled(false);
          spin.setEnabled(false);
          puzzle.setEnabled(false);
          for (int k = 0 ; k < correctVows.length ; k++)
          {
            if (correctVows[k].hasPressed == false)
            {
              correctVows[k].setEnabled(true);
            }
          }
          for (int k = 0 ; k < vows.length ; k++)
          {
            if (vows[k].hasPressed == false)
            {
              vows[k].setEnabled(true);
            }
          }
        }
      }
    });
    
    spin.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if (e.getSource() == spin)
        {
          vowel.setEnabled(false);
          spin.setEnabled(false);
          puzzle.setEnabled(false);
          spaceIndex = ranGen.nextInt(NUM_WHEEL_SPACES);
          wheelSpaces[spaceIndex].setImg();
          mainFrame.repaint();
          if (wheelSpaces[spaceIndex].getValue() == 0 || wheelSpaces[spaceIndex].getValue() == -1) 
            //if loseATurn or bankrupt
          {
            if (wheelSpaces[spaceIndex].getValue() == -1)
            {
              player[currPlayer].money = 0;
              player[currPlayer].hasConsonant = false;
              money[currPlayer].setText(String.valueOf(player[currPlayer].money));
            }
            nameTitle[currPlayer].setTitleColor(Color.BLACK);
            namePanel[currPlayer].setBorder(nameTitle[currPlayer]);
            mainFrame.repaint();
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
          else
          {
            for (int k = 0 ; k < correctCons.length ; k++)
            {
              if (correctCons[k].hasPressed == false)
              {
                correctCons[k].setEnabled(true);
              }
            }
            for (int k = 0 ; k < cons.length ; k++)
            {
              if (cons[k].hasPressed == false)
              {
                cons[k].setEnabled(true);
              }
            }
          }
        }
      }
    });
    
    puzzle.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if (e.getSource() == puzzle)
        {
          vowel.setEnabled(false);
          spin.setEnabled(false);
          puzzle.setEnabled(false);
          guessPuzzle.setVisible(true);
          mainFrame.repaint();
        }
      }
    });
    
    southPanel = new JPanel(new BorderLayout());
    bottomPanel = new JPanel(new FlowLayout());
    bottomPanel.add(puzzleContent);
    southPanel.add(letterArea, BorderLayout.NORTH);
    southPanel.add(bottomPanel, BorderLayout.SOUTH);
    
    mainFrame.add(nameArea, BorderLayout.NORTH);
    mainFrame.add(centerPanel, BorderLayout.CENTER);
    mainFrame.add(southPanel, BorderLayout.SOUTH);
    mainFrame.pack();
    mainFrame.setVisible(true);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
