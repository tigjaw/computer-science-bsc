package hps;

/**
 * This class is used to store a cell that an animal sees. It will normally
 * store details of a single visible cell including its location, relative
 * angle and distance and a reference to the object that resides in that cell.
 * 
 * @author Kevan Buckley
 * @version 1.0, January 2009
 */

public class EspiedCell {

  /**
   * The vertical position of the cell. 0 is the bottom row on the screen.
   */
  private int row;

  /**
   * The horizontal position of the cell. 0 is the leftmost column on the
   * screen.
   */
  private int column;

  /**
   * A reference to the object that resides in this cell. If the cell is empty
   * this variable will store null.
   */
  private WorldObject contents;

  /**
   * Relative angle to this cell measure relatively from the orientation of the
   * animal. 0 degrees is horizontally right and positive angles are measured
   * anticlockwise.
   */
  private double angle;

  /**
   * Euclidean distance from the animal that has seen this cell to this cell.
   */
  private double distance;

  /**
   * Constructor - see parameter entries.
   * 
   * @param row
   *          The vertical position of the cell. 0 is the bottom row on the
   *          screen.
   * @param column
   *          The horizontal position of the cell. 0 is the leftmost column on
   *          the screen.
   * @param cellContents
   *          A reference to the object that resides in this cell. If the cell
   *          is empty this variable will store null.
   * 
   * @param angleToCell
   *          Relative angle to this cell measure relatively from the
   *          orientation of the animal. 0 degrees is horizontally right and
   *          positive angles are measured anticlockwise.
   * @param distanceToCell
   *          Euclidean distance from the animal that has seen this cell to this
   *          cell.
   */
  public EspiedCell(int row, int column, WorldObject cellContents,
      double angleToCell, double distanceToCell) {
    this.row = row;
    this.column = column;
    this.contents = cellContents;
    this.angle = angleToCell;
    this.distance = distanceToCell;
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

  public WorldObject getContents() {
    return contents;
  }

  public double getAngle() {
    return angle;
  }

  public double getDistance() {
    return distance;
  }

}
