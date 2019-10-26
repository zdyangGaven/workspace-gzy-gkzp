package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrRecuritWrite;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.Page;

import java.util.List;

public interface HrRecuritWriteService {
    /**
     *  查询
     * @param page 分页
     * @param hrRecuritWrite
     * @param order 排序
     * @return
     */
    public List<HrRecuritWrite> list(HrRecuritWrite hrRecuritWrite, String order, Page page);

    /**
     * 通过用户获取笔试数据
     * @param userContext 用户
     * @return
     */
    public HrRecuritWrite getHrRecuritWriteByUser(UserContext userContext);

    /**
     * 通过基础信息id获取笔试数据
     * @param baseId
     * @return
     */
    public HrRecuritWrite getHrRecuritWriteByBaseId(int baseId);

    /**
     * 新增
     * @param hrRecuritWrite
     * @return
     */
    public void add(HrRecuritWrite hrRecuritWrite);

    /**
     * 修改
     * @param hrRecuritWrite
     * @return
     */
    public void edit(HrRecuritWrite hrRecuritWrite);
}
