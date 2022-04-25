package mvc;

import java.awt.event.*;

class FarenheitGUI extends TemperatureGUI {
	public FarenheitGUI(TemperatureModel model) {
		super("Farenheit Temperature", model);
		setDisplay("" + model.getFarenheit());
		addUpListener(new UpListener());
		addDownListener(new DownListener());
	}

	public void update(Subject t) {
		setDisplay(round(model.getFarenheit()));
	}

	class UpListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model.incrementFarenheit();
		}
	}

	class DownListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model.decrementFarenheit();
		}
	}

	class DisplayListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double value = getDisplay();
			model.setFarenheit(value);
		}
	}
}
