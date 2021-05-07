package com.avl.yamsnew.gamecontroller;

import com.avl.yamsnew.gamemodel.Figure;
import com.avl.yamsnew.gamemodel.Game;

public class GameController {
	
	private Game game;
	
	
	public GameController() {
		game = new Game();
	}
	
	public GameController(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}
	
	
	public void updateGame(String request) throws Exception {
		
		/* process the request from the view
		 * - the request follow one of those patterns : roll1, roll23, ..., or fillace, filltwo,...
		 * - catch an exception if there is one thrown (could be thrown by the model) and throw it
		 * - update the game and the gameBean
		 * */
		
		try {
			
			checkRequest(request); // could throw an exception (see below)
			
			if (request.matches("^roll[1-5]{1,5}+$")) {
				
				String str = request.substring(4);
				
				int[] indexOfDicesToRoll = new int[str.length()];
				
				for (int i = 0; i < str.length(); i++) {
					indexOfDicesToRoll[i] = Integer.parseInt(str.substring(i, i+1)) - 1;
				}
					
				game.rollDices(indexOfDicesToRoll); // could throw an exception
				
				
			}
			else {
				
				String str = request.substring(4).toUpperCase();
				
				Figure figure = Figure.valueOf(str);
				
				game.fillScoreGrid(figure);
			}
			
			
		} catch (Exception e) {
			
			throw e;
			
		}
		
	}
	
	
	private void checkRequest(String request) throws Exception{
		
		/*
		 * throw an exception if the request doesn't follow a correct pattern
		 */
		
		if (!request.matches("^roll[1-5]{1,5}+$")
				&& !request.matches("^fill"
						+ "(ace|two|three|four|five|six|min|max|fullhouse|fourofakind|straight|yahtzee)$")) {
			if (request.equals("fill")) {
				throw new Exception("You must check a box to fill");
			}
			else {
				throw new Exception("Make a valid request");
			}
		}
	}
	
	
	

}
