package com.avl.yamsnew.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.avl.yamsnew.beans.GameBean;
import com.avl.yamsnew.beans.UserBean;
import com.avl.yamsnew.dao.DAOException;
import com.avl.yamsnew.dao.GameDao;
import com.avl.yamsnew.gamecontroller.GameController;
import com.avl.yamsnew.gamemodel.Game;
import com.avl.yamsnew.util.JsonUtil;
import com.google.gson.JsonObject;

public class GameForm {
	
	/*
	 * receive the request and translate it in a way that is understandable by the game
	 * controller (GameController.java)
	 */

	public static final String FIELD_DICES  = "dices";
	public static final String FIELD_BOX    = "box";
	public static final String FIELD_ROLL   = "roll";
	public static final String FIELD_FILL   = "fill";
	public static final String ATT_USER     = "user";
	
	private Map<String, String> errors = new HashMap<String, String>();
	private String result="";
	
	private GameDao gameDao;
	
	public GameForm( GameDao gameDao ) {
	    this.gameDao = gameDao;
	}
	
	public String getResult() {
		return this.result;
	}
	
	public Map<String, String> getErrors(){
		return this.errors;
	}
	
	private void putError(String field, String errorMessage) {
		
		/*
		 * put an error in the map :
		 * - the key is the field : which part of the request is concerned by the error
		 * (basically, does this error appears because the user filled wrongly the score grid
		 * or is it because he didn't roll the dices according to the rules)
		 * - the error message is the true cause of the error, what will be shown to the user
		 */
		
		this.errors.put(field, errorMessage);
	}
	
	
	public GameBean getNewGameBean(HttpServletRequest request) {
		
		/*
		 * pass the request to the game controller after having translating it :
		 */
		
		String username = ((UserBean) request.getSession().getAttribute(ATT_USER)).getUsername();
		
		String    roll = null;
		String[] dices = null;
		
		
		JsonObject jsonRequest = JsonUtil.parseRequest(request);
		
		if (jsonRequest != null) {
			roll  = JsonUtil.getRollValue(jsonRequest);
			dices = JsonUtil.getDicesValue(jsonRequest);
		}
		
		
		String boxToFill = getFieldValue(request, FIELD_BOX);
		String fill      = getFieldValue(request, FIELD_FILL);
		
		GameBean gameBean = gameDao.read(username);
		
		try {
			
			if (gameBean == null) {
				
				gameBean = new GameBean();
				gameBean.setUsername(username);
				
				gameDao.create(gameBean);
				
			}
			
			
			if (roll != null || fill != null) {
				
				String gameState = gameBean.getGamestate();
				Game game = JsonUtil.jsonToGame(gameState);
				GameController gameController = new GameController(game);
				
				if (roll != null) {
					rollGame(dices, gameController);
				}
				
				else {
					fillGame(boxToFill, gameController);
				}
				
				game = gameController.getGame();
				
				// update the game state in the database
				String newGameState = JsonUtil.gameToJson(game);
				gameBean.setGamestate(newGameState);
				gameDao.update(gameBean);
			}
			
		} catch(DAOException e) {
			
			result = "Connection failed : an unknown error occurred.";
	        e.printStackTrace();
	        
		}
		
		return gameBean;
		
	}
	
	
	private void rollGame(String[] dices, GameController gameController) {
		
		/*
		 * called if the request consists in rolling dices
		 * - the request is transformed in another which follows the pattern : 
		 * roll{1-5}[1-5] to be understood by the game controller
		 * - try to call the game controller and update the game :
		 * 		- if there is an error, put it in the errors map
		 * 		- otherwise, update the game
		 */
		
		String requestToGameController = "roll";
		
		if (dices != null && dices.length != 0) {
			for (String str : dices) {
				
				requestToGameController += (str == null) ? "" : str;
				
			}
		}
		else {
			requestToGameController = "roll12345";
		}
		
		try {
			
			gameController.updateGame(requestToGameController);
			
		} catch (Exception e) {
			putError(FIELD_ROLL, e.getMessage());
		}
	}
	
	
	private void fillGame(String boxToFill, GameController gameController) {
		
		/*
		 * called if the request consists in filling score grid
		 * - the request is transformed in another which follows the pattern : 
		 * "fill" + "a figure" to be understood by the game controller
		 * - try to call the game controller and update the game :
		 * 		- if there is an error, put it in the errors map
		 * 		- otherwise, update the game
		 */
		
		String requestToGameController = "fill" + boxToFill;
		
		try {
			
			gameController.updateGame(requestToGameController);
			
		}  catch (Exception e) {
			putError(FIELD_FILL, e.getMessage());
		}
		
	}
	
	
	public String getFieldValue(HttpServletRequest request, String field) {
		
		String value = request.getParameter(field);
		
		if (value == null || value.trim().length() == 0) {
			return null;
		}
		else {
			return value.trim();
		}
	}
	
}
