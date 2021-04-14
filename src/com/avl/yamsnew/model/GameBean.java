package com.avl.yamsnew.model;

import java.util.HashMap;
import java.util.Map;

public class GameBean {
	
	private int currentDiceRoll = 0;
	private String[] currentHand = new String[5];
	private Map<String, Integer> grid = new HashMap<String, Integer>();
	private boolean isFinished = false;
	
	
	public int getCurrentDiceRoll() {
		return currentDiceRoll;
	}
	public void setCurrentDiceRoll(int currentDiceRoll) {
		this.currentDiceRoll = currentDiceRoll;
	}
	public String[] getCurrentHand() {
		return currentHand;
	}
	public void setCurrentHand(String[] currentHand) {
		this.currentHand = currentHand;
	}
	public Map<String, Integer> getGrid() {
		return grid;
	}
	public void setGrid(Map<String, Integer> grid) {
		this.grid = grid;
	}
	public boolean isFinished() {
		return isFinished;
	}
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	

}
