package spriteTest;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

import spriteTest.ImageLibrary;
import spriteTest.SpriteObserver;

/**
 * Class Sprite. Basic, semi-autonomous sprite objects. This class is part of the Sprite
 * Engine system designed for module CP2137 See also classes: Background, ImageLibrary,
 * SpriteArray, SpriteEngine, SpriteObserver.
 * @author Peter Wilson
 * @version 1.0 January 2009.
 */
public class Sprite implements ImageObserver {
  // Class variables
  private static int            spritesCount = 0;
  private static SpriteObserver ourSpriteObserver;
  private static ImageLibrary   ourImageLibrary;
  private static JPanel         myCanvas;
  private static int            boardWidth, boardHeight;

  // private static float scaleX, scaleY;

  /** Event codes: for feedback of events raised */
  enum EventCode {
    NO_EVENT, // No event
    LEFT_EDGE, // sprite at left edge of drawing area.
    RIGHT_EDGE, // "   " right "  " "      "
    BOTTOM_EDGE, // "   " bottom "  " "      "
    TOP_EDGE, // "   " top "  " "      "
    RUNNING_WEST, // x speed is negative
    RUNNING_EAST, // x speed is positive
    RESETTING_SPRITE,
    IS_BEING_FOLLOWED,
    END_OF_IMAGE_SEQUENCE
  }; // " has reached final image in sequence (raise only if

  // reportAtEndOfImageSequence is true.)

  // Instance variables
  /** sprite's index. */
  private int       myID;
  /** Current location (rounded to nearest int for display purposes) */
  private float     myX, myY, myZ;
  /** offset to centre of sprite image. */
  private int       myXCentre, myYCentre;
  /** Current movement vector */
  private float     myDX, myDY;
  /** Name of sprite character (selects images) */
  private String    myCharacterName;
  /** name of current action strip */
  private String    myActionName;
  /** no. of images in action strip */
  private int       myActionLength;
  /** Current image position in action strip */
  private int       myCurrentImageNo;
  /** False = ignore for all processing (move, draw, etc) */
  private boolean   isLive;
  /** True = moveable, false = fixed in position. */
  private boolean   isActive;
  /** True if it is to be drawn on screen */
  private boolean   isVisible;
  /** True if sprite is currently moving */
  private boolean   isMoving;
  /** True = raise event when final image of sequence is drawn. */
  private boolean   reportAtEndOfImageSequence;
  /** Sprite event code */
  private EventCode myEventCode;
  /** Sprite event value */
  private int       myEventValue;
  
  /** x and y location, real locations, not changed by movement,
   *  used to reset the scene. */
  private float     xLocation, yLocation;
  /** Original movement vector (speed), used to reset scene */
  private float     originalXSpeed, originalYSpeed;
  /** Original action name, used to reset scene */
  private String    originalActionName;


  /**
   * "initialiseSprites": Class method Must be called before any sprite can be used. Sets
   * up links to Image library, etc
   * @param theEngine
   *          : Sprite Engine
   * @param theLibrary
   *          : Image Library, where sprite images are held
   * @param theObserver
   *          : For handling events raised by any sprite.
   * @param theWidth
   *          : Game board width
   * @param theHeight
   *          : Game board height
   */
  public static void initialiseSprites(ImageLibrary theLibrary, JPanel theCanvas,
      SpriteObserver theObserver, int theWidth, int theHeight) {
    ourImageLibrary = theLibrary;
    myCanvas = theCanvas;
    ourSpriteObserver = theObserver;
    boardWidth = theWidth;
    boardHeight = theHeight;

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
    setOriginalActionName(myActionName);
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
   * Returns sprite's current action name. This is the name of the current action image
   * sequence being used by sprite.
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

  /** **** MODIFIED THIS METHOD ****
   * Returns sprite's current pixel position (X,Y).
   * [0] = Math.round(myX);
   * [1] = Math.round(myY);
   * [2] = myZ;
   * @return sprite's position
   */
  public Object[] getPosition() {
    Object[] thePosition = new Object[3];
    thePosition[0] = Math.round(myX);
    thePosition[1] = Math.round(myY);
    thePosition[2] = myZ;

    return thePosition;
  } // end of Position
  
  /** **** ADDED THIS METHOD ****
   * @return the xLocation
   */
  public float getxLocation() {
    return xLocation;
  }

  /** **** ADDED THIS METHOD ****
   * @return the yLocation
   */
  public float getyLocation() {
    return yLocation;
  }

  /** **** ADDED THIS METHOD ****
   * @return the originalXSpeed
   */
  public float getOriginalXSpeed() {
    return originalXSpeed;
  }
  
  /** **** ADDED THIS METHOD ****
   * @return the originalYSpeed
   */
  public float getOriginalYSpeed() {
    return originalYSpeed;
  }
  
  /** **** ADDED THIS METHOD ****
   * @return the originalActionName
   */
  public String getOriginalActionName() {
    return originalActionName;
  }

  /**
   * Returns horizontal and vertical velocities.
   * @return xySpeed (int[2]) - [0] = x speed, [1] = y speed.
   */
  public float[] getSpeed() {
    float[] xySpeed = new float[2];
    xySpeed[0] = myDX;
    xySpeed[1] = myDY;
    return xySpeed;
  }
  
  /** **** ADDED THIS METHOD ****
   * Is this sprite moving/movable?
   * @return isMoving (boolean).
   */
  public boolean isItMoving() {
    return isMoving;
  }
  
  /** **** ADDED THIS METHOD ****
   * Is this sprite moving/movable?
   * @return isMoving (boolean).
   */
  public void setIsMoving(boolean isItMoving) {
    if (isItMoving == false) {
      setSpeed(0, 0);
    }
    this.isMoving = isItMoving; 
  }

  /**
   * Returns sprite's current event code. Used when sprite raises a call-back event to
   * Observer. e.g. at edge of drawing area.
   * @return event code
   */
  public EventCode getEventCode() {
    return myEventCode;
  }

  /**
   * Returns Sprite's event value. Linked to event code, this is an optional further
   * value. It's meaning can vary depending on the current event code.
   * @return - event value.
   */
  public int getEventValue() {
    return myEventValue;
  }

  // =============== Setters ========================

  /**
   * Change the board dimensions
   * @param theWidth
   *          : game board width
   * @param theHeight
   *          : game board height
   */
  public static void setBoardSize(int theWidth, int theHeight) {
    boardWidth = theWidth;
    boardHeight = theHeight;
  }
  
  /**
   * Set new position of Sprite.
   * Used during run time to change position.
   * @param theX = X coordinate
   * @param theY = Y coordinate
   */
  public void setPosition(int theX, int theY) {
    myX = theX;
    myY = theY;
  } // end setPosition(..)

  /**
   * Set starting position of Sprite.
   * @param theX = X coordinate
   * @param theY = Y coordinate
   * @param theZ = Z coordinate
   *    - Z co-ord is sprite's distance to foreground.
   *      for example, horizon may have value of 20 
   *      (would move very little).
   *      However for objects that should remain in view
   *      for longer periods of time in spite of 
   *      horizontal movement such as celestial bodies
   *      or distant clouds the value of Z may be a
   *      negative value >= -10 for realism.
   */
  public void setPosition(int theX, int theY, float theZ) {
    myX = theX;
    setxLocation(myX);
    myY = theY;
    setyLocation(myY);
    myZ = theZ;
  } // end setPosition(..)
  
  /**
   * @param xLocation the xLocation to set
   */
  private void setxLocation(float xLocation) {
    this.xLocation = xLocation;
  }

  /**
   * @param yLocation the yLocation to set
   */
  private void setyLocation(float yLocation) {
    this.yLocation = yLocation;
  }

  /**
   * @param originalXSpeed the originalXSpeed to set
   */
  private void setOriginalXSpeed(float originalXSpeed) {
    this.originalXSpeed = originalXSpeed;
  }

  /**
   * @param originalYSpeed the originalYSpeed to set
   */
  private void setOriginalYSpeed(float originalYSpeed) {
    this.originalYSpeed = originalYSpeed;
  }

  /**
   * @param originalActionName the originalActionName to set
   */
  private void setOriginalActionName(String originalActionName) {
    this.originalActionName = originalActionName;
  }

  /**
   * Set speed of movement vector (Values in X and Y directions).
   * @param theX
   *          = speed in X direction
   * @param theY
   *          = speed in Y direction
   */
  public void setSpeed(float theX, float theY) {
    myDX = theX;
    if (!isMoving) {
      setOriginalXSpeed(myDX);
      setOriginalYSpeed(myDY);
      if (theX < 0) {
        raiseEvent(EventCode.RUNNING_WEST, -99);
      } else if (theX > 0) {
        raiseEvent(EventCode.RUNNING_EAST, 99);
      }
    }
    myDY = theY;
    isMoving = true;
  } // end setSpeed(..)
  
  public void resetSpeed() {
    myDX = getOriginalXSpeed();
    if (myDX < 0) {
      raiseEvent(EventCode.RUNNING_WEST, -99);
    } else if (myDX > 0) {
      raiseEvent(EventCode.RUNNING_EAST, 99);
    }
    myDY = getOriginalYSpeed();
    isMoving = true;
  }

  /**
   * Set a character, or character type, name for identifying this sprite type during
   * processing.
   * @param theName
   *          = character name
   */
  public void setCharacterName(String theName) {
    myCharacterName = theName;
  }

  /**
   * Set the action image to be used by this sprite. actionName parameter must be in the
   * ImageLibrary list.
   * @param actionName
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

  /**
   * Sets new action name.
   * @param actionName
   * @param reportEndOfSequence
   */
  public void setActionName(String actionName, boolean reportEndOfSequence) {
    setActionName(actionName);
    reportAtEndOfImageSequence = reportEndOfSequence;
  }

  /**
   * Set isLive property. If false then sprite is ignored in all processing.
   * @param value
   */
  public void setLive(boolean value) {
    isLive = value;
  }

  /**
   * Sets sprite's "active" property. If true, then sprite is moved each tick If false,
   * then sprite does not move.
   * @param value
   *          = true/false setting for active property.
   */
  public void setActive(boolean value) {
    isActive = value;
  } // end of setActive(..)

  /**
   * Sets sprite visibility. If true, then sprite is visible If false, then sprite is not
   * displayed, although it may still be moved.
   * @param value
   *          = true/false for visibility.
   */
  public void setVisible(boolean value) {
    isVisible = value;
  } // end of setVisible(..)

  // ============== General Methods ================

  // Method: move
  // Descript: Called every animation "tick"
  // Move sprite by current velocities
  // increment image number
  //

  /** **** MODIFIED THIS METHOD ****
   * Moves the sprite's location by its velocities in X,Y and increment its image no.
   * {Called from SpriteArray.moveAll(..)} If sprite is not active it does not move.
   * Checks for movement events (e.g. reached edge of drawing area) and raises events to
   * SpriteObserver if appropriate. N.B. This routine does NOT draw the sprite - see
   * "draw()" method.
   * @param i (int) - can be used as a speed modifier for example
   * in SpriteArray's moveAll() and parallaxMove() methods.
   */
  public void move(int i) {
    /* ****
     * parameter 'i' enables on the fly
     * movement speed modification.
     */
    EventCode theEventCode;

    if (isLive && isActive) {
      // If sprite is moving, then calculate its new position
      if (isMoving) {
        // Move the sprite
        myX += (myDX * i);
        myY += myDY;

        // Check if sprite is at edge of board
        // (i.e. within one step of it)
        theEventCode = AtEdge();
        if (theEventCode != EventCode.NO_EVENT) {
          // Raise an event to get fresh instructions
          raiseEvent(theEventCode, 0); // Reached Destination
        }
      } // end if (isMoving)
    }
  } // end of move( )

  /**
   * "AtEdge" method. Checks if sprite is at edge of the board. If sprite's bounding box
   * has crossed the edge of drawing area the appropriate event code is returned.
   * @return event code for edge reached. NO_EVENT means sprite has not reached an edge.
   */
  private EventCode AtEdge() {
    EventCode theEventCode = EventCode.NO_EVENT;

    if ((myX - myXCentre) <= 0) {
      theEventCode = EventCode.LEFT_EDGE;
    } else {
      if ((myX + myXCentre) >= boardWidth) {
        theEventCode = EventCode.RIGHT_EDGE;
      } else {
        if ((myY - myYCentre) <= 0) {
          theEventCode = EventCode.TOP_EDGE;
        } else {
          if ((myY + myYCentre) >= boardHeight) {
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
   * Draw the Sprite onto the graphics area. The sprite will be drawn onto a back buffer
   * image. Once all sprites have been drawn the back buffer will be copied to drawing
   * surface.
   * @param g
   *          = graphics area.
   */
  public void draw(Graphics g) {
    if (isLive) {
      if (isVisible) {
        int x = Math.round(myX) - myXCentre;
        int y = Math.round(myY) - myYCentre;
        BufferedImage im = ourImageLibrary.getImage(myActionName, myCurrentImageNo);

        // Draw the sprite, using its current position and image no.
        g.drawImage(im, x, y, this);
      }

      // update the image number ready for next re-draw.
      myCurrentImageNo = (myCurrentImageNo + 1) % myActionLength;

      // If at end of image sequence should we raise event?
      if (myCurrentImageNo == 0 && reportAtEndOfImageSequence) {
        EventCode theEvent = EventCode.END_OF_IMAGE_SEQUENCE;
        raiseEvent(theEvent, 0);
      }
    }
  } // end of draw(..)

  public void draw(Graphics g, float scaleX, float scaleY, float dispX, float dispY) {
    if (isLive) {
      if (isVisible) {
        // int x = Math.round(myX)-(int)(myXCentre*scaleX);
        // int y = Math.round(myY)-(int)(myYCentre*scaleY);
        float x1 = myX - myXCentre;
        float y1 = myY - myYCentre;
        //float x2 = myX + myXCentre;
        //float y2 = myY + myYCentre;
        int theX1 = Math.round(x1 * scaleX - dispX);
        int theY1 = Math.round(y1 * scaleY - dispY);
        int theX2 = Math.round(scaleX * 2 * myXCentre);
        int theY2 = Math.round(scaleY * 2 * myYCentre);

        BufferedImage im = ourImageLibrary.getImage(myActionName, myCurrentImageNo);

        // Draw the sprite, using its current position and image no.
        g.drawImage(im, theX1, theY1, theX2, theY2, this);
        // g.drawImage(im,x+width/4,y+height/4,width/2,height/2,this);
        // System.out.println("Draw sprite from X1 = " + theX1 + " to X2 =" +
        // theX2 + ".  ScaleX = " + scaleX + ", DispX = " + dispX );
      }

      // update the image number ready for next re-draw.
      myCurrentImageNo = (myCurrentImageNo + 1) % myActionLength;

      // If at end of image sequence should we raise event?
      if (myCurrentImageNo == 0 && reportAtEndOfImageSequence) {
        EventCode theEvent = EventCode.END_OF_IMAGE_SEQUENCE;
        raiseEvent(theEvent, 0);
      }
    }
  } // end of draw(..)

  /**
   * imageUpdate method (required by ImageObserver class).
   */
  public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    return false;
  }
  
  /** **** ADDED THIS METHOD ****
   * Raise an 'is being followed' event.
   * NOT USED.
   */
  public void followThisSprite() {
    raiseEvent(EventCode.IS_BEING_FOLLOWED, 99);
  }
  
  /** **** ADDED THIS METHOD ****
   * Resets sprite to original settings.
   * Raises RESETTING_SPRITE event.
   */
  public void resetSprite() {
    raiseEvent(EventCode.RESETTING_SPRITE, -99);
  }

  /**
   * Raises an event for the sprite. Event is seen by the SpriteObserver. This allows
   * messages to be sent back to the game control when some event happens to the sprite;
   * e.g. reaches edge of drawing area. The game control code must decide what to do with
   * sprite and send it the appropriate commands.
   * @param theEventCode
   * @param theEventValue
   */
  private void raiseEvent(EventCode theEventCode, int theEventValue) {
    myEventCode = theEventCode;
    myEventValue = theEventValue;
    ourSpriteObserver.update(this);
  } // end raiseEvent()

}// end of class Sprite