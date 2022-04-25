package v6;
import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Test;

public class Test_GameModel {
	GameModel model = new GameModel();
	
	@Test
	public void test_getAndSet_playerName() {
		String name = "bob";
		model.setPlayerName(name);
		assertEquals(name, model.getPlayerName());
	}
	
	@Test
	public void test_getAndSet_diceDrawer() {
		DiceDrawer dd = new DiceDrawer(model);
		model.setDiceDrawer(dd);
		assertEquals(dd, model.getDiceDrawer());
	}
	
	@Test
	public void test_getAndSet_scoreCard() {
		ScoreCard card = new ScoreCard("Bob Fourapples");
		model.setScoreCard(card);
		assertEquals(card, model.getScoreCard());
	}
	
	@Test
	public void testGetStrategy() {
		model.initiateStrategies();
		HashMap <Integer, GameStrategy> strategies = model.getStrategies();
		assertTrue(strategies != null);
	}
	
	@Test
	public void testScoringMechanism() {
		model.setScoreCard(new ScoreCard(""));
		int[] dice = {1,2,3,4,5};
		boolean scoreChosen = model.scoringMechanism(1, dice);
		assertTrue(scoreChosen);
		scoreChosen = model.scoringMechanism(2, dice);
		assertTrue(scoreChosen);
	}

}