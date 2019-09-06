package com.nsoft.gkzp.demo.service;

import com.github.pagehelper.Page;
import com.nsoft.gkzp.demo.entity.EmployeesDemo;
import com.nsoft.gkzp.syscore.service.ServiceException;

import java.util.ArrayList;

/**
 * 接口
 * @author zdyang
 * @date 2019.08.27
 */
public interface EmployeesDemoService {

    /**
     * 保存雇员信息
     * @param employees
     */
    public void save(EmployeesDemo employees) throws ServiceException;

    /**
     * 通过名称获得ID值
     * @param name
     * @return
     */
    public int getIdByName(String name) throws ServiceException;


    /**
     * 通过传入的列和值获得雇员信息
     * @param column  where条件里的列名
     * @param value   where条件里的列对应的值
     ** @param status 状态值
     * @return
     */
    public EmployeesDemo findInfoByColumn(String column,String value, int status) throws ServiceException;

    /**
     * 获得雇员信息
     * @return
     */
    public ArrayList selectEmployees() throws ServiceException;
    /**
     * 获得雇员信息
     * @return
     */
    public ArrayList selectEmployeesToMap() throws ServiceException;

    /**
     * 分页查询结果
     * @param age 查询条件  年龄
     * @return
     */
    public Page<EmployeesDemo> findByPaging(int age) throws ServiceException;
}
