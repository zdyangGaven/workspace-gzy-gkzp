package com.nsoft.gkzp.plan.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecruitNoticeDao;
import com.nsoft.gkzp.plan.entity.HrRecruitNotice;
import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoBaseService;
import com.nsoft.gkzp.plan.service.HrRecruitNoticeService;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HrRecruitNoticeServiceImpl implements HrRecruitNoticeService {

    @Autowired
    HrRecruitNoticeDao hrRecruitNoticeDao;

    //基础信息
    @Autowired
    HrRecruitEntryinfoBaseService hrRecruitEntryinfoBaseService;

    /**
     * 查询
     * @param hrRecruitNotice
     * @param order 排序
     * @param page 分页
     * @return
     */
    @Override
    public List<HrRecruitNotice> list(HrRecruitNotice hrRecruitNotice, String order, Page page) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecruitNotice.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(hrRecruitNotice);

        List<HrRecruitNotice> list = hrRecruitNoticeDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecruitNotice> pageInfo = new PageInfo<HrRecruitNotice>(list);
        return list;
    }

    /**
     * 获取未读数
     * @param user
     * @return
     */
    @Override
    public int noticeInt(UserContext user) {
        HrRecruitNotice hrRecruitNotice = new HrRecruitNotice();
        //获取基础信息id
        int baseId = hrRecruitEntryinfoBaseService.getBaseIdByUser(user);
        //筛选
        //基础id
        hrRecruitNotice.setBaseid(baseId);
        //状态为正常
        hrRecruitNotice.setStatus(1);
        //未读消息
        hrRecruitNotice.setHasread(2);
        List<HrRecruitNotice> list = list(hrRecruitNotice, null, null);
        return list.size();
    }


    /**
     * 设为已读
     * @param id
     */
    @Override
    public void read(int id) {
        HrRecruitNotice hrRecruitNotice = new HrRecruitNotice();
        hrRecruitNotice.setId(id);
        hrRecruitNotice.setHasread(1);
        hrRecruitNoticeDao.updateByPrimaryKeySelective(hrRecruitNotice);
    }

    /**
     * 用户所有设为已读
     * @param userContext
     */
    @Override
    public void userReadAll(UserContext userContext) {
        //用户筛选
        int baseId = hrRecruitEntryinfoBaseService.getBaseIdByUser(userContext);
        Example example = new Example(HrRecruitNotice.class);
        example.createCriteria().andEqualTo("baseid",baseId);

        //进行已读设置
        HrRecruitNotice hrRecruitNotice = new HrRecruitNotice();
        hrRecruitNotice.setHasread(1);
        hrRecruitNoticeDao.updateByExampleSelective(hrRecruitNotice,example);
    }
}
