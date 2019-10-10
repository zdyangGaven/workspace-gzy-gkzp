package com.nsoft.gkzp.plan.entity;

import java.io.Serializable;
import java.util.List;

public class HrRecruitEntryinfoEducationList implements Serializable {
    private List<HrRecruitEntryinfoEducation> list;

    public List<HrRecruitEntryinfoEducation> getList() {
        return list;
    }

    public void setList(List<HrRecruitEntryinfoEducation> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "HrRecruitEntryinfoEducationList{" +
                "list=" + list +
                '}';
    }
}