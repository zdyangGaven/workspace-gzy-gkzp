package com.nsoft.gkzp.plan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

public class HrRecruitEntryinfoWork implements Serializable {
    @Id
    @Column(insertable=false)
    private Integer id;

    private Integer baseid;

    //不映射数据库字段
    @Transient
    private String[] duration;

    private String unit;

    private String post;

    private String leavereason;

    private String remarks;

    private Integer syncstatus;
    @JsonIgnore
    private Date starttime;
    @JsonIgnore
    private Date endtime;

    @Override
    public String toString() {
        return "HrRecruitEntryinfoWork{" +
                "id=" + id +
                ", baseid=" + baseid +
                ", duration='" + duration + '\'' +
                ", unit='" + unit + '\'' +
                ", post='" + post + '\'' +
                ", leavereason='" + leavereason + '\'' +
                ", remarks='" + remarks + '\'' +
                ", syncstatus=" + syncstatus +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                '}';
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public String getLeavereason() {
        return leavereason;
    }

    public void setLeavereason(String leavereason) {
        this.leavereason = leavereason == null ? null : leavereason.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}