package mvc;

public class Main {
	public static void main(String args[]) {
		TemperatureModel model = new TemperatureModel();
		new FarenheitGUI(model);
		new CelsiusGUI(model);
		new SliderGUI(model);
	}
}
