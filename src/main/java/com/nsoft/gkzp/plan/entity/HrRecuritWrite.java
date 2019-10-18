package com.nsoft.gkzp.plan.entity;

import java.util.Date;

public class HrRecuritWrite {
    private Integer id;

    private Integer planid;

    private Integer baseid;

    private Integer sex;

    private Integer postid;

    private Integer posttypeid;

    private String zkzNumber;

    private String examRoom;

    private Date examTime;

    private Integer status;

    private String examScore;

    private String examOrder;

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

    public String getExamRoom() {
        return examRoom;
    }

    public void setExamRoom(String examRoom) {
        this.examRoom = examRoom == null ? null : examRoom.trim();
    }

    public Date getExamTime() {
        return examTime;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExamScore() {
        return examScore;
    }

    public void setExamScore(String examScore) {
        this.examScore = examScore == null ? null : examScore.trim();
    }

    public String getExamOrder() {
        return examOrder;
    }

    public void setExamOrder(String examOrder) {
        this.examOrder = examOrder == null ? null : examOrder.trim();
    }
}