package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoEducation;
import com.nsoft.gkzp.util.PageVo;

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
    public List<HrRecruitEntryinfoEducation> list(HrRecruitEntryinfoEducation hrRecruitEntryinfoEducation, String order, PageVo page);

    /**
     * 新增
     * @param hrRecruitEntryinfoEducation
     * @return
     */
    public void add(List<HrRecruitEntryinfoEducation> hrRecruitEntryinfoEducation,int baseId);

    /**
     * 新增
     * @param hrRecruitEntryinfoEducation
     */
    public void add(HrRecruitEntryinfoEducation hrRecruitEntryinfoEducation);

    /**
     * 修改
     * @param hrRecruitEntryinfoEducation
     */
    public void edit(HrRecruitEntryinfoEducation hrRecruitEntryinfoEducation);
}
