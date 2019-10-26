package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecruitEntryinfoEducationDao;
import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoEducation;
import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoEducationService;
import com.nsoft.gkzp.syscore.service.AbstractService;
import com.nsoft.gkzp.syscore.service.ServiceException;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class HrRecruitEntryinfoEducationServiceImpl extends AbstractService implements HrRecruitEntryinfoEducationService {
    @Autowired
    HrRecruitEntryinfoEducationDao hrRecruitEntryinfoEducationDao;
    //教育经历



    /**
     *
     * @param page  分页
     * @param hrRecruitEntryinfoEducation
     * @param order 排序
     * @return
     */
    @Override
    public List<HrRecruitEntryinfoEducation> list( HrRecruitEntryinfoEducation hrRecruitEntryinfoEducation, String order,Page page) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecruitEntryinfoEducation.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(hrRecruitEntryinfoEducation);

        List<HrRecruitEntryinfoEducation> list = hrRecruitEntryinfoEducationDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecruitEntryinfoEducation> pageInfo = new PageInfo<HrRecruitEntryinfoEducation>(list);
        return list;
    }

    /**
     * 新增
     *
     * @param hrRecruitEntryinfoEducations
     * @return
     */
    @Override
    public void add(List<HrRecruitEntryinfoEducation> hrRecruitEntryinfoEducations,int baseId) {
        try {
            for (HrRecruitEntryinfoEducation hrRecruitEntryinfoEducation:hrRecruitEntryinfoEducations) {
                //添加基础id进行关联
                hrRecruitEntryinfoEducation.setBaseid(baseId);
                //id设为空
                hrRecruitEntryinfoEducation.setId(null);
                //待同步
                hrRecruitEntryinfoEducation.setSyncstatus(1);
                //新增
                hrRecruitEntryinfoEducationDao.insertSelective(hrRecruitEntryinfoEducation);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("报名-教育经历新增报错", e);
        }
    }

    /**
     * 新增
     * @param hrRecruitEntryinfoEducation
     */
    @Override
    public void add(HrRecruitEntryinfoEducation hrRecruitEntryinfoEducation) {
        try {
            //新增
            hrRecruitEntryinfoEducationDao.insertSelective(hrRecruitEntryinfoEducation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("报名-教育经历新增报错", e);
        }
    }

    /**
     * 修改
     * @param hrRecruitEntryinfoEducation
     */
    @Override
    public void edit(HrRecruitEntryinfoEducation hrRecruitEntryinfoEducation) {
        try {
            hrRecruitEntryinfoEducationDao.updateByPrimaryKeySelective(hrRecruitEntryinfoEducation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("报名-教育经历修改报错", e);
        }
    }
}
