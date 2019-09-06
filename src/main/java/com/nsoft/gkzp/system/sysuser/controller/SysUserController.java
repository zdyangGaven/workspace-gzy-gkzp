package com.nsoft.gkzp.system.sysuser.controller;

import com.nsoft.gkzp.syscore.web.ControllerException;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.system.sysuser.entity.SysUser;
import com.nsoft.gkzp.system.sysuser.service.SysUserService;
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
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class SysUserController {

    final protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private CommonCrypto commonCrypto;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    private String ERRORMESSAGE = "errorMessage";//异常信息
    private String ERRORPAGE = "/login"; //登录失败返回页面
    private String SUCCESSRPAGE = "redirect:/index.jsp"; //登录成功页面（redirect:重定向，防止页面刷新重新提交请求）

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
                model.addAttribute("ERRORMESSAGE", "登录失败，用户名或密码不能为空");
                return ERRORPAGE;
            }
            SysUser sysUser = sysUserService.login(loginName, null);//账户校验  并获得其信息
            if (sysUser != null) {
                SHApassword =  commonCrypto.encryptSHAEncoder(password, sysUser);//密码加密
                sysUser = sysUserService.login(loginName, SHApassword);//账户+密码 校验
                if(sysUser != null && sysUser.getId()>0){

                    userContext.setSysUser(sysUser);
                    userContext.setLoginUserId(sysUser.getId());
                    userContext.setLoginDate(new Date(System.currentTimeMillis()));
                    WebUtils.setSessionAttribute(arg0, "userContext", userContext);//生成session信息userContext
                    stringRedisTemplate.opsForValue().set("loginUser:" +sysUser.getId(), arg0.getSession().getId(),1,TimeUnit.HOURS);//向redis里存储 用户ID-sessionID对,失效时间为1h，用于拦截器判断是否重复登录
                    //model.addAttribute("sysUser", sysUser);
                    logger.info(sysUser);
                    return SUCCESSRPAGE;
                }else{
                    model.addAttribute("loginName", loginName);
                    model.addAttribute("ERRORMESSAGE", "登录失败，用户名或密码错误");
                    return ERRORPAGE;
                }

            } else {
                model.addAttribute("loginName", loginName);
                model.addAttribute("ERRORMESSAGE", "登录失败，用户名或密码错误");
                return ERRORPAGE;
            }

        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute(ERRORMESSAGE, "登录产生异常!");
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
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/user/register")
    public String register(SysUser user,Model model) throws Exception{
        int id = -1;
        id = sysUserService.findIdByColumn("loginname",user.getLoginName());


        if(id >0){
            logger.info("此账号已经存在");
            return "register";
        }
        id= sysUserService.findIdByColumn("email",user.getEmail());
        if(id>0){
            logger.info("此email已经注册过");
            return "register";
        }

        sysUserService.saveSysUserInformation(user);
        return "register";


    }






}
