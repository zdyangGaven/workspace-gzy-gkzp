package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoBase;
import com.nsoft.gkzp.plan.entity.HrRecuritPlanNeeds;
import com.nsoft.gkzp.plan.entity.HrRecuritPlanNeedsDo;
import com.nsoft.gkzp.plan.entity.HrRecuritPlanNeedsVo;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.PageVo;
import com.nsoft.gkzp.util.ResultMsg;

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
    public List<HrRecuritPlanNeeds> list(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order, PageVo page);

    /**
     * 查询数据
     * @param hrRecuritPlanNeeds
     * @param order 排序
     * @param page 分页
     * @param planIdList 计划id 包含查询
     * @return
     */
    public List<HrRecuritPlanNeeds> list(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order, PageVo page, List<Object> planIdList);

    /**
     * 通过id查询岗位
     * @param id
     * @return
     */
    public HrRecuritPlanNeeds getHrRecuritPlanNeedsById(int id);

    /**
     * 查询关联岗位类别
     * @param hrRecuritPlanNeeds
     * @param order
     * @param page
     * @param planIdList
     * @return
     */
    public List<HrRecuritPlanNeedsDo> find(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order, PageVo page, List<Integer> planIdList);

    /**
     * 通过id查询岗位 包括关联类别表的信息
     * @param id
     * @return
     */
    public HrRecuritPlanNeedsDo findById(int id);

    /**
     * 申请职位  获取用户的当前状态  是否申请职位，申请职位时间
     * @param userContext 用户信息
     * @return
     */
    public ResultMsg getApplyByUser(UserContext userContext);

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
    public List<HrRecuritPlanNeedsDo> getListByPlan(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order, PageVo page);

    /**
     * 新增
     * @param hrRecuritPlanNeeds
     */
    public void add(HrRecuritPlanNeeds hrRecuritPlanNeeds);

    /**
     * 修改
     * @param hrRecuritPlanNeeds
     */
    public void edit(HrRecuritPlanNeeds hrRecuritPlanNeeds);

    /**
     * 申请岗位
     * @param id 岗位id
     * @param userContext 登录用户
     */
    public void planNeedsApply(int id,UserContext userContext);
}
