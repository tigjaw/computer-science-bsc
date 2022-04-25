package spriteTask3;

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

	
	/**
	 * "Background" constructor
	 * Store link to the JPanel where image is to be drawn.
	 * Set background to default colour Black.
	 * 
	 * @param theCanvas - drawing surface
	 */
	public Background(JPanel theCanvas)
	{
		myCanvas = theCanvas;
		myBackColour = Color.black;
		isBackImage = false;   // No background image
	}// end of constructor
	
	
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
			System.out.println("Background image loaded ");
		  } catch (IOException e) {
			System.out.println("Error in Background.setBackground: - unable to find file:" + filename);
			e.printStackTrace();
		  }
	} // end setBackground(..)
	
	
	/**
	 * "draw" method.
	 * Builds up a background buffer.
	 * Called by GraphicsEngine.
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
			g2.drawImage(myBackgroundImage, 0,0,cWidth,cHeight,0,0,iWidth,iHeight,null);
		} 
		else
		{  // using background colour.
			int width = myCanvas.getWidth();
			int height = myCanvas.getHeight();
			
			Shape rect = new Rectangle(0,0,width,height);
			g2.setPaint(myBackColour);
			g2.fill(rect);

		}
		
		//return backBuffer;
	} // end of draw()
	
	
} // end of class background

