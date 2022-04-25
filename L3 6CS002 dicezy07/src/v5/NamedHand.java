package v5;

public abstract class NamedHand extends Hand {

	public NamedHand(String handType) {
		super(handType);
	}
	
	public abstract void scoreAsJoker(int[] dice);

}