package v1;
import java.awt.Dimension;
import java.io.*;
import java.util.*;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Main Class, begins game.
 */
public class GameModel {
	private DiceDrawer diceDrawer = new DiceDrawer();
	private List<Score> scores;
	private String playerName;
	private int dicezyBonus = 0;
	private final int ONES = 0;
	private final int TWOS = 1;
	private final int THREES = 2;
	private final int FOURS = 3;
	private final int FIVES = 4;
	private final int SIXES = 5;
	private final int THREE_OF_A_KIND = 6;
	private final int FOUR_OF_A_KIND = 7;
	private final int FULL_HOUSE = 8;
	private final int SHORT_STRAIGHT = 9;
	private final int LONG_STRAIGHT = 10;
	private final int DICEZY = 11;
	private final int CHANCE = 12;
	private final int BONUS_MODE = 13;

	public void loadScores() {
		File f = new File("scorez.txt");
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

	public void run() {
		diceDrawer.setDefaultDice(null);
		diceDrawer.DiceDrawer(this);
		loadScores();
		System.out.println("Welcome To Dicezy - Can you become the Dicezy King?");
		System.out.println("Version 1.0 (c) Kevan Buckley, 2010 \n");
		System.out.println("Please enter your name: ");
		playerName = ConsoleInputter.getString();

		System.out.printf("Greetings %s. Have fun playing Dicezy \n", playerName);
		do {
			System.out.println();
			System.out.println("Main Menu");
			System.out.println("~~~~~~~~~~");
			System.out.println("1) Start game");
			System.out.println("3) View high scores");
			System.out.println("6) Display rules");
			System.out.println("0) Finish Playing");
			System.out.println("What do you want to do (0-6):");
			int menuSelection = ConsoleInputter.getInt();
			switch (menuSelection) {
			case 0:
				System.out.println("bye bye " + playerName);
				System.exit(0);
			case 1:
			case 2:
				if (menuSelection == 1) {
					play();
				}
				break;
			case 3:
				for (Score s : scores) {
					System.out.println(s);
				}
				break;
			case 6:
				displayRules();
				break;
			}
		} while (true);
	}

	public void displayRules() {
		JFrame f = new JFrame("Dicezy rule are like Yahtzee rules");

		f.setSize(new Dimension(300, 300));
		JEditorPane rulesPane;
		try {
			rulesPane = new JEditorPane(
					"http://grail.sourceforge.net/demo/yahtzee/rules.html");
			Thread.sleep(2000);
			String text = rulesPane.getText();
			String newText = text.replaceAll("Yahtzee", "Dicezy");
			rulesPane.setText(newText);
		} catch (Exception e) {
			rulesPane = new JEditorPane("text/plain",
					"Problems retrieving the rules from the Internet");
		}
		f.setContentPane(new JScrollPane(rulesPane));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	boolean hasBonus = false;

	public void play() {
		dicezyBonus = 0;
		int[] scoreCard = new int[13];
		for(int i = 0; i < scoreCard.length; i++) {
			scoreCard[i] = -1;
		}
		int turnsRemaining = 13;

		while (true) {
			diceDrawer.setDefaultDice(null);
			diceDrawer.setReroll(null);

			System.out.println();
			System.out.println("Game Menu");
			System.out.println("~~~~~~~~~");
			System.out.println("1) Roll");
			System.out.println("2) View score card");
			System.out.println("3) Get some words of inspiration");
			System.out.println("4) Autoplay");
			System.out.println("");
			System.out.println("0) Finish game");
			System.out.println("What do you want to do (0-3):");
			int chosenGameType = 0;
			int turns = 0;
			int menuSelection = ConsoleInputter.getInt();
			switch (menuSelection) {
			case 4: // autoplay
				int[] autoPlaySelections = autoPlay(turnsRemaining, playerName);
				chosenGameType = autoPlaySelections[0];
				turns = autoPlaySelections[1];
				int autoTurnNumber = 1;
				for (int r = turns; r > 0; r--) {
					System.out.println("Automated turn number " + autoTurnNumber++);
					turnsRemaining = gameStrategy(scoreCard, turnsRemaining,
							chosenGameType);
				}
				break;
			case 0: // finish game
				saveHighScores();
				diceDrawer.setDefaultDice(null);
				diceDrawer.setReroll(null);
				diceDrawer.repaint();
				System.out.println("Lets go back to the main menu " + playerName);
				return;
			case 3: // get quote
				Quotes.getRandomQuote();
				break;
			case 1: // roll dice
				chosenGameType = 0;
				turnsRemaining = gameStrategy(scoreCard, turnsRemaining, 0);
			case 2: // view score card
				viewScoreCard(scoreCard);
				break;
			} // gameOver
			System.out.println(turnsRemaining + " goes left");
			if (!(scoreCard[0] != -1 || scoreCard[1] != -1 || scoreCard[2] != -1
					|| scoreCard[3] != -1 || scoreCard[4] != -1 || scoreCard[5] != -1
					|| scoreCard[6] != -1 || scoreCard[7] != -1 || scoreCard[8] != -1
					|| scoreCard[9] != -1 || scoreCard[10] != -1 || scoreCard[11] != -1 || scoreCard[12] != -1)
					|| turnsRemaining < 1) {
				gameOver(scoreCard);
				break;
			}

		}
	}

	private void gameOver(int[] scoreCard) {
		int topScoreBeforeBonus = 0;
		for (int i = 0; i <= SIXES; i++) {
			topScoreBeforeBonus += scoreCard[i] > -1 ? scoreCard[i] : 0;
		}
		int topBonus = topScoreBeforeBonus > 63 ? 35 : 0;
		int topScore = topScoreBeforeBonus + topBonus;

		int bottomScore = dicezyBonus;
		for (int i = THREE_OF_A_KIND; i <= CHANCE; i++) {
			bottomScore += scoreCard[i] > -1 ? scoreCard[i] : 0;
		}

		int grandTotal = topScore + bottomScore;

		Score s = new Score(playerName, grandTotal, System.currentTimeMillis());
		scores.add(s);
		Collections.sort(scores);

		System.out.println("Game over - you scored " + grandTotal);
		saveHighScores();
	}

	public void viewScoreCard(int[] scoreCard) {
		int topScoreBeforeBonus = 0;
		for (int i = 0; i <= SIXES; i++) {
			// if (scoreCard[i] > -1) then (topScoreBeforeBonus = scoreCard[i])
			// else (topScoreBeforeBonus = 0)
			topScoreBeforeBonus += scoreCard[i] > -1 ? scoreCard[i] : 0;
		}
		int topBonus = topScoreBeforeBonus > 63 ? 35 : 0;
		int topScore = topScoreBeforeBonus + topBonus;
		int bottomScore = dicezyBonus;
		for (int i = THREE_OF_A_KIND; i <= CHANCE; i++) {
			bottomScore += scoreCard[i] > -1 ? scoreCard[i] : 0;
		}

		int grandTotal = topScore + bottomScore;
		System.out.println();
		String t2 = String.format("%s's Score Card", playerName);
		System.out.println(t2);
		System.out.println(t2.replaceAll(".", "="));
		System.out.printf("%20s %s\n", "Ones", scoreCard[0] == -1 ? "-"
				: Integer.toString(scoreCard[0]));
		System.out.printf("%20s %s\n", "Twos", scoreCard[1] == -1 ? "-"
				: Integer.toString(scoreCard[1]));
		System.out.printf("%20s %s\n", "Threes", scoreCard[2] == -1 ? "-"
				: Integer.toString(scoreCard[2]));
		System.out.printf("%20s %s\n", "Fours", scoreCard[3] == -1 ? "-"
				: Integer.toString(scoreCard[3]));
		System.out.printf("%20s %s\n", "Fives", scoreCard[4] == -1 ? "-"
				: Integer.toString(scoreCard[4]));
		System.out.printf("%20s %s\n", "Sixes", scoreCard[5] == -1 ? "-"
				: Integer.toString(scoreCard[5]));
		System.out.printf("%20s %d\n", "Score", topScoreBeforeBonus);
		System.out.printf("%20s %d\n", "Bonus", topBonus);
		System.out.printf("%20s %d\n", "Upper total", topScore);
		System.out.printf("%20s %s\n", "Three of a kind",
				scoreCard[6] == -1 ? "-" : Integer.toString(scoreCard[6]));
		System.out.printf("%20s %s\n", "Four of a kind",
				scoreCard[7] == -1 ? "-" : Integer.toString(scoreCard[7]));
		System.out.printf("%20s %s\n", "Full house",
				scoreCard[8] == -1 ? "-" : Integer.toString(scoreCard[8]));
		System.out.printf("%20s %s\n", "Short straight",
				scoreCard[9] == -1 ? "-" : Integer.toString(scoreCard[9]));
		System.out.printf("%20s %s\n", "Long straight",
				scoreCard[10] == -1 ? "-" : Integer.toString(scoreCard[10]));
		System.out.printf("%20s %s\n", "Dicezy", scoreCard[11] == -1 ? "-"
				: Integer.toString(scoreCard[11]));
		System.out.printf("%20s %s\n", "Chance", scoreCard[12] == -1 ? "-"
				: Integer.toString(scoreCard[12]));
		System.out.printf("%20s %d\n", "Dicezy bonus", dicezyBonus);
		System.out.printf("%20s %d\n", "Grand total", grandTotal);
	}

	private int gameStrategy(int[] scoreCard, int goesLeft, int chosenGameType) {
		goesLeft--;
		int[] dice = diceDrawer.roll();
		diceDrawer.repaint();
		switch (chosenGameType) {
		case 0: { // roll manually
			System.out.println("Which dice would you like to roll again?");
			String rerollInput = ConsoleInputter.getString();
			boolean[] rerollThese = { false, false, false, false, false };
			// for loop int i = 1... i++
			for(int i = 1; i < 6; i++) {
				if (rerollInput.indexOf("" + i) >= 0) {
					System.out.println(i);
					rerollThese[i-1] = true;
				}
			}
			diceDrawer.reroll(dice, rerollThese);
		}
			break;
		case 1: // random plod away
			boolean chosenDice = false;
			for (int i = 0; i < 6; i++) {
				if (scoreCard[i] == -1) {
					boolean[] rerollChoice = { false, false, false, false, false };
					for (int x = 0; x < 5; x++) {
						if (dice[x] != (i + 1)) {
							rerollChoice[x] = true;
						}
					}
					diceDrawer.reroll(dice, rerollChoice);
					chosenDice = true;
					break;
				}
			}
			if (chosenDice == false) {
				boolean[] rerollChoice = { false, false, false, false, false };
				for (int x = 0; x < 5; x++) {
					if (Math.random() < 0.5) {
						rerollChoice[x] = true;
					}
				}
				diceDrawer.reroll(dice, rerollChoice);
			}
			break;
		case 2: // random - random
			boolean[] rerollChoice = { false, false, false, false, false };
			for (int x = 0; x < 5; x++) {
				if (dice[x] != 6) {
					rerollChoice[x] = true;
				}
			}
			diceDrawer.reroll(dice, rerollChoice);
			break;
		} // end switch
		diceDrawer.repaint();
		
		// start scoring mechanism
		if (chosenGameType == 0) { // if manual play
			if (scoreCard[0] == -1) {
				System.out.println("1) Ones");
			}
			if (scoreCard[1] == -1) {
				System.out.println("2) Twos");
			}
			if (scoreCard[2] == -1) {
				System.out.println("3) Threes");
			}
			if (scoreCard[3] == -1) {
				System.out.println("4) Fours");
			}
			if (scoreCard[4] == -1) {
				System.out.println("5) Fives");
			}
			if (scoreCard[5] == -1) {
				System.out.println("6) Sixes");
			}
			if (scoreCard[6] == -1) {
				System.out.println("7) Three of a kind");
			}
			if (scoreCard[7] == -1) {
				System.out.println("8) Four of a kind");
			}
			if (scoreCard[8] == -1) {
				System.out.println("9) Full house");
			}
			if (scoreCard[9] == -1) {
				System.out.println("A) Short straight");
			}
			if (scoreCard[10] == -1) {
				System.out.println("B) Long straight");
			}
			if (scoreCard[11] == -1) {
				System.out.println("C) Dicezy");
			}
			if (scoreCard[12] == -1) {
				System.out.println("D) Chance");
			}
			if (hasBonus) {
				System.out.println("E) Dicezy bonus");
			}
		}
		
		boolean scoreChosen = false;
		
		while (!scoreChosen) {
			int scoreChoice = 0;
			if (chosenGameType == 0) {
				System.out.println("What do you want to score this as?");
				scoreChoice = ConsoleInputter.getHex() - 1;
			}
			int autoScoreChoice = 0;
			if (chosenGameType == 1) { // if random plodding
				for (int e = 0; e < 6; e++) {
					if (scoreCard[e] == -1) {
						scoreChoice = e;
						autoScoreChoice = 2;
						break;
					}
				}
				autoScoreChoice = autoScoreChoice == 2 ? 2 : 1;
			}
			if (chosenGameType == 2 || autoScoreChoice == 1) {
				autoScoreChoice = 3;
				while (autoScoreChoice == 3) {
					scoreChoice = (int) (Math.random() * 13);
					if (scoreCard[scoreChoice] == -1) {
						autoScoreChoice = 4;
					}
				}
			}
			
			// POSSIBLE LOOP
			
			if (scoreChoice == 0 && scoreCard[0] == -1) {
				System.out.println("You scored this as a ones");
				int totalOnes = 0;
				for (int i = 0; i < 5; i++) {
					if (dice[i] == 1) {
						totalOnes++;
					}
				}
				scoreCard[ONES] = totalOnes;
				scoreChosen = !scoreChosen;
			}
			if (scoreChoice == 1 && scoreCard[1] == -1) {
				System.out.println("You scored this as twos");
				int totalTwos = 0;
				for (int i = 0; i < 5; i++) {
					if (dice[i] == 2) {
						totalTwos++;
						totalTwos++;
					}
				}
				scoreCard[TWOS] = totalTwos;
				scoreChosen = !scoreChosen;
			}
			if (scoreChoice == 2 && scoreCard[2] == -1) {
				System.out.println("You scored this as threes");
				int totalThrees = 0;
				for (int i = 0; i < 5; i++) {
					if (dice[i] == 3) {
						totalThrees++;
					}
				}
				scoreCard[THREES] = totalThrees * 3;
				scoreChosen = !scoreChosen;
			}
			if (scoreChoice == 3 && scoreCard[3] == -1) {
				System.out.println("You scored this as fours");
				int totalFours = 0;
				for (int i = 0; i < 5; i++) {
					if (dice[i] == 4) {
						totalFours++;
					}
				}
				scoreCard[FOURS] = totalFours * 4;
				scoreChosen = !scoreChosen;
			}
			if (scoreChoice == 4 && scoreCard[4] == -1) {
				System.out.println("You scored this as fives ");
				int totalFives = 0;
				for (int i = 0; i < 5; i++) {
					if (dice[i] == 5) {
						totalFives += 5;
					}
				}
				scoreCard[FIVES] = totalFives;
				scoreChosen = !scoreChosen;
			}
			if (scoreChoice == 5 && scoreCard[5] == -1) {
				System.out.println("You scored this as sixes");
				int totalSixes = 0;
				for (int i = 0; i < 5; i++) {
					if (dice[i] == 6) {
						totalSixes += 6;
					}
				}
				scoreCard[SIXES] = totalSixes;
				scoreChosen = !scoreChosen;
			}
			if (scoreChoice == THREE_OF_A_KIND && scoreCard[THREE_OF_A_KIND] == -1) {
				System.out.println("You scored this as a three of a kind");
				scoreCard[THREE_OF_A_KIND] = 0;
				int[] countCards = { 0, 0, 0, 0, 0, 0 };
				for (int i = 0; i < 5; i++) {
					countCards[dice[i] - 1]++;
				}
				for (int i = 0; i < 6; i++) {
					if (countCards[i] >= 3) {
						scoreCard[THREE_OF_A_KIND] = 0;
						for (int j = 0; j < 5; j++) {
							scoreCard[THREE_OF_A_KIND] += dice[j];
						}
						break;
					}
				}
				scoreChosen = !scoreChosen;
			}
			if (scoreChoice == FOUR_OF_A_KIND && scoreCard[FOUR_OF_A_KIND] == -1) {
				System.out.println("You scored this as a four of a kind");
				int[] countCards = { 0, 0, 0, 0, 0, 0 };
				scoreCard[FOUR_OF_A_KIND] = 0;
				for (int i = 0; i < 5; i++) {
					countCards[dice[i] - 1]++;
				}
				for (int i = 0; i < 6; i++) {
					if (countCards[i] >= 4) {
						scoreCard[FOUR_OF_A_KIND] = 0;
						for (int j = 0; j < 5; j++) {
							scoreCard[FOUR_OF_A_KIND] += dice[j];
						}
						break;
					}
				}
				scoreChosen = !scoreChosen;
			}
			if (scoreChoice == FULL_HOUSE && scoreCard[FULL_HOUSE] == -1) {
				System.out.println("You scored this as a full house");
				int[] countCards = { 0, 0, 0, 0, 0, 0 };
				for (int i = 0; i < 5; i++) {
					countCards[dice[i] - 1]++;
				}
				if ((countCards[0] == 3 || countCards[1] == 3 || countCards[2] == 3 || countCards[3] == 3
						|| countCards[4] == 3 || countCards[5] == 3)
						&& (countCards[0] == 2 || countCards[1] == 2 || countCards[2] == 2 || countCards[3] == 2
								|| countCards[4] == 2 || countCards[5] == 2)) {
					scoreCard[FULL_HOUSE] = 25;
				} else {
					scoreCard[FULL_HOUSE] = 0;
				}
				scoreChosen = !scoreChosen;
			}
			if (scoreChoice == LONG_STRAIGHT && scoreCard[LONG_STRAIGHT] == -1) {
				System.out.println("You scored this as a long straight");
				int[] countCards = { 0, 0, 0, 0, 0, 0 };
				scoreCard[LONG_STRAIGHT] = 0;
				for (int i = 0; i < 5; i++) {
					countCards[dice[i] - 1]++;
				}
				boolean good = true;
				boolean bad = false;
				for (int i = 0; i < 6; i++) {
					if (countCards[i] > 1) {
						good = false;
						bad = true;
						break;
					}
				}
				if (good && !bad) {
					if (countCards[0] != countCards[5]) {
						scoreCard[LONG_STRAIGHT] = 40;
					} else {
						scoreCard[LONG_STRAIGHT] = 0;
					}
				} else {
					scoreCard[LONG_STRAIGHT] = 0;
				}
				scoreChosen = !scoreChosen;
			}
			if (scoreChoice == SHORT_STRAIGHT && scoreCard[SHORT_STRAIGHT] == -1) {
				System.out.println("You scored this as a short straight");
				int[] countCards = { 0, 0, 0, 0, 0, 0 };
				scoreCard[SHORT_STRAIGHT] = 0;
				for (int i = 0; i < 5; i++) {
					countCards[dice[i] - 1]++;
				}

				if ((countCards[0] >= 1 && countCards[1] >= 1 && countCards[2] >= 1 && countCards[3] >= 1)
						|| (countCards[4] >= 1 && countCards[1] >= 1 && countCards[2] >= 1 && countCards[3] >= 1)
						|| (countCards[4] >= 1 && countCards[5] >= 1 && countCards[2] >= 1 && countCards[3] >= 1)) {
					scoreCard[SHORT_STRAIGHT] = 30;
				} else {
					scoreCard[SHORT_STRAIGHT] = 0;
				}
				scoreChosen = !scoreChosen;
			}
			if (scoreChoice == DICEZY && scoreCard[DICEZY] == -1) {
				System.out.println("You scored this as a dicezy");

				hasBonus = true;
				scoreChosen = !scoreChosen;
				int[] countCards = { 0, 0, 0, 0, 0, 0 };
				for (int i = 0; i < 5; i++) {
					countCards[dice[i] - 1]++;
				}
				if ((countCards[0] == 5 || countCards[1] == 5 || countCards[2] == 5 || countCards[3] == 5
						|| countCards[4] == 5 || countCards[5] == 5)) {
					scoreCard[DICEZY] = 50;
				} else {
					scoreCard[DICEZY] = 0;
				}
			}
			if (scoreChoice == BONUS_MODE && hasBonus != false) {
				System.out.println("You scored this as a dicezy bonus!!!!!!!!!");
				scoreChosen = !scoreChosen;

				// check if there really is a five of a kind
				int[] countCards = { 0, 0, 0, 0, 0, 0 };
				for (int i = 0; i < 5; i++) {
					countCards[dice[i] - 1]++;
				}
				if ((countCards[0] == 5 || countCards[1] == 5 || countCards[2] == 5 || countCards[3] == 5
						|| countCards[4] == 5 || countCards[5] == 5)) {
					// We end up here if it is a five of a kind.
					// We can only award the bonus if the number has been in the
					// top section
					// Find out which number we have a set of
					int counter;
					for (counter = 0; counter < 6; counter++) {
						if (countCards[counter] == 5) {
							break;
						}
					}
					if (counter < 6) {
						// award no bonus if the top section part for this
						// number has not been used
						scoreCard[counter] = counter * 5;
					} else {
						// award bonus because top section part for this set is
						// already complete
						dicezyBonus += 100;
						if (scoreCard[THREE_OF_A_KIND] == -1
								|| scoreCard[FOUR_OF_A_KIND] == -1
								|| scoreCard[FULL_HOUSE] == -1
								|| scoreCard[SHORT_STRAIGHT] == -1
								|| scoreCard[LONG_STRAIGHT] == -1
								|| scoreCard[CHANCE] == -1) {
							
							System.out.println("You may use your Dicezy as a joker");
							
							if (scoreCard[THREE_OF_A_KIND] == -1) {
								System.out.println("7) Three of a kind");
							}
							if (scoreCard[FOUR_OF_A_KIND] == -1) {
								System.out.println("8) Four of a kind");
							}
							if (scoreCard[FULL_HOUSE] == -1) {
								System.out.println("9) Full house");
							}
							if (scoreCard[SHORT_STRAIGHT] == -1) {
								System.out.println("A) Short straight");
							}
							if (scoreCard[LONG_STRAIGHT] == -1) {
								System.out.println("B) Long straight");
							}
							if (scoreCard[CHANCE] == -1) {
								System.out.println("D) Chance");
							}
							System.out.println();

							boolean scoreChosen2 = false;
							while (!scoreChosen2) {
								System.out.println("What do you want to score this as?");
								int scoreChoice2 = ConsoleInputter.getHex() - 1;
								if (scoreChoice2 == THREE_OF_A_KIND
										&& scoreCard[THREE_OF_A_KIND] == -1) {
									scoreCard[THREE_OF_A_KIND] = dice[0] + dice[1] + dice[2]
											+ dice[3] + dice[4] + dice[5];
									scoreChosen2 = !scoreChosen2;
								}
								if (scoreChoice2 == FOUR_OF_A_KIND
										&& scoreCard[FOUR_OF_A_KIND] == -1) {
									scoreCard[FOUR_OF_A_KIND] = dice[0] + dice[1] + dice[2]
											+ dice[3] + dice[4] + dice[5];
									scoreChosen2 = !scoreChosen2;
								}
								if (scoreChoice2 == FULL_HOUSE && scoreCard[FULL_HOUSE] == -1) {
									scoreCard[FULL_HOUSE] = 25;
									scoreChosen2 = !scoreChosen2;
								}
								if (scoreChoice2 == 9 && scoreCard[SHORT_STRAIGHT] == -1) {
									scoreCard[9] = 30;
									scoreChosen2 = !scoreChosen2;
								}
								if (scoreChoice2 == 10 && scoreCard[LONG_STRAIGHT] == -1) {
									scoreCard[10] = 40;
									scoreChosen2 = !scoreChosen2;
								}
								if (scoreChoice2 == 12 && scoreCard[CHANCE] == -1) {
									scoreCard[12] = dice[0] + dice[1] + dice[2] + dice[3] + dice[4]
											+ dice[5];
									scoreChosen2 = !scoreChosen2;
								}
							}
						} else {
							System.out.println("Bottom section is complete you must take a forfeit in the top section");
							if (scoreCard[0] == -1) {
								System.out.println("1) Ones");
							}
							if (scoreCard[1] == -1) {
								System.out.println("2) Twos");
							}
							if (scoreCard[2] == -1) {
								System.out.println("3) Threes");
							}
							if (scoreCard[3] == -1) {
								System.out.println("4) Fours");
							}
							if (scoreCard[4] == -1) {
								System.out.println("5) Fives");
							}
							if (scoreCard[5] == -1) {
								System.out.println("6) Sixes");
							}
							System.out.println();

							boolean scoreChosen3 = false;
							while (!scoreChosen3) {
								System.out.println("What do you want to score this as?");
								int scoreChoice3 = ConsoleInputter.getHex();
								for (int i = 0; i <= 6; i++) {
									if (scoreChoice3 == i+1 && scoreCard[i] == -1) {
										scoreCard[i] = 0;
										scoreChosen3 = true;
									}
								}
							}
						}
					}
				} else {
					scoreCard[DICEZY] = 0;
					System.out.println("Don't try to cheat!!");
					System.out.println("You must now score a zero");
					if (scoreCard[0] == -1) {
						System.out.println("1) Ones");
					}
					if (scoreCard[1] == -1) {
						System.out.println("2) Twos");
					}
					if (scoreCard[2] == -1) {
						System.out.println("3) Threes");
					}
					if (scoreCard[3] == -1) {
						System.out.println("4) Fours");
					}
					if (scoreCard[4] == -1) {
						System.out.println("5) Fives");
					}
					if (scoreCard[5] == -1) {
						System.out.println("6) Sixes");
					}
					if (scoreCard[THREE_OF_A_KIND] == -1) {
						System.out.println("7) Three of a kind");
					}
					if (scoreCard[FOUR_OF_A_KIND] == -1) {
						System.out.println("8) Four of a kind");
					}
					if (scoreCard[FULL_HOUSE] == -1) {
						System.out.println("9) Full house");
					}
					if (scoreCard[SHORT_STRAIGHT] == -1) {
						System.out.println("A) Short straight");
					}
					if (scoreCard[LONG_STRAIGHT] == -1) {
						System.out.println("B) Long straight");
					}
					if (scoreCard[CHANCE] == -1) {
						System.out.println("D) Chance");
					}
					System.out.println();
					System.out.println("Which section will you score zero for?");
					Boolean zeroChosen = false;
					while (zeroChosen == false) {
						int zeroChoice = ConsoleInputter.getHex() - 1;
						for (int i = 0; i < 13; i++) {
							if (zeroChoice == i && scoreCard[i] == -1) {
								zeroChosen = true;
								scoreCard[i] = 0;
							}
						}
					}
				}
			}
			if (scoreChoice == CHANCE && scoreCard[CHANCE] == -1) {
				scoreChosen = !scoreChosen;
				scoreCard[CHANCE] = 0;
				for (int i = 0; i < 5; i++) {
					scoreCard[CHANCE] += dice[i];
				}
			}

		}
		return goesLeft;
	}

	private int[] autoPlay(int turnsRemaining, String player) {
		int[] autoOptions = { 2, turnsRemaining };
		System.out.println("Autoplay styles");
		System.out.println("1) Plodding away");
		System.out.println("2) Random play");
		System.out.println();
		System.out.println("Which style of play " + player + "?");

		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int styleChoice = 0;
		do {
			try {
				styleChoice = Integer.parseInt(r.readLine());
			} catch (Exception e) {
				styleChoice = 0;
			}
		} while (styleChoice != 1 && styleChoice != 2);
		
		System.out.println("For how many turns (maximum of " + turnsRemaining + ")");
		int turns = 0;
		do {
			try {
				turns = Integer.parseInt(r.readLine());
			} catch (Exception e) {
				turns = 0;
			}
		} while (turns > turnsRemaining || turns < 1);
		
		autoOptions[0] = styleChoice;
		autoOptions[1] = turns;

		return autoOptions;
	}

	public void saveHighScores() {
		try {
			PrintWriter p = new PrintWriter(new FileWriter("scorez.txt"));
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

	public int getDicezyBonus() {
		return dicezyBonus;
	}

	public void setDicezyBonus(int dicezyBonus) {
		this.dicezyBonus = dicezyBonus;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public DiceDrawer getDiceDrawer() {
		return diceDrawer;
	}

	public void setDiceDrawer(DiceDrawer diceDrawer) {
		this.diceDrawer = diceDrawer;
	}

	public static void main(String[] args) {
		new GameModel().run();

	}

}