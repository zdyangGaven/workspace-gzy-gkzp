package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoOther;
import com.nsoft.gkzp.util.Page;

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
    public List<HrRecruitEntryinfoOther> list(Page page, HrRecruitEntryinfoOther hrRecruitEntryinfoOther, String order);

    /**
     * 新增
     * @param hrRecruitEntryinfoOther
     * @return
     */
    public void add (HrRecruitEntryinfoOther hrRecruitEntryinfoOther);
}
