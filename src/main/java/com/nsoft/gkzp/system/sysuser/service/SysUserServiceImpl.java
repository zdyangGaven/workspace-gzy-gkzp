package com.nsoft.gkzp.system.sysuser.service;

import com.nsoft.gkzp.system.sysuser.dao.SysUserDao;
import com.nsoft.gkzp.system.sysuser.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysuserService")
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 登录验证
     * @param uName 用户名
     * @param pwd   密码
     * @return
     */
    public SysUser login(String uName,String pwd){
        return sysUserDao.login(uName,pwd);
    }

    /**
     * 查找ID
     * @param column 表名
     * @param value  参数值
     * @return
     */
    public int findIdByColumn( String column,String value) {
        int id = -1;
        Integer temp = sysUserDao.findIdByColumn(column,value);
        if(temp != null){
            id = temp.intValue();
        }
        return id;
    }


    /**
     * 查找所以用户信息
     * @return
     */
    public List<SysUser> selectUsers(){ return sysUserDao.selectUsers();}

    /**
     * 保存 用户信息
     * @param user
     */
    public void saveSysUserInformation(SysUser user){
        sysUserDao.saveSysUserInformation(user);
    }

}
