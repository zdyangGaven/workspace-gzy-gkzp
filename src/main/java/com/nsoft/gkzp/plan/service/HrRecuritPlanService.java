package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecuritPlan;
import com.nsoft.gkzp.util.Page;

import java.util.List;

public interface HrRecuritPlanService {
    /**
     * 计划的集合
     * @param page 分页
     * @param hrRecuritPlan
     * @param order 排序
     * @return
     */
    List<HrRecuritPlan> list(Page page, HrRecuritPlan hrRecuritPlan, String order);
}
