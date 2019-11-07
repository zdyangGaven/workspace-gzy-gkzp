package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecruitReviewRecordDao;
import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoBase;
import com.nsoft.gkzp.plan.entity.HrRecruitReviewRecord;
import com.nsoft.gkzp.plan.entity.HrRecruitReviewRecordVo;
import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoBaseService;
import com.nsoft.gkzp.plan.service.HrRecruitReviewRecordService;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HrRecruitReviewRecordServiceImpl implements HrRecruitReviewRecordService {
    @Autowired
    HrRecruitReviewRecordDao hrRecruitReviewRecordDao;

    @Autowired
    HrRecruitEntryinfoBaseService hrRecruitEntryinfoBaseService;

    /**
     * 查询
     * @param hrRecruitReviewRecord
     * @param order 排序
     * @param page 分页
     * @return
     */
    public List<HrRecruitReviewRecord> list(HrRecruitReviewRecord hrRecruitReviewRecord, String order, PageVo page){

        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecruitReviewRecord.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(hrRecruitReviewRecord);

        List<HrRecruitReviewRecord> list = hrRecruitReviewRecordDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecruitReviewRecord> pageInfo = new PageInfo<HrRecruitReviewRecord>(list);
        return list;
    }

    /**
     * 通过基础信息id获取资格审核数据
     * @param baseId
     * @return
     */
    @Override
    public HrRecruitReviewRecord getHrRecruitReviewRecordByBaseId(int baseId) {
        HrRecruitReviewRecord hrRecruitReviewRecord = new HrRecruitReviewRecord();
        hrRecruitReviewRecord.setBaseid(baseId);
        List<HrRecruitReviewRecord> hrRecruitReviewRecords = list(hrRecruitReviewRecord, "id DESC", null);
        //没有数据返回null
        if(hrRecruitReviewRecords.size() == 0) return null;
        return hrRecruitReviewRecords.get(0);
    }

    @Override
    public HrRecruitReviewRecordVo getHrRecruitReviewRecordVoByInfoBase(HrRecruitEntryinfoBase hrRecruitEntryinfoBase) {
        HrRecruitReviewRecordVo hrRecruitReviewRecordVo = new HrRecruitReviewRecordVo();
        //获取基础信息
        List<HrRecruitEntryinfoBase> hrRecruitEntryinfoBases = hrRecruitEntryinfoBaseService.list(hrRecruitEntryinfoBase, "id DESC", null);

        //判断基础信息为空退出
        if(hrRecruitEntryinfoBases.size() == 0) return null;

        hrRecruitReviewRecordVo.setHrRecruitEntryinfoBase(hrRecruitEntryinfoBases.get(0));

        //根据基础信息id获取资格审核
        int baseId = hrRecruitEntryinfoBases.get(0).getId();
        HrRecruitReviewRecord hrRecruitReviewRecord = new HrRecruitReviewRecord();
        hrRecruitReviewRecord.setBaseid(baseId);
        hrRecruitReviewRecord.setNode(4);
        System.out.println(hrRecruitReviewRecord);
        List<HrRecruitReviewRecord> hrRecruitReviewRecords = list(hrRecruitReviewRecord, null, null);
        System.out.println(hrRecruitReviewRecords);
        //判断资格审核信息为空返回
        if(hrRecruitReviewRecords.size() == 0) return hrRecruitReviewRecordVo;

        hrRecruitReviewRecordVo.setHrRecruitReviewRecord(hrRecruitReviewRecords.get(0));

        return hrRecruitReviewRecordVo;
    }

    @Override
    public HrRecruitReviewRecordVo getHrRecruitReviewRecordVoByUser(UserContext userContext) {
        HrRecruitEntryinfoBase hrRecruitEntryinfoBase = new HrRecruitEntryinfoBase();
        hrRecruitEntryinfoBase.setLoginuserid(userContext.getLoginUserId());
        return getHrRecruitReviewRecordVoByInfoBase(hrRecruitEntryinfoBase);
    }

    /**
     * 是否审核
     * @param baseId 基础信息id
     * @return  true 已开始审核
     */
    @Override
    public boolean isReview(int baseId) {
        HrRecruitReviewRecord hrRecruitReviewRecord = new HrRecruitReviewRecord();
        hrRecruitReviewRecord.setBaseid(baseId);
        List<HrRecruitReviewRecord> hrRecruitReviewRecords = hrRecruitReviewRecordDao.select(hrRecruitReviewRecord);
        if(hrRecruitReviewRecords.size() > 0) return  true;
        return false;
    }

    @Override
    public void add(HrRecruitReviewRecord hrRecruitReviewRecord) {
        hrRecruitReviewRecordDao.insertSelective(hrRecruitReviewRecord);
    }

    @Override
    public void edit(HrRecruitReviewRecord hrRecruitReviewRecord) {
        hrRecruitReviewRecordDao.updateByPrimaryKeySelective(hrRecruitReviewRecord);
    }
}
