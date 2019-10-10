package com.nsoft.gkzp.plan.entity;

import java.io.Serializable;
import java.util.List;

public class HrRecruitEntryinfo implements Serializable {
    //基础信息
    public HrRecruitEntryinfoBase baseInfo;
    //教育经历
    public List<HrRecruitEntryinfoEducation> educationInfo;
    //家庭成员
    public List<HrRecruitEntryinfoFamily> familyInfo;
    //其他信息
    public HrRecruitEntryinfoOther otherInfo;
    //工作经历
    public List<HrRecruitEntryinfoWork> workInfo;


    public HrRecruitEntryinfoOther getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(HrRecruitEntryinfoOther otherInfo) {
        this.otherInfo = otherInfo;
    }

    public List<HrRecruitEntryinfoWork> getWorkInfo() {
        return workInfo;
    }

    public void setWorkInfo(List<HrRecruitEntryinfoWork> workInfo) {
        this.workInfo = workInfo;
    }

    public List<HrRecruitEntryinfoEducation> getEducationInfo() {
        return educationInfo;
    }

    public void setEducationInfo(List<HrRecruitEntryinfoEducation> educationInfo) {
        this.educationInfo = educationInfo;
    }

    public HrRecruitEntryinfoBase getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(HrRecruitEntryinfoBase baseInfo) {
        this.baseInfo = baseInfo;
    }

    public List<HrRecruitEntryinfoFamily> getFamilyInfo() {
        return familyInfo;
    }

    public void setFamilyInfo(List<HrRecruitEntryinfoFamily> familyInfo) {
        this.familyInfo = familyInfo;
    }
}
