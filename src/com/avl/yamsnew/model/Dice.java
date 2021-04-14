package com.avl.yamsnew.model;

public class Dice {
	
	private DiceValue diceValue;
	
	public Dice() {
		diceValue = DiceValue.ACE;
	}

	public DiceValue getDiceValue() {
		return diceValue;
	}

	
	public void roll() {
		this.diceValue = DiceValue.randomDiceValue();
	}
	
}