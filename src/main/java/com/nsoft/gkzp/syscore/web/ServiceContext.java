package com.nsoft.gkzp.syscore.web;

import java.io.Serializable;

/**
 *
 * Service 上下文
 * @author zdyang
 * @date 2019.08.29
 *
 */
public class ServiceContext  implements Serializable {

    /**
     * 业务提示信息
     */
    private String message = "";
    private long messageType = MessageType.NONE;

    /**
     * 业务提示信息类型
     *
     */
    public static class MessageType {
        final public static long NONE = 0;
        final public static long INFO = 1;
        final public static long ERROR = 2;
    }

    public ServiceContext(){

    }

    public void setErrorMessage(String errorMessage) {
        this.message = errorMessage;
        this.messageType = MessageType.ERROR;
    }

    public void setInfoMessage(String infoMessage) {
        this.message = infoMessage;
        this.messageType = MessageType.INFO;
    }

    public long getMessageType() {
        return messageType;
    }

    public String getMessage() {
        String temp = message;
        messageType = MessageType.NONE;
        message = "";
        return temp;
    }
}
