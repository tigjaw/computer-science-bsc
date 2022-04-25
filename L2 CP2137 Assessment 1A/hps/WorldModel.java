package hps;

import java.util.*;

/**
 * Model that underpins the simulation. Keeps track of the map, the objects on
 * it, and provides methods for manipulating objects on the map.
 * 
 * @author Kevan Buckley
 * @version 2.0, January 2009
 */

public class WorldModel {

  /**
   * Time is split up into discrete ticks. This variable store the current tick
   * number.
   */
  private int time;

  /**
   * A the grid squares on the map
   */

  private WorldObject[][] map;

  /**
   * A list of user interfaces that will be notified at the end of each time
   * tick.
   */
  private List<ModelListener> modelListeners;

  /**
   * All the animals in the world including, but hunters and prey.
   */
  private List<Animal> animals;

  /**
   * All the plants in the world.
   */
  private List<Plant> plants;

  /**
   * The number of cells wide and high the world is.
   */
  public static final int WORLD_SIZE = 100;

  /**
   * The number of plants the simulation starts with - more will grow.
   */
  public static final int NUMBER_OF_INITIAL_PLANTS = 5;

  /**
   * Maximum number of plants in the simulation. When this number is reached no
   * plants will grow until some have been eaten.
   */
  public static final int MAX_NUMBER_OF_PLANTS = 750;

  /**
   * The chance that a seed will actually grow a plant.
   */
  public static final double SEEDING_PROBABILITY = 0.1;

  /**
   * The initial number of herbivores in the simulation. This number is not
   * maintained, i.e. when animals die they are not replaced.
   */
  public static final int NUMBER_OF_HERBIVORES = 5;

  /**
   * The initial number of carnivores in the simulation. This number is not
   * maintained, i.e. when animals die they are not replaced.
   */
  public static final int NUMBER_OF_CARNIVORES = 2;

  /**
   * A sprint consists of moving this number of steps in one time tick.
   */
  // TODO this is probably not needed
  // public static final int NUMBER_OF_SPRINT_STEPS = 4;
  /**
   * The distance that an animal may see in front of itself
   */
  // TODO not needed
  // public static final int VISIBILITY_DISTANCE = 4;
  /**
   * If a predator comes within this distance of the prey, they prey is
   * notified/alerted.
   */
  // TODO not needed
  public static final int ALERT_DISTANCE = 5;

  /**
   * The number of plants currently in existence. Plants will be grown each tick
   * until the maximum number of plants is reached. When plants are eaten new
   * one will be grown until the maximum is reached again.
   */

  private int numberOfPlants = 0;

  public WorldModel() {
    this.time = 0;
    setUpWorld();
    setUpPlants();
    setUpAnimals();
  }

  /**
   * Get a reference to the map. This is dangerous should really return a deep
   * copy.
   * 
   * @return a reference to the map
   */
  public WorldObject[][] getMap() {
    return map;
  }

  /**
   * Constructs predators and prey and places them at random empty locations in
   * the map.
   */
  private void setUpAnimals() {
    animals = new LinkedList<Animal>();
    for (int i = 0; i < NUMBER_OF_CARNIVORES; i++) {
      int r, c, o;
      do {
        r = (int) (Math.random() * WORLD_SIZE);
        c = (int) (Math.random() * WORLD_SIZE);
        o = 90 * ((int) (Math.random() * 4));
      } while (map[r][c] != null);
      Carnivore carnivore = new Carnivore(this, r, c, o);
      map[r][c] = carnivore;
      animals.add(carnivore);
    }
    for (int i = 0; i < NUMBER_OF_HERBIVORES; i++) {
      int r, c, o;
      do {
        r = (int) (Math.random() * WORLD_SIZE);
        c = (int) (Math.random() * WORLD_SIZE);
        o = 90 * ((int) (Math.random() * 4));
      } while (map[r][c] != null);
      Herbivore herbivore = new Herbivore(this, r, c, o);
      map[r][c] = herbivore;
      animals.add(herbivore);
    }
  }

  /**
   * Place a number of initial plants that will form the source of plant
   * clusters as plants are grown each tick.
   * 
   */
  private void setUpPlants() {
    numberOfPlants = 0;
    plants = new LinkedList<Plant>();
    for (int i = 0; i < NUMBER_OF_INITIAL_PLANTS; i++) {
      if (numberOfPlants >= MAX_NUMBER_OF_PLANTS)
        break;
      int r, c;
      do {
        r = (int) (Math.random() * WORLD_SIZE);
        c = (int) (Math.random() * WORLD_SIZE);
      } while (map[r][c] != null);
      Plant plant = new Plant(r, c);
      map[r][c] = plant;
      plants.add(plant);
    }
  }

  /**
   * This method is call each tick to possibly generate new plants in the
   * environment. Each existing plant is taken in turn, then each empty adjacent
   * location to it is considered for growing a new plant in. The plant is grown
   * if the maximum number of plants has not been reached and if the random
   * number that is generated is below the specified theshold. i.e. random
   * factors determine where and when plants are grown, but are always grown
   * near to existing clusters.
   */

  private void growPlants() {
    List<Plant> newPlants = new LinkedList<Plant>();
    for (Plant p : plants) {
      int r = p.getRow();
      int c = p.getColumn();
      if (numberOfPlants >= MAX_NUMBER_OF_PLANTS)
        break;
      seedPlant(newPlants, r, c + 1);
      seedPlant(newPlants, r, c - 1);
      seedPlant(newPlants, r + 1, c + 1);
      seedPlant(newPlants, r + 1, c - 1);
    }
    plants.addAll(newPlants);
  }

  /**
   * Generate a plant at the specified location iff a randomly generated number
   * is below the seeding probability threshold and if the maximum number of
   * plants has not been reached.
   */

  private void seedPlant(List<Plant> newPlants, int r, int c) {
    if (numberOfPlants >= MAX_NUMBER_OF_PLANTS)
      return;
    if (map[r][c] == null && Math.random() < SEEDING_PROBABILITY) {
      Plant plant = new Plant(r, c);
      map[r][c] = plant;
      newPlants.add(plant);
      numberOfPlants++;
    }
  }

  /**
   * Create the map data structure and place barrier cells around the edges to
   * ensure that animals cannot escape off the sides of the simulation.
   */

  private void setUpWorld() {
    this.map = new WorldObject[WORLD_SIZE][WORLD_SIZE];
    createBoundaries();
  }

  /**
   * Place barrier cells around the edges of the map.
   */

  private void createBoundaries() {
    for (int row = 0; row < WORLD_SIZE; row++) {
      map[row][0] = new Barrier(row, 0, 90);
      map[row][WORLD_SIZE - 1] = new Barrier(row, WORLD_SIZE - 1, 270);
    }

    for (int col = 0; col < WORLD_SIZE; col++) {
      map[0][col] = new Barrier(0, col, 0);
      map[WORLD_SIZE - 1][col] = new Barrier(WORLD_SIZE - 1, col, 0);
    }
  }

  /**
   * Perform updates to the simulation for the next time tick.
   * 
   */

  public void tick() {
    time++;
    growPlants();
    performAnimalBehaviour();
    eliminateDeadAnimals();
    notifyModelListeners();
  }

  /**
   * Cycle through all the animals and delete the dead ones from the map.
   */

  private void eliminateDeadAnimals() {
    List<Animal> dead = new LinkedList<Animal>();
    for (Animal animal : animals) {
      if (animal.isDead()) {
        dead.add(animal);
        map[animal.getRow()][animal.getColumn()] = null;
        // System.out.println("Animal is dead");
      }
    }
    animals.removeAll(dead);
  }

  /**
   * Iterate through all animals and call their behaviour methods.
   * 
   */
  private void performAnimalBehaviour() {
    for (Animal animal : animals) {
      animal.behaviour();
    }
  }

  /**
   * Work out the coordinates of the cell that is the specified distance in
   * front of the animal and return its row number. TODO delete me
   */

  public int rowInfront(Animal animal, int distance) {
    int r = animal.getRow();
    switch (animal.getOrientation()) {
    case 0:
      return r;
    case 90:
      return r + distance;
    case 180:
      return r;
    case 270:
      return r - distance;
    default:
      return r;
    }
  }

  /**
   * Work out the coordinates of the cell that is the specified distance in
   * front of the animal and return its column number.
   */
  public int columnInfront(Animal animal, int distance) {
    int c = animal.getColumn();
    switch (animal.getOrientation()) {
    case 0:
      return c + distance;
    case 90:
      return c;
    case 180:
      return c - distance;
    case 270:
      return c;
    default:
      return c;
    }
  }

  /**
   *TODO delete this method Return true iff food is directly in front of the
   * animal
   */
  /*
   * public boolean foodAdjacent(Animal animal) { int r = animal.getRow(); int c
   * = animal.getColumn(); int newR = rowInfront(animal, 1); int newC =
   * columnInfront(animal, 1); if (map[newR][newC] != null && map[newR][newC]
   * instanceof Plant) { return true; } else { return false; } }
   */

  /**
   * This version of the method is for herbivores. It allows a herbivore to eat
   * the plant that is adjacent in the direction faced.
   */

  public void eat(Herbivore animal) {
    int r = animal.getRow();
    int c = animal.getColumn();
    int newR = rowInfront(animal, 1);
    int newC = columnInfront(animal, 1);
    if (map[newR][newC] != null && map[newR][newC] instanceof Plant) {
      plants.remove(map[newR][newC]);
      animal.increaseHealth(5);
      numberOfPlants--;
      map[newR][newC] = animal;
      map[r][c] = null;
      animal.setColumn(newC);
      animal.setRow(newR);
    }
  }

  /**
   * This version of the method is for carnivores. It allows a carnivore to eat
   * the herbivore that is adjacent in the direction faced.
   */

  public void eat(Carnivore animal) {
    int r = animal.getRow();
    int c = animal.getColumn();
    int newR = rowInfront(animal, 1);
    int newC = columnInfront(animal, 1);
    if (map[newR][newC] != null && map[newR][newC] instanceof Herbivore) {
      ((Animal) map[newR][newC]).setHealth(0);
      //animals.remove(map[newR][newC]);
      animal.increaseHealth(10);
      map[newR][newC] = animal;
      map[r][c] = null;
      animal.setColumn(newC);
      animal.setRow(newR);
    }
  }

  /**
   * Rotate the specified animal 90 degrees clockwise at a cost of 1 health
   * point.
   */

  public void turnLeft(Animal animal) {
    animal.decreaseHealth(1);
    animal.setOrientation(animal.getOrientation() + 90);
  }

  /**
   * Rotate the specified animal 90 degrees clockwise at a cost of 1 health
   * point.
   */

  public void turnRight(Animal animal) {
    animal.decreaseHealth(1);
    animal.setOrientation(animal.getOrientation() - 90);
  }

  /**
   * Move the animal one cell forward. If the way is blocked, the animal stays
   * where it is.
   */
  public void stepForward(Animal animal) {
    int r = animal.getRow();
    int c = animal.getColumn();
    int newR, newC;
    switch (animal.getOrientation()) {
    case 0:
      newR = r;
      newC = c + 1;
      break;
    case 90:
      newR = r + 1;
      newC = c;
      break;
    case 180:
      newR = r;
      newC = c - 1;
      break;
    case 270:
      newR = r - 1;
      newC = c;
      break;
    default:
      newR = r;
      newC = c;
      break;
    }
    if (map[newR][newC] == null) {
      animal.decreaseHealth(1);
      map[newR][newC] = animal;
      map[r][c] = null;
      animal.setColumn(newC);
      animal.setRow(newR);
    }
  }

  /**
   * Moves the animal one step forward. If there is a plant at the destination
   * it is destroyed. If there is something other than space or a plant in the
   * cell do nothing.
   * 
   * @param animal
   *          the animal to be moved.
   */
  public void trample(Animal animal) {
    animal.decreaseHealth(1);
    int r = animal.getRow();
    int c = animal.getColumn();
    int newR, newC;
    switch (animal.getOrientation()) {
    case 0:
      newR = r;
      newC = c + 1;
      break;
    case 90:
      newR = r + 1;
      newC = c;
      break;
    case 180:
      newR = r;
      newC = c - 1;
      break;
    case 270:
      newR = r - 1;
      newC = c;
      break;
    default:
      newR = r;
      newC = c;
      break;
    }
    if (map[newR][newC] == null) {
      map[newR][newC] = animal;
      map[r][c] = null;
      animal.setColumn(newC);
      animal.setRow(newR);
    } else {
      if (map[newR][newC] instanceof Plant) {
        plants.remove(map[newR][newC]);
        map[newR][newC] = animal;
        map[r][c] = null;
        animal.setColumn(newC);
        animal.setRow(newR);
      }
    }
  }

  /**
   * Move the herbivore up to 3 steps forward destroying plants and other
   * herbivores that get in the way. If anything else, other than space,
   * herbivores or plants is encountered the animal stops moving.
   */

  public void stampede(Herbivore animal) {
    animal.decreaseHealth(5);
    for (int steps = 0; steps < 3; steps++) {
      int r = animal.getRow();
      int c = animal.getColumn();
      int newR, newC;
      switch (animal.getOrientation()) {
      case 0:
        newR = r;
        newC = c + 1;
        break;
      case 90:
        newR = r + 1;
        newC = c;
        break;
      case 180:
        newR = r;
        newC = c - 1;
        break;
      case 270:
        newR = r - 1;
        newC = c;
        break;
      default:
        newR = r;
        newC = c;
        break;
      }
      if (map[newR][newC] == null) {
        map[newR][newC] = animal;
        map[r][c] = null;
        animal.setColumn(newC);
        animal.setRow(newR);
      } else {
        if (map[newR][newC] instanceof Plant) {
          // System.out.println("plant at (" + newR + "," + newC
          // + ") was trampled");
          plants.remove(map[newR][newC]);
          map[newR][newC] = animal;
          map[r][c] = null;
          animal.setColumn(newC);
          animal.setRow(newR);
        } else {
          if (map[newR][newC] instanceof Herbivore) {
            System.out.println("herbivore at (" + newR + "," + newC
                + ") was trampled");
            ((Herbivore) (map[newR][newC])).kill();
            map[newR][newC] = animal;
            map[r][c] = null;
            animal.setColumn(newC);
            animal.setRow(newR);
          }
        }
      }
    }
  }

  /**
   * Make a random move for the animal. Most of the time this will be to head
   * forward, but sometimes might turn left or right.
   * 
   * @param animal
   */
  public void randomMove(Animal animal) {
    if (Math.random() < 0.95)
      stepForward(animal);
    else if (Math.random() < 0.5)
      turnLeft(animal);
    else
      turnRight(animal);
  }
  
  /**
   * Animal either moves left or right depending on random calculation
   * 
   * @param animal
   */
  public void randomMoveLR(Animal animal) {
    //if (Math.random() < 0.98)
	 if (Math.random() < 0.5)
		 turnLeft(animal);
	 else
		 turnRight(animal);
  }

  /**
   * Allows user interfaces to be registered with this model so that when there
   * is an update they will be notified.
   */

  public void addModelListener(ModelListener listener) {
    if (modelListeners == null) {
      modelListeners = new LinkedList<ModelListener>();
    }
    modelListeners.add(listener);
  }

  /**
   * Informs any listening user interfaces that the model has finished
   * processing this time tick and that the interface may be updated.
   */

  public void notifyModelListeners() {
    for (ModelListener listener : modelListeners) {
      listener.stateChanged();
    }
  }

  /**
   * Return the number of cells wide and high the world is.
   */
  public int getSize() {
    return WORLD_SIZE;
  }

  /**
   * Prints out details of all animals left in the world.
   */
  public void printSurvivors() {
    System.out.println("~~~~~~~~~");
    System.out.println("Survivors");
    System.out.println("~~~~~~~~~");
    for (Animal animal : animals) {
      if (animal instanceof Herbivore) {
        System.out.print("Herbivore with ");
      } else {
        System.out.print("Carnivore with ");
      }
      System.out.println(animal.getHealth());
    }

  }

  public WorldObject getWorldObject(int r, int c) {
    return map[r][c];
  }

  /**
   * When this is called the carnivore charges forward up to 5 steps
   * 
   * @param animal
   */
  public void charge(Carnivore animal) {
    animal.decreaseHealth(5);
    for (int steps = 0; steps < 5; steps++) {
      int r = animal.getRow();
      int c = animal.getColumn();
      int newR, newC;
      switch (animal.getOrientation()) {
      case 0:
        newR = r;
        newC = c + 1;
        break;
      case 90:
        newR = r + 1;
        newC = c;
        break;
      case 180:
        newR = r;
        newC = c - 1;
        break;
      case 270:
        newR = r - 1;
        newC = c;
        break;
      default:
        newR = r;
        newC = c;
        break;
      }
      if (map[newR][newC] == null) {
        map[newR][newC] = animal;
        map[r][c] = null;
        animal.setColumn(newC);
        animal.setRow(newR);
      } else {
        if (map[newR][newC] instanceof Plant) {
          // System.out.println("plant at (" + newR + "," + newC
          // + ") was trampled");
          plants.remove(map[newR][newC]);
          map[newR][newC] = animal;
          map[r][c] = null;
          animal.setColumn(newC);
          animal.setRow(newR);
        } else {
          if (map[newR][newC] instanceof Herbivore) {
            System.out.println("herbivore at (" + newR + "," + newC
                + ") was eaten by carnivore");
            ((Herbivore) map[newR][newC]).kill();
            animal.increaseHealth(200);
            map[newR][newC] = animal;
            map[r][c] = null;
            animal.setColumn(newC);
            animal.setRow(newR);
          }
        }
      }
    }
  }

  /**
   * 
   * @return all the animals that are still alive in the simulation
   */
  
  public List<Animal> getAnimals() {
    return animals;
  }

  /**
   * Checks if the cell in front of the animal is empty
   * 
   * @param a
   *          the animal to to be checked
   * @return true if the the animal in front is empty, otherwise false.
   */

  public boolean isCellInFrontEmpty(Animal a) {
    int r = a.getRow() + (int) Math.round(Trig.dsin(a.getOrientation()));
    int c = a.getColumn() + (int) Math.round(Trig.dcos(a.getOrientation()));
    WorldObject inFront = getWorldObject(r, c);
    return inFront == null;
  }

  /**
   * Checks if the cell in front of the animal contains a plant
   * 
   * @param a
   *          the animal to to be checked
   * @return true if the the animal in front is a plant, otherwise false.
   */
  
  public boolean isCellInFrontAPlant(Animal a) {
    int r = a.getRow() + (int) Math.round(Trig.dsin(a.getOrientation()));
    int c = a.getColumn() + (int) Math.round(Trig.dcos(a.getOrientation()));
    WorldObject inFront = getWorldObject(r, c);
    return inFront != null && inFront instanceof Plant;
  }

  /**
   * Checks if the cell in front of the animal contains a carnivore
   * 
   * @param a
   *          the animal to to be checked
   * @return true if the the animal in front is a carnivore, otherwise false.
   */

  public boolean isCellInFrontACarnivore(Animal a) {
    int r = a.getRow() + (int) Math.round(Trig.dsin(a.getOrientation()));
    int c = a.getColumn() + (int) Math.round(Trig.dcos(a.getOrientation()));
    WorldObject inFront = getWorldObject(r, c);
    return inFront != null && inFront instanceof Carnivore;
  }

  /**
   * Checks if the cell in front of the animal contains a herbivore
   * 
   * @param a
   *          the animal to to be checked
   * @return true if the the animal in front is a herbivore, otherwise false.
   */
  public boolean isCellInFrontAHerbivore(Animal a) {
    int r = a.getRow() + (int) Math.round(Trig.dsin(a.getOrientation()));
    int c = a.getColumn() + (int) Math.round(Trig.dcos(a.getOrientation()));
    WorldObject inFront = getWorldObject(r, c);
    return inFront != null && inFront instanceof Herbivore;
  }
}
