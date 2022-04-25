package Workshop01;

import java.util.ArrayList;
import java.util.Iterator;

public class taskFour {
	public static void main(String[] args) {
		ArrayList<String> al1 = new ArrayList<String>();

		al1.add("Matt");
		al1.add("Kevan");
		al1.add("Gordon");

		Iterator<String> ir = al1.iterator();

		while(ir.hasNext()) {
			System.out.println("Hello " + ir.next());
		}
	}
}
