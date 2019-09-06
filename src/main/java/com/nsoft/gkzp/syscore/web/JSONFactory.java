package com.nsoft.gkzp.syscore.web;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nsoft.gkzp.syscore.repository.AjaxDataEntity;
import com.nsoft.gkzp.syscore.repository.DataResult ;

/**
 * Ajax提交后的返回实体
 * @author zdyang
 * @date 2019.08.29
 *
 */
public class JSONFactory {
	
	final protected  static Logger logger = LogManager.getLogger(JSONFactory.class);

	/**
	 * 用于表单AJAX请求信息后转换为json
	 * @param userContext
	 * @return
	 */
	public static String toJSONString(UserContext userContext) {

		String json = "";
		JSONObject jso = new JSONObject();
		jso.put("messageType",userContext.getMessageType());
		jso.put("message",userContext.getMessage());
		json = jso.toString();
		logger.info("AJAX请求返回信息：" + json);
		return json;
	}
	/**
	 * 用于表单AJAX请求信息后转换为json
	 * @param entity
	 * @param userContext
	 * @return
	 */
	public static String toJSONString(AjaxDataEntity entity, UserContext userContext) {
		
		String json = "";
		entity.setMessagetype(userContext.getMessageType());
		entity.setMessage(userContext.getMessage());
		json = JSONObject.fromObject(entity).toString();
		logger.info("AJAX请求返回信息：" + json);
		return json;
	}
	
	/**
	 * 用于表单AJAX请求信息后转换为json
	 * 用于ServiceController
	 * @param serviceResponse
	 * @param serviceContext
	 * @return
	 */
//	public static String toJSONString(ServiceResponse serviceResponse, ServiceContext serviceContext) {
//
//		String json = "";
//		serviceResponse.setMessagetype(serviceContext.getMessageType());
//		serviceResponse.setMessage(serviceContext.getMessage());
//		json = JSONObject.fromObject(serviceResponse).toString();
//		logger.info("服务请求返回信息：" + json);
//		return json;
//
//	}
	
	/**
	 * 用于分页查询请求信息后转换为json
	 * @param entity
	 * @param userContext
	 * @return
	 */
	public static String toJSONString(DataResult entity, UserContext userContext) {

		String json = "";
		entity.setMessagetype(userContext.getMessageType());
		entity.setMessage(userContext.getMessage());
		json = JSONObject.fromObject(entity).toString();
		logger.info("AJAX请求返回信息：" + json);
		return json;
		
	}
	
	
//	public static void main(String args[]){
//
//		UserContext userContext = new UserContext();
//		//userContext.USERID = 1;
//
//		AjaxDataEntity entity = new AjaxDataEntity();
//		entity.addData("id", "111");
//
//		Sample sample = new Sample();
//		sample.setId(Long.valueOf(1));
//		sample.setSampleDate(new Date(System.currentTimeMillis()));
//		sample.setSampleTimestamp(new Timestamp(System.currentTimeMillis()));
//		sample.setSampleText6("9430u5tijgkfmew3rujfsdlkjf0w3reojfmj90wreofsdmew9rofdlcmrepofskd");
//		entity.setEntity(sample);
//
//
//		JSONFactory.toJSONString(entity, userContext);
//	}
}
