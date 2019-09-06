package com.nsoft.gkzp.demo.entity;
import com.nsoft.gkzp.syscore.repository.AbstractEntity;

import java.sql.Timestamp;

/**
 * 雇员表EmployeesDemo
 * @author zdyang
 * @date 2019.08.27
 */
public class EmployeesDemo extends AbstractEntity {

    private int id              = -1 ;//员工ID              demo
    private String name         = "";//员工名称             demo
    private int age             = -1;//年龄                 demo
    private String address      = "";//地址
   // @DateTimeFormat(pattern = "yyyy-mm-dd  hh:mm:ss") //进行日期格式化
    private Timestamp createTime= null;//用户创建时间
    private int status       = -1;//用户状态 0无效 1有效


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmployeesDemo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
/**
--建表SQL
 create table EmployeesDemo --雇员表demo
 (
 id            int  identity(1,1)  primary key not null,--id主键 自增长
 name	      varchar(50),      --姓名
 age			  int,              --年龄
 address       varchar(100),  	--地址
 status        int default 1,	--状态  1有效  0无效（已删除）
 createTime    datetime default CONVERT(varchar(100), GETDATE(), 20) --创建时间
 );

 execute sp_addextendedproperty    'MS_Description','雇员表demo',  'user','dbo','table','EmployeesDemo',null,null;
 execute sp_addextendedproperty    'MS_Description','id主键 自增长',  'user','dbo','table','EmployeesDemo','column','id';
 execute sp_addextendedproperty    'MS_Description','姓名',  'user','dbo','table','EmployeesDemo','column','name';
 execute sp_addextendedproperty    'MS_Description','年龄',  'user','dbo','table','EmployeesDemo','column','age';
 execute sp_addextendedproperty    'MS_Description','地址',  'user','dbo','table','EmployeesDemo','column','address';
 execute sp_addextendedproperty    'MS_Description','状态  1有效  0无效（已删除）',  'user','dbo','table','EmployeesDemo','column','status';
 execute sp_addextendedproperty    'MS_Description','创建时间',  'user','dbo','table','EmployeesDemo','column','createTime';

 */