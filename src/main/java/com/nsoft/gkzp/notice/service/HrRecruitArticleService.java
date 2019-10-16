package com.nsoft.gkzp.notice.service;

import com.nsoft.gkzp.notice.entity.HrRecruitArticle;
import com.nsoft.gkzp.util.Page;

import java.util.List;

public interface HrRecruitArticleService {

    /**
     * 查询公告数据
     * @param pageNum 显示的页数
     * @param pageSize  每页显示的数目
     * @param hrRecruitArticle
     * @param order 排序
     * @return
     */
    List<HrRecruitArticle> list( HrRecruitArticle hrRecruitArticle, String order,Page page);
}
