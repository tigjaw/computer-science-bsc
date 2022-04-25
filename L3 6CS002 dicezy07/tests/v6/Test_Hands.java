package v6;

import static org.junit.Assert.*;
import org.junit.Test;

public class Test_Hands {
	
	@Test
	public void test_scoreAs_NumbersHand() {
		ScoreCard card = new ScoreCard("Jean Poole");
		Hand h = new NumbersHand("3) Threes", 3);
		int[] dice = {3,3,3,3,3};
		int scoreShouldBe = 15;
		h.scoreChosenHand(dice, card);
		assertEquals(scoreShouldBe, h.getScore());
	}
	
	@Test
	public void test_scoreAs_ThreeofaKind() {
		ScoreCard card = new ScoreCard("Paul McGroin");
		Hand h = new ThreeOfAKind();
		int[] dice = {3,3,3,1,1};
		int scoreShouldBe = 11;
		h.scoreChosenHand(dice, card);
		assertEquals(scoreShouldBe, h.getScore());
	}
	
	@Test
	public void test_scoreAs_FourofaKind() {
		ScoreCard card = new ScoreCard("Phil McCrackin");
		Hand h = new FourOfAKind();
		int[] dice = {5,5,5,5,1};
		int scoreShouldBe = 21;
		h.scoreChosenHand(dice, card);
		assertEquals(scoreShouldBe, h.getScore());
	}
	
	@Test
	public void test_scoreAs_FullHouse() {
		ScoreCard card = new ScoreCard("Nick Tyure-Stuff");
		Hand h = new FullHouse();
		int[] dice = {5,5,5,3,3};
		int scoreShouldBe = 25;
		h.scoreChosenHand(dice, card);
		assertEquals(scoreShouldBe, h.getScore());
	}
	
	@Test
	public void test_scoreAs_ShortStraight() {
		ScoreCard card = new ScoreCard("Dan Glin");
		Hand h = new ShortStraight();
		int[] dice = {1,2,3,4,6};
		int scoreShouldBe = 30;
		h.scoreChosenHand(dice, card);
		assertEquals(scoreShouldBe, h.getScore());
	}
	
	@Test
	public void test_scoreAs_LongStraight() {
		ScoreCard card = new ScoreCard("Bill Ding");
		Hand h = new LongStraight();
		int[] dice = {1,2,3,4,5};
		int scoreShouldBe = 40;
		h.scoreChosenHand(dice, card);
		assertEquals(scoreShouldBe, h.getScore());
	}
	
	@Test
	public void test_scoreAs_DicezyHand() {
		ScoreCard card = new ScoreCard("Joe King");
		Hand h = new DicezyHand();
		int[] dice = {5,5,5,5,5};
		int scoreShouldBe = 50;
		h.scoreChosenHand(dice, card);
		assertEquals(scoreShouldBe, h.getScore());
	}
	
	@Test
	public void test_scoreAs_Chance() {
		ScoreCard card = new ScoreCard("Chris Talmeth");
		Hand h = new ChanceHand();
		int[] dice = {1,1,2,3,6};
		int scoreShouldBe = 13;
		h.scoreChosenHand(dice, card);
		assertEquals(scoreShouldBe, h.getScore());
	}
	
	@Test
	public void test_scoreAs_BonusModeHand() {
		ScoreCard card = new ScoreCard("Chris Talmeth");
		Hand h = new BonusModeHand();
		int[] dice = {1,2,3,4,5};
		assertFalse(h.scoreChosenHand(dice, card));
	}

}