package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecuritPlanDao;
import com.nsoft.gkzp.plan.entity.HrRecuritPlan;
import com.nsoft.gkzp.plan.service.HrRecuritPlanService;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HrRecuritPlanServiceImpl implements HrRecuritPlanService {
    @Autowired
    HrRecuritPlanDao hrRecuritPlanDao;

    /**
     * 计划的集合
     * @param page 分页
     * @param hrRecuritPlan
     * @param order 排序
     * @return
     */
    @Override
    public List<HrRecuritPlan> list(Page page, HrRecuritPlan hrRecuritPlan, String order) {

        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecuritPlan.class);
        //排序
        example.setOrderByClause(order);

        //开始时间和结束时间
        if(hrRecuritPlan.getStarttime() != null) example.createCriteria().andGreaterThanOrEqualTo("starttime",hrRecuritPlan.getStarttime());
        if(hrRecuritPlan.getEndtime() != null) example.createCriteria().andLessThanOrEqualTo("endtime",hrRecuritPlan.getEndtime());

        //筛选
        example.createCriteria().andEqualTo(hrRecuritPlan);


        List<HrRecuritPlan> list = hrRecuritPlanDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecuritPlan> pageInfo = new PageInfo<HrRecuritPlan>(list);
        return list;
    }
}
