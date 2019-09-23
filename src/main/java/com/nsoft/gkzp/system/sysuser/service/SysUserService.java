package com.nsoft.gkzp.system.sysuser.service;

import com.nsoft.gkzp.syscore.service.ServiceException;
import com.nsoft.gkzp.system.sysuser.entity.SysUser;

import java.util.List;

public interface SysUserService {

    /**
     * 登录验证
     * @param uName 用户名
     * @param pwd   密码
     * @return
     */
    public SysUser login(String uName, String pwd)  throws ServiceException;


    /**
     * 查找ID
     * @param column 表名
     * @param value  参数值
     * @return
     */
    public int findIdByColumn( String column,String value) throws ServiceException;

    /**
     * 保存 用户注册信息
     * @param loginName 用户名
     * @param passWord  用户密码
     * @throws ServiceException
     */
    public void saveRegister(String loginName,String  password) throws ServiceException;






    /**
     * 查找所以用户信息
     * @return
     */
    public List<SysUser> selectUsers() throws ServiceException;

    /**
     * 保存 用户信息
     * @param user
     */
    public void saveSysUserInformation(SysUser user) throws ServiceException;



}
