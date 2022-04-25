package petsToGo;

public abstract class Aquatic extends Pet {
	private IntAboveZero minSpace;
	private FoodType foodType;

	public Aquatic() {
		super();
		petID = 0;
		isSold = false;
		this.foodType = FoodType.unknown;
	}
	
	public Aquatic(String nameType, Gender gender, Age age,
			Money purchaseCost, Money retailPrice, FoodType foodType, IntAboveZero minSpace) {
		super(nameType,gender,age,purchaseCost,retailPrice);
		this.foodType = foodType;
		this.minSpace = minSpace;
		isSold = false;
	}

	public IntAboveZero getMinSpace() {
		return minSpace;
	}
	
	public String minSpaceToString() {
		String spacecm;
		spacecm = minSpace.getIntAboveZero() + " c^3";
		return spacecm;
	}

	public void setMinSpace(IntAboveZero minSpace) {
		this.minSpace = minSpace;
	}
	
	public FoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}
	
	public String toString() {
		String result = "";
		result = result + "ID= \t\t" + petID + "\n";
		result = result + "Name/Type= \t\t" + nameType + "\n";
		result = result + "Gender= \t\t" + gender + "\n";
		result = result + "Age= \t\t" + ageToString() + "\n";
		result = result + "Purchase Cost= \t" + costToString() + "\n";
		result = result + "Retail Price= \t" + priceToString() + "\n";
		result = result + "Min Space Required= \t" + minSpaceToString() + "\n";
		result = result + "Food Type= \t\t" + foodType;
		return result;
	}

}