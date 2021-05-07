package com.avl.yamsnew.beans;

import com.avl.yamsnew.gamemodel.Game;
import com.avl.yamsnew.util.JsonUtil;

public class GameBean {
	
	
	private Long id;
	private String username = "";
	private String gamestate;
	
	
	public GameBean() {
		
		Game game = new Game();
		this.gamestate = JsonUtil.gameToJson(game);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getGamestate() {
		return gamestate;
	}


	public void setGamestate(String game) {
		this.gamestate = game;
	}
	
}
