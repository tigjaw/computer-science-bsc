package v5;

import java.text.*;
import java.util.*;

public class Score implements Comparable<Score> {
	private String name;
	private int score;
	private long date;
	
	public Score(String name, int score, long date) {
		this.name = name;
		this.score = score;
		this.date = date;
	}
	
	public Score(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public long getDate() {
		return date;
	}

	public int compareTo(Score s) {
		return -(this.getScore() - s.getScore());
	}

	public String toString() {
		StringBuffer b = new StringBuffer();

		b.append(String.format("%-5d", getScore()));
		if (getName().length() > 20) {
			b.append(String.format("%-20s ", getName().substring(0, 20)));
		} else {
			b.append(String.format("%-20s ", getName()));
		}
		DateFormat f = DateFormat.getDateInstance(DateFormat.LONG);

		b.append(f.format(new Date(getDate())));
		return b.toString();
	}

	public int toInteger() {
		return (int) (getDate() - getScore());
	}
}