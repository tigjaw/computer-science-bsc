package spriteTask3;

import java.awt.Graphics; //import java.awt.Graphics2D;
import java.awt.Graphics2D; //import java.awt.GraphicsConfiguration;
//import java.awt.GraphicsEnvironment;
import java.awt.Image; //import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;
import spriteTask3.ImageLibrary;
import spriteTask3.SpriteObserver;

/**
 * Class Sprite. Basic, semi-autonomous sprite objects. This class is part of the Sprite Engine
 * system designed for module CP2137 See also classes: Background, ImageLibrary, SpriteArray,
 * SpriteEngine, SpriteObserver.
 * @author Peter Wilson
 * @version 1.0 January 2009.
 */
public class Sprite implements ImageObserver {
  // Class variables
  private static int            spritesCount = 0;
  private static SpriteObserver ourSpriteObserver;
  private static ImageLibrary   ourImageLibrary;
  private static JPanel         myCanvas;

  // Event codes: for feedback of events raised
  enum EventCode {
    NO_EVENT, // No event
    LEFT_EDGE, // sprite at left edge of drawing area.
    RIGHT_EDGE, // "   " right "  " "      "
    BOTTOM_EDGE, // "   " bottom "  " "      "
    TOP_EDGE, // "   " top "  " "      "
    END_OF_IMAGE_SEQUENCE, FOUND_DESTINATION, DESTINATION_LEFT, 
    DESTINATION_RIGHT, COLLIDED, ROTATION_COMPLETE, TERMINAL_VELOCITY
  }; // " has reached final image in sequence (raise only if

  // reportAtEndOfImageSequence is true.)

  // Instance variables
  private int       myID;                      // sprite's index.
  private float     myX, myY;                  // Current location (rounded to nearest int for
  // display purposes)
  private int       myXCentre, myYCentre;      // offset to centre of sprite image.
  private float     myDX, myDY;                // Current movement vector

  // CHARACTER SPECIFIC VARIABLES
  private String    myCharacterName;           // Name of sprite character (selects images)
  private String    myActionName;              // name of current action strip
  private int       myActionLength;            // no. of images in action strip
  private int       myCurrentImageNo;          // Current image position in action strip

  // EVENT SPECIFIC VARIABLES
  private boolean   isLive;                    // False = ignore for all processing
  private boolean   isActive;                  // True = moveable, false = fixed in position.
  private boolean   isVisible;                 // True if it is to be drawn on screen
  private boolean   isMoving;                  // True if sprite is currently moving
  private boolean   reportAtEndOfImageSequence; // True = raise event when final image of sequence
  // is drawn.
  private EventCode myEventCode;               // Sprite event code
  private int       myEventValue;              // Sprite event value

  // DESTINATION SPECIFIC VARIABLES
  private boolean   hasDestination;            // does Sprite have a specified destination?
  private int       destX, destY;              // destX and destY are the destination specified by
  // the user in setDestination()
  private float     stepSizeX, stepSizeY;      // size of x,y steps to reach destination

  // ROTATION SPECIFIC VARIABLES
  private boolean   toBeRotated;               // True if sprite is to be rotated
  private float     rotationInRadians;         // degrees of rotation * (PI / 180)
  private float     numberOfRotationSteps;     // the speed of rotation
  private float     rotationStepSize;          // rotationInRadians / speed of rotation
  private float     nextRotationStep;          // += rotationStepSize on each step
  private int       step;                      // increments until rotation complete.

  // ACCELERATION SPECIFIC VARIABLES
  private boolean   toBeAccelerated;           // True if sprite is to be accelerated to terminalV
  private float     acceleration;              // Acceleration of sprite specified by user
  private float     terminalV;                 // terminal velocity of sprite specified by user
  private boolean   printAcceleration;         // prints change in speed via console.  

  /**
   * "initialiseSprites": Class method Must be called before any sprite can be used. Sets up links
   * to Image library, etc
   * @param theEngine
   *          : Sprite Engine
   * @param theLibrary
   *          : Image Library, where sprite images are held
   * @param theObserver
   *          : For handling events raised by any sprite.
   */
  public static void initialiseSprites(ImageLibrary theLibrary, JPanel theCanvas,
      SpriteObserver theObserver) {
    ourImageLibrary = theLibrary;
    myCanvas = theCanvas;
    ourSpriteObserver = theObserver;

  } // end InitialiseSprites(..)

  // ===== Constructors ====

  /**
   * Default, parameterless, constructor
   */
  public Sprite() {
    initialiseVariables();
  } // end of constructor Sprite( )

  /**
   * Parameterised constructor
   * @param theActionName
   *          = name of initial action for this sprite.
   */
  public Sprite(String theActionName) {
    BufferedImage img;

    initialiseVariables();

    // set initial action
    myActionName = theActionName;
    myActionLength = ourImageLibrary.GetActionLength(myActionName);
    img = ourImageLibrary.getImage(myActionName, 0);
    myXCentre = img.getWidth() / 2;
    myYCentre = img.getHeight() / 2;
  } // end of constructor Sprite(..)

  /**
   * "initialiseVariables" method. Set initial values for all sprite instance variables
   */
  private void initialiseVariables() {
    myID = spritesCount;
    myX = 0;
    myY = 0;
    myXCentre = 0;
    myYCentre = 0;
    myDX = 0;
    myDY = 0;
    myCharacterName = "Unknown";
    myActionName = "Unknown";
    myActionLength = 1;
    myCurrentImageNo = 0;
    isLive = true;
    isActive = true;
    isVisible = true;
    isMoving = false;
    hasDestination = false;
    rotationInRadians = 0;
    toBeRotated = false;
    step = 0;
    toBeAccelerated = false;
    acceleration = 0;
    terminalV = 0;

    // Increment the count of sprites
    spritesCount++;
  }

  // =============== Getters =========================
  /**
   * Return Sprite's unique id
   * @return sprite's unique id number
   */
  public int getID() {
    return myID;
  }

  /**
   * Returns Sprite's character name. e.g. "Alien", "Pacman", "Ghost", etc.....
   * @return - sprite's character name
   */
  public String getCharacterName() {
    return myCharacterName;
  }// end of getCharactername( )

  /**
   * Returns sprite's current action name. This is the name of the current action image sequence
   * being used by sprite.
   * @return sprite's action image sequence name.
   */
  public String getActionName() {
    return myActionName;
  }// end of getActionName( )

  /**
   * Returns sprite's current image no. within the action sequence.
   * @return action image no.
   */
  public int getCurrentImageNo() {
    return myCurrentImageNo;
  } // end of getCurrentImageNo( )

  /**
   * Returns sprite's current pixel position (X,Y)
   * @return sprite's position
   */
  public int[] getPosition() {
    int[] thePosition = new int[2];
    thePosition[0] = Math.round(myX);
    thePosition[1] = Math.round(myY);

    return thePosition;
  } // end of Position

  /**
   * Returns sprite's current event code. Used when sprite raises a call-back event to Observer.
   * e.g. at edge of drawing area.
   * @return event code
   */
  public EventCode getEventCode() {
    return myEventCode;
  }

  /**
   * Returns Sprite's event value. Linked to event code, this is an optional further value. It's
   * meaning can vary depending on the current event code.
   * @return - event value.
   */
  public int getEventValue() {
    return myEventValue;
  }

  /**
   * ADDED THIS METHOD ***** Is the sprite active?
   * @return isActive (boolean) - true if active
   */
  public boolean getIsItActive() {
    return isActive;
  }

  /**
   * ADDED THIS METHOD ***** is the sprite moving?
   * @return isMoving (boolean) - true if moving.
   */
  public boolean getIsMoving() {
    return isMoving;
  }

  /**
   * ADDED THIS METHOD ***** Returns Sprite's hasDestination value. Was the Sprite told to
   * go to a specified point on the screen?
   * @return hasDestination (boolean) - True if sprite has a specified destination.
   */
  public boolean getHasDestination() {
    return hasDestination;
  }

  /**
   * ADDED THIS METHOD ***** returns x coordinate of destination specified by
   * setDestination()
   * @return destX (int) - x coordinate of destination, 0 if no destination specified.
   */
  public int getDestX() {
    return destX;
  }

  /**
   * ADDED THIS METHOD ***** returns y coordinate of destination specified by
   * setDestination()
   * @return destY (int) - y coordinate of destination, 0 if no destination specified.
   */
  public int getDestY() {
    return destY;
  }

  /**
   * ADDED THIS METHOD *****
   * @return myX (float) - current x position.
   */
  public float getMyX() {
    return myX;
  }

  /**
   * ADDED THIS METHOD *****
   * @return myY (float) - current y position.
   */
  public float getMyY() {
    return myY;
  }

  /**
   * ADDED THIS METHOD *****
   * @return myXCentre (int) - x position of sprite centre.
   */
  public int getMyXCentre() {
    return myXCentre;
  }

  /**
   * ADDED THIS METHOD *****
   * @return myYCentre (int) - y position of sprite centre.
   */
  public int getMyYCentre() {
    return myYCentre;
  }

  /**
   * ADDED THIS METHOD *****
   * @return rotationStepSize (float) - size of rotation steps specified by
   *         graduallyRotate method.
   */
  public float getRotationStepSize() {
    return rotationStepSize;
  }

  /**
   * ADDED THIS METHOD *****
   * @return numberOfRotationSteps (float) - number of steps specified for rotation.
   */
  public float getNumberOfRotationSteps() {
    return numberOfRotationSteps;
  }

  /**
   * ADDED THIS METHOD ***** returns total amount of rotation in radians.
   * @return rotationInRadians (float) - radians = degrees * (Pi / 180)
   */
  public float getRotationInRadians() {
    return rotationInRadians;
  }

  /**
   * ADDED THIS METHOD *****
   * @return nextRotationStep (float) - size of next rotation step.
   */
  public float getNextRotationStep() {
    return nextRotationStep;
  }

  /**
   * ADDED THIS METHOD ***** is the sprite to be rotated?
   * @return toBeRotated (boolean) - true if setRotation or setImmediateRoation called.
   */
  public boolean getToBeRotated() {
    return toBeRotated;
  }

  /**
   * ADDED THIS METHOD ***** returns vertical step size set by setDestination
   * @return stepSizeY (float) - vertical distance to travel divided by speed.
   */
  public float getStepSizeY() {
    return stepSizeY;
  }

  /**
   * ADDED THIS METHOD ***** returns horizontal step size set by setDestination
   * @return stepSizeX (float) - horizontal distance to travel divided by speed.
   */
  public float getStepSizeX() {
    return stepSizeX;
  }

  /** ADDED THIS METHOD ***** is sprite to be accelerated?
   * @return toBeAccelerated (boolean) - false if accelerateSprite() not called.
   */
  public Boolean getToBeAccelerated() {
    return toBeAccelerated;
  }

  /** ADDED THIS METHOD ***** returns acceleration set by accelerateSprite().
   * Acceleration increases speed each update/tick.
   * @return acceleration (float) - 0 if accelerateSprite() not called.
   */
  public float getAcceleration() {
    return acceleration;
  }

  /** ADDED THIS METHOD ***** returns terminal velocity set by accelerateSprite().
   * Speed will not be increased beyond value of terminal velocity.
   * @return terminalV (float) - 0 if setAcceleration not called.
   */
  public float getTerminalV() {
    return terminalV;
  }

  // =============== Setters ========================

  /**
   * Set current position of Sprite.
   * @param theX (int)
   *          = X coordinate
   * @param theY (int)
   *          = Y coordinate
   */
  public void setPosition(int theX, int theY) {
    myX = theX;
    myY = theY;
  } // end setPosition(..)

  /**
   * Set speed of movement vector (Values in X and Y directions).
   * @param theX (float)
   *          = speed in X direction
   * @param theY (float)
   *          = speed in Y direction
   */
  public void setSpeed(float theX, float theY) {
    myDX = theX;
    myDY = theY;
    isMoving = true;
  } // end setSpeed(..)

  /**
   * Set a character, or character type, name for identifying this sprite type during
   * processing.
   * @param theName (String)
   *          = character name
   */
  public void setCharacterName(String theName) {
    myCharacterName = theName;
  }

  /**
   * Set the action image to be used by this sprite. actionName parameter must be in the
   * ImageLibrary list.
   * @param actionName (String)
   *          = action image sequence name.
   */
  public void setActionName(String actionName) {
    // Attempt to select the action name.
    int aLength = ourImageLibrary.GetActionLength(actionName);
    // If aLength > 0 then action name is valid, so reset sprite's actions
    if (aLength > 0) {
      myActionName = actionName;
      myActionLength = aLength;
      myCurrentImageNo = 0;
      BufferedImage img = ourImageLibrary.getImage(myActionName, 0);
      myXCentre = img.getWidth() / 2;
      myYCentre = img.getHeight() / 2;
    } else {
      System.out.println("Error: Action " + actionName + " not loaded");
    } // end if....else....
  } // end of setActionName(...)

  public void setActionName(String actionName, boolean reportEndOfSequence) {
    setActionName(actionName);
    reportAtEndOfImageSequence = reportEndOfSequence;
  }

  /**
   * Set isLive property. If false then sprite is ignored in all processing.
   * @param value (boolean) - true if sprite is live.
   */
  public void setLive(boolean value) {
    isLive = value;
  }

  /**
   * Sets sprite's "active" property. If true, then sprite is moved each tick If false,
   * then sprite does not move.
   * @param value
   *          (boolean) = true/false setting for active property.
   */
  public void setActive(boolean value) {
    isActive = value;
    isMoving = false;
  } // end of setActive(..)

  /**
   * Sets sprite visibility. If true, then sprite is visible If false, then sprite is not
   * displayed, although it may still be moved.
   * @param value (boolean)
   *          = true/false for visibility.
   */
  public void setVisible(boolean value) {
    isVisible = value;
  } // end of setVisible(..)

  /** ADDED THIS METHOD ***** 
   * Tells the sprite that it has somewhere specific to go on the screen. if
   * true movement on game update follows different behaviour. This method is only added for
   * formality for now, no use as of yet.
   * @param hasSomewhereToGo (boolean) - true if sprite has destination.
   */
  public void setHasDestination(boolean hasSomewhereToGo) {
    hasDestination = hasSomewhereToGo;
  }

  /** ADDED THIS METHOD *****
   * Size of movement to add to current x position if sprite has a
   * destination.
   * @param stepSizeX
   *          (float) - the stepSizeX to set
   */
  public void setStepSizeX(float stepSizeX) {
    this.stepSizeX = stepSizeX;
  }

  /** ADDED THIS METHOD *****
   * Size of movement to add to current y position if sprite has a
   * destination.
   * @param stepSizeY
   *          (float) - the stepSizeY to set
   */
  public void setStepSizeY(float stepSizeY) {
    this.stepSizeY = stepSizeY;
  }

  /**
   * ADDED THIS METHOD *****
   * @param rotationStepSize
   *          (float) - set the amount of rotation each step.
   */
  public void setRotationStepSize(float rotationStepSize) {
    this.rotationStepSize = rotationStepSize;
  }

  /**
   * ADDED THIS METHOD ***** Has no real function.
   * @param numberOfRotationSteps
   *          (float) - set number of steps before rotation is complete.
   */
  public void setNumberOfRotationSteps(float numberOfRotationSteps) {
    this.numberOfRotationSteps = numberOfRotationSteps;
  }

  /**
   * ADDED THIS METHOD ***** Has no real function other than for testing.
   * @param radians
   *          (float) - set total rotation in radians.
   */
  public void setRotationInRadians(float radians) {
    this.rotationInRadians = radians;
  }

  /**
   * ADDED THIS METHOD *****
   * @param toBeRotated (boolean) - true if sprite to be rotated.
   */
  public void setToBeRotated(boolean toBeRotated) {
    this.toBeRotated = toBeRotated;
  }

  /**
   * ADDED THIS METHOD *****
   * @param toBeAccelerated
   *          (boolean) - true if sprite to be accelerated or decelerated.
   */
  public void setToBeAccelerated(Boolean toBeAccelerated) {
    this.toBeAccelerated = toBeAccelerated;
  }
  
  /** ADDED THIS METHOD *****
   * Value of acceleration is added to speed on each tick.
   * @param acceleration
   *          (float) - the acceleration to set.
   */
  public void setAcceleration(float acceleration) {
    this.acceleration = acceleration;
  }

  /** ADDED THIS METHOD *****
   * Speed cannot surpass this terminal velocity.
   * @param terminalV
   *          (float) - Terminal Velocity of an accelerating sprite.
   */
  public void setTerminalV(float terminalV) {
    this.terminalV = terminalV;
  }
  
  // ============== Additional Functions ================
  
  /** ADDED THIS METHOD *****
   * Gives sprite x and y co-ordinates to move to with a speed value.
   * Cannot be used in conjunction with accelerateSprite method and functions.
   * @param speed (float) - Low = fast, high = slow
   * @param xDest (int) - x coordinate to move to
   * @param yDest (int) - y coordinate to move to.
   */
  public void setDestination(float speed, int xDest, int yDest) {
    // set event drivers:
    isMoving = true;
    hasDestination = true;
    toBeAccelerated = false;
    
    destX = xDest;
    destY = yDest;
    // **** difference between current x co-ord and destination x co-ord
    int diffX = (int) (myX - destX);
    // **** difference between current y co-ord and destination y co-ord
    int diffY = (int) (myY - destY);
    // **** stepSize is the real speed modifier
    // **** ratio of movement
    stepSizeX = (diffX / speed);
    stepSizeY = (diffY / speed);
    // **** if destination is to the left of current position
    if (myX > destX) {
      raiseEvent(EventCode.DESTINATION_LEFT, -1);
    } else {
      // **** else if destination is to the right of current position
      raiseEvent(EventCode.DESTINATION_RIGHT, -2);
    }
  }

  /**
   * ADDED THIS METHOD *****
   * Immediately rotates sprite by this amount of degrees by first
   * converting degrees into radians. Radians = degrees * (Pi / 180).
   * @param rotationInDegrees
   *          (float) - amount of immediate rotation in degrees.
   */
  public void immediatelyRotate(float rotationInDegrees) {
    // set event drivers:
    toBeRotated = true;
    isMoving = true;
    
    rotationInRadians = (float) (rotationInDegrees * (Math.PI / 180));
    nextRotationStep = rotationInRadians;
    numberOfRotationSteps = 1;
    
    System.out.println("Sprite " + getID() + " to be rotated " + rotationInDegrees + " degrees"
        + " (" + rotationInRadians + " radians) with speed of " + numberOfRotationSteps);
  }
  
  /** ADDED THIS METHOD *****
   * Allows the user to rotate the image gradually.
   * @param rotationInDegrees (float) - the amount of rotation in degrees
   * @param speed (float) - the speed of rotation, the lower the number the faster.
   */
  public void graduallyRotate(float rotationInDegrees, float speed) {
    // set event drivers:
    toBeRotated = true;
    isMoving = true;
    
    rotationInRadians = (float) (rotationInDegrees * (Math.PI / 180));
    numberOfRotationSteps = speed;
    rotationStepSize = rotationInRadians / numberOfRotationSteps;
    
    System.out.println("Sprite " + getID() + " to be rotated " + rotationInDegrees + " degrees"
        + " (" + rotationInRadians + " radians) with speed of " + numberOfRotationSteps);
  }
  
  /**
   * If current step == number of rotation steps (ie. speed)
   * @return boolean true, else false.
   */
  public boolean rotationCompleted() {
    if(step == numberOfRotationSteps) {
      return true;
    } else {
      return false;
    }
  }
  
  /** ADDED THIS METHOD *****
   * Accelerates sprite horizontally, can also be used to decelerate using negatives.
   * Note that due to the width of the display and depending on your values input
   * terminal velocity may not be reached.
   * @param x (int) - current position on x axis
   * @param y (int) - current position on y axis
   * @param acceleration (float) - increases speed each update, high acceleration = faster
   * @param terminalVelocity (float) - once speed reaches this point acceleration stops
   * @param printAcceleration (boolean) - set to true to print speed change in console.
   */
  public void accelerateSprite(int x, int y, float acceleration, float terminalVelocity, boolean printAcceleration) {
    // set event drivers:
    isMoving = true;
    toBeAccelerated = true;
    hasDestination = false;
    this.printAcceleration = printAcceleration;
    //toBeRotated = false;
    setPosition(x, y);
    this.acceleration = acceleration;
    this.terminalV = terminalVelocity;
    myDX = 0;
    if (acceleration < 0) {
      // **** if decelerating (ie moving left)
      raiseEvent(EventCode.DESTINATION_LEFT, -1);
    } else {
      // **** else if accelerating (ie moving right)
      raiseEvent(EventCode.DESTINATION_RIGHT, -2);
    }
  }

  // ============== General Methods ================

  // Method: move
  // Descript: Called every animation "tick"
  // Move sprite by current velocities
  // increment image number

  /**
   * MODIFIED THIS METHOD **** Moves the sprite's location by its velocities in X,Y and increment
   * its image no. {Called from SpriteArray.moveAll(..)} If sprite is not active it does not move.
   * Checks for movement events (e.g. reached edge of drawing area) and raises events to
   * SpriteObserver if appropriate. N.B. This routine does NOT draw the sprite - see "draw()"
   * method. If setDestination method is used hasDestination is set to true. Movement changes
   * depending on this.
   */
  public void move() {
    EventCode theEventCode;

    if (isLive && isActive) {
      // If sprite is moving, then calculate its new position
      if (isMoving) {
        // **** if setDestination() method is used
        if (hasDestination) {
          // **** if sprite is in range of destination 'x' co-ordinate
          if ((myX <= (destX + 5)) && (myX >= (destX - 5))) {
            // **** if sprite is in range of destination 'y' co-ordinate
            if ((myY <= (destY + 5)) && (myY >= (destY - 5))) {
              raiseEvent(EventCode.FOUND_DESTINATION, -3); // Reached Destination
              // stop
            }
          }
          if (getEventCode() != EventCode.FOUND_DESTINATION) {
            myX -= stepSizeX;
            myY -= stepSizeY;
            // System.out.println("stepSizeX = " + stepSizeX + " stepSizeY = " + stepSizeY);
            // Check if sprite is at edge of board
            theEventCode = AtEdge();
            if (theEventCode != EventCode.NO_EVENT) {
              System.out.println("at edge of board " + theEventCode);
              // Raise an event to get fresh instructions
              raiseEvent(theEventCode, -4); // Reached Event
            }
          }  // end if (getEventCode() != EventCode.FOUND_DESTINATION)
        } // end if (hasDestination)
        if (toBeRotated) {
          if (rotationCompleted()) { // if rotation completed end rotation
            raiseEvent(EventCode.ROTATION_COMPLETE, -3); // Reached Destination
          } else { // else increment step, increase next rotation step
            step++;
            nextRotationStep = nextRotationStep + rotationStepSize;
          }
        } // end if (toBeRotated)
        if (hasDestination == false) {
          if(toBeAccelerated) {
            myX += myDX;
            if(printAcceleration) {
              System.out.println("Sprite " + getID() + " speed == " + myDX);
            }
            if (acceleration > 0) { // sprite must be accelerating
              if (myDX < terminalV) {
                // **** If speed less than terminal velocity then accelerate further.
                myDX += acceleration;
                if(myDX > terminalV) {
                  /* ****
                   * If after above acceleration speed is higher than terminal velocity
                   * reduce speed to terminal velocity.
                   */
                  myDX = terminalV;
                  raiseEvent(EventCode.TERMINAL_VELOCITY, -9);
                }
              }
            } else { // sprite must be decelerating
              if (myDX > terminalV) {
                // **** If speed more than terminal velocity then decelerate further.
                myDX += acceleration;
                if(myDX < terminalV) {
                  /* ****
                   * If after above deceleration speed is lower than terminal velocity
                   * set speed to terminal velocity.
                   */
                  myDX = terminalV;
                  raiseEvent(EventCode.TERMINAL_VELOCITY, -9);
                }
              }
            }
          } else { // else if toBeAccelerated == false
            myX += myDX;
            myY += myDY;
            if (myDX == 0 && myDY == 0) { // if no speed specified
              raiseEvent(EventCode.FOUND_DESTINATION, -3);
            }
          }
          theEventCode = AtEdge();
          if (theEventCode != EventCode.NO_EVENT) {
            raiseEvent(theEventCode, -4);
          }
        } // end if (hasDestination == false)
      } // end if (isMoving)
    }
  } // end of move( )

  /**
   * "AtEdge" method. Checks if sprite is at edge of the board. If sprite's bounding box has crossed
   * the edge of drawing area the appropriate event code is returned.
   * @return event code for edge reached. NO_EVENT means sprite has not reached an edge.
   */
  private EventCode AtEdge() {
    EventCode theEventCode = EventCode.NO_EVENT;

    if ((myX - myXCentre) <= 0) {
      theEventCode = EventCode.LEFT_EDGE;
    } else {
      if ((myX + myXCentre) >= myCanvas.getWidth()) {
        theEventCode = EventCode.RIGHT_EDGE;
      } else {
        if ((myY - myYCentre) <= 0) {
          theEventCode = EventCode.TOP_EDGE;
        } else {
          if ((myY + myYCentre) >= myCanvas.getHeight()) {
            theEventCode = EventCode.BOTTOM_EDGE;
          }
        }
      }
    }
    return theEventCode;
  }

  // Method: draw
  // Descript: Called every animation "tick"
  // Move sprite by current velocities
  // increment image number
  //
  /**
   * Draw the Sprite onto the graphics area. The sprite will be drawn onto a back buffer image. Once
   * all sprites have been drawn the back buffer will be copied to drawing surface.
   * @param g
   *          = graphics area.
   */
  public void draw(Graphics g) {
    if (isLive) {
      if (isVisible) {
        int x = Math.round(myX) - myXCentre;
        int y = Math.round(myY) - myYCentre;
        BufferedImage im = ourImageLibrary.getImage(myActionName, myCurrentImageNo);
        Graphics2D g2 = (Graphics2D) g;

        if (toBeRotated) {          
          /* ****
           * if to be rotated; rotate, draw image
           * and then rotate by negative-nextRotationStep.
           */
            g2.rotate(nextRotationStep, myX, myY);
            g2.drawImage(im, x, y, this);
            g2.rotate(0 - nextRotationStep, myX, myY);
          
        } else { // **** (if (toBeRotated == false)
          // Draw the sprite, using its current position and image no.
          g2.drawImage(im, x, y, this);
        }
      }

      // update the image number ready for next re-draw.
      myCurrentImageNo = (myCurrentImageNo + 1) % myActionLength;

      // If at end of image sequence should we raise event?
      if (myCurrentImageNo == 0 && reportAtEndOfImageSequence) {
        EventCode theEvent = EventCode.END_OF_IMAGE_SEQUENCE;
        raiseEvent(theEvent, -6);
      }
    }
  } // end of draw(..)

  /**
   * imageUpdate method (required by ImageObserver class).
   */
  public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    return false;
  }

  /**
   * ADDED THIS METHOD ***** Called by SpriteArray.detectCollisions() Self describing:
   * collision is detected, collided with sprite with this ID
   * @param collide
   *          - EventCode enum, the sprite has collided with another sprite
   * @param withThisSpriteId
   *          - makes use of theEventValue parameter in raiseEvent method.
   */
  public void collide(EventCode collide, int withThisSpriteId) {
    raiseEvent(collide, withThisSpriteId);
  }

  /**
   * Raises an event for the sprite. Event is seen by the SpriteObserver. This allows messages to be
   * sent back to the game control when some event happens to the sprite; e.g. reaches edge of
   * drawing area. The game control code must decide what to do with sprite and send it the
   * appropriate commands.
   * @param theEventCode
   * @param theEventValue
   */
  private void raiseEvent(EventCode theEventCode, int theEventValue) {
    myEventCode = theEventCode;
    myEventValue = theEventValue;
    ourSpriteObserver.update(this);
  } // end raiseEvent()

}// end of class Sprite
