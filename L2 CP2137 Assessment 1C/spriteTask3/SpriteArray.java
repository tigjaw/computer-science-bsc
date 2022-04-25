package spriteTask3;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import spriteTask3.Sprite;
import spriteTask3.Sprite.EventCode;

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
			theSprite.move();
		} // end for..
		detectCollisions();

	} // moveAll()

	/**
	 *	Detects all collisions between Sprites.
	 */
	public void detectCollisions() {
		for (int i = 0; i < mySprites.size(); i++) {
			//**** Starts loop through sprites.
			Sprite theSprite = get(i);
			for (int x = i+1; x < mySprites.size(); x++) {
				//**** starts loop through sprites to compare positioning
				Sprite compareSprite = get(x);
				if((theSprite.getMyX() >= compareSprite.getMyX()) &&
						(theSprite.getMyX() <= (compareSprite.getMyX() + (compareSprite.getMyXCentre() * 2)))) {
					//**** if theSprite's s x co-ordinate and compareSprite's x co-ordinate in same range
					if((theSprite.getMyY() >= compareSprite.getMyY()) &&
						(theSprite.getMyY() <= (compareSprite.getMyY() + (compareSprite.getMyYCentre() * 2)))) {
						//**** if theSprite's y co-ordinate and compareSprite's y co-ordinate in same range
						//**** raise collision event and use compareSprite's ID as eventValue.
						EventCode collideEvent = EventCode.COLLIDED;
						theSprite.collide(collideEvent, compareSprite.getID());
						//System.out.println("Sprite " + theSprite.getID() + "." + theSprite.getCharacterName() +
						//		" collided with Sprite " + compareSprite.getID() + "." + compareSprite.getCharacterName());
					}
				}
			}
		}
	}

	/**
	 * drawAll method. Causes each sprite to execute its draw method.
	 * 
	 * @param g
	 */
	public void drawAll(Image backBuffer)

	{
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
}
