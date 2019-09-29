package com.nsoft.gkzp.util;

import org.springframework.stereotype.Component;

@Component
public class ReslutMsg {
    int type = MsgType.NONE;;
    String msg = "";

    /**
     * 业务提示信息类型
     *
     */
    public static class MsgType {
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

    public void setReslutMsg(int type,String msg){
        this.type = type;
        this.msg = msg;
    }
}
