package com.nsoft.gkzp.syscore.web;
import java.io.Serializable;
import java.util.Date;
import com.nsoft.gkzp.system.sysuser.entity.SysUser;
import org.springframework.stereotype.Component;

/**
 * session
 * @author zdyang
 * @date  2019.08.29
 */
@Component
public class UserContext implements Serializable {

    /**
     * 登录语言
     */
    private String local = "zh_CN";

    /**
     * 登录时间
     */
    private Date loginDate = null;

    /**
     * 用户信息
     */
    private SysUser sysUser = null;
    /**
     * 用户ID (=sysUser.getId())
     */
    private int loginUserId = -1;


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
        final public static long NONE  = 0;
        final public static long INFO  = 1;
        final public static long ERROR = 2;
    }

    public UserContext(){

    }



    public void setLocal(String local) {
        this.local = local;
    }

    public String getLocal() {
        return local;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public SysUser getSysUser() {
        return sysUser;
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
        String temp = this.message;
        this.messageType = MessageType.NONE;
        this.message = "";
        return temp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public int getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(int loginUserId) {
        this.loginUserId = loginUserId;
    }
    /**
     * 用户拥有岗位列表
     */
    //   private List<SysDutyAgency> sysDutyAgencyList = null;

    /**
     * 当前权限岗位信息
     */
    //   private SysDutyAgency sysDutyAgency = null;

    /**
     * 当前权限单位信息
     */
    //   private SysAgency sysAgency = null;

    /**
     * 当前使用模块
     */
    //   private long moduleId = 0;

    /**
     * 模块字符串，用于模块的显示
     */
    //   private String moduleString = "";

    /**
     * 菜单字符串，用于菜单的显示
     */
    //   private String menuString = "";
/*
    public List<SysDutyAgency> getSysDutyAgencyList() {
        return sysDutyAgencyList;
    }


    public void setSysDutyAgencyList(List<SysDutyAgency> sysDutyAgencyList) {
        this.sysDutyAgencyList = sysDutyAgencyList;
    }


    public SysDutyAgency getSysDutyAgency() {
        return sysDutyAgency;
    }


    public void setSysDutyAgency(SysDutyAgency sysDutyAgency) {
        this.sysDutyAgency = sysDutyAgency;
    }


    public SysAgency getSysAgency() {
        return sysAgency;
    }

    public void setSysAgency(SysAgency sysAgency) {
        this.sysAgency = sysAgency;
    }
    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleString() {
        return moduleString;
    }

    public void setModuleString(String moduleString) {
        this.moduleString = moduleString;
    }

    public String getMenuString() {
        return menuString;
    }

    public void setMenuString(String menuString) {
        this.menuString = menuString;
    }
*/

}
