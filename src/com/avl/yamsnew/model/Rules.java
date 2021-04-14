package com.avl.yamsnew.model;

import java.util.stream.IntStream;

public class Rules {
	
	
	private static int scoreGridMaxSize = Figure.values().length;
	
	
	public static void checkRoll(Game gameTurn, int[] indexOfDicesToRoll, ScoreGrid scoreGrid) throws Exception {
		
		/*
		 * throw an exception if the roll can't be done, i.e :
		 * - if dices had been rolled already three times during the turn ;
		 * - if all the dices aren't all rolled at the first roll of the turn ;
		 * - if the game is finished
		 */
		
		if (gameTurn.getCurrentDiceRoll() == 3) {
			throw new Exception("You can't play more than three times");
		}
		else if (gameTurn.getCurrentDiceRoll() == 0 
				&& IntStream.of(indexOfDicesToRoll).distinct().toArray().length != 5) {
			throw new Exception("You must roll all the dices");
		}
		else if (isGameFinished(scoreGrid)) {
			throw new Exception("The game is finished, stop rolling dices");
		}
	}
	
	
	public static void checkFill(Game gameTurn, ScoreGrid scoreGrid, Figure figure) throws Exception {
		
		/*
		 * throw an exception if the score grid can't be filled i.e :
		 * - if the dices havn't been rolled before filling the grid ;
		 * - if the figure to be filled has already been filled
		 */
		
		
		if (gameTurn.getCurrentDiceRoll() == 0) {
			throw new Exception("Roll the dices before to fill the score grid");
		}
		else if (scoreGrid.getGrid().containsKey(figure)) {
			throw new Exception("Box already filled");
		}
	}
	
	
	
	
	
	public static boolean isGameFinished(ScoreGrid scoreGrid) {
		
		/*
		 * return true if the player has totally filled the score grid
		 */
		
		return scoreGrid.getGrid().size() == scoreGridMaxSize;
		
	}

}



