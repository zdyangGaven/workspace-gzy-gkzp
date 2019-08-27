package com.nsoft.gkzp.system.sysuser.service;

import com.nsoft.gkzp.system.sysuser.entity.SysUser;

import java.util.List;

public interface SysUserService {

    /**
     * 登录验证
     * @param uName 用户名
     * @param pwd   密码
     * @return
     */
    public SysUser login(String uName, String pwd);


    /**
     * 查找ID
     * @param column 表名
     * @param value  参数值
     * @return
     */
    public int findIdByColumn( String column,String value) ;


    /**
     * 查找所以用户信息
     * @return
     */
    public List<SysUser> selectUsers();

    /**
     * 保存 用户信息
     * @param user
     */
    public void saveSysUserInformation(SysUser user);


}
