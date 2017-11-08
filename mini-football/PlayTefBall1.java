package eecs285.proj2.hewen;

import static java.lang.System.out;

/** This class provides a main method for playing a game of Tefball
 *  ("tef" = "t"wo "e"ighty "f"ive).
 *  @author Andrew M. Morgan
 *  @date 9/24/2012
 */
/*public class PlayTefBall1
{
  public static void main(String args[])
  {
    PlayingFieldClass theField; //The game field, which I will interface with
    GameResultEnum gameResult;  //The outcome of the game
    int playerInd;     //These local variables are just to make the
    double startX;     //code easier to read so that the order and
    double startY;     //meaning of the arguments to the "add..."
    double stopX;      //functions are easy to interpret.
    double stopY;
    double throwToX;
    double throwToY;
    double speed;
    double throwSpeed;
    double intermedX;
    double intermedY;

    int numPlayers;
    int fieldWidth;
    int fieldHeight;

    //Print the welcome message and instantiate a new field
    out.println("Welcome to Tefball - enjoy the game");
    numPlayers = 4;
    fieldWidth = 16;
    fieldHeight = 12;
    theField = new PlayingFieldClass(numPlayers, fieldWidth, fieldHeight);

    //Now request the creation of some players on the field.
    playerInd = 0;
    startX = 8;
    startY = 4;
    stopX = 8;
    stopY = 1;
    throwToX = 10;
    throwToY = 8;
    speed = 1;
    throwSpeed = 3;
    theField.addQuarterback(playerInd,
                            startX, startY,
                            stopX, stopY,
                            throwToX, throwToY,
                            speed, throwSpeed);

    
    playerInd = 1;
    startX = 4;
    startY = 4;
    intermedX = 2;
    intermedY = 6;
    stopX = 4;
    stopY = 8;
    speed = 1.4143;
    theField.addReceiver(playerInd,
                         startX, startY,
                         intermedX, intermedY,
                         stopX, stopY,
                         speed);

    playerInd = 2;
    startX = 6;
    startY = 5;
    //speed = 1.4143;
    speed = 0.708;
    theField.addDefender(playerInd,
                         startX, startY,
                         speed);

    playerInd = 3;
    startX = 12;
    startY = 4;
    intermedX = 11;
    intermedY = 6;
    stopX = 10;
    stopY = 8;
    speed = 1;
    theField.addReceiver(playerInd,
                         startX, startY,
                         intermedX, intermedY,
                         stopX, stopY,
                         speed);

    //Finally, run the game, as long as the set up was all done
    //properly, otherwise output an error message.
    if (theField.checkIsValidGame())
    {
      out.println("Game is properly set up - let's play!");
      gameResult = theField.playBall();
      
  
      out.println("Game ends with: " + gameResult);
    }
    else
    {
      out.println("The game has not been set up properly!");
    }
  }
}*/

public class PlayTefBall1
{
  public static void main(String args[])
  {
    PlayingFieldClass theField; //The game field, which I will interface with
    GameResultEnum gameResult;  //The outcome of the game
    int playerInd;     //These local variables are just to make the
    double startX;     //code easier to read so that the order and
    double startY;     //meaning of the arguments to the "add..."
    double stopX;      //functions are easy to interpret.
    double stopY;
    double throwToX;
    double throwToY;
    double speed;
    double throwSpeed;
    double intermedX;
    double intermedY;

    int numPlayers;
    int fieldWidth;
    int fieldHeight;

    //Print the welcome message and instantiate a new field
    out.println("Welcome to Tefball - enjoy the game");
    numPlayers = 4;
    fieldWidth = 16;
    fieldHeight = 12;
    theField = new PlayingFieldClass(numPlayers, fieldWidth, fieldHeight);

    //Now request the creation of some players on the field.
    playerInd = 0;
    startX = 8;
    startY = 4;
    stopX = 8;
    stopY = 1;
    throwToX = 10;
    throwToY = 8;
    speed = 1;
    throwSpeed = 3;
    theField.addQuarterback(playerInd,
                            startX, startY,
                            stopX, stopY,
                            throwToX, throwToY,
                            speed, throwSpeed);

    
    playerInd = 1;
    startX = 4;
    startY = 4;
    intermedX = 2;
    intermedY = 6;
    stopX = 4;
    stopY = 8;
    speed = 1.4143;
    theField.addReceiver(playerInd,
                         startX, startY,
                         intermedX, intermedY,
                         stopX, stopY,
                         speed);

    playerInd = 2;
    startX = 6;
    startY = 5;
    //speed = 1.4143;
    speed = 0.708;
    theField.addDefender(playerInd,
                         startX, startY,
                         speed);

    playerInd = 3;
    startX = 12;
    startY = 4;
    intermedX = 11;
    intermedY = 6;
    stopX = 10;
    stopY = 8;
    speed = 1;
    theField.addReceiver(playerInd,
                         startX, startY,
                         intermedX, intermedY,
                         stopX, stopY,
                         speed);

    //Finally, run the game, as long as the set up was all done
    //properly, otherwise output an error message.
    if (theField.checkIsValidGame())
    {
      out.println("Game is properly set up - let's play!");
      gameResult = theField.playBall();
  
      out.println("Game ends with: " + gameResult);
    }
    else
    {
      out.println("The game has not been set up properly!");
    }
  }
}

