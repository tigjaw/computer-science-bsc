package v6;

public class AutoRandomStrategy implements GameStrategy {

	@Override
	public void performStrategy(int[] dice, GameModel model) {
		DiceDrawer diceDrawer = model.getDiceDrawer();
		boolean[] rerollChoice = { false, false, false, false, false };
		for (int x = 0; x < 5; x++) {
			if (dice[x] != 6) {
				rerollChoice[x] = true;
			}
		}
		diceDrawer.reroll(dice, rerollChoice);
	}

	@Override
	public String printStrategyName() {
		return "Random Play (Auto-play)";
	}

}