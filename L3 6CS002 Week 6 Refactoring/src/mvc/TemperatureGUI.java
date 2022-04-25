package mvc;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;

abstract class TemperatureGUI implements Observer {
	protected TemperatureModel model;
	private JButton upButton;
	private JButton downButton;
	private JTextField textDisplay;

	TemperatureGUI(String label, TemperatureModel model) {
		this.model = model;
		model.attach(this);

		JFrame frame = new JFrame(label);
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		frame.setContentPane(contentPane);
		contentPane.add(BorderLayout.NORTH, new JLabel(label));
		textDisplay = new JTextField();
		textDisplay.setEnabled(false);
		contentPane.add(BorderLayout.CENTER, textDisplay);
		JPanel buttonsPanel = new JPanel();
		upButton = new JButton("Raise");
		buttonsPanel.add(upButton);
		downButton = new JButton("Lower");
		buttonsPanel.add(downButton);
		contentPane.add(BorderLayout.SOUTH, buttonsPanel);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				e.getWindow().setVisible(false);
				System.exit(0);
			}
		});
		frame.pack();
		frame.setVisible(true);
	}

	public void setDisplay(String s) {
		textDisplay.setText(s);
	}

	public double getDisplay() {
		double result = 0.0;
		try {
			result = Double.valueOf(textDisplay.getText()).doubleValue();
		} catch (NumberFormatException e) {
		}
		return result;
	}

	public void addUpListener(ActionListener a) {
		upButton.addActionListener(a);
	}

	public void addDownListener(ActionListener a) {
		downButton.addActionListener(a);
	}

	public String round(double value) {
		NumberFormat f = DecimalFormat.getInstance();
		f.setMinimumFractionDigits(2);
		return f.format(value);
	}
}
