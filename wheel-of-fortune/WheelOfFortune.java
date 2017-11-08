//package eecs285.proj3.morgana;

//Programmer: Andrew M. Morgan
//Date: November 2016
//Purpose: Class containing the main function for a simplified
//         single round of the game "Wheel Of Fortune".  This
//         is being developed for a programming project in 
//         the EECS285 course.

import javax.swing.JFrame;

public class WheelOfFortune
{
  public static void main(String [] args)
  {
    //This will be the main frame that contains the game interface...
    WheelOfFortuneFrame gameFrame;
    
    //These can be changed as desired...
    int randomSeedVal = 100;
    int numPlayers = 3;
    
    gameFrame = new WheelOfFortuneFrame(randomSeedVal, numPlayers);
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameFrame.pack();
    
    //Show the game interface and start the game!
    gameFrame.setVisible(true);
  }
}
