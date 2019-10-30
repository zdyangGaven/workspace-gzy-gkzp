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
    public List<HrRecruitEntryinfoBase> list( HrRecruitEntryinfoBase hrRecruitEntryinfoBase, String order,Page page);


    /**
     * 根据登录用户查询信息
     * @param userContext
     * @return
     */
    public HrRecruitEntryinfo getInfoByUser(UserContext userContext);

    /**
     * 根据用户获取基础信息
     * @param userContext
     * @return
     */
    public HrRecruitEntryinfoBase getBaseByUser(UserContext userContext);

    /**
     * 根据登录用户获取基础信息id
     * @param userContext 用户信息
     * @return
     */
    public int getBaseIdByUser(UserContext userContext);

    /**
     * 身份证验证
     * @param idCard
     * @return
     */
    public boolean verifyIdCard(String idCard);

    /**
     * 用户信息新增
     * @param jsonObject
     * @return
     */
    public void add(JSONObject jsonObject,UserContext userContext);


    /**
     * 根据用户来进行判断是否修改还是新增
     * @param jsonObject
     */
    public void edit(JSONObject jsonObject,UserContext userContext);

    /**
     * 修改全部编辑
     * @param jsonObject
     */
    public void edit(JSONObject jsonObject);

    /**
     * 修改编辑
     * @param hrRecruitEntryinfoBase
     */
    public void edit(HrRecruitEntryinfoBase hrRecruitEntryinfoBase);
}
