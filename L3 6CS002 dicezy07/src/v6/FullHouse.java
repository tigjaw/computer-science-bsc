package v6;

public class FullHouse extends NamedHand {

	public FullHouse() {
		super("9) Full House");
	}

	@Override
	protected boolean scoreChosenHand() {
		boolean scoreChosen = false;
		if (getScore() == -1) {
			scoreChosen = true;
			int[] countCards = { 0, 0, 0, 0, 0, 0 };
			for (int i = 0; i < 5; i++) {
				countCards[getDice()[i] - 1]++;
			}
			if ((countCards[0] == 3 || countCards[1] == 3 || countCards[2] == 3
					|| countCards[3] == 3 || countCards[4] == 3 || countCards[5] == 3)
					&& (countCards[0] == 2 || countCards[1] == 2
							|| countCards[2] == 2 || countCards[3] == 2
							|| countCards[4] == 2 || countCards[5] == 2)) {
				setScore(25);
			} else {
				setScore(0);
			}
		}
		return scoreChosen;
	}

	@Override
	public void scoreAsJoker(int[] dice) {
		setScore(25);
	}

}