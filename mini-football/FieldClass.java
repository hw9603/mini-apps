package eecs285.proj2.hewen;

public class FieldClass {
  public static int width;
  public static int height;
  public static String[][] field;
  
  public FieldClass(
      final int inWidth,
      final int inHeight
      )
  {
    width = inWidth;
    height = inHeight;
    field = new String[width + 1][height + 1];
  }
  
  public void setUpField(ObjectClass[] Player, int players)
  {
	for (int j = 0 ; j < height + 1 ; j++)
    {
      for (int i = 0 ; i < width + 1 ; i++)
      {
        field[i][j] = "-";
      }
    }
    field[0][0] = " ";
    for (int i = 0 ; i < width ; i++)
    {
      field[i + 1][0] = (i + 10) % 10 + "";
    }
    for (int i = 0 ; i < height ; i++)
    {
      field[0][i + 1] = (i + 10) % 10 + "";
      for (int j = 0 ; j < width ; j++)
      {
       if (Math.rint(BallClass.locationX) == j
               && Math.rint(BallClass.locationY) == i)
        {
          field[j + 1][i + 1] = "B";
        }
        for (int k = players - 1 ; k >= 0 ; k--)
        {
          if (Math.rint(Player[k].locationX) == j
                 && Math.rint(Player[k].locationY) == i)
          {
            field[j + 1][i + 1] = Player[k].type;
          }
        }
      }
    }
  }
  
  public void printField()
  {
    for (int j = 0 ; j < height + 1 ; j++)
    {
      for (int i = 0 ; i < width + 1 ; i++)
      {
        System.out.print(field[i][j]);
      }
      System.out.println();
    }
  }
}
