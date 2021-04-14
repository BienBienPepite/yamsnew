package com.avl.yamsnew.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Game {

	int currentDiceRoll;
	Hand currentHand;
	ScoreGrid scoreGrid;
	BonusAndTotalPoints bonusAndTot;
	
	Game(){
		currentDiceRoll = 0;
		currentHand = new Hand();
		scoreGrid = new ScoreGrid();
		bonusAndTot = new BonusAndTotalPoints();
	}
	
	public int getCurrentDiceRoll() {
		return this.currentDiceRoll;
	}
	
	public void rollDices(int[] indexOfDicesToRoll) throws Exception{
		
		/*
		 * roll every dice whose index is in the input array, if it's allowed, 
		 * and update the current dice roll
		 */
		
		try {
			
			Rules.checkRoll(this, indexOfDicesToRoll, scoreGrid);
			
			this.currentHand.roll(indexOfDicesToRoll);
			
			currentDiceRoll += 1;
			
		} catch (Exception e) {
			
			throw e;
			
		}
	}
	
	
	public void fillScoreGrid(Figure figure) throws Exception {
		
		/*
		 * - check if filling the score grid, with the figure, is allowed (catch an exception if not)
		 * - fill the score grid and update the bonus and total points grid
		 * -  switch to next turn
		 */
		
		
		try {
			
			Rules.checkFill(this, scoreGrid, figure);
			
			scoreGrid.fill(figure, currentHand);
			
			bonusAndTot.updateBonusAndTotalPointsGrid(scoreGrid);
			
			nextTurn();
			
		}
		catch (Exception e){
			
			throw e;
			
		}
		
	}
	
	private void nextTurn() {
		
		/*
		 * switch to the next turn, i.e initialize some variables
		 */
		
		this.currentDiceRoll = 0;
		this.currentHand = new Hand();
	}
	
	
	public GameBean getGameBean() {
		
		/*
		 * create a GameBean, image of the Game (to be used by the view)
		 */
		
		GameBean gameBean = new GameBean();
		
		
		// the gameBean contains the current dice roll
		
		gameBean.setCurrentDiceRoll(currentDiceRoll);
		
		
		/* the gameBean contains a map representing the current hand :
		 * the keys are the dices values as strings
		 * the entries are the value of the dice corresponding */
		
		String[] handString = (String[]) Arrays.stream(currentHand.getDices())
									.map(dice -> dice.getDiceValue().name())
									.toArray();
		
		gameBean.setCurrentHand(handString);
		
		
		/* the gameBean contains a map, where keys are string of the figures, bonuses and totals,
		 and entries are the points made during the game */ 
		
		Map<String, Integer> grid = new HashMap<String, Integer>();
		
		for (Figure figure : this.scoreGrid.getGrid().keySet()) {
			
			grid.put(figure.name().toLowerCase() , scoreGrid.getGrid().get(figure));
			
		}
		
		for (String string : this.bonusAndTot.getBonusAndTotalPointsGrid().keySet()) {
			
			grid.put(string.toLowerCase(), this.bonusAndTot.getBonusAndTotalPointsGrid().get(string));
			
		}
		
		gameBean.setGrid(grid);
		
		
		// the gameBean contains a boolean true if the game is finished, false otherwise
		
		boolean isFinished = Rules.isGameFinished(this.scoreGrid);
		
		gameBean.setFinished(isFinished);
		
		
		
		return gameBean;
		
	}
	
	
}
