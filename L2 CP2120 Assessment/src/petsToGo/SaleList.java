package petsToGo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

import javax.naming.LinkLoopException;

public class SaleList {
	private LinkedList<Sale> sales;
	private int numberOfSales;

	public SaleList() {
		initialiseData();
	}

	public void addSale(Sale sale) {
		if (sale != null || sale.getRef() != 0) {
			sales.add(sale);
			numberOfSales++;
		}
	}

	public LinkedList<Sale> listAllSales() {
		return sales;
	}
	
	public LinkedList<Object> listMammalSales() {
		// Mammal Sales
		LinkedList<Object> mammalSales = new LinkedList<Object>();
		int mammalNumber = 0;
		String mammalNumbertxt = "";
		Money mammalTot = new Money(0);
		mammalSales.add("Mammals");
		Iterator<Sale> it = sales.iterator();
		Pet pet;
		Sale loopSale;
		while(it.hasNext()) {
			loopSale = it.next();
			pet = loopSale.getSellPet();
			if(pet instanceof Mammal) {
				mammalNumber ++;
				mammalTot.addMoney(loopSale.getCost().getPoundsAndPence());
			}
		}
		mammalNumbertxt = "" + mammalNumber;
		mammalSales.add(mammalNumbertxt);
		mammalSales.add(mammalTot);
		return mammalSales;
	}
	
	public LinkedList<Object> listBirdSales() {
		LinkedList<Object> birdSales = new LinkedList<Object>();
		int birdNumber = 0;
		String birdNumbertxt = "";
		Money birdTot = new Money(0);
		birdSales.add("Birds");
		Iterator<Sale> it = sales.iterator();
		Pet pet;
		Sale loopSale;
		while(it.hasNext()) {
			loopSale = it.next();
			pet = loopSale.getSellPet();
			if(pet instanceof Bird) {
				birdNumber ++;
				birdTot.addMoney(loopSale.getCost().getPoundsAndPence());
			}
		}
		birdNumbertxt = "" + birdNumber;
		birdSales.add(birdNumbertxt);
		birdSales.add(birdTot);
		return birdSales;
	}
	
	public LinkedList<Object> listReptileSales() {
		LinkedList<Object> reptileSales = new LinkedList<Object>();
		int reptileNumber = 0;
		String reptileNumbertxt = "";
		Money reptileTot = new Money(0);
		reptileSales.add("Reptiles");
		Iterator<Sale> it = sales.iterator();
		Pet pet;
		Sale loopSale;
		while(it.hasNext()) {
			loopSale = it.next();
			pet = loopSale.getSellPet();
			if(pet instanceof Reptile) {
				reptileNumber ++;
				reptileTot.addMoney(loopSale.getCost().getPoundsAndPence());
			}
		}
		reptileNumbertxt = "" + reptileNumber;
		reptileSales.add(reptileNumbertxt);
		reptileSales.add(reptileTot);
		return reptileSales;
	}

	public LinkedList<Object> listMarineSales() {
		LinkedList<Object> marineSales = new LinkedList<Object>();
		int marineNumber = 0;
		String marineNumbertxt = "";
		Money marineTot = new Money(0);
		marineSales.add("Marine Aquatics");
		Iterator<Sale> it = sales.iterator();
		Pet pet;
		Sale loopSale;
		while(it.hasNext()) {
			loopSale = it.next();
			pet = loopSale.getSellPet();
			if(pet instanceof Marine) {
				marineNumber ++;
				marineTot.addMoney(loopSale.getCost().getPoundsAndPence());
			}
		}
		marineNumbertxt = "" + marineNumber;
		marineSales.add(marineNumbertxt);
		marineSales.add(marineTot);
		return marineSales;
	}
	
	public LinkedList<Object> listFreshwaterSales() {
		LinkedList<Object> freshSales = new LinkedList<Object>();
		int freshNumber = 0;
		String freshNumbertxt = "";
		Money freshTot = new Money(0);
		freshSales.add("Freshwater Aquatics");
		Iterator<Sale> it = sales.iterator();
		Pet pet;
		Sale loopSale;
		while(it.hasNext()) {
			loopSale = it.next();
			pet = loopSale.getSellPet();
			if(pet instanceof Freshwater) {
				freshNumber ++;
				freshTot.addMoney(loopSale.getCost().getPoundsAndPence());
			}
		}
		freshNumbertxt = "" + freshNumber;
		freshSales.add(freshNumbertxt);
		freshSales.add(freshTot);
		return freshSales;
	}
	
	/**
	 * Gets data from XML
	 */
	private void initialiseData() {
		sales = new LinkedList<Sale>();
		sales.add(new Sale(new Customer("Dell", "Penny", (new ContactInfo(
				"04586224459", new Address("Java Street", "Smartvale",
						"London", "London", "LN49OP")))),
				new GregorianCalendar(2005, Calendar.FEBRUARY, 17), new Money(
						200, 45), new Bird("Pidgeon", Gender.male, new Age(
						new GregorianCalendar(2003, Calendar.NOVEMBER, 18)),
						new Money(33, 33), new Money(55, 55), Trilean.yes,
						Trilean.no, Trilean.yes, "England")));
		sales.add(new Sale(new Customer("Dodger", "Jammy", (new ContactInfo(
				"15486245965", new Address("gener street", "smart lane",
						"birmingham", "West Midlands", "fd5d87")))),
				new GregorianCalendar(2003, Calendar.SEPTEMBER, 11), new Money(
						325, 50), new Reptile("iguana", Gender.female, new Age(
						new GregorianCalendar(2005, Calendar.MARCH, 24)),
						new Money(55, 99), new Money(65, 89), Trilean.yes,
						new IntAboveZero(33), new Temp(55))));
		numberOfSales = sales.size();
		/*
		 * int noOfSales = 0; // gets linkedlist from xml LinkedList <Sale> xml
		 * = new LinkedList(); sales = xml; Iterator <Sale> it = xml.iterator();
		 * while (it.hasNext()) { noOfSales ++; } numberOfSales = numberOfSales
		 * + noOfSales;
		 */
	}

	private void saveDate() {
		// save data
	}
}