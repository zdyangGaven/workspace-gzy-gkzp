package com.nsoft.gkzp.plan.entity;

import java.io.Serializable;
import java.util.Date;

public class HrRecuritInterview implements Serializable {
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

    private String lounge;

    private String waitFor;

    private String skillTest;

    private String remark;

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

    public String getLounge() {
        return lounge;
    }

    public void setLounge(String lounge) {
        this.lounge = lounge == null ? null : lounge.trim();
    }

    public String getWaitFor() {
        return waitFor;
    }

    public void setWaitFor(String waitFor) {
        this.waitFor = waitFor == null ? null : waitFor.trim();
    }

    public String getSkillTest() {
        return skillTest;
    }

    public void setSkillTest(String skillTest) {
        this.skillTest = skillTest == null ? null : skillTest.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        sb.append(", interRoom=").append(interRoom);
        sb.append(", interTime=").append(interTime);
        sb.append(", status=").append(status);
        sb.append(", interScore=").append(interScore);
        sb.append(", interOrder=").append(interOrder);
        sb.append(", lounge=").append(lounge);
        sb.append(", waitFor=").append(waitFor);
        sb.append(", skillTest=").append(skillTest);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}