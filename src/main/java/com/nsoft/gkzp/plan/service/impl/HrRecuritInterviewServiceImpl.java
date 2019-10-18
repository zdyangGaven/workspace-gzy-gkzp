package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecuritInterviewDao;
import com.nsoft.gkzp.plan.entity.HrRecuritInterview;
import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoBaseService;
import com.nsoft.gkzp.plan.service.HrRecuritInterviewService;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class HrRecuritInterviewServiceImpl implements HrRecuritInterviewService {
    @Autowired
    HrRecuritInterviewDao hrRecuritInterviewDao;

    @Autowired
    HrRecruitEntryinfoBaseService hrRecruitEntryinfoBaseService;

    @Override
    public List<HrRecuritInterview> list(HrRecuritInterview hrRecuritInterview, String order, Page page) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecuritInterview.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(hrRecuritInterview);

        List<HrRecuritInterview> list = hrRecuritInterviewDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecuritInterview> pageInfo = new PageInfo<HrRecuritInterview>(list);
        return list;
    }

    /**
     * 通过用户获取面试数据
     * @param userContext 用户
     * @return
     */
    @Override
    public HrRecuritInterview getHrRecuritInterviewByUser(UserContext userContext) {
        int baseId = hrRecruitEntryinfoBaseService.getBaseIdByUser(userContext);
        HrRecuritInterview hrRecuritInterview = new HrRecuritInterview();
        hrRecuritInterview.setBaseid(baseId);
        List<HrRecuritInterview> hrRecuritInterviews = list(hrRecuritInterview, "id DESC", null);
        //没有数据返回null
        if(hrRecuritInterviews.size() == 0) return null;
        return hrRecuritInterviews.get(0);
    }
}
