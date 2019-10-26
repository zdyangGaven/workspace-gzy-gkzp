package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecruitHealthchkDao;
import com.nsoft.gkzp.plan.entity.HrRecruitHealthchk;
import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoBaseService;
import com.nsoft.gkzp.plan.service.HrRecruitHealthchkService;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HrRecruitHealthchkServiceImpl implements HrRecruitHealthchkService {
    @Autowired
    HrRecruitHealthchkDao hrRecruitHealthchkDao;

    @Autowired
    HrRecruitEntryinfoBaseService hrRecruitEntryinfoBaseService;

    /**
     * 查询
     * @param hrRecruitHealthchk
     * @param order 排序
     * @param page 分页
     * @return
     */
    @Override
    public List<HrRecruitHealthchk> list(HrRecruitHealthchk hrRecruitHealthchk, String order, Page page) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecruitHealthchk.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(hrRecruitHealthchk);

        List<HrRecruitHealthchk> list = hrRecruitHealthchkDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecruitHealthchk> pageInfo = new PageInfo<HrRecruitHealthchk>(list);
        return list;
    }

    /**
     * 通过用户获取体检数据
     * @param userContext 用户
     * @return
     */
    @Override
    public HrRecruitHealthchk getHrRecruitHealthchkByUser(UserContext userContext) {
        int baseId = hrRecruitEntryinfoBaseService.getBaseIdByUser(userContext);
        return getHrRecruitHealthchkByBaseId(baseId);
    }

    /**
     * 通过基础信息id获取体检数据
     * @param baseId
     * @return
     */
    @Override
    public HrRecruitHealthchk getHrRecruitHealthchkByBaseId(int baseId) {
        HrRecruitHealthchk hrRecruitHealthchk = new HrRecruitHealthchk();
        hrRecruitHealthchk.setBaseid(baseId);
        List<HrRecruitHealthchk> hrRecruitHealthchks = list(hrRecruitHealthchk, "id DESC", null);
        //没有数据返回null
        if(hrRecruitHealthchks.size() == 0) return null;
        return hrRecruitHealthchks.get(0);
    }

    /**
     * 新增
     * @param hrRecruitHealthchk
     */
    @Override
    public void add(HrRecruitHealthchk hrRecruitHealthchk) {
        hrRecruitHealthchkDao.insertSelective(hrRecruitHealthchk);
    }

    /**
     * 修改
     * @param hrRecruitHealthchk
     */
    @Override
    public void edit(HrRecruitHealthchk hrRecruitHealthchk) {
        hrRecruitHealthchkDao.updateByPrimaryKeySelective(hrRecruitHealthchk);
    }
}
