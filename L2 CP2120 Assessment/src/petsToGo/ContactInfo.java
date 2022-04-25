package petsToGo;

public class ContactInfo {
	private String telephone;
	protected Address address;
	
	public ContactInfo() {
		this.telephone = "unknown";
	}

	/**
	 * @param surname
	 * @param forenames
	 * @param telephone
	 * @param address
	 */
	public ContactInfo(String telephone,
			Address address) {
		this.telephone = telephone;
		this.address = address;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	
	public String toString() {
		String result = "";
		result = result + "Telephone: " + telephone + "\n";
		result = result + "Address:\n" + address.getStreetName1() + "\n";
		result = result + "" + address.getStreetName2() + "\n";
		result = result + "" + address.getCity() + "\n";
		result = result + "" + address.getCounty() + "\n";
		result = result + "" + address.getPostCode();
		return result;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof ContactInfo) {
			ContactInfo ci = (ContactInfo) obj;
			if (ci.telephone.equals(telephone)) {
				return true;
			}
		}
		return false;
	}
}