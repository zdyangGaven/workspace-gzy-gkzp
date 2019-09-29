package com.nsoft.gkzp.util;

import org.springframework.stereotype.Component;
/**公共方法
 * 返回结果信息
 * @author 孙理锟
 * @date 2019.09.29
 */
@Component
public class ResultMsg {

    int type    = MsgType.NONE; //返回信息类型
    String msg  = "";           //返回信息

    /**
     * 业务提示信息类型
     *
     */
    public static class MsgType {
        final public static int NONE  = 0; //无
        final public static int INFO  = 1;//正常
        final public static int ERROR = 2;//问题
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

    public void setResultMsg(int type,String msg){
        this.type = type;
        this.msg = msg;
    }
}
