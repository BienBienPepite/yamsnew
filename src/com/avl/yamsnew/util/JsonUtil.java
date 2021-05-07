package com.avl.yamsnew.util;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.avl.yamsnew.gamemodel.Game;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil {
	
	public static Game jsonToGame(String jsonGame) {
		
		Gson gson = new Gson();
		
		Game game = gson.fromJson(jsonGame, Game.class);
		
		return game;
	}
	
	
	public static String gameToJson(Game game) {
		
		Gson gson = new Gson();
		
		String jsonGame = gson.toJson(game, Game.class);
		
		return jsonGame;
	}
	
	public static String mergeJson(String jsonStr1, String jsonStr2) {
		
		String leftStr  = jsonStr1.substring(0, jsonStr1.length() - 1);
		String rightStr = jsonStr1.substring(1, jsonStr1.length());
		
		return leftStr + "," + rightStr;
		
	}
	
	public static JsonObject parseRequest(HttpServletRequest request) {
		
		StringBuffer strBuffer = new StringBuffer();
		String line = null;
		
		try {
			
			BufferedReader reader = request.getReader();
			
			while ((line = reader.readLine()) != null) {
		      strBuffer.append(line);
			}
			
		} catch (Exception e) { /*report an error*/ }
		
		String str = strBuffer.toString();
		if (!str.isEmpty()) {
			JsonObject jsonRequest = new JsonParser().parse(str).getAsJsonObject();
			return jsonRequest;
		}
		else {
			return null;
		}
		
	}
	
	public static String getRollValue(JsonObject jsonRequest) {
		
		return jsonRequest.get("roll").getAsString();
		
	}
	
	public static String[] getDicesValue(JsonObject jsonRequest) {
		
		JsonElement dices = jsonRequest.get("dices");
		JsonArray dicesArrJson = dices.getAsJsonArray();
		
		List<String> dicesList = new ArrayList<String>();
		
		for (JsonElement obj : dicesArrJson) {
			dicesList.add(obj.getAsString());
		}
		
		String[] dicesArray = dicesList.stream()
								.map(str -> str.trim())
								.filter(str -> str.trim().length() != 0)
								.toArray(String[]::new);
		
		return dicesArray;
	}

}
