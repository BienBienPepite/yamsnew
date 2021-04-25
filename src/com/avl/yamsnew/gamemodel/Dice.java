package com.avl.yamsnew.gamemodel;

public class Dice {
	
	private DiceValue diceValue;
	
	public Dice() {
		this.diceValue = DiceValue.ACE;
	}
	
	public Dice(DiceValue diceValue) {
		
		/*
		 * this constructor is only used to test the program
		 */
		
		this.diceValue = diceValue;
	}
	

	public DiceValue getDiceValue() {
		return diceValue;
	}

	
	public void roll() {
		this.diceValue = DiceValue.randomDiceValue();
	}
	
}