package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfo;
import com.nsoft.gkzp.syscore.web.UserContext;
import org.json.JSONObject;

public interface HrRecruitEntryinfoBaseService {

    /**
     * 根据登录用户查询信息
     * @param userContext
     * @return
     */
    public HrRecruitEntryinfo getInfoByUser(UserContext userContext);

    /**
     * 基础信息新增
     * @param jsonObject
     * @return
     */
    public void add(JSONObject jsonObject);

}
