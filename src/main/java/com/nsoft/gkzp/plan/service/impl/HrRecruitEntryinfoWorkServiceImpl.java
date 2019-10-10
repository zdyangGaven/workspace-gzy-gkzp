package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecruitEntryinfoWorkDao;
import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoWork;
import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoWorkService;
import com.nsoft.gkzp.syscore.service.ServiceException;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HrRecruitEntryinfoWorkServiceImpl implements HrRecruitEntryinfoWorkService {
    @Autowired
    HrRecruitEntryinfoWorkDao hrRecruitEntryinfoWorkDao;
    //工作经历

    /**
     * 查询
     * @param page 分页
     * @param hrRecruitEntryinfoWork
     * @param order 排序
     * @return
     */
    @Override
    public List<HrRecruitEntryinfoWork> list(Page page, HrRecruitEntryinfoWork hrRecruitEntryinfoWork, String order) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecruitEntryinfoWork.class);
        //排序
        example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(hrRecruitEntryinfoWork);

        List<HrRecruitEntryinfoWork> list = hrRecruitEntryinfoWorkDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecruitEntryinfoWork> pageInfo = new PageInfo<HrRecruitEntryinfoWork>(list);
        return list;
    }

    /**
     * 新增
     * @param hrRecruitEntryinfoWorks
     * @return
     */
    @Override
    public void add(List<HrRecruitEntryinfoWork> hrRecruitEntryinfoWorks, int baseId) {
        try {
            for (HrRecruitEntryinfoWork hrRecruitEntryinfoWork:hrRecruitEntryinfoWorks) {
                //添加基础id进行关联
                hrRecruitEntryinfoWork.setBaseid(baseId);
                //新增
                hrRecruitEntryinfoWorkDao.insertSelective(hrRecruitEntryinfoWork);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("报名-工作经历新增报错",e);
        }
    }
}
