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

    //@GetMapping（"/sysuser/login"）
    //@PostMapping("/sysuser/login")
    @RequestMapping("/user/login")
    public String login(String loginName, String password, HttpSession session, Model model){
        SysUser user = sysUserService.login(loginName,password);
        if(user != null){
            session.setAttribute("user",user);
            model.addAttribute("user",user);
            return "index";
        }
        return "login";
    }

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





}
