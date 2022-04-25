package hps;

/**
 * Superclass of all entities in the simulation. The code in this class
 * maintains details of the location and orientation of the object.
 * 
 * @author Kevan Buckley
 * @version 1.0, January 2008
 */

public abstract class WorldObject {
  private int row;

  private int column;

  private int orientation;

  public WorldObject(int row, int col, int orientation) {
    this.row = row;
    this.column = col;
    this.orientation = orientation;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public int getOrientation() {
    return orientation;
  }

  public void setOrientation(int orientation) {
    while (orientation < 0) {
      orientation += 360;
    }
    this.orientation = orientation % 360;
  }

}
