package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoEducation;
import com.nsoft.gkzp.util.Page;

import java.util.List;

public interface HrRecruitEntryinfoEducationService {
    //教育经历

    /**
     * 查询
     * @param page  分页
     * @param hrRecruitEntryinfoEducation
     * @param order 排序
     * @return
     */
    public List<HrRecruitEntryinfoEducation> list(Page page, HrRecruitEntryinfoEducation hrRecruitEntryinfoEducation, String order);

    /**
     * 新增
     * @param hrRecruitEntryinfoEducation
     * @return
     */
    public void add(List<HrRecruitEntryinfoEducation> hrRecruitEntryinfoEducation,int baseId);


}
