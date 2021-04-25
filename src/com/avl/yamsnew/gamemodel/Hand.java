package com.avl.yamsnew.gamemodel;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Hand {

	private Dice[] dices;
	
	public Hand() {
		dices = new Dice[5];
		
		for (int i = 0 ; i < 5 ; i++) {
			// initialize every dice
			dices[i] = new Dice();
		}
	}
	
	public void setDices(Dice[] dices) {
		this.dices = dices;
	}



	public Hand(DiceValue[] dicesValues) {
		
		/*
		 * this constructor is only used to test the program
		 */
		
		dices = new Dice[5];
		
		for (int i = 0 ; i < 5 ; i++) {
			// initialize every dice
			dices[i] = new Dice(dicesValues[i]);
		}
	}
	

	public Dice[] getDices() {
		return dices;
	}
	
	
	public void roll(int[] diceIndex) {
		
		/*
		 * roll every dice whose index is contained into the diceIndex array
		 */
		
		for (int i : diceIndex) {
			this.dices[i].roll();
		}

	}
	
	public int amountOf(DiceValue diceValue) {
		
		/*
		 * return the amount of times a certain dice value appears in the hand
		 */
		
		Map<DiceValue, Integer> dicesAsMap = this.mapDices();
		
		if (dicesAsMap.keySet().contains(diceValue)) {
			return dicesAsMap.get(diceValue);
		}
		
		return 0;
	}
	
	
	public int sum() {
		
		/*
		 * sum the values of the dices of the hand
		 */
		
		return Arrays.stream(this.dices)
				.mapToInt(dice -> dice.getDiceValue().getDiceValue())
				.sum();
	}
	
	
	public boolean isFullHouse(){
		
		/*
		 * return true if the hand realizes a full-house
		 * notice that, because the number values a dice can take (the amount of keys in dicesAsMap)
		 * is equal to 2 (and cannot be 1), we implicitly considered that a yahtzee isn't a fullhouse
		 */
		
		Map<DiceValue, Integer> dicesAsMap = this.mapDices();
		
		return (dicesAsMap.keySet().size() == 2 
				&& dicesAsMap.values().contains(2));
		
	}
	
	public boolean isFourOfAKind(){
		
		/*
		 * return true if the hand realizes a four-of-a-kind
		 * notice that a yahtzee can be considered as a four-of-a-kind
		 */
		
		Map<DiceValue, Integer> dicesAsMap = this.mapDices();
		
		return (dicesAsMap.values().contains(4)
					|| dicesAsMap.values().contains(5)); // that's why a yathzee can be considered as a four-of-a-kind
		
	}
	
	public boolean isStraight() {
		
		/*
		 * return true if the hand realizes a straight
		 */
		
		Map<DiceValue, Integer> dicesAsMap = this.mapDices();
		
		return (dicesAsMap.keySet().size() == 5
				&& (!dicesAsMap.keySet().contains(DiceValue.ACE)
						|| !dicesAsMap.keySet().contains(DiceValue.SIX))
				);
		
	}
	
	public boolean isYahtzee() {
		
		/*
		 * return true if the hand realizes a four-of-a-kind
		 */
		
		Map<DiceValue, Integer> dicesAsMap = this.mapDices();
		
		return dicesAsMap.values().contains(5);
		
	}
	
	
	private Map<DiceValue, Integer> mapDices(){
		
		/*
		 * return a map :
		 * 		key : dice value ;
		 * 		value : number of times the dice value appears in the hand, if it's > 0.
		 */
		
		return Arrays.stream(this.dices)
				.collect(
						Collectors.groupingBy(
								Dice::getDiceValue, Collectors.summingInt(x -> 1)));
		
	}
	
}
