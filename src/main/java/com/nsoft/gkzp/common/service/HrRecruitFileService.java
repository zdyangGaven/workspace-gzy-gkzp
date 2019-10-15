package com.nsoft.gkzp.common.service;

import com.nsoft.gkzp.common.entity.HrRecruitFile;

public interface HrRecruitFileService {
    /**
     * 通过id查询
     * @param id
     * @return
     */
    public HrRecruitFile getHrRecruitFileById(int id);

    /**
     * 新增
     * @param hrRecruitFile
     */
    public void add(HrRecruitFile hrRecruitFile);
}
