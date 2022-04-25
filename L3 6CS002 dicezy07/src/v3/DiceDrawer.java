package v3;

import java.awt.*;
import javax.swing.*;

public class DiceDrawer {
	private int[] reroll = null;
	private GameModel modelMaster = null;
	private int[] defaultDice = { 9, 9, 9, 9, 9 };

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

		public void parseNumbers(Graphics g) {
			for (int i = 0; i < 5; i++) {
				drawDigitGivenCentre(g, 35 + (60 * i), 76, 16, i + 1);
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
			g.drawString(txt, x - fm.stringWidth(txt) / 2, y
					+ fm.getMaxAscent() / 2);
		}

		protected void paintComponent(Graphics g) {
			g.setColor(Color.YELLOW);
			g.fillRect(0, 0, getWidth(), getHeight());

			parseNumbers(g);
			if (modelMaster != null && defaultDice != null) {
				drawRoll(g, defaultDice);
			}
			if (reroll != null) {
				drawReroll(g, reroll);
			}
		}

		public Dimension getPreferredSize() {
			return new Dimension(310, 160);
		}
	}

	public DicePanel dicePanel;

	public DiceDrawer(GameModel sf) {
		modelMaster = sf;
		if (dicePanel == null) {
			JFrame f = new JFrame("Dicezy");
			dicePanel = new DicePanel();
			f.setContentPane(dicePanel);
			f.pack();
			f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			f.setVisible(true);
			setDefaultDice(null);
		}
	}
	
	public int[] roll() {
		int[] d = { 1, 2, 3, 4, 5 };
		d[0] = 1 + (int) (Math.random() * 6);
		d[1] = 1 + (int) (Math.random() * 6);
		d[2] = 1 + (int) (Math.random() * 6);
		d[3] = 1 + (int) (Math.random() * 6);
		d[4] = 1 + (int) (Math.random() * 6);

		defaultDice = new int[d.length];
		for (int bounces = 1; bounces <= defaultDice.length; bounces++) {
			defaultDice[bounces - 1] = d[bounces - 1];
		}
		printDiceRolled(d);
		setReroll(null);
		
		return d;
	}
	
	void reroll(int[] dice, boolean[] diceToRoll) {
		for (int i = 0; i < 5; i++) {
			if (diceToRoll[i] != false) {
				dice[i] = 1 + (int) (Math.random() * 6);
			}
		}
		reroll = dice;
		printDiceRolled(reroll);
	}
	
	public void printDiceRolled(int[] dice) {
		System.out.println();
		System.out.println("You rolled:");
		System.out.println("die 1 = " + dice[0]);
		System.out.println("die 2 = " + dice[1]);
		System.out.println("die 3 = " + dice[2]);
		System.out.println("die 4 = " + dice[3]);
		System.out.println("die 5 = " + dice[4] + "\n");
	}

	public int[] getReroll() {
		return reroll;
	}

	public void setReroll(int[] reroll) {
		this.reroll = reroll;
	}

	public int[] getDefaultDice() {
		return defaultDice;
	}

	public void setDefaultDice(int[] defaultDice) {
		this.defaultDice = defaultDice;
	}

	public void repaint() {
		dicePanel.repaint();
	}

}