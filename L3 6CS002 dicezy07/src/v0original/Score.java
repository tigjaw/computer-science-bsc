package v0original;
import java.text.*;
import java.util.*;

public class Score extends BasicScore implements Comparable<Score>  {
  public String name;
  public int score;
  public long date;

  public int compareTo(Score s) {
    return - (this.score - s.score);
  }

  public String toString() {
    StringBuffer b = new StringBuffer();
    
    b.append(String.format("%-5d", score));
    if(name.length()>20) {
      b.append(String.format("%-20s ", name.substring(0,20)));
    } else {
      b.append(String.format("%-20s ", name));
    }
    DateFormat f = DateFormat.getDateInstance(DateFormat.LONG);
    
    b.append(f.format(new Date(date)));
    return b.toString();
  }

  public int toInteger() {
    return (int) (date - score);
  }
}
