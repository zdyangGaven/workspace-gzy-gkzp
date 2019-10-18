package com.nsoft.gkzp.plan.entity;

import java.util.Date;

public class HrRecuritInterview {
    private Integer id;

    private Integer planid;

    private Integer baseid;

    private Integer sex;

    private Integer postid;

    private Integer posttypeid;

    private String zkzNumber;

    private String interRoom;

    private Date interTime;

    private Integer status;

    private String interScore;

    private String interOrder;

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public Integer getPosttypeid() {
        return posttypeid;
    }

    public void setPosttypeid(Integer posttypeid) {
        this.posttypeid = posttypeid;
    }

    public String getZkzNumber() {
        return zkzNumber;
    }

    public void setZkzNumber(String zkzNumber) {
        this.zkzNumber = zkzNumber == null ? null : zkzNumber.trim();
    }

    public String getInterRoom() {
        return interRoom;
    }

    public void setInterRoom(String interRoom) {
        this.interRoom = interRoom == null ? null : interRoom.trim();
    }

    public Date getInterTime() {
        return interTime;
    }

    public void setInterTime(Date interTime) {
        this.interTime = interTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInterScore() {
        return interScore;
    }

    public void setInterScore(String interScore) {
        this.interScore = interScore == null ? null : interScore.trim();
    }

    public String getInterOrder() {
        return interOrder;
    }

    public void setInterOrder(String interOrder) {
        this.interOrder = interOrder == null ? null : interOrder.trim();
    }
}