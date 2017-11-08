package eecs285.proj2.hewen;

import static eecs285.proj2.hewen.GameResultEnum.*;

public class BallClass{
  static double startX;
  static double startY;
  static double speed;
  static double throwSpeed;
  static double throwFromX;
  static double throwFromY;
  static double throwToX;
  static double throwToY;
  static boolean isThrown;
  static double locationX;
  static double locationY;
  static String type;
	
  public BallClass(
      final double inStartX,
      final double inStartY,
      final double inSpeed,
      final double inThrowSpeed,
      final double inThrowFromX,
      final double inThrowFromY,
      final double inThrowToX,
      final double inThrowToY
      )
  {
    startX = inStartX;
    startY = inStartY;
    speed = inSpeed;
    throwSpeed = inThrowSpeed;
    throwFromX = inThrowFromX;
    throwFromY = inThrowFromY;
    throwToX = inThrowToX;
    throwToY= inThrowToY;
    isThrown = false;
    locationX = startX;
    locationY = startY;
    type = "B";
  }
	
  public void performStep(double startX, double startY, double stopX,
			double stopY, double speed)
  {
    double directionX = stopX - locationX;
    double directionY = stopY - locationY;
    double directionLength = Math.sqrt(Math.pow(directionX, 2) 
                                        + Math.pow(directionY,2));
    double directionUnitX = directionX / directionLength;
    double directionUnitY = directionY / directionLength;
    double stepTurnX = directionUnitX * speed;
    double stepTurnY = directionUnitY * speed;
    boolean isOverstep 
            = ((locationX - stopX) * (locationX + stepTurnX - stopX) < 0)
                || ((locationY - stopY) * (locationY + stepTurnY - stopY) < 0);
    if (isOverstep == true)
    {
      locationX = stopX;
      locationY = stopY;
    }
    else
    {
      locationX += stepTurnX;
      locationY += stepTurnY;
    }
  }
	
  public GameResultEnum performMove()
  {
	double tempLocationX = locationX;
	double tempLocationY = locationY;
    if (QuarterbackClass.hasThrown == false)
    {
      if (locationX != throwFromX || locationY != throwFromY)
      {
        performStep(startX, startY, throwFromX, throwFromY, speed);
      }
      System.out.println("Ball is held by quarterback");
    }
    else
    {
      isThrown = true;
      if (locationX != throwToX || locationY != throwToY)
      {
        performStep(throwFromX, throwFromY, throwToX, throwToY, throwSpeed);
        System.out.println("Ball is moving");
      }
    }
    while (tempLocationX != locationX || tempLocationY != locationY)
    {
      double directionX = locationX - tempLocationX;
      double directionY = locationY - tempLocationY;
      double directionLength = Math.sqrt(Math.pow(directionX, 2) 
                                          + Math.pow(directionY,2));
      double directionUnitX = directionX / directionLength;
      double directionUnitY = directionY / directionLength;
      double stepTurnX = directionUnitX;
      double stepTurnY = directionUnitY;
      boolean isOverstep 
  = ((tempLocationX - locationX) * (tempLocationX + stepTurnX - locationX) < 0) 
|| ((tempLocationY - locationY) * (tempLocationY + stepTurnY - locationY) < 0);
      if (isOverstep == true)
      {
        tempLocationX = locationX;
        tempLocationY = locationY;
      }
      else
      {
        tempLocationX += stepTurnX;
        tempLocationY += stepTurnY;
      }
      if (FieldClass.field[(int)Math.round(tempLocationX) + 1]
                    [(int)Math.round(tempLocationY) + 1] == "R")
      {
        return RECEPTION;
      }
      if (FieldClass.field[(int)Math.round(tempLocationX) + 1]
                    [(int)Math.round(tempLocationY) + 1] == "D")
      {
        if (isThrown == false)
        {
          return SACK;
        }
        else
        {
          return INTERCEPTION;
        }
      }
    }
    if (BallClass.locationX == BallClass.throwToX
            && BallClass.locationY == BallClass.throwToY)
    {
      return INCOMPLETION;
    }
    return ONGOING;
  }
}
