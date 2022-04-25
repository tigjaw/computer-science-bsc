package spriteTest;

import java.awt.Graphics; //import java.awt.Graphics2D;
//import java.awt.GraphicsConfiguration;
//import java.awt.GraphicsEnvironment;
import java.awt.Image; //import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

import spriteTest.ImageLibrary;
import spriteTest.SpriteObserver;

/**
 * Class Sprite. Basic, semi-autonomous sprite objects.
 * 
 * This class is part of the Sprite Engine system designed for module CP2137 See
 * also classes: Background, ImageLibrary, SpriteArray, SpriteEngine,
 * SpriteObserver.
 * 
 * @author Peter Wilson
 * @version 1.0 January 2009.
 * 
 */
public class Sprite implements ImageObserver {
	// Class variables
	private static int spritesCount = 0;
	private static SpriteObserver ourSpriteObserver;
	private static ImageLibrary ourImageLibrary;
	private static JPanel myCanvas;

	// Event codes: for feedback of events raised
	enum EventCode {
		NO_EVENT, // No event
		LEFT_EDGE, // sprite at left edge of drawing area.
		RIGHT_EDGE, // "   " right "  " "      "
		BOTTOM_EDGE, // "   " bottom "  " "      "
		TOP_EDGE, // "   " top "  " "      "
		END_OF_IMAGE_SEQUENCE, FOUND_DESTINATION, DESTINATION_LEFT, DESTINATION_RIGHT, COLLIDED
	}; // " has reached final image in sequence (raise only if

	// reportAtEndOfImageSequence is true.)

	// Instance variables
	private int myID; // sprite's index.
	private float myX, myY; // Current location (rounded to nearest int for
	// display purposes)
	private int myXCentre, myYCentre; // offset to centre of sprite image.
	private float myDX, myDY; // Current movement vector
	private String myCharacterName; // Name of sprite character (selects images)
	private String myActionName; // name of current action strip
	private int myActionLength; // no. of images in action strip
	private int myCurrentImageNo; // Current image position in action strip
	private boolean isLive; // False = ignore for all processing
	private boolean isActive; // True = moveable, false = fixed in position.
	private boolean isVisible; // True if it is to be drawn on screen
	private boolean isMoving; // True if sprite is currently moving
	private boolean reportAtEndOfImageSequence; // True = raise event when final
	// image of sequence is drawn.
	private EventCode myEventCode; // Sprite event code
	private int myEventValue; // Sprite event value
	private boolean hasDestination; // does Sprite have a specified destination?
	private int destX, destY; // destX and destY are the destination specified
	// by the user in setDestination()
	private float stepSizeX, stepSizeY; // size of x,y steps to reach destination

	/**
	 * "initialiseSprites": Class method Must be called before any sprite can be
	 * used. Sets up links to Image library, etc
	 * 
	 * @param theEngine
	 *            : Sprite Engine
	 * @param theLibrary
	 *            : Image Library, where sprite images are held
	 * @param theObserver
	 *            : For handling events raised by any sprite.
	 */
	public static void initialiseSprites(ImageLibrary theLibrary,
			JPanel theCanvas, SpriteObserver theObserver) {
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
	 * 
	 * @param theActionName
	 *            = name of initial action for this sprite.
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
	 * "initialiseVariables" method. Set initial values for all sprite instance
	 * variables
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

		// Increment the count of sprites
		spritesCount++;

	}

	// =============== Getters =========================
	/**
	 * Return Sprite's unique id
	 * 
	 * @return sprite's unique id number
	 */
	public int getID() {
		return myID;
	}

	/**
	 * Returns Sprite's character name. e.g. "Alien", "Pacman", "Ghost",
	 * etc.....
	 * 
	 * @return - sprite's character name
	 */
	public String getCharacterName() {
		return myCharacterName;
	}// end of getCharactername( )

	/**
	 * Returns sprite's current action name. This is the name of the current
	 * action image sequence being used by sprite.
	 * 
	 * @return sprite's action image sequence name.
	 */
	public String getActionName() {
		return myActionName;
	}// end of getActionName( )

	/**
	 * Returns sprite's current image no. within the action sequence.
	 * 
	 * @return action image no.
	 */
	public int getCurrentImageNo() {
		return myCurrentImageNo;
	} // end of getCurrentImageNo( )

	/**
	 * Returns sprite's current pixel position (X,Y)
	 * 
	 * @return sprite's position
	 */
	public int[] getPosition() {
		int[] thePosition = new int[2];
		thePosition[0] = Math.round(myX);
		thePosition[1] = Math.round(myY);

		return thePosition;
	} // end of Position

	/**
	 * Returns sprite's current event code. Used when sprite raises a call-back
	 * event to Observer. e.g. at edge of drawing area.
	 * 
	 * @return event code
	 */
	public EventCode getEventCode() {
		return myEventCode;
	}

	/**
	 * Returns Sprite's event value. Linked to event code, this is an optional
	 * further value. It's meaning can vary depending on the current event code.
	 * 
	 * @return - event value.
	 */
	public int getEventValue() {
		return myEventValue;
	}

	/** ADDED THIS METHOD *****
	 * Returns Sprite's hasDestination value. Was the Sprite told to go to a
	 * specified point on the screen?
	 * 
	 * @return - hasDestination value.
	 */
	public boolean getHasDestination() {
		return hasDestination;
	}

	/** ADDED THIS METHOD *****
	 * @return the destX
	 */
	public int getDestX() {
		return destX;
	}

	/** ADDED THIS METHOD *****
	 * @return the destY
	 */
	public int getDestY() {
		return destY;
	}

	/** ADDED THIS METHOD *****
	 * @return myX
	 */
	public float getMyX() {
		return myX;
	}

	/** ADDED THIS METHOD *****
	 * @return the myY
	 */
	public float getMyY() {
		return myY;
	}

	/** ADDED THIS METHOD *****
	 * @return the myXCentre
	 */
	public int getMyXCentre() {
		return myXCentre;
	}

	/** ADDED THIS METHOD *****
	 * @return the myYCentre
	 */
	public int getMyYCentre() {
		return myYCentre;
	}

	// =============== Setters ========================

	/**
	 * Set current position of Sprite.
	 * 
	 * @param theX
	 *            = X coordinate
	 * @param theY
	 *            = Y coordinate
	 */
	public void setPosition(int theX, int theY) {
		myX = theX;
		myY = theY;
	} // end setPosition(..)

	/** ADDED THIS METHOD *****
	 * Sets a destination for sprite to move to. Calls 'setSpeed' with the first
	 * two parameters.
	 * 
	 * @param theXspeed
	 *            = speed in X direction
	 * @param theYspeed
	 *            = speed in Y direction
	 * @param xDest
	 *            = destination in X direction
	 * @param yDest
	 *            = destination in Y direction
	 */
	public void setDestination(float speed, int xDest, int yDest) {
		hasDestination = true;
		destX = xDest;
		destY = yDest;
		//**** difference between current x co-ord and destination x co-ord
		int diffX = (int) (myX - destX);
		//**** difference between current y co-ord and destination y co-ord
		int diffY = (int) (myY - destY);
		//**** stepSize is the real speed modifier
		//**** ratio of movement
		stepSizeX = (diffX / speed);
		stepSizeY = (diffY / speed);
		isMoving = true;
		//**** if destination is to the left of current position
		if (myX > destX) {
			raiseEvent(EventCode.DESTINATION_LEFT, -1);
		} else {
		//**** else if destination is to the right of current position
			raiseEvent(EventCode.DESTINATION_RIGHT, -2);
		}
	}

	/**
	 * Set speed of movement vector (Values in X and Y directions).
	 * 
	 * @param theX
	 *            = speed in X direction
	 * @param theY
	 *            = speed in Y direction
	 */
	public void setSpeed(float theX, float theY) {
		myDX = theX;
		myDY = theY;
		isMoving = true;
	} // end setSpeed(..)

	/**
	 * Set a character, or character type, name for identifying this sprite type
	 * during processing.
	 * 
	 * @param theName
	 *            = character name
	 */
	public void setCharacterName(String theName) {
		myCharacterName = theName;
	}

	/**
	 * Set the action image to be used by this sprite. actionName parameter must
	 * be in the ImageLibrary list.
	 * 
	 * @param actionName
	 *            = action image sequence name.
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
	 * 
	 * @param value
	 */
	public void setLive(boolean value) {
		isLive = value;
	}

	/**
	 * Sets sprite's "active" property. If true, then sprite is moved each tick
	 * If false, then sprite does not move.
	 * 
	 * @param value
	 *            = true/false setting for active property.
	 */
	public void setActive(boolean value) {
		isActive = value;
	} // end of setActive(..)

	/**
	 * Sets sprite visibility. If true, then sprite is visible If false, then
	 * sprite is not displayed, although it may still be moved.
	 * 
	 * @param value
	 *            = true/false for visibility.
	 */
	public void setVisible(boolean value) {
		isVisible = value;
	} // end of setVisible(..)

	/** ADDED THIS METHOD *****
	 * Tells the sprite that it has somewhere specific to go on the screen. if
	 * true movement on game update follows different behaviour. This method is
	 * only added for formality for now, no use as of yet.
	 * 
	 * @param hasSomewhereToGo
	 */
	public void setHasDestination(boolean hasSomewhereToGo) {
		hasDestination = hasSomewhereToGo;
	}

	// ============== General Methods ================

	// Method: move
	// Descript: Called every animation "tick"
	// Move sprite by current velocities
	// increment image number
	//

	/** MODIFIED THIS METHOD ****
	 * Moves the sprite's location by its velocities in X,Y and increment its
	 * image no. {Called from SpriteArray.moveAll(..)} If sprite is not active
	 * it does not move. Checks for movement events (e.g. reached edge of
	 * drawing area) and raises events to SpriteObserver if appropriate. N.B.
	 * This routine does NOT draw the sprite - see "draw()" method.
	 * 
	 * If setDestination method is used hasDestination is set to true.
	 * Movement changes depending on this.
	 */
	public void move() {
		EventCode theEventCode;

		if (isLive && isActive) {
			// If sprite is moving, then calculate its new position
			if (isMoving) {
				//**** if setDestination() method is used
				if (hasDestination == true) {
					//boolean arrived = false;
					//**** if sprite is in range of destination 'x' co-ordinate
					if ((myX <= (destX + 10)) && (myX >= (destX - 10))) {
						// System.out.println("NEAR destination: myX " + myX +
						// ", myY " + myY);
						//**** if sprite is in range of destination 'y' co-ordinate
						if ((myY <= (destY + 10)) && (myY >= (destY - 10))) {
							theEventCode = EventCode.FOUND_DESTINATION;
							raiseEvent(theEventCode, -3); // Reached Destination
							// arrived = true;
							// stop
						}
					}
					//if (arrived == false) {
					//**** if sprite has not yet found destination
					if (getEventCode() != EventCode.FOUND_DESTINATION) {
						myX -= stepSizeX;
						// System.out.println("stepping x " + myX);
						myY -= stepSizeY;
						// System.out.println("stepping y " + myY);

						// Check if sprite is at edge of board
						// (i.e. within one step of it)
						theEventCode = AtEdge();
						if (theEventCode != EventCode.NO_EVENT) {
							System.out.println("at edge of board "
									+ theEventCode);
							// Raise an event to get fresh instructions
							raiseEvent(theEventCode, -4); // Reached Destination
						}
					}
				} else {
					//**** if setDestination() method is not used for the movement
					// Move the sprite
					myX += myDX;
					myY += myDY;

					// Check if sprite is at edge of board
					// (i.e. within one step of it)
					theEventCode = AtEdge();
					if (theEventCode != EventCode.NO_EVENT) {
						// Raise an event to get fresh instructions
						raiseEvent(theEventCode, -5); // Reached Destination
					}
				}

			} // end if (isMoving)

		}
	} // end of move( )

	/**
	 * "AtEdge" method. Checks if sprite is at edge of the board. If sprite's
	 * bounding box has crossed the edge of drawing area the appropriate event
	 * code is returned.
	 * 
	 * @return event code for edge reached. NO_EVENT means sprite has not
	 *         reached an edge.
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
	 * Draw the Sprite onto the graphics area. The sprite will be drawn onto a
	 * back buffer image. Once all sprites have been drawn the back buffer will
	 * be copied to drawing surface.
	 * 
	 * @param g
	 *            = graphics area.
	 */
	public void draw(Graphics g) {
		if (isLive) {
			if (isVisible) {
				int x = Math.round(myX) - myXCentre;
				int y = Math.round(myY) - myYCentre;
				BufferedImage im = ourImageLibrary.getImage(myActionName,
						myCurrentImageNo);

				// Draw the sprite, using its current position and image no.
				g.drawImage(im, x, y, this);
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
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

	/** ADDED THIS METHOD *****
	 * Called by SpriteArray.detectCollisions()
	 * Self describing: collision is detected, collided with sprite with this ID
	 * 
	 * @param collide
	 *            - EventCode enum, the sprite has collided with another sprite
	 * @param withThisSpriteId
	 *            - makes use of theEventValue parameter in raiseEvent method.
	 */
	public void collide(EventCode collide, int withThisSpriteId) {
		raiseEvent(collide, withThisSpriteId);
	}

	/**
	 * Raises an event for the sprite. Event is seen by the SpriteObserver. This
	 * allows messages to be sent back to the game control when some event
	 * happens to the sprite; e.g. reaches edge of drawing area. The game
	 * control code must decide what to do with sprite and send it the
	 * appropriate commands.
	 * 
	 * @param theEventCode
	 * @param theEventValue
	 */
	private void raiseEvent(EventCode theEventCode, int theEventValue) {
		myEventCode = theEventCode;
		myEventValue = theEventValue;
		ourSpriteObserver.update(this);
	} // end raiseEvent()

}// end of class Sprite
