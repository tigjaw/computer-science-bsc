package chainsaw;

/**
 * Class that models a Chainsaw. Stores all data for Chainsaw and provides
 * update and query methods.
 * 
 * @author Kevan Buckley and modified by Joshua Adam Woodyatt.
 * @version 2.1, April 2008, (based on v2.0, February 2008)
 */
public class Chainsaw {
	/**
	 * name of manufacturer of Chainsaw.
	 */
	protected String manufacturer;
	/**
	 * Number of Teeth on Chainsaw.
	 */
	protected int numberOfTeeth;
	/**
	 * Weight(mass) of Chainsaw. Stored in Kilograms.
	 */
	protected double weight;
	/**
	 * Set true if Chainsaw has safety cut out otherwise set to false.
	 */
	protected boolean hasSafetyCutOut;
	/**
	 * Set true if Chainsaw is used otherwise set to false.
	 */
	protected boolean isUsed;
	/**
	 * Colour of Chainsaw.
	 */
	protected String colour;
	/**
	 * Cut Length of Chainsaw. Stored in cm.
	 */
	protected double cutLength;
	/**
	 * Set true if Chainsaw is exhibited otherwise set to false.
	 */
	protected boolean isExhibited;
	/**
	 * Set true to delete chainsaw otherwise false.
	 */
	protected boolean isDeleted;
	/**
	 * id is unique identifier of Chainsaw.
	 */
	protected int id;
	/**
	 * Shared throughout class. Stores next free id number.
	 */
	protected static int nextId = 0;

	/**
	 * Chainsaw default Constructor.
	 */
	public Chainsaw() {
		super();
		this.manufacturer = "Unknown";
		this.numberOfTeeth = 0;
		this.weight = 0;
		this.hasSafetyCutOut = false;
		this.isUsed = false;
		this.colour = "unknown";
		this.cutLength = 0;
		this.isExhibited = false;
		id = 0;

	}

	/**
	 * Chainsaw constructor.
	 * 
	 * @param manufacturer
	 *            Name of Chainsaw's Manufacturer as String.
	 * @param numberOfTeeth
	 *            Chainsaw's number of teeth as integer.
	 * @param weight
	 *            Chainsaw's weight in kg as double.
	 * @param hasSafetyCutOut
	 *            Whether Chainsaw has safety cut out as boolean.
	 * @param isUsed
	 *            Whether Chainsaw is used as boolean.
	 * @param colour
	 *            Chainsaw's colour as String.
	 * @param cutLength
	 *            Chainsaw's cut length as double.
	 * @param isExhibited
	 *            Whether Chainsaw is exhibited as boolean.
	 */
	public Chainsaw(String manufacturer, int numberOfTeeth, double weight,
			boolean hasSafetyCutOut, boolean isUsed, String colour,
			double cutLength, boolean isExhibited) {
		this.manufacturer = manufacturer;
		this.numberOfTeeth = numberOfTeeth;
		this.weight = weight;
		this.hasSafetyCutOut = hasSafetyCutOut;
		this.isUsed = isUsed;
		this.colour = colour;
		this.cutLength = cutLength;
		this.isExhibited = isExhibited;
		isDeleted = false;
		id = nextId;
		nextId++;
	}

	/**
	 * Sets the manufacturer of the Chainsaw.
	 * 
	 * @param manufacturer
	 *            Manufacturer of Chainsaw.
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Returns the name of the Chainsaw's manufacturer.
	 * 
	 * @return The manufacturer of the Chainsaw.
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Sets the number of teeth the Chainsaw has.
	 * 
	 * @param numberOfTeeth
	 *            Chainsaw's number of teeth.
	 */
	public void setNumberOfTeeth(int numberOfTeeth) {
		this.numberOfTeeth = numberOfTeeth;
	}

	/**
	 * Returns the number of teeth the Chainsaw has.
	 * 
	 * @return The number of teeth.
	 */
	public int getNumberOfTeeth() {
		return numberOfTeeth;
	}

	/**
	 * Sets the weight of the Chainsaw.
	 * 
	 * @param weight
	 *            weight of the Chainsaw in kg.
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Returns the weight of the Chainsaw.
	 * 
	 * @return weight of chainsaw in kg.
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Sets whether the Chainsaw has safety cut out.
	 * 
	 * @param hasSafetyCutOut
	 *            Whether Chainsaw has safety cut out.
	 */
	public void setHasSafetyCutOut(boolean hasSafetyCutOut) {
		this.hasSafetyCutOut = hasSafetyCutOut;
	}

	/**
	 * Returns safety cut out true or false
	 * 
	 * @return whether has safety cut out.
	 */
	public boolean getHasSafetyCutOut() {
		return hasSafetyCutOut;
	}

	/**
	 * Sets whether Chainsaw is used.
	 * 
	 * @param isUsed
	 *            Whether Chainsaw is used.
	 */
	public void setIsUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * Returns is used true or false.
	 * 
	 * @return whether Chainsaw is used or not.
	 */
	public boolean getIsUsed() {
		return isUsed;
	}

	/**
	 * Sets colour of Chainsaw.
	 * 
	 * @param colour
	 *            colour of Chainsaw.
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * Returns the colour of Chainsaw.
	 * 
	 * @return colour of Chainsaw.
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * Sets cut length of Chainsaw.
	 * 
	 * @param cutLength
	 *            cut length of Chainsaw.
	 */
	public void setCutLength(double cutLength) {
		this.cutLength = cutLength;
	}

	/**
	 * Returns cut length of Chainsaw.
	 * 
	 * @return cut length of Chainsaw in cm.
	 */
	public double getCutLength() {
		return cutLength;
	}

	/**
	 * Method used in original text application but also used in testing.
	 */
	public String toString() {
		String result = "";
		result = result + "ID= " + id + ", ";
		result = result + "Manufacturer= " + manufacturer + ", ";
		result = result + "No. of Teeth= " + numberOfTeeth + ", ";
		result = result + "Weight= " + weight + ", ";
		result = result + "Has Safety Cutout= " + hasSafetyCutOut + ", ";
		result = result + "Ised Used= " + isUsed + ", ";
		result = result + "Colour= " + colour + ", ";
		result = result + "Cut Length= " + cutLength + ", ";
		result = result + "Is Exhibited= " + isExhibited;
		return result;
	}

	/**
	 * Returns the ID of Chainsaw.
	 * 
	 * @return unique ID of Chainsaw.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns Chainsaw is deleted in true or false.
	 * 
	 * @return Chainsaw is deleted true or false.
	 */
	public boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * Sets whether Chainsaw is deleted or not.
	 * 
	 * @param isDeleted
	 *            if Chainsaw is marked for deletion.
	 */
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * Sets Chainsaw for deletion.
	 */
	public void delete() {
		this.isDeleted = true;
	}

	/**
	 * Returns Chainsaw is exhibited in true or false.
	 * 
	 * @return is exhibited true or false.
	 */
	public boolean getIsExhibited() {
		return isExhibited;
	}

	/**
	 * Sets Chainsaw for exhibition.
	 * 
	 * @param isExhibited
	 *            if Chainsaw is on exhibition.
	 */
	public void setIsExhibited(boolean isExhibited) {
		this.isExhibited = isExhibited;
	}

	/**
	 * Sets Chainsaw as used
	 * 
	 * @param isUsed
	 *            Chainsaw is used.
	 */
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * Sets exhibited dependant on isExhibited.
	 * 
	 * @param isExhibited
	 *            is exhibited.
	 */
	public void setExhibited(boolean isExhibited) {
		this.isExhibited = isExhibited;
	}

	/**
	 * Sets deleted.
	 * 
	 * @param isDeleted
	 *            Chainsaw deletion.
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * Sets unique ID of Chainsaw.
	 * 
	 * @param id
	 *            Chainsaw unique ID.
	 */
	public void setId(int id) {
		this.id = id;
		nextId = id + 1;
	}

	/**
	 * object equals method override. (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Chainsaw) {
			Chainsaw chain = (Chainsaw) obj;
			if ((manufacturer.equals(chain.getManufacturer()))
					&& (numberOfTeeth == chain.getNumberOfTeeth())
					&& (weight == chain.getWeight())
					&& (hasSafetyCutOut == chain.getHasSafetyCutOut())
					&& (isUsed == chain.getIsUsed())
					&& (colour.equals(chain.getColour()))
					&& (cutLength == chain.getCutLength())
					&& isExhibited == chain.getIsExhibited())
				return true;
		}
		return false;
	}

}