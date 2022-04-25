package hps;

import javax.swing.*;
import java.awt.*;

/**
 * Graphical view of the simulation. It is updated every time its stateChanged
 * method is called by the model. For this to happen you must ensure that the
 * main method registers the GUI as a listener with the model.
 * 
 * @author Kevan Buckley
 * @version 1.0, January 2008
 */

public class GUI implements ModelListener {
  protected JFrame frame;

  protected Container contentPane;

  protected WorldModel model;

  protected GraphicsArea graphicsArea;

  public GUI(WorldModel model) {
    this.model = model;
    graphicsArea = new GraphicsArea(model);
    frame = new JFrame("Hunter-Prey Simulation");
    contentPane = new JPanel(new BorderLayout());
    contentPane.add(graphicsArea, BorderLayout.CENTER);
    frame.setContentPane(contentPane);
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void stateChanged() {
    graphicsArea.repaint();
  }
}
