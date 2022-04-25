package v4;

public class NumbersHand extends Hand {
	private int scoreModifier;
	
	public NumbersHand(String handType, int scoreModifier) {
		super(handType);
		this.scoreModifier = scoreModifier;
	}

	@Override
	public boolean scoreChosenHand() {
		boolean scoreChosen = false;
		if (getScore() == -1) {
			scoreChosen = true;
			int total = 0;
			for (int i = 0; i < 5; i++) {
				if (getDice()[i] == scoreModifier) {
					total++;
				}
			}
			setScore(total * scoreModifier);
		}
		return scoreChosen;
	}
	
}