package mvc;

public class TemperatureModel extends Subject {
	private double farenheit = 32.0;

	public double getFarenheit() {
		return farenheit;
	}

	public double getCelsius() {
		return (5.0 / 9.0) * (farenheit - 32.0);
	}

	public void setFarenheit(final double farenheit) {
		this.farenheit = farenheit;
		notifyObservers();
	}

	public void setCelsius(final double celsius) {
		this.farenheit = (celsius * 9.0 / 5.0) + 32.0;
		notifyObservers();
	}

	public void incrementFarenheit() {
		setFarenheit(getFarenheit() + 1);
	}

	public void decrementFarenheit() {
		setFarenheit(getFarenheit() - 1);
	}

	public void incrementCelsius() {
		setCelsius(getCelsius() + 1);
	}

	public void decrementCelsius() {
		setCelsius(getCelsius() - 1);
	}
}
