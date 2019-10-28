package com.nsoft.gkzp.system.sysuser.service;

import com.nsoft.gkzp.syscore.service.ServiceException;
import com.nsoft.gkzp.system.sysuser.entity.SysUser;

import java.awt.*;
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
     * @param password  用户密码
     * @throws ServiceException
     */
    public void saveRegister(String loginName,String  password) throws ServiceException;

    /**
     * 修改用户密码
     * @param id 用户ID
     * @param password  用户密码
     * @throws ServiceException
     */
    public void changePWD(int id,String  password) throws ServiceException;

    /**
     * 验证码相关
     * @param fc
     * @param bc
     * @return
     */
    public Color getRandColor(int fc, int bc) ;

    /**
     * 验证码相关
     * @return
     */
    public  int getRandomIntColor();

    /**
     * 验证码相关
     * @param g
     * @param w1
     * @param h1
     * @param color
     */
    public  void shear(Graphics g, int w1, int h1, Color color) ;


}
