package com.nsoft.gkzp.plan.entity;

import java.io.Serializable;

public class HrRecuritPlanNeedsDo implements Serializable {
    private Integer id;

    private Integer planId;

    private Integer dept;

    private String postname;

    private String postnum;

    private Integer postintention;

    private Integer posttype;

    private String postconfig;

    private String specialities;

    private Integer degree;

    private String age;

    private Integer title;

    private Integer politic;

    private String condition;

    private String examination;

    private String interview;

    private Integer employment;

    private String reason;

    private String remarks;

    private String affixfile;

    private Integer status;

    private String postnote;

    private String deptname;

    private String postTypeName;

    public String getPostTypeName() {
        return postTypeName;
    }

    public void setPostTypeName(String postTypeName) {
        this.postTypeName = postTypeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getDept() {
        return dept;
    }

    public void setDept(Integer dept) {
        this.dept = dept;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname == null ? null : postname.trim();
    }

    public String getPostnum() {
        return postnum;
    }

    public void setPostnum(String postnum) {
        this.postnum = postnum == null ? null : postnum.trim();
    }

    public Integer getPostintention() {
        return postintention;
    }

    public void setPostintention(Integer postintention) {
        this.postintention = postintention;
    }

    public Integer getPosttype() {
        return posttype;
    }

    public void setPosttype(Integer posttype) {
        this.posttype = posttype;
    }

    public String getPostconfig() {
        return postconfig;
    }

    public void setPostconfig(String postconfig) {
        this.postconfig = postconfig == null ? null : postconfig.trim();
    }

    public String getSpecialities() {
        return specialities;
    }

    public void setSpecialities(String specialities) {
        this.specialities = specialities == null ? null : specialities.trim();
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public Integer getTitle() {
        return title;
    }

    public void setTitle(Integer title) {
        this.title = title;
    }

    public Integer getPolitic() {
        return politic;
    }

    public void setPolitic(Integer politic) {
        this.politic = politic;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition == null ? null : condition.trim();
    }

    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination == null ? null : examination.trim();
    }

    public String getInterview() {
        return interview;
    }

    public void setInterview(String interview) {
        this.interview = interview == null ? null : interview.trim();
    }

    public Integer getEmployment() {
        return employment;
    }

    public void setEmployment(Integer employment) {
        this.employment = employment;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getAffixfile() {
        return affixfile;
    }

    public void setAffixfile(String affixfile) {
        this.affixfile = affixfile == null ? null : affixfile.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPostnote() {
        return postnote;
    }

    public void setPostnote(String postnote) {
        this.postnote = postnote == null ? null : postnote.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }
}