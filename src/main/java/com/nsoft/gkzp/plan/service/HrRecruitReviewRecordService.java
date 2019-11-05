package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoBase;
import com.nsoft.gkzp.plan.entity.HrRecruitReviewRecord;
import com.nsoft.gkzp.plan.entity.HrRecruitReviewRecordVo;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.PageVo;

import java.util.List;

public interface HrRecruitReviewRecordService {
    /**
     * 查询
     * @param hrRecruitReviewRecord
     * @param order
     * @param page
     * @return
     */
    public List<HrRecruitReviewRecord> list(HrRecruitReviewRecord hrRecruitReviewRecord, String order, PageVo page);

    /**
     * 通过基础信息id获取资格审核数据
     * @param baseId
     * @return
     */
    public HrRecruitReviewRecord getHrRecruitReviewRecordByBaseId(int baseId);

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

    /**
     * 是否审核
     * @param baseId 基础信息id
     * @return
     */
    public boolean isReview(int baseId);

    /**
     * 新增
     * @param hrRecruitReviewRecord
     */
    public void add(HrRecruitReviewRecord hrRecruitReviewRecord);

    /**
     * 修改
     * @param hrRecruitReviewRecord
     */
    public void edit(HrRecruitReviewRecord hrRecruitReviewRecord);
}
