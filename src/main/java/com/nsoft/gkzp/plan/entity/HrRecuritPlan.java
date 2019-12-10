package com.nsoft.gkzp.plan.entity;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class HrRecuritPlan implements Serializable {

    @Id
    private Integer id;

    private String years;//年份

    private String planName;//计招聘计划

    private Integer isreport;//开放上报

    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;//应聘开始时间

    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;//应聘结束时间

    private String affixfile;//附件

    private Date submittime;//提交时间

    private String submitman;//提交人



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years == null ? null : years.trim();
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public Integer getIsreport() {
        return isreport;
    }

    public void setIsreport(Integer isreport) {
        this.isreport = isreport;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getAffixfile() {
        return affixfile;
    }

    public void setAffixfile(String affixfile) {
        this.affixfile = affixfile == null ? null : affixfile.trim();
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    public String getSubmitman() {
        return submitman;
    }

    public void setSubmitman(String submitman) {
        this.submitman = submitman == null ? null : submitman.trim();
    }

    @Override
    public String toString() {
        return "HrRecuritPlan{" +
                "id=" + id +
                ", years='" + years + '\'' +
                ", planName='" + planName + '\'' +
                ", isreport=" + isreport +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", affixfile='" + affixfile + '\'' +
                ", submittime=" + submittime +
                ", submitman='" + submitman + '\'' +
                '}';
    }
}