package com.avl.yamsnew.gamemodel.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.avl.yamsnew.gamemodel.DiceValue;
import com.avl.yamsnew.gamemodel.Hand;

public class HandTest {
	
	@Test
	public void amountOf() {
		
		DiceValue[] dices = {DiceValue.ACE, DiceValue.FIVE, DiceValue.ACE, DiceValue.SIX, DiceValue.FOUR};
		Hand hand = new Hand(dices);
		
		int expectedAmountOfAce   = 2;
		int expectedAmountOfThree = 0;
		int expectedAmountOfFour  = 1;
		
		int actualAmountOfAce   = hand.amountOf(DiceValue.ACE);
		int actualAmountOfThree = hand.amountOf(DiceValue.THREE);
		int actualAmountOfFour  = hand.amountOf(DiceValue.FOUR);
		
		assertEquals(expectedAmountOfAce,   actualAmountOfAce);
		assertEquals(expectedAmountOfThree, actualAmountOfThree);
		assertEquals(expectedAmountOfFour,  actualAmountOfFour);
	}
	
	
	@Test
	public void sum() {
		
		DiceValue[] dices = {DiceValue.ACE, DiceValue.FIVE, DiceValue.ACE, DiceValue.SIX, DiceValue.FOUR};
		Hand hand = new Hand(dices);
		
		int expectedSum = 17;
		
		int actualSum = hand.sum();
		
		assertEquals(expectedSum, actualSum);
	}
	
	
	@Test
	public void isFullHouse() {
		
		DiceValue[] basicDices = {DiceValue.ACE, DiceValue.FIVE, DiceValue.ACE, DiceValue.SIX, DiceValue.FOUR};
		Hand basicHand = new Hand(basicDices);
		
		DiceValue[] fullHouseDices = {DiceValue.ACE, DiceValue.FOUR, DiceValue.ACE, DiceValue.ACE, DiceValue.FOUR};
		Hand fullHouseHand = new Hand(fullHouseDices);
		
		assertFalse(basicHand.isFullHouse());
		assertTrue(fullHouseHand.isFullHouse());
	}
	
	@Test
	public void isFourOfAKind() {
		
		DiceValue[] basicDices = {DiceValue.ACE, DiceValue.FIVE, DiceValue.ACE, DiceValue.SIX, DiceValue.FOUR};
		Hand basicHand = new Hand(basicDices);
		
		DiceValue[] fourOfAKindDices = {DiceValue.ACE, DiceValue.FOUR, DiceValue.ACE, DiceValue.ACE, DiceValue.ACE};
		Hand fourOfAKindHand = new Hand(fourOfAKindDices);
		
		assertFalse(basicHand.isFourOfAKind());
		assertTrue(fourOfAKindHand.isFourOfAKind());
	}
	
	@Test
	public void isStraight() {
		
		DiceValue[] basicDices = {DiceValue.ACE, DiceValue.FIVE, DiceValue.ACE, DiceValue.SIX, DiceValue.FOUR};
		Hand basicHand = new Hand(basicDices);
		
		DiceValue[] straightDices = {DiceValue.ACE, DiceValue.FOUR, DiceValue.TWO, DiceValue.FIVE, DiceValue.THREE};
		Hand straightHand = new Hand(straightDices);
		
		assertFalse(basicHand.isStraight());
		assertTrue(straightHand.isStraight());
	}
	
	@Test
	public void isYahtzee() {
		
		DiceValue[] basicDices = {DiceValue.ACE, DiceValue.FIVE, DiceValue.ACE, DiceValue.SIX, DiceValue.FOUR};
		Hand basicHand = new Hand(basicDices);
		
		DiceValue[] yahtzeeDices = {DiceValue.ACE, DiceValue.ACE, DiceValue.ACE, DiceValue.ACE, DiceValue.ACE};
		Hand yahtzeeHand = new Hand(yahtzeeDices);
		
		assertFalse(basicHand.isYahtzee());
		assertTrue(yahtzeeHand.isYahtzee());
	}
}
