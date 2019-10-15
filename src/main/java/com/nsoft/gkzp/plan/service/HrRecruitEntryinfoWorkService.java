package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoWork;
import com.nsoft.gkzp.util.Page;

import java.util.List;

public interface HrRecruitEntryinfoWorkService {
    //工作经历

    /**
     *  查询
     * @param page 分页
     * @param hrRecruitEntryinfoWork
     * @param order 排序
     * @return
     */
    public List<HrRecruitEntryinfoWork> list(Page page, HrRecruitEntryinfoWork hrRecruitEntryinfoWork, String order);

    /**
     * 新增
     * @param hrRecruitEntryinfoWork
     * @return
     */
    public void add(List<HrRecruitEntryinfoWork> hrRecruitEntryinfoWork, int baseId);

    /**
     * 新增
     * @param hrRecruitEntryinfoWork
     */
    public void add(HrRecruitEntryinfoWork hrRecruitEntryinfoWork);

    /**
     * 修改
     * @param hrRecruitEntryinfoWork
     */
    public void edit(HrRecruitEntryinfoWork hrRecruitEntryinfoWork);
}
