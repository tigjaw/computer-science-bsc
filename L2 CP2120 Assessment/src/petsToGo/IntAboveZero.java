package petsToGo;

public class IntAboveZero {
	private int overZero;

	public IntAboveZero() {
		overZero = -100;
	}

	public IntAboveZero(int overZero) {
		this.overZero = overZero;
	}

	public int getIntAboveZero() {
		return overZero;
	}

	public boolean setIntAboveZero(int overZero) {
		if (overZero >= 0) {
			this.overZero = overZero;
		} else {
			return false;	
		}
		return true;
	}
}