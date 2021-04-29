package com.avl.yamsnew.gamemodel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ScoreGrid {
	
	private Map<Figure, Integer> grid;
	
	private static int fullHousePoints   = 20;
	private static int fourOfAKindPoints = 30;
	private static int straightPoints    = 40;
	private static int yahtzeePoints     = 50;
	
	public ScoreGrid(){
		grid = new HashMap<Figure, Integer>();
	}
	
	public Map<Figure, Integer> getGrid() {
		return grid;
	}
	
	public void setGrid(Map<Figure, Integer> grid) {
		this.grid = grid;
	}
	
	
	public static int getFourOfAKindPoints() {
		/*
		 * this getter is only used to test the program
		 */
		
		return fourOfAKindPoints;
	}
	
	public static int getFullHousePoints() {
		/*
		 * this getter is only used to test the program
		 */
		
		return fullHousePoints;
	}
	
	public static int getYahtzeePoints() {
		/*
		 * this getter is only used to test the program
		 */
		
		return yahtzeePoints;
	}

	public void fill(Figure figure, Hand hand) {
		
		/*
		 * add to the grid, the name of the figures that has been chosen by the player,
		 * and the amount of points it represents, based on the hand he had.
		 */
		
		
		Figure[] oneToSix = new Figure[] {
				
				Figure.ACE,
				Figure.TWO,
				Figure.THREE,
				Figure.FOUR,
				Figure.FIVE,
				Figure.SIX
		};
		
		if (Arrays.asList(oneToSix).contains(figure)) {
			
			//convert the figure into dice value, using their name.
			
			DiceValue figureToDiceValue =
					DiceValue.valueOf(figure.name());
			
			grid.put(figure, hand.amountOf(figureToDiceValue) * figureToDiceValue.getDiceValue());
		}
		
		else if (figure.equals(Figure.MIN)
				|| figure.equals(Figure.MAX)) {
			
			grid.put(figure, hand.sum());
			
		}
		
		else {
			
			int points = 0;
			
			if (figure.equals(Figure.FULLHOUSE)) {
				points = hand.isFullHouse() ? fullHousePoints : 0; 
			}
			else if (figure.equals(Figure.FOUROFAKIND)) {
				points = hand.isFourOfAKind() ? fourOfAKindPoints : 0; 
			}
			else if (figure.equals(Figure.STRAIGHT)) {
				points = hand.isStraight() ? straightPoints : 0; 
			}
			else {
				points = hand.isYahtzee() ? yahtzeePoints : 0; 
			}
			
			grid.put(figure, points);
		}
		
	}
	
	
	public void clearGrid() {
		this.grid.clear();
	}
}
