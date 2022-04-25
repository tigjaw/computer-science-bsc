package spriteTask3;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import spriteTask3.Sprite;
import spriteTask3.SpriteEngine;
import spriteTask3.SpriteObserver;
import spriteTask3.Sprite.EventCode;

@SuppressWarnings("serial")
class TestJPanel extends JPanel implements SpriteObserver {

  SpriteEngine                se;
  private final static String IMAGE_INFO_FILE = "Images/imsInfo.txt";
  private final static String BACK_INFO_FILE  = "Images/Background3.jpg";

  int                         aIndex, bIndex, mIndex;

  /**
   * paintComponent - required by JPanel. Not used by SpriteEngine.
   */
  public void paintComponent(Graphics g) {
    System.out.println("paintComponent running");
  }

  /**
   * getPreferredSize() - required by JPanel. Set size of JPanel.
   */
  public Dimension getPreferredSize() {
    return new Dimension(800, 500);
  }

  /**
   * Instantiate the sprite engine and start it running. Creates and loads some sprites.
   */
  public void start() {

    se = new SpriteEngine(this, IMAGE_INFO_FILE, this); // Instantiate the new Sprite Engine
    se.setBackgroundImage(BACK_INFO_FILE); // Set background image for sprite engine
    se.startEngine(); // start sprite engine (animation running)

    // Create new sprites
    Sprite a = new Sprite("catRight");
    Sprite b = new Sprite("catLeft");
    Sprite m = new Sprite("MammothRight");

    /* **** READ ME ****
     * I have set up the case switch functionality to respond 'happily' to:
     * 'Cat' for :
     *    o use of setPosition method
     *    o use of setDestination method
     *    o use of graduallyRotate and immediatelyRotate methods.
     * 'Mammoth' for :
     *    o use of all of the above
     *    o and use of accelerateSprite method.
     * 
     * This was mainly done for testing purposes.
     * All methods may be used with all sprites but take note of atEdge() behaviour.
     * Then again, you may like exploding cats!
     * 
     * Rotation may be used in conjunction with other methods as shown below.
     * Please note in all cases, low speed parameter = fast, high =  slow.
     * This does not include acceleration, high acceleration = faster.
     * All methods have javadoc commenting for more explanation.
     */
        
    // Set initial position and speed for sprite a
    a.setCharacterName("Cat");
    a.setPosition(450, 50);
    // *************** DESTINATION DEMO ***************
    a.setDestination(90, 520, 400);
    // *************** ROTATION DEMO ***************
    a.graduallyRotate(90, 50);

    // **** Set initial values for sprite b.
    b.setCharacterName("Cat");
    b.setPosition(650, 400);
    // *************** DESTINATION DEMO ***************
    b.setDestination(70, 90, 90);
    //b.setDestination(20, 0, 80); // To left edge: Cat explodes
    //b.setDestination(20, 800, 80); // To right edge: Cat turns left and stops
    /* *************** ROTATION DEMO **************/
    b.immediatelyRotate(20);

    // **** Set initial values for sprite m.
    m.setCharacterName("Mammoth");
    //m.setPosition(600, 450);
    // *************** DESTINATION DEMO ***************
    //m.setDestination(70, 600, 70);
    /* *************** ROTATION DEMO **************/
    m.immediatelyRotate(45);
    //m.graduallyRotate(90, 50);
    // *************** ACCELERATION DEMO ***************
    //m.accelerateSprite(650, 200, (float) -1, -30, true);  // Deceleration
    m.accelerateSprite(80, 200, (float) 2,15, true); // Acceleration

    // Add the sprites to the sprite engine
    aIndex = se.addSprite(a);
    bIndex = se.addSprite(b);
    se.addSprite(m);

  } // end of start()

  /**
   * "update" method. Required by SpriteObserver class. Handle events raised by Sprites.
   */
  public void update(Sprite theSprite) {
    @SuppressWarnings("unused")
    int spriteId = theSprite.getID();
    @SuppressWarnings("unused")
    int[] position = theSprite.getPosition();

    // System.out.print("Sprite No.:" + spriteId + " reports ");
    switch (theSprite.getEventCode()) {
    // **** if the cat's destination is to the left show left leading image
    case DESTINATION_LEFT:
      if (theSprite.getCharacterName() == "Cat") {
        theSprite.setActionName("catLeft");
      } else {
        theSprite.setActionName("MammothLeft");
      }
      break;
    // **** if the cat's destination is to the right show right leading image
    case DESTINATION_RIGHT:
      if (theSprite.getCharacterName() == "Cat") {
        theSprite.setActionName("catRight");
      } else {
        theSprite.setActionName("MammothRight");
      }
      break;
    // **** Once cat has reached destination set it as inactive
    case ROTATION_COMPLETE:
      if (theSprite.getRotationInRadians() != 0) {
        /* ****
         * This if-statement ensures the following is only printed out once at the moment that
         * rotation is completed and not again afterward.
         */
        System.out.println("sprite " + theSprite.getID() + "." + theSprite.getCharacterName()
            + " completed rotation of: " + theSprite.getRotationInRadians() + " radians");
        theSprite.setRotationInRadians(0);
      }
      break;
    // **** Once cat has reached destination set it as inactive
    case FOUND_DESTINATION:
      /* ****
       * These if statements attempt to cull the amount of times the program prints out destination
       * arrivals.
       */
      if (theSprite.getIsItActive()) {
        if (theSprite.getStepSizeX() != 0) {
          if (theSprite.getStepSizeY() != 0) {
            System.out.println("sprite " + theSprite.getID() + "." + theSprite.getCharacterName()
                + " found destination at X: " + theSprite.getMyX() + ", Y: "
                + theSprite.getMyY());
          }
        }
      }
      /* ****
       * If found destination before rotation completed rotation is allowed to complete.
       */
      if (theSprite.getToBeRotated()) {
        if (theSprite.rotationCompleted() == false) {
          /* ****
           * if rotation not complete do not make sprite inactive but instead deplete step sizes to
           * zero. setting isActive to false stops rotation too!
           */
          theSprite.setStepSizeX(0);
          theSprite.setStepSizeY(0);
        } else {
          // **** if rotation is complete set to inactive.
          theSprite.setActive(false);
        }
      } else {
        // **** if sprite not rotating just end activity as usual ie properly.
        theSprite.setActive(false);
      }
      break;
    // **** if sprites collide
    case COLLIDED: 
      System.out.println("Sprite " + theSprite.getID() + "." + theSprite.getCharacterName()
          + " collided with Sprite " + theSprite.getEventValue());
      break;
    case TERMINAL_VELOCITY:
      System.out.println("Sprite " + theSprite.getID() + "." + theSprite.getCharacterName()
          + " reached terminal velocity of " + theSprite.getTerminalV());
      break;
    case LEFT_EDGE:
      // System.out.println( "at left edge (" + position[0] + "," +
      // position[1] + ")");
      if (theSprite.getCharacterName() == "Cat") {
        theSprite.setSpeed(0, 0);
        theSprite.setActionName("kaboom", true);
      } else {
        theSprite.setActionName("MammothRight");
        theSprite.setActive(false);
      }
      break;
    case RIGHT_EDGE:
      // System.out.println("at right edge");
      if (theSprite.getCharacterName() == "Mammoth") {
        theSprite.setActionName("MammothLeft");
        //theSprite.setSpeed(-30, 0); // un-comment this to see a good elastic effect
        theSprite.setActive(false);   // but comment this!
      } else {
        theSprite.setActive(false);
        theSprite.setActionName("catLeft");
      }
      break;
    case BOTTOM_EDGE:
      // System.out.println("at bottom edge");
      theSprite.setSpeed(0, 0);
      theSprite.setActionName("kaboom", true);
      theSprite.setActive(false);
      break;
    case TOP_EDGE:
      // System.out.println("at top edge");
      theSprite.setActive(false);
      break;
    case END_OF_IMAGE_SEQUENCE:
      //if (theSprite.getCharacterName() == "Cat") {
        theSprite.setLive(false);
        System.out.println(theSprite.getCharacterName() + " sprite no. " + theSprite.getID()
            + " is dead");
      //} else {
        //theSprite.setActionName("MammothRight");
        //theSprite.setSpeed(8, 0);
      //}
      break;
    default:
      System.out.println(" EventCode = " + theSprite.getEventCode());
    }
  }

}
