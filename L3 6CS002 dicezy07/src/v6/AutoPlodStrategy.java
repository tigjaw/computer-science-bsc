package v6;

public class AutoPlodStrategy implements GameStrategy {

	@Override
	public void performStrategy(int[] dice, GameModel model) {
		DiceDrawer diceDrawer = model.getDiceDrawer();
		ScoreCard scoreCard = model.getScoreCard();
		boolean chosenDice = false;
		for (int i = 0; i <6; i++) {
			Hand h = scoreCard.getHandFromHands(i+1);
			if (h.getScore() == -1) {
				boolean[] rerollChoice = { false, false, false, false, false };
				for (int x = 0; x < 5; x++) {
					if (dice[x] != (i + 1)) {
						rerollChoice[x] = true;
					}
				}
				diceDrawer.reroll(dice, rerollChoice);
				chosenDice = true;
				break;
			}
		}
		if (chosenDice == false) {
			boolean[] rerollChoice = { false, false, false, false, false };
			for (int x = 0; x < 5; x++) {
				if (Math.random() < 0.5) {
					rerollChoice[x] = true;
				}
			}
			diceDrawer.reroll(dice, rerollChoice);
		}
	}

	@Override
	public String printStrategyName() {
		return "Plodding Away (Auto-play)";
	}

}