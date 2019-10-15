package com.nsoft.gkzp.common.service.impl;

import com.nsoft.gkzp.common.dao.HrRecruitFileDao;
import com.nsoft.gkzp.common.entity.HrRecruitFile;
import com.nsoft.gkzp.common.service.HrRecruitFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HrRecruitFileServiceImpl implements HrRecruitFileService {

    @Autowired
    HrRecruitFileDao hrRecruitFileDao;

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @Override
    public HrRecruitFile getHrRecruitFileById(int id) {
        return hrRecruitFileDao.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param hrRecruitFile
     */
    @Override
    public void add(HrRecruitFile hrRecruitFile) {
        hrRecruitFileDao.insertSelective(hrRecruitFile);
    }
}
