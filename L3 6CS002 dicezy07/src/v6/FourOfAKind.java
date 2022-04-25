package v6;

public class FourOfAKind extends NamedHand {

	public FourOfAKind() {
		super("8) Four of a Kind");
	}

	@Override
	protected boolean scoreChosenHand() {
		boolean scoreChosen = false;
		if (getScore() == -1) {
			scoreChosen = true;
			setScore(0);
			int[] countCards = { 0, 0, 0, 0, 0, 0 };
			for (int i = 0; i < 5; i++) {
				countCards[getDice()[i] - 1]++;
			}
			for (int i = 0; i < 6; i++) {
				if (countCards[i] >= 4) {
					setScore(0);
					for (int j = 0; j < 5; j++) {
						addToScore(getDice()[j]);
					}
					break;
				}
			}
		}
		return scoreChosen;
	}

	@Override
	public void scoreAsJoker(int[] dice) {
		setScore(dice[0] + dice[1] + dice[2] + dice[3] + dice[4] + dice[5]);
	}

}