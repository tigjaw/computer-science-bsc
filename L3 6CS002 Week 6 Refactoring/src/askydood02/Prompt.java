package askydood02;
import java.io.*;


public class Prompt {
  public static int getInt(String message){
    System.out.print(message + ":");
    while(true){
      try {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(r.readLine());
      }catch (Exception e) {
        System.out.println("Enter a whole number:");
      }
    }
  }

  public static String getString(String message){
    System.out.print(message + ":");
    while(true){
      try {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        return r.readLine();
      }catch (Exception e) {
      }
    }
  }

  public static char getChar(String message){
    System.out.print(message + ":");
    while(true){
      try {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        return r.readLine().charAt(0);
      }catch (Exception e) {
      }
    }
  }

}
