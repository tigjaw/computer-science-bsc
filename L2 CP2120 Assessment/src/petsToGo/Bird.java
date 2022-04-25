package petsToGo;

public class Bird extends Pet {
	private Trilean canFly;
	private Trilean fluVac;
	private Trilean canSing;
	private String origin;

	public Bird() {
		super();
		petID = 0;
		isSold = false;
		this.canFly = Trilean.unknown;
		this.fluVac = Trilean.unknown;
		this.canSing = Trilean.unknown;
		this.origin = "Unknown";
	}

	public Bird(String nameType, Gender gender, Age age,
			Money purchaseCost, Money retailPrice, Trilean canFly, Trilean canSing, Trilean fluVac, String origin) {
		super(nameType,gender,age,purchaseCost,retailPrice);
		this.canFly = canFly;
		this.canSing = canSing;
		this.fluVac = fluVac;
		this.origin = origin;
		isSold = false;
	}

	public Trilean getCanFly() {
		return canFly;
	}

	public void setCanFly(Trilean canFly) {
		this.canFly = canFly;
	}

	public Trilean getFluVac() {
		return fluVac;
	}

	public void setFluVac(Trilean fluVac) {
		this.fluVac = fluVac;
	}

	public Trilean getCanSing() {
		return canSing;
	}

	public void setCanSing(Trilean canSing) {
		this.canSing = canSing;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String toString() {
		String result = "";
		result = result + "ID= \t\t" + petID + "\n";
		result = result + "Name/Type= \t\t" + nameType + "\n";
		result = result + "Gender= \t\t" + gender + "\n";
		result = result + "Age= \t\t" + ageToString() + "\n";
		result = result + "Purchase Cost= \t" + costToString() + "\n";
		result = result + "Retail Price= \t\t" + priceToString() + "\n";
		result = result + "Can Fly= \t\t" + canFly + "\n";
		result = result + "Is Fly Vaccinated= \t" + fluVac + "\n";
		result = result + "Can Sing= \t\t" + canSing + "\n";
		result = result + "Origin= \t\t" + origin;
		return result;
	}
}