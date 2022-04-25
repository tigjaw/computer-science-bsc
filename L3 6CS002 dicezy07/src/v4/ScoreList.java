package v4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ScoreList {
	private List<Score> scores;
	private String fileToRead = "scorez.txt";
	
	public ScoreList() {
		fileToRead = "scorez.txt";
		loadScores();
	}
	
	public void printScores() {
		for (Score s : scores) {
			System.out.println(s);
		}
	}
	
	public void addScore(Score score) {
		scores.add(score);
		Collections.sort(scores);
	}
	
	public void loadScores() {
		File f = new File(fileToRead);
		if (f.exists() && f.isFile() && f.canRead()) {
			scores = new LinkedList<Score>();
			try {
				BufferedReader r = new BufferedReader(new FileReader(f));
				while (true) {
					String line1 = r.readLine();
					if (line1 == null) {
						break;
					}
					String line2 = r.readLine();
					if (line2 == null) {
						break;
					}
					String line3 = r.readLine();
					if (line3 == null) {
						break;
					}
					Score s = new Score(line1, Integer.parseInt(line2), 
							(long) Double.parseDouble(line3));
					scores.add(s);
				}
				Collections.sort(scores);
			} catch (Exception e) {
				System.out.println("Computer says Doh!!");
				System.exit(0);
			}
		} else {
			System.out.println("Creating new score table");
			scores = new LinkedList<Score>();
			scores.add(new Score("Martha Farquhar", 100, 1281625395123L));
			scores.add(new Score("Juan Kaur", 50, 1281625395123L));
			Collections.sort(scores);
		}
	}
	
	public void saveHighScores() {
		try {
			PrintWriter p = new PrintWriter(new FileWriter(fileToRead));
			for (Score s : scores) {
				p.println(s.getName());
				p.println(s.getScore());
				p.println(s.getDate());
			}
			p.flush();
			p.close();
		} catch (Exception e) {
			for (int i = 0; i < 1000; i++) {
				System.out.println("Problems writing high scores table to disk");
			}
		}
	}
	
	public List<Score> getScores() {
		return scores;
	}
	
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

}