package com.nsoft.gkzp.plan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

public class HrRecruitEntryinfoEducation implements Serializable {
    @Id
    @Column(insertable=false)
    private Integer id;

    private Integer baseid;//基础信息ID

    //不映射数据库字段
    @Transient
    private String[] duration;

    private String trainingagency;//培训机构

    private String major;//专业

    private String inform;//形式

    private Integer syncstatus;//是否已同步

    @JsonIgnore
    private Date starttime;//开始时间

    @JsonIgnore
    private Date endtime;//结束时间

    private String degrees;//取得学历

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

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }

    public Integer getSyncstatus() {
        return syncstatus;
    }

    public void setSyncstatus(Integer syncstatus) {
        this.syncstatus = syncstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBaseid() {
        return baseid;
    }

    public void setBaseid(Integer baseid) {
        this.baseid = baseid;
    }

    public String[] getDuration() {
        return duration;
    }

    public void setDuration(String[] duration) {
        this.duration = duration;
    }

    public String getTrainingagency() {
        return trainingagency;
    }

    public void setTrainingagency(String trainingagency) {
        this.trainingagency = trainingagency == null ? null : trainingagency.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getInform() {
        return inform;
    }

    public void setInform(String inform) {
        this.inform = inform == null ? null : inform.trim();
    }

    @Override
    public String toString() {
        return "HrRecruitEntryinfoEducation{" +
                "id=" + id +
                ", baseid=" + baseid +
                ", duration='" + duration + '\'' +
                ", trainingagency='" + trainingagency + '\'' +
                ", major='" + major + '\'' +
                ", inform='" + inform + '\'' +
                '}';
    }
}