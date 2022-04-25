package v5;

import java.io.*;

/**
 * Main Class, begins game.
 */
public class GameModel {
	private ScoreCard scoreCard;
	private DiceDrawer diceDrawer;
	private ScoreList scores;
	private String playerName;

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
		int turnsRemaining = 13;
		scoreCard = new ScoreCard(playerName);

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
				scoreCard.viewScoreCard();
				break;
			} // end switch
			ConsoleOutputter.printTurnsLeft(turnsRemaining);
			if (turnsRemaining < 1) { // Game over
				gameOver();
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

	private void gameOver() {
		int grandTotal = scoreCard.calculateGrandTotal();
		Score s = new Score(playerName, grandTotal, System.currentTimeMillis());
		scores.addScore(s);
		System.out.println("Game over - you scored " + grandTotal);
		scores.saveHighScores();
	}

	private int gameStrategy(int goesLeft, int chosenGameType) {
		goesLeft--;
		int[] dice = diceDrawer.roll();
		diceDrawer.repaint();
		switch (chosenGameType) {
		case 0: // roll manually
			System.out.println("Which dice would you like to roll again?");
			manualRollStrategy(dice, ConsoleInputter.getString());
			break;
		case 1: // auto - plod away
			autoPlodStrategy(dice);
			break;
		case 2: // auto - random
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
				scoreChoice = ConsoleInputter.getHex();
			}
			scoreChosen = scoreCard.scoring(dice, chosenGameType, scoreChoice);
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
		for (int i = 0; i <6; i++) {
			Hand h = scoreCard.getHand(i+1);
			if (h.getScore() == -1) {
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

	public void manualRollStrategy(int[] dice, String rerollInput) {
		boolean[] rerollThese = { false, false, false, false, false };
		// for loop int i = 1... i++
		for(int i = 1; i < 6; i++) {
			if (rerollInput.indexOf("" + i) >= 0) {
				System.out.println(i);
				rerollThese[i-1] = true;
			}
		}
		diceDrawer.reroll(dice, rerollThese);
		ConsoleOutputter.printScoringChoices(scoreCard, scoreCard.getHasBonus());
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