package mvc;

import java.awt.event.*;

class CelsiusGUI extends TemperatureGUI {
	public CelsiusGUI(TemperatureModel model) {
		super("Celsius Temperature", model);
		setDisplay("" + model.getCelsius());
		addUpListener(new UpListener());
		addDownListener(new DownListener());
	}

	public void update(Subject subject) {
		setDisplay(round(model.getCelsius()));
	}

	class UpListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model.incrementCelsius();
		}
	}

	class DownListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model.decrementCelsius();
		}
	}
}
