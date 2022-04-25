package petsToGo;

public class Customer {
	private int custID;
	protected static int nextId = 0;
	private String surname;
	private String forenames;
	protected ContactInfo contact;
	protected boolean isDeleted;

	public Customer() {
		this.surname = "unknown";
		this.forenames = "unknown";
		custID = 0;
		isDeleted = false;
	}

	/**
	 * @param custID
	 * @param contact
	 */
	public Customer(String surname, String forenames, ContactInfo contact) {
		this.surname = surname;
		this.forenames = forenames;
		this.contact = contact;
		custID = nextId;
		nextId++;
		isDeleted = false;
	}
	
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the forenames
	 */
	public String getForenames() {
		return forenames;
	}

	/**
	 * @param forenames the forenames to set
	 */
	public void setForenames(String forenames) {
		this.forenames = forenames;
	}
	
	/**
	 * @return the custID
	 */
	public int getCustID() {
		return custID;
	}

	/**
	 * @return the contact
	 */
	public ContactInfo getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(ContactInfo contact) {
		this.contact = contact;
	}
	
	/**
	 * @return the isDeleted
	 */
	public boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public String toString() {
		String result = "";
		result = result + "ID: " + custID + "\t";
		result = result + "Name: " + surname + ", ";
		result = result + "" + forenames + "\n";
		result = result + contact.toString();
		return result;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Customer) {
			Customer cust = (Customer) obj;
			if (cust.surname.equals(surname) && cust.contact.equals(contact)) {
				return true;
			}
		}
		return false;
	}
}