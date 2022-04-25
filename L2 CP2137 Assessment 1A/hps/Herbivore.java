package hps;

import java.util.List;

/**
 * An animal that eats plants and is hunted by carnivores. Its behaviour is
 * controlled by a state machine consisting of 3 states, roaming, munching and
 * panicking. The animal roams until it finds food. It then uses a simple
 * strategy to stay within clusters of plants and eat them (the munching state).
 * If at any time a predator comes within range, the alert method is called and
 * the animal moves into the panic state in which it aims to sprint away.
 * 
 * @author Kevan Buckley
 * @version 2.0, January 2009
 */

public class Herbivore extends Animal {

  enum State {
    ROAMING, CLOSING_IN_ON_FOOD, MUNCHING, PANICKING
  };

  protected State currentState = State.ROAMING;

  protected int ticksSinceMeal = 0;
  protected int ticksSinceAlert = 0;

  public Herbivore(WorldModel world, int row, int col, int orientation) {
    super(world, row, col, orientation);
    visibilityRange = 5;
    angleOfVisibility = 130;
  }

  private State previousState = State.ROAMING;  

  public void behaviour() {
    if (isDead()) {
      return;
    }
    determineWhichCellsAreVisible();
    
    if(previousState!=currentState){
      //System.out.println(currentState);
    }
    previousState = currentState;
    
    health--;
    ticksSinceMeal++;
    ticksSinceAlert++;

    switch (currentState) {
    case ROAMING:
      roamingBehaviour();
      break;
    case CLOSING_IN_ON_FOOD:
      closingInBehaviour();
      break;
    case MUNCHING:
      munchingBehaviour();
      break;
    case PANICKING:
      panickingBehaviour();
      break;
    default:
      System.out.println("unexpected state reached");
    }
  }

  protected void switchState(State newState) {
    currentState = newState;
    behaviour();
  }

  boolean justRotated = false;

  protected void closingInBehaviour() {
    // System.out.println("closing in");
    if (isCarnivoreVisible()) {
      ticksSinceAlert = 0;
      switchState(State.PANICKING);
    } else {
      if (world.isCellInFrontAPlant(this)) {
        switchState(State.MUNCHING);
      } else {
        if (!isPlantVisible()) {
          switchState(State.ROAMING);
        } else {
          // System.out.println("seen a plant:");
          // printCellList(visiblePlants);
          headTowardsFood();
        }
      }
    }
  }

  protected void headTowardsFood() {
    if (world.isCellInFrontAPlant(this)) {
      switchState(State.MUNCHING);
    } else {
      if (isNearestPlantDirectlyAhead()) {
        // System.out.println("nearest plant is directly ahead");
        // System.out.println("angle is " + visiblePlants.get(0).getAngle());
        world.stepForward(this);
      } else {
        if (isNearestPlantToTheLeft()) {
          // System.out.println("nearest plant is left");
          // System.out.println("angle is " + visiblePlants.get(0).getAngle());
          if (justRotated) {
            world.stepForward(this);
            justRotated = false;
          } else {
            world.turnLeft(this);
            justRotated = true;
          }
        } else {
          if (isNearestPlantToTheRight()) {
            // System.out.println("nearest plant is right");
            // System.out.println("angle is " +
            // visiblePlants.get(0).getAngle());
            if (justRotated) {
              world.stepForward(this);
              justRotated = false;
            } else {
              world.turnRight(this);
              justRotated = true;
            }
          }
        }
      }
    }
  }

  protected void munchingBehaviour() {
    // System.out.println("munching");
    if (isCarnivoreVisible()) {
      ticksSinceAlert = 0;
      switchState(State.PANICKING);
    } else {
      if (world.isCellInFrontAPlant(this)) {
        world.eat(this);
      } else {
        switchState(State.ROAMING);
      }
    }
  }

  protected void panickingBehaviour() {
    // System.out.println("panicking");
    if (isCarnivoreVisible()) {
      ticksSinceAlert = 0;
      runAway();
    } else {
      ticksSinceAlert++;
      if (ticksSinceAlert >= 3) {
        switchState(State.ROAMING);
      } else {
        runAway();
      }
    }
  }

  private void runAway() {
    if (isNearestCarnivoreDirectlyAhead()) {
      world.turnLeft(this);
    } else {
      world.stampede(this);
    }
  }

  protected void roamingBehaviour() {
    // System.out.println("roaming");

    if (isCarnivoreVisible()) {
      ticksSinceAlert = 0;
      switchState(State.PANICKING);
    } else {
      if (isPlantVisible()) {
        switchState(State.CLOSING_IN_ON_FOOD);
      } else {
        if (isBarrierVisible()) {
          // System.out.println("seen a barrier:");
          // printCellList(visibleBarriers);
          avoidBarrier();
        } else {
          // System.out.println("seen nothing");
          world.stepForward(this);
        }
      }
    }

  }

  protected void avoidBarrier() {
    if (isThereABarrierDirectlyAhead()) {
      // System.out.println("barrier is ahead, so turning right");
      world.turnRight(this);
      return;
    } else {
      world.stepForward(this);
    }
  }

  protected void printCellList(List<EspiedCell> cells) {
    for (EspiedCell cell : cells) {
      System.out.println("" + cell.getRow() + "," + cell.getColumn() + ") d="
          + cell.getDistance() + " angle=" + cell.getAngle());
    }
  }

}
