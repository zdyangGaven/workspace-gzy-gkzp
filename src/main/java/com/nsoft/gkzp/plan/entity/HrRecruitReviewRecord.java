package com.nsoft.gkzp.plan.entity;

import java.io.Serializable;
import java.util.Date;

public class HrRecruitReviewRecord implements Serializable {
    private Integer id;

    private Integer baseid;

    private Integer node;

    private String actionman;

    private Date actiontime;

    private Integer result;

    private String reason;

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