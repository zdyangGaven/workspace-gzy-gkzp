package com.nsoft.gkzp.system.sysuser.entity;

import java.sql.Timestamp;

/**
 * 用户信息实体类
 * @author zdyang
 * @date 2019.07.18
 */
public class SysUser {
    private int id              = -1 ;//用户ID
    private String loginName    = "";//用户登录名
    private String password     = "";//用户登录密码
    private String email        = "";//用户邮箱
    private Timestamp createTime= null;//用户创建时间
    private String code         = "";//激活码
    private int state           = -1;//用户激活状态：0表示未激活，1表示激活
    private int nStatusID       = -1;//用户状态 0无效 1有效

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getnStatusID() {
        return nStatusID;
    }

    public void setnStatusID(int nStatusID) {
        this.nStatusID = nStatusID;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", code='" + code + '\'' +
                ", state=" + state +
                ", nStatusID=" + nStatusID +
                '}';
    }
}
