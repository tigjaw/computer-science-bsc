package hps;

import java.util.List;

/**
 * Can you build state machine based behaviour that can make this kind of animal a
 * successful predator.
 * @author Kevan Buckley
 * @version 1.0, January 2008
 */

public class Carnivore extends Animal {

  /**
   * enumeration State. The range of behavioural states that apply to the carnivore.
   * @param ROAMING
   * @param CHASE
   * @param MUNCHING
   * @param PANICKING
   * @param TRACKING
   */
  enum State {
    ROAMING, CHASE, MUNCHING, PANICKING, TRACKING
  };

  /**
   * enumeration LastSeen. The possible directions in which the carnivore last saw the
   * herbivore during a chase.
   * @param: LEFT
   * @param: RIGHT
   * @param: CENTRE
   * @param: NONE
   */
  enum LastSeen {
    LEFT, RIGHT, CENTRE, NONE
  };

  /**
   * currentState and previousState used to print out changes in state.
   */
  protected State    currentState      = State.ROAMING;
  /**
   * currentState and previousState used to print out changes in state.
   */
  protected State    previousState     = State.ROAMING;
  /**
   * ticksSinceVisible relates to the number of ticks since the carnivore lost sight of
   * the herbivore.
   */
  protected int      ticksSinceVisible = 0;
  /**
   * LastSeen lastSeen If carnivore loses sight of harbivore it was chasing lastSeen
   * records the direction the herbivore was seen.
   */
  protected LastSeen lastSeen          = LastSeen.NONE;

  /**
   * public Carnivore() constructor for carnivore.
   * @param world
   * @param row
   * @param col
   * @param orientation
   * @param health
   *          = maximum health of carnivore
   * @param visibilityRange
   *          = maximum distance visible
   * @param angleOfVisibility
   *          = field of view.
   */
  public Carnivore(WorldModel world, int row, int col, int orientation) {
    super(world, row, col, orientation);
    health = 750;
    visibilityRange = 20;
    angleOfVisibility = 50; // increased visibility range to improve chase chance
  }

  /**
   * public void behaviour() Main 'running' method, providing state behaviour switching.
   */
  public void behaviour() {
    if (isDead()) {
      return;
    }

    // Prints Changes in State
    if (currentState != previousState) {
      System.out.println("State has changed to: " + currentState);
      previousState = currentState;
    }
    /*
     * Sometimes the 'sysos' below will not print when they seemingly should do. This is
     * due to the fact that health is not just affected by the above health-- command.
     * Health is also affected by actions or behaviours in other methods such as
     * world.charge(). Therefore if the next tick should reduce health to exactly 800 but
     * the during that time another health reducing action is carried out, the health will
     * not equal exactly 800 upon returning to this method. These sysos can be removed
     * from commenting for testing purposes.
     */
    /*
     * if(health == 900) { System.out.println("health: " + health); } else { if(health ==
     * 800) { System.out.println("health: " + health); } else { if(health == 700) {
     * System.out.println("health: " + health); } else { if(health == 600) {
     * System.out.println("health: " + health); } else { if(health == 500) {
     * System.out.println("health: " + health); } else { if(health == 400) {
     * System.out.println("health: " + health); } else { if(health == 300) {
     * System.out.println("health: " + health); } else { if(health == 200) {
     * System.out.println("health: " + health); } else { if(health == 100) {
     * System.out.println("health: " + health); } else { if(health <= 50) {
     * System.out.println("health: " + health); } } } } } } } } } }
     */
    decreaseHealth(1);
    determineWhichCellsAreVisible();

    switch (currentState) {
    case ROAMING:
      roamingBehaviour();
      break;
    case CHASE:
      chasingBehaviour();
      break;
    case MUNCHING:
      munchingBehaviour();
      break;
    case PANICKING:
      panickingBehaviour();
      break;
    case TRACKING:
      trackingBehaviour();
      break;
    default:
      System.out.println("unexpected state reached");
    }

    // world.randomMove(this); // this makes the animal wander aimlessly
  }

  /**
   * protected void switchState(State) switches old state with previousState switches
   * currentState with newState parameter.
   * @param newState
   */
  protected void switchState(State newState) {
    previousState = currentState;
    currentState = newState;
    behaviour();
  }

  /** boolean justRotated. True if carnivore just rotated. */
  boolean justRotated = false;

  /**
   * roamingBehaviour() Contains roaming behaviour for the carnivore.
   */
  protected void roamingBehaviour() {
    ticksSinceVisible = 0;
    lastSeen = LastSeen.NONE;
    // System.out.println("roaming");
    if (isHerbivoreVisible()) {
      switchState(State.CHASE);
    } else {
      if (health < 100) {
        switchState(State.PANICKING);
      } else {
        if (isBarrierVisible()) {
          // System.out.println("seen a barrier:");
          // printCellList(visibleBarriers);
          avoidBarrier();
        }
        // System.out.println("seen nothing");
        if (world.isCellInFrontEmpty(this)) {
          world.randomMove(this);
        } else if (world.isCellInFrontACarnivore(this)) {
          world.randomMoveLR(this);
        } else if (world.isCellInFrontAPlant(this)) {
          world.randomMoveLR(this);
        }
      }
    }
  }

  /**
   * protected void chasingBehaviour() Contains carnivore's chasing behaviour having seen
   * a herbivore.
   */
  protected void chasingBehaviour() {
    // System.out.println("closing in");

    ticksSinceVisible = 0;
    if (world.isCellInFrontAHerbivore(this)) {
      switchState(State.MUNCHING);
    } else {
      if (isHerbivoreVisible()) {
        lastSeen = LastSeen.NONE;
        headTowardsFood();
      } else {
        if (lastSeen == LastSeen.NONE) {
          switchState(State.ROAMING);
        } else {
          switchState(State.TRACKING);
        }
      }
    }
  }

  /**
   * protected void headTowardFood() Contains carnivore's movement behaviour for heading
   * toward a visible herbivore. Called by chasingBehaviour() and trackingBehaviour().
   */
  protected void headTowardsFood() {
    // System.out.println("Heading toward food");
    if (world.isCellInFrontAHerbivore(this)) {
      switchState(State.MUNCHING);
    } else {
      // If this is a new chase.
      if (lastSeen == LastSeen.NONE) {
        // Produced isNearestHerbivoreDirectlyAhead() method in Animal class
        if (isNearestHerbivoreDirectlyAhead()) {
          // System.out.println("nearest herbivore is directly ahead");
          // System.out.println("angle is " + visibleHerbivores.get(0).getAngle());
          world.charge(this);
          System.out.println("herbivore was seen to the centre");
          lastSeen = LastSeen.CENTRE;
        } else {
          // Produced isNearestHerbivoreDirectlyAhead() method in Animal class
          if (isNearestHerbivoreToTheLeft()) {
            // System.out.println("herbivore was seen to the left");
            world.turnLeft(this);
            world.stepForward(this);
            world.turnRight(this);
            world.stepForward(this);
            lastSeen = LastSeen.LEFT;
          } else {
            // Produced isNearestHerbivoreDirectlyAhead() method in Animal class
            if (isNearestHerbivoreToTheRight()) {
              // System.out.println("herbivore was seen to the right");
              world.turnRight(this);
              world.stepForward(this);
              world.turnLeft(this);
              world.stepForward(this);
              lastSeen = LastSeen.RIGHT;
            }
          }
        }
      } else { // if this chase is continuing a previous lost chase
        if (lastSeen == LastSeen.CENTRE) { // if herbivore was last seen ahead
          world.randomMoveLR(this);
          // world.stepForward(this);
        } else {
          if (lastSeen == LastSeen.LEFT) { // if herbivore was last seen to the left
            if (justRotated == false) {
              world.turnLeft(this);
              world.stepForward(this);
              justRotated = true;
            } else {
              world.turnRight(this);
              world.stepForward(this);
              justRotated = false;
            }
          } else {
            if (lastSeen == LastSeen.RIGHT) { // if herbivore was last seen to the right
              if (justRotated == false) {
                world.turnRight(this);
                world.stepForward(this);
                justRotated = true;
              } else {
                world.turnLeft(this);
                world.stepForward(this);
                justRotated = false;
              }
            }
          }
        }
      }
    }
  }

  /**
   * protected void trackingBehaviour() Tells carnivore to track herbivore if lost sight
   * of herbivore during chase. Tracking only continues for five ticks since the herbivore
   * was last seen.
   */
  protected void trackingBehaviour() {
    if (ticksSinceVisible < 5) {
      ticksSinceVisible++;
    }
    if (isHerbivoreVisible()) {
      // ticksSinceVisible = 0;
      switchState(State.CHASE);
    } else {
      if (ticksSinceVisible == 5) {
        // ticksSinceVisible = 0;
        switchState(State.ROAMING);
      } else {
        headTowardsFood();
      }
    }
  }

  /**
   * protected void munchingBehaviour() Contains behaviour for 'eating' chased herbivore.
   */
  protected void munchingBehaviour() {
    lastSeen = LastSeen.NONE;
    // System.out.println("munching");
    if (world.isCellInFrontAHerbivore(this)) {
      /*
       * eat increases health by 10 points instead of 5. This is because if carnivore
       * charges to catch herbivore it spends 6 points to catch it so charging to eat
       * would have no benefit. Now charging to eat benefits by a profit of 4 health
       * points.
       */
      world.eat(this);
    } else if (isHerbivoreVisible()) {
      switchState(State.CHASE);
    } else {
      switchState(State.ROAMING);
    }
  }

  /**
   * protected void panickingBehaviour() Contains behaviour for panicking when health
   * drops below 100. The carnivore panics in this way to conserve energy.
   */
  protected void panickingBehaviour() {
    if (world.isCellInFrontAHerbivore(this)) {
      switchState(State.MUNCHING);
    } else if (isHerbivoreVisible()) {
      switchState(State.CHASE);
    } else {
      if (Math.random() < 0.90) {

      } else if (Math.random() < 0.5) {
        world.randomMoveLR(this);
      } else {
        world.stepForward(this);
      }
    }
  }

  /**
   * protected void avoidBarrier() Tells carnivore how to avoid a barrier.
   */
  protected void avoidBarrier() {
    if (isThereABarrierDirectlyAhead()) {
      // System.out.println("barrier is ahead, so turning a random direction");
      // Produced this randomMoveLR method in WorldModel
      world.randomMoveLR(this);
      return;
    } else {
      world.stepForward(this);
    }
  }

  /**
   * protected void printCellList() Prints list of cells.
   * @param cells
   */
  protected void printCellList(List<EspiedCell> cells) {
    for (EspiedCell cell : cells) {
      System.out.println("" + cell.getRow() + "," + cell.getColumn() + ") d=" + cell.getDistance()
          + " angle=" + cell.getAngle());
    }
  }

}