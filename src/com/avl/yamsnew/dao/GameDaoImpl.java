package com.avl.yamsnew.dao;

import static com.avl.yamsnew.dao.DAOUtil.close;
import static com.avl.yamsnew.dao.DAOUtil.initPreparedRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.avl.yamsnew.beans.GameBean;

public class GameDaoImpl implements GameDao {

	private DAOFactory daoFactory;
	
	private static final String SQL_INSERT = "INSERT INTO Game "
			+ "(username, starting_date, gamestate) VALUES ( ?, NOW(), ? )";
	
	private static final String SQL_SELECT_BY_USERNAME = 
			  "SELECT id, username, gamestate"
			+ " FROM Game WHERE username = ?";
	
	private static final String SQL_UPDATE = "UPDATE Game SET "
			+ "username = ?, gamestate = ? "
			+ "WHERE id = ?";
	
	private static final String SQL_DELETE = "DELETE FROM Game WHERE id = ?";
	
	
	
	GameDaoImpl ( DAOFactory daoFactory ){
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(GameBean game) throws DAOException {
		
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet autoGeneratedValues = null;

	    try {
	        connection = daoFactory.getConnection();
	        preparedStatement = initPreparedRequest( connection, SQL_INSERT, true, game.getUsername(), game.getGamestate() );
	        int status = preparedStatement.executeUpdate();
	        
	        if ( status == 0 ) {
	            throw new DAOException( "Fail to create game, no row added." );
	        }
	        
	        autoGeneratedValues = preparedStatement.getGeneratedKeys();
	        if ( autoGeneratedValues.next() ) {
	            game.setId( autoGeneratedValues.getLong( 1 ) );
	        } else {
	            throw new DAOException( "Fail to create game in database, no auto-generated ID returned." );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        close( autoGeneratedValues, preparedStatement, connection );
	    }
		
	}
	

	@Override
	public GameBean read(String username) throws DAOException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    GameBean game = null;

	    try {
	        connection = daoFactory.getConnection();
        	preparedStatement = initPreparedRequest( connection, SQL_SELECT_BY_USERNAME, false, username);
	        
	        resultSet = preparedStatement.executeQuery();
	        
	        if ( resultSet.next() ) {
	            game = map( resultSet );
	        }
	        
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        close( resultSet, preparedStatement, connection );
	    }

	    return game;
	}

	
	@Override
	public void update(GameBean game) throws DAOException {
		
		Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = daoFactory.getConnection();
	        preparedStatement = initPreparedRequest( 
	        		connection, SQL_UPDATE, true, 
	        		game.getUsername(), game.getGamestate(), game.getId());
	        int status = preparedStatement.executeUpdate();
	        
	        if ( status == 0 ) {
	            throw new DAOException( "Fail to update game." );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        close( preparedStatement, connection );
	    }
	}
	

	@Override
	public void delete(GameBean game) throws DAOException {
		
		Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = daoFactory.getConnection();
	        preparedStatement = initPreparedRequest( connection, SQL_DELETE, true, game.getId());
	        int status = preparedStatement.executeUpdate();
	        
	        if ( status == 0 ) {
	            throw new DAOException( "Fail to delete game." );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        close( preparedStatement, connection );
	    }
		
	}
	
	
	
	private static GameBean map( ResultSet resultSet ) throws SQLException {
		
		GameBean game = new GameBean();
		
	    game.setId( resultSet.getLong( "id" ) );
	    game.setUsername( resultSet.getString( "username" ) );
	    game.setGamestate( resultSet.getString( "gamestate" ) );
	    
		return game;
	}

	
}
