package v0original;
import java.awt.*;

import javax.swing.*;

public class DiceDrawer {
  public int[] reroll = null;
  Swordfish master = null;

  class DicePanel extends JPanel {
    private static final long serialVersionUID = -1013206070641714716L;

    public void drawDotGivenCentre(Graphics g, int x, int y, int diameter) {
      int radius = diameter / 2;
      g.fillOval(x - radius, y - radius, diameter, diameter);
    }

    public void drawDie(Graphics g, int x, int y, int w, int h, int n) {
      int curve = w / 5;
      int border = 10;
      int halfWidth = w / 2;
      int halfHeight = w / 2;
      int centerX = x + halfWidth;
      int centreY = y + halfHeight;
      int leftX = x + border;
      int topY = y + border;
      int rightX = x + w - border;
      int bottomY = y + h - border;
      g.setColor(Color.WHITE);
      g.fillRoundRect(x, y, w, h, curve, curve);
      g.setColor(Color.BLACK);
      g.drawRoundRect(x, y, w, h, curve, curve);
      if (n % 2 != 0) {
        drawDotGivenCentre(g, centerX, centreY, 10);
      }
      if (n > 1) {
        drawDotGivenCentre(g, leftX, topY, 10);
        drawDotGivenCentre(g, rightX, bottomY, 10);
      }
      if (n > 3) {
        drawDotGivenCentre(g, rightX, topY, 10);
        drawDotGivenCentre(g, leftX, bottomY, 10);
        drawDotGivenCentre(g, rightX, topY, 10);
      }
      if (n == 6) {
        drawDotGivenCentre(g, leftX, centreY, 10);
        drawDotGivenCentre(g, rightX, centreY, 10);
      }
    }

    public void drawRoll(Graphics g, int[] d) {
      for (int i = 0; i < 5; i++) {
        drawDie(g, 10 + (60 * i), 10, 50, 50, d[i]);
      }
    }

    public void drawReroll(Graphics g, int[] d) {
      for (int i = 0; i < 5; i++) {
        drawDie(g, 10 + (60 * i), 90, 50, 50, d[i]);
      }
    }

    public void numbaz(Graphics g) {
      for (int i = 0; i < 5; i++) {
        drawDigitGivenCentre(g, 35 + (60 * i), 76, 16, i+1);
      }
    }

    void drawDigitGivenCentre(Graphics g, int x, int y, int diameter, int n) {
      int radius = diameter / 2;
      g.setColor(Color.GREEN);
      g.fillOval(x - radius, y - radius, diameter, diameter);
      g.setColor(Color.BLACK);
      g.drawOval(x - radius, y - radius, diameter, diameter);
      FontMetrics fm = g.getFontMetrics();
      String txt = Integer.toString(n);
      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
    }

    protected void paintComponent(Graphics g) {
      g.setColor(Color.YELLOW);
      g.fillRect(0, 0, getWidth(), getHeight());

      numbaz(g);
      if (master!=null && master.orig != null) {
        drawRoll(g, master.orig);
      }
      if (reroll != null) {
        drawReroll(g, reroll);
      }
    }

    public Dimension getPreferredSize() {
      return new Dimension(310, 160);
    }
  }

  public DicePanel dp;
  

  public void DiceDrawer(Swordfish sf) {
    master = sf;
    if (dp == null) {
      JFrame f = new JFrame("Dicezy");
      dp = new DicePanel();
      f.setContentPane(dp);
      f.pack();
      f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      f.setVisible(true);
    }
  }


  public void reset() {
    // TODO Auto-generated method stub
    
  }

}
