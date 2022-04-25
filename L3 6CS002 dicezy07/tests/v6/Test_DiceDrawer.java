package v6;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Test_DiceDrawer {
	DiceDrawer dd;
	
	@Test
	public void testRoll() {
		dd = new DiceDrawer(new GameModel());
		int dice[] = dd.roll();
		boolean between1and6 = true;
		for (int i = 0; i < dice.length; i++) {
			if (dice[i] < 1 || dice[i] > 6) {
				between1and6 = false;
			}
		}
		assertTrue(between1and6);
	}
	
	@Test
	public void testReroll() {
		dd = new DiceDrawer(new GameModel());
		int dice[] = dd.roll();
		
		boolean reroll_these[] = {false, false, true, false, false};
		dd.reroll(dice, reroll_these);
		int new_dice[] = dd.getReroll();
		
		assertTrue(dice[0] == new_dice[0]);
		assertTrue(dice[1] == new_dice[1]);
		// new_dice[2] could still equal dice[2]
		assertTrue(dice[3] == new_dice[3]);
		assertTrue(dice[4] == new_dice[4]);
	}
	
	@Test
	public void TestRerollSetterGetter() {
		dd = new DiceDrawer(new GameModel());
		int dice[] = {1,2,3,4,5};
		dd.setReroll(dice);
		assertTrue(dd.getReroll() == dice);
	}
	
	@Test
	public void TestDefaultDiceSetterGetter() {
		dd = new DiceDrawer(new GameModel());
		int dice[] = {1,2,3,4,5};
		dd.setDefaultDice(dice);
		assertTrue(dd.getDefaultDice() == dice);
	}

}