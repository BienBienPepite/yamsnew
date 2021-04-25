package com.avl.yamsnew.gamemodel.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.avl.yamsnew.gamemodel.BonusAndTotalPoints;
import com.avl.yamsnew.gamemodel.DiceValue;
import com.avl.yamsnew.gamemodel.Figure;
import com.avl.yamsnew.gamemodel.Hand;
import com.avl.yamsnew.gamemodel.ScoreGrid;

public class BonusAnTotalPointsTest {
	
	private static String upperSectionTotalString  = BonusAndTotalPoints.getUppersectiontotalstring();
	private static String upperSectionBonusString  = BonusAndTotalPoints.getUppersectionbonusstring();
	private static String mediumSectionTotalString = BonusAndTotalPoints.getMediumsectiontotalstring();
	private static String lowerSectionTotalString  = BonusAndTotalPoints.getLowersectiontotalstring();
	
	@Test
	public void updateUpperSectionTotal() {
		
		BonusAndTotalPoints bonAndTot = new BonusAndTotalPoints();
		Map<String, Integer> bonAndTotGrid = bonAndTot.getBonusAndTotalPointsGrid();
		
		ScoreGrid scoreGrid = new ScoreGrid();
		
		DiceValue[] dices1 = {DiceValue.SIX, DiceValue.FIVE, DiceValue.SIX, DiceValue.FIVE, DiceValue.FIVE};
		DiceValue[] dices2 = {DiceValue.ACE, DiceValue.FIVE, DiceValue.ACE, DiceValue.FIVE, DiceValue.FIVE};
		
		Hand hand1 = new Hand(dices1);
		Hand hand2 = new Hand(dices2);
		
		scoreGrid.fill(Figure.FIVE, hand1);
		scoreGrid.fill(Figure.ACE, hand2);
		
		bonAndTot.updateBonusAndTotalPointsGrid(scoreGrid);
		
		int expectedUpperSectionTotal = 17;
		int actualUpperSectionTotal   = bonAndTotGrid.get(upperSectionTotalString);
		
		assertEquals(expectedUpperSectionTotal, actualUpperSectionTotal);
	}
	
	
	@Test
	public void updateUpperSectionBonus() {
		
		BonusAndTotalPoints bonAndTot = new BonusAndTotalPoints();
		Map<String, Integer> bonAndTotGrid = bonAndTot.getBonusAndTotalPointsGrid();
		
		int upperSectionBonusValue = BonusAndTotalPoints.getUpperSectionBonusValue();
		
		ScoreGrid scoreGrid = new ScoreGrid();
		
		DiceValue[] dices1 = {DiceValue.FIVE, DiceValue.FIVE, DiceValue.SIX, DiceValue.FIVE, DiceValue.FIVE};
		DiceValue[] dices2 = {DiceValue.SIX, DiceValue.SIX, DiceValue.SIX, DiceValue.SIX, DiceValue.FIVE};
		DiceValue[] dices3 = {DiceValue.FOUR, DiceValue.FOUR, DiceValue.FOUR, DiceValue.SIX, DiceValue.FOUR};
		DiceValue[] dices4 = {DiceValue.THREE, DiceValue.THREE, DiceValue.THREE, DiceValue.THREE, DiceValue.FOUR};
		
		Hand hand1 = new Hand(dices1);
		Hand hand2 = new Hand(dices2);
		Hand hand3 = new Hand(dices3);
		Hand hand4 = new Hand(dices4);
		
		scoreGrid.fill(Figure.FIVE, hand1);
		scoreGrid.fill(Figure.SIX, hand2);
		
		bonAndTot.updateBonusAndTotalPointsGrid(scoreGrid);
		
		int expectedUpperSectionBonus = 0;
		int actualUpperSectionBonus   = bonAndTotGrid.get(upperSectionBonusString);
		
		assertEquals(expectedUpperSectionBonus, actualUpperSectionBonus);
		
		
		scoreGrid.fill(Figure.FOUR, hand3);
		scoreGrid.fill(Figure.THREE, hand4);
		
		bonAndTot.updateBonusAndTotalPointsGrid(scoreGrid);
		
		expectedUpperSectionBonus = upperSectionBonusValue;
		actualUpperSectionBonus   = bonAndTotGrid.get(upperSectionBonusString);
		
		assertEquals(expectedUpperSectionBonus, actualUpperSectionBonus);
	}
	
	
	@Test
	public void updateMediumSectionTotal() {
		
		BonusAndTotalPoints bonAndTot = new BonusAndTotalPoints();
		Map<String, Integer> bonAndTotGrid = bonAndTot.getBonusAndTotalPointsGrid();
		
		ScoreGrid scoreGrid = new ScoreGrid();
		
		DiceValue[] dices1 = {DiceValue.FIVE, DiceValue.FIVE, DiceValue.SIX, DiceValue.FIVE, DiceValue.FIVE};
		DiceValue[] dices2 = {DiceValue.FIVE, DiceValue.FIVE, DiceValue.SIX, DiceValue.FIVE, DiceValue.SIX};
		
		Hand hand1 = new Hand(dices1);
		Hand hand2 = new Hand(dices2);
		
		scoreGrid.fill(Figure.MIN, hand1);
		
		bonAndTot.updateBonusAndTotalPointsGrid(scoreGrid);
		
		int expectedMediumSectionTotal = 0;
		int actualMediumSectionTotal   = bonAndTotGrid.get(mediumSectionTotalString);
		
		assertEquals(expectedMediumSectionTotal, actualMediumSectionTotal);
		
		
		scoreGrid.fill(Figure.MAX, hand1);
		
		bonAndTot.updateBonusAndTotalPointsGrid(scoreGrid);
		
		expectedMediumSectionTotal = 0;
		actualMediumSectionTotal   = bonAndTotGrid.get(mediumSectionTotalString);
		
		assertEquals(expectedMediumSectionTotal, actualMediumSectionTotal);
		
		scoreGrid.clearGrid();
		scoreGrid.fill(Figure.MIN, hand1);
		scoreGrid.fill(Figure.MAX, hand2);
		
		bonAndTot.updateBonusAndTotalPointsGrid(scoreGrid);
		
		expectedMediumSectionTotal = 53;
		actualMediumSectionTotal   = bonAndTotGrid.get(mediumSectionTotalString);
		
		assertEquals(expectedMediumSectionTotal, actualMediumSectionTotal);
	}
	
	
	@Test
	public void updateLowerSectionTotal() {
		
		BonusAndTotalPoints bonAndTot = new BonusAndTotalPoints();
		Map<String, Integer> bonAndTotGrid = bonAndTot.getBonusAndTotalPointsGrid();
		
		int fourOfAKindPoints = ScoreGrid.getFourOfAKindPoints();
		int fullHousePoints    = ScoreGrid.getFullHousePoints();
		
		ScoreGrid scoreGrid = new ScoreGrid();
		
		DiceValue[] dices1 = {DiceValue.FIVE, DiceValue.FIVE, DiceValue.SIX, DiceValue.FIVE, DiceValue.FIVE};
		DiceValue[] dices2 = {DiceValue.FIVE, DiceValue.FIVE, DiceValue.SIX, DiceValue.FIVE, DiceValue.SIX};
		
		Hand hand1 = new Hand(dices1);
		Hand hand2 = new Hand(dices2);
		
		scoreGrid.fill(Figure.FOUROFAKIND, hand1);
		
		bonAndTot.updateBonusAndTotalPointsGrid(scoreGrid);
		
		int expectedLowerSectionTotal = fourOfAKindPoints;
		int actualLowerSectionTotal   = bonAndTotGrid.get(lowerSectionTotalString);
		
		assertEquals(expectedLowerSectionTotal, actualLowerSectionTotal);
		
		
		scoreGrid.fill(Figure.STRAIGHT, hand1);
		
		bonAndTot.updateBonusAndTotalPointsGrid(scoreGrid);
		
		expectedLowerSectionTotal = fourOfAKindPoints;
		actualLowerSectionTotal   = bonAndTotGrid.get(lowerSectionTotalString);
		
		assertEquals(expectedLowerSectionTotal, actualLowerSectionTotal);
		
		
		scoreGrid.fill(Figure.FULLHOUSE, hand2);
		
		bonAndTot.updateBonusAndTotalPointsGrid(scoreGrid);
		
		expectedLowerSectionTotal = fourOfAKindPoints + fullHousePoints;
		actualLowerSectionTotal   = bonAndTotGrid.get(lowerSectionTotalString);
		
		assertEquals(expectedLowerSectionTotal, actualLowerSectionTotal);
	}

}
