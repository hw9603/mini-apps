package eecs285.proj3.hewen;

import java.io.File;
import java.io.FileFilter;
import static eecs285.proj3.hewen.WheelOfFortuneFrame.*;

public class WheelSpaceImageFilter implements FileFilter
{
    String prefix;  //The prefix of the filename we're looking
    //for - what comes before the first underscore

    WheelSpaceImageFilter(int inPref)
    {
      //Sets the prefix member to string version of space number
      prefix = new Integer(inPref).toString();
    }

    //Test whether the file provided should be accepted by our
    //file filter. In the FileFilter interface.
  public boolean accept(File fname)
  {
    boolean isAccepted = false;

    //Accepted if matched "<prefix>_<...>.jpg" where IMAGE_EXTENSION
    //is assumed to be "jpg" for this example
    if (fname.getName().startsWith(prefix + "_") &&
        fname.getName().endsWith("." + IMAGE_EXTENSION))
    {
      isAccepted = true;
    }

    return (isAccepted);
  }

  //Parses the provided filename to determine the dollar value
  //associated with this wheel space image's filename.
  public static int getSpaceValue(File fname)
  {
    int value = 1;
    int div1 = fname.getName().indexOf("_");
    int div2 = fname.getName().indexOf(".");
    if (fname.getName().substring(div1 + 1, div2).compareTo("loseATurn") == 0)
    {
      value = 0;
    }
    else if (fname.getName().substring(div1 + 1, div2).compareTo("bankrupt") == 0)
    {
      value = -1;
    }
    else
    {
      value = (int)Integer.parseInt(fname.getName().substring(div1 + 1, div2));
    }  
    return value;
  }
}
