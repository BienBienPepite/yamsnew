package com.avl.yamsnew.dao;

import com.avl.yamsnew.beans.GameBean;

public interface GameDao {

	void create (GameBean game) throws DAOException;
	
	GameBean read (String username) throws DAOException;
	
	void update (GameBean game) throws DAOException;
	
	void delete (GameBean game) throws DAOException;
	
}
