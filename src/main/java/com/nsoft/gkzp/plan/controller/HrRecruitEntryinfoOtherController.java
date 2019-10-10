package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HrRecruitEntryinfoOtherController {
    @Autowired
    HrRecruitEntryinfoOtherService hrRecruitEntryinfoOtherService;

    //其他信息

    /**
     * 新增
     * @param hrRecruitEntryinfoOther
     * @return
     */
    /*@RequestMapping(value="HrRecruitEntryinfoOtherController/add",method= RequestMethod.POST)
    @ResponseBody
    public int add(HrRecruitEntryinfoOther hrRecruitEntryinfoOther){
        return hrRecruitEntryinfoOtherService.add(hrRecruitEntryinfoOther);
    }*/
}
