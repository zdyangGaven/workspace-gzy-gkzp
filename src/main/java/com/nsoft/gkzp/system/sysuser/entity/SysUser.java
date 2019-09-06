package com.nsoft.gkzp.system.sysuser.entity;

import com.nsoft.gkzp.syscore.repository.AbstractEntity;

import java.sql.Timestamp;

/**
 * 用户信息实体类
 * @author zdyang
 * @date 2019.07.18
 */
public class SysUser extends AbstractEntity {
    private int id              = -1 ;//用户ID
    private String loginName    = "";//用户登录名
   // private String password     = "";//用户登录密码
    private String email        = "";//用户邮箱
    private int phone           = -1;//用户电话
    private Timestamp createTime= null;//用户创建时间
    private Timestamp pwdChangeTime = null;//改密码时间（初始值为用户创建时间）
    private String code         = "";//激活码
    private int userType        = -1;//用户类型（0系统管理员 1、内网用户 2、普通用户）
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

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Timestamp getPwdChangeTime() {
        return pwdChangeTime;
    }

    public void setPwdChangeTime(Timestamp pwdChangeTime) {
        this.pwdChangeTime = pwdChangeTime;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
             //   ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", createTime=" + createTime +
                ", pwdChangeTime=" + pwdChangeTime +
                ", code='" + code + '\'' +
                ", userType=" + userType +
                ", state=" + state +
                ", nStatusID=" + nStatusID +
                '}';
    }
}
