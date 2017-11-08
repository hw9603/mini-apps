package eecs285.proj2.hewen;

import static eecs285.proj2.hewen.GameResultEnum.*;
public class ObjectClass {
  double startX;
  double startY;
  double speed;
  String type;
  double locationX;
  double locationY;
  
  public ObjectClass(
      final double inStartX,
      final double inStartY,
      final double inSpeed,
      final String inType
      )
  {
    startX = inStartX;
    startY = inStartY;
    speed = inSpeed;
    type = inType;
    locationX = startX;
    locationY = startY;
  }

  public void performStep(double startX, double startY, double stopX, 
                               double stopY, double speed)
  {
    double directionX = stopX - locationX;
    double directionY = stopY - locationY;
    double directionLength = Math.sqrt(directionX * directionX 
                                      + directionY * directionY);
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
    return ONGOING;
  }
}
