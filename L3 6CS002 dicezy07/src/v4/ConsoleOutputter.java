package v4;

public class ConsoleOutputter {
	private final static String handStrings[] = { "1) Ones", "2) Twos", "3) Threes", "4) Fours",
			"5) Fives", "6) Sixes", "7) Three of a kind",
			"8) Four of a kind", "9) Full house", "A) Short straight",
			"B) Long straight", "C) Dicezy", "D) Chance" };
	
	public static void welcomeMessage() {
		System.out.println("Welcome To Dicezy - Can you become the Dicezy King?");
		System.out.println("Version 1.0 (c) Kevan Buckley, 2010 \n");
		System.out.println("Please enter your name: ");
	}
	
	public static void greetPlayer(String playerName) {
		System.out.printf("Greetings %s. Have fun playing Dicezy \n", playerName);
	}
	
	public static void printMainMenu() {
		System.out.println();
		System.out.println("Main Menu");
		System.out.println("~~~~~~~~~~");
		System.out.println("1) Start game");
		System.out.println("3) View high scores");
		System.out.println("6) Display rules");
		System.out.println("0) Finish Playing");
		System.out.println("What do you want to do (0-6):");
	}
	
	public static void printGameMenu() {
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
	}
	
	public static void printScoringChoices(int[] scoreCard, boolean hasBonus) {
		printScoringChoicesTop(scoreCard);
		printScoringChoicesBottom(scoreCard);
		if (hasBonus) {
			System.out.println("E) Dicezy bonus");
		}
	}
	
	public static void printScoringChoicesBottom(int[] scoreCard) {
		for (int i = 6; i < handStrings.length; i++) {
			if (scoreCard[i] == -1) {
				System.out.println(handStrings[i]);
			}
		}
		System.out.println();
	}
	
	public static void printScoringChoicesTop(int[] scoreCard) {
		for (int i = 0; i < 6; i++) {
			if (scoreCard[i] == -1) {
				System.out.println(handStrings[i]);
			}
		}
		System.out.println();
	}
	
	public static void printScoringChoices(ScoreCard card, boolean hasBonus) {
		printScoringChoicesTop(card);
		printScoringChoicesBottom(card);
		if (hasBonus) {
			System.out.println(card.getHand(14).getHandType());
		}
	}
	
	public static void printScoringChoicesBottom(ScoreCard card) {
		for (int i = 7; i <= 13; i++) {
			if (card.getHand(i).getScore() == -1) {
				System.out.println(card.getHand(i).getHandType());
			}
		}
		System.out.println();
	}
	
	public static void printScoringChoicesTop(ScoreCard card) {
		for (int i = 1; i <= 6; i++) {
			if (card.getHand(i).getScore() == -1) {
				System.out.println(card.getHand(i).getHandType());
			}
		}
		System.out.println();
	}
	
	public static void printGameFinished(String playerName) {
		System.out.println("Lets go back to the main menu " + playerName);
	}
	
	public static void printTurnsLeft(int turnsRemaining) {
		System.out.println(turnsRemaining + " goes left");
	}

}