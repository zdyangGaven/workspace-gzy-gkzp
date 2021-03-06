package com.nsoft.gkzp.plan.entity;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class HrRecruitHealthchk implements Serializable {
    @Id
    private Integer id;

    private Integer planid;//计划ID

    private Integer baseid;//基本信息ID

    private Integer isjoin;//是否参与体检

    private Date actiontime1;//是否体检操作时间

    private String actionman1;//是否体检操作人

    private Integer issendnotice;//是否已发送通知

    private String actionman2;//通知发送人

    private Date actiontime2;//通知发送时间

    private Integer result;//体检结果

    private Date actiontime3;//体检结果操作时间

    private String actionman3;//体检结果操作人

    private Integer syncisjoin;//是否已同步

    public Integer getSyncisjoin() {
        return syncisjoin;
    }

    public void setSyncisjoin(Integer syncisjoin) {
        this.syncisjoin = syncisjoin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanid() {
        return planid;
    }

    public void setPlanid(Integer planid) {
        this.planid = planid;
    }

    public Integer getBaseid() {
        return baseid;
    }

    public void setBaseid(Integer baseid) {
        this.baseid = baseid;
    }

    public Integer getIsjoin() {
        return isjoin;
    }

    public void setIsjoin(Integer isjoin) {
        this.isjoin = isjoin;
    }

    public Date getActiontime1() {
        return actiontime1;
    }

    public void setActiontime1(Date actiontime1) {
        this.actiontime1 = actiontime1;
    }

    public String getActionman1() {
        return actionman1;
    }

    public void setActionman1(String actionman1) {
        this.actionman1 = actionman1 == null ? null : actionman1.trim();
    }

    public Integer getIssendnotice() {
        return issendnotice;
    }

    public void setIssendnotice(Integer issendnotice) {
        this.issendnotice = issendnotice;
    }

    public String getActionman2() {
        return actionman2;
    }

    public void setActionman2(String actionman2) {
        this.actionman2 = actionman2 == null ? null : actionman2.trim();
    }

    public Date getActiontime2() {
        return actiontime2;
    }

    public void setActiontime2(Date actiontime2) {
        this.actiontime2 = actiontime2;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getActiontime3() {
        return actiontime3;
    }

    public void setActiontime3(Date actiontime3) {
        this.actiontime3 = actiontime3;
    }

    public String getActionman3() {
        return actionman3;
    }

    public void setActionman3(String actionman3) {
        this.actionman3 = actionman3 == null ? null : actionman3.trim();
    }
}