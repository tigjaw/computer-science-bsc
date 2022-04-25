package spriteTest;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Image Library class to load, store and process 
 * sprite cels.
 * 
 * @author Peter Wilson. 
 *         (Adapted from Andrew Davison,"Killer Game Programming in Java")         
 * @version 1.0, January 2009
 */
public class ImageLibrary {

	  //Location of images on Office PC;
	  //private final static String IMAGE_DIR = "U:/Modules/Current Modules/CP2137/CP2137Workspace/SpriteTestv1/src/spriteTestv1/Images/";
	  //Location of images on PC in package;
	  //private final static String IMAGE_DIR = "/Images/";
	  
	  // Location of images on laptop
	  //private final static String IMAGE_DIR = "/home/peter/CP2137/CP2137Workspace/SpriteTestv1/src/spriteTestv1/Images/";
	  /**
	   * Path name to Images directory.
	   * All animation cels MUST be stored here
	   */
	  private String IMAGE_DIR;           //Path name to Images directory

	  /**
	   * Hash table holding the animation image sequences.
	   *   key = filename prefix.
	   *   object = arraylists of BufferedImages
	   */
	  private HashMap<String, ArrayList<BufferedImage>> imagesMap; 

	  /**
	   * 
	   */
	  private HashMap<String, ArrayList<String>> gNamesMap;
	    /* The key is the 'g' <name> string, the object is an
	       ArrayList of filename prefixes for the group. This is used to 
	       access a group image by its 'g' name and filename. */

	  private GraphicsConfiguration gc;


	  /**
	   * Parameterised constructor 
	   * 
	   * @param fnm  = Text-file identifying animation image files.
	   *               (Full path-name of this file).
	   *               N.B. The image files must be in same sub-directory.                
	   */
	  public ImageLibrary(String fnm)
	  { 
		  initLoader();
		  
		  // separate the file name into directory and filename
		  String fileName;
		  int lastSlashIndex;
		  
		  lastSlashIndex = fnm.lastIndexOf("/");
		  
		  IMAGE_DIR = fnm.substring(0,lastSlashIndex+1);
		  fileName = fnm.substring(lastSlashIndex+1);
  
		  loadImagesFile(fileName);
		  System.out.println("Image Library created successfully");
		  
	  }  // end of ImagesLoader()


	  /**
	   * Initialise the loader 
	   */
	  private void initLoader()
	  {
	    imagesMap = new HashMap<String, ArrayList<BufferedImage>>();
	    gNamesMap = new HashMap<String, ArrayList<String>>();

	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
	  }  // end of initLoader()


	  /**
	   * "loadImagesFile" method.
	   * Process the text file <fnm> which contains the names of 
	   * the images to be used in the animations. 
	   * This loads the image sequences into the Hash tables. 
	   * 
	   * The text file contains one line per animation sequence, 
	   * defining the name of the sequence and how to access its
	   * images.  There are several possible formats for the images.
	   * 
	   * Possible formats of each line in the text file:
	   *   Image Strip: Images all in one file, stored side by side
	   *    s <filename> <numberOfCels> 
	   *   
	   *   One image only: Single image.
	   *    o <filename>                
	   *    
	   *   Group to be composed from separate image files.
	   *    g <sequence name> <filename> [<filename>]* 
	   *                   
	   * @param fnm - name of file holding the lists of character 
	   *              image cels to be used in this animation.
	   */
	  private void loadImagesFile(String fnm)
	  { 
	    String imsFNm = IMAGE_DIR +  fnm;
	    System.out.println("Reading file: " + imsFNm);
	    try {
   
	      File myFile = new File(imsFNm);
	      FileReader fr = new FileReader(myFile);
	      BufferedReader br = new BufferedReader(fr);
     
	      String line;
	      char ch;

	      while((line = br.readLine()) != null) {
	    	  
	    	  System.out.println("  Read line: " + line);
	    	  
	        if (line.length() == 0)  // blank line
	          continue;
	        if (line.startsWith("//"))   // comment
	          continue;
	        ch = Character.toLowerCase( line.charAt(0) );
	        
	        //Process line from file.
	        switch(ch){
	        case 's':
	        	getStripOfImages(line);
	        	break;
	        //case 'o':
	        //	getSingleImage(line);
	        // 	break;
	        //case 'g':
	        //	getGroupImages(line);
	        //	break;
	        //case 'n':
	        //	getNumberedImages(line);
	        //	break;
	        default:
	 	        System.out.println("Error in ImageLibrary.loadImagesFile(..)::Do not recognize line: " + line);
	        	break;
	        } // end of switch
	        
	      }
	      br.close();
	    } 
	    catch (IOException e) 
	    { System.out.println("Error reading file: " + imsFNm);
	      System.exit(1);
	    }
	  }  // end of loadImagesFile()


	  //============================================================
	  // --------- Loading image strips -------------------------------

	  /**
	   * "getStripOfImages(..)" method.
	   * Checks correctness of 
	   */
	  private void getStripOfImages(String line)
	  /* format:
	        s <filename> <number of cels>
	  */
	  {
	    StringTokenizer tokens = new StringTokenizer(line);

	    if (tokens.countTokens() != 3)
	      System.out.println("Wrong no. of arguments for " + line);
	    else {
	      tokens.nextToken();    // skip command label
	      System.out.println("Processing an s Line in text file. ");

	      String fnm = tokens.nextToken();
	      int number = -1;
	      try {
	        number = Integer.parseInt( tokens.nextToken() );
	      }
	      catch(Exception e)
	      { System.out.println("Number is incorrect for " + line);  }

	      loadStripOfImages(fnm, number);
	    }
	  }  // end of getStripImages()



	  public int loadStripOfImages(String fnm, int number)
	  /* Can be called directly, to load a strip file, <fnm>,
	     holding <number> images.
	  */
	  {
		//Get the file name (before the extension - e.g. "cats.gif" => prefix = "cats")
		String name = getPrefix(fnm);
		
		// Error if this file name is already in the Hash table
	    if (imagesMap.containsKey(name)) {
	      System.out.println( "Error: " + name + "already used");
	      return 0;
	    }
	    
	    // load the images into an image array
	    BufferedImage[] strip = loadStripImageArray(fnm, number);
	    if (strip == null)
	      return 0;

	    //Copy each image in strip array to the ArrayList
	    ArrayList<BufferedImage> imsList = new ArrayList<BufferedImage>();
	    int loadCount = 0;
	    System.out.print("  Adding " + name + "/" + fnm + "... ");
	    for (int i=0; i < strip.length; i++) {
	      loadCount++;
	      imsList.add(strip[i]);
	      System.out.print(i + " ");
	    }
	    System.out.println();

	    //If images copied then store im ImagesMap Hash table
	    if (loadCount > 0)
	        imagesMap.put(name, imsList);
	    else 
	        System.out.println("No images loaded for " + name);

	    return loadCount;
	  }  // end of loadStripImages()


	  public BufferedImage[] loadStripImageArray(String fnm, int number) 
	  /* Extract the individual images from the strip image file, <fnm>.
	     We assume the images are stored in a single row, and that there
	     are <number> of them. The images are returned as an array of
	     BufferedImages
	  */
	  {
	    // Return null if the no. of images is zero or less.
		if (number <= 0) {
	      System.out.println("number <= 0; returning null");
	      return null;
	    }

	    // Return null if can't load the file.
		BufferedImage stripIm;
	    if ((stripIm = loadImage(fnm)) == null) {
	      System.out.println("Returning null");
	      return null;
	    }

	    // Calculate width of each image
	    int imWidth = (int) stripIm.getWidth() / number;
	    int height = stripIm.getHeight();
	    int transparency = stripIm.getColorModel().getTransparency();

	    // Create array to hold the strip images.
	    BufferedImage[] strip = new BufferedImage[number];
	    Graphics2D stripGC;

	    // each BufferedImage from the strip file is stored in strip[]
	    for (int i=0; i < number; i++) {
	      strip[i] =  gc.createCompatibleImage(imWidth, height, transparency);
	       
	      // create a graphics context
	      stripGC = strip[i].createGraphics();
	      // stripGC.setComposite(AlphaComposite.Src);

	      // copy image into strip[i]
	      stripGC.drawImage(stripIm, 0,0, imWidth,height,i*imWidth,0, (i*imWidth)+imWidth,height, null);
	      stripGC.dispose();
	    } 
	    return strip;
	  } // end of loadStripImageArray()

    //=================================================================
	  
	  
	  // --------- load a single image -------------------------------

	  @SuppressWarnings("unused")
	private void getSingleImage(String line)
	  /* format:
	        o <fnm>
	  */
	  { StringTokenizer tokens = new StringTokenizer(line);

	    if (tokens.countTokens() != 2)
	      System.out.println("Wrong no. of arguments for " + line);
	    else {
	      tokens.nextToken();    // skip command label
	      System.out.print("o Line: ");
	      loadSingleImage( tokens.nextToken() );
	    }
	  }  // end of getFileNameImage()


	  public boolean loadSingleImage(String fnm)
	  // can be called directly
	  {
	 
		String name = getPrefix(fnm);

	 	if (imagesMap.containsKey(name)) {
	      System.out.println( "Error: " + name + "already used");
	      return false;
	    }

	    BufferedImage bi = loadImage(fnm);
	    if (bi != null) {
	      ArrayList<BufferedImage> imsList = new ArrayList<BufferedImage>();
	      imsList.add(bi);
	      imagesMap.put(name, imsList);
	      System.out.println("  Stored " + name + "/" + fnm);
	      return true;
	    }
	    else
	      return false;
	  }  // end of loadSingleImage()


	  private String getPrefix(String fnm)
	  // extract name before '.' of filename
	  {
	    int posn;
	    if ((posn = fnm.lastIndexOf(".")) == -1) {
	      System.out.println("No prefix found for filename: " + fnm);
	      return fnm;
	    }
	    else
	      return fnm.substring(0, posn);
	  } // end of getPrefix()



	  // --------- load numbered images -------------------------------

	  @SuppressWarnings("unused")
	private void getNumberedImages(String line)
	  /* format:
	        n <fnm*.ext> <number>
	  */
	  {
	    StringTokenizer tokens = new StringTokenizer(line);

	    if (tokens.countTokens() != 3)
	      System.out.println("Wrong no. of arguments for " + line);
	    else {
	      tokens.nextToken();    // skip command label
	      System.out.print("n Line: ");

	      String fnm = tokens.nextToken();
	      int number = -1;
	      try {
	        number = Integer.parseInt( tokens.nextToken() );
	      }
	      catch(Exception e)
	      { System.out.println("Number is incorrect for " + line);  }

	      loadNumImages(fnm, number);
	    }
	  }  // end of getNumberedImages()



	  public int loadNumImages(String fnm, int number)
	  /*  Can be called directly.
	      fnm is the filename argument in:
	           n <f*.ext> <number>
	  */
	  {
	    String prefix = null;
	    String postfix = null;
	    int starPosn = fnm.lastIndexOf("*");   // find the '*'
	    if (starPosn == -1) {
	      System.out.println("No '*' in filename: " + fnm);
	      prefix = getPrefix(fnm);
	    }
	    else {   // treat the fnm as prefix + "*" + postfix
	      prefix = fnm.substring(0, starPosn);
	      postfix = fnm.substring(starPosn+1);
	    }

	    if (imagesMap.containsKey(prefix)) {
	      System.out.println( "Error: " + prefix + "already used");
	      return 0;
	    }

	    return loadNumImages(prefix, postfix, number);
	  }  // end of loadNumImages()



	  private int loadNumImages(String prefix, String postfix, int number)
	  /* Load a series of image files with the filename format
	            prefix + <i> + postfix
	     where i ranges from 0 to number-1
	  */
	  { 
	    String imFnm;
	    BufferedImage bi;
	    ArrayList<BufferedImage> imsList = new ArrayList<BufferedImage>();
	    int loadCount = 0;

	    if (number <= 0) {
	      System.out.println("Error: Number <= 0: " + number);
	      imFnm = prefix + postfix;
	      if ((bi = loadImage(imFnm)) != null) {
	        loadCount++;
	        imsList.add(bi);
	        System.out.println("  Stored " + prefix + "/" + imFnm);
	      }
	    }
	    else {   // load prefix + <i> + postfix, where i = 0 to <number-1>
	      System.out.print("  Adding " + prefix + "/" + 
	                           prefix + "*" + postfix + "... ");
	      for(int i=0; i < number; i++) {
	        imFnm = prefix + i + postfix;
	        if ((bi = loadImage(imFnm)) != null) {
	          loadCount++;
	          imsList.add(bi);
	          System.out.print(i + " ");
	        }
	      }
	      System.out.println();
	    }

	    if (loadCount == 0)
	      System.out.println("No images loaded for " + prefix);
	    else 
	      imagesMap.put(prefix, imsList);

	    return loadCount;
	  }  // end of loadNumImages()





	  // ------ grouped filename seq. of images ---------


	  @SuppressWarnings("unused")
	private void getGroupImages(String line)
	  /* format:
	        g <name> <fnm>  [ <fnm> ]*
	  */
	  { StringTokenizer tokens = new StringTokenizer(line);

	    if (tokens.countTokens() < 3)
	      System.out.println("Wrong no. of arguments for " + line);
	    else {
	      tokens.nextToken();    // skip command label
	      System.out.print("g Line: ");

	      String name = tokens.nextToken();

	      ArrayList<String> fnms = new ArrayList<String>();
	      fnms.add( tokens.nextToken() );  // read filenames
	      while (tokens.hasMoreTokens())
	        fnms.add( tokens.nextToken() );

	      loadGroupImages(name, fnms);
	    }
	  }  // end of getGroupImages()



	  public int loadGroupImages(String name, ArrayList<String> fnms)
	  /* Can be called directly to load a group of images, whose
	     filenames are stored in the ArrayList <fnms>. They will
	     be stored under the 'g' name <name>.
	  */
	  {
	    if (imagesMap.containsKey(name)) {
	      System.out.println( "Error: " + name + "already used");
	      return 0;
	    }

	    if (fnms.size() == 0) {
	      System.out.println("List of filenames is empty");
	      return 0;
	    }

	    BufferedImage bi;
	    ArrayList<String> nms = new ArrayList<String>();
	    ArrayList<BufferedImage> imsList = new ArrayList<BufferedImage>();
	    String nm, fnm;
	    int loadCount = 0;

	    System.out.println("  Adding to " + name + "...");
	    System.out.print("  ");
	    for (int i=0; i < fnms.size(); i++) {    // load the files
	      fnm = (String) fnms.get(i);
	      nm = getPrefix(fnm);
	      if ((bi = loadImage(fnm)) != null) {
	        loadCount++;
	        imsList.add(bi);
	        nms.add( nm );
	        System.out.print(nm + "/" + fnm + " ");
	      }
	    }
	    System.out.println();

	    if (loadCount == 0)
	      System.out.println("No images loaded for " + name);
	    else {
	      imagesMap.put(name, imsList);
	      gNamesMap.put(name, nms);
	    }

	    return loadCount;
	  }  // end of loadGroupImages()


	  public int loadGroupImages(String name, String[] fnms)
	  // supply the group filenames in an array
	  {  
	    ArrayList<String> al = new ArrayList<String>( Arrays.asList(fnms) );
	    return loadGroupImages(name, al);  
	  }



	  // ------------------ access methods -------------------

	  public BufferedImage getImage(String name)
	  /* Get the image associated with <name>. If there are
	     several images stored under that name, return the 
	     first one in the list.
	  */
	  {
	    ArrayList<BufferedImage> imsList = (ArrayList<BufferedImage>) imagesMap.get(name);
	    if (imsList == null) {
	      System.out.println("No image(s) stored under " + name);  
	      return null;
	    }

	    // System.out.println("Returning image stored under " + name);  
	    return (BufferedImage) imsList.get(0);
	  }  // end of getImage() with name input;



	  public BufferedImage getImage(String name, int posn)
	  /* Get the image associated with <name> at position <posn>
	    in its list. If <posn> is < 0 then return the first image
	    in the list. If posn is bigger than the list's size, then
	    calculate its value modulo the size.
	  */
	  {
	    ArrayList<BufferedImage> imsList = (ArrayList<BufferedImage>) imagesMap.get(name);
	    if (imsList == null) {
	      System.out.println("No image(s) stored under " + name);  
	      return null;
	    }

	    int size = imsList.size();
	    if (posn < 0) {
	      // System.out.println("No " + name + " image at position " + posn +
	      //                      "; return position 0"); 
	      return (BufferedImage) imsList.get(0);   // return first image
	    }
	    else if (posn >= size) {
	      // System.out.println("No " + name + " image at position " + posn); 
	      int newPosn = posn % size;   // modulo
	      // System.out.println("Return image at position " + newPosn); 
	      return (BufferedImage) imsList.get(newPosn);
	    }

	    // System.out.println("Returning " + name + " image at position " + posn);  
	    return (BufferedImage) imsList.get(posn);
	  }  // end of getImage() with posn input;



	  public BufferedImage getImage(String name, String fnmPrefix)
	  /* Get the image associated with the group <name> and filename
	     prefix <fnmPrefix>. 
	  */
	  {
	    ArrayList<BufferedImage> imsList = (ArrayList<BufferedImage>) imagesMap.get(name);
	    if (imsList == null) {
	      System.out.println("No image(s) stored under " + name);  
	      return null;
	    }

	    int posn = getGroupPosition(name, fnmPrefix);
	    if (posn < 0) {
	      // System.out.println("Returning image at position 0"); 
	      return (BufferedImage) imsList.get(0);   // return first image
	    }

	    // System.out.println("Returning " + name + 
	    //                        " image with pair name " + fnmPrefix);  
	    return (BufferedImage) imsList.get(posn);
	  }  // end of getImage() with fnmPrefix input;



	  private int getGroupPosition(String name, String fnmPrefix)
	  /* Search the hashmap entry for <name>, looking for <fnmPrefix>.
	     Return its position in the list, or -1.
	  */
	  {
	    ArrayList<String> groupNames = (ArrayList<String>) gNamesMap.get(name);
	    if (groupNames == null) {
	      System.out.println("No group names for " + name);  
	      return -1;
	    }

	    String nm;
	    for (int i=0; i < groupNames.size(); i++) {
	      nm = (String) groupNames.get(i);
	      if (nm.equals(fnmPrefix))
	        return i;          // the posn of <fnmPrefix> in the list of names
	    }

	    System.out.println("No " + fnmPrefix + 
	                  " group name found for " + name);  
	    return -1;
	  }  // end of getGroupPosition()



	  public ArrayList<BufferedImage> getImages(String name)
	  // return all the BufferedImages for the given name
	  {
	    ArrayList<BufferedImage> imsList = (ArrayList<BufferedImage>) imagesMap.get(name);
	    if (imsList == null) {
	      System.out.println("No image(s) stored under " + name);  
	      return null;
	    }

	    System.out.println("Returning all images stored under " + name);  
	    return imsList;
	  }  // end of getImages();


	  public boolean isLoaded(String name)
	  // is <name> a key in the imagesMap hashMap?
	  {
	    ArrayList<BufferedImage> imsList = (ArrayList<BufferedImage>) imagesMap.get(name);
	    if (imsList == null)
	      return false;
	    return true;
	  }  // end of isLoaded()


	  public int numImages(String name)
	  // how many images are stored under <name>?
	  {
	    ArrayList<BufferedImage> imsList = (ArrayList<BufferedImage>) imagesMap.get(name);
	    if (imsList == null) {
	      System.out.println("No image(s) stored under " + name);  
	      return 0;
	    }
	    return imsList.size();
	  } // end of numImages()


	  // ------------------- Image Input ------------------

	  /* There are three versions of loadImage() here! They use:
	           ImageIO   // the preferred approach
	           ImageIcon
	           Image
	     We assume that the BufferedImage copy required an alpha
	     channel in the latter two approaches.
	  */

	   public BufferedImage loadImage(String fnm) 
	   /* Load the image from <fnm>, returning it as a BufferedImage
	      which is compatible with the graphics device being used.
	      Uses ImageIO.
	   */
	   {
	     try {
	       System.out.println("In loadImage - file is : " + IMAGE_DIR + fnm);
	       
	       //The next line expects the image files to be in a "jar" resource
	       // Problem here loading the image file
	       //BufferedImage im =  ImageIO.read(getClass().getResource(IMAGE_DIR + fnm) );
	       BufferedImage im =  ImageIO.read(new File(IMAGE_DIR + fnm) );
	       
	       System.out.println("  Image " + fnm + " successfully loaded as Image class");

	       // An image returned from ImageIO in J2SE <= 1.4.2 is 
	       // _not_ a managed image, but is after copying!

	       int transparency = im.getColorModel().getTransparency();
	       
	       BufferedImage copy =  gc.createCompatibleImage(
	                                im.getWidth(), im.getHeight(),
			                        transparency );
	       // create a graphics context
	       Graphics2D g2d = copy.createGraphics();
	       // g2d.setComposite(AlphaComposite.Src);

	       // reportTransparency(IMAGE_DIR + fnm, transparency);

	       // copy image
	       g2d.drawImage(im,0,0,null);
	       g2d.dispose();
	       return copy;
	     } 
	     //catch(IOException e) {
	     catch(Exception e) {
	       System.out.println("Load Image error for " +
	                     IMAGE_DIR + fnm + ":\n" + "  :" + e); 
	       return null;
	     }
	  } // end of loadImage() using ImageIO


	  @SuppressWarnings("unused")
	private void reportTransparency(String fnm, int transparency)
	  {
	    System.out.print(fnm + " transparency: ");
	    switch(transparency) {
	      case Transparency.OPAQUE:
	        System.out.println("opaque");
	        break;
	      case Transparency.BITMASK:
	        System.out.println("bitmask");
	        break;
	      case Transparency.TRANSLUCENT:
	        System.out.println("translucent");
	        break;
	      default:
	        System.out.println("unknown");
	        break;
	    } // end switch
	  }  // end of reportTransparency()



	  @SuppressWarnings("unused")
	private BufferedImage loadImage2(String fnm)
	   /* Load the image from <fnm>, returning it as a BufferedImage.
	      Uses ImageIcon.
	   */
	  { ImageIcon imIcon = new ImageIcon(
	                      getClass().getResource(IMAGE_DIR + fnm) );
	    if (imIcon == null)
	      return null;

	    int width = imIcon.getIconWidth();
	    int height = imIcon.getIconHeight();
	    Image im = imIcon.getImage();

	    return makeBIM(im, width, height);
	  }  // end of loadImage() using ImageIcon


	  private BufferedImage makeBIM(Image im, int width, int height)
	  // make a BufferedImage copy of im, assuming an alpha channel
	  {
	    BufferedImage copy = new BufferedImage(width, height, 
	                                        BufferedImage.TYPE_INT_ARGB);
	    // create a graphics context
	     Graphics2D g2d = copy.createGraphics();
	    // g2d.setComposite(AlphaComposite.Src);

	    // copy image
	    g2d.drawImage(im,0,0,null);
	    g2d.dispose();
	    return copy;
	  }  // end of makeBIM()



	  public BufferedImage loadImage3(String fnm) 
	  /* Load the image from <fnm>, returning it as a BufferedImage.
	     Use Image.
	  */
	  { Image im = readImage(fnm);
	    if (im == null)
	      return null;

	    int width = im.getWidth( null );
	    int height = im.getHeight( null );

	    return makeBIM(im, width, height);
	  } // end of loadImage() using Image


	  private Image readImage(String fnm)
	  // load the image, waiting for it to be fully downloaded
	  {
	    Image image = Toolkit.getDefaultToolkit().getImage(
	                     getClass().getResource(IMAGE_DIR + fnm) );
	    MediaTracker imageTracker = new MediaTracker( new JPanel() );

	    imageTracker.addImage(image, 0);
	    try {
	      imageTracker.waitForID(0);
	    }
	    catch (InterruptedException e) {
	      return null;
	    }
	    if (imageTracker.isErrorID(0))
	      return null;
	    return image;
	  } // end of readImage()




	  //========================= Image Queries =================================
	  
	  // Method:   GetActionLength(..)
	  // Descript: Returns the number of images in an action sequence
	  //           (or 0 if action name not found).
	  // Param:    actionName = name of action sequence.
	  public int GetActionLength(String actionName)
	  {
		  int theLength;
		  ArrayList<BufferedImage> imsList = new ArrayList<BufferedImage>();
		  
		  // Find the action name
		  if (imagesMap.containsKey(actionName)) 
		  {
			imsList = (ArrayList<BufferedImage>)imagesMap.get(actionName);
			theLength = imsList.size();
		  }
		  else
		  {   // Can't find that action, so return zero.
			  System.out.println("ImageLibrary: GetActionLength(): Error - can't find action " + actionName );
			  theLength = 0;
		  }
		
		  return theLength;
		  
	  } // end of GetActionLength(..)

}
