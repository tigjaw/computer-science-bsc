package v6;

import static org.junit.Assert.*;
import org.junit.Test;

public class Test_GameStrategies {
	GameModel model;

	class Test_AutoPlodStrategy {
		@Test
		public void test_performStrategy() {
			model = new GameModel();
			model.setDiceDrawer(new DiceDrawer(model));
			int dice[] = {1,0,0,0,0};
			ScoreCard scoreCard = new ScoreCard("Bob");
			for (int i = 2; i < scoreCard.getHands().size(); i++) {
				Hand h = scoreCard.getHandFromHands(i);
				h.setScore(0);
			}
			model.setScoreCard(scoreCard);
			new AutoPlodStrategy().performStrategy(dice, model);
			assertTrue(dice[0] == 1);
			for (int i = 1; i < 5; i++) {
				assertFalse(dice[i] == 0);
			}
		}
	}
	
	class Test_AutoRandomStrategy {
		@Test
		public void test_performStrategy() {
			model = new GameModel();
			model.setDiceDrawer(new DiceDrawer(model));
			int[] dice = {0,0,0,0,6};
			new AutoRandomStrategy().performStrategy(dice, model);
			assertTrue(dice[0] != 0);
			assertTrue(dice[4] == 6);
		}
	}
	
	class Test_ManualStrategy {
		@Test
		public void test_performStrategy() {
			model = new GameModel();
			model.setDiceDrawer(new DiceDrawer(model));
			model.setScoreCard(new ScoreCard("Bob"));
			int dice[] = {0,0,0,0,0};
			String rollThis = "1";
			new ManualStrategy().performStrategy(dice, model, rollThis);
			assertFalse(dice[0] == 0);
			for (int i = 1; i < 5; i++) {
				assertTrue(dice[i] == 0);
			}
		}
	}
	
	@Test
	public void runTests() {
		new Test_AutoPlodStrategy().test_performStrategy();
		new Test_AutoRandomStrategy().test_performStrategy();
		new Test_ManualStrategy().test_performStrategy();
	}

}