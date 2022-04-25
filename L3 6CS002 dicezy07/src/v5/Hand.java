package v5;

public abstract class Hand {
	private ScoreCard scorecard;
	private String handType;
	private boolean hasScored = false;
	private int score = -1;
	private int dice[];
	
	public Hand(String handType) {
		this.handType = handType;
	}
	
	public boolean scoreChosenHand(int[] dice, ScoreCard card) {
		this.dice = dice;
		this.scorecard = card;
		System.out.println("You Scored this as a " + handType);
		return scoreChosenHand();
	}
	
	protected abstract boolean scoreChosenHand();
	
	public void printScore() {
		// change getScore() == -1 comparison to getHasBonus() ?????
		System.out.printf("%20s %s\n", handType, getScore() == -1 ? "-"
				: getScore());
	}

	public ScoreCard getScoreCard() {
		return scorecard;
	}

	public void setScorecard(ScoreCard scorecard) {
		this.scorecard = scorecard;
	}

	public String getHandType() {
		return handType;
	}

	public void setHandType(String handType) {
		this.handType = handType;
	}

	public boolean isHasScored() {
		return hasScored;
	}

	public void setHasScored(boolean hasScored) {
		this.hasScored = hasScored;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void addToScore(int addScore) {
		this.score += addScore;
	}

	public int[] getDice() {
		return dice;
	}

	public void setDice(int[] dice) {
		this.dice = dice;
	}
	
}