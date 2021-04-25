package com.avl.yamsnew.gamemodel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.avl.yamsnew.beans.GameBean;

public class GameLogicUtil {
	
	public static Game getGameFromGameBean(GameBean gameBean) {
		
		/*
		 * create a Game, based on its image, the GameBean
		 */
		
		Game game = new Game();
		
		
		// the gameBean contains the current dice roll
		
		game.setCurrentDiceRoll(gameBean.getCurrentDiceRoll());
		
		
		/* the gameBean contains a map representing the current hand :
		 * the keys are the dices values as strings
		 * the entries are the value of the dice corresponding */
		
		Hand hand = new Hand();
		
		hand.setDices(
				Arrays.stream(gameBean.getCurrentHand())
				.map(diceStr -> new Dice(DiceValue.valueOf(diceStr)))
				.toArray(Dice[]::new)
				);
		
		game.setCurrentHand(hand);
		
		
		/* the gameBean contains a map, where keys are string of the figures
		 and entries are the points made during the game */ 
		
		Map<Figure, Integer> scoreGridMap = new HashMap<Figure, Integer>();
		
		for (String str : gameBean.getScoreGrid().keySet()) {
			
			scoreGridMap.put(Figure.valueOf(str.toUpperCase()), gameBean.getScoreGrid().get(str));
			
		}
		
		ScoreGrid scoreGrid = new ScoreGrid();
		
		scoreGrid.setGrid(scoreGridMap);
		
		game.setScoreGrid(scoreGrid);
		
		
		/* the gameBean contains a map, where keys are string of the bonus and totals
		 and entries are their related points */ 
		
		Map<String, Integer> bonusAndTotalGrid = new HashMap<String, Integer>();
		
		for (String string : gameBean.getBonusAndTotalGrid().keySet()) {
			
			bonusAndTotalGrid.replace(string, gameBean.getBonusAndTotalGrid().get(string));
			
		}
		
		BonusAndTotalPoints bonusAndTot = new BonusAndTotalPoints();
		
		bonusAndTot.setBonusAndTotalPoints(bonusAndTotalGrid);
		
		game.setBonusAndTot(bonusAndTot);
		

		return game;
		
	}
	
	public static GameBean getGameBeanFromGame(Game game) {
		
		/*
		 * create a GameBean, image of the Game (to be used by the view)
		 */
		
		GameBean gameBean = new GameBean();
		
		
		// the gameBean contains the current dice roll
		
		gameBean.setCurrentDiceRoll(game.getCurrentDiceRoll());
		
		
		/* the gameBean contains a map representing the current hand :
		 * the keys are the dices values as strings
		 * the entries are the value of the dice corresponding */
		
		String[] handString = (Arrays.stream(game.getCurrentHand().getDices())
									.map(dice -> dice.getDiceValue().name()))
									.toArray(String[]::new);
		
		gameBean.setCurrentHand(handString);
		
		
		/* the gameBean contains a map, where keys are string of the figures
		 and entries are the points made during the game */ 
		
		Map<String, Integer> beanScoreGrid = gameBean.getScoreGrid();
		
		for (Figure figure : game.getScoreGrid().getGrid().keySet()) {
			
			beanScoreGrid.put(figure.name().toLowerCase() , game.getScoreGrid().getGrid().get(figure));
			
		}
		
		gameBean.setScoreGrid(beanScoreGrid);
		
		
		/* the gameBean contains a map, where keys are string of the bonus and totals
		 and entries are their related points */ 
		
		Map<String, Integer> beanBonusAndTotalGrid = gameBean.getBonusAndTotalGrid();
		
		for (String string : game.getBonusAndTot().getBonusAndTotalPointsGrid().keySet()) {
			
			beanBonusAndTotalGrid.replace(string.toLowerCase(), game.getBonusAndTot().getBonusAndTotalPointsGrid().get(string));
			
		}
		
		gameBean.setBonusAndTotalGrid(beanBonusAndTotalGrid);
		
		
		// the gameBean contains a boolean true if the game is finished, false otherwise
		
		boolean isFinished = Rules.isGameFinished(game.getScoreGrid());
		
		gameBean.setFinished(isFinished);
		
		
		return gameBean;
		
	}

}
