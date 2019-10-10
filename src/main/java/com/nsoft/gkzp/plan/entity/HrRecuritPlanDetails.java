package com.nsoft.gkzp.plan.entity;

import java.util.Date;

public class HrRecuritPlanDetails {
    private Integer id;

    private String planId;

    private String deptno;

    private Integer detailsStatus;

    private String inputman;

    private Date inputtime;

    private String deptexamine;

    private Date depttime;

    private String deptopinion;

    private String leaderman;

    private Date leadertime;

    private String leaderopinion;

    private String personnel;

    private Date personneltime;

    private String personnelopinion;

    private String affixfile;

    private String deptname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno == null ? null : deptno.trim();
    }

    public Integer getDetailsStatus() {
        return detailsStatus;
    }

    public void setDetailsStatus(Integer detailsStatus) {
        this.detailsStatus = detailsStatus;
    }

    public String getInputman() {
        return inputman;
    }

    public void setInputman(String inputman) {
        this.inputman = inputman == null ? null : inputman.trim();
    }

    public Date getInputtime() {
        return inputtime;
    }

    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }

    public String getDeptexamine() {
        return deptexamine;
    }

    public void setDeptexamine(String deptexamine) {
        this.deptexamine = deptexamine == null ? null : deptexamine.trim();
    }

    public Date getDepttime() {
        return depttime;
    }

    public void setDepttime(Date depttime) {
        this.depttime = depttime;
    }

    public String getDeptopinion() {
        return deptopinion;
    }

    public void setDeptopinion(String deptopinion) {
        this.deptopinion = deptopinion == null ? null : deptopinion.trim();
    }

    public String getLeaderman() {
        return leaderman;
    }

    public void setLeaderman(String leaderman) {
        this.leaderman = leaderman == null ? null : leaderman.trim();
    }

    public Date getLeadertime() {
        return leadertime;
    }

    public void setLeadertime(Date leadertime) {
        this.leadertime = leadertime;
    }

    public String getLeaderopinion() {
        return leaderopinion;
    }

    public void setLeaderopinion(String leaderopinion) {
        this.leaderopinion = leaderopinion == null ? null : leaderopinion.trim();
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel == null ? null : personnel.trim();
    }

    public Date getPersonneltime() {
        return personneltime;
    }

    public void setPersonneltime(Date personneltime) {
        this.personneltime = personneltime;
    }

    public String getPersonnelopinion() {
        return personnelopinion;
    }

    public void setPersonnelopinion(String personnelopinion) {
        this.personnelopinion = personnelopinion == null ? null : personnelopinion.trim();
    }

    public String getAffixfile() {
        return affixfile;
    }

    public void setAffixfile(String affixfile) {
        this.affixfile = affixfile == null ? null : affixfile.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }
}