package v4;

public class ShortStraight extends NamedHand {

	public ShortStraight() {
		super("A) Short Straight");
	}

	@Override
	protected boolean scoreChosenHand() {
		boolean scoreChosen = false;
		if (getScore() == -1) {
			scoreChosen = true;
			int[] countCards = { 0, 0, 0, 0, 0, 0 };
			setScore(0);
			for (int i = 0; i < 5; i++) {
				countCards[getDice()[i] - 1]++;
			}
			if ((countCards[0] >= 1 && countCards[1] >= 1 && countCards[2] >= 1 && countCards[3] >= 1)
					|| (countCards[4] >= 1 && countCards[1] >= 1
							&& countCards[2] >= 1 && countCards[3] >= 1)
					|| (countCards[4] >= 1 && countCards[5] >= 1
							&& countCards[2] >= 1 && countCards[3] >= 1)) {
				setScore(30);
			} else {
				setScore(0);
			}
		}
		return scoreChosen;
	}

	@Override
	public void scoreAsJoker(int[] dice) {
		setScore(30);
	}

}