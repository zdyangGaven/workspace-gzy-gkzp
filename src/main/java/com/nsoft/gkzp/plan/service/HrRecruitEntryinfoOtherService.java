package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoOther;
import com.nsoft.gkzp.util.PageVo;

import java.util.List;

public interface HrRecruitEntryinfoOtherService {
    //其他信息

    /**
     * 查询
     * @param page 分页
     * @param hrRecruitEntryinfoOther
     * @param order 排序
     * @return
     */
    public List<HrRecruitEntryinfoOther> list(HrRecruitEntryinfoOther hrRecruitEntryinfoOther, String order, PageVo page);

    /**
     * 新增
     * @param hrRecruitEntryinfoOther
     * @return
     */
    public void add (HrRecruitEntryinfoOther hrRecruitEntryinfoOther);

    /**
     * 修改
     * @param hrRecruitEntryinfoOther
     */
    public void edit(HrRecruitEntryinfoOther hrRecruitEntryinfoOther);
}
