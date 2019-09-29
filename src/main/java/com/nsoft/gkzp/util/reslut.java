package com.nsoft.gkzp.util;

public class reslut {
    int type = MessageType.NONE;;
    String msg = "";

    /**
     * 业务提示信息类型
     *
     */
    public static class MessageType {
        final public static int NONE  = 0;
        final public static int INFO  = 1;
        final public static int ERROR = 2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
