package petsToGo;

public class Marine extends Aquatic {
	private Salinity salinity;

	public Marine() {
		super();
		petID = 0;
		isSold = false;
		this.salinity = salinity;
	}

	/**
	 * @param salinity
	 */
	public Marine(String nameType, Gender gender, Age age,
			Money purchaseCost, Money retailPrice, FoodType foodType,
			IntAboveZero minSpace, Salinity salinity) {
		super(nameType, gender, age, purchaseCost, retailPrice, foodType, minSpace);
		this.salinity = salinity;
	}

	/**
	 * @return the salinity
	 */
	public Salinity getSalinity() {
		return salinity;
	}
	
	public String salinityToString() {
		String salinitys;
		salinitys = salinity.getSalinity() + "%";
		return salinitys;
	}

	/**
	 * @param salinity the salinity to set
	 */
	public void setSalinity(Salinity salinity) {
		this.salinity = salinity;
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
		result = result + "Required Salinity= \t" + salinityToString();
		return result;
	}

}