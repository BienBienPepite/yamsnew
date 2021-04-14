package com.avl.yamsnew.model;

import java.util.Random;

public enum DiceValue {

	ACE   (1),
	TWO   (2),
	THREE (3),
	FOUR  (4),
	FIVE  (5),
	SIX   (6);
	
	private int diceValue;
	
	private DiceValue(int diceValue) {
		this.diceValue = diceValue;
	}
	
	public int getDiceValue() {
		return this.diceValue;
	}
	
	
	
	public static DiceValue randomDiceValue() {
		
		/*
		 * return a random dice value
		 */
		
		int pick = new Random().nextInt(DiceValue.values().length);
		
		return DiceValue.values()[pick];
	}
}
