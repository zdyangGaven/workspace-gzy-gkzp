package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecuritWriteDao;
import com.nsoft.gkzp.plan.entity.HrRecuritWrite;
import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoBaseService;
import com.nsoft.gkzp.plan.service.HrRecuritWriteService;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class HrRecuritWriteServiceImpl implements HrRecuritWriteService {
    @Autowired
    HrRecuritWriteDao hrRecuritWriteDao;

    @Autowired
    HrRecruitEntryinfoBaseService hrRecruitEntryinfoBaseService;

    /**
     * 查询
     * @param hrRecuritWrite
     * @param order 排序
     * @param page 分页
     * @return
     */
    @Override
    public List<HrRecuritWrite> list(HrRecuritWrite hrRecuritWrite, String order, PageVo page) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecuritWrite.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(hrRecuritWrite);

        List<HrRecuritWrite> list = hrRecuritWriteDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecuritWrite> pageInfo = new PageInfo<HrRecuritWrite>(list);
        return list;
    }

    /**
     * 通过用户获取笔试数据
     * @param userContext 用户
     * @return
     */
    @Override
    public HrRecuritWrite getHrRecuritWriteByUser(UserContext userContext) {
        int baseId = hrRecruitEntryinfoBaseService.getBaseIdByUser(userContext);
        return getHrRecuritWriteByBaseId(baseId);
    }

    /**
     * 通过基础信息id获取笔试数据
     * @param baseId
     * @return
     */
    @Override
    public HrRecuritWrite getHrRecuritWriteByBaseId(int baseId) {
        HrRecuritWrite hrRecuritWrite = new HrRecuritWrite();
        hrRecuritWrite.setBaseid(baseId);
        List<HrRecuritWrite> hrRecuritWrites = list(hrRecuritWrite, "id DESC", null);
        //没有数据返回null
        if(hrRecuritWrites.size() == 0) return null;
        return hrRecuritWrites.get(0);
    }

    /**
     * 新增
     * @param hrRecuritWrite
     * @return
     */
    @Override
    public void add(HrRecuritWrite hrRecuritWrite) {
        hrRecuritWriteDao.insertSelective(hrRecuritWrite);
    }

    /**
     * 修改
     * @param hrRecuritWrite
     * @return
     */
    @Override
    public void edit(HrRecuritWrite hrRecuritWrite) {
        hrRecuritWriteDao.updateByPrimaryKeySelective(hrRecuritWrite);
    }
}
