package com.avl.yamsnew.forms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.avl.yamsnew.beans.GameBean;
import com.avl.yamsnew.gamecontroller.GameController;

public class GameForm {

	public static final String FIELD_DICES = "dices";
	public static final String FIELD_BOX   = "box";
	public static final String FIELD_ROLL  = "roll";
	public static final String FIELD_FILL  = "fill";
	
	private GameBean gameBean = new GameBean();
	private Map<String, String> errors = new HashMap<String, String>();
	private String result="";
	
	
	public GameBean getGameBean() {
		return this.gameBean;
	}
	
	public String getResult() {
		return this.result;
	}
	
	public Map<String, String> getErrors(){
		return this.errors;
	}
	
	private void putError(String field, String errorMessage) {
		this.errors.put(field, errorMessage);
	}
	
	public void updateGameBean(HttpServletRequest request, GameBean gameBean) {
		
		String[] dices   = getFieldValues(request, FIELD_DICES);
		
		String boxToFill = getFieldValue(request, FIELD_BOX);
		
		String roll      = getFieldValue(request, FIELD_ROLL);
		
		String fill      = getFieldValue(request, FIELD_FILL);

		GameController gameController = new GameController(gameBean);
		
		if (roll != null) {
			
			rollGameBean(dices, gameController);
			
		}
		
		else if (fill != null) {
			
			fillGameBean(boxToFill, gameController);
			
		}
	}
	
	
	private void rollGameBean(String[] dices, GameController gameController) {
		
		String requestToGameController = "roll";
		
		if (dices != null) {
			for (String str : dices) {
				
				requestToGameController += (str == null) ? "" : str;
				
			}
		}
		else {
			requestToGameController = "roll12345";
		}
		
		try {
			
			gameController.updateGame(requestToGameController);
			this.gameBean = gameController.getGameBean();
			
		} catch (Exception e) {
			putError(FIELD_ROLL, e.getMessage());
		}
	}
	
	
	private void fillGameBean(String boxToFill, GameController gameController) {
		
		String requestToGameController = "fill" + boxToFill;
		
		try {
			
			gameController.updateGame(requestToGameController);
			this.gameBean = gameController.getGameBean();
			
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
	
	public String[] getFieldValues(HttpServletRequest request, String field) {
		String[] values = request.getParameterValues(field);
		
		if (values == null) {
			return null;
		}
		else {
			String[] fieldValues = Arrays.stream(values)
					.map(str -> str.trim())
					.filter(str -> str.trim().length() != 0)
					.toArray(String[]::new);
			
			return fieldValues;
		}
	}
	
}
