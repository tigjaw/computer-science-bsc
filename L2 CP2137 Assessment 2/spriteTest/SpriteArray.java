package spriteTest;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import spriteTest.Sprite;

/**
 * Collection of semi-autonomous sprites.
 * 
 * @author Peter Wilson
 * 
 */
public class SpriteArray {

  /**
   * Collection of sprites
   */
  private ArrayList<Sprite> mySprites;

  public SpriteArray() {
    mySprites = new ArrayList<Sprite>();

  } // end of SpriteArray()

  /**
   * add method. Add a new Sprite to the collection
   * 
   * @param theSprite
   * @return
   */
  public int add(Sprite theSprite) {
    mySprites.add(theSprite);
    return mySprites.size() - 1;
  } // add(...)

  /**
   * get method. returns Sprite at given position
   * 
   * @param theIndex
   * @return
   */
  public Sprite get(int theIndex) {
    Sprite theSprite;
    theSprite = (Sprite) mySprites.get(theIndex);
    return theSprite;
  } // get(..)

  /**
   * moveAll method. Causes each Sprite to execute its "Move" method.
   */
  public void moveAll() {
    int i;

    for (i = 0; i < mySprites.size(); i++) {
      // Get the next sprite
      Sprite theSprite = get(i);

      // move it
      theSprite.move(1);
    } // end for..

  } // moveAll()
  
  /**
   * parallaxMove(int spriteID) method.
   * Causes each Sprite to execute its "Move" method or to
   * change position based on the perspective of the
   * viewing area.
   */
  public void parallaxMove(int spriteID) {
    int i;
    //**** Get the sprite to be followed
    Sprite spriteBeingFollowed = get(spriteID);
    
    for (i = 0; i < mySprites.size(); i++) {
      // Get the next sprite
      Sprite theSprite = get(i);

      // move it
      if (theSprite.isItMoving()) {
        //**** move any mobile sprites.
        /*
        if (theSprite.getCharacterName().equals("cat") ) {
          System.out.print(theSprite.getID() + ".cat Speed: " + theSprite.getSpeed()[0]);
          System.out.print(", x position =  " + theSprite.getPosition()[0]);
        }
        */
        if (theSprite.getID() == spriteBeingFollowed.getID()) {
          theSprite.move(1);
        } else {
          if (((theSprite.getSpeed()[0] > 0) && (spriteBeingFollowed.getSpeed()[0] < 0)) ||
              ((theSprite.getSpeed()[0] < 0) && (spriteBeingFollowed.getSpeed()[0] > 0))) {
            /* ****
             * If a sprite is moving in the opposite direction
             * to the sprite being followed its movement speed
             * is multiplied by three in order to make it move
             * quicker past the sprite being followed.
             */
            theSprite.move(3);
          } else {
            theSprite.move(1);
          }
        }
        
        /*
        if (theSprite.getCharacterName().equals("cat") ) {
          System.out.println(", new x position =  " + theSprite.getPosition()[0]);
        }
        */
      } else { //**** if sprite is scenery
        /* ****
         * The scenery sprite's x and z values are
         * collected along with the speed of the
         * sprite being followed.
         * 
         * Using these values the 'perspective
         * movement' of the scenery is handled.
         */
        int x = ((Number)theSprite.getPosition()[0]).intValue();
        //System.out.println("x = " + x);
        float z = ((Number)theSprite.getPosition()[2]).floatValue();
        //System.out.println("z = " + z);
        
        float speed = ((Number)spriteBeingFollowed.getSpeed()[0]).floatValue();
        //System.out.println("speed = " + speed);
        
        int perspective;
        if (z == 0) {
          /* ****
           * Something cannot be divided by zero so
           * is z value equals zero, perspective
           * value is made zero.
           */
          perspective = 0;
        } else {
          /* ****
           * The perspective is the apparent
           * movement of the scenery in relation to
           * the scenery's distance to the viewing
           * area and the speed of movement of the
           * viewing area.
           */
          perspective = (int) (speed / z);
        }
        //System.out.println("perspective = " + perspective);
        
        //**** new scenery 'perspective position'.
        theSprite.setPosition(x - perspective, ((Number)theSprite.getPosition()[1]).intValue());
      }
    } // end for..

  } // parallaxMove()
  
  /** **** ADDED THIS METHOD ****
   * resetScene method.
   * Resets scene/idol sprites to original position.
   * Calls each sprite's resetSprite method.
   */
  public void resetScene() {
    for (int i = 0; i < mySprites.size(); i++) {
      // Get the next sprite
      Sprite theSprite = get(i);
      theSprite.resetSprite();
        /*
        System.out.println(theSprite.getCharacterName() + " - x = " + (int) theSprite.getxLocation() + 
            ", y = " + (int) theSprite.getyLocation());
        theSprite.setPosition((int) theSprite.getxLocation(), (int) theSprite.getyLocation());
        */

    } // end for..
    
  } // end reset scene.

  /**
   * drawAll method. Causes each sprite to execute its draw method.
   * @param g
   */
  public void drawAll(Image backBuffer) {
    Graphics g = backBuffer.getGraphics();

    int i;
    Sprite theSprite;

    for (i = 0; i < mySprites.size(); i++) {
      // Get the next sprite
      theSprite = get(i);

      // draw it
      theSprite.draw(g);
    } // end for..

  } // drawAll()

  public void drawAll(Image backBuffer, float scaleX, float scaleY,
      float dispX, float dispY) {
    Graphics g = backBuffer.getGraphics();

    int i;
    Sprite theSprite;

    for (i = 0; i < mySprites.size(); i++) {
      // Get the next sprite
      theSprite = get(i);

      // draw it
      theSprite.draw(g, scaleX, scaleY, dispX, dispY);
    } // end for..

  } // drawAll()

  /*
  public Sprite findSpriteID(int id) {
    boolean found = false;
    for (int i = 0; i < mySprites.size(); i++) {
      if (get(i).getID() == id) {
        System.out.println("Found Sprite to Follow, " +
        		"character name = " + get(i).getID() + "." + get(i).getCharacterName());
        //found = true;
        return get(i);
      }
    }
    if (!found) {
      System.out.println("Sprite with ID " + id + " not found.");
      return null;
    }
    return null;
  }
  */
}