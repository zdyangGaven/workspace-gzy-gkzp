package com.nsoft.gkzp.notice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.notice.dao.HrRecruitArticleDao;
import com.nsoft.gkzp.notice.entity.HrRecruitArticle;
import com.nsoft.gkzp.notice.service.HrRecruitArticleService;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HrRecruitArticleServiceImpl implements HrRecruitArticleService {

    @Autowired
    HrRecruitArticleDao hrRecruitArticleDao;

    /**
     * 查询公告数据
     * @param page 分页
     * @param hrRecruitArticle
     * @param order 排序
     * @return
     */
    @Override
    public List<HrRecruitArticle> list(Page page, HrRecruitArticle hrRecruitArticle, String order) {

        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecruitArticle.class);

        //排序
        example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(hrRecruitArticle);

        List<HrRecruitArticle> list = hrRecruitArticleDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecruitArticle> pageInfo = new PageInfo<HrRecruitArticle>(list);
        return list;
    }
}
