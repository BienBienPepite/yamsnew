package com.avl.yamsnew.gamemodel;

import java.util.HashMap;
import java.util.Map;

public class BonusAndTotalPoints {
	
	private Map<String, Integer> bonusAndTotalPointsGrid;
	
	private static int minimumToGetTheUpperSectionBonus = 60;
	private static int upperSectionBonusValue  = 30;
	
	private static final String upperSectionBonusString  = "uppersectionbonus";
	private static final String upperSectionTotalString  = "uppersectiontotal";
	private static final String mediumSectionTotalString = "mediumsectiontotal";
	private static final String lowerSectionTotalString  = "lowersectiontotal";
	
	
	public BonusAndTotalPoints() {
		
		bonusAndTotalPointsGrid = new HashMap<String, Integer>();
		
		bonusAndTotalPointsGrid.put(upperSectionBonusString , 0);
		bonusAndTotalPointsGrid.put(upperSectionTotalString  , 0);
		bonusAndTotalPointsGrid.put(mediumSectionTotalString, 0);
		bonusAndTotalPointsGrid.put(lowerSectionTotalString , 0);
		
	}
	
	public void setBonusAndTotalPoints(Map<String, Integer> bonusAndTotalPointsGrid) {
		this.bonusAndTotalPointsGrid = bonusAndTotalPointsGrid;
	}
	
	public static String getUppersectionbonusstring() {
		return upperSectionBonusString;
	}

	public static String getUppersectiontotalstring() {
		return upperSectionTotalString;
	}

	public static String getMediumsectiontotalstring() {
		return mediumSectionTotalString;
	}

	public static String getLowersectiontotalstring() {
		return lowerSectionTotalString;
	}



	public Map<String, Integer> getBonusAndTotalPointsGrid(){
		
		return bonusAndTotalPointsGrid;
		
	}
	
	public static int getMinimumToGetTheUpperSectionBonus() {
		
		return minimumToGetTheUpperSectionBonus;
		
	}
	
	public static int getUpperSectionBonusValue() {
		
		return upperSectionBonusValue;
		
	}
	
	
	public void updateBonusAndTotalPointsGrid(ScoreGrid scoreGrid) {
		
		updateUpperSectionBonusAndTotalPoints(scoreGrid);
		updateMediumSectionTotalPoints(scoreGrid);
		updateLowerSectionTotalPoints(scoreGrid);
		
	}
	
	
	private void updateUpperSectionBonusAndTotalPoints(ScoreGrid scoreGrid) {
		
		/*
		 * update the upper section bonus and total points (Ace to Six) :
		 * - sum all the points made in this section
		 * - check if it's greater than the minimum to get the upper section bonus value
		 * - update the table
		 */
		
		Map<Figure, Integer> grid = scoreGrid.getGrid();
		
		int upperSectionPoints = 0;
		
		for (DiceValue diceValue : DiceValue.values()) {
			
			// for every dice value (Ace to Six) :
			
			// - convert it to the corresponding figure
			
			Figure diceValueToFigure = Figure.valueOf(diceValue.name());
			
			// - check if the figure has already been filled in the grid
			
			boolean diceValueFilled = grid.containsKey(diceValueToFigure);
			
			// - add the points to the global score of the upper section
			
			upperSectionPoints += diceValueFilled ? grid.get(diceValueToFigure) : 0;
			
		}
		
		
		if (upperSectionPoints >= minimumToGetTheUpperSectionBonus) {
			
			this.bonusAndTotalPointsGrid.replace(upperSectionBonusString, upperSectionBonusValue);
			
		}
		
		this.bonusAndTotalPointsGrid.replace(upperSectionTotalString, upperSectionPoints);
	}
	
	
	private void updateMediumSectionTotalPoints(ScoreGrid scoreGrid) {
		
		/*
		 * update the min-max section :
		 * if the max and the min are filled in the grid, and if min < max :
		 * set the total points to min + max + upperSectionBonus
		 * otherwise set it to upperSectionBonus
		 */
		
		int bonus = this.bonusAndTotalPointsGrid.get(upperSectionBonusString);
		
		this.bonusAndTotalPointsGrid
			.replace(mediumSectionTotalString, 
					bonus
					);
		
		Map<Figure, Integer> grid = scoreGrid.getGrid();
		
		boolean minFilled = grid.containsKey(Figure.MIN);
		boolean maxFilled = grid.containsKey(Figure.MAX);
		
		if (minFilled
			&& maxFilled) {
			
			int min = grid.get(Figure.MIN);
			int max = grid.get(Figure.MAX);
			
			if (min < max) {
				
				this.bonusAndTotalPointsGrid.replace(mediumSectionTotalString, min + max + bonus);
			}
		}
			
		
	}
	
	
	private void updateLowerSectionTotalPoints(ScoreGrid scoreGrid) {
		
		/*
		 * set the total points of the lower section to the sum of 
		 * points in this section that are in the score grid.
		 */
		
		Map<Figure, Integer> grid = scoreGrid.getGrid();
		
		int lowerSum = 0;
		
		if (grid.containsKey(Figure.FULLHOUSE)) {
			lowerSum += grid.get(Figure.FULLHOUSE);
		}
		
		if (grid.containsKey(Figure.FOUROFAKIND)) {
			lowerSum += grid.get(Figure.FOUROFAKIND);
		}
		
		if (grid.containsKey(Figure.STRAIGHT)) {
			lowerSum += grid.get(Figure.STRAIGHT);
		}
		
		if (grid.containsKey(Figure.YAHTZEE)) {
			lowerSum += grid.get(Figure.YAHTZEE);
		}
		
		this.bonusAndTotalPointsGrid.replace(lowerSectionTotalString, lowerSum);
		
	}

}
