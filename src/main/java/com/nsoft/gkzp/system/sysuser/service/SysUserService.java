package com.nsoft.gkzp.system.sysuser.service;

import com.nsoft.gkzp.system.sysuser.dao.SysUserDao;
import com.nsoft.gkzp.system.sysuser.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysuserService")
public class SysUserService {

    @Autowired
    private SysUserDao sysUserDao;


    public SysUser login(String uName,String pwd){
        return sysUserDao.login(uName,pwd);
    }

    public List<SysUser> selectUsers(){ return sysUserDao.selectUsers();}

}
