package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.UenumdataDao;
import com.nsoft.gkzp.plan.entity.Uenumdata;
import com.nsoft.gkzp.plan.service.UenumdataService;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UenumdataServiceImpl implements UenumdataService {
    @Autowired
    UenumdataDao uenumdataDao;

    @Override
    public List<Uenumdata> list(Uenumdata uenumdata, String order, Page page) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(Uenumdata.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(uenumdata);

        List<Uenumdata> list = uenumdataDao.selectByExample(example);

        // 取分页信息
        PageInfo<Uenumdata> pageInfo = new PageInfo<Uenumdata>(list);
        return list;
    }
}
