package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecuritInterview;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.PageVo;

import java.util.List;

public interface HrRecuritInterviewService {
    /**
     *  查询
     * @param page 分页
     * @param hrRecuritInterview
     * @param order 排序
     * @return
     */
    public List<HrRecuritInterview> list(HrRecuritInterview hrRecuritInterview, String order, PageVo page);

    /**
     * 通过用户获取面试数据
     * @param userContext 用户
     * @return
     */
    public HrRecuritInterview getHrRecuritInterviewByUser(UserContext userContext);

    /**
     * 通过基础信息id获取面试数据
     * @param baseId
     * @return
     */
    public HrRecuritInterview getHrRecuritInterviewByBaseId(int baseId);

    /**
     * 新增
     * @param hrRecuritInterview
     */
    public void add(HrRecuritInterview hrRecuritInterview);

    /**
     * 修改
     * @param hrRecuritInterview
     */
    public void edit(HrRecuritInterview hrRecuritInterview);
}
