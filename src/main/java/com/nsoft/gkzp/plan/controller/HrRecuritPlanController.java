package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.plan.entity.HrRecuritPlan;
import com.nsoft.gkzp.plan.service.HrRecuritPlanService;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HrRecuritPlanController {
    @Autowired
    HrRecuritPlanService hrRecuritPlanService;

    /**
     * 计划的集合
     * @param page 分页
     * @return
     */
    @ResponseBody
    @RequestMapping("HrRecuritPlanController/list")
    public List<HrRecuritPlan> list(Page page, HrRecuritPlan hrRecuritPlan, String order){
        System.out.println("--------------"+hrRecuritPlan);
        return hrRecuritPlanService.list(page,hrRecuritPlan,order);
    }
}
