package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.plan.entity.HrRecuritPlanNeeds;
import com.nsoft.gkzp.plan.service.HrRecuritPlanNeedsService;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HrRecuritPlanNeedsController {
    @Autowired
    HrRecuritPlanNeedsService hrRecuritPlanNeedsService;

    //招聘技术人才需求
    /**
     * 查询数据
     * @param page 分页
     * @param hrRecuritPlanNeeds
     * @param order 排序
     * @return
     */
    @ResponseBody
    @RequestMapping("HrRecuritPlanNeedsController/list")
    public List<HrRecuritPlanNeeds> list(Page page, HrRecuritPlanNeeds hrRecuritPlanNeeds, String order){
        return hrRecuritPlanNeedsService.list(page,hrRecuritPlanNeeds,order);
    }


}
