package com.nsoft.gkzp.system.sysuser.controller;

import com.nsoft.gkzp.system.sysuser.entity.SysUser;
import com.nsoft.gkzp.system.sysuser.service.SysUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

@Controller
public class SysUserController {
    Logger logger = LogManager.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     * @param loginName
     * @param password
     * @param session
     * @param model
     * @return
     */

    //@RequestMapping("/user/login")
    //@GetMapping（"/user/login"）
    @PostMapping("/user/login") // 发送post请求，代替了RequestMapping（value="/user/login", method="post"）
    public String login(String loginName, String password, HttpSession session, Model model){
       if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)){
           model.addAttribute("msg","用户名或密码不能为空");
           return "login";
       }
        SysUser user = sysUserService.login(loginName,password);
        if(user != null){
            session.setAttribute("sysUser",user);
            model.addAttribute("sysUser",user);
            logger.info(user);
            return "redirect:/index.jsp";
        }else{
            model.addAttribute("loginName",loginName);
            model.addAttribute("msg","用户名或密码错误");
            return "login";
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
        System.out.println("userList="+userList);
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
