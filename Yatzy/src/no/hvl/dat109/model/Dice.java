package no.hvl.dat109.model;

public class Dice {
	private int value;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void RollDice() {
		this.value = (int) (Math.random()*6 + 1);
	}
}
