package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfo;
import org.json.JSONObject;

public interface HrRecruitEntryinfoBaseService {

    public HrRecruitEntryinfo getInfoByUser();

    /**
     * 基础信息新增
     * @param jsonObject
     * @return
     */
    public void add(JSONObject jsonObject);

}
