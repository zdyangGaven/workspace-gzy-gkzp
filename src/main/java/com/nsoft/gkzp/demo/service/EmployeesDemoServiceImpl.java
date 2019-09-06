package com.nsoft.gkzp.demo.service;

import com.github.pagehelper.Page;
import com.nsoft.gkzp.demo.dao.EmployeesDemoDao;
import com.nsoft.gkzp.demo.entity.EmployeesDemo;
import com.nsoft.gkzp.syscore.service.AbstractService;
import com.nsoft.gkzp.syscore.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 接口实现
 * @author zdyang
 * @date 2019.08.27
 */
@Service
public class EmployeesDemoServiceImpl extends AbstractService implements EmployeesDemoService{


    @Autowired
    EmployeesDemoDao  demoDao;

    /**
     * 保存雇员信息
     * @param employees
     */
    public void save(EmployeesDemo employees) throws ServiceException {
        try {

            demoDao.save(employees);
            logger.info("不需要定义logger了，在AbstractService抽象类中已定义好了");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("保存雇员信息时出错 参数employees="+employees, e);
        }

    }

    /**
     * 通过名称获得ID值
     * @param name
     * @return
     */
    public int getIdByName(String name) throws ServiceException {
        int id = -1;
        try {
            id = demoDao.getIdByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("通过名称获得ID值报错 参数 name="+name, e);
        }
        return id;
    }


    /**
     * 通过传入的列和值获得雇员信息
     * @param column  where条件里的列名
     * @param value   where条件里的列对应的值
     ** @param status 状态值
     * @return
     */
    public EmployeesDemo findInfoByColumn(String column,String value, int status) throws ServiceException {
        EmployeesDemo info = null;
        try {
            info = demoDao.findInfoByColumn(column, value, status);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("通过传入的列和值获得雇员信息 参数 column,value,status=" + column + "," + value + "," + status, e);
        }
        return info;
    }

    /**
     * 获得雇员信息
     * @return
     */
    public ArrayList selectEmployees() throws ServiceException {
        ArrayList list = null;
        try{
            list = demoDao.selectEmployees();
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("获得雇员信息报错1", e);
        }
        return list;
    }

    /**
     * 获得雇员信息
     * @return
     */
    public ArrayList selectEmployeesToMap() throws ServiceException {
        ArrayList list = null;
        try{
            list = demoDao.selectEmployeesToMap();
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("获得雇员信息报错2", e);
        }
        return list;
    }

    /**
     * 分页查询结果
     * @param age 查询条件  年龄
     * @return
     */
    public Page<EmployeesDemo> findByPaging(int age)throws ServiceException {
        Page<EmployeesDemo> pages = null;
        try{
            pages =demoDao.findByPaging(age);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("分页查询结果出错 参数age="+age, e);
        }
        return pages;
    }




}
