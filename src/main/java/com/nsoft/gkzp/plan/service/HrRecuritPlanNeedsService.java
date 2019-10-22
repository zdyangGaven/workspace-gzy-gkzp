package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoBase;
import com.nsoft.gkzp.plan.entity.HrRecuritPlanNeeds;
import com.nsoft.gkzp.plan.entity.HrRecuritPlanNeedsDo;
import com.nsoft.gkzp.plan.entity.HrRecuritPlanNeedsVo;
import com.nsoft.gkzp.syscore.web.UserContext;
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
    public List<HrRecuritPlanNeeds> list( HrRecuritPlanNeeds hrRecuritPlanNeeds, String order,Page page);
    public List<HrRecuritPlanNeeds> list( HrRecuritPlanNeeds hrRecuritPlanNeeds, String order,Page page,List<Object> planIdList);

    public List<HrRecuritPlanNeedsDo> find(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order,Page page, List<Object> planIdList);

    /**
     * 根据id获取岗位信息和招聘计划
     * @param id 岗位id
     * @return
     */
    public HrRecuritPlanNeedsVo getHrRecuritPlanNeedsVoById(int id);

    /**
     * 根据登录用户获取岗位信息和招聘计划和基础信息
     * @param userContext 用户信息
     * @return
     */
    public HrRecuritPlanNeedsVo getHrRecuritPlanNeedsVoByUser(UserContext userContext);

    /**
     * 通过基础信息筛选查询岗位信息和招聘计划和基础信息
     * @param hrRecruitEntryinfoBase
     * @return
     */
    public HrRecuritPlanNeedsVo getHrRecuritPlanNeedsVoByHrRecruitEntryinfoBase(HrRecruitEntryinfoBase hrRecruitEntryinfoBase);

    /**
     *根据计划获取人才需求
     * @return
     */
    public List<HrRecuritPlanNeedsDo> getListByPlan(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order,Page page);
}