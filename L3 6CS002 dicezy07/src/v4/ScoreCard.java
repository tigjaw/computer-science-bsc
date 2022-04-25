package v4;

import java.util.HashMap;

public class ScoreCard {
	private HashMap<Integer, Hand> hands;
	private boolean hasBonus = false;
	private Hand handToScore;
	private String playerName;
	private int dicezyBonus;

	public ScoreCard() {
		dicezyBonus = 0;
		initialiseHands();
	}

	public ScoreCard(String playerName) {
		dicezyBonus = 0;
		this.setPlayerName(playerName);
		initialiseHands();
	}

	public boolean scoring(int[] dice, int chosenGameType, int scoreChoice) {
		if (chosenGameType != 0) {
			boolean autoScoreModifier = false;
			if (chosenGameType == 1) { // if random plodding
				for (int e = 1; e <= 6; e++) {
					if (getHand(e).getScore() == -1) {
						scoreChoice = e;
						autoScoreModifier = true;
						break;
					}
				}
				autoScoreModifier = autoScoreModifier == true ? true : false;
			}
			if (chosenGameType == 2 || (autoScoreModifier == false && chosenGameType != 0)) {
				autoScoreModifier = true;
				while (autoScoreModifier) {
					scoreChoice = (int) (Math.random() * 13) + 1;
					if (getHand(scoreChoice).getScore() == -1) {
						autoScoreModifier = false;
					}
				}
			}
		}
		handToScore = hands.get(scoreChoice);
		return scoreChosenHand(dice);
	}
	
	private boolean scoreChosenHand(int[] dice) {
		try {
			return handToScore.scoreChosenHand(dice, this);
		} catch (Exception e) {
			return false;
		}
	}

	public int calculateGrandTotal() {
		int topScoreBeforeBonus = 0;
		for (int i = 1; i <= 6; i++) {
			Hand h = hands.get(i);
			topScoreBeforeBonus += h.getScore() > -1 ? h.getScore() : 0;
		}
		int topBonus = topScoreBeforeBonus > 63 ? 35 : 0;
		int topScore = topScoreBeforeBonus + topBonus;
		int bottomScore = getDicezyBonus();
		for (int i = 7; i <= 13; i++) {
			Hand h = hands.get(i);
			bottomScore += h.getScore() > -1 ? h.getScore() : 0;
		}
		int grandTotal = topScore + bottomScore;
		return grandTotal;
	}

	public void viewScoreCard() {
		int topScoreBeforeBonus = 0;
		for (int i = 1; i <= 6; i++) {
			Hand h = hands.get(i);
			topScoreBeforeBonus += h.getScore() > -1 ? h.getScore() : 0;
		}
		int topBonus = topScoreBeforeBonus > 63 ? 35 : 0;
		int topScore = topScoreBeforeBonus + topBonus;
		int bottomScore = getDicezyBonus();
		for (int i = 7; i <= 13; i++) {
			Hand h = hands.get(i);
			bottomScore += h.getScore() > -1 ? h.getScore() : 0;
		}
		int grandTotal = topScore + bottomScore;
		System.out.println();
		String t2 = String.format("%s's Score Card", getPlayerName());
		System.out.println(t2);
		System.out.println(t2.replaceAll(".", "="));
		for (int i = 1; i < hands.size(); i++) {
			hands.get(i).printScore();
		}
		System.out.printf("%20s %d\n", "Score", topScoreBeforeBonus);
		System.out.printf("%20s %d\n", "Bonus", topBonus);
		System.out.printf("%20s %d\n", "Upper total", topScore);
		System.out.printf("%20s %d\n", "Dicezy bonus", getDicezyBonus());
		System.out.printf("%20s %d\n", "Grand total", grandTotal);
	}

	private void initialiseHands() {
		setHands(new HashMap<Integer, Hand>());
		hands.put(1, new NumbersHand("1) Ones", 1));
		hands.put(2, new NumbersHand("2) Twos", 2));
		hands.put(3, new NumbersHand("3) Threes", 3));
		hands.put(4, new NumbersHand("4) Fours", 4));
		hands.put(5, new NumbersHand("5) Fives", 5));
		hands.put(6, new NumbersHand("6) Sixes", 6));
		hands.put(7, new ThreeOfAKind());
		hands.put(8, new FourOfAKind());
		hands.put(9, new FullHouse());
		hands.put(10, new ShortStraight());
		hands.put(11, new LongStraight());
		hands.put(12, new DicezyHand());
		hands.put(13, new ChanceHand());
		hands.put(14, new BonusModeHand());
	}

	public HashMap<Integer, Hand> getHands() {
		return hands;
	}

	public void setHands(HashMap<Integer, Hand> hands) {
		this.hands = hands;
	}
	
	public Hand getHand(int i) {
		return hands.get(i);
	}

	public boolean getHasBonus() {
		return hasBonus;
	}

	public void setHasBonus(boolean hasBonus) {
		this.hasBonus = hasBonus;
	}

	public Hand getHandToScore() {
		return handToScore;
	}

	public void setHandToScore(Hand handToScore) {
		this.handToScore = handToScore;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getDicezyBonus() {
		return dicezyBonus;
	}

	public void setDicezyBonus(int dicezyBonus) {
		this.dicezyBonus = dicezyBonus;
	}

}