package v1;
import static org.junit.Assert.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class Test_GameModel {
	GameModel model;
	String filePath = "scorez.txt";
	
	@Test
	public void testScores_getterAndSetter() {
		model = new GameModel();
		List<Score> testScores = new LinkedList<Score>();
		String name = "Dinah Saurs";
		int score = 97;
		long date = 1281625395123L;
		Score testScore = new Score(name, score, date);
		testScores.add(testScore);
		
		model.setScores(testScores);
		assertEquals(testScores, model.getScores());
	}
	
	@Test
	public void testLoadScores() {
		model = new GameModel();
		
		File file = new File(filePath);
		String archiveScores = readFromScores(file);
		System.out.println("Archive:\n" + archiveScores + "\n");
		
		String name = "Dinah Saurs";
		int score = 97;
		long date = 1281625395123L;
		Score testScore = new Score(name, score, date);
		
		try {
			PrintWriter p;
			p = new PrintWriter(new FileWriter(filePath));
			p.println(testScore.getName());
			p.println(testScore.getScore());
			p.println(testScore.getDate());
			p.close();
		} catch (Exception e) {
			System.out.println("Error Writing to File " + filePath);
		}
		
		model.loadScores();
		assertEquals(name, model.getScores().get(0).getName());
		
		// rewrite archived scores to file
		try {
			PrintWriter p;
			p = new PrintWriter(new FileWriter(filePath));
			p.println(archiveScores.trim());
			p.close();
		} catch (Exception e) {
			System.out.println("Error Writing to File " + filePath);
		}
	}
	
	@Test
	public void testSaveScores() {
		model = new GameModel();
		List<Score> scores = new LinkedList<Score>();
		
		File file = new File(filePath);
		String archiveScores = readFromScores(file);
		System.out.println("Archive:\n" + archiveScores + "\n");
		
		String name = "Dinah Saurs";
		int score = 97;
		long date = 1281625395123L;
		Score testScore = new Score(name, score, date);
		scores.add(testScore);
		model.setScores(scores);
		model.saveHighScores();
		
		model.loadScores();
		assertEquals(name, model.getScores().get(0).getName());
		
		// rewrite archived scores to file
		try {
			PrintWriter p;
			p = new PrintWriter(new FileWriter(filePath));
			p.println(archiveScores.trim());
			p.close();
		} catch (Exception e) {
			System.out.println("Error Writing to File " + filePath);
		}
	}
	
	@Test
	public void testDiceDrawer_setterAndGetter() {
		model = new GameModel();
	}

	/**
	 * used by testLoadScores() to put scores
	 * from scorez.txt into a string.
	 */
	private String readFromScores(File file) {
		String scores = null;
		byte[] buffer = new byte[(int) new File(file.getPath()).length()];
	    try {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file.getPath()));
		    stream.read(buffer);
		    scores = new String(buffer);
		    stream.close();
		} catch (Exception e) {
			System.out.println("Error reading from file " + filePath);
			e.printStackTrace();
		}
		return scores;
	}

}