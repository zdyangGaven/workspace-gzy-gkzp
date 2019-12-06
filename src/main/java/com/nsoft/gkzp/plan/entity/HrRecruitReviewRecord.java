package com.nsoft.gkzp.plan.entity;

import java.io.Serializable;
import java.util.Date;

public class HrRecruitReviewRecord implements Serializable {
    private Integer id;

    private Integer baseid;//基本信息ID

    private Integer node;//环节

    private String actionman;//审查人

    private Date actiontime;//审查时间

    private Integer result;//审查结果

    private String reason;//原因

    @Override
    public String toString() {
        return "HrRecruitReviewRecord{" +
                "id=" + id +
                ", baseid=" + baseid +
                ", node=" + node +
                ", actionman='" + actionman + '\'' +
                ", actiontime=" + actiontime +
                ", result=" + result +
                ", reason='" + reason + '\'' +
                '}';
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

    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
        this.node = node;
    }

    public String getActionman() {
        return actionman;
    }

    public void setActionman(String actionman) {
        this.actionman = actionman == null ? null : actionman.trim();
    }

    public Date getActiontime() {
        return actiontime;
    }

    public void setActiontime(Date actiontime) {
        this.actiontime = actiontime;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
}