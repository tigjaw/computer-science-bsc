package theDogDoor;

import java.util.Timer;
import java.util.TimerTask;

public class Remote {
	private DogDoor door;

	/**
	 * Constructor.
	 * @param door
	 */
	public Remote(DogDoor door) {
		this.door = door;
	}

	/**
	 * Checks state of the door before opening or closing it.
	 */
	public void pressButton() {
		System.out.println("Pressing the Remote Control Button");
		if (door.isItOpen()) {
			door.close();
		} else {
			door.open();
		}
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				door.close();
				timer.cancel();
			}
		}, 5000);
	}
}