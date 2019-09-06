package com.nsoft.gkzp.syscore.web;

/**
 *
 * @author zdyang
 * 用于Web层异常
 * @date  2019.08.29
 */
public class ControllerException extends RuntimeException {

    /**
     * 向Web层通知异常
     *
     * @param string
     * @param e
     * @param userContext
     */
    public ControllerException(String string, Throwable e, UserContext userContext) {
        super(string, e);
        userContext.setErrorMessage(string);
    }

    /**
     * 向Web层通知异常
     * @param string
     * @param userContext
     */
    public ControllerException(String string, UserContext userContext) {
        super(string);
        userContext.setErrorMessage(string);
    }


    /**
     * 向Web层通知异常
     *
     * @param string
     * @param e
     * @param serviceContext
     */
    public ControllerException(String string, Throwable e, ServiceContext serviceContext) {
        super(string, e);
        serviceContext.setErrorMessage(string);
    }

    /**
     * 向Web层通知异常
     * @param string
     * @param serviceContext
     */
    public ControllerException(String string, ServiceContext serviceContext) {
        super(string);
        serviceContext.setErrorMessage(string);
    }
}
