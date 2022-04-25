package spriteTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

/**
 * A Sprite graphics engine, running in its own thread. Handles all of the graphical side
 * of a sprite-based game, etc. Handles semi-autonomous sprites. Related classes:
 * Background, ImageLibrary, Sprite, SpriteArray, SpriteObserver
 * @author Peter Wilson. (Adapted from code by A. Davison
 *         "Killer games programming in Java".)
 * @modified Joshua Woodyatt.
 * @version: 1.0 January 2009.
 */
public class SpriteEngine implements Runnable {
  /** Drawing surface */
  private JPanel           myCanvas;
  /** Background for animation */
  private Background       myBackground;
  /** Board dimensions */
  private int              boardWidth, boardHeight;
  /** Current Viewing Area (VA) position dimensions */
  private int              vaX, vaY;
  /** Current Viewing Area (VA) dimensions */
  private int              vaWidth, vaHeight;
  /** Collection of sprites */
  private SpriteArray      mySprites           = new SpriteArray();
  /** Image library */
  private ImageLibrary     myImageLibrary;
  /**
   * Refresh rate - in frames per second. Defaults to 25 frames/sec
   */
  private int              myFramesPerSecond   = 25;                // 25
  /** time(ms) for each frame */
  private long             redrawPeriod        = calcRedrawPeriod();
  private int              noSleepCount        = 0;
  /** max. no. of unrendered updates before forcing a redraw */
  private static int       MAX_FRAME_SKIPS     = 5;
  private static final int NO_DELAYS_PER_YIELD = 16;
  /*
   * Number of frames with a delay of 0 ms before the animation thread yields to other
   * running threads.
   */

  /** Animation thread */
  private Thread           myThread;
  /** Is program running? */
  private volatile boolean running             = false;
  /** Is program paused? */
  private volatile boolean isPaused            = false;

  /** Is user following a sprite? */
  private boolean          followingASprite    = false;
  /** The sprite being followed */
  private Sprite           spriteBeingFollowed;

  // ========= Constructor ===============
  public SpriteEngine(JPanel theJPanel, String imageDefinitionFileName,
      SpriteObserver theSpriteObserver) {
    // **** Store link to JPanel
    myCanvas = theJPanel;

    // Initialise board and view area equal to JPanel
    boardWidth = myCanvas.getWidth();
    boardHeight = myCanvas.getHeight();
    vaX = 0;
    vaY = 0;
    vaWidth = boardWidth;
    vaHeight = boardHeight;

    // **** Instantiate and load the Image Library
    myImageLibrary = new ImageLibrary(imageDefinitionFileName);

    // **** Initialise the static values of the Sprite class.
    // Sprite.initialiseSprites(this, myImageLibrary, theJPanel, theSpriteObserver);
    Sprite.initialiseSprites(myImageLibrary, theJPanel, theSpriteObserver, boardWidth, boardHeight);

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

  /** "startEngine" method. Start the thread. */
  public void startEngine()
  // initialise and start the thread
  {
    if (myThread == null || !running) {
      myThread = new Thread(this);
      myThread.start();
    }
  }

  /** "pauseEngine" method. Pause SpriteEngine. */
  public void pauseEngine() {
    isPaused = true;
  }

  /** "resumeEngine" method. Resume after pause. */
  public void resumeEngine() {
    isPaused = false;
  }

  /** "stopEngine" method. Stop SpriteEngine. */
  public void stopEngine() {
    running = false;
  }

  /** ****MODIFIED THIS METHOD ****
   * "run" method : needed for Runnable interface Main loop for the Sprite Engine.
   * Moves sprites and redraws scene.
   */
  public void run() {
    long beforeTime;
    Image backBuffer;

    running = true;
    isPaused = false;

    float scaleX, scaleY;
    float dispX, dispY;

    // Enter main engine cycle.
    while (running) {
      beforeTime = System.nanoTime();

      // Update the sprite positions etc and re-draw.
      updateEngine(); // update sprites

      backBuffer = myCanvas.createImage(myCanvas.getWidth(), myCanvas.getHeight());
      
      //**** If user is following a sprite.
      if (followingASprite) {
        //**** Get the position of the sprite being followed
        Object spritePosition[] = getSprite(getSpriteBeingFollowed().getID()).getPosition();
        //System.out.println("Sprite X Position: " + spritePosition[0]);

        //**** Set the Viewing Area accordingly with -65y offset.
        setVAOrigin(((Number)spritePosition[0]).intValue() - (vaWidth/2), 
                    (((Number)spritePosition[1]).intValue() - (vaHeight/2)) - 65);
        //System.out.println("Sprite Y Position: " + spritePosition[1]);
      }
      myBackground.draw(backBuffer, vaX, vaY, vaWidth, vaHeight);
      
      // Draw sprites to image buffer
      // System.out.println("Engine.Run: Canvas width = " + myCanvas.getWidth() +
      // ", vaWidth = " + vaWidth);
      // System.out.println("            vaX = " + vaX);
      scaleX = (float) myCanvas.getWidth() / (float) vaWidth;
      scaleY = (float) myCanvas.getHeight() / (float) vaHeight;
      dispX = vaX * scaleX;
      dispY = vaY * scaleY;
      // System.out.println("            scaleX = " + scaleX + ", dispX = " + dispX);
      mySprites.drawAll(backBuffer, scaleX, scaleY, dispX, dispY);

      // ==================================
      paintToCanvas(backBuffer); // draw the buffer on-screen
      pause(beforeTime); // put thread to sleep
    }

  } // end of run()

  /** **** MODIFIED THIS METHOD ****
   * "updateEngine" method.
   * Make any updates to graphics before next redraw (i.e. move sprites)
   */
  private void updateEngine() {
    if (!followingASprite) {
      //**** If not following a sprite
      mySprites.moveAll();
    } else {
      //**** If following a sprite
      mySprites.parallaxMove(getSpriteBeingFollowed().getID());
    }
  }

  /**
   * "paintToCanvas" method. Copy back buffer to the JPanel.
   * @param backBuffer
   *          - the back buffer in which new image has been built up.
   */
  private void paintToCanvas(Image backBuffer) {
    Graphics g;
    try {
      g = myCanvas.getGraphics();
      if ((g != null) && (backBuffer != null))
        g.drawImage(backBuffer, 0, 0, null);
      // g.drawImage(backBuffer, 0,0, myCanvas.getWidth(), myCanvas.getHeight(), vaX, vaY,
      // vaX+vaWidth, vaY+vaHeight,null);
      // g.drawImage(backBuffer, 0,0, myCanvas.getWidth(), myCanvas.getHeight(), 0, 0,
      // vaWidth, vaHeight,null);

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
   * "pause" method. Puts thread to sleep for the rest of the cycle. Calculates how much
   * sleep time is available. If no sleep time for several refreshes, it will yield
   * control to allow other threads to run.
   * @param beforeTime
   *          - Time at which this re-draw started. Used to calculate the time taken to
   *          re-draw and hence how much time to pause for.
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
     * If frame animation is taking too long, update the game state without rendering it,
     * to get the updates/sec nearer to the required FPS.
     */
    int skips = 0;
    while ((excess > redrawPeriod) && (skips < MAX_FRAME_SKIPS)) {
      excess -= redrawPeriod;
      updateEngine(); // update state but don't render
      skips++;
    }

  } // end of pause(..)

  // ======= PAN & ZOOM =================

  /** **** MODIFIED THIS METHOD  ****
   * Pan - change View Area coords. Change 'int panBy' to modify pixel movement when
   * panning.
   * @param - 1-4 or 6-9 (directions as in numeric pad)
   */
  public void pan(int direction) {
    int panBy = 15; // modify to change pan amount.
    if (!followingASprite) {
      //**** Can only pan if not following a sprite.
      if (vaWidth != boardWidth) {
        //**** can only pan if zoomed in.
        switch (direction) {
        case 1: // Down and Left
          vaX -= panBy;
          vaY += panBy;
          break;
        case 2: // Down
          vaY += panBy;
          break;
        case 3: // Down and Right
          vaX += panBy;
          vaY += panBy;
          break;
        case 4: // Left
          vaX -= panBy;
          break;
        case 6: // Right
          vaX += panBy;
          break;
        case 7: // Up and Left
          vaX -= panBy;
          vaY -= panBy;
          break;
        case 8: // Up
          vaY -= panBy;
          break;
        case 9: // Up and Right
          vaX += panBy;
          vaY -= panBy;
          break;
        default: // error
          System.out.println("Error - " + direction + " is invalid for Pan direction");
        } // end switch
      } // END if (vaWidth != boardWidth)
    }
  }

  /** **** MODIFIED THIS METHOD ****
   * Zoom - change View Area for zoom in/out
   * @param direction - 5 zooms in, 0 zooms out.
   */
  public void zoom(int direction) {
      switch (direction) {
      case 0: // zoom out
        if (!followingASprite) {
          //**** If not following a sprite zoom out normally.
          vaWidth = getVAWidth() * 2;
          vaHeight = getVAHeight() * 2;
          vaX = (getBoardWidth() / 2) - (getVAWidth() / 2);
          vaY = (getBoardHeight() / 2) - (getVAHeight() / 2);
        } else {
          followingASprite = false;
          resetViewingArea();
        }
        break;
      case 5: // zoom in directly by user input
        if (!followingASprite) {
          //**** Can only zoom in if not following a sprite.
          vaWidth = getVAWidth() / 2;
          vaHeight = getVAHeight() / 2;
          vaX = (getBoardWidth() / 2) - (getVAWidth() / 2);
          vaY = (getBoardHeight() / 2) - (getVAHeight() / 2);
        }
      case 6: // zoom in - is not called directly by user input
        if (followingASprite) {
          //****  zoom in but vaX and vaY are set in run method.
          vaWidth = getBoardWidth() / 2;
          vaHeight = getBoardHeight() / 2;
        }
        break;
      default: // error
        System.out.println("Error - " + direction + " is invalid for Zoom direction");
      }
    //System.out.println("Zoom method - vaWidth = " + vaWidth + ", vaHeight = " + vaHeight);
    //System.out.println("Zoom method - vaX = " + vaX + ", vaY = " + vaY);
  }
  
  /** **** ADDED THIS METHOD ****
   * Resets viewing area to board and sprite default values.
   */
  public void resetViewingArea() {
    System.out.println("resetting viewing area");
    vaWidth = getBoardWidth();
    vaHeight = getBoardHeight();
    vaX = 0;
    vaY = 0;
    followingASprite = false;
    mySprites.resetScene();
  }

  /** **** ADDED THIS METHOD ****
   * Follows sprite with specified sprite ID.
   * If sprite with ID specified not found operation
   * is resumed normally.
   * @param spriteID - sprite of ID to follow.
   */
  public void followSprite(int spriteID) {
    try {
      if (followingASprite) {
        // **** If switched immediately from following a previous sprite.
        resetViewingArea();
      }
      // **** If sprite with ID is found the try block will be successful
      spriteBeingFollowed = getSprite(spriteID);
      followingASprite = true;
      // System.out.println("Following Sprite with ID " + spriteID + "."
      // + spriteBeingFollowed.getCharacterName());
      
      // **** Follow this sprite with command zoom(6)
      zoom(6);
    } catch (Exception e) {
      // **** If caught error in 'spriteBeingFollowed = getSprite(spriteID)'
      followingASprite = false;
      resetViewingArea();
      System.out.println("Sprite with ID " + spriteID + " not found or cannot be followed.");
    }
  }

  // ===== Background settings ==========
  /**
   * Set background to a simple colour fill (no image)
   * @param theColor - Chosen background colour
   */
  public void setBackColour(Color theColour) {
    myBackground.setBackColour(theColour);
  }

  /**
   * Set background to be an image
   * @param filename
   *          - filename of background image (full path-name)
   */
  public void setBackgroundImage(String filename) {
    myBackground.setBackground(filename);
  }

  /**
   * Change the dimensions of the game board.
   * @param theWidth
   * @param theHeight
   */
  public void setBoardSize(int theWidth, int theHeight) {
    boardWidth = theWidth;
    boardHeight = theHeight;
    vaX = 0;
    vaY = 0;
    vaWidth = boardWidth;
    vaHeight = boardHeight;
    myBackground.setBoardSize(boardWidth, boardHeight);
    Sprite.setBoardSize(boardWidth, boardHeight);
  }

  /**
   * Set coords of origin (top-left) for View Area
   * @param X
   * @param Y
   */
  public void setVAOrigin(int x, int y) {
    vaX = x;
    vaY = y;
  }

  /**
   * Set size of View Area
   * @param theWidth
   * @param theHeight
   */
  public void setVASize(int theWidth, int theHeight) {
    vaWidth = theWidth;
    vaHeight = theHeight;
  }

  /**
   * Set origin and size of View Area
   * @param theX
   * @param theY
   * @param theWidth
   * @param theHeight
   */
  public void setVADimensions(int theX, int theY, int theWidth, int theHeight) {
    setVAOrigin(theX, theY);
    setVASize(theWidth, theHeight);
  }

  /** **** ADDED THIS METHOD. ****
   * Tells the program whether or not the view area is
   * following a specific sprite.
   * @param value
   */
  public void setFollowingASprite(boolean value) {
    followingASprite = value;
  }

  /** **** ADDED THIS METHOD. ****
   * @param spriteBeingFollowed
   *          the spriteBeingFollowed to set
   */
  public void setSpriteBeingFollowed(Sprite spriteBeingFollowed) {
    this.spriteBeingFollowed = spriteBeingFollowed;
  }

  /**
   * returns the width of game board
   * @return
   */
  public int getBoardWidth() {
    return boardWidth;
  }

  /**
   * returns the height of game board
   * @return
   */
  public int getBoardHeight() {
    return boardHeight;
  }

  /**
   * Return the View Area's origin coords
   * @return
   */
  public int[] getVAOrigin() {
    int[] origin = new int[2];
    origin[0] = vaX;
    origin[1] = vaY;
    return origin;
  }

  /**
   * Return the width of View Area
   * @return (int) vaWidth.
   */
  public int getVAWidth() {
    return vaWidth;
  }

  /**
   * returns the height of View Area
   * @return (int) vaHeight.
   */
  public int getVAHeight() {
    return vaHeight;
  }
  
  /**
   * "getSprite" method. Returns the sprite at given index position.
   * @param theIndex - index no of requested sprite
   * @return Sprite - requested
   */
  public Sprite getSprite(int theIndex) {
    return mySprites.get(theIndex);
  }

  /** **** ADDED THIS METHOD ****
   * Is the view area following a specific sprite?
   * @return (boolean) followingSprite
   */
  public boolean isFollowingASprite() {
    return followingASprite;
  }

  /** **** ADDED THIS METHOD ****
   * @return the spriteBeingFollowed
   */
  public Sprite getSpriteBeingFollowed() {
    return spriteBeingFollowed;
  }

  // ===== Sprite interactions =========

  /**
   * "addSprite" method. Add this new sprite to the SpriteArray.
   * @param theSprite - the new sprite
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

} // end of class SpriteEngine