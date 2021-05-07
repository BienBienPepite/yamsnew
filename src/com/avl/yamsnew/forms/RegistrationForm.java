package com.avl.yamsnew.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.avl.yamsnew.beans.UserBean;
import com.avl.yamsnew.dao.DAOException;
import com.avl.yamsnew.dao.UserDao;

public final class RegistrationForm {
	
	public static final String FIELD_USERNAME      = "username";
	public static final String FIELD_PASSWORD      = "password";
	public static final String FIELD_PASSWORDCHECK = "passwordCheck";
	
	private static final String ENCRYPTION_ALGORITHM = "SHA-256";
	
	private String result="";
	private Map<String, String> errors = new HashMap<String, String>();
	
	private UserDao userDao;

	
	public RegistrationForm( UserDao userDao ) {
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
	
	
	public UserBean registerUser(HttpServletRequest request) {
		
		String username  = getFieldValue(request, FIELD_USERNAME);
		String pass      = getFieldValue(request, FIELD_PASSWORD);
		String passCheck = getFieldValue(request, FIELD_PASSWORDCHECK);
		
		UserBean user = new UserBean();
			
		try {		
			manageUsername(username, user);
			managePassword(pass, passCheck, user);

			if (errors.isEmpty())
			{
				userDao.create(user);
				result = "Registration successed";
			}
			else
			{
				result = "Registration failed";
			}
		
		} catch ( DAOException e ) {
	        result = "Registration failed : an unknown error occurred.";
	        e.printStackTrace();
	    }
		
		return user;
	}
	
	private void manageUsername(String username, UserBean user) {
		try {
			checkUsername(username);
		} catch (Exception e) {
			putError(FIELD_USERNAME, e.getMessage());
		}
		user.setUsername(username);
	}
	
	private void managePassword(String pass, String passCheck, UserBean user) {
		try {
			checkPassword(pass, passCheck);
		} catch (Exception e) {
			putError(FIELD_PASSWORD, e.getMessage());
			putError(FIELD_PASSWORDCHECK, null);
		}
		
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
	    passwordEncryptor.setAlgorithm( ENCRYPTION_ALGORITHM );
	    passwordEncryptor.setPlainDigest( false );
	    String encryptedPassword = passwordEncryptor.encryptPassword( pass );
		
		user.setPassword(encryptedPassword);
	}
	
	
	private void checkUsername(String username) throws Exception {
		
		if (username != null && username.trim().length() != 0) {
	        if (username.trim().length() < 3 || username.trim().length() > 30) {
	        	throw new Exception( "*between 3 and 30 characters long" );
	        }
	        else if (!username.matches("^[A-Za-z0-9]{1,}$")) {
	        	throw new Exception("*only letters and numbers");
	        }
	        else if ( userDao.read( username ) != null ) {
	            throw new Exception( "*username already taken");
	        }
	    }
		else {
	        throw new Exception( "*enter a username" );
	    }
	}
	
	
	private void checkPassword(String pass, String passCheck) throws Exception {
		if (pass != null && pass.trim().length() != 0 && passCheck != null && passCheck.trim().length() != 0) {
	        if (!pass.equals(passCheck)) {
	            throw new Exception("*two different passwords, enter again");
	        } 
	        else if (pass.trim().length() < 6 || pass.trim().length() > 100) {
	            throw new Exception("*between 6 and 100 characters long");
	        }
	    } 
		else {
	        throw new Exception("*enter and confirm a password");
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

