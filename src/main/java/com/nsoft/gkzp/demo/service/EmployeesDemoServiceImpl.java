package com.nsoft.gkzp.demo.service;

import com.github.pagehelper.Page;
import com.nsoft.gkzp.demo.dao.EmployeesDemoDao;
import com.nsoft.gkzp.demo.entity.EmployeesDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 接口实现
 * @author zdyang
 * @date 2019.08.27
 */
@Service
public class EmployeesDemoServiceImpl implements EmployeesDemoService{


    @Autowired
    EmployeesDemoDao  demoDao;

    /**
     * 保存雇员信息
     * @param employees
     */
    public void save(EmployeesDemo employees){
        demoDao.save(employees);
    }

    /**
     * 通过名称获得ID值
     * @param name
     * @return
     */
    public int getIdByName(String name){
        return demoDao.getIdByName(name);
    }


    /**
     * 通过传入的列和值获得雇员信息
     * @param column  where条件里的列名
     * @param value   where条件里的列对应的值
     ** @param status 状态值
     * @return
     */
    public EmployeesDemo findInfoByColumn(String column,String value, int status){
        return demoDao.findInfoByColumn(column,value,status);
    }

    /**
     * 获得雇员信息
     * @return
     */
    public ArrayList selectEmployees(){
        return demoDao.selectEmployees();
    }

    /**
     * 获得雇员信息
     * @return
     */
    public ArrayList selectEmployeesToMap(){
        return demoDao.selectEmployeesToMap();
    }

    /**
     * 分页查询结果
     * @param age 查询条件  年龄
     * @return
     */
    public Page<EmployeesDemo> findByPaging(int age){
        return demoDao.findByPaging(age);
    }




}
