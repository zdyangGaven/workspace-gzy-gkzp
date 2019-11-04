package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecuritPlanDao;
import com.nsoft.gkzp.plan.entity.HrRecuritPlan;
import com.nsoft.gkzp.plan.service.HrRecuritPlanService;
import com.nsoft.gkzp.util.PageVo;
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
    public List<HrRecuritPlan> list(HrRecuritPlan hrRecuritPlan, String order, PageVo page) {

        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecuritPlan.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        Example.Criteria criteria = example.createCriteria();

        //开始时间小于参数和结束时间大于参数
        if(hrRecuritPlan.getEndtime() != null) criteria.andGreaterThanOrEqualTo("endtime",hrRecuritPlan.getEndtime());
        if(hrRecuritPlan.getStarttime() != null) criteria.andLessThanOrEqualTo("starttime",hrRecuritPlan.getStarttime());

        //清除时间
        hrRecuritPlan.setEndtime(null);
        hrRecuritPlan.setStarttime(null);

        //筛选
        criteria.andEqualTo(hrRecuritPlan);

        List<HrRecuritPlan> list = hrRecuritPlanDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecuritPlan> pageInfo = new PageInfo<HrRecuritPlan>(list);
        return list;
    }

    /**
     * 通过id查询计划
     * @param id
     * @return
     */
    @Override
    public HrRecuritPlan getHrRecuritPlanById(int id) {
        return hrRecuritPlanDao.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param hrRecuritPlan
     */
    @Override
    public void add(HrRecuritPlan hrRecuritPlan) {
        hrRecuritPlanDao.insertSelective(hrRecuritPlan);
    }

    /**
     * 修改
     * @param hrRecuritPlan
     */
    @Override
    public void edit(HrRecuritPlan hrRecuritPlan) {
        hrRecuritPlanDao.updateByPrimaryKeySelective(hrRecuritPlan);
    }
}
