package com.avl.yamsnew.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DAOFactory {

	private static final String FILE_PROPERTIES   = "/com/avl/yamsnew/dao/dao.properties";
    private static final String PROPERTY_URL      = "url";
    private static final String PROPERTY_DRIVER   = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";
    
    String url = "";
    String username = "";
    String password = "";
    
    DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String username;
        String password;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fileProperties = classLoader.getResourceAsStream( FILE_PROPERTIES );

        if ( fileProperties == null ) {
            throw new DAOConfigurationException( "File properties " + FILE_PROPERTIES + " is not found." );
        }

        try {
            properties.load( fileProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            username = properties.getProperty( PROPERTY_USERNAME );
            password = properties.getProperty( PROPERTY_PASSWORD );
        } catch (FileNotFoundException e) {
        	throw new DAOConfigurationException("File " + FILE_PROPERTIES + " is not found.");
    	} catch ( IOException e ) {
            throw new DAOConfigurationException( "Can't load file " + FILE_PROPERTIES, e );
        }

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Driver is not found in the classpath.", e );
        }
        
        DAOFactory instance = new DAOFactory (url, username, password);
        
        return instance;
    }
    
    
   public Connection getConnection() throws SQLException {
	   return DriverManager.getConnection(url, username, password);
   }

   
   public UserDao getUserDao() {
       return new UserDaoImpl( this );
   }
   
   
   public GameDao getGameDao() {
	   return new GameDaoImpl(this);
   }
}
