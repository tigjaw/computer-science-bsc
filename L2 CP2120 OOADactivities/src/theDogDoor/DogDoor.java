package theDogDoor;

public class DogDoor {
	private boolean open;
	
	/**
	 * constructor for the class.
	 */
	public DogDoor() {
		this.open = false;
	}
	
	/**
	 * This opens the door.
	 */
	public void open() {
		System.out.println("The Dog Door Opens");
		open = true;
	}
	
	/**
	 * This closes the door.
	 */
	public void close() {
		System.out.println("The Dog Door Closes");
		open = false;
	}
	
	/**
	 * This returns the current state of the door.
	 * @return open.
	 */
	public boolean isItOpen(){
		return open;
	}
}