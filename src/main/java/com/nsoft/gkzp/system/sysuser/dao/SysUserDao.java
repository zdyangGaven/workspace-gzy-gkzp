package com.nsoft.gkzp.system.sysuser.dao;

import com.nsoft.gkzp.system.sysuser.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

@Repository
@Mapper
public interface SysUserDao {

    public SysUser login(@Param("loginName") String loginName,
                      @Param("password") String password);


    public List<SysUser> selectUsers();

    @Select("select * from sys_user where 1=1")

    public List<SysUser> selectUserByLoginName( @Param("loginName") String loginName);
}