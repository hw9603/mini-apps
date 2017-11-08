package eecs285.proj2.hewen;

import static eecs285.proj2.hewen.GameResultEnum.*;

public class QuarterbackClass extends ObjectClass {
  int playerIndex;
  double stopX;
  double stopY;
  static boolean hasThrown;
	
  public QuarterbackClass(
      final int inPlayerIndex,
      final double inStartX,
      final double inStartY,
      final double inStopX,
      final double inStopY,
      final double inSpeed
      )
  {
    super(inStartX, inStartY, inSpeed, "Q");
    playerIndex = inPlayerIndex;
    stopX = inStopX;
    stopY = inStopY;
    hasThrown = false;
  }

  public GameResultEnum performMove()
  {
    if (locationX != stopX || locationY != stopY)
    {
      performStep(startX, startY, stopX, stopY, speed);
      System.out.println("Quarterback moving");
    }
    else
    {
      if (hasThrown == false)
      {
        System.out.println("Quarterback throwing");
        hasThrown = true;
      }
      else
      {
        System.out.println("Quarterback does nothing");
      }
    }
    return ONGOING;
  }
}
