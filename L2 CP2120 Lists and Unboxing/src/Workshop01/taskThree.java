package Workshop01;

import java.util.ArrayList;
import java.util.Iterator;

public class taskThree {

	/*
	 * A program to create an ArrayList with values 1-9 and then print them out
	 * in reverse order.
	 */

	public static void main(String[] args) {
		// Creates ArrayList called myAL.
		ArrayList<Integer> myAL = new ArrayList<Integer>();

		// Adds values 1-9 to ArrayList.
		for (int a = 1; a <= 9; a++) {
			myAL.add(a);
		}

		// Prints out ArrayList.
		for (Integer i : myAL) {
			System.out.println(i);
		}
	}

}