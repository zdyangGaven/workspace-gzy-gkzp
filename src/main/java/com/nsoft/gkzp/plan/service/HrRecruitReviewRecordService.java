package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitReviewRecord;
import com.nsoft.gkzp.plan.entity.HrRecruitReviewRecordVo;
import com.nsoft.gkzp.util.Page;

import java.util.List;

public interface HrRecruitReviewRecordService {
    /**
     * 查询
     * @param hrRecruitReviewRecord
     * @param order
     * @param page
     * @return
     */
    public List<HrRecruitReviewRecord> list(HrRecruitReviewRecord hrRecruitReviewRecord, String order, Page page);

    /**
     * 通过姓名获取资格审查和基础资料
     * @param name
     * @return
     */
    public HrRecruitReviewRecordVo getHrRecruitReviewRecordVoByName(String name);
}
