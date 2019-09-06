package com.nsoft.gkzp.syscore.repository;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

/**
 * 用于存储层Bean Catch异常后向外抛出RepositoryException
 * @author zdyang
 * @date 2019.08.29
 *
 */
public class DAOException extends DataAccessException {

	/**
	 * @param message
	 * @param e
	 */
	public DAOException(String message, Throwable e)
	{
		super(message, e);
		
	}

	/**
	 * @param message
	 */
	public DAOException(String message)
	{
		super(message);
	}
	
}
