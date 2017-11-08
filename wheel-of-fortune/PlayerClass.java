package eecs285.proj3.hewen;

import static eecs285.proj3.hewen.WheelOfFortuneFrame.*;
public class PlayerClass
{
  boolean hasConsonant;
  int money;
  boolean notEnoughMoney;
  
  public PlayerClass()
  {
    hasConsonant = false;
    money = 0;
    notEnoughMoney = true;
  }
  
  void judgeMoney()
  {
    if (money < BUYVOWEL)
    {
      notEnoughMoney = true;
    }
    else
    {
      notEnoughMoney = false;
    }
  }
}
