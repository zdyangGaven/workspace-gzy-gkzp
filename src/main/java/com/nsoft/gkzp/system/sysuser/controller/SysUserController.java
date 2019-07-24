package com.nsoft.gkzp.system.sysuser.controller;

import com.nsoft.gkzp.system.sysuser.entity.SysUser;
import com.nsoft.gkzp.system.sysuser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

@Controller
public class SysUserController {

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
    //@GetMapping（"/sysuser/login"）
    //@PostMapping("/sysuser/login")
    @RequestMapping("/user/login")
    public String login(String loginName, String password, HttpSession session, Model model){
        SysUser user = sysUserService.login(loginName,password);
        if(user != null){
            session.setAttribute("sysUser",user);
            model.addAttribute("sysUser",user);
            System.out.println(user);
            return "index";
        }
        return "login";
    }

    /**
     * 测试
     * @return
     */
    @RequestMapping("/user/getUsers")
    public List<SysUser> getUsers(){
        List<SysUser> userList =  null;
        userList = sysUserService.selectUsers();
        SysUser aa = null;
        if(userList !=null && !userList.isEmpty()){
            Iterator it = userList.iterator();
            while(it.hasNext()) {
               aa = (SysUser)it.next();
               System.out.println(aa);
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
    public String register(SysUser user,Model model){
        int id = -1;
        id = sysUserService.findIdByColumn("loginname",user.getLoginName());
        if(id >0){
            System.out.println("此账号已经存在");
            return "register";
        }
        id= sysUserService.findIdByColumn("email",user.getEmail());
        if(id>0){
            System.out.println("此email已经注册过");
            return "register";
        }

        sysUserService.saveSysUserInformation(user);
        return "register";


    }






}
