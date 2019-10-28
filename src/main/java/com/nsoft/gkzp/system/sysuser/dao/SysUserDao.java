package com.nsoft.gkzp.system.sysuser.dao;

import com.nsoft.gkzp.system.sysuser.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User 实体映射器接口
 * @author zdyang
 * @date 2019.07.18
 *
 * @Mapper注解是Mybatis框架用的，标记为映射器
 * @Repository注解是Spring框架用的，标记为一个Bean
 */


//@Mapper  //在启动类中加了@MapperScan("com.nsoft.gkzp.**.dao"),故这里可不添加@Mapper注解
@Repository
public interface SysUserDao {

    /**
     * 通话登录用户名和密码获得用户信息
     * @param loginName
     * @param password
     * @return
     */
    public SysUser login(@Param("loginName") String loginName,
                      @Param("password") String password);



    /**
     * 通过传入的列和值获得用户id
     * @param column
     * @param value
     * @return
     */
    public Integer findIdByColumn(@Param("column") String column, @Param("value") String value);

    /**
     * 保存 用户注册信息
     * @param loginName 用户名
     * @param password  用户密码
     */
    public void saveRegister(@Param("loginName") String loginName, @Param("password") String password);


    /**
     * 修改用户密码
     * @param id 用户ID
     * @param password  用户密码
     */
    public void changePWD(@Param("id") int id,@Param("password") String  password) ;

}
