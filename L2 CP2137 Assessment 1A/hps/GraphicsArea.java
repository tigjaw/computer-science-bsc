package hps;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.List;
@SuppressWarnings("serial")
/**
 * Draw the current state of the world on an image, then display the image on
 * the screen. This class does not make use of the Java 2D API or functionality
 * found in swing components. It is a legacy system and should not be considered
 * good practice - you should use the 2D API and swing in future developments.
 * 
 * @author Kevan Buckley
 * @version 2.0, January 2009
 */

public class GraphicsArea extends Canvas {

  /**
   * If this is true with cells that are visible to each animal are shown with
   * circles. If this is fale they are not drawn.
   */
  public static final boolean SIGHT_IS_VISIBLE = true;

  /**
   * Contains the objects in this simulation.
   */
  protected WorldModel model;

  /**
   * The number of pixels used to display each cell of the image.
   */
  protected static final int PIXELS_PER_CELL = 7;

  /**
   * The width of the area that the world will be displayed in, measured in
   * pixels.
   */
  protected int width;

  /**
   * The height of the area that the world will be displayed in, measured in
   * pixels.
   */
  protected int height;

  /**
   * Offscreen image used to improve graphics performance.
   */

  protected BufferedImage buffer;

  public GraphicsArea(WorldModel model) {
    super();
    this.model = model;
    width = model.getSize() * PIXELS_PER_CELL;
    height = model.getSize() * PIXELS_PER_CELL;
    buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  }

  /**
   * This method is called by the layout manager of the container of this
   * component to find out how big this component is.
   */

  public Dimension getPreferredSize() {
    return new Dimension(width, height);
  }

  /**
   * This method is overriden to avoid pointless screen clearing operations and
   * eliminate flicker.
   */
  public void update(Graphics g) {
    paint(g);
  }

  /**
   * Draw on an offscreen image then display it on the screen.
   */

  public void paint(Graphics graphics) {
    Graphics2D g = (Graphics2D) buffer.getGraphics();
    g.transform(AffineTransform.getScaleInstance(1, -1));
    g.transform(AffineTransform.getTranslateInstance(0, -height));
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, 700, 700);

    WorldObject[][] map = model.getMap();
    for (int row = 0; row < map.length; row++) {
      int y = row * PIXELS_PER_CELL;
      for (int col = 0; col < map[0].length; col++) {
        int x = col * PIXELS_PER_CELL;
        if (map[row][col] instanceof Barrier) {
          g.setColor(Color.BLACK);
          g.drawLine(x, y, x + PIXELS_PER_CELL, y + PIXELS_PER_CELL);
        }
        if (map[row][col] instanceof Herbivore) {
          g.setColor(Color.BLUE);
          drawAnimal(g, x, y, map[row][col].getOrientation());
        }
        if (map[row][col] instanceof Carnivore) {
          g.setColor(Color.RED);
          drawAnimal(g, x, y, map[row][col].getOrientation());
        }
        if (map[row][col] instanceof Plant) {
          g.setColor(Color.GREEN);
          g.fillRect(x, y, PIXELS_PER_CELL, PIXELS_PER_CELL);
        }
      }
    }

    if (SIGHT_IS_VISIBLE) {
      highlightAllVisibleCells(g);
    }
    g.dispose();
    graphics.drawImage(buffer, 0, 0, null);
  }

  /**
   * Go though all animals and draw their visible areas.
   * 
   * @param g
   *          graphics context to be drawn on
   */
  private void highlightAllVisibleCells(Graphics2D g) {
    for (Animal a : model.getAnimals()) {
      highlightVisibleCellsForOneAnimal(g, a);
    }
  }

  /**
   * Draw the visible areas for one animal
   * 
   * @param g
   *          graphics context to be drawn on
   * @param a
   *          the animal that will provide the cell data
   */
  private void highlightVisibleCellsForOneAnimal(Graphics g, Animal a) {
    if (a instanceof Herbivore) {
      g.setColor(Color.BLUE);
    } else {
      g.setColor(Color.RED);
    }

    // a.refreshVision(); TODO check that we don't still need this

    List<EspiedCell> cells = a.getVisibleCells();
    if(cells!=null){
      for (EspiedCell cell : cells) {
        g.drawOval(cell.getColumn() * PIXELS_PER_CELL, cell.getRow()
            * PIXELS_PER_CELL, PIXELS_PER_CELL, PIXELS_PER_CELL);
      }
    }
  }

  /**
   * Draws an arrow to show the location and orientation of an animal.
   */

  private void drawAnimal(Graphics g, int x, int y, int orientation) {
    int s2 = PIXELS_PER_CELL / 2;
    switch (orientation) {
    case 0:
      g.drawLine(x + s2, y, x + PIXELS_PER_CELL, y + s2);
      g.drawLine(x + s2, y + PIXELS_PER_CELL, x + PIXELS_PER_CELL, y + s2);
      g.drawLine(x, y + s2, x + PIXELS_PER_CELL, y + s2);
      break;
    case 270:
      g.drawLine(x, y + s2, x + s2, y);
      g.drawLine(x + PIXELS_PER_CELL, y + s2, x + s2, y);
      g.drawLine(x + s2, y + PIXELS_PER_CELL, x + s2, y);
      break;
    case 180:
      g.drawLine(x, y + s2, x + s2, y);
      g.drawLine(x, y + s2, x + s2, y + PIXELS_PER_CELL);
      g.drawLine(x, y + s2, x + PIXELS_PER_CELL, y + s2);
      break;
    case 90:
      g.drawLine(x, y + s2, x + s2, y + PIXELS_PER_CELL);
      g.drawLine(x + s2, y + PIXELS_PER_CELL, x + PIXELS_PER_CELL, y + s2);
      g.drawLine(x + s2, y, x + s2, y + PIXELS_PER_CELL);
      break;

    }
  }
}
