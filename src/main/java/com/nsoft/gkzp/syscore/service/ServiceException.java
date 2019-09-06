package com.nsoft.gkzp.syscore.service;

import com.nsoft.gkzp.syscore.repository.DAOException;

/**
 * 
 *
 * 用于Service层异常
 *@author zdyang
 *
 *
 */
public class ServiceException extends DAOException {

	/**
	 * @param message
	 * @param e
	 */
	public ServiceException(String message, Throwable e)
	{
		super(message, e);
	}

	/**
	 * @param message
	 */
	public ServiceException(String message)
	{
		super(message);
	}
	
}
