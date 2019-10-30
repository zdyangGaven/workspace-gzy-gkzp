package com.nsoft.gkzp.system.sysuser.service;

import com.github.pagehelper.Page;
import com.nsoft.gkzp.syscore.service.AbstractService;
import com.nsoft.gkzp.syscore.service.ServiceException;
import com.nsoft.gkzp.system.sysuser.dao.SysUserDao;
import com.nsoft.gkzp.system.sysuser.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

@Service("sysuserService")
public class SysUserServiceImpl extends AbstractService implements SysUserService{

    @Autowired
    private SysUserDao sysUserDao;

    private static Random random = new Random();

    /**
     * 登录验证
     * @param uName 用户名
     * @param pwd   密码
     * @return
     */
    public SysUser login(String uName,String pwd)throws ServiceException {
        SysUser sysUser;
        try {
            sysUser =sysUserDao.login(uName,pwd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("登录校验出错 uName="+uName, e);
        }
        return sysUser;
    }

    /**
     * 查找ID
     * @param column 表名
     * @param value  参数值
     * @return
     */
    public int findIdByColumn( String column,String value) throws ServiceException{
        int id = -1;
        try {
            Integer temp = sysUserDao.findIdByColumn(column,value);
            if(temp != null){
                id = temp.intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("查找ID出错 column="+column+",value="+value, e);
        }

        return id;
    }


    /**
     * 保存 用户注册信息
     * @param loginName 用户名
     * @param password  用户密码
     * @throws ServiceException
     */
    public void saveRegister(String loginName,String  password) throws ServiceException{
        try {
             sysUserDao.saveRegister(loginName,password);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("向数据库插入注册信息时出错 loginName="+loginName+",password="+password, e);
        }
    }

    /**
     * 修改用户密码
     * @param id 用户ID
     * @param password  用户密码
     * @throws ServiceException
     */
    public void changePWD(int id,String  password)  throws ServiceException{
        try {
            sysUserDao.changePWD(id,password);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("向数据库修改密码时出错 id="+id+",password="+password, e);
        }
    }


    /**
     * 获取用户数据
     * @return
     * @throws ServiceException
     */
    public Page getUserInfos() throws ServiceException{
        Page<HashMap> list = null;
        try {
            list = sysUserDao.getUserInfos();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("查询获取用户信息时出错", e);
        }
        return list;
    }


    /**
     * 验证码相关
     * @param fc
     * @param bc
     * @return
     */
    public  Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 验证码相关
     * @return
     */
    public  int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    /**
     * 验证码相关
     * @param g
     * @param w1
     * @param h1
     * @param color
     */
    public  void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }


    /**
     *  验证码相关
     * @return
     */
    public static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }


    /**
     *  验证码相关
     * @param g
     * @param w1
     * @param h1
     * @param color
     */
    public static void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    /**
     *  验证码相关
     * @param g
     * @param w1
     * @param h1
     * @param color
     */
    public static void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }

    }



}
