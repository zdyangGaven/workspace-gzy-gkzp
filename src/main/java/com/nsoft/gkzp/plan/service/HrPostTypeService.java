package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrPostType;
import com.nsoft.gkzp.util.Page;

import java.util.List;

public interface HrPostTypeService {
    /**
     * 招聘类别查询
     * @param page 分页
     * @param hrPostType
     * @param order 排序
     * @return
     */
    List<HrPostType> list(HrPostType hrPostType, String order, Page page);
}
