package spriteTest;

import javax.swing.JFrame;

import spriteTest.TestJPanel;


public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 
	    new TestMain().run();
	}
			  
	public void run() {
	    JFrame frame = new JFrame("Graphics Engine test");
	    TestJPanel p = new TestJPanel();
	    frame.setContentPane(p);
	    frame.pack();
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    p.start();
	}
}