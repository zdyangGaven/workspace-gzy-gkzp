package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrPostTypeDao;
import com.nsoft.gkzp.plan.entity.HrPostType;
import com.nsoft.gkzp.plan.service.HrPostTypeService;
import com.nsoft.gkzp.util.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HrPostTypeServiceImpl implements HrPostTypeService {
    @Autowired
    HrPostTypeDao hrPostTypeDao;

    /**
     * 招聘类别查询
     * @param page 分页
     * @param hrPostType
     * @param order 排序
     * @return
     */
    @Override
    public List<HrPostType> list(HrPostType hrPostType, String order, PageVo page) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrPostType.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(hrPostType);

        List<HrPostType> list = hrPostTypeDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrPostType> pageInfo = new PageInfo<HrPostType>(list);
        return list;
    }

    /**
     * 新增
     * @param hrPostType
     */
    @Override
    public void add(HrPostType hrPostType) {
        hrPostTypeDao.insertSelective(hrPostType);
    }

    /**
     * 修改
     * @param hrPostType
     */
    @Override
    public void edit(HrPostType hrPostType) {
        hrPostTypeDao.updateByPrimaryKeySelective(hrPostType);
    }
}
