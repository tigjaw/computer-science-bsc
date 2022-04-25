package v6;

public interface GameStrategy {
	public void performStrategy(int[] dice, GameModel model);
	public String printStrategyName();
}