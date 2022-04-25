package v6;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class Test_ScoreCard {
	ScoreCard card;
	
	@Test
	public void test_getAndSet_hands() {
		card = new ScoreCard("Marge Areen");
		HashMap<Integer, Hand> hands = new HashMap<Integer, Hand>();
		card.setHands(hands);
		assertEquals(hands, card.getHands());
	}

	@Test
	public void test_Constructor() {
		String name = "Lee Vinhome";
		card = new ScoreCard(name);
		assertEquals(name, card.getPlayerName());
		int bonusShouldBe = 0;
		assertEquals(bonusShouldBe, card.getDicezyBonus());
		assertNotNull(card.getHands());
	}

	@Test
	public void testScoring() {
		manualScore_FourofaKind();
		autoPlodScore();
		autoRandomScore();
	}

	private void manualScore_FourofaKind() {
		card = new ScoreCard("Gerry Attrick");
		int[] dice = {4,4,4,4,1};
		int scoreChoice = 8;
		int score = 17;
		assertTrue(card.scoring(dice, 0, scoreChoice));
		assertEquals(score, card.getHandFromHands(scoreChoice).getScore());
	}
	
	private void autoPlodScore() {
		card = new ScoreCard("Gerry Attrick");
		int[] dice = {1,1,2,6,4};
		int scoreChoice = 1;
		int score = 2;
		assertTrue(card.scoring(dice, 1, scoreChoice));
		assertEquals(score, card.getHandFromHands(scoreChoice).getScore());
	}
	
	private void autoRandomScore() {
		card = new ScoreCard("Gerry Attrick");
		int[] dice = {1,1,1,1,1};
		assertTrue(card.scoring(dice, 2, 0));
	}

	@Test
	public void testCalculateGrandTotal() {
		card = new ScoreCard("Will Youreadthis?");
		int[] dice = {1,1,1,1,1};
		card.scoring(dice, 0, 12);
		int grandTotalShouldBe = 50;
		assertEquals(grandTotalShouldBe, card.calculateGrandTotal());
	}

	@Test
	public void test_getAndSet_handToScore() {
		card = new ScoreCard("Lee Vinhome");
		Hand h = new ThreeOfAKind();
		card.setHandToScore(h);
		assertEquals(h, card.getHandToScore());
	}
	
	@Test
	public void test_getAndSet_playerName() {
		String name = "Harrold Dite";
		card = new ScoreCard("");
		card.setPlayerName(name);
		assertEquals(name, card.getPlayerName());
	}

	@Test
	public void test_getAndSet_dicezyBonus() {
		card = new ScoreCard("Lee Vinhome");
		int bonus = -50;
		card.setDicezyBonus(bonus);
		assertEquals(bonus, card.getDicezyBonus());
	}
	
	@Test
	public void test_getAndSet_hasBonus() {
		card = new ScoreCard("Lee Vinhome");
		boolean bonus = true;
		card.setHasBonus(bonus);
		assertEquals(bonus, card.getHasBonus());
	}

}