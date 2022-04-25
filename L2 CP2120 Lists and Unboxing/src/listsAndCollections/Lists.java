package listsAndCollections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Lists {

	public static void main(String[] args) {
		//typeSafety01();
		//linkedListPrint();
		iterateExample();
	}

	public static void linkedListPrint() {
		LinkedList<Integer> myList = new LinkedList<Integer>();
		myList.add(1);
		myList.add(2);
		myList.add(3);

		for (int i = 0; i < myList.size(); i++) {
			System.out.println(myList.get(i));
		}
	}
	
	public static void iterateExample() {
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("One");
		myList.add("Two");
		myList.add("Three");
		
		Iterator<String> it = myList.iterator();
		System.out.println("it Iterator:");
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("it2 Iterator:");
		for(Iterator<String> it2 = myList.iterator(); it2.hasNext(); ) {
			System.out.println(it2.next());
		}

	}

	public static void typeSafety01() {
		boolean bResult;
		ArrayList<String> al = new ArrayList<String>();
		al.add("Hello");
		al.add("Goodbye");
		// al.add(3);

		for (int i = 0; i < al.size(); i++) {
			bResult = al.get(i).endsWith("o");
			System.out.println(bResult);
		}
	}

}