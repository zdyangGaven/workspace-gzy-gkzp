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
                //新增
                hrRecruitEntryinfoEducationDao.insertSelective(hrRecruitEntryinfoEducation);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("报名-教育经历新增报错", e);
        }
    }

    /**
     *
     * @param page  分页
     * @param hrRecruitEntryinfoEducation
     * @param order 排序
     * @return
     */
    @Override
    public List<HrRecruitEntryinfoEducation> list(Page page, HrRecruitEntryinfoEducation hrRecruitEntryinfoEducation, String order) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecruitEntryinfoEducation.class);
        //排序
        example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(hrRecruitEntryinfoEducation);

        List<HrRecruitEntryinfoEducation> list = hrRecruitEntryinfoEducationDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecruitEntryinfoEducation> pageInfo = new PageInfo<HrRecruitEntryinfoEducation>(list);
        return list;
    }
}
