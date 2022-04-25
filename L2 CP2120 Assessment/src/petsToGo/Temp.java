package petsToGo;

public class Temp {
	private int celsius;

	public Temp() {
		celsius = -100;
	}
	
	public Temp(int celsius) {
		this.celsius = celsius;
	}

	public int getCelsius() {
		return celsius;
	}

	public void setCelsius(int celsius) {
		if (celsius >= -5 && celsius <= 50) {
			this.celsius = celsius;
		}

	}

}