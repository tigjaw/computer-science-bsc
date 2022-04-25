package v4;

public class LongStraight extends NamedHand {

	public LongStraight() {
		super("B) Long Straight");
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
			boolean good = true;
			boolean bad = false;
			for (int i = 0; i < 6; i++) {
				if (countCards[i] > 1) {
					good = false;
					bad = true;
					break;
				}
			}
			if (good && !bad) {
				if (countCards[0] != countCards[5]) {
					setScore(40);
				} else {
					setScore(0);
				}
			} else {
				setScore(0);
			}
		}
		return scoreChosen;
	}

	@Override
	public void scoreAsJoker(int[] dice) {
		setScore(40);
	}

}