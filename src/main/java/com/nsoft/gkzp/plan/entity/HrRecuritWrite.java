package com.nsoft.gkzp.plan.entity;

import java.io.Serializable;
import java.util.Date;

public class HrRecuritWrite implements Serializable {
    private Integer id;

    private Integer planid;//招聘计划

    private Integer baseid;//基础id

    private Integer sex;//性别

    private Integer postid;//岗位id

    private Integer posttypeid;//岗位类别

    private String zkzNumber;//准考证号

    private String examRoom;//考试场地

    private Date examTime;//考试时间

    private Integer status;//状态

    private String examScore;//分数

    private String examOrder;//分数排名

    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", planid=").append(planid);
        sb.append(", baseid=").append(baseid);
        sb.append(", sex=").append(sex);
        sb.append(", postid=").append(postid);
        sb.append(", posttypeid=").append(posttypeid);
        sb.append(", zkzNumber=").append(zkzNumber);
        sb.append(", examRoom=").append(examRoom);
        sb.append(", examTime=").append(examTime);
        sb.append(", status=").append(status);
        sb.append(", examScore=").append(examScore);
        sb.append(", examOrder=").append(examOrder);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}