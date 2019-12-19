package com.nsoft.gkzp.system.sysuser.service;

import com.github.pagehelper.Page;
import com.nsoft.gkzp.syscore.service.ServiceException;
import com.nsoft.gkzp.system.sysuser.entity.SysUser;
import com.nsoft.gkzp.util.ResultMsg;

import java.awt.*;

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
     * @param column 字段名
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
     * 校验用户信息
     * @param loginName 用户名
     * @param password  密码
     * @param rePassword 确认密码
     * @param checkCode 验证码
     * @param sessionId session的id值
     * @param type 注册时参数校验    0 注册操作
     *      *                       1 注册登录名校验
     *      *                       2 输入密码格式校验
     *      *                       3 确认密码校验
     *      *                       4.验证码校验
     * @throws ServiceException
     */
    public ResultMsg checkUserInfo(int type, String loginName, String password, String rePassword, String checkCode,String sessionId) throws ServiceException;


    /**
     * 修改用户密码
     * @param id 用户ID
     * @param password  用户密码
     * @throws ServiceException
     */
    public void changePWD(int id,String  password) throws ServiceException;


    /**
     * 获取用户数据
     * @param fuzzyChars 模糊查询字符串
     * @return
     * @throws ServiceException
     */
    public Page getUserInfos(String fuzzyChars) throws ServiceException;


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
