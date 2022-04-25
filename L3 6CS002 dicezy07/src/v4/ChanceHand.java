package v4;

public class ChanceHand extends NamedHand {

	public ChanceHand() {
		super("D) Chance");
	}

	@Override
	public boolean scoreChosenHand() {
		boolean scoreChosen = false;
		if (getScore() == -1) {
			scoreChosen = true;
			setScore(0);
			for (int i = 0; i < 5; i++) {
				addToScore(getDice()[i]);
			}
		}
		return scoreChosen;
	}

	@Override
	public void scoreAsJoker(int[] dice) {
		setScore(dice[0] + dice[1] + dice[2] + dice[3] + dice[4] + dice[5]);
	}

}