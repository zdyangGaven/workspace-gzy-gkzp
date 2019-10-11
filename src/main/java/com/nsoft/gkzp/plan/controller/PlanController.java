package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.plan.entity.HrPostType;
import com.nsoft.gkzp.plan.entity.HrRecuritPlan;
import com.nsoft.gkzp.plan.entity.HrRecuritPlanNeeds;
import com.nsoft.gkzp.plan.service.HrPostTypeService;
import com.nsoft.gkzp.plan.service.HrRecuritPlanNeedsService;
import com.nsoft.gkzp.plan.service.HrRecuritPlanService;
import com.nsoft.gkzp.syscore.web.AbstractController;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlanController extends AbstractController {
    @Autowired
    HrRecuritPlanNeedsService hrRecuritPlanNeedsService;

    @Autowired
    HrRecuritPlanService hrRecuritPlanService;

    @Autowired
    HrPostTypeService hrPostTypeService;

    /**
     * 招聘类别查询
     * @param page 分页
     * @param hrPostType
     * @param order 排序
     * @return
     */
    @RequestMapping("HrPostTypeController/list")
    public List<HrPostType> hrPostTypeControllerList(Page page, HrPostType hrPostType, String order){
        return hrPostTypeService.list(page,hrPostType,order);
    }

    /**
     * 计划的集合
     * @param page 分页
     * @return
     */
    @RequestMapping("HrRecuritPlanController/list")
    public List<HrRecuritPlan> hrRecuritPlanList(Page page, HrRecuritPlan hrRecuritPlan, String order){
        return hrRecuritPlanService.list(page,hrRecuritPlan,order);
    }

    //招聘技术人才需求
    /**
     * 查询数据
     * @param page 分页
     * @param hrRecuritPlanNeeds
     * @param order 排序
     * @return
     */
    @RequestMapping("HrRecuritPlanNeedsController/list")
    public List<HrRecuritPlanNeeds> hrRecuritPlanNeedsList(Page page, HrRecuritPlanNeeds hrRecuritPlanNeeds, String order){
        return hrRecuritPlanNeedsService.list(page,hrRecuritPlanNeeds,order);
    }
}
