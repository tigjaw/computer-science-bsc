package v5;

public class DicezyHand extends Hand {

	public DicezyHand() {
		super("C) Dicezy");
	}

	@Override
	public boolean scoreChosenHand() {
		boolean scoreChosen = false;
		if (getScore() == -1) {
			scoreChosen = true;
			getScoreCard().setHasBonus(true);
			int[] countCards = { 0, 0, 0, 0, 0, 0 };
			for (int i = 0; i < 5; i++) {
				countCards[getDice()[i] - 1]++;
			}
			if ((countCards[0] == 5 || countCards[1] == 5 || countCards[2] == 5
					|| countCards[3] == 5 || countCards[4] == 5 || countCards[5] == 5)) {
				setScore(50);
			} else {
				setScore(0);
			}
		}
		return scoreChosen;
	}

}