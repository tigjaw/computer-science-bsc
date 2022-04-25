package spriteTest;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import spriteTest.SpriteEngine;
import spriteTest.Sprite;
import spriteTest.SpriteObserver;

class TestJPanel extends JPanel implements SpriteObserver, KeyListener {

  /** Instance of SpriteEngine */
  SpriteEngine                se;
  /** Path to image info file */
  private final static String IMAGE_INFO_FILE = "Images/imsInfo.txt";
  /** Path to background scene image */
  private final static String BACK_INFO_FILE  = "Images/mybackground/background.jpg";
  //private final static String BACK_INFO_FILE  = "Images/backScene.jpg";
  int                         aIndex, bIndex, mIndex;
  /** Image containing controls, press F1 to display. */
  Sprite f1pressed;
  boolean displayF1Panel = true;

  public TestJPanel() {
    setFocusable(true);
    this.requestFocus();
    // readyForTermination();
    HandleKeys();
  }

  /**
   * Handle key presses - Call appropriate zoom/pan
   * Controls:
   * F1 = Controls Panel on/off (Displayed on Program Start)
   * Number Keys 0-9 = Follow Sprite with that ID
   *                 ~ Cats accessed via 1 and 3.
   * Numpad 1,2,3,4,6,7,8,9 = Pan in number's direction.
   * Numpad 5        = Zoom In,
   * Numpad 0        = Zoom Out / Stop Following
   *                 ~ Also Resets Scene.
   */
  private void HandleKeys() {
    addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
        case KeyEvent.VK_F1:
          //**** Press F1 to display Controls (F1 Panel)
          if (displayF1Panel == false) {
            if (se.isFollowingASprite() == false) {
              System.out.println("Displaying F1 Panel");
              displayF1Panel = true;
              f1pressed.setVisible(true);
            }
          } else {
            displayF1Panel = false;
            f1pressed.setVisible(false);
          }
          /*
          System.out.println("Displaying Help");
          displayF1Panel = !displayF1Panel;
          f1pressed.setVisible(displayF1Panel);
          */
          break;
        case KeyEvent.VK_0:
          System.out.println("Pressed key 0");
          se.followSprite(0);
          break;
        case KeyEvent.VK_1:
          System.out.println("Pressed key 1");
          se.followSprite(1);
          break;
        case KeyEvent.VK_2:
          System.out.println("Pressed key 2");
          se.followSprite(2);
          break;
        case KeyEvent.VK_3:
          System.out.println("Pressed key 3");
          se.followSprite(3);
          break;
        case KeyEvent.VK_4:
          System.out.println("Pressed key 4");
          se.followSprite(4);
          break;
        case KeyEvent.VK_5:
          System.out.println("Pressed key 5");
          se.followSprite(5);
          break;
        case KeyEvent.VK_6:
          System.out.println("Pressed key 6");
          se.followSprite(6);
          break;
        case KeyEvent.VK_7:
          System.out.println("Pressed key 7");
          se.followSprite(7);
          break;
        case KeyEvent.VK_8:
          System.out.println("Pressed key 8");
          se.followSprite(8);
          break;
        case KeyEvent.VK_9:
          System.out.println("Pressed key 9");
          se.followSprite(9);
          break;
        case KeyEvent.VK_NUMPAD0:
          // case KeyEvent.VK_0:
          System.out.println("Pressed NUMPAD_0");
          se.zoom(0);
          break;
        case KeyEvent.VK_NUMPAD5:
          // case KeyEvent.VK_5:
          System.out.println("Pressed NUMPAD_5");
          se.zoom(5);
          break;
        case KeyEvent.VK_NUMPAD1:
          // case KeyEvent.VK_1:
          System.out.println("Pressed NUMPAD_1");
          se.pan(1);
          break;
        case KeyEvent.VK_NUMPAD2:
          // case KeyEvent.VK_2:
          System.out.println("Pressed NUMPAD_2");
          se.pan(2);
          break;
        case KeyEvent.VK_NUMPAD3:
          // case KeyEvent.VK_3:
          System.out.println("Pressed NUMPAD_3");
          se.pan(3);
          break;
        case KeyEvent.VK_NUMPAD4:
          // case KeyEvent.VK_4:
          System.out.println("Pressed NUMPAD_4");
          se.pan(4);
          break;
        case KeyEvent.VK_NUMPAD6:
          // case KeyEvent.VK_6:
          System.out.println("Pressed NUMPAD_6");
          se.pan(6);
          break;
        case KeyEvent.VK_NUMPAD7:
          // case KeyEvent.VK_7:
          System.out.println("Pressed NUMPAD_7");
          se.pan(7);
          break;
        case KeyEvent.VK_NUMPAD8:
          // case KeyEvent.VK_8:
          System.out.println("Pressed NUMPAD_8");
          se.pan(8);
          break;
        case KeyEvent.VK_NUMPAD9:
          // case KeyEvent.VK_9:
          System.out.println("Pressed NUMPAD_9");
          se.pan(9);
          break;

        }
      }
    });
  }

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
    return new Dimension(800, 400);
  }

  /**
   * Instantiate the sprite engine and start it running. Creates and loads some sprites.
   */
  public void start() {

    // Instantiate the new Sprite Engine
    se = new SpriteEngine(this, IMAGE_INFO_FILE, this);

    // Set background image for sprite engine
    se.setBackgroundImage(BACK_INFO_FILE);
    se.setBoardSize(2000, 1000);

    // start sprite engine (animation running)
    se.startEngine();

    // ==== CREATE NEW SPRITES ====
    
    // **** Mobile Sprites ****
    Sprite a = new Sprite("catRight");
    Sprite b = new Sprite("catLeft");
    Sprite m = new Sprite("MammothRight");
    
    // ==== BACKGROUND SCENERY SPRITES ====
    Sprite theSun = new Sprite("thesun");
    theSun.setCharacterName("The_Sun");
    /* ****
     * A Negative Z value will make the scenery
     * sprite move at the given speed in
     * parallel motion to the moving sprite.
     * The scenery will appear to follow the
     * moving sprite instead of moving in the
     * opposite direction.
     */
    theSun.setPosition(720, 450, (float) -2);
    
    /* ****
     * Background Sprites should be given higher
     * Z values, the higher the number the closer
     * to the horizon.
     */
    Sprite mountainsNear = new Sprite("mountainsnear");
    mountainsNear.setCharacterName("Mountains_Near");
    mountainsNear.setPosition(1000, 560, (float) 2.10);
    
    Sprite mountainsFar = new Sprite("mountainsfar");
    mountainsFar.setCharacterName("Mountains_Far");
    mountainsFar.setPosition(1000, 530, (float) 3.5);
    
    // ==== FOREGROUND SCENERY SPRITES ====
    
    /* ****
     * Foreground Sprites should be given lower
     * Z values, the lower the number the closer
     * the sprite is to the viewing angle perspective.
     * 
     * A Z value of 0 ensures no 'perspective movement'.
     * The sprite will still appear to be moving but this
     * is only due to the opposing movement of the
     * sprite being followed.
     */
    
    Sprite hut = new Sprite("hut");
    hut.setCharacterName("Hut");
    hut.setPosition(600, 650, (float) 0.7);
    Sprite hut2 = new Sprite("hut");
    hut2.setCharacterName("Hut2");
    hut2.setPosition(4500, 650, (float) 0.3);
    
    Sprite tree = new Sprite("tree");
    tree.setCharacterName("Tree");
    tree.setPosition(3200, 750, (float) 0.2);
    Sprite tree2 = new Sprite("tree");
    tree2.setCharacterName("Tree2");
    tree2.setPosition(-1000, 750, (float) 0.2);
    Sprite tree3 = new Sprite("tree");
    tree3.setCharacterName("Tree3");
    tree3.setPosition(-3000, 750, (float) 0.2);
    
    Sprite grass = new Sprite("grasspatch");
    grass.setCharacterName("Grass_Patch");
    grass.setPosition(1200, 850, (float) 0.10);
    Sprite grass2 = new Sprite("grasspatch");
    grass2.setCharacterName("Grass_Patch2");
    grass2.setPosition(4500, 850, (float) 0.2);
    Sprite grass3 = new Sprite("grasspatch");
    grass3.setCharacterName("Grass_Patch3");
    grass3.setPosition(7000, 850, (float) 0.2);
    
    // ==== MOVING SPRITES ====
    
    /* ****
     * If a Sprite's speed is set that sprite
     * will then move by its own accord irrelevant 
     * of the perspective of the sprite being
     * followed.
     * It should not matter what Z value the sprite
     * has so long as the speed is set.
     */
    
    // Set initial position and speed for sprite a
    a.setCharacterName("Cat");
    a.setPosition(200, 700, 1);
    a.setSpeed(6, 0);
    // Set initial values for sprite b.
    b.setCharacterName("cat");
    b.setPosition(1500, 760, 1);
    b.setSpeed(-6, 0);
    //b.setVisible(false);
    // Set initial values for sprite m.
    m.setCharacterName("Mammoth");
    m.setPosition(1000, 500, 1);
    m.setSpeed(0, 0);
    m.setVisible(false);
    
    // ===== ADD SPRITES TO THE ENGINE  =====
    
    //**** Add BACKGROUND Sprites
    se.addSprite(theSun);
    se.addSprite(mountainsFar);
    se.addSprite(mountainsNear);
    se.addSprite(hut);
    se.addSprite(hut2);
    
    //**** Add MOVING Sprites
    se.addSprite(a);
    se.addSprite(b);
    se.addSprite(m);
    
    //**** Add FOREGROUND sprites
    se.addSprite(tree);
    se.addSprite(tree2);
    se.addSprite(tree3);
    se.addSprite(grass);
    se.addSprite(grass2);
    se.addSprite(grass3);
    
    //**** F1 Controls Panel Sprite
    f1pressed = new Sprite("controlsF1");
    f1pressed.setPosition(1000, 500, 0);
    f1pressed.setVisible(displayF1Panel);
    se.addSprite(f1pressed);

  } // end of start()

  /** **** MODIFIED THIS METHOD ****
   * "update" method. Required by SpriteObserver class. Handle events raised by Sprites.
   */
  public void update(Sprite theSprite) {
    
    int spriteId = theSprite.getID();
    Object[] position = theSprite.getPosition();

    // System.out.print("Sprite No.:" + spriteId + " reports ");
    switch (theSprite.getEventCode()) {
    case LEFT_EDGE:
      // System.out.println( "at left edge (" + position[0] + "," + position[1] + ")");
      if (theSprite.getCharacterName().equalsIgnoreCase("cat")) {
        theSprite.setSpeed(theSprite.getSpeed()[0] - (theSprite.getSpeed()[0] * 2), 0);
        theSprite.setActionName("catRight");
      } else {
        theSprite.setSpeed(theSprite.getSpeed()[0] - (theSprite.getSpeed()[0] * 2), 0);
        theSprite.setActionName("MammothRight");
      }
      break;
    case RIGHT_EDGE:
      // System.out.println("at right edge");
      if (theSprite.getCharacterName().equalsIgnoreCase("Mammoth")) {
        theSprite.setSpeed(theSprite.getSpeed()[0] - (theSprite.getSpeed()[0] * 2), 0);
        theSprite.setActionName("MammothLeft");
      } else {
        theSprite.setSpeed(theSprite.getSpeed()[0] - (theSprite.getSpeed()[0] * 2), 0);
        theSprite.setActionName("catLeft");
      }
      break;
    case RUNNING_EAST:
      //**** Event occurs if original speed is positive
      if (theSprite.getCharacterName().equalsIgnoreCase("Cat")) {
        theSprite.setActionName("catRight");
      }
      break;
    case RUNNING_WEST:
      //**** Event occurs if original speed is negative
      if (theSprite.getCharacterName().equalsIgnoreCase("Cat")) {
        theSprite.setActionName("catLeft");
      }
      break;
    case RESETTING_SPRITE:
      //**** Resets Scene to original settings.
      /*
      System.out.println("Resetting Sprite");
      System.out.print(theSprite.getCharacterName() + " - x = " + (int) theSprite.getxLocation()
          + ", y = " + (int) theSprite.getyLocation() + ": ");
      System.out.println("actionName = " + theSprite.getOriginalActionName() + ", X Speed = "
          + theSprite.getOriginalXSpeed() + ", Y Speed = " + theSprite.getOriginalYSpeed());
      */
      theSprite.setPosition((int) theSprite.getxLocation(), (int) theSprite.getyLocation());
      theSprite.setActionName(theSprite.getOriginalActionName());
      if (theSprite.isItMoving()) {
        theSprite.resetSpeed();
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
      break;
    case END_OF_IMAGE_SEQUENCE:
      if (theSprite.getCharacterName().equalsIgnoreCase("Cat")) {
        theSprite.setLive(false);
        System.out.println(theSprite.getCharacterName() + " sprite no. " + theSprite.getID()
            + " is dead");
      } else {
        theSprite.setActionName("MammothRight");
        theSprite.setSpeed(8, 0);
      }
      break;
    default:
      System.out.println(" EventCode = " + theSprite.getEventCode());
    }
  }

  /**
   * Process the key typing on the numberic keys only.
   * @param e
   */
  public void keyTyped(KeyEvent e) {
  }

  public void keyPressed(KeyEvent e) {
    int[] origin = se.getVAOrigin();
    int vaWidth = se.getVAWidth();
    int vaHeight = se.getVAHeight();
    int xMove = vaWidth * 3 / 4;
    int yMove = vaHeight * 3 / 4;
    int x, y;

    switch (e.getKeyCode()) {
    case KeyEvent.VK_4:
      x = origin[0] - xMove;
      y = origin[1];
      break;
    case KeyEvent.VK_6:
      x = origin[0] + xMove;
      y = origin[1];
      break;

    }

  }

  public void keyReleased(KeyEvent e) {
  }

}