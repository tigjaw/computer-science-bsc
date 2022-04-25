package v6;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class Test_ScoreList {
	ScoreList scores;
	
	@Test
	public void testScores_getterAndSetter() {
		scores = new ScoreList();

		List<Score> testScores = new LinkedList<Score>();
		String name = "Dinah Saurs";
		int score = 97;
		long date = 1281625395123L;
		Score testScore = new Score(name, score, date);
		testScores.add(testScore);
		
		scores.setScores(testScores);
		assertEquals(testScores, scores.getScores());
	}
	
	String filePath = "scorez.txt";
	
	@Test
	public void testLoadScores() {
		scores = new ScoreList();
		
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
		
		scores.loadScores();
		assertEquals(name, scores.getScores().get(0).getName());
		
		// rewrite archived scores to file
		restoreBackup(archiveScores);
	}
	
	@Test
	public void testSaveScores() {
		scores = new ScoreList();
		List<Score> scoreList = new LinkedList<Score>();
		
		File file = new File(filePath);
		String archiveScores = readFromScores(file);
		System.out.println("Archive:\n" + archiveScores + "\n");
		
		String name = "Dinah Saurs";
		int score = 97;
		long date = 1281625395123L;
		Score testScore = new Score(name, score, date);
		scoreList.add(testScore);
		scores.setScores(scoreList);
		scores.saveHighScores();
		
		scores.loadScores();
		assertEquals(name, scores.getScores().get(0).getName());
		
		// rewrite archived scores to file
		restoreBackup(archiveScores);
	}
	
	public void restoreBackup(String archiveScores) {
		try {
			PrintWriter p;
			p = new PrintWriter(new FileWriter(filePath));
			p.println(archiveScores.trim());
			p.close();
		} catch (Exception e) {
			System.out.println("Error Writing to File " + filePath);
		}
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