package v6;

import java.io.*;
import java.util.HashMap;

/**
 * Main Class, begins game.
 */
public class GameModel {
	private ScoreCard scoreCard;
	private DiceDrawer diceDrawer;
	private ScoreList scores;
	private String playerName;
	private HashMap <Integer, GameStrategy> strategies;
	
	private void run() {
		diceDrawer = new DiceDrawer(this);
		scores = new ScoreList();
		ConsoleOutputter.welcomeMessage();
		playerName = ConsoleInputter.getString();
		ConsoleOutputter.greetPlayer(playerName);
		do {
			ConsoleOutputter.printMainMenu();
			int menuSelection = ConsoleInputter.getInt();
			switch (menuSelection) {
			case 0: // Exit
				System.out.println("bye bye " + playerName);
				System.exit(0);
			case 1: // Play
			case 2:
				play();
				break;
			case 3: // Print High Scores
				scores.printScores();
				break;
			case 6: // Display Rules
				new RulesDisplay();
				break;
			}
		} while (true);
	}

	private void play() {
		int turnsRemaining = 13;
		scoreCard = new ScoreCard(playerName);

		while (true) {
			initiateStrategies();
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
		GameStrategy strategy = strategies.get(chosenGameType);
		strategy.performStrategy(dice, this);
		diceDrawer.repaint();
		
		scoringMechanism(chosenGameType, dice);
		return goesLeft;
	}

	protected boolean scoringMechanism(int chosenGameType, int[] dice) {
		boolean scoreChosen = false;
		while (scoreChosen == false) {
			int scoreChoice = 0;
			if (chosenGameType == 0) {
				System.out.println("What do you want to score this as?");
				scoreChoice = ConsoleInputter.getHex();
			}
			scoreChosen = scoreCard.scoring(dice, chosenGameType, scoreChoice);
		}
		return scoreChosen;
	}

	protected void initiateStrategies() {
		strategies = new HashMap<Integer, GameStrategy>();
		strategies.put(0, new ManualStrategy());
		strategies.put(1, new AutoPlodStrategy());
		strategies.put(2, new AutoRandomStrategy());
	}

	private int autoPlay(int turnsRemaining) {
		System.out.println("Autoplay styles");
		for(int i = 1; i < strategies.size(); i++) {
			System.out.println(i + " - " + strategies.get(i).printStrategyName());
		}
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
		} while (styleChoice == 0 || styleChoice > strategies.size());
		
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

	public ScoreCard getScoreCard() {
		return scoreCard;
	}
	
	public void setScoreCard(ScoreCard card) {
		this.scoreCard = card;
	}

	public DiceDrawer getDiceDrawer() {
		return diceDrawer;
	}

	public void setDiceDrawer(DiceDrawer diceDrawer) {
		this.diceDrawer = diceDrawer;
	}

	public HashMap<Integer, GameStrategy> getStrategies() {
		return strategies;
	}

	public static void main(String[] args) {
		new GameModel().run();
	}

}