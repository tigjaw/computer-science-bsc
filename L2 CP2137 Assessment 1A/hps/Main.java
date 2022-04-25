package hps;

/**
 * Run this class to start the simulation. Uses the Observer design pattern to
 * allow the GUI and simulation model to
 * 
 * @author Kevan Buckley
 * @version 1.0, January 2008
 */

public class Main {

  public void run() {
    WorldModel model = new WorldModel();
    GUI gui = new GUI(model);
    model.addModelListener(gui);
    int iteration = 0;
    boolean finished = false;

    while (!finished) {
      iteration++;
      // System.out.println("iteration " + iteration);
      model.tick();
      pause();
      finished = iteration >= 1000;
    }
    model.printSurvivors();
  }

  /**
   * This method causes a time delay between ticks. If you want to change the
   * speed that the simulation is animated, change the parameter of the call to
   * the sleep method. For a 1 second delay call sleep with 1000. For a half
   * second delay call sleep with 500 etc.
   * 
   */

  private void pause() {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new Main().run();
  }

}
