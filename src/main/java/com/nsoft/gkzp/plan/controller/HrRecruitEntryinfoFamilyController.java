package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HrRecruitEntryinfoFamilyController {
    @Autowired
    HrRecruitEntryinfoFamilyService hrRecruitEntryinfoFamilyService;
    //家庭成员
    /**
     * 新增
     * @param hrRecruitEntryinfoFamily
     * @return
     */
    /*@RequestMapping(value="HrRecruitEntryinfoFamilyController/add",method= RequestMethod.POST)
    @ResponseBody
    public int add(HrRecruitEntryinfoFamily hrRecruitEntryinfoFamily){
        return hrRecruitEntryinfoFamilyService.add(hrRecruitEntryinfoFamily);
    }*/
}
