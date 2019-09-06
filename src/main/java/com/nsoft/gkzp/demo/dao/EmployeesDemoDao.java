package com.nsoft.gkzp.demo.dao;

import com.github.pagehelper.Page;
import com.nsoft.gkzp.demo.entity.EmployeesDemo;
import com.nsoft.gkzp.syscore.repository.DAOException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * EmployeesDemo 实体映射器接口
 * @author zdyang
 * @date 2019.08.27
 *
 */
@Mapper
@Repository
public interface EmployeesDemoDao {

    /**demo
     * 保存雇员信息
     * @param employees
     */
    public void save(EmployeesDemo employees)throws DAOException;

    /**demo
     * 通过名称获得ID值
     * @param name
     * @return
     */
    public int getIdByName(@Param("name") String name);


    /**demo
     * 通过传入的列和值获得雇员信息
     * @param column  where条件里的列名
     * @param value   where条件里的列对应的值
     ** @param status 状态值
     * @return
     */
    public EmployeesDemo findInfoByColumn(@Param("column")String column,@Param("value")String value,@Param("status") int status);


    /**demo
     * 获得雇员信息 以EmployeesDemo类组成list
     * @return
     */
    public ArrayList selectEmployees();

    /**demo
     * 获得雇员信息 - 以Hashmap组成list
     * @return
     */
    public ArrayList selectEmployeesToMap();

    /**demo
     * 分页查询
     * @param age  查询条件  年龄
     * @return
     */
    public Page<EmployeesDemo> findByPaging( int age);

}
