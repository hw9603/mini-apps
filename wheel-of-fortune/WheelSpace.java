package eecs285.proj3.hewen;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import static eecs285.proj3.hewen.WheelOfFortuneFrame.*;

public class WheelSpace extends ImageIcon
{
  ImageIcon img;
  int spaceValue;
  public WheelSpace(int _spaceValue, ImageIcon _img)
  {
    img = _img;
    spaceValue = _spaceValue;
  }
  
  int getValue()
  {
    return spaceValue;
  }
  
  void setImg()
  {
    image.setIcon(img);
  }
}
