package com.avl.yamsnew.dao;

import com.avl.yamsnew.beans.UserBean;

public interface UserDao {
	
	void create (UserBean user) throws DAOException;
	
	UserBean read (String username) throws DAOException;
	
	void update (UserBean user) throws DAOException;
	
	void delete (UserBean user) throws DAOException;
}
