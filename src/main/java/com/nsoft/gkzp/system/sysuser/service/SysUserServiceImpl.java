package com.nsoft.gkzp.system.sysuser.service;

import com.github.pagehelper.Page;
import com.nsoft.gkzp.syscore.service.AbstractService;
import com.nsoft.gkzp.syscore.service.ServiceException;
import com.nsoft.gkzp.system.sysuser.dao.SysUserDao;
import com.nsoft.gkzp.system.sysuser.entity.SysUser;
import com.nsoft.gkzp.util.CheckDataType;
import com.nsoft.gkzp.util.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

@Service("sysuserService")
public class SysUserServiceImpl extends AbstractService implements SysUserService{

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
            logger.error("登录验证出错 参数：uName="+uName,e);
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
            logger.error("查找ID出错 column="+column+",value="+value, e);
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
            logger.error("向数据库插入注册信息时出错 loginName="+loginName+",password="+password, e);
            throw new ServiceException("向数据库插入注册信息时出错 loginName="+loginName+",password="+password, e);
        }
    }

    /**
     * 校验用户信息
     * @param loginName 用户名
     * @param password  密码
     * @param rePassword 确认密码
     * @param checkCode 验证码
     * @param sessionId session的id值
     * @param type 注册时参数校验    0 注册操作 全部校验
     *      *                       1 注册登录名校验
     *      *                       2 输入密码格式校验
     *      *                       3 确认密码校验
     *      *                       4.验证码校验
     * @throws ServiceException
     */
    public ResultMsg checkUserInfo(int type, String loginName, String password, String rePassword, String checkCode,String sessionId) throws ServiceException{

        ResultMsg resultMsg = new ResultMsg();
        try{
            logger.info("开始用户信息校验,type="+type+";loginName="+loginName+";checkCode="+checkCode+";sessionId="+sessionId);
            switch (type){
                case 0:
                case 1:
                    if (StringUtils.isEmpty(loginName)) {
                        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR, "用户名不能为空,请检查!");
                        break;
                    }else if(!CheckDataType.checkName(loginName)) {
                        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR, "用户名必须是4-10位字母或数字或下划线组成，且不能以数字开头");
                        break;
                    }else{
                        int id = this.findIdByColumn("loginname", loginName);
                        if (id > 0) {
                            resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"此账户信息已被注册过");
                            break;
                        }
                    }
                    if(type ==1)
                        break;
                case 2:
                    if (StringUtils.isEmpty(password)) {
                        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"密码不能为空");
                        break;
                    }else if (CheckDataType.isChinese(password)) {
                        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"密码错误，密码不能包含汉子");
                        break;
                    }else if (password.length() < 8 || password.length() > 16) {
                        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"密码长度错误，密码必须是8-16位且至少包含字母、数字、特殊字符中的两种");
                        break;
                    } if (!StringUtils.isEmpty(loginName) && password.contains(loginName)) {
                        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"密码内容错误，密码不能包含账户信息");
                        break;
                    }else{
                        int i = CheckDataType.matcheData(password, 1) ? 1 : 0;
                        int j = CheckDataType.matcheData(password, 2) ? 1 : 0;
                        int k = CheckDataType.matcheData(password, 3) ? 1 : 0;
                        if (i + j + k < 2) {
                            resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"密码格式错误，密码必须是8-16位且至少包含字母、数字、特殊字符中的两种");
                           break;
                        }
                    }
                    if(type ==2)
                        break;
                case 3:
                    if(!StringUtils.isEmpty(password) && !password.equals(rePassword)){
                        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"密码和确认密码不一致,请检查");
                       break;
                    }
                    if(type == 3)
                        break;
                case 4:
                    //验证码校验
                    if(StringUtils.isEmpty(checkCode)  ){
                        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"验证码不能为空,请检查!");
                        break;
                    }else{
                        String checkCodeByRedis = stringRedisTemplate.opsForValue().get("loginUser:checkCode-"+sessionId);//获取redis里存的验证码
                        logger.info("session中的验证码="+checkCodeByRedis);
                        if(StringUtils.isEmpty(checkCodeByRedis)){
                            resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"验证码已失效");
                            break;
                        }else if(!checkCode.toLowerCase().equals(checkCodeByRedis.toLowerCase())){
                            resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"验证码不正确，请重新填写");
                            break;
                        }
                    }
                    if(type == 4)
                        break;
            }

        }catch (Exception e) {
            logger.error("校验用户信息时出错，type="+type+";loginName="+loginName+";password="+password+";rePassword="+rePassword+";checkCode="+checkCode+";sessionId="+sessionId+";结果为resultMsg="+resultMsg,e);
            throw new ServiceException("校验出错", e);
        }
        return resultMsg;
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
            logger.error("向数据库修改密码时出错 id="+id+",password="+password, e);
            throw new ServiceException("向数据库修改密码时出错 id="+id+",password="+password, e);
        }
    }


    /**
     * 获取用户数据
     * @param fuzzyChars 模糊查询字符串
     * @return
     * @throws ServiceException
     */
    public Page getUserInfos(String fuzzyChars) throws ServiceException{
        Page<HashMap> list = null;
        try {
            list = sysUserDao.getUserInfos(fuzzyChars);

        } catch (Exception e) {
            logger.error("查询获取用户信息时出错 fuzzyChars="+fuzzyChars, e);
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
