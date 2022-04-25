package v0original;
import java.net.*;
import java.io.*;

/**
 * The most important class in the application - it manages high scores. It does
 * this by talking to the central Score Daemon.
 * 
 * @author Kevan Buckley
 * @version 1.q, January 1996
 * 
 */
public class ScoreDaemonInterpetter {

  public static String daemonTest(String message) throws Exception {
    Socket s = new Socket("www.scit.wlv.ac.uk", 80);
    PrintWriter w = new PrintWriter(s.getOutputStream());
    BufferedReader r = new BufferedReader(new InputStreamReader(
        s.getInputStream()));
    w.printf("GET /~in6659/ScoreDaemon.php?message=%s HTTP/1.0\n", message);
    w.printf("host: www.scit.wlv.ac.uk\n\n");
    w.flush();
    String line;
    int n = 0;
    while ((line = r.readLine()) != null) {
      System.out.printf("%4d %s\n", ++n, line);
    }
    
    s.close();
    return "yep";
  }

  public static String phoneHome(String message) throws Exception {
    Socket s = new Socket("www.scit.wlv.ac.uk", 80);
    PrintWriter w = new PrintWriter(s.getOutputStream());
    BufferedReader r = new BufferedReader(new InputStreamReader(
        s.getInputStream()));
    w.printf("GET /~in6659/ScoreDaemon.php?message=%s HTTP/1.0\n", message);
    w.printf("host: www.scit.wlv.ac.uk\n\n");
    w.flush();
    String line;
    int n = 0;
    while ((line = r.readLine()) != null) {
      ++n;
      if(n==9){
        break;
      }
    }
    
    s.close();
    int errorCode = Integer.parseInt(line);
    if(errorCode != 99){
      throw new IOException("transmission failure");
    }
    return r.readLine();
  }


  public static void saveHighScore(Score s) {
    try {
      phoneHome("name_"+s.name);
      phoneHome("time_"+s.date);
      phoneHome("score_"+s.score);
    } catch (Exception e) {
      System.out.println("problems saving high score");
    }
  }
  
  public static void getHighScores(){
    try {
      String scores = phoneHome("get_da_scorez");
      String [] stuff  = scores.split("_");
      for(int i=0;i<stuff.length;i+=3){
        String name = stuff[i];
        String date = stuff[i+1];
        String score = stuff[i+1];
        Score s = new Score();
        s.name = name;
        s.date = (long) Long.parseLong(date);
        s.score = Integer.parseInt(score);
        System.out.println(s);
      }
    } catch (Exception e) {
      System.out.println("problems getting high score");
    }

  }
  
  public static void main(String[] args) throws Exception {
    ScoreDaemonInterpetter.daemonTest("xx");
  }

}
