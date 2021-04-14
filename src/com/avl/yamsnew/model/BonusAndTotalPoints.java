package com.avl.yamsnew.model;

import java.util.HashMap;
import java.util.Map;

public class BonusAndTotalPoints {
	
	private Map<String, Integer> bonusAndTotalPointsGrid;
	
	private static int minimumToGetTheUpperSectionBonus = 60;
	private static int upperSectionBonusValue  = 30;
	
	BonusAndTotalPoints() {
		
		bonusAndTotalPointsGrid = new HashMap<String, Integer>();
		
		bonusAndTotalPointsGrid.put("UpperSectionBonus" , 0);
		bonusAndTotalPointsGrid.put("UpperSectionTotal" , 0);
		bonusAndTotalPointsGrid.put("MediumSectionTotal", 0);
		bonusAndTotalPointsGrid.put("LowerSectionTotal" , 0);
		
	}
	
	public Map<String, Integer> getBonusAndTotalPointsGrid(){
		
		return bonusAndTotalPointsGrid;
		
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
			
			upperSectionPoints += upperSectionBonusValue;
			
			this.bonusAndTotalPointsGrid.replace("UpperSectionBonus", upperSectionBonusValue);
			
		}
		
		this.bonusAndTotalPointsGrid.replace("UpperSectionTotal", upperSectionPoints);
	}
	
	
	private void updateMediumSectionTotalPoints(ScoreGrid scoreGrid) {
		
		/*
		 * update the min-max section :
		 * if the max and the min are filled in the grid, and if min < max :
		 * set the total points to min + max
		 */
		
		Map<Figure, Integer> grid = scoreGrid.getGrid();
		
		boolean minFilled = grid.containsKey(Figure.MIN);
		boolean maxFilled = grid.containsKey(Figure.MAX);
		
		int min = grid.get(Figure.MIN);
		int max = grid.get(Figure.MAX);
		
		if (minFilled
			&& maxFilled
			&& (min < max)) {
			
			this.bonusAndTotalPointsGrid.replace("MediumSectionTotal", min + max);
		}
		
	}
	
	
	private void updateLowerSectionTotalPoints(ScoreGrid scoreGrid) {
		
		/*
		 * set the total points of the lower section to the sum of 
		 * points in this section that are in the score grid.
		 */
		
		Map<Figure, Integer> grid = scoreGrid.getGrid();
		
		int lowerSum = grid.get(Figure.FULLHOUSE)
				+ grid.get(Figure.FOUROFAKIND)
				+ grid.get(Figure.STRAIGHT)
				+grid.get(Figure.YAHTZEE);
		
		this.bonusAndTotalPointsGrid.replace("LowerSectionTotal", lowerSum);
		
	}

}
