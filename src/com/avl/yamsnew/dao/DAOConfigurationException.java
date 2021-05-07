package com.avl.yamsnew.dao;

public class DAOConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1321778597122074599L;

	public DAOConfigurationException( String message ) {
        super( message );
    }

    public DAOConfigurationException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOConfigurationException( Throwable cause ) {
        super( cause );
    }
}
