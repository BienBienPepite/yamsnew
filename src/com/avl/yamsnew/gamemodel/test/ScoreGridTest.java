package com.avl.yamsnew.gamemodel.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.avl.yamsnew.gamemodel.DiceValue;
import com.avl.yamsnew.gamemodel.Figure;
import com.avl.yamsnew.gamemodel.Hand;
import com.avl.yamsnew.gamemodel.ScoreGrid;

public class ScoreGridTest {
	
	@Test
	public void fillAceToSix() {
		
		ScoreGrid scoreGrid = new ScoreGrid();
		
		DiceValue[] dices = {DiceValue.ACE, DiceValue.FIVE, DiceValue.ACE, DiceValue.SIX, DiceValue.FIVE};
		Hand hand = new Hand(dices);
		
		scoreGrid.fill(Figure.ACE, hand);
		scoreGrid.fill(Figure.TWO, hand);
		scoreGrid.fill(Figure.FIVE, hand);
		
		int expectedAceScore  = 2;
		int expectedTwoScore  = 0;
		int expectedFiveScore = 10;
		
		int actualAceScore  = scoreGrid.getGrid().get(Figure.ACE);
		int actualTwoScore  = scoreGrid.getGrid().get(Figure.TWO);
		int actualFiveScore = scoreGrid.getGrid().get(Figure.FIVE);
		
		assertEquals(expectedAceScore, actualAceScore);
		assertEquals(expectedTwoScore, actualTwoScore);
		assertEquals(expectedFiveScore, actualFiveScore);
	}
	
	
	@Test
	public void fillMinMax() {
		
		ScoreGrid scoreGrid = new ScoreGrid();
		
		DiceValue[] dices = {DiceValue.ACE, DiceValue.FIVE, DiceValue.ACE, DiceValue.SIX, DiceValue.FIVE};
		Hand hand = new Hand(dices);
		
		scoreGrid.fill(Figure.MIN, hand);
		scoreGrid.fill(Figure.MAX, hand);
		
		int expectedMin = 18;
		int expectedMax = 18;
		
		int actualMin = scoreGrid.getGrid().get(Figure.MIN);
		int actualMax = scoreGrid.getGrid().get(Figure.MAX);
		
		assertEquals(expectedMin, actualMin);
		assertEquals(expectedMax, actualMax);
	}
	
	
	@Test
	public void fillSpecialFigure() {
		
		ScoreGrid scoreGrid = new ScoreGrid();
		
		DiceValue[] basicDices = {DiceValue.ACE, DiceValue.FIVE, DiceValue.ACE, DiceValue.SIX, DiceValue.FIVE};
		DiceValue[] fullHouseDices = {DiceValue.ACE, DiceValue.FIVE, DiceValue.ACE, DiceValue.FIVE, DiceValue.FIVE};
		DiceValue[] yahtzeeDices = {DiceValue.FIVE, DiceValue.FIVE, DiceValue.FIVE, DiceValue.FIVE, DiceValue.FIVE};
		
		Hand basicHand     = new Hand(basicDices);
		Hand fullHouseHand = new Hand(fullHouseDices);
		Hand yahtzeeHand   = new Hand(yahtzeeDices);
		
		int expectedBasicHandFullHouseScore     = 0;
		int expectedFullHouseHandFullHouseScore = ScoreGrid.getFullHousePoints();
		int expectedYahtzeeHandYahtzeeScore = ScoreGrid.getYahtzeePoints();
		
		scoreGrid.fill(Figure.FULLHOUSE, basicHand);
		
		int actualBasicHandFullHouseScore = scoreGrid.getGrid().get(Figure.FULLHOUSE);
		
		assertEquals(expectedBasicHandFullHouseScore, actualBasicHandFullHouseScore);
		
		
		scoreGrid.clearGrid();
		
		scoreGrid.fill(Figure.FULLHOUSE, fullHouseHand);
		
		int actualFullHouseHandFullHouseScore = scoreGrid.getGrid().get(Figure.FULLHOUSE);
		
		assertEquals(expectedFullHouseHandFullHouseScore, actualFullHouseHandFullHouseScore);
		
		
		scoreGrid.clearGrid();
		
		scoreGrid.fill(Figure.YAHTZEE, yahtzeeHand);
		
		int actualYahtzeeHandYahtzeeScore = scoreGrid.getGrid().get(Figure.YAHTZEE);
		
		assertEquals(expectedYahtzeeHandYahtzeeScore, actualYahtzeeHandYahtzeeScore);
	}

}
