package com.avl.yamsnew.gamemodel;


public class Game {

	int currentDiceRoll;
	Hand currentHand;
	ScoreGrid scoreGrid;
	BonusAndTotalPoints bonusAndTot;
	
	public Game(){
		currentDiceRoll = 0;
		currentHand = new Hand();
		scoreGrid = new ScoreGrid();
		bonusAndTot = new BonusAndTotalPoints();
	}
	
	public int getCurrentDiceRoll() {
		return this.currentDiceRoll;
	}
	
	public Hand getCurrentHand() {
		return this.currentHand;
	}
	
	public ScoreGrid getScoreGrid() {
		return this.scoreGrid;
	}
	
	public BonusAndTotalPoints getBonusAndTot() {
		return this.bonusAndTot;
	}
	
	public void setCurrentDiceRoll(int currentDiceRoll) {
		this.currentDiceRoll = currentDiceRoll;
	}

	public void setCurrentHand(Hand currentHand) {
		this.currentHand = currentHand;
	}

	public void setScoreGrid(ScoreGrid scoreGrid) {
		this.scoreGrid = scoreGrid;
	}

	public void setBonusAndTot(BonusAndTotalPoints bonusAndTot) {
		this.bonusAndTot = bonusAndTot;
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
	
}
