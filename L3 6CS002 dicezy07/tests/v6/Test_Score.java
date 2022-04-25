package v6;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test_Score {
	
	@Test
	public void testConstructor() {
		String name = "Martha Farquhar";
		int score = 100;
		long date = 1281625395123L;
		Score testScore = new Score(name, score, date);
		assertEquals(name, testScore.getName());
		assertEquals(score, testScore.getScore());
		assertEquals(date, testScore.getDate());
	}
	
	@Test
	public void testName_getterAndSetter() {
		String name = "Bob";
		Score testScore = new Score("Jayne", 50, 1281625395123L);
		testScore.setName(name);
		assertEquals(name, testScore.getName());
	}
	
	@Test
	public void testScore_getterAndSetter() {
		int score = 20;
		Score testScore = new Score("Jayne", 50, 1281625395123L);
		testScore.setScore(score);
		assertEquals(score, testScore.getScore());
	}
	
	@Test
	public void testDate_getterAndSetter() {
		long date = 1181625395123L;
		Score testScore = new Score("Jayne", 50, 1281625395123L);
		testScore.setDate(date);
		assertEquals(date, testScore.getDate());
	}
}