package com.nsoft.gkzp.plan.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class HrRecruitEntryinfoEducation implements Serializable {
    @Id
    @Column(insertable=false)
    private Integer id;

    private Integer baseid;

    private String duration;

    private String trainingagency;

    private String major;

    private String inform;

    private Integer syncstatus;

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
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