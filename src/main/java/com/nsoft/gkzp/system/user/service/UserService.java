package com.nsoft.gkzp.system.user.service;

import com.nsoft.gkzp.system.user.dao.UserMapper;
import com.nsoft.gkzp.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User login(String uName,String pwd){
        return userMapper.login(uName,pwd);
    }

    public List<User> selectUsers(){ return userMapper.selectUsers();}

}
