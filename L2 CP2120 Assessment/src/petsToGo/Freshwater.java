package petsToGo;

public class Freshwater extends Aquatic {
	private Trilean dechlorinated;

	public Freshwater() {
		super();
		petID = 0;
		isSold = false;
		this.dechlorinated = dechlorinated;
	}

	/**
	 * @param dechlorinated
	 */
	public Freshwater(String nameType, Gender gender, Age age,
			Money purchaseCost, Money retailPrice, FoodType foodType,
			IntAboveZero minSpace, Trilean dechlorinated) {
		super(nameType, gender, age, purchaseCost, retailPrice, foodType,
				minSpace);
		isSold = false;
		this.dechlorinated = dechlorinated;
	}

	/**
	 * @return the dechlorinated
	 */
	public Trilean getDechlorinated() {
		return dechlorinated;
	}

	/**
	 * @param dechlorinated the dechlorinated to set
	 */
	public void setDechlorinated(Trilean dechlorinated) {
		this.dechlorinated = dechlorinated;
	}
	
	public String toString() {
		String result = "";
		result = result + "ID= \t\t" + petID + "\n";
		result = result + "Name/Type= \t\t" + nameType + "\n";
		result = result + "Gender= \t\t" + gender + "\n";
		result = result + "Age= \t\t" + ageToString() + "\n";
		result = result + "Purchase Cost= \t" + costToString() + "\n";
		result = result + "Retail Price= \t\t" + priceToString() + "\n";
		result = result + "Min Space Required= \t" + minSpaceToString() + "\n";
		result = result + "Food Type= \t\t" + getFoodType() + "\n";
		result = result + "Requires Dechlorinated= \t" + dechlorinated;
		return result;
	}

}