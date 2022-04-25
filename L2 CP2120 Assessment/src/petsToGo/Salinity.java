package petsToGo;

public class Salinity {
	private int salinity;

	public Salinity() {
		salinity = -100;
	}
	
	public Salinity(int salinity) {
		if (salinity >= 0 && salinity <= 100) {
			this.salinity = salinity;
		}
	}

	public int getSalinity() {
		return salinity;
	}

	public void setSalinity(int salinity) {
		if (salinity >= 0 && salinity <= 100) {
			this.salinity = salinity;
		}
	}

}