package eecs285.proj2.hewen;

import static eecs285.proj2.hewen.GameResultEnum.*;

public class PlayingFieldClass {
  int players;
  int width;
  int height;
  int numQuarter;
  int numReceiver;
  int numDefender;
  BallClass ball;
  ObjectClass [] Player;
  FieldClass playField;

  ///Value ctor that allows the user to create a new playing
  ///field of the specified width and height and the specified
  ///number of players to participate in the game.
  PlayingFieldClass(
      final int numPlayers,
      final int fieldWidth,
      final int fieldHeight
      )
  {
    players = numPlayers;
    width = fieldWidth;
    height = fieldHeight;
    numQuarter = 0;
    numReceiver = 0;
    numDefender = 0;
    Player = new ObjectClass[players];
    playField = new FieldClass(width, height);
  }
  
  ///Allows the user to add a quarterback to the field at the
  ///specified starting location. The quarterback will run to
  ///the specified stopping location at the specified speed, then
  ///throw to the ball at the specified throw speed to the specified
  ///location. The newly created player will move during the game
  ///based on the 0-based index it is placed at.
  void addQuarterback(
      final int playerIndex,
      final double startX,
      final double startY,
      final double stopX,
      final double stopY,
      final double throwToX,
      final double throwToY,
      final double speed,
      final double throwSpeed
      )
  {
    numQuarter++;
    Player[playerIndex] = new QuarterbackClass(playerIndex, startX, startY,
                                                  stopX, stopY, speed);
    ball = new BallClass(startX, startY, speed, 
                             throwSpeed, stopX, stopY, throwToX, throwToY);
  }
  
  ///Allows the user to add a receiver to the field at the
  ///specified starting location. The receiver will run to 
  ///the specified intermediate location as the specified speed,
  ///then continue on to the specified stopping location at the
  ///specified speed. The newly created player will move during
  ///the game based on the 0-based index it is placed at.
  void addReceiver(
      final int playerIndex,
      final double startX,
      final double startY,
      final double intermediateX,
      final double intermediateY,
      final double stopX,
      final double stopY,
      final double speed
      )
  {
    try
    {
      Player[playerIndex] = new ReceiverClass(playerIndex, startX, startY, 
                           intermediateX, intermediateY, stopX, stopY, speed);
      numReceiver++;
    }
    catch (java.lang.ArrayIndexOutOfBoundsException excep)
    {
      numReceiver = -1;
    }
  }
  
  ///Allows the user to add a defender to the field at the 
  ///specified starting location. The defender will first attempt
  ///to sack the quarterback by running toward the quarterback's
  ///current position at the specified speed until the quarterback
  ///throws the ball. Once the quarterback throws the ball, the
  ///defender runs at the specified speed to the location the ball
  ///was thrown to in an attempt to intercept it. The newly
  ///created player will move during the game based on the 0-based 
  ///index it is placed at.
  void addDefender(
      final int playerIndex,
      final double startX,
      final double startY,
      final double speed
      )
  {
    try
    {
       Player[playerIndex] = new DefenderClass(playerIndex, 
                                                startX, startY, speed);
       numDefender++;
    }
    catch (java.lang.ArrayIndexOutOfBoundsException excep)
    {
       numDefender = -1;
    }
  }
  
  ///Returns true if, and only if, the game is set up such that
  ///there is exactly one quarterback, and every player slot has
  ///been filled with a player.
  boolean checkIsValidGame()
  {
    if (numQuarter != 1) return false;
    if (numReceiver + numDefender + 1 != players) return false;
    return true;
  }
  
  ///Runs the simulation by drawing the current state of the field,
  ///then performing a turn and continuing this until finally the 
  ///game ends for some reason. A complete "turn" is: having the 
  ///ball perform its move first, then having each player perform 
  ///its move in order of their indices. The return type is one
  ///of 5 values: ONGOING, SACK, INTERCEPTION, RECEPTION, or 
  ///INCOMPLETION. Note: A "turn" may not complete - if an
  ///object's movement results in one of the game ending criteria,
  ///the turn ends immediately, without the remaining players moving.
  GameResultEnum playBall()
  {
    GameResultEnum result = ONGOING;
    while(true)
    {
      playField.setUpField(Player, players);
      playField.printField();
      result = ball.performMove();
      if (result != ONGOING)
      {
        return result;
      }
      for(int i = 0 ; i < players ; i++)
      {
        result = Player[i].performMove();
        if (result != ONGOING)
        {
          return result;
        }
      }
    }
  }
}
