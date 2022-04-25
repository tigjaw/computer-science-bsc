package petsToGo;

public class Mammal extends Pet {
	private Trilean isRodent;
	private Trilean isNeutered;
	private Cage cage;

	public Mammal() {
		super();
		this.nameType = "unknown";
		petID = 0;
		isSold = false;
		this.cage = Cage.unknown;
		this.isNeutered = Trilean.unknown;
		this.isRodent = Trilean.unknown;
	}

	public Mammal(String nameType, Gender gender, Age age,
			Money purchaseCost, Money retailPrice, Cage cage, Trilean isNeutered, Trilean isRodent) {
		super(nameType,gender,age,purchaseCost,retailPrice);
		isSold = false;
		this.cage = cage;
		this.isNeutered = isNeutered;
		this.isRodent = isRodent;
	}

	/**
	 * @return the isRodent
	 */
	public Trilean getIsRodent() {
		return isRodent;
	}

	/**
	 * @param isRodent the isRodent to set
	 */
	public void setIsRodent(Trilean isRodent) {
		this.isRodent = isRodent;
	}

	/**
	 * @return the isNeutered
	 */
	public Trilean getIsNeutered() {
		return isNeutered;
	}

	/**
	 * @param isNeutered the isNeutered to set
	 */
	public void setIsNeutered(Trilean isNeutered) {
		this.isNeutered = isNeutered;
	}

	/**
	 * @return the cage
	 */
	public Cage getCage() {
		return cage;
	}

	/**
	 * @param cage the cage to set
	 */
	public void setCage(Cage cage) {
		this.cage = cage;
	}
	
	public String toString() {
		String result = "";
		result = result + "ID= \t\t" + petID + "\n";
		result = result + "Name/Type= \t\t" + nameType + "\n";
		result = result + "Gender= \t\t" + gender + "\n";
		result = result + "Age= \t\t" + ageToString() + "\n";
		result = result + "Purchase Cost= \t" + costToString() + "\n";
		result = result + "Retail Price= \t\t" + priceToString() + "\n";
		result = result + "Is a Rodent= \t\t" + isRodent + "\n";
		result = result + "Is Neutered= \t\t" + isNeutered + "\n";
		result = result + "Cage Requirement= \t" + cage;
		return result;
	}

}