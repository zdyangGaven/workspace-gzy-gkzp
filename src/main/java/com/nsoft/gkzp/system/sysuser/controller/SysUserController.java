package com.nsoft.gkzp.system.sysuser.controller;

import com.nsoft.gkzp.syscore.web.ControllerException;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.system.sysuser.entity.SysUser;
import com.nsoft.gkzp.system.sysuser.service.SysUserService;
import com.nsoft.gkzp.util.CheckDataType;
import com.nsoft.gkzp.util.CommonCrypto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class SysUserController {

    final protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private CommonCrypto commonCrypto;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    private String MESSAGE      = "msg";//异常信息
    private String ERRORPAGE    = "redirect:/jsp/login.jsp"; //登录失败返回页面
    private String SUCCESSRPAGE = "redirect:/jsp/index.jsp"; //登录成功页面（redirect:重定向，防止页面刷新重新提交请求）

    private String REGISTER_ERRORPAGE    = "/jsp/register.jsp"; //注册失败返回页面
    private String REGISTER_SUCCESSRPAGE = "redirect:/jsp/login.jsp"; //注册成功，返回登录页面（redirect:重定向，防止页面刷新重新提交请求）

    /**
     * 登录
     * @param loginName
     * @param password
     * @param arg0
     * @param arg1
     * @param model
     * @return
     * @throws ControllerException
     */

    //@RequestMapping("/user/login")
    //@GetMapping（"/user/login"）
    @PostMapping("/user/login") // 发送post请求，代替了RequestMapping（value="/user/login", method="post"）
    public String login(String loginName, String password, HttpServletRequest arg0, HttpServletResponse arg1, Model model) throws ControllerException {
        try {

            UserContext userContext = new UserContext(); //session
            String SHApassword = "";//加密后密码

            logger.info("开始用户登录校验");
            if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
                model.addAttribute(MESSAGE, "登录失败，用户名或密码不能为空");
                return ERRORPAGE;
            }
            SHApassword =  commonCrypto.encryptSHAEncoder(password);//密码加密
            SysUser sysUser = sysUserService.login(loginName, SHApassword);//账户+密码 校验
            if (sysUser != null && sysUser.getId()>0) {
                    userContext.setSysUser(sysUser);
                    userContext.setLoginUserId(sysUser.getId());
                    userContext.setLoginDate(new Date(System.currentTimeMillis()));
                    WebUtils.setSessionAttribute(arg0, "userContext", userContext);//生成session信息userContext
                    stringRedisTemplate.opsForValue().set("loginUser:" +sysUser.getId(), arg0.getSession().getId(),1,TimeUnit.HOURS);//向redis里存储 用户ID-sessionID对,失效时间为1h，用于拦截器判断是否重复登录
                    //model.addAttribute("sysUser", sysUser);
                    logger.info(sysUser);
                    return SUCCESSRPAGE;
            } else {
                model.addAttribute("loginName", loginName);
                model.addAttribute(MESSAGE, "登录失败，用户名或密码错误");
                return ERRORPAGE;
            }

        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute(MESSAGE, "登录产生异常!");
            return ERRORPAGE;
        }

    }


    /**
     * 测试
     * @return
     */
    @ResponseBody   //返回json数据
    @RequestMapping("/user/getUsers")
       public List<SysUser> getUsers(){
        List<SysUser> userList =  null;
        userList = sysUserService.selectUsers();
        SysUser aa = null;
        if(userList !=null && !userList.isEmpty()){
            Iterator it = userList.iterator();
            while(it.hasNext()) {
               aa = (SysUser)it.next();
                logger.info(aa);
            }
        }
        logger.info("userList="+userList);
        return userList;
    }

    /**
     * 注册
     * @param
     * @param model
     * @return
     */
    @RequestMapping("/user/register")
    public String register(String loginName,String password,String rePassword, HttpServletRequest arg0, HttpServletResponse arg1,Model model) throws Exception{
        int id = -1;

        try {
            logger.info("开始用户注册信息校验");
            if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
                model.addAttribute(MESSAGE, "注册失败，用户名或密码不能为空,请检查");
                return REGISTER_ERRORPAGE;
            }

            if(!password.equals(rePassword)){
                model.addAttribute(MESSAGE, "注册失败，密码和确认密码不一样,请检查");
                return REGISTER_ERRORPAGE;
            }

            if (!CheckDataType.checkName(loginName)) {
                model.addAttribute(MESSAGE, "注册失败，用户名必须是4-10位字母或数字或下划线组成，且不能以数字开头,请检查");
                return REGISTER_ERRORPAGE;
            }

            if (password.length() < 8 || password.length() > 16) {
                model.addAttribute(MESSAGE, "注册失败，密码必须是8-16位且至少包含字母、数字、特殊字符中的两种,请检查");
                return REGISTER_ERRORPAGE;
            }
            if (CheckDataType.isChinese(password)) {
                model.addAttribute(MESSAGE, "注册失败，密码不能包含汉子,请检查");
                return REGISTER_ERRORPAGE;
            }

            int i = CheckDataType.matcheData(password, 1) ? 1 : 0;
            int j = CheckDataType.matcheData(password, 2) ? 1 : 0;
            int k = CheckDataType.matcheData(password, 3) ? 1 : 0;
            if (i + j + k < 2) {
                model.addAttribute(MESSAGE, "注册失败，密码必须是8-16位且至少包含字母、数字、特殊字符中的两种,请检查");
                return REGISTER_ERRORPAGE;
            }

            if (password.contains(loginName)) {
                model.addAttribute(MESSAGE, "注册失败，密码不能包含账户信息,请检查");
                return REGISTER_ERRORPAGE;
            }

            id = sysUserService.findIdByColumn("loginname", loginName);
            if (id > 0) {
                model.addAttribute(MESSAGE, "注册失败，此账户信息已被注册过,请检查");
                return REGISTER_ERRORPAGE;
            }
            logger.info("注册信息校验成功");

            String  SHApassword =  commonCrypto.encryptSHAEncoder(password);//密码加密
            sysUserService.saveRegister(loginName,SHApassword);

        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute(MESSAGE, "注册产生异常!");
            return REGISTER_ERRORPAGE;
        }

        model.addAttribute(MESSAGE, "注册成功，请登录!");
        return REGISTER_SUCCESSRPAGE;


    }






}
