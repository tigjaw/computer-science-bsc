package petsToGo;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class DataModelTest extends TestCase {
	private DataModel model;
	private Pet pet1;
	private Pet pet2;
	private Pet pet3;
	private Pet pet4;
	private Pet petRemove;
	private Customer cust1;
	private Customer cust2;
	private Customer cust3;
	private Customer cust4;
	private Customer custRemove;
	private Sale sale1;
	private Sale sale2;
	private Sale sale3;
	private Sale sale4;
	private Order order1;
	private Order order2;
	private Order order3;
	private Order order4;
	private Order purchase1;
	private Order purchase2;
	private Order purchase3;
	private Order purchase4;
	private Supplier supp1;
	private Supplier supp2;
	private Supplier supp3;
	private Supplier supp4;
	private Supplier suppRemove;

	@Before
	protected void setUp() throws Exception {
		model = new DataModel();
		pet1 = new Bird("Pidgeon", Gender.male, new Age(new GregorianCalendar(
				2003, Calendar.NOVEMBER, 18)), new Money(33, 33), new Money(55,
				55), Trilean.yes, Trilean.no, Trilean.yes, "England");
		pet2 = new Reptile("iguana", Gender.male, new Age(
				new GregorianCalendar(2005, Calendar.MARCH, 24)), new Money(55,
				99), new Money(65, 89), Trilean.yes, new IntAboveZero(33),
				new Temp(55));
		pet3 = new Mammal("Dog", Gender.male, new Age(new GregorianCalendar(
				2006, Calendar.JULY, 05)), new Money(230, 50), new Money(300,
				55), Cage.No_Cage, Trilean.yes, Trilean.no);
		pet4 = new Bird("Pidgeon", Gender.male, new Age(new GregorianCalendar(
				2003, Calendar.NOVEMBER, 18)), new Money(33, 33), new Money(55,
				55), Trilean.yes, Trilean.no, Trilean.yes, "England");
		model.addPet(pet1);
		// model.addPet(pet2);
		model.addPet(pet3);
		model.addPet(pet4);

		petRemove = new Reptile("Dogo", Gender.male, new Age(
				new GregorianCalendar(2002, Calendar.JULY, 18)), new Money(55,
				55), new Money(87, 87), Trilean.no, new IntAboveZero(2),
				new Temp(33));
		model.addPet(petRemove);

		cust1 = new Customer("Poole", "Gene",(new ContactInfo("01902592348",
				new Address("Street Road", "Branvale", "Birmingham", "West Midlands",
				"BM55LL"))));
		cust2 = new Customer("Bailey", "Bob", (new ContactInfo("16487254986",
				new Address("Gar Street", "Hobfield", "Manchester", "Manchester",
				"MC96RT"))));
		cust3 = new Customer("Jackel", "Jon", (new ContactInfo("55447012458",
				new Address("Red Road", "Writon", "Norwich", "Norwich",
				"NR39GS"))));
		cust4 = new Customer("Poole", "Gene",(new ContactInfo("01902592348",
				new Address("Street Road", "Branvale", "Birmingham", "West Midlands",
				"BM55LL"))));
		model.addCustomer(cust1);
		// model.addCustomer(cust2);
		model.addCustomer(cust3);
		model.addCustomer(cust4);

		custRemove = new Customer("Drake", "Dave",(new ContactInfo(
				"14596134685", new Address("Dove Ave", "hobfield", "wolv",
						"West Midlands", "648fhd"))));
		model.addCustomer(custRemove);

		sale1 = new Sale(new Customer("Poole", "Gene",(new ContactInfo("01902592348",
				new Address("Street Road", "Branvale", "Birmingham", "West Midlands",
				"BM55LL")))), new GregorianCalendar(2007, Calendar.JULY, 05),
				new Money(200,45), new Bird("Pidgeon", Gender.male, new Age(
						new GregorianCalendar(2003, Calendar.NOVEMBER, 18)),
						new Money(33, 33), new Money(55, 55), Trilean.yes,
						Trilean.no, Trilean.yes, "England"));
		sale2 = new Sale(new Customer("dodger", "jammy",(new ContactInfo(
				"15486245965", new Address("gener street", "smart lane",
						"birmingham", "West Midlands", "fd5d87")))), new GregorianCalendar(
				2006, Calendar.APRIL, 15), new Money(325,50), new Reptile("iguana", Gender.female,
				new Age(new GregorianCalendar(2005, Calendar.MARCH, 24)),
				new Money(55, 99), new Money(65, 89), Trilean.yes,
				new IntAboveZero(33), new Temp(55)));
		sale3 = new Sale(new Customer("Janets", "Jane",(new ContactInfo(
				"65478124589", new Address("uni ave", "writablane",
						"Manchester", "Manchester", "m45r78")))), new GregorianCalendar(2008,
				Calendar.JANUARY, 9), new Money(524,99), new Mammal("Dog", Gender.male, new Age(
				new GregorianCalendar(2006, Calendar.JULY, 05)), new Money(230,
				50), new Money(300, 55), Cage.No_Cage, Trilean.yes, Trilean.no));
		sale4 = new Sale(new Customer("Poole", "Gene",(new ContactInfo("01902592348",
				new Address("Street Road", "Branvale", "Birmingham", "West Midlands",
				"BM55LL")))), new GregorianCalendar(2004, Calendar.AUGUST, 27),
				new Money(200,45), new Bird("Pidgeon", Gender.male, new Age(
						new GregorianCalendar(2003, Calendar.NOVEMBER, 18)),
						new Money(33, 33), new Money(55, 55), Trilean.yes,
						Trilean.no, Trilean.yes, "England"));
		model.recordSale(sale1);
		// model.recordSale(sale2);
		model.recordSale(sale3);
		model.recordSale(sale4);

		order1 = new Order(new Supplier("petSuppliers", "14568215478", 
				new Address("53 Orchard Lane", "Brentwood", "Essex", "Essex", "CM132DR")),
				new Money(100, 99), new Mammal("Dog", Gender.male, new Age(
						new GregorianCalendar(2006, Calendar.JULY, 05)),
						new Money(100, 99), new Money(300, 55), Cage.No_Cage,
						Trilean.yes, Trilean.no));
		order2 = new Order(new Supplier("Pets to Leave", "14587256489",
				new Address("63 Horsefair Green", "Old Radnor", "Burnley",
						"Lancashire", "BN31JF")), new Money(200, 50),
				new Reptile("iguana", Gender.female, new Age(
						new GregorianCalendar(2005, Calendar.JUNE, 24)),
						new Money(200, 50), new Money(250, 89), Trilean.yes,
						new IntAboveZero(33), new Temp(55)));
		order3 = new Order(new Supplier("Birds 'R Us", "48756124856",
				new Address("10 Lindsay Square", "Deans Industrial Estate",
						"Livingston", "Livingston", "EH548RL")), new Money(
				145, 23), new Bird("Parrot", Gender.female, new Age(
				new GregorianCalendar(2007, Calendar.DECEMBER, 11)), new Money(
				145, 23), new Money(200, 23), Trilean.no, Trilean.yes,
				Trilean.yes, "Brazil"));
		order4 = new Order(new Supplier("petSuppliers", "14568215478", 
				new Address("53 Orchard Lane", "Brentwood", "Essex", "Essex", "CM132DR")),
				new Money(100, 99), new Mammal("Dog", Gender.male, new Age(
						new GregorianCalendar(2006, Calendar.JULY, 05)),
						new Money(100, 99), new Money(300, 55), Cage.No_Cage,
						Trilean.yes, Trilean.no));

		purchase1 = new Purchase(new Supplier("petSuppliers", "14568215478", 
				new Address("53 Orchard Lane", "Brentwood", "Essex", "Essex", "CM132DR")),
				new Money(100, 99), new Mammal("Dog", Gender.male, new Age(
						new GregorianCalendar(2006, Calendar.JULY, 05)),
						new Money(100, 99), new Money(300, 55), Cage.No_Cage,
						Trilean.yes, Trilean.no), new GregorianCalendar(2008, Calendar.DECEMBER, 21));
		purchase2 = new Purchase(new Supplier("We Sell Pets", "14587256489",
				new Address("63 Horsefair Green", "Old Radnor", "Burnley",
						"Lancashire", "BN31JF")), new Money(200, 50),
				new Reptile("iguana", Gender.female, new Age(
						new GregorianCalendar(2005, Calendar.MARCH, 24)),
						new Money(200, 50), new Money(250, 89), Trilean.yes,
						new IntAboveZero(33), new Temp(55)), new GregorianCalendar(2004, Calendar.FEBRUARY,
						15));
		purchase3 = new Purchase(new Supplier("Birds 'R Us", "48756124856",
				new Address("10 Lindsay Square", "Deans Industrial Estate",
						"Livingston", "Livingston", "EH548RL")), new Money(
				145, 23), new Bird("Parrot", Gender.female, new Age(
				new GregorianCalendar(2007, Calendar.DECEMBER, 11)), new Money(
				145, 23), new Money(200, 23), Trilean.no, Trilean.yes,
				Trilean.yes, "Brazil"), new GregorianCalendar(2006, Calendar.JULY, 02));
		purchase4 = new Purchase(new Supplier("petSuppliers", "14568215478", 
				new Address("53 Orchard Lane", "Brentwood", "Essex", "Essex", "CM132DR")),
				new Money(100, 99), new Mammal("Dog", Gender.male, new Age(
						new GregorianCalendar(2006, Calendar.JULY, 05)),
						new Money(100, 99), new Money(300, 55), Cage.No_Cage,
						Trilean.yes, Trilean.no), new GregorianCalendar(2008, Calendar.MARCH, 9));

		model.recordOrder(order1);
		// model.recordOrder(order2);
		model.recordOrder(order3);
		model.recordOrder(order4);

		model.recordOrder(purchase1);
		// model.recordOrder(purchase2);
		model.recordOrder(purchase3);
		model.recordOrder(purchase4);

		supp1 = new Supplier("petSuppliers", "14568215478", new Address(
				"53 Orchard Lane", "Brentwood", "Essex", "Essex", "CM132DR"));
		supp2 = new Supplier("Birds 'R Us", "48756124856", new Address(
				"10 Lindsay Square", "Deans Industrial Estate", "Livingston",
				"Livingston", "EH548RL"));
		supp3 = new Supplier("We Sell Pets", "14587256489", new Address(
				"63 Horsefair Green", "Old Radnor", "Burnley", "Lancashire",
				"BN31JF"));
		supp4 = new Supplier("petSuppliers", "14568215478", new Address(
				"53 Orchard Lane", "Brentwood", "Essex", "Essex", "CM132DR"));
		model.addSupplier(supp1);
		// model.addSupplier(supp2);
		model.addSupplier(supp3);
		model.addSupplier(supp4);

		suppRemove = new Supplier("Come here for Pets", "58462154986",
				new Address("45 Gilmore Road", "Yore Square", "Melton",
						"Meltlands", "ML92TY"));
		model.addSupplier(suppRemove);
	}

	@Test
	public void testPetEquals() {
		assertTrue(pet1.equals(pet4));
		assertFalse(pet1.equals(pet3));
	}

	@Test
	public void testGetPets() {
		assertTrue(includes(model.getPets(0), pet1));
		assertFalse(includes(model.getPets(0), pet2));
		assertTrue(includes(model.getPets(0), pet3));
		assertTrue(includes(model.getPets(0), pet4));
	}

	@Test
	public void testRemovePets() {
		assertTrue(includes(model.getPets(0), petRemove));
		model.removePet(petRemove.getPetID());
		assertFalse(includes(model.getPets(0), petRemove));
	}

	@Test
	public void testCustomerEquals() {
		assertTrue(cust1.equals(cust4));
		assertFalse(cust1.equals(cust3));
	}

	@Test
	public void testGetCustomers() {
		assertTrue(includes(model.getAllCustomers(), cust1));
		assertFalse(includes(model.getAllCustomers(), cust2));
		assertTrue(includes(model.getAllCustomers(), cust3));
	}

	@Test
	public void testRemoveCustomer() {
		assertTrue(includes(model.getAllCustomers(), custRemove));
		model.removeCustomer(custRemove.getCustID());
		assertFalse(includes(model.getAllCustomers(), custRemove));
	}

	@Test
	public void testSaleEquals() {
		assertTrue(sale1.equals(sale4));
		assertFalse(sale1.equals(sale3));
	}

	@Test
	public void testGetSales() {
		assertTrue(includes(model.getAllSales(), sale1));
		assertFalse(includes(model.getAllSales(), sale2));
		assertTrue(includes(model.getAllSales(), sale3));
	}

	@Test
	public void testOrderEquals() {
		assertTrue(order1.equals(order4));
		assertFalse(order1.equals(order3));
	}

	@Test
	public void testGetOrders() {
		assertTrue(includes(model.getAllOrders(), order1));
		assertFalse(includes(model.getAllOrders(), order2));
		assertTrue(includes(model.getAllOrders(), order3));
		assertTrue(includes(model.getAllOrders(), order4));
	}

	@Test
	public void testPurchaseEquals() {
		assertTrue(purchase1.equals(purchase4));
		assertFalse(purchase1.equals(purchase3));
	}

	@Test
	public void testGetPurchases() {
		assertTrue(includes(model.getCompletePurchases(), purchase1));
		assertFalse(includes(model.getCompletePurchases(), purchase2));
		assertTrue(includes(model.getCompletePurchases(), purchase3));
		assertTrue(includes(model.getCompletePurchases(), purchase4));
	}

	@Test
	public void testPayForOrder() {
		assertTrue(!(order1 instanceof Purchase));
		model.payForOrder(order1.getRef(), new GregorianCalendar(2008, Calendar.APRIL, 05));
		assertTrue(order1 instanceof Purchase);
	}

	@Test
	public void testSupplierEquals() {
		assertTrue(supp1.equals(supp4));
		assertFalse(supp1.equals(supp3));
	}

	@Test
	public void testGetSuppliers() {
		assertTrue(includes(model.listAllSuppliers(), supp1));
		assertFalse(includes(model.listAllSuppliers(), supp2));
		assertTrue(includes(model.listAllSuppliers(), supp3));
		assertTrue(includes(model.listAllSuppliers(), supp4));
	}

	@Test
	public void testRemoveSupplier() {
		assertTrue(includes(model.listAllSuppliers(), suppRemove));
		model.removeSupplier(suppRemove.getSupplierID());
		assertFalse(includes(model.listAllSuppliers(), suppRemove));
	}

	@Test
	public void showRevenues() {
		/* add new sale
		 * sum up expected total
		 * check expected total with actual total
		 * if total == expected total test has passed.
		 */
	}

	private boolean includes(LinkedList<Pet> pets, Pet pet) {
		Iterator<Pet> it = pets.iterator();
		while (it.hasNext()) {
			Pet p = it.next();
			if (p.equals(pet)) {
				return true;
			}
		}
		return false;
	}

	private boolean includes(LinkedList<Customer> customers, Customer cust) {
		Iterator<Customer> it = customers.iterator();
		while (it.hasNext()) {
			Customer c = it.next();
			if (c.equals(cust)) {
				return true;
			}
		}
		return false;
	}

	private boolean includes(LinkedList<Sale> sales, Sale sale) {
		Iterator<Sale> it = sales.iterator();
		while (it.hasNext()) {
			Sale s = it.next();
			if (s.equals(sale)) {
				return true;
			}
		}
		return false;
	}

	private boolean includes(LinkedList<Order> orders, Order order) {
		Iterator<Order> it = orders.iterator();
		while (it.hasNext()) {
			Order o = it.next();
			if (o.equals(order)) {
				return true;
			}
		}
		return false;
	}

	private boolean includes(LinkedList<Supplier> suppliers, Supplier supplier) {
		Iterator<Supplier> it = suppliers.iterator();
		while (it.hasNext()) {
			Supplier s = it.next();
			if (s.equals(supplier)) {
				return true;
			}
		}
		return false;
	}
}