package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecruitReviewRecordDao;
import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfoBase;
import com.nsoft.gkzp.plan.entity.HrRecruitReviewRecord;
import com.nsoft.gkzp.plan.entity.HrRecruitReviewRecordVo;
import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoBaseService;
import com.nsoft.gkzp.plan.service.HrRecruitReviewRecordService;
import com.nsoft.gkzp.util.Page;
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

    public List<HrRecruitReviewRecord> list( HrRecruitReviewRecord hrRecruitReviewRecord, String order,Page page){

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

    @Override
    public HrRecruitReviewRecordVo getHrRecruitReviewRecordVoByName(String name) {
        HrRecruitReviewRecordVo hrRecruitReviewRecordVo = new HrRecruitReviewRecordVo();
        //获取基础信息
        HrRecruitEntryinfoBase hrRecruitEntryinfoBase = new HrRecruitEntryinfoBase();
        hrRecruitEntryinfoBase.setName(name);
        List<HrRecruitEntryinfoBase> hrRecruitEntryinfoBases = hrRecruitEntryinfoBaseService.list(hrRecruitEntryinfoBase, "id DESC", null);

        //判断基础信息为空退出
        if(hrRecruitEntryinfoBases.size() == 0) return null;

        hrRecruitReviewRecordVo.setHrRecruitEntryinfoBase(hrRecruitEntryinfoBases.get(0));

        //根据基础信息id获取资格审核
        int baseId = hrRecruitEntryinfoBases.get(0).getId();
        HrRecruitReviewRecord hrRecruitReviewRecord = new HrRecruitReviewRecord();
        hrRecruitReviewRecord.setBaseid(baseId);
        List<HrRecruitReviewRecord> hrRecruitReviewRecords = list(hrRecruitReviewRecord, null, null);

        //判断资格审核信息为空返回
        if(hrRecruitReviewRecords.size() == 0) return hrRecruitReviewRecordVo;

        hrRecruitReviewRecordVo.setHrRecruitReviewRecord(hrRecruitReviewRecords.get(0));

        return hrRecruitReviewRecordVo;
    }
}
