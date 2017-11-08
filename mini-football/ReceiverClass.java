package eecs285.proj2.hewen;

import static eecs285.proj2.hewen.GameResultEnum.*;

public class ReceiverClass extends ObjectClass{
  int playerIndex;
  double intermediateX;
  double intermediateY;
  double stopX;
  double stopY;
  boolean reachIntermediate;
	
  public ReceiverClass(
      final int inPlayerIndex,
      final double inStartX,
      final double inStartY,
      final double inIntermediateX,
      final double inIntermediateY,
      final double inStopX,
      final double inStopY,
      final double inSpeed
      )
  {
    super(inStartX, inStartY, inSpeed, "R");
    playerIndex = inPlayerIndex;
    intermediateX = inIntermediateX;
    intermediateY = inIntermediateY;
    stopX = inStopX;
    stopY = inStopY;
    reachIntermediate = false;
  }
	
  public GameResultEnum performMove()
  {
    double tempLocationX = locationX;
    double tempLocationY = locationY;
    if (reachIntermediate == false)
    {
      performStep(startX, startY, intermediateX, intermediateY, speed);
      System.out.println("Receiver moving");
			
      if (locationX == intermediateX && locationY == intermediateY)
      {
        reachIntermediate = true;
      }
    }
    else
    {
      if (locationX != stopX || locationY != stopY)
      {
        performStep(intermediateX, intermediateY, stopX, stopY, speed);
        System.out.println("Receiver moving");
      }
      else
      {
        System.out.println("Receiver waiting at destination");
      }
    }
    boolean meet = ((tempLocationX - BallClass.locationX)
                           * (locationX - BallClass.locationX) <= 0)
                || ((tempLocationY - BallClass.locationY) 
                           * (locationY - BallClass.locationY) <= 0);
    if (meet == true)
    {
      return RECEPTION;
    }
    else
    {
      return ONGOING;
    }
  }
}
