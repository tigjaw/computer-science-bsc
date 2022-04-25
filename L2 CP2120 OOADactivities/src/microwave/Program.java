package microwave;

public class Program {
	public boolean isDefrosting;
	public int duration;

	public enum PowerLevel {
		LOW, MED, HIGH
	};

	protected PowerLevel powerLevel;

	/**
	 * @param isDefrosting
	 * @param duration
	 * @param powerLevel
	 */
	public Program(boolean isDefrosting, int duration, PowerLevel powerLevel) {
		super();
		this.isDefrosting = isDefrosting;
		this.duration = duration;
		this.powerLevel = powerLevel;
	};

	public Program() {
		isDefrosting = false;
		duration = 0;
		powerLevel = powerLevel.LOW;
	}

	/**
	 * @return the isDefrosting
	 */
	public boolean isDefrosting() {
		return isDefrosting;
	}

	/**
	 * @param isDefrosting
	 *            the isDefrosting to set
	 */
	public void setDefrosting(boolean isDefrosting) {
		this.isDefrosting = isDefrosting;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the powerLevel
	 */
	public PowerLevel getPowerLevel() {
		return powerLevel;
	}

	/**
	 * @param powerLevel
	 *            the powerLevel to set
	 */
	public void setPowerLevel(PowerLevel powerLevel) {
		this.powerLevel = powerLevel;
	}
}