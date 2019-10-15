package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfo;
import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoBase;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.Page;
import org.json.JSONObject;

import java.util.List;

public interface HrRecruitEntryinfoBaseService {

    /**
     * 查询
     * @param page 分页
     * @param hrRecruitEntryinfoBase
     * @param order 排序
     * @return
     */
    public List<HrRecruitEntryinfoBase> list(Page page, HrRecruitEntryinfoBase hrRecruitEntryinfoBase, String order);


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

    /**
     * 根据用户来进行判断是否修改还是新增
     * @param jsonObject
     */
    public void edit(JSONObject jsonObject,UserContext userContext);

    /**
     * 修改编辑
     * @param jsonObject
     */
    public void edit(JSONObject jsonObject);
}
