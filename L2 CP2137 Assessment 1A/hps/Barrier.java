package hps;

/**
 * Type of world object that is used to mark the boundaries of the world. This
 * could potentially be used for placing obstacles inside the world, for example
 * to build a maze.
 * 
 * @author Kevan Buckley
 * @version 1.0, January 2008
 */

public class Barrier extends WorldObject {

  public Barrier(int row, int col, int orientation) {
    super(row, col, orientation);
  }

}
