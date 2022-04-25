package petsToGo;

public class Supplier {
	private int supplierID;
	private String supplierName;
	private String phoneNumber;
	protected Address address;
	protected static int nextId = 0;
	protected boolean isDeleted;

	/**
	 * @param supplierID
	 * @param supplierName
	 */
	public Supplier() {
		supplierID = 0;
		this.supplierName = "unknown";
		this.phoneNumber = "unknown";
		isDeleted = false;
	}

	public Supplier(String supplierName, String phoneNumber, Address address) {
		this.supplierName = supplierName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		supplierID = nextId;
		nextId++;
		isDeleted = false;
	}

	/**
	 * @return the supplierID
	 */
	public int getSupplierID() {
		return supplierID;
	}

	/**
	 * @param supplierID
	 *            the supplierID to set
	 */
	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName
	 *            the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
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
		result = result + "Supplier ID:  " + getSupplierID() + "\n";
		result = result + "Supplier Name:  " + getSupplierName() + "\n";
		result = result + "Phone Number:  " + getPhoneNumber() + "\n";
		result = result + address.getStreetName1() + ", ";
		result = result + address.getStreetName2() + "\n";
		result = result + address.getCity() + "\n";
		result = result + address.getCounty() + ", ";
		result = result + address.getPostCode();
		return result;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Supplier) {
			Supplier supp = (Supplier) obj;
			if (supp.supplierName.equals(supplierName)
					&& supp.phoneNumber.equals(phoneNumber)) {
				return true;
			}
		}
		return false;
	}
}