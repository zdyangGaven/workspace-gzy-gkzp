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
    List<HrRecuritPlan> list( HrRecuritPlan hrRecuritPlan, String order,Page page);

    /**
     * 通过id查询计划
     * @param id
     * @return
     */
    public HrRecuritPlan getHrRecuritPlanById(int id);

    /**
     * 新增
     * @param hrRecuritPlan
     * @return
     */
    public void add(HrRecuritPlan hrRecuritPlan);

    /**
     * 修改
     * @param hrRecuritPlan
     * @return
     */
    public void edit(HrRecuritPlan hrRecuritPlan);
}
