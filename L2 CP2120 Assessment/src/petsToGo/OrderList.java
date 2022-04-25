package petsToGo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

public class OrderList {
	private LinkedList<Order> orders;
	private int numberOfOrders;

	public OrderList() {
		initialiseData();
	}

	public void addOrder(Order order) {
		if (order != null || order.getRef() != 0) {
			orders.add(order);
			numberOfOrders++;
		}
	}

	public boolean purchaseOrder(int id, GregorianCalendar datePaid) {
		Iterator<Order> it = orders.iterator();
		Order loopOrder;
		LinkedList<Order> loopOrders = new LinkedList<Order>();
		while (it.hasNext()) {
			loopOrder = it.next();
			if (loopOrder.getRef() == id && !(loopOrder instanceof Purchase)) {
				Order newOrder = new Purchase(loopOrder.getSupplier(),
						loopOrder.getCost(), loopOrder.getPetFromSupplier(),
						datePaid);
				newOrder.setRef(loopOrder.getRef());
				int i = loopOrders.indexOf(loopOrder);
				loopOrders.remove(loopOrders.indexOf(i));
				loopOrders.add(newOrder);
				return true;
			} else {
				System.out.println("no orders found with specified ID");
				return false;
				
			}
		}
		return true;
	}

	public LinkedList<Order> listAllOrders() {
		return orders;
	}

	public LinkedList<Order> listOnlyOrders() {
		Iterator<Order> it = orders.iterator();
		Order loopOrder;
		LinkedList<Order> loopOrders = new LinkedList<Order>();
		while (it.hasNext()) {
			loopOrder = it.next();
			if (!(loopOrder instanceof Purchase)) {
				loopOrders.add(loopOrder);
			}
		}
		return loopOrders;
	}

	public LinkedList<Order> listOnlyPurchases() {
		Iterator<Order> it = orders.iterator();
		Order loopOrder;
		LinkedList<Order> loopOrders = new LinkedList<Order>();
		while (it.hasNext()) {
			loopOrder = it.next();
			if (loopOrder instanceof Purchase) {
				loopOrders.add(loopOrder);
			}
		}
		return loopOrders;
	}

	/**
	 * Gets data from XML
	 */
	private void initialiseData() {
		orders = new LinkedList<Order>();
		orders.add(new Order(new Supplier("Pet Suppliers", "01568215478",
				new Address("53 Orchard Lane", "Brentwood", "Essex", "Essex",
						"CM132DR")), new Money(100, 99), new Mammal("Dog",
				Gender.male, new Age(new GregorianCalendar(2006, Calendar.JULY,
						05)), new Money(100, 99), new Money(300, 55),
				Cage.No_Cage, Trilean.yes, Trilean.no)));
		orders.add(new Order(new Supplier("We Sell Pets", "01587256489",
				new Address("63 Horsefair Green", "Old Radnor", "Burnley",
						"Lancashire", "BN31JF")), new Money(200, 50),
				new Reptile("iguana", Gender.female, new Age(
						new GregorianCalendar(2005, Calendar.MARCH, 24)),
						new Money(200, 50), new Money(250, 89), Trilean.yes,
						new IntAboveZero(33), new Temp(55))));
		orders.add(new Purchase(new Supplier("Birds 'R Us", "01756124856",
				new Address("10 Lindsay Square", "Deans Industrial Estate",
						"Livingston", "Livingston", "EH548RL")), new Money(145,
				23), new Bird("Parrot", Gender.female, new Age(
				new GregorianCalendar(2007, Calendar.DECEMBER, 11)), new Money(
				145, 23), new Money(200, 23), Trilean.no, Trilean.yes,
				Trilean.yes, "Brazil"), new GregorianCalendar(2006,
				Calendar.JULY, 02)));
		numberOfOrders = orders.size();
	}

	private void saveData() {
		// save data
	}
}