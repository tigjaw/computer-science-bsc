package v4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This class provides all the user interaction functionality the application
 * will need.
 * 
 * @author Kevan Buckley
 * @version 1.0, August 2010
 */
public final class ConsoleInputter {

	/**
	 * The user types in characters into the terminal window. When they press
	 * return the characters are read into a string.
	 * 
	 * @return a string containing the entered characters.
	 */
	public static String getString() {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		do {
			try {
				return r.readLine();
			} catch (Exception e) {
			}
		} while (true);
	}

	/**
	 * public staticint getHex() Gets user inputer from keyboard Called after
	 * asked "What do you want to score this as?"
	 * 
	 * @return
	 */
	public static int getHex() {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		do {
			try {
				int hex = Integer.parseInt(r.readLine(), 16);
				return hex;
			} catch (Exception e) {
			}
		} while (true);
	}

	/**
	 * Keeps accepting input until the user enters something that is
	 * successfully converted into an Integer.
	 * 
	 * @return the integer that the user types in.
	 */
	public static int getInt() {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		do {
			try {
				return Integer.parseInt(r.readLine());
			} catch (Exception e) {
				// ignore any thrown exceptions
			}
		} while (true);
	}

}