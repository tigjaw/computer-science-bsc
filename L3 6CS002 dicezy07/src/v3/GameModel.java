package v3;

import java.io.*;

/**
 * Main Class, begins game.
 */
public class GameModel {
	private int[] scoreCard = new int[13];
	private DiceDrawer diceDrawer;
	private ScoreList scores;
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
	private boolean hasBonus = false;

	public void run() {
		diceDrawer = new DiceDrawer(this);
		scores = new ScoreList();
		ConsoleOutputter.welcomeMessage();
		playerName = ConsoleInputter.getString();
		ConsoleOutputter.greetPlayer(playerName);
		do {
			ConsoleOutputter.printMainMenu();
			int menuSelection = ConsoleInputter.getInt();
			switch (menuSelection) {
			case 0:
				System.out.println("bye bye " + playerName);
				System.exit(0);
			case 1:
			case 2:
				play();
				break;
			case 3:
				scores.printScores();
				break;
			case 6:
				new RulesDisplay();
				break;
			}
		} while (true);
	}

	public void play() {
		dicezyBonus = 0;
		for(int i = 0; i < scoreCard.length; i++) {
			scoreCard[i] = -1;
		}
		int turnsRemaining = 13;

		while (true) {
			diceDrawer.setDefaultDice(null);
			diceDrawer.setReroll(null);
			ConsoleOutputter.printGameMenu();
			int chosenGameType = 0;
			int menuSelection = ConsoleInputter.getInt();
			switch (menuSelection) {
			case 4: // autoplay
				turnsRemaining = autoPlay(turnsRemaining);
				break;
			case 0: // finish game
				finishGame();
				return;
			case 3: // get quote
				Quotes.getRandomQuote();
				break;
			case 1: // roll dice
				chosenGameType = 0;
				turnsRemaining = gameStrategy(turnsRemaining, chosenGameType);
			case 2: // view score card
				viewScoreCard(scoreCard);
				break;
			} // end switch
			ConsoleOutputter.printTurnsLeft(turnsRemaining);
			if (turnsRemaining < 1) { // Game over
				gameOver(scoreCard);
				break;
			}
		}// end while (true)
	}

	private void finishGame() {
		scores.saveHighScores();
		diceDrawer.setDefaultDice(null);
		diceDrawer.setReroll(null);
		diceDrawer.repaint();
		ConsoleOutputter.printGameFinished(playerName);
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
		scores.addScore(s);

		System.out.println("Game over - you scored " + grandTotal);
		scores.saveHighScores();
	}

	public void viewScoreCard(int[] scoreCard) {
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

	private int gameStrategy(int goesLeft, int chosenGameType) {
		goesLeft--;
		int[] dice = diceDrawer.roll();
		diceDrawer.repaint();
		switch (chosenGameType) {
		case 0: // roll manually
			manualRollStrategy(dice);
			break;
		case 1: // random plod away
			autoPlodStrategy(dice);
			break;
		case 2: // random - random
			autoRandomStrategy(dice);
			break;
		} // end switch
		diceDrawer.repaint();
		
		// start scoring mechanism
		boolean scoreChosen = false;
		while (!scoreChosen) {
			int scoreChoice = 0;
			if (chosenGameType == 0) {
				System.out.println("What do you want to score this as?");
				scoreChoice = ConsoleInputter.getHex() - 1;
				System.out.println(scoreChoice);
			}
			scoreChosen = scoring(chosenGameType, dice, scoreChosen,
					scoreChoice);
			
		}
		return goesLeft;
	}
	
	public void autoRandomStrategy(int[] dice) {
		boolean[] rerollChoice = { false, false, false, false, false };
		for (int x = 0; x < 5; x++) {
			if (dice[x] != 6) {
				rerollChoice[x] = true;
			}
		}
		diceDrawer.reroll(dice, rerollChoice);
	}

	public void autoPlodStrategy(int[] dice) {
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
	}

	public void manualRollStrategy(int[] dice) {
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
		ConsoleOutputter.printScoringChoices(scoreCard, hasBonus);
	}

	private int autoPlay(int turnsRemaining) {
		System.out.println("Autoplay styles");
		System.out.println("1) Plodding away");
		System.out.println("2) Random play");
		System.out.println();
		System.out.println("Which style of play " + playerName + "?");
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
		
		int autoTurnNumber = 1;
		for (int t = turns; t > 0; t--) {
			System.out.println("Automated turn number " + autoTurnNumber++);
			turnsRemaining = gameStrategy(turnsRemaining, styleChoice);
		}
		return turnsRemaining;

	}

	public boolean scoring(int chosenGameType, int[] dice,
			boolean scoreChosen, int scoreChoice) {
		scoreChoice = autoScoring(chosenGameType, scoreChoice);
		System.out.println(scoreChoice);;
		// POSSIBLE LOOP
		scoreChosen = scoreOnes(dice, scoreChosen, scoreChoice);
		scoreChosen = scoreTwos(dice, scoreChosen, scoreChoice);
		scoreChosen = scoreThrees(dice, scoreChosen, scoreChoice);
		scoreChosen = scoreFours(dice, scoreChosen, scoreChoice);
		scoreChosen = scoreFives(dice, scoreChosen, scoreChoice);
		scoreChosen = scoreSixes(dice, scoreChosen, scoreChoice);
		scoreChosen = scoreThreeofaKind(dice, scoreChosen, scoreChoice);
		scoreChosen = scoreFourofaKind(dice, scoreChosen, scoreChoice);
		scoreChosen = scoreFullHouse(dice, scoreChosen, scoreChoice);
		scoreChosen = scoreLongStraight(dice, scoreChosen, scoreChoice);
		scoreChosen = scoreShortStraight(dice, scoreChosen, scoreChoice);
		scoreChosen = scoreDicezy(dice, scoreChosen, scoreChoice);
		scoreChosen = scoreDicezyBonus(dice, scoreChosen, scoreChoice);
		scoreChosen = scoreChance(dice, scoreChosen, scoreChoice);
		return scoreChosen;
	}
	
	public int autoScoring(int chosenGameType, int scoreChoice) {
		boolean autoScoreModifier = false;
		if (chosenGameType == 1) { // if random plodding
			for (int e = 0; e < 6; e++) {
				if (scoreCard[e] == -1) {
					scoreChoice = e;
					autoScoreModifier = true;
					break;
				}
			}
			autoScoreModifier = autoScoreModifier == true ? true : false;
		}
		if (chosenGameType == 2 || (autoScoreModifier == false && chosenGameType != 0)) {
			autoScoreModifier = true;
			while (autoScoreModifier) {
				scoreChoice = (int) (Math.random() * 13);
				if (scoreCard[scoreChoice] == -1) {
					autoScoreModifier = false;
				}
			}
		}
		return scoreChoice;
	}

	public boolean scoreOnes(int[] dice, boolean scoreChosen, int scoreChoice) {
		if (scoreChoice == 0 && scoreCard[0] == -1) {
			System.out.println("You scored this as ones");
			int totalOnes = 0;
			for (int i = 0; i < 5; i++) {
				if (dice[i] == 1) {
					totalOnes++;
				}
			}
			scoreCard[ONES] = totalOnes;
			scoreChosen = !scoreChosen;
		}
		return scoreChosen;
	}
	
	public boolean scoreTwos(int[] dice, boolean scoreChosen, int scoreChoice) {
		if (scoreChoice == 1 && scoreCard[1] == -1) {
			System.out.println("You scored this as twos");
			int totalTwos = 0;
			for (int i = 0; i < 5; i++) {
				if (dice[i] == 2) {
					totalTwos++;;
				}
			}
			scoreCard[TWOS] = totalTwos * 2;
			scoreChosen = !scoreChosen;
		}
		return scoreChosen;
	}
	
	public boolean scoreThrees(int[] dice, boolean scoreChosen, int scoreChoice) {
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
		return scoreChosen;
	}
	
	public boolean scoreFours(int[] dice, boolean scoreChosen, int scoreChoice) {
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
		return scoreChosen;
	}
	
	public boolean scoreFives(int[] dice, boolean scoreChosen, int scoreChoice) {
		if (scoreChoice == 4 && scoreCard[4] == -1) {
			System.out.println("You scored this as fives ");
			int totalFives = 0;
			for (int i = 0; i < 5; i++) {
				if (dice[i] == 5) {
					totalFives++;
				}
			}
			scoreCard[FIVES] = totalFives * 5;
			scoreChosen = !scoreChosen;
		}
		return scoreChosen;
	}
	
	public boolean scoreSixes(int[] dice, boolean scoreChosen, int scoreChoice) {
		if (scoreChoice == 5 && scoreCard[5] == -1) {
			System.out.println("You scored this as sixes");
			int totalSixes = 0;
			for (int i = 0; i < 5; i++) {
				if (dice[i] == 6) {
					totalSixes++;
				}
			}
			scoreCard[SIXES] = totalSixes * 6;
			scoreChosen = !scoreChosen;
		}
		return scoreChosen;
	}
	
	public boolean scoreThreeofaKind(int[] dice, boolean scoreChosen,
			int scoreChoice) {
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
		return scoreChosen;
	}

	public boolean scoreFourofaKind(int[] dice, boolean scoreChosen,
			int scoreChoice) {
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
		return scoreChosen;
	}

	public boolean scoreFullHouse(int[] dice, boolean scoreChosen,
			int scoreChoice) {
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
		return scoreChosen;
	}

	public boolean scoreLongStraight(int[] dice, boolean scoreChosen,
			int scoreChoice) {
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
		return scoreChosen;
	}

	public boolean scoreShortStraight(int[] dice, boolean scoreChosen,
			int scoreChoice) {
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
		return scoreChosen;
	}

	public boolean scoreDicezy(int[] dice, boolean scoreChosen, int scoreChoice) {
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
		return scoreChosen;
	}

	public boolean scoreChance(int[] dice, boolean scoreChosen, int scoreChoice) {
		if (scoreChoice == CHANCE && scoreCard[CHANCE] == -1) {
			scoreChosen = !scoreChosen;
			scoreCard[CHANCE] = 0;
			for (int i = 0; i < 5; i++) {
				scoreCard[CHANCE] += dice[i];
			}
		}
		return scoreChosen;
	}

	public boolean scoreDicezyBonus(int[] dice, boolean scoreChosen,
			int scoreChoice) {
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
						ConsoleOutputter.printScoringChoicesBottom(scoreCard);
	
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
						ConsoleOutputter.printScoringChoicesTop(scoreCard);
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
				ConsoleOutputter.printScoringChoices(scoreCard, false);
				System.out.println("\nWhich section will you score zero for?");
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
		return scoreChosen;
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