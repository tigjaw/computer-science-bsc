package chainsaw;

import java.io.*;
    
/**
 * This class provides static methods that enable data entry in a variety of
 * formats. For example the <i>getInt</i> method displays a dialog box that 
 * prompts the user to type in an integer. The user will not be able to close
 * the dialog until a valid integer is entered. The entered integer is returned
 * to the calling program. Similar methods are available for other data types.
 * This is an important part of the chainsaw Museum application.
 * 
 * @author Kevan Buckley
 * @version 1.1, March 2003, updated February 2008 for chainsaw  
 */

public class TextInputPrompt {

/**
 *  This class need not (cannot!) be constructed. All its functionality is
 *  provided through static methods.
 */

  private TextInputPrompt() {
  }
  
/**
 * Displays the specified prompt and waits for user to type in an integer
 * and press the OK button. If the text typed cannot be converted to an 
 * integer or if the user presses the cancel button, the prompt is 
 * redisplayed and the user must enter text again.
 * 
 * @param prompt Text to be displayed whilst waiting for input.
 * @return The integer the user typed.
 */
  
  public static int getInt(String prompt) {
    while(true) {
      System.out.print(prompt);
      try {
        return Integer.parseInt(readLine());
      } catch(Exception e) {      
      }
    }
  }
  
/**
 * Displays the specified prompt and waits for user to type in a double
 * and press the OK button. If the text typed cannot be converted to a 
 * double or if the user presses the cancel button, the prompt is 
 * redisplayed and the user must enter text again.
 * 
 * @param prompt Text to be displayed whilst waiting for input.
 * @return The double the user typed.
 */
  
  public static double getDouble(String prompt) {
    while(true) {
      System.out.print(prompt);
      try {
        return Double.parseDouble(readLine());
      } catch(Exception e) {      
      }
    }
  }

/**
 * Displays the specified prompt and waits for user to type in a Boolean
 * value (either <i>true</i> or <i>false</i>)
 * and press the OK button. If the text typed cannot be recognised as a 
 * Boolean or if the user presses the cancel button, the prompt is 
 * redisplayed and the user must enter text again.
 * 
 * @param prompt Text to be displayed whilst waiting for input.
 * @return The Boolean the user typed.
 */
  
  public static boolean getBoolean(String prompt) {
    while(true) {
      System.out.print(prompt);
      try {
        return (new Boolean(readLine())).booleanValue();
      } catch(Exception e) {      
      }
    }
  }

/**
 * Displays the specified prompt and waits for user to type in some text and
 * press a button.
 * 
 * @param prompt Text to be displayed whilst waiting for input.
 * @return If the user pressed <i>OK</i> it returns the text the user typed. 
 * If the user presses cancel <i>null</i> is returned.
 */
  
  public static String getString(String prompt) {
    System.out.print(prompt);
    try {
      return readLine();
    } catch(Exception e) {      
      return "";
    }   
  }
  
  public static void pause() {
    System.out.print("Press return to continue");
    try {
      readLine();
    } catch(Exception e) {      
    }   
  }
  
  private static String readLine() throws IOException {
     BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
     String result = in.readLine();
     return result;
  }
}

// chainsaw
