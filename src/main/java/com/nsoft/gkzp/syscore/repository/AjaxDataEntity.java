package com.nsoft.gkzp.syscore.repository;

import java.util.HashMap;
import com.nsoft.gkzp.syscore.web.UserContext.MessageType;

/**
 * 针对ajax请求 返回entity数据
 * @author zdyang
 * @date 2019.08.29
 */
public class AjaxDataEntity {
	
	protected long messageType = MessageType.NONE;
	protected String message = "";
	protected AbstractEntity entity = null;
	protected HashMap<String, Object> map = new HashMap<String, Object>();

	public void addData(String key, Object value) {
		map.put(key, value);
		
	}
	public void putAll(HashMap<String, Object> map) {
		this.map.putAll(map);
	}
	public void clear() {
		map.clear();
	}
	public void remove(String key) {
		map.remove(key);
	}
	public long getMessagetype() {
		return messageType;
	}
	public void setMessagetype(long messagetype) {
		this.messageType = messagetype;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public AbstractEntity getEntity() {
		return entity;
	}

	public void setEntity(AbstractEntity entity) {
		this.entity = entity;
	}

	public HashMap<String, Object> getMap() {
		return map;
	}
	



}
