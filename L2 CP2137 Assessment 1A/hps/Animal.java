package hps;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Superclass of all animal classes in the simulation.
 * 
 * @author Kevan Buckley
 * @version 1.0, January 2008
 */

public abstract class Animal extends WorldObject {
  /**
   * When this reaches zero the animal is dead
   */
  protected int health = 500;

  /**
   * Reference to the world model
   */
  protected WorldModel world;

  /**
   * All cells the animal can see. Sorted into nearest first order.
   */

  protected List<EspiedCell> visibleCells;

  /**
   * All cells the cells containing carnivores animal can see. Sorted into
   * nearest first order.
   */

  protected List<EspiedCell> visibleCarnivores;

  /**
   * All cells containing herbivores the animal can see. Sorted into nearest
   * first order.
   */

  protected List<EspiedCell> visibleHerbivores;

  /**
   * All cells containing plants that the animal can see. Sorted into nearest
   * first order.
   */

  protected List<EspiedCell> visiblePlants;

  /**
   * All cells containing barriers that the animal can see. Sorted into nearest
   * first order.
   */

  protected List<EspiedCell> visibleBarriers;

  /**
   * Measured from the direction that animal is facing, this is the angle in
   * either direction that the animal can see.
   */

  protected int angleOfVisibility;

  /**
   * The distance (in cells) that the animal can see.
   */
  protected int visibilityRange;

  public Animal(WorldModel world, int row, int col, int orientation) {
    super(row, col, orientation);
    this.world = world;
    visibilityRange = 20;
    angleOfVisibility = 10;
  }

  /**
   * 
   * @return all the cells that this animal can see
   */
  public List<EspiedCell> getVisibleCells() {
    return visibleCells;
  }

  /**
   * Increases the animal's health level
   * 
   * @param amount
   *          the amount to alter the health by
   */
  public void increaseHealth(int amount) {
    health = health + amount;
  }

  /**
   * Reduces the animal's health level
   * 
   * @param amount
   *          the amount to alter the health by
   */
  public void decreaseHealth(int amount) {
    health = health - amount;
    if (isDead()) {
      System.out.println("animal died of starvation (" + getRow() + ", "
          + getColumn() + ")");
    }
  }

  /**
   * 
   * @return true if the animal is dead, else false
   */

  public boolean isDead() {
    return health <= 0;
  }

  /**
   * Override this method to give the animal behaviour
   */

  public abstract void behaviour();

  public void setHealth(int health) {
    this.health = health;
  }

  public int getHealth() {
    return health;
  }

  /**
   * This method MUST be called before handling any behaviour so that the
   * visibility lists are up to date. This method maintains all visibility
   * lists. These are instance variables of type "List" that start with the
   * world "visible". See descriptions before their declarations for details.
   */

  protected void determineWhichCellsAreVisible() {
    visibleCells = new LinkedList<EspiedCell>();
    visibleCarnivores = new LinkedList<EspiedCell>();
    visibleHerbivores = new LinkedList<EspiedCell>();
    visiblePlants = new LinkedList<EspiedCell>();
    visibleBarriers = new LinkedList<EspiedCell>();

    for (int r = getRow() - visibilityRange; r <= getRow() + visibilityRange; r++) {
      for (int c = getColumn() - visibilityRange; c <= getColumn()
          + visibilityRange; c++) {
        boolean rowIsValid = r >= 0 && r < world.getSize();
        boolean colIsValid = c >= 0 && c < world.getSize();
        boolean pointsAreNotSame = c != getColumn() || r != getRow();

        if (rowIsValid && colIsValid && pointsAreNotSame) {
          double dx = getColumn() - c;
          double dy = getRow() - r;
          double eyeToPointDistanceSquared = dx * dx + dy * dy;

          if (eyeToPointDistanceSquared == 0) {
            continue;
          }

          double eyeToPointDistance = Math.sqrt(eyeToPointDistanceSquared);

          if (eyeToPointDistance > visibilityRange) {
            continue;
          }

          double beta = Trig.datan2((r - getRow()), c - getColumn());
          double theta = -Trig.normaliseAngle(getOrientation() - beta);

          if (Math.abs(theta) > angleOfVisibility) {
            continue;
          }

          EspiedCell cell = new EspiedCell(r, c, world.getWorldObject(r, c),
              theta, eyeToPointDistance);
          visibleCells.add(cell);
          if (cell.getContents() == null) {

          } else {
            if (cell.getContents() instanceof Carnivore) {
              visibleCarnivores.add(cell);

            } else {
              if (cell.getContents() instanceof Herbivore) {
                visibleHerbivores.add(cell);

              } else {
                if (cell.getContents() instanceof Plant) {
                  visiblePlants.add(cell);

                } else {
                  if (cell.getContents() instanceof Barrier) {
                    visibleBarriers.add(cell);
                  }
                }
              }
            }
          }
        }
      }
    }

    Comparator<EspiedCell> espiedCellDistanceComparator = new Comparator<EspiedCell>() {
      public int compare(EspiedCell cell1, EspiedCell cell2) {
        if (cell1.getDistance() == cell2.getDistance()) {
          return 0;
        }
        if (cell1.getDistance() < cell2.getDistance()) {
          return -1;
        }
        return 1;
      }
    };

    Collections.sort(visibleCells, espiedCellDistanceComparator);
    Collections.sort(visibleHerbivores, espiedCellDistanceComparator);
    Collections.sort(visibleCarnivores, espiedCellDistanceComparator);
    Collections.sort(visiblePlants, espiedCellDistanceComparator);
    Collections.sort(visibleBarriers, espiedCellDistanceComparator);
  }

  /**
   * Kill this animal by setting its health to zero.
   */

  public void kill() {
    health = 0;
  }

  /**
   * Checks if this animal can see a carnivore
   * 
   * @return true if a carnivore is visible, else returns false.
   */

  public boolean isCarnivoreVisible() {
    return visibleCarnivores.size() > 0;
  }

  /**
   * Checks if this animal can see a barrier
   * 
   * @return true if a barrier is visible, else returns false.
   */

  public boolean isBarrierVisible() {
    return visibleBarriers.size() > 0;
  }

  /**
   * Checks if this animal can see a plant
   * 
   * @return true if a plant is visible, else returns false.
   */

  public boolean isPlantVisible() {
    return visiblePlants.size() > 0;
  }

  /**
   * Checks if this animal can see a herbivore
   * 
   * @return true if a herbivore is visible, else returns false.
   */
  public boolean isHerbivoreVisible() {
    return visibleHerbivores.size() > 0;
  }

  /**
   * Returns true if the nearest plant to this animal is to the left of the
   * animal. As there may be more than one plant at the same distance away, all
   * plants with the minimum distance are checked.
   * 
   * @return true if the nearest plant is to the left, else false.
   */

  public boolean isNearestPlantToTheLeft() {
    double distance = 99999;
    for (EspiedCell cell : visiblePlants) {
      if (cell.getDistance() > distance) {
        return false;
      }
      distance = cell.getDistance();
      if (cell.getAngle() > 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if the nearest plant to this animal is to the right of the
   * animal. As there may be more than one plant at the same distance away, all
   * plants with the minimum distance are checked.
   * 
   * @return true if the nearest plant is to the right, else false.
   */

  public boolean isNearestPlantToTheRight() {
    double distance = 99999;
    for (EspiedCell cell : visiblePlants) {
      if (cell.getDistance() > distance) {
        return false;
      }
      distance = cell.getDistance();
      if (cell.getAngle() < 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if the nearest plant to this animal is in front of the animal.
   * As there may be more than one plant at the same distance away, all plants
   * with the minimum distance are checked.
   * 
   * @return true if the nearest plant is towards the front, else false.
   */

  public boolean isNearestPlantDirectlyAhead() {
    double distance = 99999;
    for (EspiedCell cell : visiblePlants) {
      if (cell.getDistance() > distance) {
        return false;
      }
      distance = cell.getDistance();
      if (Math.abs(cell.getAngle()) < 20) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Returns true if the nearest herbivore to this animal is to the left of the
   * animal. As there may be more than one herbivore at the same distance away,
   * all herbivore with the minimum distance are checked.
   * 
   * @return true if the nearest herbivore is to the left, else false.
   */

  public boolean isNearestHerbivoreToTheLeft() {
    double distance = 99999;
    for (EspiedCell cell : visibleHerbivores) {
      if (cell.getDistance() > distance) {
        return false;
      }
      distance = cell.getDistance();
      if (cell.getAngle() > 0) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Returns true if the nearest herbivore to this animal is to the right of the
   * animal. As there may be more than one herbivore at the same distance away, all
   * herbivore with the minimum distance are checked.
   * 
   * @return true if the nearest herbivore is to the right, else false.
   */

  public boolean isNearestHerbivoreToTheRight() {
    double distance = 99999;
    for (EspiedCell cell : visibleHerbivores) {
      if (cell.getDistance() > distance) {
        return false;
      }
      distance = cell.getDistance();
      if (cell.getAngle() < 0) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Returns true if the nearest plant to this animal is in front of the animal.
   * As there may be more than one plant at the same distance away, all plants
   * with the minimum distance are checked.
   * 
   * @return true if the nearest plant is towards the front, else false.
   */

  public boolean isNearestHerbivoreDirectlyAhead() {
    double distance = 99999;
    for (EspiedCell cell : visibleHerbivores) {
      if (cell.getDistance() > distance) {
        return false;
      }
      distance = cell.getDistance();
      if (Math.abs(cell.getAngle()) < 20) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if the nearest carnivore to this animal is to the left of the
   * animal. As there may be more than one carnivore at the same distance away,
   * all carnivores with the minimum distance are checked.
   * 
   * @return true if the nearest carnivore is to the left, else false.
   */

  public boolean isNearestCarnivoreToTheLeft() {
    double distance = 99999;
    for (EspiedCell cell : visibleCarnivores) {
      if (cell.getDistance() > distance) {
        return false;
      }
      distance = cell.getDistance();
      if (cell.getAngle() > 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if the nearest carnivore to this animal is to the right of the
   * animal. As there may be more than one carnivore at the same distance away,
   * all carnivores with the minimum distance are checked.
   * 
   * @return true if the nearest carnivore is to the right, else false.
   */

  public boolean isNearestCarnivoreToTheRight() {
    double distance = 99999;
    for (EspiedCell cell : visibleCarnivores) {
      if (cell.getDistance() > distance) {
        return false;
      }
      distance = cell.getDistance();
      if (cell.getAngle() < 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if the nearest barrier to this animal is in front of the
   * animal. As there may be more than one plant at the same distance away, all
   * barriers with the minimum distance are checked.
   * 
   * @return true if the nearest barrier towards the front, else false.
   */
  public boolean isNearestCarnivoreDirectlyAhead() {
    double distance = 99999;
    for (EspiedCell cell : visibleCarnivores) {
      if (cell.getDistance() > distance) {
        return false;
      }
      distance = cell.getDistance();
      if (Math.abs(cell.getAngle()) < 20) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if the nearest barrier to this animal is to the left of the
   * animal. As there may be more than one barrier at the same distance away,
   * all barriers with the minimum distance are checked.
   * 
   * @return true if the nearest barrier towards the left, else false.
   */

  public boolean isNearestBarrierToTheLeft() {
    double distance = 99999;
    for (EspiedCell cell : visibleBarriers) {
      if (cell.getDistance() > distance) {
        return false;
      }
      distance = cell.getDistance();
      if (cell.getAngle() > 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if the nearest barrier to this animal is to the right of the
   * animal. As there may be more than one barrier at the same distance away,
   * all barriers with the minimum distance are checked.
   * 
   * @return true if the nearest barrier towards the right, else false.
   */

  public boolean isNearestBarrierToTheRight() {
    double distance = 99999;
    for (EspiedCell cell : visibleBarriers) {
      if (cell.getDistance() > distance) {
        return false;
      }
      distance = cell.getDistance();
      if (cell.getAngle() < 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if the nearest barrier to this animal is in front of the
   * animal. As there may be more than one barrier at the same distance away,
   * all barriers with the minimum distance are checked.
   * 
   * @return true if the nearest barrier towards the front, else false.
   */
  public boolean isNearestBarrierDirectlyAhead() {
    double distance = 99999;
    for (EspiedCell cell : visibleBarriers) {
      if (cell.getDistance() > distance) {
        return false;
      }
      distance = cell.getDistance();
      if (cell.getAngle() < 20) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check if the animal can see a barrier straight in front. Note that unlike
   * other methods of this type this method strictly requires the barrier to be
   * at zero degree in front as opposed to almost in front which is used by
   * other methods.
   * 
   * @returns true if there is a barrier within sight in a straight line in
   *          front of the animal.
   */
  public boolean isThereABarrierDirectlyAhead() {
    for (EspiedCell cell : visibleBarriers) {
      if (cell.getAngle() == 0) {
        return true;
      }
    }
    return false;
  }
}
