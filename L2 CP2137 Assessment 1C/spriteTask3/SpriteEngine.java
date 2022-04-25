package spriteTask3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * A Sprite graphics engine, running in its own thread. Handles all of the
 * graphical side of a sprite-based game, etc. Handles semi-autonomous sprites.
 * 
 * Related classes: Background, ImageLibrary, Sprite, SpriteArray,
 * SpriteObserver
 * 
 * @author Peter Wilson. (Adapted from code by A. Davison
 *         "Killer games programming in Java".)
 * @version: 1.0 January 2009.
 */
public class SpriteEngine implements Runnable

{
    /**
     * Drawing surface
     */
    private JPanel myCanvas;

    /**
     * Background for animation
     */
    private Background myBackground;

    /**
     * Collection of sprites
     */
    private SpriteArray mySprites = new SpriteArray();

    /**
     * Image library
     */
    private ImageLibrary myImageLibrary;

    /**
     * Refresh rate - in frames per second. Defaults to 25 frames/sec
     */
    private int myFramesPerSecond = 25;
    private long redrawPeriod = calcRedrawPeriod(); // time(ms) for each frame
    private int noSleepCount = 0;
    private static int MAX_FRAME_SKIPS = 5; // max. no. of unrendered updates
					    // before forcing a redraw
    private static final int NO_DELAYS_PER_YIELD = 16;
    /*
     * Number of frames with a delay of 0 ms before the animation thread yields
     * to other running threads.
     */

    /**
     * Animation thread
     */
    private Thread myThread;
    private volatile boolean running = false;
    @SuppressWarnings("unused")
    private volatile boolean isPaused = false;

    // ========= Constructor ===============

    public SpriteEngine(JPanel theJPanel, String imageDefinitionFileName,
	    SpriteObserver theSpriteObserver) {
	// **** Store link to JPanel
	myCanvas = theJPanel;
	System.out.println("JPanel size   = (" + theJPanel.getWidth() + ","
		+ theJPanel.getHeight() + ")");
	System.out.println("myCanvas size = (" + myCanvas.getWidth() + ","
		+ myCanvas.getHeight() + ")");

	// **** Instantiate and load the Image Library
	myImageLibrary = new ImageLibrary(imageDefinitionFileName);

	// **** Initialise the static values of the Sprite class.
	// Sprite.initialiseSprites(this, myImageLibrary, theJPanel,
	// theSpriteObserver);
	Sprite.initialiseSprites(myImageLibrary, theJPanel, theSpriteObserver);

	// Initialise background
	myBackground = new Background(theJPanel);

    } // end constructor SpriteEngine

    private long calcRedrawPeriod() {
	long rPeriod;

	rPeriod = (long) 1000.0 / myFramesPerSecond; // in ms
	rPeriod = rPeriod * 1000000L; // in ns
	return rPeriod;
    }

    // ------------- game life cycle methods ------------

    /**
     * "startEngine" method. Start the thread.
     */
    public void startEngine()
    // initialise and start the thread
    {
	if (myThread == null || !running) {
	    myThread = new Thread(this);
	    myThread.start();
	}
    }

    /**
     * "pauseEngine" method. Pause SpriteEngine.
     */
    public void pauseEngine() {
	isPaused = true;
    }

    /**
     * "resumeEngine" method. Resume after pause.
     */
    public void resumeEngine() {
	isPaused = false;
    }

    /**
     * "stopEngine" method. Stop SpriteEngine.
     */
    public void stopEngine() {
	running = false;
    }

    /**
     * "run" method : needed for Runnable interface Main loop for the Sprite
     * Engine. Moves sprites and redraws scene.
     */
    public void run() {
	long beforeTime;
	Image backBuffer;

	running = true;
	isPaused = false;

	// Enter main engine cycle.
	while (running) {
	    beforeTime = System.nanoTime();

	    // Update the sprite positions etc and re-draw.
	    updateEngine(); // update sprites

	    backBuffer = myCanvas.createImage(myCanvas.getWidth(), myCanvas
		    .getHeight());

	    // Draw background to image buffer
	    myBackground.draw(backBuffer);

	    // Draw sprites to image buffer
	    mySprites.drawAll(backBuffer);

	    // ==================================
	    paintToCanvas(backBuffer); // draw the buffer on-screen
	    pause(beforeTime); // put thread to sleep
	}

    } // end of run()

    /**
     * "updateEngine" method. Make any updates to graphics before next redraw
     * (i.e. move sprites)
     */
    private void updateEngine() {
	mySprites.moveAll();
    }

    /**
     * "paintToCanvas" method. Copy back buffer to the JPanel.
     * 
     * @param backBuffer
     *            - the back buffer in which new image has been built up.
     */
    private void paintToCanvas(Image backBuffer) {
	Graphics g;
	try {
	    g = myCanvas.getGraphics();
	    if ((g != null) && (backBuffer != null))
		g.drawImage(backBuffer, 0, 0, null);
	    // Sync the display on some systems.
	    // (on Linux, this fixes event queue problems)
	    Toolkit.getDefaultToolkit().sync();
	    g.dispose();
	} catch (Exception e) // quite commonly seen at applet destruction
	{
	    System.out.println("Graphics error: " + e);
	}

    }

    /**
     * "pause" method. Puts thread to sleep for the rest of the cycle.
     * Calculates how much sleep time is available. If no sleep time for several
     * refreshes, it will yield control to allow other threads to run.
     * 
     * @param beforeTime
     *            - Time at which this re-draw started. Used to calculate the
     *            time taken to re-draw and hence how much time to pause for.
     */
    private void pause(long beforeTime) {
	long afterTime, timeDiff, sleepTime;
	long overSleepTime = 0L;
	long excess = 0L;

	afterTime = System.nanoTime();
	timeDiff = afterTime - beforeTime;
	sleepTime = (redrawPeriod - timeDiff) - overSleepTime;

	if (sleepTime > 0) { // some time left in this cycle
	    try {
		Thread.sleep(sleepTime / 1000000L); // nano -> ms
	    } catch (InterruptedException ex) {
	    }

	    overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
	} else { // sleepTime <= 0; the frame took longer than the period
	    excess -= sleepTime; // store excess time value
	    overSleepTime = 0L;
	    if (++noSleepCount >= NO_DELAYS_PER_YIELD) {
		Thread.yield(); // give another thread a chance to run
		noSleepCount = 0;
	    }
	}

	// beforeTime = System.nanoTime();

	/*
	 * If frame animation is taking too long, update the game state without
	 * rendering it, to get the updates/sec nearer to the required FPS.
	 */
	int skips = 0;
	while ((excess > redrawPeriod) && (skips < MAX_FRAME_SKIPS)) {
	    excess -= redrawPeriod;
	    updateEngine(); // update state but don't render
	    skips++;
	}

    } // end of pause(..)

    // =====Background settings ==========
    /**
     * Set background to a simple colour fill (no image)
     * 
     * @param theColor
     *            - Chosen background colour
     */
    public void setBackColour(Color theColour) {
	myBackground.setBackColour(theColour);
    }

    /**
     * Set background to be an image
     * 
     * @param filename
     *            - filename of background image (full path-name)
     */
    public void setBackgroundImage(String filename) {
	myBackground.setBackground(filename);
    }

    // ===== Sprite interactions =========

    /**
     * "addSprite" method. Add this new sprite to the SpriteArray.
     * 
     * @param theSprite
     *            - the new sprite
     */
    public int addSprite(Sprite theSprite) {
	if (theSprite == null) {
	    System.out.println("SpriteEngine.addSprite: theSprite is null");
	    return -1;
	} else {
	    System.out.println("SpriteEngine.addSprite: theSprite is Ok");
	    return mySprites.add(theSprite);
	}
    }

    /**
     * "getSprite" method. Returns the sprite at given index position.
     * 
     * @param theIndex
     *            index no of requested sprite
     * @return Sprite requested
     */
    public Sprite getSprite(int theIndex) {
	return mySprites.get(theIndex);
    }

} // end of class SpriteEngine
