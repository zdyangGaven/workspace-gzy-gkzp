package com.nsoft.gkzp.notice.service;

import com.nsoft.gkzp.notice.entity.HrRecruitArticle;
import com.nsoft.gkzp.util.PageVo;

import java.util.List;

public interface HrRecruitArticleService {

    /**
     * 查询公告数据
     * @param page  分页
     * @param hrRecruitArticle
     * @param order 排序
     * @return
     */
    List<HrRecruitArticle> list(HrRecruitArticle hrRecruitArticle, String order, PageVo page);

    /**
     * 查询公告数据
     * @param hrRecruitArticle
     * @param order 排序
     * @param page 分页
     * @param typeList 类型的包含查询
     * @return
     */
    List<HrRecruitArticle> list(HrRecruitArticle hrRecruitArticle, String order, PageVo page,List<Object> typeList);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    HrRecruitArticle getHrRecruitArticleById(int id);

    /**
     * 新增
     * @param hrRecruitArticle
     *
     */
    void add(HrRecruitArticle hrRecruitArticle);

    /**
     * 修改
     * @param hrRecruitArticle
     */
    void edit(HrRecruitArticle hrRecruitArticle);
}
