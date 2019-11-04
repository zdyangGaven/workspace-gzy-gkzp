package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitHealthchk;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.PageVo;

import java.util.List;

public interface HrRecruitHealthchkService {
    /**
     *  查询
     * @param page 分页
     * @param hrRecruitHealthchk
     * @param order 排序
     * @return
     */
    public List<HrRecruitHealthchk> list(HrRecruitHealthchk hrRecruitHealthchk, String order, PageVo page);

    /**
     * 通过用户获取体检数据
     * @param userContext 用户
     * @return
     */
    public HrRecruitHealthchk getHrRecruitHealthchkByUser(UserContext userContext);

    /**
     * 通过基础信息id获取体检数据
     * @param baseId
     * @return
     */
    public HrRecruitHealthchk getHrRecruitHealthchkByBaseId(int baseId);

    /**
     * 新增
     * @param hrRecruitHealthchk
     */
    public void add(HrRecruitHealthchk hrRecruitHealthchk);

    /**
     * 修改
     * @param hrRecruitHealthchk
     */
    public void edit(HrRecruitHealthchk hrRecruitHealthchk);
}
