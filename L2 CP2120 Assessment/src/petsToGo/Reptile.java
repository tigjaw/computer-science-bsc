package petsToGo;

public class Reptile extends Pet {
	private Temp temp;
	private IntAboveZero length;
	private Trilean isPoisonous;

	public Reptile() {
		super();
		petID = 0;
		isSold = false;
		this.isPoisonous = Trilean.unknown;
	}
	public Reptile(String nameType, Gender gender, Age age,
			Money purchaseCost, Money retailPrice, Trilean isPoisonous, IntAboveZero length, Temp temp) {
		super(nameType,gender,age,purchaseCost,retailPrice);
		isSold = false;
		this.isPoisonous = isPoisonous;
		this.length = length;
		this.temp = temp;
	}
	
	/**
	 * @return the temp
	 */
	public Temp getTemp() {
		return temp;
	}
	
	public String tempToString() {
		String tempc;
		tempc = temp.getCelsius() + "c";
		return tempc;
	}
	
	/**
	 * @param temp the temp to set
	 */
	public void setTemp(Temp temp) {
		this.temp = temp;
	}
	/**
	 * @return the length
	 */
	public IntAboveZero getLength() {
		return length;
	}
	
	public String lengthToString() {
		String lengthmm;
		lengthmm = length.getIntAboveZero() + "mm";
		return lengthmm;
	}
	
	/**
	 * @param length the length to set
	 */
	public void setLength(IntAboveZero length) {
		this.length = length;
	}
	/**
	 * @return the isPoisonous
	 */
	public Trilean getIsPoisonous() {
		return isPoisonous;
	}
	/**
	 * @param isPoisonous the isPoisonous to set
	 */
	public void setIsPoisonous(Trilean isPoisonous) {
		this.isPoisonous = isPoisonous;
	}
	
	public String toString() {
		String result = "";
		result = result + "ID= \t\t" + petID + "\n";
		result = result + "Name/Type= \t\t" + nameType + "\n";
		result = result + "Gender= \t\t" + gender + "\n";
		result = result + "Age= \t\t" + ageToString() + "\n";
		result = result + "Purchase Cost= \t" + costToString() + "\n";
		result = result + "Retail Price= \t\t" + priceToString() + "\n";
		result = result + "Required Temperature= \t" + tempToString() + "\n";
		result = result + "Length= \t\t" + lengthToString() + "\n";
		result = result + "Is Poisonous= \t" + isPoisonous;
		return result;
	}
}