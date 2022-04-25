package petsToGo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;

public class SupplierList {
	private LinkedList<Supplier> suppliers;
	private int numberOfSuppliers;
	
	public SupplierList() {
		initialiseData();
	}
	
	public boolean addSupplier(Supplier supp) {
		if (supp == null
				|| supp.getSupplierName().equals("unknown")
				|| supp.getPhoneNumber().equals("unknown")) {
			return false;
		} else {
			suppliers.add(supp);
			numberOfSuppliers++;
			return true;
		}
	}
	
	public boolean remSupplier(int id) {
		boolean deleted = false;
		Iterator<Supplier> it = suppliers.iterator();
		Supplier loopSupp;
		LinkedList<Supplier> loopSupps = new LinkedList<Supplier>();
		while (it.hasNext()) {
			loopSupp = it.next();
			if(loopSupp.getSupplierID() == id) {
				loopSupp.setDeleted(true);
				loopSupps.add(loopSupp);
				deleted = true;
				numberOfSuppliers--;
			} else {
				loopSupps.add(loopSupp);
			}
		}
		suppliers = loopSupps;
		return deleted;
	}
	
	public LinkedList<Supplier> listAllSuppliers() {
		Iterator<Supplier> it = suppliers.iterator();
		Supplier loopSupplier;
		LinkedList<Supplier> loopSuppliers = new LinkedList<Supplier>();
		while(it.hasNext()) {
			loopSupplier = it.next();
			if(loopSupplier.getIsDeleted() != true) {
				loopSuppliers.add(loopSupplier);
			}
		}
		return loopSuppliers;
	}
	
	/**
	 * Gets data from XML
	 */
	private void initialiseData() {
		suppliers = new LinkedList<Supplier>();
		LinkedList<Supplier> suppliersFromXml;
		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream("suppliers.xml"));
			suppliersFromXml = (LinkedList<Supplier>) decoder.readObject();
			Iterator<Supplier> it = suppliersFromXml.iterator();
			Supplier supplier;
			while(it.hasNext()) {
				supplier = it.next();
				Supplier s = new Supplier(supplier.getSupplierName(), supplier.getPhoneNumber(), supplier.getAddress());
				suppliers.add(s);
			}
			setNumberOfSuppliers(suppliers.size());
			decoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
/*		suppliers.add(new Supplier("Pet Suppliers", "01456821547", 
				new Address("53 Orchard Lane", "Brentwood", "Essex", "Essex", "CM13 2DR")));
		suppliers.add(new Supplier("Birds 'R Us", "04875612485", 
				new Address("10 Lindsay Square", "Deans Industrial Estate", "Livingston", "Livingston", "EH54 8RL")));
		suppliers.add(new Supplier("We Sell Pets", "01458725648", 
				new Address("63 Horsefair Green", "Old Radnor", "Burnley", "Lancashire", "BN3 1JF")));
		numberOfSuppliers = suppliers.size();*/
	}
	
	public void saveData() {
		try {
			XMLEncoder encoder = new XMLEncoder(new FileOutputStream("suppliers.xml"));
			encoder.writeObject(suppliers);
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int getNumberOfSuppliers() {
		return numberOfSuppliers;
	}

	public void setNumberOfSuppliers(int numberOfSuppliers) {
		this.numberOfSuppliers = numberOfSuppliers;
	}

}