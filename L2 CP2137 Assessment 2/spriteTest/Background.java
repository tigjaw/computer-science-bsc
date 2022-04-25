package spriteTest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Background {

	// Drawing surface
	private JPanel myCanvas;
	
	// Default background colour
	private Color myBackColour = Color.BLACK;
	
	// Background Image
	private BufferedImage myBackgroundImage = null;
	private boolean isBackImage = false;
	private int boardWidth, boardHeight;
	private float scaleX, scaleY;
	
	
	/**
	 * "Background" constructor
	 * Store link to the JPanel where image is to be drawn.
	 * Set background to default colour Black.
	 * 
	 * @param theCanvas - drawing surface
	 */
	public Background(JPanel theCanvas)
	{
		myCanvas     = theCanvas;
		myBackColour = Color.black;
		isBackImage  = false;   // No background image
		boardWidth   = theCanvas.getWidth();
		boardHeight  = theCanvas.getHeight();
	}// end of constructor
	
	
	//=========================== Setters ========================
	
	/**
	 * "setBackColour" method.
	 * Set background colour (no image).
	 * 
	 * @param theBackColour
	 */
	public void setBackColour(Color theBackColour)
	{
		myBackColour = theBackColour;
		isBackImage = false;
	} // end of setBackColour(..)
	
	
	/**
	 * "setBackground" method.
	 * Store the background image file.
	 * 
	 * @param filename - name of background image filename
	 */
	public void setBackground (String filename)
	{
		try {
			myBackgroundImage = ImageIO.read(new File(filename));
			isBackImage = true;
			reScale();
			System.out.println("Background image loaded ");
		  } catch (IOException e) {
			System.out.println("Error in Background.setBackground: - unable to find file:" + filename);
			e.printStackTrace();
		  }
	} // end setBackground(..)
	
	
	public void setBoardSize(int theBoardWidth, int theBoardHeight)
	{
		boardWidth = theBoardWidth;
		boardHeight = theBoardHeight;
		reScale();
	}


	//=============== General Methods ===============
	
	private void reScale()
	{
		if (isBackImage) 
		{
			// Background image, so scale image to fit board
			scaleX = myBackgroundImage.getWidth()/(float)boardWidth;
			scaleY = myBackgroundImage.getHeight()/(float)boardHeight;
		}
		else
		{
		    scaleX = 1;
		    scaleY = 1;
		}
	}
	
	/**
	 * "draw" method.
	 * Builds up a background buffer.
	 * Called by SpriteEngine.
	 */
	public void draw(Image backBuffer)
	//public Image draw()
	{
		Graphics2D g2;
		int cWidth  = myCanvas.getWidth();
		int cHeight = myCanvas.getHeight();
		
		g2 = (Graphics2D) backBuffer.getGraphics();
		
	
		if (isBackImage){
			int iWidth  = myBackgroundImage.getWidth();
			int iHeight = myBackgroundImage.getHeight();
			
			//g2.drawImage(myBackgroundImage,0,0,null);
			//g2.drawImage(myBackgroundImage, 0,0,cWidth,cHeight,0,0,iWidth,iHeight,null);
			//g2.drawImage(myBackgroundImage, 0,0,cWidth,cHeight, myX,myY, myX+myAreaWidth, myY+myAreaHeight,null);
			g2.drawImage(myBackgroundImage,0,0,backBuffer.getWidth(null),backBuffer.getHeight(null),0,0,iWidth, iHeight,null); 
		} 
		else
		{  // using background colour.
			//int width = myCanvas.getWidth();
			//int height = myCanvas.getHeight();
			
			Shape rect = new Rectangle(0,0,backBuffer.getWidth(null),backBuffer.getHeight(null));
			g2.setPaint(myBackColour);
			g2.fill(rect);

		}
		
	} // end of draw()
	
	/**
	 * "draw" method - version 2.
	 * Builds up a background buffer for visible area only.
	 * Called by SpriteEngine.
	 */
	public void draw(Image backBuffer, int vaX, int vaY, int vaWidth, int vaHeight)
	{
		Graphics2D g2;
		//int cWidth  = myCanvas.getWidth();
		//int cHeight = myCanvas.getHeight();
		
		g2 = (Graphics2D) backBuffer.getGraphics();
		
	
		if (isBackImage){
			//int iWidth  = myBackgroundImage.getWidth();
			//int iHeight = myBackgroundImage.getHeight();
			
			//g2.drawImage(myBackgroundImage,0,0,null);
			//g2.drawImage(myBackgroundImage, 0,0,cWidth,cHeight,0,0,iWidth,iHeight,null);
			//g2.drawImage(myBackgroundImage, 0,0,cWidth,cHeight, myX,myY, myX+myAreaWidth, myY+myAreaHeight,null);
			int x1 = (int) (vaX*scaleX);
			int y1 = (int) (vaY*scaleY);
			int x2 = x1 + (int)(vaWidth *scaleX);
			int y2 = y1 + (int)(vaHeight*scaleY);
			g2.drawImage(myBackgroundImage,0,0,backBuffer.getWidth(null),backBuffer.getHeight(null),x1,y1,x2,y2,null); 
		} 
		else
		{  // Fill backBuffer using background colour.
			Shape rect = new Rectangle(0,0,backBuffer.getWidth(null),backBuffer.getHeight(null));
			g2.setPaint(myBackColour);
			g2.fill(rect);
		}
	
	}
} // end of class background

