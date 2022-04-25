package petsToGo;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Sale {
	protected Customer customer;
	private GregorianCalendar saleDate;
	private Money price;
	private Pet sellPet;
	private int ref;
	
	public Sale() {
		ref = 0;
	}

	/**
	 * @param customer
	 * @param saleDate
	 * @param cost
	 * @param sellPet 
	 */
	public Sale(Customer customer, GregorianCalendar saleDate, Money price, Pet sellPet) {
		super();
		this.customer = customer;
		this.saleDate = saleDate;
		this.price = price;
		this.sellPet = sellPet;
		String refString = sellPet.getPetID() + "" + customer.getCustID();
		this.ref = Integer.parseInt(refString);
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	public int getPetID() {
		int id = sellPet.getPetID();
		return id;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the saleDate
	 */
	public GregorianCalendar getSaleDate() {
		return saleDate;
	}
	
	public String dateToString() {
		String datetxt = "";
		int day = saleDate.get(Calendar.DAY_OF_MONTH);
		String month = "";
		switch(saleDate.get(Calendar.MONTH)) {
		case 0:
			month = "January";
			break;
		case 1:
			month = "February";
			break;
		case 2:
			month = "March";
			break;
		case 3:
			month = "April";
			break;
		case 4:
			month = "May";
			break;
		case 5:
			month = "June";
			break;
		case 6:
			month = "July";
			break;
		case 7:
			month = "August";
			break;
		case 8:
			month = "September";
			break;
		case 9:
			month = "October";
			break;
		case 10:
			month = "November";
			break;
		case 11:
			month = "December";
			break;
		}
		datetxt = datetxt + day + "/" + month + "/";
		datetxt = datetxt + saleDate.get(Calendar.YEAR);
		return datetxt;
	}

	/**
	 * @param saleDate the saleDate to set
	 */
	public void setSaleDate(GregorianCalendar saleDate) {
		this.saleDate = saleDate;
	}

	/**
	 * @return the cost
	 */
	public Money getCost() {
		return price;
	}
	
	public String costToString() {
		return price.getCostTxt();
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(Money cost) {
		this.price = cost;
	}

	/**
	 * @return the sellPet
	 */
	public Pet getSellPet() {
		return sellPet;
	}

	/**
	 * @param sellPet the sellPet to set
	 */
	public void setSellPet(Pet sellPet) {
		this.sellPet = sellPet;
	}

	/**
	 * @return the ref
	 */
	public int getRef() {
		return ref;
	}

	/**
	 * @param ref the ref to set
	 */
	public void setRef() {
		String refString = sellPet.getPetID() + "" + "" + customer.getCustID();
		this.ref = Integer.parseInt(refString);
	}
	
	public String toString() {
		String result = "";
		result = result + " ID: " + getRef() + ", ";
		result = result + " Date of Sale: " + dateToString() + ", ";
		result = result + " Value of Sale: " + price.getCostTxt() + "\n";
		result = result + " Customer: " + customer + "\n";
		result = result + " Pet: " + sellPet;
		return result;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Sale) {
			Sale sale = (Sale) obj;
			if (sale.customer.getSurname().equals(customer.getSurname()) && sale.sellPet.getNameType().equals(sellPet.getNameType())) {
				return true;
			}
		}
		return false;
	}
}