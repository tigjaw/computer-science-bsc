package petsToGo;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Purchase extends Order {
	private GregorianCalendar dateOfPurchase;

	public Purchase() {
		super();
	}

	public Purchase(Supplier supplier, Money cost, Pet petFromSupplier,
			GregorianCalendar dateOfPurchase) {
		super(supplier, cost, petFromSupplier);
		this.dateOfPurchase = dateOfPurchase;
	}

	/**
	 * @return the dateOfPurchase
	 */
	public GregorianCalendar getDateOfPurchase() {
		return dateOfPurchase;
	}
	
	public String dateToString() {
		String datetxt = "";
		int day = dateOfPurchase.get(Calendar.DAY_OF_MONTH);
		String month = "";
		switch(dateOfPurchase.get(Calendar.MONTH)) {
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
		datetxt = datetxt + dateOfPurchase.get(Calendar.YEAR);
		return datetxt;
	}

	/**
	 * @param dateOfPurchase
	 *            the dateOfPurchase to set
	 */
	public void setDateOfPurchase(GregorianCalendar dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
}