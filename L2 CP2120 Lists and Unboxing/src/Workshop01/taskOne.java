package Workshop01;

import java.util.Iterator;
import java.util.LinkedList;

public class taskOne {
	
	/*
	 * A program to create a LinkedList with values 1-9 and then print them out
	 * in reverse order.
	 */
	
	public static void main(String[] args) {
		// Creates LinkedList called myList
		LinkedList<Integer> myList = new LinkedList<Integer>();

		// Adds values 1-9 to LinkedList.
		for (int a = 1; a <= 9; a++) {
			myList.add(a);
		}
		
		/* This prints out whole collection:
		for (Integer i : myList) {
			System.out.println(i);
		}
		*/
		
		// Following prints out list in reverse order:
		Iterator<Integer> it = myList.descendingIterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}
}