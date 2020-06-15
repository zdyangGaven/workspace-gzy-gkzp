package com.nsoft.gkzp.member.entity;

import javax.persistence.Id;
import java.io.Serializable;

public class Dept implements Serializable {
    @Id
    private Integer id;

    private Integer deptno;//部门编号

    private String deptname;//部门名称

    private Integer parentid;//上级部门

    /*private Integer deptlevel;//部门级别

    private Integer deptright;//部门权限

    private String leader;//部门领导*/



    /*private String deptduty;//部门职责

    private String deptphone;//部门电话

    private String deptemail;//部门邮箱

    private Date modifytime;//修改时间

    private Integer nserial;//顺序号

    private String pym;//拼音码

    private Integer deptstatus;//部门状态

    private String cname;//别名

    private String oaid;//部门编号
*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}