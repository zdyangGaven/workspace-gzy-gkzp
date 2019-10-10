package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecuritPlanNeedsDao;
import com.nsoft.gkzp.plan.entity.HrRecuritPlanNeeds;
import com.nsoft.gkzp.plan.service.HrRecuritPlanNeedsService;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HrRecuritPlanNeedsServiceImpl implements HrRecuritPlanNeedsService {

    @Autowired
    HrRecuritPlanNeedsDao hrRecuritPlanNeedsDao;


    //招聘技术人才需求
    /**
     * 查询数据
     * @param page 分页
     * @param hrRecuritPlanNeeds
     * @param order 排序
     * @return
     */
    @Override
    public List<HrRecuritPlanNeeds> list(Page page, HrRecuritPlanNeeds hrRecuritPlanNeeds, String order) {

        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecuritPlanNeeds.class);
        //排序
        example.setOrderByClause(order);

        //模糊筛选
        if(hrRecuritPlanNeeds.getPostname() != null) example.createCriteria().andLike("postname","%"+hrRecuritPlanNeeds.getPostname()+"%");

        //筛选
        example.createCriteria().andEqualTo(hrRecuritPlanNeeds);

        List<HrRecuritPlanNeeds> list = hrRecuritPlanNeedsDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecuritPlanNeeds> pageInfo = new PageInfo<HrRecuritPlanNeeds>(list);
        return list;
    }
}
