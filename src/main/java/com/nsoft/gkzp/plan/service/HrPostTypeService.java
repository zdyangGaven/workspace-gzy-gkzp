package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.HrPostType;
import com.nsoft.gkzp.util.PageVo;

import java.util.List;

public interface HrPostTypeService {
    /**
     * 招聘类别查询
     * @param page 分页
     * @param hrPostType
     * @param order 排序
     * @return
     */
    List<HrPostType> list(HrPostType hrPostType, String order, PageVo page);

    /**
     * 通过类型查询子类
     * @param typeString
     * @return
     */
    List<HrPostType> getPostTypeByTypeString(String typeString);

    /**
     * 新增
     * @param hrPostType
     */
    void add(HrPostType hrPostType);

    /**
     * 修改
     * @param hrPostType
     */
    void edit(HrPostType hrPostType);
}
