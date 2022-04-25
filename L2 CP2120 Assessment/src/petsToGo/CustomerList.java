package petsToGo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;

public class CustomerList {
	private LinkedList<Customer> customers;
	private int numberOfCustomers;
	
	public CustomerList() {
		initialiseData();
	}
	
	public boolean addCust(Customer cust) {
		if(cust != null || !cust.getForenames().equals("unknown")) {
			customers.add(cust);
			numberOfCustomers++;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean remCust(int id) {
		boolean deleted = false;
		Iterator<Customer> it = customers.iterator();
		Customer loopCust;
		LinkedList<Customer> loopCustomers = new LinkedList<Customer>();
		while (it.hasNext()) {
			loopCust = it.next();
			if(loopCust.getCustID() == id) {
				loopCust.setDeleted(true);
				loopCustomers.add(loopCust);
				deleted = true;
				numberOfCustomers --;
			} else {
				loopCustomers.add(loopCust);
			}
		}
		customers = loopCustomers;
		return deleted;
	}
	
	public LinkedList<Customer> listAllCustomers() {
		Iterator<Customer> it = customers.iterator();
		Customer loopCust;
		LinkedList<Customer> loopCusts = new LinkedList<Customer>();
		while(it.hasNext()) {
			loopCust = it.next();
			if(loopCust.getIsDeleted() != true) {
				loopCusts.add(loopCust);
			}
		}
		return loopCusts;
	}
	
	private void initialiseData() {
		LinkedList<Customer> customersFromXml;
		customers = new LinkedList<Customer>();
		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream("customers.xml"));
			customersFromXml = (LinkedList<Customer>) decoder.readObject();
			Iterator<Customer> it = customersFromXml.iterator();
			Customer customer;
			while(it.hasNext()) {
				customer = it.next();
				Customer c = new Customer(customer.getSurname(), customer.getForenames(), customer.getContact());
				customers.add(c);
			}
			numberOfCustomers = customers.size();
			decoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

/*		customers.add(new Customer("Poole", "Gene",(new ContactInfo("01902592348",
				new Address("Street Road", "Branvale", "Birmingham", "West Midlands",
						"BM55LL")))));
		customers.add(new Customer("Bobinson", "Bob", (new ContactInfo("16487254986",
				new Address("Gar Street", "Hobfield", "Manchester", "Manchester",
				"MC96RT")))));
		customers.add(new Customer("Jackel", "Jon", (new ContactInfo("55447012458",
				new Address("Red Road", "Writon", "Norwich", "Norwich",
				"NR39GS")))));
		numberOfCustomers = customers.size();*/
	}
	
	public void saveData() {
		try {
			XMLEncoder encoder = new XMLEncoder(new FileOutputStream("customers.xml"));
			encoder.writeObject(customers);
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int getNumberOfCustomers() {
		return numberOfCustomers;
	}

	public void setNumberOfCustomers(int numberOfCustomers) {
		this.numberOfCustomers = numberOfCustomers;
	}
}