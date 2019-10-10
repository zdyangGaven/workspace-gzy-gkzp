package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoFamily;
import com.nsoft.gkzp.util.Page;

import java.util.List;

public interface HrRecruitEntryinfoFamilyService {
    //家庭成员

    /**
     * 查询
     * @param page 分页
     * @param hrRecruitEntryinfoFamily
     * @param order 排序
     * @return
     */
    public List<HrRecruitEntryinfoFamily> list(Page page, HrRecruitEntryinfoFamily hrRecruitEntryinfoFamily, String order);

    /**
     * 新增
     * @param hrRecruitEntryinfoFamily
     * @param baseId  基础信息id
     * @return
     */
    public void add(List<HrRecruitEntryinfoFamily> hrRecruitEntryinfoFamily, int baseId);


}
