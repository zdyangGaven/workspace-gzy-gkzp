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

    public int findIdByColumn( String column,String value) {
        int id = -1;
        Integer temp = sysUserDao.findIdByColumn(column,value);
        if(temp != null){
            id = temp.intValue();
        }
        return id;
    }



    public List<SysUser> selectUsers(){ return sysUserDao.selectUsers();}

    public void saveSysUserInformation(SysUser user){
        sysUserDao.saveSysUserInformation(user);
    }

}
