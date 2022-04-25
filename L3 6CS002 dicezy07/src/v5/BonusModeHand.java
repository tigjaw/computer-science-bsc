package v5;

public class BonusModeHand extends Hand {

	public BonusModeHand() {
		super("E) Dicezy Bonus!!!!!!!!!");
	}

	@Override
	public boolean scoreChosenHand() {
		boolean scoreChosen = false;
		if (getScoreCard().getHasBonus()) {
			scoreChosen = true;
			// check if there really is a five of a kind
			int[] countCards = { 0, 0, 0, 0, 0, 0 };
			for (int i = 0; i < 5; i++) {
				countCards[getDice()[i] - 1]++;
			}
			if ((countCards[0] == 5 || countCards[1] == 5 || countCards[2] == 5
					|| countCards[3] == 5 || countCards[4] == 5 || countCards[5] == 5)) {
				// We end up here if it is a five of a kind.
				// We can only award the bonus if the number has been in the
				// top section
				// Find out which number we have a set of
				int counter;
				for (counter = 1; counter < 7; counter++) {
					if (countCards[counter] == 5) {
						break;
					}
				}
				if (counter < 7) {
					// award no bonus if the top section part for this
					// number has not been used
					getScoreCard().getHand(counter).setScore(counter * 5);
				} else {
					// award bonus because top section part for this set is
					// already complete
					getScoreCard().setDicezyBonus(100);
					if (getScoreCard().getHand(7).getScore() == -1
							|| getScoreCard().getHand(8).getScore() == -1
							|| getScoreCard().getHand(9).getScore() == -1
							|| getScoreCard().getHand(10).getScore() == -1
							|| getScoreCard().getHand(11).getScore() == -1
							|| getScoreCard().getHand(12).getScore() == -1) {
						scoreJoker();
					} else {
						scoreForfeit();
					}
				}
			} else {
				dontCheat();
			}
		}
		return scoreChosen;
	}

	private void scoreJoker() {
		System.out.println("You may use your Dicezy as a joker");
		ConsoleOutputter.printScoringChoicesBottom(getScoreCard());

		System.out.println("What do you want to score this as?");
		NamedHand joker;
		while (true) {
			int i = ConsoleInputter.getHex();
			if (i >= 7 && i <= 13) {
				joker = (NamedHand) getScoreCard().getHand(i);
				if (joker.getScore() == -1 && i != 12) {
					joker.scoreAsJoker(getDice());
					break;
				}
			}
		}
	}

	private void scoreForfeit() {
		System.out.println("Bottom section is complete you must take a forfeit in the top section");
		ConsoleOutputter.printScoringChoicesTop(getScoreCard());
		Hand joker;
		while (true) {
			int i = ConsoleInputter.getHex();
			if (i >= 1 && i <= 6) {
				joker = getScoreCard().getHand(i);
				if (joker.getScore() == -1) {
					joker.setScore(0);
					break;
				}
			}
		}
	}

	private void dontCheat() {
		System.out.println("Don't try to cheat!!");
		System.out.println("You must now score a zero");
		ConsoleOutputter.printScoringChoices(getScoreCard(), false);
		System.out.println("\nWhich section will you score zero for?");
		Hand joker;
		while (true) {
			int i = ConsoleInputter.getHex();
			if (i >= 1 && i <=13) {
				joker = getScoreCard().getHand(i);
				if (joker.getScore() == -1) {
					joker.setScore(0);
					break;
				}
			}
		}
	}

}