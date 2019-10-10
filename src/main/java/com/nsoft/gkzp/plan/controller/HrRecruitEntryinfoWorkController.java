package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HrRecruitEntryinfoWorkController {
    @Autowired
    HrRecruitEntryinfoWorkService hrRecruitEntryinfoWorkService;
    //工作经历
    /**
     * 新增
     * @param hrRecruitEntryinfoWork
     * @return
     */
    /*@RequestMapping(value="HrRecruitEntryinfoWorkController/add",method= RequestMethod.POST)
    @ResponseBody
    public int add(HrRecruitEntryinfoWork hrRecruitEntryinfoWork){
        return hrRecruitEntryinfoWorkService.add(hrRecruitEntryinfoWork);
    }*/
}
