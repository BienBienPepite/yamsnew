package com.avl.yamsnew.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.avl.yamsnew.beans.UserBean;
import com.avl.yamsnew.dao.DAOException;
import com.avl.yamsnew.dao.UserDao;

public class LoginForm {
	
	public static final String FIELD_USERNAME  = "username";
	public static final String FIELD_PASSWORD  = "password";
	
	private static final String ENCRYPTION_ALGORITHM = "SHA-256";
	
	private String result="";
	private Map<String, String> errors = new HashMap<String, String>();
	
	private UserDao userDao;
	
	
	public LoginForm(UserDao userDao) {
		this.userDao = userDao;
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
	
	
	public UserBean loginUser(HttpServletRequest request) {
		
		String username  = getFieldValue(request, FIELD_USERNAME);
		String pass      = getFieldValue(request, FIELD_PASSWORD);
		
		UserBean user = userDao.read(username);
		
		
		try {
			manageUsername(username, user);
			managePassword(pass, user);
			
			try {
				checkPassword(pass, user);
			} catch (Exception e) {
				putError(FIELD_PASSWORD, e.getMessage());
			}
			
			
			if (errors.isEmpty())
			{
				result = "Connection succeeded";
			}
			else
			{
				result = "Connection failed";
			}
		} catch ( DAOException e ) {
	        result = "Connection failed : an unknown error occurred.";
	        e.printStackTrace();
	    }
		
		return user;
	}
	
	
	private void manageUsername(String username, UserBean user) {
		try {
			checkUsername(username, user);
		} catch (Exception e) {
			putError(FIELD_USERNAME, e.getMessage());
		}
	}
	
	
	private void managePassword(String pass, UserBean user) {
		try {
			checkPassword(pass, user);
		} catch (Exception e) {
			putError(FIELD_PASSWORD, e.getMessage());
		}
	}
	
	
	private void checkUsername(String username, UserBean user) throws Exception {
		
		if (username != null && username.trim().length() != 0) {
	        if (user == null) {
	        	throw new Exception("*unknown username");
	        }
	    }
		else {
	        throw new Exception( "*enter a username" );
	    }
	}
	
	
	private void checkPassword(String pass, UserBean user) throws Exception {
		
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
	    passwordEncryptor.setAlgorithm( ENCRYPTION_ALGORITHM );
	    passwordEncryptor.setPlainDigest( false );
		
		if (pass != null && pass.trim().length() != 0) {
	        if (!passwordEncryptor.checkPassword(pass, user.getPassword())) {
	            throw new Exception("*invalid password");
	        } 
	    } 
		else {
	        throw new Exception("*enter a password");
	    }
	}
	
	private String getFieldValue(HttpServletRequest request, String field) {
		
		String value = request.getParameter(field);
		
		if (value == null || value.trim().length() ==0) {
			return null;
		}
		else {
			return value.trim();
		}
	}
	
}

