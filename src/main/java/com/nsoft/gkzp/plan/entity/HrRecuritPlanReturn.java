package com.nsoft.gkzp.plan.entity;

public class HrRecuritPlanReturn {
    private Integer id;

    private String planId;//招聘计划id

    private String returnMan;//退回人

    private Integer returnTime;//退回时间

    private String returnMess;//退回信息

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getReturnMan() {
        return returnMan;
    }

    public void setReturnMan(String returnMan) {
        this.returnMan = returnMan == null ? null : returnMan.trim();
    }

    public Integer getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Integer returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnMess() {
        return returnMess;
    }

    public void setReturnMess(String returnMess) {
        this.returnMess = returnMess == null ? null : returnMess.trim();
    }
}