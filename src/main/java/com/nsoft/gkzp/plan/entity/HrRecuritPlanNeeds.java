package com.nsoft.gkzp.plan.entity;

import javax.persistence.Id;
import java.io.Serializable;

public class HrRecuritPlanNeeds implements Serializable {
    @Id
    private Integer id;

    private Integer planId;//招聘计划id

    private Integer dept;//需求部门

    private String postname;//岗位名称

    private String postnum;//招聘人数

    private Integer postintention;//需求意向

    private Integer posttype;//岗位类别

    private String postconfig;//岗位设置

    private String specialities;//专业

    private Integer degree;//学历学位

    private String age;//年龄

    private Integer title;//职称或职业资格

    private Integer politic;//政治面貌

    private String condition;//其他条件

    private String examination;//考试考核方式

    private String interview;//面试方式及比例

    private Integer employment;//用人方式

    private String reason;//引进人才理由

    private String remarks;//备注

    private String affixfile;//附件

    private Integer status;//状态

    private String postnote;//岗位意见

    private String deptname;//部门名称

    @Override
    public String toString() {
        return "HrRecuritPlanNeeds{" +
                "id=" + id +
                ", planId=" + planId +
                ", dept=" + dept +
                ", postname='" + postname + '\'' +
                ", postnum='" + postnum + '\'' +
                ", postintention=" + postintention +
                ", posttype=" + posttype +
                ", postconfig='" + postconfig + '\'' +
                ", specialities='" + specialities + '\'' +
                ", degree=" + degree +
                ", age='" + age + '\'' +
                ", title=" + title +
                ", politic=" + politic +
                ", condition='" + condition + '\'' +
                ", examination='" + examination + '\'' +
                ", interview='" + interview + '\'' +
                ", employment=" + employment +
                ", reason='" + reason + '\'' +
                ", remarks='" + remarks + '\'' +
                ", affixfile='" + affixfile + '\'' +
                ", status=" + status +
                ", postnote='" + postnote + '\'' +
                ", deptname='" + deptname + '\'' +
                '}';
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