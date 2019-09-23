package com.nsoft.gkzp.system.sysuser.service;

import com.nsoft.gkzp.syscore.service.AbstractService;
import com.nsoft.gkzp.syscore.service.ServiceException;
import com.nsoft.gkzp.system.sysuser.dao.SysUserDao;
import com.nsoft.gkzp.system.sysuser.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysuserService")
public class SysUserServiceImpl extends AbstractService implements SysUserService{

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 登录验证
     * @param uName 用户名
     * @param pwd   密码
     * @return
     */
    public SysUser login(String uName,String pwd)throws ServiceException {
        SysUser sysUser;
        try {
            sysUser =sysUserDao.login(uName,pwd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("登录校验出错 uName="+uName, e);
        }
        return sysUser;
    }

    /**
     * 查找ID
     * @param column 表名
     * @param value  参数值
     * @return
     */
    public int findIdByColumn( String column,String value) throws ServiceException{
        int id = -1;
        try {
            Integer temp = sysUserDao.findIdByColumn(column,value);
            if(temp != null){
                id = temp.intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("查找ID出错 column="+column+",value="+value, e);
        }

        return id;
    }


    /**
     * 保存 用户注册信息
     * @param loginName 用户名
     * @param password  用户密码
     * @throws ServiceException
     */
    public void saveRegister(String loginName,String  password) throws ServiceException{
        try {
             sysUserDao.saveRegister(loginName,password);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("向数据库插入注册信息时出错 loginName="+loginName+",password="+password, e);
        }
    }





    /**
     * 查找所有用户信息
     * @return
     */
    public List<SysUser> selectUsers() throws ServiceException{
        List<SysUser>  userList = null;
        try {
            userList = sysUserDao.selectUsers();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("查找所有用户信息出错", e);
        }
        return userList;
    }

    /**
     * 保存 用户信息
     * @param user
     */
    public void saveSysUserInformation(SysUser user)throws ServiceException{
        try {
            sysUserDao.saveSysUserInformation(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("保存用户信息出错", e);
        }

    }

}
