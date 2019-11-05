package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecruitEntryinfoOtherDao;
import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoOther;
import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoOtherService;
import com.nsoft.gkzp.syscore.service.ServiceException;
import com.nsoft.gkzp.util.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HrRecruitEntryinfoOtherServiceImpl implements HrRecruitEntryinfoOtherService {
    @Autowired
    HrRecruitEntryinfoOtherDao hrRecruitEntryinfoOtherDao;
    //其他信息

    /**
     *
     * @param page 分页
     * @param hrRecruitEntryinfoOther
     * @param order 排序
     * @return
     */
    @Override
    public List<HrRecruitEntryinfoOther> list(HrRecruitEntryinfoOther hrRecruitEntryinfoOther, String order, PageVo page) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecruitEntryinfoOther.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(hrRecruitEntryinfoOther);

        List<HrRecruitEntryinfoOther> list = hrRecruitEntryinfoOtherDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecruitEntryinfoOther> pageInfo = new PageInfo<HrRecruitEntryinfoOther>(list);
        return list;
    }

    /**
     * 新增
     * @param hrRecruitEntryinfoOther
     * @return
     */
    @Override
    public void add(HrRecruitEntryinfoOther hrRecruitEntryinfoOther) {
        try {
            //id设为空
            hrRecruitEntryinfoOther.setId(null);
            //待同步
            hrRecruitEntryinfoOther.setSyncstatus(1);
            hrRecruitEntryinfoOtherDao.insertSelective(hrRecruitEntryinfoOther);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("报名-其他信息新增报错",e);
        }
    }

    /**
     * 修改
     * @param hrRecruitEntryinfoOther
     */
    @Override
    public void edit(HrRecruitEntryinfoOther hrRecruitEntryinfoOther) {
        try {
            hrRecruitEntryinfoOtherDao.updateByPrimaryKeySelective(hrRecruitEntryinfoOther);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("报名-其他信息修改报错",e);
        }
    }
}
