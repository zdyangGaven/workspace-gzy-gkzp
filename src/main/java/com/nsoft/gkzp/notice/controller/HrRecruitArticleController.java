package com.nsoft.gkzp.notice.controller;

import com.nsoft.gkzp.notice.entity.HrRecruitArticle;
import com.nsoft.gkzp.notice.service.HrRecruitArticleService;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HrRecruitArticleController {

    @Autowired
    HrRecruitArticleService hrRecruitArticleService;

    /**
     * 查询公告数据
     * @param page 分页
     * @param hrRecruitArticle
     * @param order 排序
     * @return
     */
    @RequestMapping("/HrRecruitArticleController/list")
    public List<HrRecruitArticle> list( HrRecruitArticle hrRecruitArticle, String order,Page page){
        return hrRecruitArticleService.list(hrRecruitArticle,order, page);
    }

    @RequestMapping("notice/getHrRecruitArticleById")
    public HrRecruitArticle getHrRecruitArticleById(int id){
        return hrRecruitArticleService.getHrRecruitArticleById(id);
    }
}
