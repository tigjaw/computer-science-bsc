package v4;
import static org.junit.Assert.*;
import org.junit.Test;

public class Test_GameModel {
	GameModel model;
	
	@Test
	public void testAutoRandomStrategy() {
		model = new GameModel();
		model.setDiceDrawer(new DiceDrawer(model));
		int[] dice = {0,0,0,0,6};
		model.autoRandomStrategy(dice);
		assertTrue(dice[0] != 0);
		assertTrue(dice[4] == 6);
	}
	
	@Test
	public void testAutoPlodStrategy() {
		model = new GameModel();
		model.setDiceDrawer(new DiceDrawer(model));
		int dice[] = {1,0,0,0,0};
		int scoreCard[] = {-1,0,0,0,0,0,0,0,0,0,0,0,0};
		model.setScoreCard(scoreCard);
		model.autoPlodStrategy(dice);
		assertTrue(dice[0] == 1);
		for (int i = 1; i < 5; i++) {
			assertFalse(dice[i] == 0);
		}
	}
	
	@Test
	public void testManualRollStrategy() {
		model = new GameModel();
		model.setDiceDrawer(new DiceDrawer(model));
		int dice[] = {0,0,0,0,0};
		String rollThis = "1";
		model.manualRollStrategy(dice, rollThis);
		assertFalse(dice[0] == 0);
		for (int i = 1; i < 5; i++) {
			assertTrue(dice[i] == 0);
		}
	}

}