package com.nsoft.gkzp.system.user.entity;

/**
 * 用户信息实体类
 */
public class User {
    private long id = -1 ;//用户ID
    private String loginName = "";//用户登录名
    private String userName = "";//用户名
    private String nickName = "";//昵称
    private String password = "";//密码

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
