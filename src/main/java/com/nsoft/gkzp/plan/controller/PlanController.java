package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.plan.entity.HrPostType;
import com.nsoft.gkzp.plan.entity.HrRecuritPlan;
import com.nsoft.gkzp.plan.entity.HrRecuritPlanNeeds;
import com.nsoft.gkzp.plan.entity.HrRecuritPlanNeedsVo;
import com.nsoft.gkzp.plan.service.HrPostTypeService;
import com.nsoft.gkzp.plan.service.HrRecuritPlanNeedsService;
import com.nsoft.gkzp.plan.service.HrRecuritPlanService;
import com.nsoft.gkzp.syscore.web.AbstractController;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.DataFormat;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
public class PlanController extends AbstractController {
    @Autowired
    HrRecuritPlanNeedsService hrRecuritPlanNeedsService;

    @Autowired
    HrRecuritPlanService hrRecuritPlanService;

    @Autowired
    HrPostTypeService hrPostTypeService;

    @Autowired
    DataFormat dataFormat;

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
    @RequestMapping("plan/getPlanByCurrentTime")
    public List<HrRecuritPlan> getPlanByCurrentTime(Page page, HrRecuritPlan hrRecuritPlan, String order){
        //传入当前时间
        hrRecuritPlan.setStarttime(new Date());
        hrRecuritPlan.setEndtime(new Date());
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

    /**
     * 根据计划获取人才需求
     * @return
     */
    @RequestMapping("plan/getPlanNeedsListByPlan")
    public List<HrRecuritPlanNeeds> getPlanNeedsListByPlan(){
        return hrRecuritPlanNeedsService.getListByPlan();
    }

    /**
     * 根据登录用户获取岗位信息和招聘计划
     * @param request
     * @return
     */
    @RequestMapping("plan/getHrRecuritPlanNeedsVoByUser")
    public HrRecuritPlanNeedsVo getHrRecuritPlanNeedsVoByUser(HttpServletRequest request) {
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        return hrRecuritPlanNeedsService.getHrRecuritPlanNeedsVoByUser(userContext);
    }
}
