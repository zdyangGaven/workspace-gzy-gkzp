package com.nsoft.gkzp.syscore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Service 层基础，所有的Service必须继承AbstractService
 * 
 * @author zdyang
 * @date  2019.08.29
 *
 */
public abstract class AbstractService {

	final protected Logger logger = LoggerFactory.getLogger(getClass());
	
}
