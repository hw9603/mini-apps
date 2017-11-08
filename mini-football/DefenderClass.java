package eecs285.proj2.hewen;
import static eecs285.proj2.hewen.GameResultEnum.*;

public class DefenderClass extends ObjectClass{
  int playerIndex;
  public DefenderClass(
      final int inPlayerIndex,
      final double inStartX,
      final double inStartY,
      final double inSpeed
      )
  {
    super(inStartX, inStartY, inSpeed, "D");
    playerIndex = inPlayerIndex;
  }
	
  public GameResultEnum performMove()
  {
    if (QuarterbackClass.hasThrown == false)
    {
      performStep(locationX, locationY,BallClass.locationX,
    		  BallClass.locationY, speed);
    }
    else
    {
      performStep(locationX, locationY, BallClass.throwToX, 
                           BallClass.throwToY, speed);
    }
    System.out.println("Defender moving");
		
    if (BallClass.isThrown == false)
    {
      if (Math.rint(locationX) == Math.rint(BallClass.locationX)
                  && Math.rint(locationY) == Math.rint(BallClass.locationY))
      {
        return SACK;
      }
    }
			
    if (Math.rint(BallClass.locationX) == Math.rint(locationX)
                  && Math.rint(BallClass.locationY) == Math.rint(locationY))
    {
      return INTERCEPTION;
    }
    return ONGOING;
  }
}
