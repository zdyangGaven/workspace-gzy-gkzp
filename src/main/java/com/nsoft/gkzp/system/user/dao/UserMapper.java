package com.nsoft.gkzp.system.user.dao;

import com.nsoft.gkzp.system.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * User 实体映射器接口
 * @author zdyang
 * @date 2019.07.18
 *
 * @Mapper注解是Mybatis框架用的，标记为映射器
 * @Repository注解是Spring框架用的，标记为一个Bean
 */
@Mapper
@Repository
public interface UserMapper {

    public User login(@Param("loginName") String loginName,
                      @Param("password") String password);
}
