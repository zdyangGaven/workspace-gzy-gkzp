package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecuritPlanNeeds;
import com.nsoft.gkzp.util.Page;

import java.util.List;

public interface HrRecuritPlanNeedsService {
    //招聘技术人才需求

    /**
     * 查询数据
     * @param page 分页
     * @param hrRecuritPlanNeeds
     * @param order 排序
     * @return
     */
    List<HrRecuritPlanNeeds> list(Page page, HrRecuritPlanNeeds hrRecuritPlanNeeds, String order);
}
