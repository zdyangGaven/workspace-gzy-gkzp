package com.nsoft.gkzp.member.entity;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class Dept implements Serializable {
    @Id
    private Integer id;

    private Integer deptno;

    private String deptname;

    private Integer deptlevel;

    private Integer deptright;

    private String leader;

    private Integer parentid;

    private String deptduty;

    private String deptphone;

    private String deptemail;

    private Date modifytime;

    private Integer nserial;

    private String pym;

    private Integer deptstatus;

    private String cname;

    private String oaid;

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

    public Integer getDeptlevel() {
        return deptlevel;
    }

    public void setDeptlevel(Integer deptlevel) {
        this.deptlevel = deptlevel;
    }

    public Integer getDeptright() {
        return deptright;
    }

    public void setDeptright(Integer deptright) {
        this.deptright = deptright;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getDeptduty() {
        return deptduty;
    }

    public void setDeptduty(String deptduty) {
        this.deptduty = deptduty == null ? null : deptduty.trim();
    }

    public String getDeptphone() {
        return deptphone;
    }

    public void setDeptphone(String deptphone) {
        this.deptphone = deptphone == null ? null : deptphone.trim();
    }

    public String getDeptemail() {
        return deptemail;
    }

    public void setDeptemail(String deptemail) {
        this.deptemail = deptemail == null ? null : deptemail.trim();
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Integer getNserial() {
        return nserial;
    }

    public void setNserial(Integer nserial) {
        this.nserial = nserial;
    }

    public String getPym() {
        return pym;
    }

    public void setPym(String pym) {
        this.pym = pym == null ? null : pym.trim();
    }

    public Integer getDeptstatus() {
        return deptstatus;
    }

    public void setDeptstatus(Integer deptstatus) {
        this.deptstatus = deptstatus;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid == null ? null : oaid.trim();
    }
}