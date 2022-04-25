package petsToGo;

public class Money {
	private int pounds;
	private int pence;
	private String costTxt;
	private int poundsAndPence;
	
	public Money() {
		super();
		this.poundsAndPence = -100;
		this.pounds = -200;
		this.pence = -300;
		this.costTxt = "no value";
	}
	
	public Money(int pounds, int pence) {
		super();
		String inputTxt = "";
		if(pence<10) {
			inputTxt = "" + pounds + "0" + pence;
		} else {
			inputTxt = "" + pounds + pence;
		}
		this.poundsAndPence = Integer.parseInt(inputTxt);
		this.pounds = poundsAndPence / 100;
		this.pence = poundsAndPence - (pounds * 100);
		if(pence<10) {
			this.costTxt = "£" + pounds + "." + "0" + pence;
		} else {
			this.costTxt = "£" + pounds + "." + pence;
		}
	}
	
	public Money(int poundsAndPence) {
		super();
		this.pounds = poundsAndPence / 100;
		this.pence = poundsAndPence - (pounds * 100);
		if(this.pence < 0) {
			this.costTxt = "£" + this.pounds + "." + "0" + this.pence;
		} else {
			this.costTxt = "£" + this.pounds + "." + this.pence;
		}
	}
	
	public void addMoney(int poundsAndPence) {
		this.poundsAndPence += poundsAndPence;
		this.pounds = this.poundsAndPence / 100;
		this.pence = this.poundsAndPence - (pounds * 100);
		if(this.pence < 0) {
			this.costTxt = "£" + pounds + "." + "0" + pence;
		} else {
			this.costTxt = "£" + pounds + "." + pence;
		}
	}

	/**
	 * @return the poundsAndPence
	 */
	public int getPoundsAndPence() {
		return poundsAndPence;
	}

	/**
	 * @param poundsAndPence the poundsAndPence to set
	 */
	public boolean setPoundsAndPence(int poundsAndPence) {
		if(poundsAndPence >= 0) {
			this.poundsAndPence = poundsAndPence;
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * @return the pounds
	 */
	public int getPounds() {
		return pounds;
	}

	/**
	 * @param pounds the pounds to set
	 */
	public boolean setPounds(int pounds) {
		if(pounds >= 0) {
			this.pounds = pounds;
			return true;
		} else {
			return false;
		}
		
	}

	/**
	 * @return the pence
	 */
	public int getPence() {
		return pence;
	}

	/**
	 * @param pence the pence to set
	 */
	public boolean setPence(int pence) {
		if(pence >= 0) {
			this.pence = pence;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the costTxt
	 */
	public String getCostTxt() {
		return costTxt;
	}

}