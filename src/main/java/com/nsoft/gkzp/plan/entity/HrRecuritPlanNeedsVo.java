package com.nsoft.gkzp.plan.entity;

import java.io.Serializable;

public class HrRecuritPlanNeedsVo implements Serializable {
    //招聘计划
    public HrRecuritPlan hrRecuritPlan;
    //招聘人才需求
    public HrRecuritPlanNeeds hrRecuritPlanNeeds;

    @Override
    public String toString() {
        return "HrRecuritPlanNeedsVo{" +
                "hrRecuritPlan=" + hrRecuritPlan +
                ", hrRecuritPlanNeeds=" + hrRecuritPlanNeeds +
                '}';
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