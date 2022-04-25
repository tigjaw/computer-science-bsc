package petsToGo;

public class Address {
	private String streetName1;
	private String streetName2;
	private String city;
	private String county;
	private String postCode;

	public Address() {
		this.streetName1 = "unknown";
		this.streetName2 = "unknown";
		this.city = "unknown";
		this.county = "unknown";
		this.postCode = "unknown";
	}

	/**
	 * @param streetName1
	 * @param streetName2
	 * @param city
	 * @param county
	 * @param postCode
	 */
	public Address(String streetName1, String streetName2, String city,
			String county, String postCode) {
		super();
		this.streetName1 = streetName1;
		this.streetName2 = streetName2;
		this.city = city;
		this.county = county;
		this.postCode = postCode;
	}

	/**
	 * @return the streetName1
	 */
	public String getStreetName1() {
		return streetName1;
	}

	/**
	 * @param streetName1
	 *            the streetName1 to set
	 */
	public void setStreetName1(String streetName1) {
		this.streetName1 = streetName1;
	}

	/**
	 * @return the streetName2
	 */
	public String getStreetName2() {
		return streetName2;
	}

	/**
	 * @param streetName2
	 *            the streetName2 to set
	 */
	public void setStreetName2(String streetName2) {
		this.streetName2 = streetName2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * @param county
	 *            the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode
	 *            the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Address) {
			Address add = (Address) obj;
			if (add.streetName1.equals(streetName1)
					&& add.streetName2.equals(streetName2)
					&& add.city.equals(city) && add.county.equals(county)
					&& add.postCode.equals(postCode)) {
				return true;
			}
		}
		return false;
	}

}