package com.nsoft.gkzp.plan.entity;

import java.io.Serializable;

public class HrRecuritPlanNeedsVo implements Serializable {
    //基础信息
    public HrRecruitEntryinfoBase hrRecruitEntryinfoBase;
    //招聘计划
    public HrRecuritPlan hrRecuritPlan;
    //招聘人才需求
    public HrRecuritPlanNeeds hrRecuritPlanNeeds;


    @Override
    public String toString() {
        return "HrRecuritPlanNeedsVo{" +
                "hrRecruitEntryinfoBase=" + hrRecruitEntryinfoBase +
                ", hrRecuritPlan=" + hrRecuritPlan +
                ", hrRecuritPlanNeeds=" + hrRecuritPlanNeeds +
                '}';
    }

    public HrRecruitEntryinfoBase getHrRecruitEntryinfoBase() {
        return hrRecruitEntryinfoBase;
    }

    public void setHrRecruitEntryinfoBase(HrRecruitEntryinfoBase hrRecruitEntryinfoBase) {
        this.hrRecruitEntryinfoBase = hrRecruitEntryinfoBase;
    }

    public HrRecuritPlan getHrRecuritPlan() {
        return hrRecuritPlan;
    }

    public void setHrRecuritPlan(HrRecuritPlan hrRecuritPlan) {
        this.hrRecuritPlan = hrRecuritPlan;
    }

    public HrRecuritPlanNeeds getHrRecuritPlanNeeds() {
        return hrRecuritPlanNeeds;
    }

    public void setHrRecuritPlanNeeds(HrRecuritPlanNeeds hrRecuritPlanNeeds) {
        this.hrRecuritPlanNeeds = hrRecuritPlanNeeds;
    }
}