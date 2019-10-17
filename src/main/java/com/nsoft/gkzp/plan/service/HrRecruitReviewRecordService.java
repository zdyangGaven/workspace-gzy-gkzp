package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoBase;
import com.nsoft.gkzp.plan.entity.HrRecruitReviewRecord;
import com.nsoft.gkzp.plan.entity.HrRecruitReviewRecordVo;
import com.nsoft.gkzp.syscore.web.UserContext;
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
     * 通过基础信息的字段筛选获取资格审查和基础资料
     * @param hrRecruitEntryinfoBase
     * @return
     */
    public HrRecruitReviewRecordVo getHrRecruitReviewRecordVoByInfoBase(HrRecruitEntryinfoBase hrRecruitEntryinfoBase);

    /**
     * 通过用户获取资格审查和基础资料
     * @param userContext
     * @return
     */
    public HrRecruitReviewRecordVo getHrRecruitReviewRecordVoByUser(UserContext userContext);
}
