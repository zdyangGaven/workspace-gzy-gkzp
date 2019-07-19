package com.nsoft.gkzp.system.user.controller;

import com.nsoft.gkzp.system.user.entity.User;
import com.nsoft.gkzp.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //@GetMapping（"/user/login"）
    //@PostMapping("/user/login")
    @RequestMapping("/user/login")
    public String login(String loginName, String password, HttpSession session, Model model){
        User user = userService.login(loginName,password);
        if(user != null){
            session.setAttribute("user",user);
            model.addAttribute("user",user);
            return "index";
        }
        return "login";

    }
}
