package petsToGo;

public abstract class Pet {
	protected String nameType;
	protected Gender gender;
	protected Age age;
	protected Money purchaseCost;
	protected Money retailPrice;
	protected int petID;
	protected static int nextId = 0;
	protected boolean isSold;

	public Pet() {
		super();
		this.nameType = "Unknown";
		this.gender = Gender.unknown;
		petID = 0;
		isSold = false;
	}

	/**
	 * @param nameType
	 * @param gender
	 * @param age
	 * @param purchaseCost
	 * @param retailPrice
	 */
	public Pet(String nameType, Gender gender, Age age,
			Money purchaseCost, Money retailPrice) {
		this.nameType = nameType;
		this.gender = gender;
		this.age = age;
		this.purchaseCost = purchaseCost;
		this.retailPrice = retailPrice;
		petID = nextId;
		nextId++;
		isSold = false;
	}

	public String getNameType() {
		return nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Age getAge() {
		return age;
	}
	
	public String ageToString() {
		return age.returnAge();
	}

	public void setAge(Age age) {
		this.age = age;
	}

	public Money getCost() {
		return purchaseCost;
	}
	
	public String costToString() {
		return purchaseCost.getCostTxt();
	}
	
	public void setCost(Money money) {
		this.purchaseCost = money;
	}

	public Money getPrice() {
		return retailPrice;
	}
	
	public String priceToString() {
		return retailPrice.getCostTxt();
	}

	public void setPrice(Money price) {
		this.retailPrice = price;
	}

	public int getPetID() {
		return petID;
	}

	public void setPetID(int petID) {
		this.petID = petID;
	}

	/*
	 * public boolean isDeleted() { return isDeleted; }
	 * 
	 * public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
	 */
	public static int getNextId() {
		return nextId;
	}

	public void setNextId(int petID) {
		this.petID = petID;
		nextId = petID + 1;
	}

	public boolean getIsSold() {
		return isSold;
	}

	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}

	public String toString() {
		String result = "";
		result = result + "ID= \t\t" + petID + "\n";
		result = result + "Name/Type= \t\t" + nameType + "\n";
		result = result + "Gender= \t\t" + gender + "\n";
		result = result + "Age= \t\t" + ageToString() + "\n";
		result = result + "Purchase Cost= \t" + costToString() + "\n";
		result = result + "Retail Price= \t\t" + priceToString() + "\n";
		return result;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Pet) {
			Pet pet = (Pet) obj;
			if (pet.age.equals(age) && pet.gender == gender
					&& pet.nameType == nameType) {
				return true;
			}
		}
		return false;
	}

}