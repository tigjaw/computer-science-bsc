package v6;

public class ManualStrategy implements GameStrategy {

	@Override
	public void performStrategy(int[] dice, GameModel model) {
		System.out.println("Which dice would you like to roll again?");
		String rerollInput = ConsoleInputter.getString();
		performStrategy(dice, model, rerollInput);
	}

	protected void performStrategy(int[] dice, GameModel model,
			String rerollInput) {
		/* separated this functionality to enable testing
		 * without requiring command input.
		 */
		int[] diceToRoll = dice;
		DiceDrawer diceDrawer = model.getDiceDrawer();
		ScoreCard scoreCard = model.getScoreCard();
		boolean[] rerollThese = { false, false, false, false, false };
		for(int i = 1; i < 6; i++) {
			if (rerollInput.indexOf("" + i) >= 0) {
				System.out.println(i);
				rerollThese[i-1] = true;
			}
		}
		diceDrawer.reroll(diceToRoll, rerollThese);
		ConsoleOutputter.printScoringChoices(scoreCard, scoreCard.getHasBonus());
	}

	@Override
	public String printStrategyName() {
		return "Manual Strategy";
	}

}