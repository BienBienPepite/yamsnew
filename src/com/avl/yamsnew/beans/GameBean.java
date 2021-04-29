package com.avl.yamsnew.beans;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameBean {
	
	/*
	 * class which is an image of the game and which can be used in a jsp document :
	 *  only primitive types and getters and setters
	 */
	
	private int currentDiceRoll;
	private String[] currentHand;
	private Map<String, Integer> scoreGrid;
	private Map<String, Integer> bonusAndTotalGrid;
	private boolean isFinished;
	
	
	public GameBean() {
		
		currentDiceRoll = 0;
		
		currentHand =  new String[]{"ACE", "ACE", "ACE", "ACE", "ACE"};
		
		scoreGrid = new HashMap<String, Integer>();
		
		bonusAndTotalGrid = Stream.of(
				  new AbstractMap.SimpleEntry<>("uppersectiontotal", 0), 
				  new AbstractMap.SimpleEntry<>("uppersectionbonus", 0),
				  new AbstractMap.SimpleEntry<>("mediumsectiontotal", 0),
				  new AbstractMap.SimpleEntry<>("lowersectiontotal", 0)
				  )
				  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		isFinished = false;
		
	}
	
	
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
	public Map<String, Integer> getScoreGrid() {
		return scoreGrid;
	}
	public void setScoreGrid(Map<String, Integer> grid) {
		this.scoreGrid = grid;
	}
	public Map<String, Integer> getBonusAndTotalGrid() {
		return bonusAndTotalGrid;
	}
	public void setBonusAndTotalGrid(Map<String, Integer> grid) {
		this.bonusAndTotalGrid = grid;
	}
	public boolean isFinished() {
		return isFinished;
	}
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	

}
