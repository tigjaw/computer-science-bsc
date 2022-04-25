package mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

public class SliderGUI implements Observer {
	private TemperatureModel model = null;
	private JScrollBar tempControl;

	public SliderGUI(TemperatureModel model) {
		model.attach(this);
		this.model = model;
		JFrame frame = new JFrame("Celsius");
		JPanel contentPane = new JPanel() {
			private static final long serialVersionUID = -8280876528418329454L;

			public Dimension getPreferredSize() {
				return new Dimension(200, 50);
			}
		};
		contentPane.setLayout(new BorderLayout());
		frame.setContentPane(contentPane);
		tempControl = new JScrollBar(JScrollBar.HORIZONTAL, 0, 10, -50, 160);
		contentPane.add(BorderLayout.CENTER, tempControl);

		tempControl.addAdjustmentListener(new SliderListener());
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.pack();
		frame.setVisible(true);
	}

	public void update(Subject t) {
		double temp = ((TemperatureModel) t).getCelsius();
		tempControl.setValue((int) temp);
	}

	class SliderListener implements AdjustmentListener {
		public void adjustmentValueChanged(AdjustmentEvent e) {
			model.setCelsius(tempControl.getValue());
		}
	}
}
