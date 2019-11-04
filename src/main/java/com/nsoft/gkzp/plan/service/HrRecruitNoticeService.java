package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitNotice;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.PageVo;

import java.util.List;

public interface HrRecruitNoticeService {
    /**
     *  查询
     * @param page 分页
     * @param hrRecruitNotice
     * @param order 排序
     * @return
     */
    public List<HrRecruitNotice> list(HrRecruitNotice hrRecruitNotice, String order, PageVo page);

    /**
     * 未读数
     * @param user
     * @return
     */
    public int noticeInt(UserContext user);

    /**
     * 单个设为阅读
     * @param id
     * @return
     */
    public void read(int id);

    /**
     * 用户的所有消息设为已读
     * @param userContext
     */
    public void userReadAll(UserContext userContext);
}
