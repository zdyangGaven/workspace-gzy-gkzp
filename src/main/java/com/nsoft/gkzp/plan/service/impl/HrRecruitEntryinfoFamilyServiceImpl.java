package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecruitEntryinfoFamilyDao;
import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoFamily;
import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoFamilyService;
import com.nsoft.gkzp.syscore.service.AbstractService;
import com.nsoft.gkzp.syscore.service.ServiceException;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HrRecruitEntryinfoFamilyServiceImpl extends AbstractService implements HrRecruitEntryinfoFamilyService {
    @Autowired
    HrRecruitEntryinfoFamilyDao hrRecruitEntryinfoFamilyDao;

    //家庭成员

    /**
     * 查询
     * @param page 分页
     * @param hrRecruitEntryinfoFamily
     * @param order 排序
     * @return
     */
    @Override
    public List<HrRecruitEntryinfoFamily> list( HrRecruitEntryinfoFamily hrRecruitEntryinfoFamily, String order,Page page) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecruitEntryinfoFamily.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(hrRecruitEntryinfoFamily);

        List<HrRecruitEntryinfoFamily> list = hrRecruitEntryinfoFamilyDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecruitEntryinfoFamily> pageInfo = new PageInfo<HrRecruitEntryinfoFamily>(list);
        return list;
    }

    /**
     * 新增
     * @param hrRecruitEntryinfoFamilys
     * @return
     */
    @Override
    public void add(List<HrRecruitEntryinfoFamily> hrRecruitEntryinfoFamilys, int baseId) throws  ServiceException{
        try {
            for (HrRecruitEntryinfoFamily hrRecruitEntryinfoFamily: hrRecruitEntryinfoFamilys) {
                //添加基础id进行关联
                hrRecruitEntryinfoFamily.setBaseid(baseId);
                //新增
                hrRecruitEntryinfoFamilyDao.insertSelective(hrRecruitEntryinfoFamily);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("报名-家庭成员新增报错",e);

        }

    }

    /**
     * 新增
     * @param hrRecruitEntryinfoFamily
     */
    @Override
    public void add(HrRecruitEntryinfoFamily hrRecruitEntryinfoFamily) {
        try {
            //新增
            hrRecruitEntryinfoFamilyDao.insertSelective(hrRecruitEntryinfoFamily);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("报名-家庭成员新增报错",e);

        }
    }

    /**
     * 修改
     * @param hrRecruitEntryinfoFamily
     */
    @Override
    public void edit(HrRecruitEntryinfoFamily hrRecruitEntryinfoFamily) {
        try {
            hrRecruitEntryinfoFamilyDao.updateByPrimaryKeySelective(hrRecruitEntryinfoFamily);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("报名-家庭成员修改报错",e);

        }
    }
}
