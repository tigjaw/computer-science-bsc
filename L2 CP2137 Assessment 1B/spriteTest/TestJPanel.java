package spriteTest;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import spriteTest.SpriteEngine;
import spriteTest.Sprite;
import spriteTest.SpriteObserver;

class TestJPanel extends JPanel implements SpriteObserver {

    SpriteEngine se;
    private final static String IMAGE_INFO_FILE = "Images/imsInfo.txt";
    private final static String BACK_INFO_FILE = "Images/Background3.jpg";

    int aIndex, bIndex, mIndex;

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
     * Instantiate the sprite engine and start it running. Creates and loads
     * some sprites.
     * 
     */
    public void start() {

	// Instantiate the new Sprite Engine
	se = new SpriteEngine(this, IMAGE_INFO_FILE, this);

	// Set background image for sprite engine
	se.setBackgroundImage(BACK_INFO_FILE);

	// start sprite engine (animation running)
	se.startEngine();

	// Create new sprites
	Sprite a = new Sprite("catRight");
	Sprite b = new Sprite("catLeft");
	Sprite m = new Sprite("MammothRight");

	// int aIndex, bIndex;

	// Set initial position and speed for sprite a
	a.setCharacterName("Cat");
	a.setPosition(450, 350);
	/*
	 * setSpeed, speed is directional movement in specified step-sizes. a
	 * positive x value moves sprite east, positive y value moves sprite
	 * south a negative x value moves sprite west, positive y value moves
	 * sprite north
	 */
	a.setSpeed(10, 0);

	//**** Set initial values for sprite b. USE THIS SPRITE ****
	b.setCharacterName("Cat");
	// Set initial position
	b.setPosition(100, 80);
	/* Set destination (speed, x, y). The lower the speed (the first
	 * number) the faster the movement.
	 */
	b.setDestination(50, 650, 400);
	//**** more examples: (or change the values manually)
	/*
	 * b.setPosition(650, 400);
	 * b.setDestination(25, 100, 80);
	*/
	
	/*
	 * b.setPosition(650, 80);
	 * b.setDestination(50, 100, 400);
	*/

	m.setCharacterName("Mammoth");
	m.setPosition(500, 190);
	m.setSpeed(30, 0);

	// Add the sprites to the sprite engine
	aIndex = se.addSprite(a);
	bIndex = se.addSprite(b);
	se.addSprite(m);

    } // end of start()

    /**
     * "update" method. Required by SpriteObserver class. Handle events raised
     * by Sprites.
     */
    public void update(Sprite theSprite) {
	@SuppressWarnings("unused")
	int spriteId = theSprite.getID();
	@SuppressWarnings("unused")
	int[] position = theSprite.getPosition();

	// System.out.print("Sprite No.:" + spriteId + " reports ");
	switch (theSprite.getEventCode()) {
	//**** if the cat's destination is to the left show left leading image
	case DESTINATION_LEFT:
		if(theSprite.getCharacterName() == "Cat") {
			theSprite.setActionName("catLeft");
		}
		break;
	//**** if the cat's destination is to the right show right leading image
	case DESTINATION_RIGHT:
		if(theSprite.getCharacterName() == "Cat") {
			theSprite.setActionName("catRight");
		}
		break;
	//**** Once cat has reached destination set it as inactive
	case FOUND_DESTINATION:
		theSprite.setActive(false);
		System.out.println("sprite " + theSprite.getID()
				+ " found destination at myX: " + theSprite.getMyX()
				+ ", myY: " + theSprite.getMyY());
		break;
	//**** if sprites collide
	case COLLIDED:
		System.out.println("Sprite " + theSprite.getID() + "." + theSprite.getCharacterName() +
				" collided with Sprite " + theSprite.getEventValue());
		break;
	case LEFT_EDGE:
	    // System.out.println( "at left edge (" + position[0] + "," +
	    // position[1] + ")");
	    if (theSprite.getCharacterName() == "Cat") {
		theSprite.setSpeed(0, 0);
		theSprite.setActionName("kaboom", true);
	    } else {
		theSprite.setSpeed(30, 0);
		theSprite.setActionName("MammothRight");
	    }
	    break;
	case RIGHT_EDGE:
	    // System.out.println("at right edge");
	    if (theSprite.getCharacterName() == "Mammoth") {
		theSprite.setSpeed(-30, 0);
		theSprite.setActionName("MammothLeft");
	    } else {
		theSprite.setSpeed(-10, 0);
		theSprite.setActionName("catLeft");
	    }
	    break;
	case BOTTOM_EDGE:
	    // System.out.println("at bottom edge");
	    theSprite.setSpeed(0, 0);
	    theSprite.setActionName("kaboom", true);

	    break;
	case TOP_EDGE:
	    // System.out.println("at top edge");
	    theSprite.setSpeed(0, 0);
	    theSprite.setActionName("kaboom", true);

	case END_OF_IMAGE_SEQUENCE:
	    if (theSprite.getCharacterName() == "Cat") {
		theSprite.setLive(false);
		System.out.println(theSprite.getCharacterName()
			+ " sprite no. " + theSprite.getID() + " is dead");
	    } else {
		theSprite.setActionName("MammothRight");
		theSprite.setSpeed(8, 0);
	    }

	    break;
	default:
	    System.out.println(" EventCode = " + theSprite.getEventCode());
	}
    }

}
