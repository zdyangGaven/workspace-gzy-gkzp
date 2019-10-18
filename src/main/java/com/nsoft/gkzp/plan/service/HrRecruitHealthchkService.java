package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitHealthchk;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.Page;

import java.util.List;

public interface HrRecruitHealthchkService {
    /**
     *  查询
     * @param page 分页
     * @param hrRecruitHealthchk
     * @param order 排序
     * @return
     */
    public List<HrRecruitHealthchk> list(HrRecruitHealthchk hrRecruitHealthchk, String order, Page page);

    /**
     * 通过用户获取体检数据
     * @param userContext 用户
     * @return
     */
    public HrRecruitHealthchk getHrRecruitHealthchkByUser(UserContext userContext);
}
